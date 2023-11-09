package dev.puzzler995.fedibean.data.modules.services;

// @Service
public class JobQueueService {
  private static final int MAX_RETRIES = 3;
  private static final String QUEUE_NAME = "jobQueue";

  //  @Autowired RedisTemplate<String, Job> redisTemplate;
  //  @Autowired TaskExecutor taskExecutor;
  //  @Autowired WebClient webClient;

  //  public void enqueue(Job job) {
  //    redisTemplate.opsForList().leftPush(QUEUE_NAME, job);
  //  }
  //
  //  public Job dequeue() {
  //    return redisTemplate.opsForList().rightPop(QUEUE_NAME);
  //  }
  //
  //  public void processJob(Job job) {
  //    taskExecutor.execute(
  //        () -> {
  //          // TODO: Process job
  //          // TODO: move to a retrytemplate
  //          // TODO: This... probably isn't correct
  //          AtomicInteger retries = new AtomicInteger();
  //          AtomicReference<Boolean> success = new AtomicReference<>(Boolean.FALSE);
  //          while (retries.get() < MAX_RETRIES && Boolean.TRUE.equals(success.get())) {
  //            try {
  //              webClient
  //                  .post()
  //                  .uri(job.getDestination())
  //                  .bodyValue(BodyInserters.fromValue(job.getPayload()))
  //                  .retrieve()
  //                  .toEntity(String.class)
  //                  .subscribe(
  //                      responseEntity -> {
  //                        // Success
  //                        HttpStatusCode status = responseEntity.getStatusCode();
  //                        if (status.is2xxSuccessful()) {
  //                          success.set(Boolean.TRUE);
  //                        } else {
  //                          retries.getAndIncrement();
  //                        }
  //                      },
  //                      error -> {
  //                        // Failure
  //                        retries.getAndIncrement();
  //                      });
  //              success.set(Boolean.TRUE);
  //            } catch (Exception e) {
  //              retries.getAndIncrement();
  //            }
  //          }
  //          if (!success.get()) {
  //            // TODO: Log failure
  //          }
  //        });
  //  }
}
