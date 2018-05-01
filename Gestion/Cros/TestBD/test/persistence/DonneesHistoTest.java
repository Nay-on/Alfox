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
 * @author acros
 */
public class DonneesHistoTest {
    
    public DonneesHistoTest() {
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
     * Test of create method, of class DonneesHisto.
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
        double latitudeGPS = 40.123456;
        double longitudeGPS = 50.123456;
        long distanceParcourue = 50;
        int vehiculeID = 1;
        DonneesHisto result = DonneesHisto.create(con, mode, datation, vitesse, regime, consommation, vitesseMax, regimeMax, consoMax, nbDefauts, defaut1, defaut2, defaut3, defaut4, latitudeGPS, longitudeGPS, distanceParcourue, vehiculeID);
        assertEquals(Utils.stringToTimestamp("2017/03/22 12:00:00"), result.getDatation());
        result.delete(con);
    }

    /**
     * Test of save method, of class DonneesHisto.
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
        double latitudeGPS = 40.123456f;
        double longitudeGPS = 50.123456f;
        long distanceParcourue = 50;
        int vehiculeID = 1;
        DonneesHisto instance = DonneesHisto.create(con, mode, datation, vitesse, regime, consommation, vitesseMax, regimeMax, consoMax, nbDefauts, defaut1, defaut2, defaut3, defaut4, latitudeGPS, longitudeGPS, distanceParcourue, vehiculeID);
        instance.save(con);
        assertEquals(50, instance.getVitesse());
        instance.delete(con);
    }
    
    /**
     * Test of getByDatation method, of class DonneesHisto.
     */
    @Test
    public void testGetByDatation() throws Exception {
        System.out.println("getByDatation");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesHisto result = DonneesHisto.getByDatation(con, "2018/01/03 17:42:37.0");
        assertEquals(Utils.stringToTimestamp("2018/01/03 17:42:37"), result.getDatation());
    }

    /**
     * Test of getMode method, of class DonneesHisto.
     */
    @Test
    public void testGetMode() throws Exception {
        System.out.println("getMode");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesHisto instance = DonneesHisto.getByDatation(con, "2018/01/03 17:42:37.0");
        assertEquals("STANDARD", instance.getMode());
    }

    /**
     * Test of getDatation method, of class DonneesHisto.
     */
    @Test
    public void testGetDatation() throws Exception{
        System.out.println("getDatation");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesHisto instance = DonneesHisto.getByDatation(con, "2018/01/03 17:42:37.0");
        assertEquals(Utils.stringToTimestamp("2018/01/03 17:42:37.0"), instance.getDatation());
    }

    /**
     * Test of getVitesse method, of class DonneesHisto.
     */
    @Test
    public void testGetVitesse() throws Exception{
        System.out.println("getVitesse");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesHisto instance = DonneesHisto.getByDatation(con, "2018/01/03 17:42:37.0");
        assertEquals(50, instance.getVitesse());
    }

    /**
     * Test of getRegime method, of class DonneesHisto.
     */
    @Test
    public void testGetRegime() throws Exception{
        System.out.println("getRegime");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesHisto instance = DonneesHisto.getByDatation(con, "2018/01/03 17:42:37.0");
        assertEquals(1800, instance.getRegime());
    }

    /**
     * Test of getConsommation method, of class DonneesHisto.
     */
    @Test
    public void testGetConsommation() throws Exception {
        System.out.println("getConsommation");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesHisto instance = DonneesHisto.getByDatation(con, "2018/01/03 17:42:37.0");
        assertEquals(62, instance.getConsommation());
    }

    /**
     * Test of getVitesseMax method, of class DonneesHisto.
     */
    @Test
    public void testGetVitesseMax() throws Exception {
        System.out.println("getVitesseMax");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesHisto instance = DonneesHisto.getByDatation(con, "2018/01/03 17:42:37.0");
        assertEquals(136, instance.getVitesseMax());
    }

    /**
     * Test of getRegimeMax method, of class DonneesHisto.
     */
    @Test
    public void testGetRegimeMax() throws Exception{
        System.out.println("getRegimeMax");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesHisto instance = DonneesHisto.getByDatation(con, "2018/01/03 17:42:37.0");
        assertEquals(3600, instance.getRegimeMax());
    }

    /**
     * Test of getConsoMax method, of class DonneesHisto.
     */
    @Test
    public void testGetConsoMax() throws Exception {
        System.out.println("getConsoMax");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesHisto instance = DonneesHisto.getByDatation(con, "2018/01/03 17:42:37.0");
        assertEquals(250, instance.getConsoMax());
    }

    /**
     * Test of getNbDefauts method, of class DonneesHisto.
     */
    @Test
    public void testGetNbDefauts() throws Exception {
        System.out.println("getNbDefauts");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesHisto instance = DonneesHisto.getByDatation(con, "2018/01/03 17:42:37.0");
        assertEquals(0, instance.getNbDefauts());
    }

    /**
     * Test of getDefaut1 method, of class DonneesHisto.
     */
    @Test
    public void testGetDefaut1() throws Exception {
        System.out.println("getDefaut1");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesHisto instance = DonneesHisto.getByDatation(con, "2018/01/03 17:42:37.0");
        assertEquals(0, instance.getDefaut1());
    }

    /**
     * Test of getDefaut2 method, of class DonneesHisto.
     */
    @Test
    public void testGetDefaut2() throws Exception {
        System.out.println("getDefaut2");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesHisto instance = DonneesHisto.getByDatation(con, "2018/01/03 17:42:37.0");
        assertEquals(0, instance.getDefaut2());
    }

    /**
     * Test of getDefaut3 method, of class DonneesHisto.
     */
    @Test
    public void testGetDefaut3() throws Exception{
        System.out.println("getDefaut3");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesHisto instance = DonneesHisto.getByDatation(con, "2018/01/03 17:42:37.0");
        assertEquals(0, instance.getDefaut3());
    }

    /**
     * Test of getDefaut4 method, of class DonneesHisto.
     */
    @Test
    public void testGetDefaut4() throws Exception{
        System.out.println("getDefaut4");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesHisto instance = DonneesHisto.getByDatation(con, "2018/01/03 17:42:37.0");
        assertEquals(0, instance.getDefaut4());
    }

    /**
     * Test of getLatitudeGPS method, of class DonneesHisto.
     */
    @Test
    public void testGetLatitudeGPS() throws Exception {
        System.out.println("getLatitudeGPS");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesHisto instance = DonneesHisto.getByDatation(con, "2018/01/03 17:42:37.0");
        assertEquals(40.564345, instance.getLatitudeGPS(), 0.00001);
    }

    /**
     * Test of getLongitudeGPS method, of class DonneesHisto.
     */
    @Test
    public void testGetLongitudeGPS() throws Exception {
        System.out.println("getLongitudeGPS");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesHisto instance = DonneesHisto.getByDatation(con, "2018/01/03 17:42:37.0");
        assertEquals(50.654734, instance.getLongitudeGPS(), 0.00001);
    }

    /**
     * Test of getDistanceParcourue method, of class DonneesHisto.
     */
    @Test
    public void testGetDistanceParcourue() throws Exception {
        System.out.println("getDistanceParcourue");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesHisto instance = DonneesHisto.getByDatation(con, "2018/01/03 17:42:37.0");
        assertEquals(7, instance.getDistanceParcourue());
    }

    /**
     * Test of getVehiculeID method, of class DonneesHisto.
     */
    @Test
    public void testGetVehiculeID() throws Exception {
        System.out.println("getVehiculeID");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesHisto instance = DonneesHisto.getByDatation(con, "2018/01/03 17:42:37.0");
        assertEquals(1, instance.getVehiculeID());
    }
    
}
