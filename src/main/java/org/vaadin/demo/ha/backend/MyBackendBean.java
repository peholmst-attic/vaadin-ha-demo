package org.vaadin.demo.ha.backend;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MyBackendBean {

    @PersistenceContext(unitName = "demoPU")
    EntityManager entityManager;

    public String echo(String s) {
        MyEntity entity = new MyEntity();
        entity.setName(s);
        entityManager.persist(entity);
        return "Echoed: [" + s + "]";
    }

    public List<MyEntity> findEntities() {
        return entityManager.createQuery("SELECT e FROM MyEntity e", MyEntity.class).getResultList();
    }
}
