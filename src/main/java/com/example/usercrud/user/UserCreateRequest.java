package com.example.usercrud.user;

import jakarta.validation.constraints.*;

public record UserCreateRequest (
        @NotBlank @Size(min=3, max=50) String username,
        @Email @NotBlank String email,
        @NotBlank @Size(min=2, max=100) String fullName
) {}
