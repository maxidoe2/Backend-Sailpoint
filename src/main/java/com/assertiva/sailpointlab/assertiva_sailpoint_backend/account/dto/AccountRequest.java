package com.assertiva.sailpointlab.assertiva_sailpoint_backend.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AccountRequest {

    @NotBlank
    private String username;

    private String displayName;

    @Email
    private String email;

    private String department;

    private String status;

    // Getters / setters
    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getDisplayName() { return displayName; }

    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }

    public void setDepartment(String department) { this.department = department; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
