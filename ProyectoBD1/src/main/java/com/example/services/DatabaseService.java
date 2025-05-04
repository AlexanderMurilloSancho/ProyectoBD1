package com.example.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class DatabaseService {

    public void insertarImagen(Integer userId, Integer taxonId, Date imageDate, String place, BigDecimal latitude, BigDecimal longitude, String license) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Usar createNativeQuery en lugar de createSQLQuery
            NativeQuery<?> query = session.createNativeQuery(
                    "SELECT  insertar_imagen(:user_id, :taxon_id, :image_date, :place, :latitude, :longitude, :license)"
            );
            query.setParameter("user_id", userId);
            query.setParameter("taxon_id", taxonId);
            query.setParameter("image_date", new Timestamp(imageDate.getTime()));
            query.setParameter("place", place);
            query.setParameter("latitude", latitude);
            query.setParameter("longitude", longitude);
            query.setParameter("license", license);

            query.getSingleResult(); //

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void insertarObservacion(Integer userId, Integer taxonId, Date observationDate, BigDecimal latitude, BigDecimal longitude, String imageUrl, Integer imageId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            NativeQuery<?> query = session.createNativeQuery(
                    "SELECT insertar_observacion(:user_id, :taxon_id, :observation_date, :latitude, :longitude, :image_url, :image_id)"
            );
            query.setParameter("user_id", userId);
            query.setParameter("taxon_id", taxonId);
            query.setParameter("observation_date", new java.sql.Date(observationDate.getTime())); // o Timestamp si tu función lo espera
            query.setParameter("latitude", latitude);
            query.setParameter("longitude", longitude);
            query.setParameter("image_url", imageUrl);
            query.setParameter("image_id", imageId);

            query.getSingleResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void insertarIdentificacion(Integer observationId, Integer userId, Integer taxonId, String identificationLevel, Date identificationDate) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            NativeQuery<?> query = session.createNativeQuery(
                    "SELECT insertar_identificacion(:observation_id, :user_id, :taxon_id, :identification_level, :identification_date)"
            );
            query.setParameter("observation_id", observationId);
            query.setParameter("user_id", userId);
            query.setParameter("taxon_id", taxonId);
            query.setParameter("identification_level", identificationLevel);
            query.setParameter("identification_date", new java.sql.Date(identificationDate.getTime()));

            query.getSingleResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public void actualizarImagen(Integer imageId, String newValue, Integer column) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Usar createNativeQuery en lugar de createSQLQuery
            NativeQuery<?> query = session.createNativeQuery(
                    "CALL actualizar_image(:id, :new_value, :column)"
            );
            query.setParameter("id", imageId);
            query.setParameter("new_value", newValue);
            query.setParameter("column", column);

            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void actualizarObservation(Integer observationId, String newValue, Integer column) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            NativeQuery<?> query = session.createNativeQuery(
                    "CALL actualizar_observation(:id, :new_value, :column)"
            );
            query.setParameter("id", observationId);
            query.setParameter("new_value", newValue);
            query.setParameter("column", column);

            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void actualizarIdentification(Integer identificationId, String newValue, Integer column) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            NativeQuery<?> query = session.createNativeQuery(
                    "CALL actualizar_identification(:id, :new_value, :column)"
            );
            query.setParameter("id", identificationId);
            query.setParameter("new_value", newValue);
            query.setParameter("column", column);

            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public void eliminarDato(Integer id, Integer opcion) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Usar createNativeQuery en lugar de createSQLQuery
            NativeQuery<?> query = session.createNativeQuery(
                    "CALL Delete_Data(:id, :opcion)"
            );
            query.setParameter("id", id);
            query.setParameter("opcion", opcion);

            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
