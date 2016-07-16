package com.entity;

import java.util.List;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Created by Aymen Ben Othmen on 16/07/16.
 */

public class UserManager {

    /*
    private static SessionFactory sessionFactory;

    static {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("/hibernate.cfg.xml")
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }
    */

    private static SessionFactory sessionFactory;
    public static void main(String[] args) {

        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("/hibernate.cfg.xml")
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
        UserManager userManager = new UserManager();
        userManager.listUsers();
        userManager.updateUser(5, "fake_user");
        userManager.deleteUser(5);
    }

    public Integer addUser(String username, String email, String fname, String lname){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Integer userId = null;
        try{
            transaction = session.beginTransaction();
            User user = new User( username, email, fname, lname );
            user.setPassword( username + "-pass" );
            user.setGender( "male" );
            userId = (Integer) session.save( user );
            transaction.commit();

        }catch (HibernateException e) {
            if ( transaction != null )
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return userId;
    }

    public void listUsers( ){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery( "from " + User.class.getName() );
            List<User> users = query.list();
            for (Iterator iterator = users.iterator(); iterator.hasNext();){
                User user = (User) iterator.next();
                System.out.print("ID: " + user.getId());
                System.out.print(", First Name: " + user.getFirstname());
                System.out.print(", Last Name: " + user.getLastname());
                System.out.println(", Username: " + user.getUsername());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void updateUser(Integer userId, String username ){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            User user = session.get(User.class, userId);
            if(user != null) {
                user.setUsername( username );
                session.update(user);
                System.out.println("user updated successfully");
            }else{
                System.out.println("user update cancelled. user not found!");
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void deleteUser(Integer userID){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            User user = session.get(User.class, userID);
            if(user != null) {
                System.out.println("user deleted successfully");
                session.delete(user);
            }else{
                System.out.println("user delete cancelled. user not found!");
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}