package day8;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class StudentQueryTable {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addResource("Student.hbm.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session=sessionFactory.openSession();
        List<Student> studentList = session.createQuery("from Student",Student.class).getResultList();
        System.out.println(studentList);
        session.close();
    }

}