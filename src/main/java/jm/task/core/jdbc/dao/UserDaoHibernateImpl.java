package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

import static jm.task.core.jdbc.dao.UserDaoJDBCImpl.*;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {}

    SessionFactory sessionFactory = Util.getCurrentSession();

    @Override
    public void createUsersTable() {
        try {Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.createSQLQuery(CREATE_USERS_TABLE).executeUpdate();
            tx.commit();
            System.out.println("\tТаблица создана");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try {Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.createSQLQuery(DROP_USERS_TABLE).executeUpdate();
            tx.commit();
            System.out.println("\tТаблица удалена");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.persist(new User(name, lastName, age));
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.createQuery("DELETE users WHERE id = :id").executeUpdate();
            tx.commit();
            System.out.println("\tПользователь удален");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List getAllUsers() {
        List users;
        try (Session session = sessionFactory.openSession()) {
          users = (session.createSQLQuery(GET_ALL_USERS).addEntity(User.class)).list();
          session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.createSQLQuery(CLEAN_USERS_TABLE).executeUpdate();
            tx.commit();
            System.out.println("\tТаблица очищена");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}


