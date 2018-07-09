package br.com.tasking.dao;

import br.com.tasking.entity.Task;

import javax.persistence.Query;
import java.util.List;

public class TaskDAO extends GenericDAO<Task, Integer> {

    public TaskDAO() {
        super(Task.class);
    }

    public List<Task> findPendentes(String login) {
        Query query = getEntityManager().createNamedQuery("Task.findPendentes");
        query.setParameter("P_LOGIN", login);
        return query.getResultList();
    }

    public List<Task> findAll(String login) {
        Query query = getEntityManager().createNamedQuery("Task.findAll");
        query.setParameter("P_LOGIN", login);
        return query.getResultList();
    }
}
