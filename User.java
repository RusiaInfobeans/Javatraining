package day11.Task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name = "chat")
class UserChat {
    @Id
    @Column(name = "Chat_Id")
    private int id;
    @Column(name = "Message")
    private String message;

    public int getCount() {
        return count;
    }


    @Column(name = "count")
    private int count = 0;
    public UserChat(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        this.count++;
    }
}


public class User {
    public static void main(String[] args) {
        newChat();
       sendMSG();
        getMax();
    }

    private static void newChat() {
        UserChat chat = new UserChat();
        chat.setId(103);
        chat.setMessage("How are you");
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(chat);
        transaction.commit();
        session.close();
    }



    private static void sendMSG() {;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        UserChat chat = session.get(UserChat.class,103);
        chat.setMessage((chat.getMessage())+"\nYa good enough");
        chat.setMessage((chat.getMessage())+"good day");
        session.persist(chat);
        transaction.commit();
        session.close();
    }

    public static void getMax(){
        Session session = getSession();
        System.out.println(session.createQuery("FROM UserChat ORDER BY count DESC LIMIT 1", UserChat.class).getSingleResult().getCount());
    }

    private static Session getSession() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(UserChat.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }
}