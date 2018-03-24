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
public class VehiculeTest {
    
    public VehiculeTest() {
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
     * Test of create method, of class Vehicule.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Connection con = ConnexionMySQL.newConnexion();
        String marque = "Citroën";
        String modele = "DS5";
        String immatriculation = "DD-000-EE";
        Timestamp dateMiseEnService = null;
        String motorisation = "Diesel";
        String idSigfox = "0123";
        Timestamp dateVidange = null;
        int kmVidange = 0;
        boolean horsZone = false;
        int tauxUtilisation = 0;
        boolean aProbleme = false;
        float compteurReel = 50.0F;
        Timestamp dateControleTechnique = null;
        Vehicule expResult = null;
        Vehicule result = Vehicule.create(con, marque, modele, immatriculation, dateMiseEnService, motorisation, idSigfox, dateVidange, kmVidange, horsZone, tauxUtilisation, aProbleme, compteurReel, dateControleTechnique);
        assertEquals("DS5", result.getModele());
        result.delete(con);
    }

    /**
     * Test of save method, of class Vehicule.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule instance = Vehicule.getByImmatriculation(con, "AA-000-BB");
        instance.setCompteurReel(2540.652F);
        instance.save(con);
        instance = Vehicule.getByImmatriculation(con, "AA-000-BB");
        assertEquals(2540.652F, instance.getCompteurReel(), 0.001);
    }

    /**
     * Test of getByImmatriculation method, of class Vehicule.
     */
    @Test
    public void testGetByImmatriculation() throws Exception {
        System.out.println("getByImmatriculation");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "AA-000-BB");
        assertEquals("AA-000-BB", result.getImmatriculation());
    }

    /**
     * Test of getLastDatation method, of class Vehicule.
    
    @Test
    public void testGetLastDatation() throws Exception {
        System.out.println("getLastDatation");
        Connection con = null;
        Vehicule instance = null;
        Timestamp expResult = null;
        Timestamp result = instance.getLastDatation(con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    
    /**
     * Test of isDehors method, of class Vehicule.
     */
    @Test
    public void testIsDehors() throws Exception {
        System.out.println("isDehors");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "AA-000-BB");
        assertEquals(false, result.isDehors(con));
    }

    /**
     * Test of getMarque method, of class Vehicule.
     */
    @Test
    public void testGetMarque() throws Exception {
        System.out.println("getMarque");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "AA-000-BB");
        assertEquals("Audi", result.getMarque());
    }

    /**
     * Test of getModele method, of class Vehicule.
     */
    @Test
    public void testGetModele() throws Exception {
        System.out.println("getModele");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "AA-000-BB");
        assertEquals("RS6", result.getModele());
    }

    /**
     * Test of getImmatriculation method, of class Vehicule.
     */
    @Test
    public void testGetImmatriculation() throws Exception {
        System.out.println("getImmatriculation");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "AA-000-BB");
        assertEquals("AA-000-BB", result.getImmatriculation());
    }

    /**
     * Test of getDateMiseEnService method, of class Vehicule.
     */
    @Test
    public void testGetDateMiseEnService() throws Exception {
        System.out.println("getDateMiseEnService");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "AA-000-BB");
        assertEquals("2018-01-01 00:00:00", result.getDateMiseEnService());
    }

    /**
     * Test of getMotorisation method, of class Vehicule.
     */
    @Test
    public void testGetMotorisation() throws Exception {
        System.out.println("getMotorisation");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "AA-000-BB");
        assertEquals("Essence", result.getMotorisation());
    }

    /**
     * Test of getIdSigfox method, of class Vehicule.
     */
    @Test
    public void testGetIdSigfox() throws Exception {
        System.out.println("getIdSigfox");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "AA-000-BB");
        assertEquals("1", result.getIdSigfox());
    }

    /**
     * Test of getDateVidange method, of class Vehicule.
     */
    @Test
    public void testGetDateVidange() throws Exception {
        System.out.println("getDateVidange");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "AA-000-BB");
        assertEquals("2018-01-01 00:00:00", result.getDateVidange());
    }

    /**
     * Test of getKmVidange method, of class Vehicule.
     */
    @Test
    public void testGetKmVidange() throws Exception {
        System.out.println("getKmVidange");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "AA-000-BB");
        assertEquals(30000, result.getKmVidange());
    }

    /**
     * Test of getHorsZone method, of class Vehicule.
     */
    @Test
    public void testGetHorsZone() throws Exception {
        System.out.println("getHorsZone");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "AA-000-BB");
        assertEquals(false, result.getHorsZone());
    }

    /**
     * Test of getTauxUtilisation method, of class Vehicule.
     */
    @Test
    public void testGetTauxUtilisation() throws Exception {
        System.out.println("getTauxUtilisation");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "AA-000-BB");
        assertEquals(100, result.getTauxUtilisation());
    }

    /**
     * Test of getAProbleme method, of class Vehicule.
     */
    @Test
    public void testGetAProbleme() throws Exception {
        System.out.println("getAProbleme");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "AA-000-BB");
        assertEquals(false, result.getAProbleme());
    }

    /**
     * Test of getCompteurReel method, of class Vehicule.
     */
    @Test
    public void testGetCompteurReel() throws Exception {
        System.out.println("getCompteurReel");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "AA-000-BB");
        assertEquals(result.getCompteurReel(), 21526.15F, 0.1);
    }

    /**
     * Test of getDateControleTechnique method, of class Vehicule.
     */
    @Test
    public void testGetDateControleTechnique() throws Exception {
        System.out.println("getDateControleTechnique");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "AA-000-BB");
        assertEquals("2020-01-01 00:00:00", result.getDateControleTechnique());
    }

    /**
     * Test of kmVidange method, of class Vehicule.
     */
    @Test
    public void testSetKmVidange() throws Exception {
        System.out.println("setKmVidange");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule instance = Vehicule.getByImmatriculation(con, "AA-000-BB");
        instance.setKmVidange(400);
        instance.save(con);
        assertEquals(instance.getKmVidange(), 400);
    }

    /**
     * Test of setDateVidange method, of class Vehicule.
     */
    @Test
    public void testSetDateVidange() throws Exception {
        System.out.println("setDateVidange");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule instance = Vehicule.getByImmatriculation(con, "AA-000-BB");
        instance.setDateVidange("2020-05-05 00:00:00");
        instance.save(con);
        assertEquals(instance.getDateVidange(), "2020-05-05 00:00:00");
    }

    /**
     * Test of setHorsZone method, of class Vehicule.
     */
    @Test
    public void testSetHorsZone() throws Exception {
        System.out.println("setHorsZone");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule instance = Vehicule.getByImmatriculation(con, "AA-000-BB");
        instance.setHorsZone(true);
        instance.save(con);
        assertEquals(instance.getHorsZone(), true);
    }

    /**
     * Test of setTauxUtilisation method, of class Vehicule.
     */
    @Test
    public void testSetTauxUtilisation() throws Exception {
        System.out.println("setTauxUtilisation");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule instance = Vehicule.getByImmatriculation(con, "AA-000-BB");
        instance.setTauxUtilisation(13);
        instance.save(con);
        assertEquals(instance.getTauxUtilisation(), 13);
    }

    /**
     * Test of setAProbleme method, of class Vehicule.
     */
    @Test
    public void testSetAProbleme() throws Exception {
        System.out.println("setAProbleme");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule instance = Vehicule.getByImmatriculation(con, "AA-000-BB");
        instance.setAProbleme(true);
        instance.save(con);
        assertEquals(instance.getAProbleme(), true);
    }

    /**
     * Test of setCompteurReel method, of class Vehicule.
     */
    @Test
    public void testSetCompteurReel() throws Exception {
        System.out.println("setCompteurReel");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule instance = Vehicule.getByImmatriculation(con, "AA-000-BB");
        instance.setCompteurReel(45270.31F);
        instance.save(con);
        assertEquals(instance.getCompteurReel(), 45270.31F, 0.01);
    }

    /**
     * Test of setDateControleTechnique method, of class Vehicule.
     */
    @Test
    public void testSetDateControleTechnique() throws Exception {
        System.out.println("setDateControleTechnique");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule instance = Vehicule.getByImmatriculation(con, "AA-000-BB");
        instance.setDateControleTechnique("2020-05-05 00:00:00");
        instance.save(con);
        assertEquals(instance.getDateControleTechnique(), "2020-05-05 00:00:00");
    }    
}
