package br.ce.cosmocode.fortalshop.repositories;

import java.util.List;
import java.util.UUID;

import br.ce.cosmocode.fortalshop.domain.User;

public interface UserRepository {
    User save(User user);
    User findById(UUID id);
    List<User> findAll();
    void delete(User user);
}
