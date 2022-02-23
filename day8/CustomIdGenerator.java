package day8;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.factory.spi.StandardGenerator;

import java.util.Random;

public class CustomIdGenerator implements StandardGenerator {
    private final String DEFAULT_SEQUENCE_NAME="my_seq";

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));

    }
}