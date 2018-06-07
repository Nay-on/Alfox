/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * @author acros
 */
public class ZoneLimiteTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

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

    /**
     * Test of getLatCentre method, of class ZoneLimite.
     */
    @Test
    public void testGetLatCentre() throws Exception {
        System.out.println("getLatCentre");
        Connection con = ConnexionMySQL.newConnexion();
        assertEquals(43.600541, ZoneLimite.getLatCentre(con, "Toulouse"), 0.000001);
    }

    /**
     * Test of getLgCentre method, of class ZoneLimite.
     */
    @Test
    public void testGetLgCentre() throws Exception {
        System.out.println("getLgCentre");
        Connection con = ConnexionMySQL.newConnexion();
        assertEquals(1.434093, ZoneLimite.getLgCentre(con, "Toulouse"), 0.000001);
    }

    /**
     * Test of size method, of class ZoneLimite.
     */
    @Test
    public void testSize() throws Exception {
        System.out.println("size");
        Connection con = ConnexionMySQL.newConnexion();
        int result = ZoneLimite.size(con);
        assertEquals(2, result);
    }

    /**
     * Test of getLstZone method, of class ZoneLimite.
     */
    @Test
    public void testGetLstZone() throws Exception {
        System.out.println("getLstZone");
        Connection con = ConnexionMySQL.newConnexion();
        ArrayList<ZoneLimite> result = ZoneLimite.getLstZone(con);
        assertEquals(2, result.size());
        assertEquals("Alcis", result.get(0).getNom());
        assertEquals("Toulouse", result.get(1).getNom());
    }

    /**
     * Test of getLatByZone method, of class ZoneLimite.
     */
    @Test
    public void testGetLatByZone() throws Exception {
        System.out.println("getLatByZone");
        Connection con = ConnexionMySQL.newConnexion();
        ArrayList<Double> result = ZoneLimite.getLatByZone(con, "Alcis");
        assertEquals(4, result.size());
        assertEquals(43.604014, result.get(0), 0.000001);
        assertEquals(43.601590, result.get(1), 0.000001);
        assertEquals(43.601920, result.get(2), 0.000001);
        assertEquals(43.600355, result.get(3), 0.000001);
    }

    /**
     * Test of getLgByZone method, of class ZoneLimite.
     */
    @Test
    public void testGetLgByZone() throws Exception {
        System.out.println("getLgByZone");
        Connection con = ConnexionMySQL.newConnexion();
        ArrayList<Double> result = ZoneLimite.getLgByZone(con, "Alcis");
        assertEquals(4, result.size());
        assertEquals(1.526581, result.get(0), 0.000001);
        assertEquals(1.524203, result.get(1), 0.000001);
        assertEquals(1.530292, result.get(2), 0.000001);
        assertEquals(1.528461, result.get(3), 0.000001);
    }
}
