package com.example.usercrud.user;


import com.example.usercrud.user.dto.UserCreateRequest;
import com.example.usercrud.user.dto.UserResponse;
import com.example.usercrud.user.dto.UserUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;
    public UserController(UserService service) {this.service = service ;}


    @GetMapping
    public List<UserResponse> all() {return service.findAll();}


    @GetMapping("/{id}")
    public UserResponse one(@PathVariable Long id ) { return service.findById(id);}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserResponse create(@Valid @RequestBody UserCreateRequest req) { return service.create(req) ;}

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest req) {
        return service.update(id, req);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {service.delete(id);}

}
