package com.scutcms.DAO.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Administrator on 2016/6/17 0017.
 */
public enum SessionFac {
    INSTANCE;
    public Session getSession(){
        Configuration config = new Configuration().configure("resources/hibernate.cfg.xml");
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }
}
