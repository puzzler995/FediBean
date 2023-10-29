package dev.puzzler995.fedibean.data.modules.processors;

import dev.puzzler995.fedibean.data.model.Job;
import dev.puzzler995.fedibean.data.modules.services.JobQueueService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobProcessor {
    @Autowired
    private JobQueueService jobQueueService;

    @Autowired private RedisTemplate<String, Job> redisTemplate;

    public void enqueue(Job job) {
        jobQueueService.enqueue(job);
    }

    @PostConstruct
    public void startProcessingJobs() {
        while (true) {
            Job job = jobQueueService.dequeue();
            if (job != null) {
                jobQueueService.processJob(job);
            }
        }
    }
}
