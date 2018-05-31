/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence;

import java.sql.Connection;
import java.sql.Timestamp;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author acros
 */
public class ContratTest {
    
    public ContratTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class Contrat.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Connection con = ConnexionMySQL.newConnexion();
        Timestamp dateCreation = Utils.stringToTimestamp("2017/10/15 12:00:00");
        Contrat result = Contrat.create(con, "A3", dateCreation, "m1", "", 2, 2, 1);
        assertEquals("A3", result.getNumero());
        
        result.delete(con);
        
        dateCreation = Utils.stringToTimestamp("2017/10/15 12:00:00");
        result = Contrat.create(con, "A4", dateCreation, "m1", "", 1, 1, 1);
        assertEquals("A4", result.getNumero());
        
        result.delete(con);
    }

    /**
     * Test of save method, of class Contrat.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat result = Contrat.getByNumero(con, "C1");
        result.setInfos("nouvelle info");
        result.save(con);
        result = Contrat.getByNumero(con, "C1");
        assertEquals("nouvelle info", result.getInfos());
        result.setInfos("");
        result.save(con);
    }

    /**
     * Test of getByNumero method, of class Contrat.
     */
    @Test
    public void testGetByNumero() throws Exception {
        System.out.println("getByNumero");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat result = Contrat.getByNumero(con, "C1");
        assertEquals("C1", result.getNumero());
        //on cherche un contrat qui n'existe pas ; on attend null
        assertNull(Contrat.getByNumero(con, "X2"));
    }

    /**
     * Test of getNumero method, of class Contrat.
     */
    @Test
    public void testGetNumero() throws Exception {
        System.out.println("getNumero");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat instance = Contrat.getByNumero(con, "C1");
        assertEquals("C1", instance.getNumero());
    }

    /**
     * Test of getDate method, of class Contrat.
     */
    @Test
    public void testGetDate() throws Exception {
        System.out.println("getDate");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat instance = Contrat.getByNumero(con, "C1");
        assertEquals(Utils.stringToTimestamp("2017/01/01 00:00:00"), instance.getDate());
    }

    /**
     * Test of getType method, of class Contrat.
     */
    @Test
    public void testGetType() throws Exception {
        System.out.println("getType");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat instance = Contrat.getByNumero(con, "C2");
        assertEquals("annuel", instance.getType());
    }

    /**
     * Test of getInfos method, of class Contrat.
     */
    @Test
    public void testGetInfos() throws Exception{
        System.out.println("getInfos");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat instance = Contrat.getByNumero(con, "C2");
        assertEquals("", instance.getInfos());
    }

    /**
     * Test of setType method, of class Contrat.
     */
    @Test
    public void testSetType() throws Exception {
        System.out.println("setType");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat instance = Contrat.getByNumero(con, "C1");
        instance.setType("mensuel");
        instance.save(con);
        assertEquals("mensuel", instance.getType());
        instance.setType("annuel");
        instance.save(con);
    }

    /**
     * Test of setInfos method, of class Contrat.
     */
    @Test
    public void testSetInfos() throws Exception {
        System.out.println("setInfos");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat instance = Contrat.getByNumero(con, "C1");
        instance.setInfos("Ce contrat est valable un an");
        instance.save(con);
        assertEquals("Ce contrat est valable un an", instance.getInfos());
        instance.setType("");
        instance.save(con);
    }

    /**
     * Test of getLoueurID method, of class Contrat.
     */
    @Test
    public void testGetLoueurID() throws Exception {
        System.out.println("getLoueurID");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat instance = Contrat.getByNumero(con, "C2");
        assertEquals(2, instance.getLoueurID());
    }

    /**
     * Test of GetVehiculeID method, of class Contrat.
     */
    @Test
    public void testGetVehiculeID() throws Exception {
        System.out.println("getVehiculeID");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat instance = Contrat.getByNumero(con, "C2");
        assertEquals(2, instance.getVehiculeID());
    }

    /**
     * Test of getZoneLimiteID method, of class Contrat.
     */
    @Test
    public void testGetZoneLimiteID() throws Exception {
        System.out.println("getZoneLimiteID");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat instance = Contrat.getByNumero(con, "C2");
        assertEquals(2, instance.getZoneLimiteID());
        instance = Contrat.getByNumero(con, "C5");
        assertEquals(2, instance.getZoneLimiteID());
    }

    /**
     * Test of setLoueurID method, of class Contrat.
     */
    @Test
    public void testSetLoueurID() throws Exception {
        System.out.println("setLoueurID");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat instance = Contrat.getByNumero(con, "C1");
        instance.setLoueurID(3);
        instance.save(con);
        assertEquals(3, instance.getLoueurID());
        instance.setLoueurID(1);
        instance.save(con);
    }

    /**
     * Test of SetVehiculeID method, of class Contrat.
     */
    @Test
    public void testSetVehiculeID() throws Exception {
        System.out.println("setVehiculeID");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat instance = Contrat.getByNumero(con, "C1");
        instance.setVehiculeID(2);
        instance.save(con);
        assertEquals(2, instance.getVehiculeID());
        instance.setVehiculeID(1);
        instance.save(con);
    }

    /**
     * Test of setZoneLimiteID method, of class Contrat.
     */
    @Test
    public void testSetZoneLimiteID() throws Exception {
        System.out.println("setZoneLimiteID");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat instance = Contrat.getByNumero(con, "C1");
        instance.setZoneLimiteID(1);
        instance.save(con);
        assertEquals(1, instance.getZoneLimiteID());
        instance.setZoneLimiteID(2);
        instance.save(con);
    }    
}
