package ua.learning.atmserver.api.dto;

import lombok.Data;

@Data
public class BiometricsStatusAcknowledgeRequest {
    private int clientId;
    private int atmId;
    private String cardNumber;
    private boolean passed;
}
