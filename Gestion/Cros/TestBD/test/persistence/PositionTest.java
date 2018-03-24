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
public class PositionTest {
    
    public PositionTest() {
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
     * Test of create method, of class Position.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Connection con = ConnexionMySQL.newConnexion();
        int ordre = 12;
        float latitude = (float)40.123456;
        float longitude = (float)50.123456;
        int zoneLimiteID = 1;
        Position result = Position.create(con, ordre, zoneLimiteID, latitude, longitude);
        assertEquals(12, result.getOrdre());
        result.delete(con);
    }

    /**
     * Test of save method, of class Position.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Connection con = ConnexionMySQL.newConnexion();
        Position instance = Position.getByOrdre(con, 1);
        instance.setOrdre(15);
        instance.save(con);
        instance = Position.getByOrdre(con, 15);
        assertEquals(15, instance.getOrdre());
    }

    /**
     * Test of getByOrdre method, of class Position.
     */
    @Test
    public void testGetByOrdre() throws Exception {
        System.out.println("getByOrdre");
        Connection con = ConnexionMySQL.newConnexion();
        Position result = Position.getByOrdre(con, 4);
        assertEquals(4, result.getOrdre());
    }

    /**
     * Test of getOrdre method, of class Position.
     */
    @Test
    public void testGetOrdre() throws Exception {
        System.out.println("getOrdre");
        Connection con = ConnexionMySQL.newConnexion();
        Position result = Position.getByOrdre(con, 4);
        assertEquals(4, result.getOrdre());
    }

    /**
     * Test of getLatitude method, of class Position.
     */
    @Test
    public void testGetLatitude() throws Exception {
        System.out.println("getLatitude");
        Connection con = ConnexionMySQL.newConnexion();
        Position result = Position.getByOrdre(con, 4);
        assertEquals(43.668415, result.getLatitude(), 0.00001);
    }

    /**
     * Test of getLongitude method, of class Position.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        Position instance = null;
        float expResult = 0.0F;
        float result = instance.getLongitude();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrdre method, of class Position.
     */
    @Test
    public void testSetOrdre() throws Exception {
        System.out.println("setOrdre");
        int ordre = 0;
        Position instance = null;
        instance.setOrdre(ordre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLatitude method, of class Position.
     */
    @Test
    public void testSetLatitude() throws Exception {
        System.out.println("setLatitude");
        float latitude = 0.0F;
        Position instance = null;
        instance.setLatitude(latitude);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLongitude method, of class Position.
     */
    @Test
    public void testSetLongitude() throws Exception {
        System.out.println("setLongitude");
        float longitude = 0.0F;
        Position instance = null;
        instance.setLongitude(longitude);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Position.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Position instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
