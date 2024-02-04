package ua.learning.atmserver.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.learning.atmserver.entity.Client;
import ua.learning.atmserver.service.AtmService;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final AtmService atmService;

    @GetMapping
    public Client getClientData(@RequestParam String account, @RequestParam int atmId) {
        return atmService.getClientDataByAccount(account, atmId);
    }
}
