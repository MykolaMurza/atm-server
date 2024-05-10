package ua.learning.atmserver.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.learning.atmserver.api.dto.TransactionRequest;
import ua.learning.atmserver.service.AtmService;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final AtmService atmService;

    @PostMapping("/withdraw")
    public boolean withdraw(@RequestBody TransactionRequest request) {
        return atmService.saveTransaction(request.getClientId(), request.getAtmId(),
                request.getAmount(), "WITHDRAW");
    }

    @PostMapping("/send")
    public boolean send(@RequestBody TransactionRequest request) {
        return atmService.saveTransaction(request.getClientId(), request.getAtmId(),
                request.getAmount(), "SEND");
    }
}
