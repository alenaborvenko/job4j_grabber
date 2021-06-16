package ru.job4j.grabber;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ru.job4j.grabber.Post;

import java.util.List;

public class GrabJob implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap map = context.getJobDetail().getJobDataMap();
        Store store = (Store) map.get("store");
        Parse parse = (Parse) map.get("parse");
        String link = (String) map.get("link");
        try {
            List<Post> posts = parse.list(link);
            for (Post post: posts ) {
                store.save(post);
                System.out.println(post);
            }
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }
}
