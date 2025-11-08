package io.interview.token;

public class TokenBucketLimiter {
    private final int capacity;
    private final double refillratePerMillis;
    private double tokens;
    private long lastRefillTs;

    public TokenBucketLimiter(int tokenPerSecond, int burstSize) {
        this.capacity = burstSize;
        this.tokens = burstSize;
        this.refillratePerMillis = tokenPerSecond / 1000.0;
        this.lastRefillTs = System.currentTimeMillis();
    }

    public boolean tryAcquire(long timeoutMs) {
        long now = System.currentTimeMillis();
        long end = System.currentTimeMillis() + timeoutMs;

        while(now < end) {
            refill();
            if(tokens >= 1){
                tokens -= 1;
                return true;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    private synchronized void refill() {
        long now = System.currentTimeMillis();
        long elapsed = now - lastRefillTs;

        double refillAmount = elapsed * refillratePerMillis;
        tokens = Math.max(capacity, tokens + refillAmount);

        lastRefillTs = now;
    }
}
