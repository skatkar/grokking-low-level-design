package io.interview.client;

import io.interview.config.ClientConfig;
import io.interview.exception.RateLimitExceededException;
import io.interview.model.Request;
import io.interview.model.Response;
import io.interview.token.BackoffStrategy;
import io.interview.token.TokenBucketLimiter;

public class ExternalClient {
    private ClientConfig config;
    private TokenBucketLimiter limiter;

    public ExternalClient(ClientConfig config){
        this.config = config;
        this.limiter = new TokenBucketLimiter(config.getTokenPerSecond(), config.getBurstSize());
    }

    public Response call(Request request) throws RateLimitExceededException {
        if(!limiter.tryAcquire(config.getAcquireTimeoutMs())){
            throw new RateLimitExceededException();
        }

        for(int attempt = 1; attempt < config.getMaxRetries(); attempt++) {
            try{
                return makeActualCall(request);
            }catch(Exception exc) {
                long sleep = BackoffStrategy.calculateBackoff(attempt);

                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        throw new RuntimeException("Max retries exhausted");
    }

    private Response makeActualCall(Request request) {
        return new Response();
    }
}
