package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class AlertRabbit {
    public static void main(String[] args) {
        try {
            Properties config = new Properties();
            try (BufferedReader br = new BufferedReader(
                    new FileReader("./src/main/java/ru/job4j/quartz/rabbit.properties"))) {
                config.load(br);
            }
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = newJob(Rabbit.class).build();
            SimpleScheduleBuilder times = simpleSchedule().withIntervalInSeconds(
                    Integer.parseInt(config.getProperty("rabbit.interval")))
                    .repeatForever();
            Trigger trigger = newTrigger().startNow().withSchedule(times).build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException | IOException e) {
            e.printStackTrace();
        }
    }
}
