package de.rieckpil.user.boundary;

import de.rieckpil.user.entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);
        TypedQuery<User> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
    
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    public User createUser(User user) {
        em.persist(user);
        return user;
    }
}
