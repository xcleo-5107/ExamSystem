package cn.edu.zjut.examsystem.dao;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class MyJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> {
    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;

    public MyJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <S extends T> S save(S entity) {
        ID entityId = (ID) entityInformation.getId(entity);
        Optional<T> optionalT = findById(entityId);

        if (!optionalT.isPresent()) {
            em.persist(entity);
            return entity;
        } else {
            T target = optionalT.get();
            BeanUtils.copyProperties(entity, target, getNullProperties(entity));
            em.merge(target);
            return entity;
        }
    }

    private String[] getNullProperties(Object src) {
        BeanWrapper srcBean = new BeanWrapperImpl(src);
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        Set<String> properties = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : pds) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = srcBean.getPropertyValue(propertyName);
            if (propertyValue == null) {
                properties.add(propertyName);
            }
        }
        return properties.toArray(new String[0]);
    }
}