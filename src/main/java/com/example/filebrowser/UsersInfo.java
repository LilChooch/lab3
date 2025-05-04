package com.example.filebrowser;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class UsersInfo {

    public static void addUser(User user){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        }

    }

    public static User getUser(String username) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(User.class, username);

        }
    }

    public static boolean validateUser(String login, String password){
        User user = getUser(login);
        return user != null && user.getPassword().equals(password);
    }
}
