package ua.learning.atmserver.service;

import ua.learning.atmserver.entity.Client;
import ua.learning.atmserver.entity.Transaction;

public interface AtmService {
    Client getClientDataByAccount(String account, int atmId);

    Boolean verifyClientBiometrics(int clientId, int atmId, byte[] biometrics);

    Transaction makeTransaction(int clientId, int atmId, int amount, String action);
}
