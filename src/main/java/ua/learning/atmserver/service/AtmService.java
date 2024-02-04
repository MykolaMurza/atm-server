package ua.learning.atmserver.service;

import ua.learning.atmserver.entity.Client;
import ua.learning.atmserver.entity.Transaction;

public interface AtmService {
    Client getClientDataByAccount(String account, int atmId);

    Boolean verifyClientPIN(int clientId, int atmId, byte[] pin);

    Boolean verifyClientOTP(int clientId, int atmId, byte[] otp);

    byte[] getClientBiometrics(int clientId, int atmId);

    Transaction saveTransaction(int clientId, int atmId, int amount, String action);
}
