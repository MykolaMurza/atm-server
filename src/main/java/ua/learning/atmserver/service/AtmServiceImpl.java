package ua.learning.atmserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.learning.atmserver.entity.*;
import ua.learning.atmserver.repository.*;
import ua.learning.atmserver.util.AtmAuditor;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AtmServiceImpl implements AtmService {
    private final AtmRepository atmRepository;
    private final BiometricRepository biometricRepository;
    private final ClientRepository clientRepository;
    private final TransactionRepository transactionRepository;
    private final OtpRepository otpRepository;
    private final AtmAuditor atmAuditor;

    @Override
    public Client getClientDataByAccount(String account, int atmId) {
        Client client = clientRepository.findClientByAccountNumber(account);
        Atm atm = atmRepository.getReferenceById(atmId);

        atmAuditor.log("Отримання даних по клієнту", client, atm);

        return client;
    }

    @Override
    public Boolean verifyClientPIN(int clientId, int atmId, byte[] pinBytes) {
        Client client = clientRepository.getReferenceById(clientId);
        Atm atm = atmRepository.getReferenceById(atmId);

        atmAuditor.log("Автентифікація PIN клієнта", client, atm);

        return client.getPin().equals(new String(pinBytes, StandardCharsets.UTF_8));
    }

    @Override
    public Boolean verifyClientOTP(int clientId, int atmId, byte[] otpBytes) {
        Client client = clientRepository.getReferenceById(clientId);
        Atm atm = atmRepository.getReferenceById(atmId);
        Otp otp = otpRepository.findOtpByClientClientIdAndAtmAtmId(clientId, atmId);

        atmAuditor.log("Автентифікація OTP клієнта", client, atm);

        return otp.getOtpCode().equals(new String(otpBytes, StandardCharsets.UTF_8));
    }

    @Override
    public byte[] getClientBiometrics(int clientId, int atmId) {
        Biometric biometric = biometricRepository.findBiometricByClientClientId(clientId);
        Client client = biometric.getClient();
        Atm atm = atmRepository.getReferenceById(atmId);

        atmAuditor.log("Отримання біометричних даних клієнта", client, atm);

        return biometric.getBiometricData();
    }

    @Override
    public Transaction saveTransaction(int clientId, int atmId, int amount, String action) {
        Client client = clientRepository.getReferenceById(clientId);
        Atm atm = atmRepository.getReferenceById(atmId);

        Transaction transaction = new Transaction();
        transaction.setClient(client);
        transaction.setAtm(atm);
        transaction.setAmount(amount);
        transaction.setType(action);
        transaction.setTimestamp(Timestamp.from(Instant.now()));

        atmAuditor.log("Виконання транзакції клієнтом", client, atm);

        return transactionRepository.saveAndFlush(transaction);
    }
}
