package br.com.tasking.dao;

import br.com.tasking.entity.User;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class UserDAO extends GenericDAO<User, Integer> {

    public UserDAO() {
        super(User.class);
    }

    public User findByLogin(String login) throws Exception, NoResultException {
        Query query = getEntityManager().createNamedQuery("User.findByLogin");
        query.setParameter("P_LOGIN", login);
        return (User) query.getSingleResult();
    }

    public User findByLoginWithoutThrows(String login) {
        Query query = getEntityManager().createNamedQuery("User.findByLogin");
        query.setParameter("P_LOGIN", login);
        return (User) query.getSingleResult();
    }
}
