package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
        double latitude = 40.123456;
        double longitude = 50.123456;
        int zoneLimiteID = 1;
        Position result = Position.create(con, zoneLimiteID, ordre, latitude, longitude);
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
        Position result = Position.getById(con, 1);
        result.setLatitude(43.546266); 
        result.save(con);
        result = Position.getById(con, 1);
        assertEquals(43.546266, result.getLatitude(), 0.000001);
        // on restitue l'état initial
        result.setLatitude(43.546231);
        result.save(con);
    }

    /**
     * Test of getByZone method, of class Position.
     */
    @Test
    public void testGetByZone() throws Exception {
        System.out.println("getByZone");
        Connection con = ConnexionMySQL.newConnexion();
        ArrayList<Position> result = Position.getByZone(con, 1);
        assertEquals(result.size(), 8);
        assertEquals(43.659971, result.get(2).getLatitude(), 0.000001);
    }
    
    /**
     * Test of getOrdre method, of class Position.
     */
    @Test
    public void testGetOrdre() throws Exception {
        System.out.println("getOrdre");
        Connection con = ConnexionMySQL.newConnexion();
        ArrayList<Position> result = Position.getByZone(con, 1);
        assertEquals(1, result.get(0).getOrdre());
    }

    /**
     * Test of getLatitude method, of class Position.
     */
    @Test
    public void testGetLatitude() throws Exception {
        System.out.println("getLatitude");
        Connection con = ConnexionMySQL.newConnexion();
        ArrayList<Position> result = Position.getByZone(con, 1);
        assertEquals(43.546231, result.get(0).getLatitude(), 0.000001);
    }

    /**
     * Test of getLongitude method, of class Position.
     */
    @Test
    public void testGetLongitude() throws Exception {
        System.out.println("getLongitude");
        Connection con = ConnexionMySQL.newConnexion();
        ArrayList<Position> result = Position.getByZone(con, 1);
        assertEquals(1.350065, result.get(0).getLongitude(), 0.000001);
    }

    /**
     * Test of setLatitude method, of class Position.
     */
    @Test
    public void testSetLatitude() throws Exception {
        System.out.println("setLatitude");
        Connection con = ConnexionMySQL.newConnexion();
        Position result = Position.getById(con, 1);
        result.setLatitude(43.546266);
        result.save(con);
        result = Position.getById(con, 1);
        assertEquals(43.546266, result.getLatitude(), 0.000001);
        // on restitue l'état initial
        result.setLatitude(43.546231);
        result.save(con);
    }

    /**
     * Test of setLongitude method, of class Position.
     */
    @Test
    public void testSetLongitude() throws Exception {
        System.out.println("setLongitude");
        Connection con = ConnexionMySQL.newConnexion();
        Position result = Position.getById(con, 1);
        result.setLongitude(43.546266);
        result.save(con);
        result = Position.getById(con, 1);
        assertEquals(43.546266, result.getLongitude(), 0.000001);
        // on restitue l'état initial
        result.setLongitude(1.350065);
        result.save(con);
    }
}
