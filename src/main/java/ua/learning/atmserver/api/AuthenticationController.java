package ua.learning.atmserver.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.learning.atmserver.api.dto.ClientOtpVerificationRequest;
import ua.learning.atmserver.api.dto.ClientPinVerificationRequest;
import ua.learning.atmserver.service.AtmService;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AtmService atmService;

    @PostMapping("/pin")
    public Boolean verifyClientPIN(@RequestBody ClientPinVerificationRequest request) {
        return atmService.verifyClientPIN(request.getClientId(), request.getAtmId(), request.getPin());
    }

    @PostMapping("/otp")
    public Boolean verifyClientOTP(@RequestBody ClientOtpVerificationRequest request) {
        return atmService.verifyClientOTP(request.getClientId(), request.getAtmId(), request.getOtp());
    }

    @GetMapping("/biometrics")
    public byte[] verifyClientBiometrics(@RequestParam int clientId, @RequestParam int atmId) {
        return atmService.getClientBiometrics(clientId, atmId);
    }
}
