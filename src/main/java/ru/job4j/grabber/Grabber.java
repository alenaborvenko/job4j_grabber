package ru.job4j.grabber;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import ru.job4j.utils.SqlRuDateTimeParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class Grabber implements Grab {
    private final Properties config = new Properties();

    public Store store() {
        return new MemStore();
        //return new PsqlStore(config);
    }

    public Scheduler scheduler() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        return scheduler;
    }

    public void config() throws IOException {
        try (InputStream in = new FileInputStream("src/main/resources/app.properties")) {
            config.load(in);
        }
    }

    @Override
    public void init(Parse parse, Store store, Scheduler scheduler) throws SchedulerException {
        JobDataMap data = new JobDataMap();
        data.put("store", store);
        data.put("parse", parse);
        data.put("link", config.getProperty("link"));
        JobDetail job = newJob(GrabJob.class)
                .usingJobData(data)
                .build();
        SimpleScheduleBuilder times = simpleSchedule()
                .withIntervalInSeconds(Integer.parseInt(config.getProperty("time")))
                .repeatForever();
        Trigger trigger = newTrigger().startNow().withSchedule(times).build();
        scheduler.scheduleJob(job, trigger);
    }

    public void web(Store store) {
        new Thread(() -> {
            try (ServerSocket server = new ServerSocket(Integer.parseInt(config.getProperty("port")))) {
                while (!server.isClosed()) {
                    Socket socket = server.accept();
                    System.out.println("Server start");
                    try (OutputStream out = socket.getOutputStream()) {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        for (Post post : store.getAll()) {
                            out.write(post.toString().getBytes());
                            out.write(System.lineSeparator().getBytes());
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) throws IOException, SchedulerException {
        Grabber grab = new Grabber();
        grab.config();
        Scheduler scheduler = grab.scheduler();
        Store store = grab.store();
        grab.init(new SqlRuParse(new SqlRuDateTimeParser()), store, scheduler);
        grab.web(store);
    }
}
