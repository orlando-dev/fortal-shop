package br.ce.cosmocode.fortalshop.infra;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.ce.cosmocode.fortalshop.domain.User;
import br.ce.cosmocode.fortalshop.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User findById(UUID id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }
}
