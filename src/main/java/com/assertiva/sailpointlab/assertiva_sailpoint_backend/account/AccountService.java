package com.assertiva.sailpointlab.assertiva_sailpoint_backend.account;

import com.assertiva.sailpointlab.assertiva_sailpoint_backend.account.dto.AccountRequest;
import com.assertiva.sailpointlab.assertiva_sailpoint_backend.account.dto.AccountResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<AccountResponse> listAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public AccountResponse getByUsername(String username) {
        Account acc = repository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + username));
        return toResponse(acc);
    }

    @Transactional
    public AccountResponse create(AccountRequest req) {
        Account acc = new Account();
        acc.setUsername(req.getUsername());
        acc.setDisplayName(req.getDisplayName());
        acc.setEmail(req.getEmail());
        acc.setDepartment(req.getDepartment());
        acc.setStatus(req.getStatus());
        acc = repository.save(acc);
        return toResponse(acc);
    }

    @Transactional
    public AccountResponse update(String username, AccountRequest req) {
        Account acc = repository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + username));

        if (req.getDisplayName() != null) acc.setDisplayName(req.getDisplayName());
        if (req.getEmail() != null) acc.setEmail(req.getEmail());
        if (req.getDepartment() != null) acc.setDepartment(req.getDepartment());
        if (req.getStatus() != null) acc.setStatus(req.getStatus());

        acc = repository.save(acc);
        return toResponse(acc);
    }

    @Transactional
    public AccountResponse disable(String username) {
        Account acc = repository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + username));

        acc.setStatus("DISABLED");
        repository.save(acc);
        return toResponse(acc);
    }

    private AccountResponse toResponse(Account acc) {
        AccountResponse res = new AccountResponse();
        res.setId(acc.getId());
        res.setUsername(acc.getUsername());
        res.setDisplayName(acc.getDisplayName());
        res.setEmail(acc.getEmail());
        res.setDepartment(acc.getDepartment());
        res.setStatus(acc.getStatus());
        res.setCreatedAt(acc.getCreatedAt());
        res.setUpdatedAt(acc.getUpdatedAt());
        return res;
    }
}
