/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

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
 * @author snir2g1
 */
public class DonneesTRTest {
    
    public DonneesTRTest() {
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
     * Test of create method, of class DonneesTR.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Connection con = ConnexionMySQL.newConnexion();
        String mode = "STANDARD";
        Timestamp datation = Utils.stringToTimestamp("2017/03/22 12:00:00");
        int vitesse = 50;
        int regime = 1800;
        int consommation = 7;
        int vitesseMax = 83;
        int regimeMax = 2400;
        int consoMax = 24;
        int nbDefauts = 0;
        int defaut1 = 0;
        int defaut2 = 0;
        int defaut3 = 0;
        int defaut4 = 0;
        float latitudeGPS = (float)40.123456;
        float longitudeGPS = (float)50.123456;
        long distanceParcourue = 50;
        int vehiculeID = 1;
        String expResult = "2017/03/22 12:00:00";
        DonneesTR result = DonneesTR.create(con, mode, datation, vitesse, regime, consommation, vitesseMax, regimeMax, consoMax, nbDefauts, defaut1, defaut2, defaut3, defaut4, latitudeGPS, longitudeGPS, distanceParcourue, vehiculeID);
        assertEquals(expResult, result.getDatation());
        result.delete(con);
    }

    /**
     * Test of save method, of class DonneesTR.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Connection con = ConnexionMySQL.newConnexion();
        String mode = "STANDARD";
        Timestamp datation = Utils.stringToTimestamp("2017/03/22 12:00:00");
        int vitesse = 50;
        int regime = 1800;
        int consommation = 7;
        int vitesseMax = 83;
        int regimeMax = 2400;
        int consoMax = 24;
        int nbDefauts = 0;
        int defaut1 = 0;
        int defaut2 = 0;
        int defaut3 = 0;
        int defaut4 = 0;
        float latitudeGPS = (float)40.123456;
        float longitudeGPS = (float)50.123456;
        long distanceParcourue = 50;
        int vehiculeID = 1;
        DonneesTR instance = DonneesTR.create(con, mode, datation, vitesse, regime, consommation, vitesseMax, regimeMax, consoMax, nbDefauts, defaut1, defaut2, defaut3, defaut4, latitudeGPS, longitudeGPS, distanceParcourue, vehiculeID);
        instance.save(con);
        assertEquals(50, instance.getVitesse());
        instance.delete(con);
    }

    /**
     * Test of getByDatation method, of class DonneesTR.
     */
    @Test
    public void testGetByDatation() throws Exception {
        System.out.println("getByDatation");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR result = DonneesTR.getByDatation(con, "2018-03-20 00:00:00");
        assertEquals("2018-03-20 00:00:00", result.getDatation());
    }

    /**
     * Test of getMode method, of class DonneesTR.
     */
    @Test
    public void testGetMode() throws Exception {
        System.out.println("getMode");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getByDatation(con, "2018-03-20 00:00:00");
        assertEquals("STANDARD", instance.getMode());
    }

    /**
     * Test of getDatation method, of class DonneesTR.
     */
    @Test
    public void testGetDatation() throws Exception {
        System.out.println("getDatation");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getByDatation(con, "2018-03-20 00:00:00");
        assertEquals("2018-03-20 00:00:00", instance.getDatation());
    }

    /**
     * Test of getVitesse method, of class DonneesTR.
     */
    @Test
    public void testGetVitesse() throws Exception {
        System.out.println("getVitesse");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getByDatation(con, "2018-03-20 00:00:00");
        assertEquals(35, instance.getVitesse());
    }

    /**
     * Test of getRegime method, of class DonneesTR.
     */
    @Test
    public void testGetRegime() throws Exception {
        System.out.println("getRegime");
        System.out.println("getRegime");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getByDatation(con, "2018-03-20 00:00:00");
        assertEquals(1479, instance.getRegime());
    }

    /**
     * Test of getConsommation method, of class DonneesTR.
     */
    @Test
    public void testGetConsommation() throws Exception {
        System.out.println("getConsommation");
        System.out.println("getConsommation");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getByDatation(con, "2018-03-20 00:00:00");
        assertEquals(6, instance.getConsommation());
    }

    /**
     * Test of getVitesseMax method, of class DonneesTR.
     */
    @Test
    public void testGetVitesseMax() throws Exception {
        System.out.println("getVitesseMax");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getByDatation(con, "2018-03-20 00:00:00");
        assertEquals(114, instance.getVitesseMax());
    }

    /**
     * Test of getRegimeMax method, of class DonneesTR.
     */
    @Test
    public void testGetRegimeMax() throws Exception {
        System.out.println("getRegimeMax");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getByDatation(con, "2018-03-20 00:00:00");
        assertEquals(3205, instance.getRegimeMax());
    }

    /**
     * Test of getConsoMax method, of class DonneesTR.
     */
    @Test
    public void testGetConsoMax() throws Exception {
        System.out.println("getConsoMax");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getByDatation(con, "2018-03-20 00:00:00");
        assertEquals(30, instance.getConsoMax());
    }

    /**
     * Test of getNbDefauts method, of class DonneesTR.
     */
    @Test
    public void testGetNbDefauts() throws Exception {
        System.out.println("getNbDefauts");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getByDatation(con, "2018-03-20 00:00:00");
        assertEquals(3, instance.getNbDefauts());
    }

    /**
     * Test of getDefaut1 method, of class DonneesTR.
     */
    @Test
    public void testGetDefaut1() throws Exception {
        System.out.println("getDefaut1");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getByDatation(con, "2018-03-20 00:00:00");
        assertEquals(75, instance.getDefaut1());
    }

    /**
     * Test of getDefaut2 method, of class DonneesTR.
     */
    @Test
    public void testGetDefaut2() throws Exception {
        System.out.println("getDefaut2");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getByDatation(con, "2018-03-20 00:00:00");
        assertEquals(5, instance.getDefaut2());
    }

    /**
     * Test of getDefaut3 method, of class DonneesTR.
     */
    @Test
    public void testGetDefaut3() throws Exception {
        System.out.println("getDefaut3");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getByDatation(con, "2018-03-20 00:00:00");
        assertEquals(0, instance.getDefaut3());
    }

    /**
     * Test of getDefaut4 method, of class DonneesTR.
     */
    @Test
    public void testGetDefaut4() throws Exception {
        System.out.println("getDefaut4");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getByDatation(con, "2018-03-20 00:00:00");
        assertEquals(0, instance.getDefaut4());
    }

    /**
     * Test of getLatitude method, of class DonneesTR.
     */
    @Test
    public void testGetLatitude() throws Exception {
        System.out.println("getLatitude");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getByDatation(con, "2018-03-20 00:00:00");
        assertEquals(57.761382, instance.getLatitude(), 0.00001);
    }

    /**
     * Test of getLongitude method, of class DonneesTR.
     */
    @Test
    public void testGetLongitude() throws Exception {
        System.out.println("getLongitude");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getByDatation(con, "2018-03-20 00:00:00");
        assertEquals(11.700803, instance.getLongitude(), 0.00001);
    }

    /**
     * Test of getDistanceParcourue method, of class DonneesTR.
     */
    @Test
    public void testGetDistanceParcourue() throws Exception {
        System.out.println("getDistanceParcourue");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getByDatation(con, "2018-03-20 00:00:00");
        assertEquals(20, instance.getDistanceParcourue());
    }
    
}
