package com.example.services;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class QueryService {

    public List<Object[]> getObservationsWithDetails() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            String sql = """
            SELECT t.kingdom_name AS reino,
                   t.species_name AS especie,
                   o.observation_date AS fecha_observacion,
                   i.place AS sitio_observacion,
                   u.first_name || ' ' || u.last_name AS nombre_usuario,
                   o.image_url AS imagen
            FROM observations o
            JOIN taxons t ON o.taxon_id = t.taxon_id
            JOIN users u ON o.user_id = u.user_id
            LEFT JOIN images i ON o.image_id = i.image_id
        """;

            NativeQuery<Object[]> query = session.createNativeQuery(sql);
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    public List<Object[]> getTaxonomyByObservation() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            String sql = """
            SELECT o.observation_id AS id_observacion,
                   t.kingdom_name AS reino,
                   t.phylum_name AS filo,
                   t.class_name AS clase,
                   t.order_name AS orden,
                   t.family_name AS familia,
                   t.genus_name AS genero,
                   t.species_name AS especie,
                   t.common_name AS nombre_comun
            FROM observations o
            JOIN taxons t ON o.taxon_id = t.taxon_id
        """;

            NativeQuery<Object[]> query = session.createNativeQuery(sql);
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    public List<Object[]> getImageDetailsWithTaxonomy() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            String sql = """
            SELECT T.kingdom_name, 
                   T.species_name, 
                   O.observation_date, 
                   I.place, 
                   U.first_name, 
                   I.image_date
            FROM USERS U
            JOIN IMAGES I ON U.user_id = I.user_id
            JOIN OBSERVATIONS O ON I.user_id = O.user_id
            JOIN TAXONS T ON O.taxon_id = T.taxon_id
        """;

            NativeQuery<Object[]> query = session.createNativeQuery(sql);
            return query.getResultList();
        } finally {
            session.close();
        }
    }

}