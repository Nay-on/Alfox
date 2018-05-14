/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence;

import java.sql.Connection;
import java.sql.Timestamp;
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
        String mode = "NORMAL";
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
        double latitude = 40.123456;
        double longitude = 50.123456;
        long distanceParcourue = 50;
        int seqNumber = 52;
        double snr = 123.45;
        double rssi = 123.56;
        double avgSnr = 123.67;
        String device = "1";
        DonneesTR result = DonneesTR.create(con, mode, datation, vitesse, regime, consommation, vitesseMax, regimeMax, consoMax, nbDefauts, defaut1, defaut2, defaut3, defaut4, latitude, longitude, distanceParcourue, seqNumber, snr, rssi, avgSnr, device);
        assertEquals(Utils.stringToTimestamp("2017/03/22 12:00:00.0"), result.getDatation());
        result.delete(con);
    }

    /**
     * Test of save method, of class DonneesTR.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Connection con = ConnexionMySQL.newConnexion();
        String mode = "NORMAL";
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
        double latitude = 40.123456;
        double longitude = 50.123456;
        long distanceParcourue = 50;
        int seqNumber = 53;
        double snr = 123.45;
        double rssi = 123.56;
        double avgSnr = 123.67;
        String device = "1";
        DonneesTR instance = DonneesTR.create(con, mode, datation, vitesse, regime, consommation, vitesseMax, regimeMax, consoMax, nbDefauts, defaut1, defaut2, defaut3, defaut4, latitude, longitude, distanceParcourue, seqNumber, snr, rssi, avgSnr, device);
        instance.save(con);
        assertEquals(50, instance.getVitesse());
        instance.delete(con);
    }

    /**
     * Test of getMode method, of class DonneesTR.
     */
    @Test
    public void testGetMode() throws Exception {
        System.out.println("getMode");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals("NORMAL", instance.getMode());
    }

    /**
     * Test of getDatation method, of class DonneesTR.
     */
    @Test
    public void testGetDatation() throws Exception {
        System.out.println("getDatation");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(Utils.stringToTimestamp("2018/03/20 01:10:00.0"), instance.getDatation());
    }

    /**
     * Test of getVitesse method, of class DonneesTR.
     */
    @Test
    public void testGetVitesse() throws Exception {
        System.out.println("getVitesse");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(26, instance.getVitesse());
    }

    /**
     * Test of getRegime method, of class DonneesTR.
     */
    @Test
    public void testGetRegime() throws Exception {
        System.out.println("getRegime");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(1589, instance.getRegime());
    }

    /**
     * Test of getConsommation method, of class DonneesTR.
     */
    @Test
    public void testGetConsommation() throws Exception {
        System.out.println("getConsommation");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(1.0, instance.getConsommation(), 0.1);
    }

    /**
     * Test of getVitesseMax method, of class DonneesTR.
     */
    @Test
    public void testGetVitesseMax() throws Exception {
        System.out.println("getVitesseMax");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(62, instance.getVitesseMax());
    }

    /**
     * Test of getRegimeMax method, of class DonneesTR.
     */
    @Test
    public void testGetRegimeMax() throws Exception {
        System.out.println("getRegimeMax");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(3495, instance.getRegimeMax());
    }

    /**
     * Test of getConsoMax method, of class DonneesTR.
     */
    @Test
    public void testGetConsoMax() throws Exception {
        System.out.println("getConsoMax");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(2.0, instance.getConsoMax(), 0.1);
    }

    /**
     * Test of getNbDefauts method, of class DonneesTR.
     */
    @Test
    public void testGetNbDefauts() throws Exception {
        System.out.println("getNbDefauts");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(4, instance.getNbDefauts());
    }

    /**
     * Test of getDefaut1 method, of class DonneesTR.
     */
    @Test
    public void testGetDefaut1() throws Exception {
        System.out.println("getDefaut1");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(71, instance.getDefaut1());
    }

    /**
     * Test of getDefaut2 method, of class DonneesTR.
     */
    @Test
    public void testGetDefaut2() throws Exception {
        System.out.println("getDefaut2");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(29, instance.getDefaut2());
    }

    /**
     * Test of getDefaut3 method, of class DonneesTR.
     */
    @Test
    public void testGetDefaut3() throws Exception {
        System.out.println("getDefaut3");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(26, instance.getDefaut3());
    }

    /**
     * Test of getDefaut4 method, of class DonneesTR.
     */
    @Test
    public void testGetDefaut4() throws Exception {
        System.out.println("getDefaut4");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(199, instance.getDefaut4());
    }

    /**
     * Test of getLatitude method, of class DonneesTR.
     */
    @Test
    public void testGetLatitude() throws Exception {
        System.out.println("getLatitude");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(13.930618, instance.getLatitude(), 0.00001);
    }

    /**
     * Test of getLongitude method, of class DonneesTR.
     */
    @Test
    public void testGetLongitude() throws Exception {
        System.out.println("getLongitude");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(4.677368, instance.getLongitude(), 0.00001);
    }

    /**
     * Test of getDistanceParcourue method, of class DonneesTR.
     */
    @Test
    public void testGetDistanceParcourue() throws Exception {
        System.out.println("getDistanceParcourue");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(18, instance.getDistanceParcourue());
    }

    /**
     * Test of getSeqNumber method, of class DonneesTR.
     */
    @Test
    public void testGetSeqNumber() throws Exception {
        System.out.println("getSeqNumber");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(8, instance.getSeqNumber());
    }

    /**
     * Test of getSnr method, of class DonneesTR.
     */
    @Test
    public void testGetSnr() throws Exception {
        System.out.println("getSnr");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(123.45, instance.getSnr(), 0.01);
    }

    /**
     * Test of getRssi method, of class DonneesTR.
     */
    @Test
    public void testGetRssi() throws Exception {
        System.out.println("getRssi");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(123.56, instance.getRssi(), 0.01);
    }

    /**
     * Test of getAvgSnr method, of class DonneesTR.
     */
    @Test
    public void testGetAvgSnr() throws Exception {
        System.out.println("getAvgSnr");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(123.78, instance.getAvgSnr(), 0.01);
    }

    /**
     * Test of getDevice method, of class DonneesTR.
     */
    @Test
    public void testGetDevice() throws Exception {
        System.out.println("getDevice");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR instance = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals("1", instance.getDevice());
    }

    /**
     * Test of getLastByImmatriculation method, of class DonneesTR.
     */
    @Test
    public void testGetLastByImmatriculation() throws Exception {
        System.out.println("getLastByImmatriculation");
        Connection con = ConnexionMySQL.newConnexion();
        DonneesTR result = DonneesTR.getLastByImmatriculation(con, "1");
        assertEquals(Utils.stringToTimestamp("2018/03/20 01:10:00.0"), result.getDatation());
    }

    /**
     * Test of getByDate method, of class DonneesTR.
     */
    @Test
    public void testGetByDate() throws Exception {
        System.out.println("getByDate");
        Connection con = ConnexionMySQL.newConnexion();
        ArrayList<DonneesTR> result = DonneesTR.getByDate(con, "1", "2018-03-20");
        assertEquals(8, result.size());
        assertEquals(26, result.get(0).getVitesse());
        assertEquals(36, result.get(5).getVitesse());
    }

    /**
     * Test of getDonneesVehicule method, of class DonneesTR.
     */
    @Test
    public void testGetDonneesVehicule() throws Exception {
        System.out.println("getDonneesVehicule");
        Connection con = ConnexionMySQL.newConnexion();
        ArrayList<DonneesTR> result = DonneesTR.getDonneesVehicule(con, "1");
        assertEquals(8, result.size());
        assertEquals(26, result.get(0).getVitesse());
        assertEquals(36, result.get(5).getVitesse());
    }
}