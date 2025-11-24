package com.assertiva.sailpointlab.assertiva_sailpoint_backend.account;

import com.assertiva.sailpointlab.assertiva_sailpoint_backend.account.dto.AccountRequest;
import com.assertiva.sailpointlab.assertiva_sailpoint_backend.account.dto.AccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public List<AccountResponse> listAll() {
        return service.listAll();
    }

    @GetMapping("/{username}")
    public AccountResponse getByUsername(@PathVariable String username) {
        return service.getByUsername(username);
    }

    @PostMapping
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody AccountRequest req) {
        return ResponseEntity.status(201).body(service.create(req));
    }

    @PatchMapping("/{username}")
    public AccountResponse update(@PathVariable String username,
                                  @RequestBody AccountRequest req) {
        return service.update(username, req);
    }

    @PostMapping("/{username}/disable")
    public AccountResponse disable(@PathVariable String username) {
        return service.disable(username);
    }
}
