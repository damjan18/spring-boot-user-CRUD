package com.example.usercrud.user;

import com.example.usercrud.user.exception.UniqueViolationException;
import com.example.usercrud.user.exception.UserNotFoundException;
import com.example.usercrud.user.dto.UserCreateRequest;
import com.example.usercrud.user.dto.UserMapper;
import com.example.usercrud.user.dto.UserResponse;
import com.example.usercrud.user.dto.UserUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class UserService {
    private final UserRepository repo;
    public UserService(UserRepository repo) {this.repo = repo;}

    public List<UserResponse> findAll() {
        return repo.findAll().stream().map(UserMapper::toResponse).toList();
    }

    public UserResponse findById(Long id) {
        var u = repo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return UserMapper.toResponse(u);
    }

    public UserResponse create(UserCreateRequest req) {
        if (repo.existsByUsername(req.username())) throw new UniqueViolationException("username already exists");
        if (repo.existsByEmail(req.email())) throw new UniqueViolationException("email already exists");
        var created = repo.save(UserMapper.fromCreate(req));
        return UserMapper.toResponse(created);
    }

    public UserResponse update(Long id, UserUpdateRequest req) {
        var u = repo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        if (!u.getEmail().equals(req.email()) && repo.existsByEmail(req.email())) {
            throw new UniqueViolationException("email already exists");
        }
        UserMapper.applyUpdate(u, req);
        return UserMapper.toResponse(u);
    }

    public void delete(Long id) {
        if(!repo.existsById(id)) throw new UserNotFoundException(id);
        repo.deleteById(id);
    }
}
