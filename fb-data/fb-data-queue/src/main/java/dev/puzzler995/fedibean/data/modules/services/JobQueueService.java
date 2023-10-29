package dev.puzzler995.fedibean.data.modules.services;

import dev.puzzler995.fedibean.data.model.Job;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class JobQueueService {
  private static final String QUEUE_NAME = "jobQueue";
  private static final int MAX_RETRIES = 3;

  @Autowired RedisTemplate<String, Job> redisTemplate;
  @Autowired TaskExecutor taskExecutor;
  @Autowired WebClient webClient;

  public void enqueue(Job job) {
    redisTemplate.opsForList().leftPush(QUEUE_NAME, job);
  }

  public Job dequeue() {
    return redisTemplate.opsForList().rightPop(QUEUE_NAME);
  }

  public void processJob(Job job) {
    taskExecutor.execute(
        () -> {
          // TODO: Process job
          // TODO: move to a retrytemplate
          // TODO: This... probably isn't correct
          AtomicInteger retries = new AtomicInteger();
          AtomicReference<Boolean> success = new AtomicReference<>(Boolean.FALSE);
          while (retries.get() < MAX_RETRIES && Boolean.TRUE.equals(success.get())) {
            try {
              webClient
                  .post()
                  .uri(job.getDestination())
                  .bodyValue(BodyInserters.fromValue(job.getPayload()))
                  .retrieve()
                  .toEntity(String.class)
                  .subscribe(
                      responseEntity -> {
                        // Success
                        HttpStatusCode status = responseEntity.getStatusCode();
                        if (status.is2xxSuccessful()) {
                          success.set(Boolean.TRUE);
                        } else {
                          retries.getAndIncrement();
                        }
                      },
                      error -> {
                        // Failure
                        retries.getAndIncrement();
                      });
              success.set(Boolean.TRUE);
            } catch (Exception e) {
              retries.getAndIncrement();
            }
          }
          if (!success.get()) {
            // TODO: Log failure
          }
        });
  }
}
