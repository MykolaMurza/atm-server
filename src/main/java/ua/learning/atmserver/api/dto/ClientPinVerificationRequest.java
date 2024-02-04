package ua.learning.atmserver.api.dto;

import lombok.Data;

@Data
public class ClientPinVerificationRequest {
    private int clientId;
    private int atmId;
    private byte[] pin;
}
