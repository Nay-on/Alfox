/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author snir2g1
 */
public class ZoneLimiteTest {
    
    public ZoneLimiteTest() {
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
     * Test of create method, of class ZoneLimite.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Connection con = null;
        String nom = "";
        ZoneLimite expResult = null;
        ZoneLimite result = ZoneLimite.create(con, nom);
        assertEquals(expResult, result);
    }

    /**
     * Test of save method, of class ZoneLimite.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Connection con = null;
        ZoneLimite instance = null;
        instance.save(con);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByNom method, of class ZoneLimite.
     */
    @Test
    public void testGetByNom() throws Exception {
        System.out.println("getByNom");
        Connection con = null;
        String nom = "";
        ZoneLimite expResult = null;
        ZoneLimite result = ZoneLimite.getByNom(con, nom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNom method, of class ZoneLimite.
     */
    @Test
    public void testGetNom() {
        System.out.println("getNom");
        ZoneLimite instance = null;
        String expResult = "";
        String result = instance.getNom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNom method, of class ZoneLimite.
     */
    @Test
    public void testSetNom() {
        System.out.println("setNom");
        String nom = "";
        ZoneLimite instance = null;
        instance.setNom(nom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ZoneLimite.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ZoneLimite instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
