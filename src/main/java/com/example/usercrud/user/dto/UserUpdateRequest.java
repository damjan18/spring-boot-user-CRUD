package com.example.usercrud.user.dto;

import jakarta.validation.constraints.*;

public record UserUpdateRequest(
        @Email @NotBlank String email,
        @NotBlank @Size(min=2, max=100) String fullName
) {}
