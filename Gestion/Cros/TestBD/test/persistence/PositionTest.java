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
    public void testGetLongitude() throws Exception {
        System.out.println("getLongitude");
        Connection con = ConnexionMySQL.newConnexion();
        Position result = Position.getByOrdre(con, 4);
        assertEquals(43.668415, result.getLongitude(), 0.00001);
    }

    /**
     * Test of setOrdre method, of class Position.
     */
    @Test
    public void testSetOrdre() throws Exception {
        System.out.println("setOrdre");
        Connection con = ConnexionMySQL.newConnexion();
        Position instance = Position.getByOrdre(con, 8);
        instance.setOrdre(12);
        instance.save(con);
        assertEquals(12, instance.getOrdre());
    }

    /**
     * Test of setLatitude method, of class Position.
     */
    @Test
    public void testSetLatitude() throws Exception {
        System.out.println("setLatitude");
        Connection con = ConnexionMySQL.newConnexion();
        Position instance = Position.getByOrdre(con, 6);
        instance.setLatitude(40.123456F);
        instance.save(con);
        assertEquals(40.123456F, instance.getLatitude(), 0.00001F);
    }

    /**
     * Test of setLongitude method, of class Position.
     */
    @Test
    public void testSetLongitude() throws Exception {
        System.out.println("setLongitude");
        Connection con = ConnexionMySQL.newConnexion();
        Position instance = Position.getByOrdre(con, 6);
        instance.setLatitude(40.123456F);
        instance.save(con);
        assertEquals(40.123456F, instance.getLatitude(), 0.00001F);
    }  
}
