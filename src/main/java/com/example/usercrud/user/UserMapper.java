package com.example.usercrud.user;

public class UserMapper {
    public static UserResponse toResponse(User u) {
        return new UserResponse(u.getId(), u.getUsername(), u.getEmail(), u.getFullName());
    }
    public static User fromCreate(UserCreateRequest r) {
        return User.builder().username(r.username()).email(r.email()).fullName(r.fullName()).build();
    }
    public static void applyUpdate(User u, UserUpdateRequest r) {
        u.setEmail(r.email());
        u.setFullName(r.fullName());
    }
}
