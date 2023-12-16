package ua.learning.atmserver.api.dto;

import lombok.Data;

@Data
public class TransactionRequest {
    private int clientId;
    private int atmId;
    private int amount;
    private String action;
}
