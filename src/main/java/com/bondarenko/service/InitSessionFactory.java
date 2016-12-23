package com.bondarenko.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


public class InitSessionFactory {

    private static SessionFactory sessionFactory;

    private InitSessionFactory(){}

    static {
        final AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.configure("/META-INF/hibernate.cfg.xml");
        sessionFactory = cfg.buildSessionFactory();
    }

    public static SessionFactory getInstance(){
        return sessionFactory;
    }
}
