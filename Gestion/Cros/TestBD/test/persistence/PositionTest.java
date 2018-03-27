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
     * Test of getByZone method, of class Position.
     */
    @Test
    public void testGetByZone() throws Exception {
        System.out.println("getByZone");
        Connection con = ConnexionMySQL.newConnexion();
        
        String queryString = "select * from position where ZoneLimiteID=1 order by Ordre";
        Statement lStat = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        ArrayList<Position> lstPos = new  ArrayList<Position>();
        while (lResult.next()) {
            int       lOrdre  = lResult.getInt("Ordre");
            double    lLatitude = lResult.getDouble("Latitude");
            double    lLongitude = lResult.getDouble("Longitude");
            lstPos.add("Ordre = " + Utils.toString(lOrdre) + " Latitude = " + Utils.toString(lLatitude) + " Longitude = " + Utils.toString(lLongitude));
        }
        
        ArrayList<Position> result = Position.getByZone(con, 1);
        for (int i=0 ; i<result.size() ; i++) {
            assertEquals(result.get(i), result);
        }
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
        assertEquals(43.668415, result.getLatitude(), 0.000001);
    }

    /**
     * Test of getLongitude method, of class Position.
     */
    @Test
    public void testGetLongitude() throws Exception {
        System.out.println("getLongitude");
        Connection con = ConnexionMySQL.newConnexion();
        Position result = Position.getByOrdre(con, 4);
        assertEquals(1.403108, result.getLongitude(), 0.000001);
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
        instance.setLatitude(40.123456);
        instance.save(con);
        assertEquals(40.123456, instance.getLatitude(), 0.000001);
    }

    /**
     * Test of setLongitude method, of class Position.
     */
    @Test
    public void testSetLongitude() throws Exception {
        System.out.println("setLongitude");
        Connection con = ConnexionMySQL.newConnexion();
        Position instance = Position.getByOrdre(con, 6);
        instance.setLatitude(40.123456);
        instance.save(con);
        assertEquals(40.123456, instance.getLatitude(), 0.000001);
    }  

    /**
     * Test of delete method, of class Position.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Connection con = null;
        Position instance = null;
        boolean expResult = false;
        boolean result = instance.delete(con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
