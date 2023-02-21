package com.toffee.nuts.bulletinboard.repository;

import com.toffee.nuts.bulletinboard.domain.Members;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.List;

@Repository
public class MembersRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Members member) {
        em.persist(member);
        return member.getId();
    }

    public Members findOne(Long id) {
        return em.find(Members.class, id);
    }

    public List<Members> findAll() {
        return em.createQuery("select m from Members m", Members.class)
                .getResultList();
    }

    public List<Members> findByName(String name) {
        return em.createQuery("select m from Members m where m.name = :name", Members.class)
                .setParameter("name",name)
                .getResultList();
    }

    public Members find(Long id) {
        return em.find(Members.class, id);
    }
}
