package com.example.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");
        EntityManager em = emf.createEntityManager();

        try {         
           em.getTransaction().begin();

            System.out.println("=== INSERTION ===");
            Produit p1 = new Produit("Laptop", new BigDecimal("999.99"));
            Produit p2 = new Produit("Smartphone", new BigDecimal("499.99"));
            Produit p3 = new Produit("Tablette", new BigDecimal("299.99"));

            em.persist(p1);
            em.persist(p2);
            em.persist(p3);

            em.getTransaction().commit(); // Validation
            System.out.println("Produits insérés avec succès !");

            System.out.println("=== LECTURE ===");
            List<Produit> produits = em.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
            
            for (Produit p : produits) {
                System.out.println(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}