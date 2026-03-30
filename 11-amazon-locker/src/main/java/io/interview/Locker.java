package io.interview;

import io.interview.model.AccessToken;
import io.interview.model.Compartment;
import io.interview.model.Size;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Locker {
    private List<Compartment> compartments;
    private Map<UUID, AccessToken> accessTokenMapping;

    public Locker(List<Compartment> compartments) {
        this.compartments = compartments; // This list is created at the application startup time
        this.accessTokenMapping = new HashMap<>();
    }

    /**
     * Delivery personnel deposits the package and the access token is generated for the customer
     * @param size - Compartment size
     * @return accessTokenCode
     */
    public UUID depositPackage(Size size) {
        // Get the available compartment
        Compartment availableCompartment = getAvailableCompartment(size);
        if(availableCompartment == null) {
            throw new IllegalArgumentException("Compartment not available");
        }
        // The available compartment is now occupied
        availableCompartment.markOccupied();

        // Generate an access token and map it to this compartment
        AccessToken accessToken = generateAccessToken(availableCompartment);
        accessTokenMapping.put(accessToken.getCode(), accessToken);
        return accessToken.getCode();
    }

    private AccessToken generateAccessToken(Compartment compartment) {
        UUID tokenCode = UUID.randomUUID();
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(LockerUtils.TOKEN_EXPIRATION_DAYS);
        return new AccessToken(tokenCode, expirationDate, compartment);
    }

    private Compartment getAvailableCompartment(Size size) {
        for(Compartment compartment : compartments) {
            if(!compartment.isOccupied() && compartment.getSize() == size) {
                return compartment;
            }
        }
        return null;
    }

    /**
     * Performing an action when a customer picks up the package
     * @param tokenCode - Access token to open the compartment
     */
    public void pickup(UUID tokenCode) {
        if(tokenCode == null || tokenCode.toString().isEmpty()) {
            throw new IllegalArgumentException("Invalid tokenCode");
        }
        AccessToken accessToken = accessTokenMapping.get(tokenCode);
        if(accessToken == null) {
            throw new IllegalArgumentException("Access token does not exist");
        }
        Compartment compartment = accessToken.getCompartment();
        compartment.open();
        clearDeposit(accessToken);
    }

    private void clearDeposit(AccessToken accessToken) {
        // Get the compartment associated with this access token
        Compartment compartmentToClear = accessToken.getCompartment();

        // Mark it as free
        compartmentToClear.markFree();

        // Remove the access token mapping
        accessTokenMapping.remove(accessToken.getCode());
    }

    /**
     * Opens compartments with expired access tokens
     */
    public void openExpiredCompartments() {
        for(AccessToken accessToken : accessTokenMapping.values()) {
            if(accessToken.isExpired()) {
                Compartment compartmentToOpen = accessToken.getCompartment();
                compartmentToOpen.open();
            }
        }
    }
}
