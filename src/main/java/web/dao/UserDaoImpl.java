package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public User readUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public void updateUser(int id, User user) {
        User userToBeUpdated = readUser(id);
        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setSurname(user.getSurname());

        entityManager.merge(userToBeUpdated);
    }

    @Transactional
    @Override
    public void deleteUser(int id) {
        User user = readUser(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = entityManager.createQuery("SELECT u FROM User u").getResultList();
        return users;
    }
}
