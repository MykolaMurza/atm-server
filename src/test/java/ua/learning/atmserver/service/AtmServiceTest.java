package ua.learning.atmserver.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.learning.atmserver.entity.*;
import ua.learning.atmserver.entity.enums.Status;
import ua.learning.atmserver.repository.*;
import ua.learning.atmserver.util.AtmAuditor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtmServiceTest {
    @Mock
    private AtmAuditor atmAuditor;
    @Mock
    private AtmRepository atmRepository;
    @Mock
    private BiometricRepository biometricRepository;
    @Mock
    private CardRepository cardRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private TransactionRepository transactionRepository;
    @InjectMocks
    private AtmServiceImpl atmService;

    @BeforeEach
    void setup() {
    }

    @Test
    void testVerifyClientCard() {
        String cardNumber = "1234123412341234";

        Card card = new Card();
        card.setNumber(cardNumber);
        card.setStatus(Status.ACTIVE);
        Account account = new Account();
        account.setStatus(Status.ACTIVE);
        Client client = new Client();
        client.setStatus(Status.ACTIVE);
        account.setClient(client);
        card.setAccount(account);

        when(cardRepository.findCardByNumber(cardNumber)).thenReturn(card);

        assertTrue(atmService.verifyClientCard(1, cardNumber));
        verify(cardRepository).findCardByNumber(cardNumber);
    }

    @Test
    void testVerifyClientPIN_IncorrectPIN_ReturnsFalse() {
        Card card = new Card();
        card.setStatus(Status.ACTIVE);
        card.setNumber("123456789");
        card.setPIN("expectedPin");
        when(cardRepository.findCardByNumber("123456789")).thenReturn(card);
        when(clientRepository.getReferenceById(1)).thenReturn(new Client());
        when(atmRepository.getReferenceById(1)).thenReturn(new Atm());

        boolean result = atmService.verifyClientPIN(1, 1, "123456789", "wrongPin".getBytes());

        assertFalse(result);
        verify(atmAuditor).log(eq("AUTH_PIN"), any(Client.class), any(Atm.class));
    }

    @Test
    void testGetClientBiometrics_ValidClient_ReturnsFingerprintData() {
        Biometric biometric = new Biometric();
        biometric.setFingerprint("fingerprintData".getBytes());
        when(biometricRepository.findBiometricByClientId(1)).thenReturn(biometric);

        byte[] result = atmService.getClientBiometrics(1, 1);

        assertArrayEquals("fingerprintData".getBytes(), result);
    }

    @Test
    void testBiometricsStatusAcknowledge_LogsAcknowledgment() {
        doNothing().when(atmAuditor).log(anyString(), any(Client.class), any(Atm.class));
        when(clientRepository.getReferenceById(1)).thenReturn(new Client());
        when(atmRepository.getReferenceById(1)).thenReturn(new Atm());

        atmService.biometricsStatusAcknowledge(1, 1, "123456789", true);

        verify(atmAuditor).log(eq("ACK"), any(Client.class), any(Atm.class));
    }

    @Test
    void testSaveTransaction_TransactionSavedSuccessfully_ReturnsTrue() {
        when(clientRepository.getReferenceById(1)).thenReturn(new Client());
        when(atmRepository.getReferenceById(1)).thenReturn(new Atm());
        when(transactionRepository.saveAndFlush(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        boolean result = atmService.saveTransaction(1, 1, 100, "DEPOSIT");

        assertTrue(result);
        verify(transactionRepository).saveAndFlush(any(Transaction.class));
        verify(atmAuditor).log(eq("DEPOSIT"), any(Client.class), any(Atm.class));
    }
}