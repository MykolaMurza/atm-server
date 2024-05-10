package ua.learning.atmserver.api.dto;

import lombok.Data;

@Data
public class CardVerificationRequest {
    private int atmId;
    private String cardNumber;
}
