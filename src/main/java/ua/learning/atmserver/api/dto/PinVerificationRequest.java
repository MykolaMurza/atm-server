package ua.learning.atmserver.api.dto;

import lombok.Data;

@Data
public class PinVerificationRequest {
    private int clientId;
    private int atmId;
    private String cardNumber;
    private byte[] pin;
}
