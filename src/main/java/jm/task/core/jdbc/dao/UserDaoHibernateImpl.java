package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;



public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {}

    SessionFactory sessionFactory = Util.getSessionFactory();

    @Override
    public void createUsersTable() {
        String str = "CREATE TABLE IF NOT EXISTS`hibernate_db`.`user` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT(3) NOT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);";
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(str).executeUpdate();
            System.out.println("The table 'user' was create successfully");
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String str = "DROP TABLE IF EXISTS user";
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(str).executeUpdate();
            System.out.println("The table 'user' was delete");
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            System.out.println("User" + name + " " + lastName + " " + age + " was added");
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            System.out.println("Done!");
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            result = session.createQuery("FROM User", User.class).list();
            System.out.println(result);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    @Override
    public void cleanUsersTable() {
        String str = "TRUNCATE user";
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(str).executeUpdate();
            System.out.println("All 'user' was delete");
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
