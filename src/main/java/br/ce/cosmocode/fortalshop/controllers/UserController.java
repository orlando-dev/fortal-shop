package br.ce.cosmocode.fortalshop.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ce.cosmocode.fortalshop.common.ErrorResponse;
import br.ce.cosmocode.fortalshop.domain.User;
import br.ce.cosmocode.fortalshop.exceptions.UserAlreadyExistsException;
import br.ce.cosmocode.fortalshop.exceptions.UserIdNotProvidedException;
import br.ce.cosmocode.fortalshop.repositories.IUserRepository;
import br.ce.cosmocode.fortalshop.usercases.CreateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final IUserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        try {
            User createdUser = createUserUseCase.execute(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Nome de usuário já existe"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        if (id == null) {
            ErrorResponse errorResponse = new ErrorResponse("Forneça um ID de usuário");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }    
        try {
            User user = userRepository.findById(id);

            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                ErrorResponse errorResponse = new ErrorResponse("Usuário não encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (UserIdNotProvidedException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }


    @GetMapping
    public ResponseEntity<List<User> >getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        User user = userRepository.findById(id);

        if (user != null) {
            userRepository.delete(user);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
