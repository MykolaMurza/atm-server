package ua.learning.atmserver.service;

public interface AtmService {
    Boolean verifyClientCard(int atmId, String cardNumber);

    Boolean verifyClientPIN(int clientId, int atmId, String cardNumber, byte[] pin);

    byte[] getClientBiometrics(int clientId, int atmId);

    boolean saveTransaction(int clientId, int atmId, int amount, String action);

    void biometricsStatusAcknowledge(int clientId, int atmId, String cardNumber, boolean passed);
}
