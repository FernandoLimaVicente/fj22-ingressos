package br.com.caelum.ingresso.dao;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.model.Lugar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by nando on 03/03/17.
 */
@Repository
public class LugarDao {

    @PersistenceContext
    private EntityManager manager;

    public void save(Lugar lugar) {
        manager.persist(lugar);
    }
}
