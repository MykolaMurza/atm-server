package ua.learning.atmserver.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.learning.atmserver.entity.*;
import ua.learning.atmserver.entity.enums.Status;
import ua.learning.atmserver.repository.*;
import ua.learning.atmserver.util.AtmAuditor;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AtmServiceImpl implements AtmService {
    private final AtmAuditor atmAuditor;
    private final AtmRepository atmRepository;
    private final BiometricRepository biometricRepository;
    private final CardRepository cardRepository;
    private final ClientRepository clientRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public Boolean verifyClientCard(int atmId, String cardNumber) {
        Card card = cardRepository.findCardByNumber(cardNumber);

        if (card != null && card.getStatus() == Status.ACTIVE) {
            Account account = card.getAccount();
            if (account.getStatus() == Status.ACTIVE) {
                Client client = account.getClient();
                return client.getStatus() == Status.ACTIVE;
            }
        }

        return false;
    }

    @Override
    public Boolean verifyClientPIN(int clientId, int atmId, String cardNumber, byte[] pinBytes) {
        Card card = cardRepository.findCardByNumber(cardNumber);
        Client client = clientRepository.getReferenceById(clientId);
        Atm atm = atmRepository.getReferenceById(atmId);

        atmAuditor.log("AUTH_PIN", client, atm);

        return card.getPIN().equals(new String(pinBytes, StandardCharsets.UTF_8));
    }

    @Override
    public byte[] getClientBiometrics(int clientId, int atmId) {
        Biometric biometric = biometricRepository.findBiometricByClientId(clientId);
        Client client = biometric.getClient();
        Atm atm = atmRepository.getReferenceById(atmId);

        atmAuditor.log("AUTH_BIO", client, atm);

        return biometric.getFingerprint();
    }

    @Override
    public void biometricsStatusAcknowledge(int clientId, int atmId, String cardNumber, boolean passed) {
        Client client = clientRepository.getReferenceById(clientId);
        Atm atm = atmRepository.getReferenceById(atmId);

        atmAuditor.log("ACK", client, atm);
    }

    @Override
    @Transactional
    public boolean saveTransaction(int clientId, int atmId, int amount, String action) {
        Client client = clientRepository.getReferenceById(clientId);
        Atm atm = atmRepository.getReferenceById(atmId);

        Transaction transaction = new Transaction();
        transaction.setClient(client);
        transaction.setAtm(atm);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());

        atmAuditor.log(action, client, atm);
        transactionRepository.saveAndFlush(transaction);

        return true;
    }
}
