package ua.learning.atmserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.learning.atmserver.entity.*;
import ua.learning.atmserver.repository.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AtmServiceImpl implements AtmService {
    private final AtmRepository atmRepository;
    private final AtmAuditLogRepository auditLogRepository;
    private final BiometricRepository biometricRepository;
    private final ClientRepository clientRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public Client getClientDataByAccount(String account, int atmId) {
        Client client = clientRepository.findClientByAccountNumber(account);
        Atm atm = atmRepository.getReferenceById(atmId);

        saveAuditLog("Отримання даних по клієнту", client, atm);

        return client;
    }

    @Override
    public Boolean verifyClientBiometrics(int clientId, int atmId, byte[] biometrics) {
        Biometric biometric = biometricRepository.findBiometricByClientClientId(clientId);
        Client client = biometric.getClient();
        Atm atm = atmRepository.getReferenceById(atmId);

        saveAuditLog("Верифікація відбитка пальця клієнта", client, atm);

        //TODO: Змінити цей код на справжню верифікацію відбитка
        return Arrays.equals(biometric.getBiometricData(), biometrics);
    }

    @Override
    public Transaction makeTransaction(int clientId, int atmId, int amount, String action) {
        Client client = clientRepository.getReferenceById(clientId);
        Atm atm = atmRepository.getReferenceById(atmId);

        Transaction transaction = new Transaction();
        transaction.setClient(client);
        transaction.setAtm(atm);
        transaction.setAmount(amount);
        transaction.setType(action);
        transaction.setTimestamp(Timestamp.from(Instant.now()));

        saveAuditLog("Виконання транзакції клієнтом", client, atm);

        return transactionRepository.saveAndFlush(transaction);
    }

    private void saveAuditLog(String action, Client client, Atm atm) {
        AtmAuditLog log = new AtmAuditLog();
        log.setAction(action);
        log.setTimestamp(Timestamp.from(Instant.now()));
        log.setClient(client);
        log.setAtm(atm);
        auditLogRepository.save(log);
    }
}
