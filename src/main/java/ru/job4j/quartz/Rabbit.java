package ru.job4j.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.System.currentTimeMillis;

public class Rabbit implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("Rabbit starts...");
        Connection cn = (Connection) jobExecutionContext.getJobDetail()
                .getJobDataMap().get("connection");
        try (PreparedStatement preparedStatement =
                     cn.prepareStatement("insert into rabbit (create_date) values (?)")) {
            preparedStatement.setLong(1, currentTimeMillis());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
