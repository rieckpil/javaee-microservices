package de.rieckpil.user.boundary;

import de.rieckpil.user.entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserStore {

    @PersistenceContext
    EntityManager em;
    
    private List<User> userList;

    @PostConstruct
    private void initUserStore() {

        userList = new ArrayList<>();

        User user1 = new User("Doe", "John");
        User user2 = new User("Mustermann", "Max");
        User user3 = new User("Freier", "Paul");
        User user4 = new User("Reiner", "Zufall");

        this.createUser(user1);
        this.createUser(user2);
        this.createUser(user3);
        this.createUser(user4);
    }

    public List<User> getAllUser() {
        return userList;
    }
    
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    public User createUser(User user) {
        em.persist(user);
        return user;
    }
}
