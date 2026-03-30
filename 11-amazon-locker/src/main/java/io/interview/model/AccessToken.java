package io.interview.model;

import io.interview.LockerUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class AccessToken {
    private UUID code;
    private LocalDateTime expiration;
    private Compartment compartment;

    public AccessToken(UUID code, LocalDateTime expiration, Compartment compartment) {
        this.code = code;
        this.expiration = expiration;
        this.compartment = compartment;
    }

    /**
     * Checks whether the token is expired or not
     * @return true: expired, false: valid
     */
    public boolean isExpired() {
        return Duration.between(LocalDateTime.now(), expiration).toDays() >= LockerUtils.TOKEN_EXPIRATION_DAYS;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public UUID getCode() {
        return code;
    }

    public Compartment getCompartment() {
        return compartment;
    }
}
