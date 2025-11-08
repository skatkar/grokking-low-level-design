package io.interview.token;


public class BackoffStrategy {
    public static long calculateBackoff(int attempt) {
        long base = 50;
        long max = 3000;

        long exp = (long) (base + Math.pow(2, attempt));
        long jitter = (long) (Math.random() * 100);

        return Math.min(max, exp + jitter);
    }

}
