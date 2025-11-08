package io.interview.config;

import lombok.Data;

@Data
public class ClientConfig {
    int tokenPerSecond;
    int burstSize;
    int maxRetries;
    long acquireTimeoutMs;
}
