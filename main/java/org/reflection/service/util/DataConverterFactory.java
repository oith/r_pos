package org.reflection.service.util;

import org.reflection.model.com.Abstract;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigInteger;

@Service("dataConverterFactory")
public class DataConverterFactory implements ConverterFactory<String, Abstract> {

    private final EntityManager entityManager;

    public DataConverterFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public <T extends Abstract> Converter<String, T> getConverter(Class<T> targetType) {
        return new GenericConverter(targetType);
    }

    private final class GenericConverter<T extends Abstract> implements Converter<String, T> {

        private final Class<T> targetType;

        public GenericConverter(Class<T> targetType) {
            this.targetType = targetType;
            System.out.println("shs fhj 358 hj:" + targetType);
        }

        @Override
        public T convert(String text) {
            if (text != null && !text.isEmpty() && !text.equalsIgnoreCase("-1")) {//Ignore - to be determined later
                try {
                    return (T) entityManager.find(targetType, new BigInteger(text));
                } catch (Exception e) {
                    System.out.println("hagdhd 234:" + e + " val: " + text);
                    //return (T) text;
                }
            }
            return null;
        }
    }
}
