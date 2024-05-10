package ua.learning.atmserver.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.learning.atmserver.api.dto.BiometricsStatusAcknowledgeRequest;
import ua.learning.atmserver.api.dto.CardVerificationRequest;
import ua.learning.atmserver.api.dto.PinVerificationRequest;
import ua.learning.atmserver.service.AtmService;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final AtmService atmService;

    @PostMapping("/card")
    public Boolean verifyClientCard(@RequestBody CardVerificationRequest request) {
        return atmService.verifyClientCard(request.getAtmId(), request.getCardNumber());
    }

    @PostMapping("/pin")
    public Boolean verifyClientPIN(@RequestBody PinVerificationRequest request) {
        return atmService.verifyClientPIN(request.getClientId(), request.getAtmId(),
                request.getCardNumber(), request.getPin());
    }

    @GetMapping("/biometrics")
    public byte[] getClientBiometrics(@RequestParam int clientId, @RequestParam int atmId) {
        return atmService.getClientBiometrics(clientId, atmId);
    }

    @PostMapping("/acknowledge")
    public byte[] biometricsStatusAcknowledge(@RequestBody BiometricsStatusAcknowledgeRequest request) {
        return atmService.biometricsStatusAcknowledge(request.getClientId(), request.getAtmId(),
                request.getCardNumber(), request.isPassed());
    }
}
