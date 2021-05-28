package ru.job4j.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Rabbit implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("Rabbit starts...");
    }
}
