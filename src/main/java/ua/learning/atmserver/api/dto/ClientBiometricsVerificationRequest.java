package ua.learning.atmserver.api.dto;

import lombok.Data;

@Data
public class ClientBiometricsVerificationRequest {
    private int clientId;
    private int atmId;
    private byte[] biometrics;
}
