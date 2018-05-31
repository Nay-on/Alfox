/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence;

import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author acros
 */
public class ZoneLimiteTest {

    /**
     * Test of create method, of class ZoneLimite.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Connection con = ConnexionMySQL.newConnexion();
        String nom = "Paris";
        ZoneLimite result = ZoneLimite.create(con, nom);
        assertEquals("Paris", result.getNom());
        result.delete(con);
    }

    /**
     * Test of save method, of class ZoneLimite.
     * @throws java.lang.Exception
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Connection con = ConnexionMySQL.newConnexion();
        ZoneLimite instance = ZoneLimite.getByNom(con, "Toulouse");
        instance.setNom("Toulouse2");
        instance.save(con);
        instance = ZoneLimite.getByNom(con, "Toulouse2");
        assertEquals("Toulouse2", instance.getNom());
        instance.setNom("Toulouse");
        instance.save(con);
    }

    /**
     * Test of getByNom method, of class ZoneLimite.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetByNom() throws Exception {
        System.out.println("getByNom");
        Connection con = ConnexionMySQL.newConnexion();
        ZoneLimite result = ZoneLimite.getByNom(con, "Alcis");
        assertEquals("Alcis", result.getNom());
    }

    /**
     * Test of getNom method, of class ZoneLimite.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetNom() throws Exception {
        System.out.println("getNom");
        Connection con = ConnexionMySQL.newConnexion();
        ZoneLimite result = ZoneLimite.getByNom(con, "Alcis");
        assertEquals("Alcis", result.getNom());
    }

    /**
     * Test of setNom method, of class ZoneLimite.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetNom() throws Exception {
        System.out.println("setNom");
        Connection con = ConnexionMySQL.newConnexion();
        ZoneLimite instance = ZoneLimite.getByNom(con, "Alcis");
        instance.setNom("Alcis2");
        instance.save(con);
        assertEquals(instance.getNom(), "Alcis2");
        instance.setNom("Alcis");
        instance.save(con);
    }
}
