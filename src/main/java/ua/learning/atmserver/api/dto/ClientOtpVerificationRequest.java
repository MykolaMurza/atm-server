package ua.learning.atmserver.api.dto;

import lombok.Data;

@Data
public class ClientOtpVerificationRequest {
    private int clientId;
    private int atmId;
    private byte[] otp;
}
