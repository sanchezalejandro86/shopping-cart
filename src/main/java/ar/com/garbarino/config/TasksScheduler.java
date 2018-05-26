package ar.com.garbarino.config;

import ar.com.garbarino.task.ProcessCartTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Created by alejandro on 26/05/18.
 */
@Configuration
public class TasksScheduler {
    @Autowired
    private ProcessCartTask task;

    @Value("${cart.tasks.payments.frecuency:5}")
    private int minutes;

    @Autowired
    ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @PostConstruct
    public void schedule() {
        threadPoolTaskScheduler.schedule(task, new PeriodicTrigger(minutes, TimeUnit.MINUTES));
    }

    public void setTask(ProcessCartTask task) {
        this.task = task;
    }
}
