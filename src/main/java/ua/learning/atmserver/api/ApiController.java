package ua.learning.atmserver.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.learning.atmserver.api.dto.ClientBiometricsVerificationRequest;
import ua.learning.atmserver.api.dto.TransactionRequest;
import ua.learning.atmserver.entity.Client;
import ua.learning.atmserver.entity.Transaction;
import ua.learning.atmserver.service.AtmService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    private final AtmService atmService;

    @GetMapping("/client")
    public Client getClientData(@RequestParam String account, @RequestParam int atmId) {
        return atmService.getClientDataByAccount(account, atmId);
    }

    @PostMapping("/verify")
    public Boolean verifyClientBiometrics(@RequestBody ClientBiometricsVerificationRequest request) {
        return atmService.verifyClientBiometrics(request.getClientId(), request.getAtmId(), request.getBiometrics());
    }

    @PostMapping("/transaction")
    public Transaction makeTransaction(@RequestBody TransactionRequest request) {
        return atmService.makeTransaction(request.getClientId(), request.getAtmId(),
                request.getAmount(), request.getAction());
    }
}
