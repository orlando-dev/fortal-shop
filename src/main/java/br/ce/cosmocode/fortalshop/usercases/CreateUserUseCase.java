package br.ce.cosmocode.fortalshop.usercases;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.ce.cosmocode.fortalshop.domain.User;
import br.ce.cosmocode.fortalshop.exceptions.UserAlreadyExistsException;
import br.ce.cosmocode.fortalshop.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final IUserRepository userRepository;

    public User execute(User user) {
        try {
            User existingUser = userRepository.findByUsername(user.getUsername());
            if (existingUser != null) {
                throw new UserAlreadyExistsException();
            }
        } catch (EmptyResultDataAccessException e) {
            return userRepository.save(user);
        }
        return user;
    }

}
