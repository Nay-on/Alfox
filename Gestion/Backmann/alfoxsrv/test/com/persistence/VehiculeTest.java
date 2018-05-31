/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author acros
 */
public class VehiculeTest {
    /**
     * Test of create method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Connection con = ConnexionMySQL.newConnexion();
        String marque = "Citroën";
        String modele = "DS5";
        String immatriculation = "DD-000-EE";
        Timestamp dateMiseEnService = Utils.stringToTimestamp("2018/03/26 00:00:00");
        String motorisation = "Diesel";
        Timestamp dateVidange = Utils.stringToTimestamp("2018/05/26 00:00:00");
        int kmVidange = 0;
        boolean horsZone = false;
        int tauxUtilisation = 0;
        boolean aProbleme = false;
        float compteurReel = 50.0F;
        Timestamp dateControleTechnique = Utils.stringToTimestamp("2020/03/26 00:00:00");
        Vehicule result = Vehicule.create(con, marque, modele, immatriculation, dateMiseEnService, motorisation, dateVidange, kmVidange, horsZone, tauxUtilisation, aProbleme, compteurReel, dateControleTechnique);
        assertEquals("DS5", result.getModele());
        result.delete(con);
    }

    /**
     * Test of save method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule instance = Vehicule.getByImmatriculation(con, "ED-592-CY");
        instance.setCompteurReel(2540.652);
        instance.save(con);
        instance = Vehicule.getByImmatriculation(con, "ED-592-CY");
        assertEquals(2540.652F, instance.getCompteurReel(), 0.1);
        instance.setCompteurReel(40787.0);
        instance.save(con);
    }

    /**
     * Test of getByImmatriculation method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetByImmatriculation() throws Exception {
        System.out.println("getByImmatriculation");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "ED-592-CY");
        assertEquals("ED-592-CY", result.getImmatriculation());
    }

    /**
     * Test of getLastDatation method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetLastDatation() throws Exception {
        System.out.println("getLastDatation");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "ED-592-CY");
        assertEquals(Utils.stringToTimestamp("2018/03/20 01:10:00.0"), result.getLastDatation(con));
    }
    
    /**
     * Test of isDehors method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testIsDehors() throws Exception {
        System.out.println("isDehors");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "ED-592-CY");
        assertEquals(false, result.isDehors(con));
        result = Vehicule.getByImmatriculation(con, "ED-593-VS");
        assertEquals(false, result.isDehors(con));
        result = Vehicule.getByImmatriculation(con, "EE-239-QM");
        assertEquals(false, result.isDehors(con));
        
        // A vérifier celui là, car il est juste à l'Est de Balma !!!
        result = Vehicule.getByImmatriculation(con, "EE-300-QM");
        assertEquals(true, result.isDehors(con));
        
        result = Vehicule.getByImmatriculation(con, "EK-462-GX");
        assertEquals(true, result.isDehors(con));
        result = Vehicule.getByImmatriculation(con, "EM-045-BC");
        assertEquals(true, result.isDehors(con));
        result = Vehicule.getByImmatriculation(con, "EM-150-BE");
        assertEquals(true, result.isDehors(con));
        result = Vehicule.getByImmatriculation(con, "EM-862-ML");
        assertEquals(true, result.isDehors(con));
    }

    /**
     * Test of nbVehiculesDehors method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testNbVehiculesDehors() throws Exception {
        System.out.println("nbVehiculesDehors");
        Connection con = ConnexionMySQL.newConnexion();
        int result = Vehicule.nbVehiculesDehors(con);
        assertEquals(5, result);
    }
    
    /**
     * Test of getMarque method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMarque() throws Exception {
        System.out.println("getMarque");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "ED-592-CY");
        assertEquals("Mercedes", result.getMarque());
    }

    /**
     * Test of getModele method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetModele() throws Exception {
        System.out.println("getModele");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "ED-592-CY");
        assertEquals("Vito", result.getModele());
    }

    /**
     * Test of getImmatriculation method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetImmatriculation() throws Exception {
        System.out.println("getImmatriculation");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "ED-592-CY");
        assertEquals("ED-592-CY", result.getImmatriculation());
    }

    /**
     * Test of getDateMiseEnService method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetDateMiseEnService() throws Exception {
        System.out.println("getDateMiseEnService");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "ED-592-CY");
        assertEquals(Utils.stringToTimestamp("2018/01/01 00:00:00.0"), result.getDateMiseEnService());
    }

    /**
     * Test of getMotorisation method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMotorisation() throws Exception {
        System.out.println("getMotorisation");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "ED-592-CY");
        assertEquals("Diesel", result.getMotorisation());
    }

    /**
     * Test of getDateVidange method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetDateVidange() throws Exception {
        System.out.println("getDateVidange");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "ED-592-CY");
        assertEquals(Utils.stringToTimestamp("2018/01/01 00:00:00.0"), result.getDateVidange());
    }

    /**
     * Test of getKmVidange method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetKmVidange() throws Exception {
        System.out.println("getKmVidange");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "ED-592-CY");
        assertEquals(40787, result.getKmVidange());
    }

    /**
     * Test of getHorsZone method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetHorsZone() throws Exception {
        System.out.println("getHorsZone");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "ED-592-CY");
        assertEquals(false, result.getHorsZone());
    }

    /**
     * Test of getTauxUtilisation method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTauxUtilisation() throws Exception {
        System.out.println("getTauxUtilisation");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "ED-592-CY");
        assertEquals(100, result.getTauxUtilisation());
    }

    /**
     * Test of getAProbleme method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAProbleme() throws Exception {
        System.out.println("getAProbleme");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "ED-592-CY");
        assertEquals(false, result.getAProbleme());
    }

    /**
     * Test of getCompteurReel method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCompteurReel() throws Exception {
        System.out.println("getCompteurReel");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "ED-592-CY");
        assertEquals(result.getCompteurReel(), 40787.0F, 0.1);
    }

    /**
     * Test of getDateControleTechnique method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetDateControleTechnique() throws Exception {
        System.out.println("getDateControleTechnique");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule result = Vehicule.getByImmatriculation(con, "ED-592-CY");
        assertEquals(Utils.stringToTimestamp("2020/01/01 00:00:00.0"), result.getDateControleTechnique());
    }

    /**
     * Test of kmVidange method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetKmVidange() throws Exception {
        System.out.println("setKmVidange");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule instance = Vehicule.getByImmatriculation(con, "ED-592-CY");
        instance.setKmVidange(400);
        instance.save(con);
        assertEquals(instance.getKmVidange(), 400);
        instance.setKmVidange(40787);
        instance.save(con);
    }

    /**
     * Test of setDateVidange method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetDateVidange() throws Exception {
        System.out.println("setDateVidange");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule instance = Vehicule.getByImmatriculation(con, "ED-592-CY");
        instance.setDateVidange("2020/05/05 00:00:00");
        instance.save(con);
        assertEquals(Utils.stringToTimestamp("2020/05/05 00:00:00.0"), instance.getDateVidange());
        instance.setDateVidange("2018/01/01 00:00:00");
        instance.save(con);
    }

    /**
     * Test of setHorsZone method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetHorsZone() throws Exception {
        System.out.println("setHorsZone");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule instance = Vehicule.getByImmatriculation(con, "ED-592-CY");
        instance.setHorsZone(true);
        instance.save(con);
        assertEquals(instance.getHorsZone(), true);
        instance.setHorsZone(false);
        instance.save(con);
    }

    /**
     * Test of setTauxUtilisation method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetTauxUtilisation() throws Exception {
        System.out.println("setTauxUtilisation");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule instance = Vehicule.getByImmatriculation(con, "ED-592-CY");
        instance.setTauxUtilisation(13);
        instance.save(con);
        assertEquals(instance.getTauxUtilisation(), 13);
        instance.setTauxUtilisation(100);
        instance.save(con);
    }

    /**
     * Test of setAProbleme method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetAProbleme() throws Exception {
        System.out.println("setAProbleme");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule instance = Vehicule.getByImmatriculation(con, "ED-592-CY");
        instance.setAProbleme(true);
        instance.save(con);
        assertEquals(instance.getAProbleme(), true);
        instance.setAProbleme(false);
        instance.save(con);
    }

    /**
     * Test of setCompteurReel method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetCompteurReel() throws Exception {
        System.out.println("setCompteurReel");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule instance = Vehicule.getByImmatriculation(con, "ED-592-CY");
        instance.setCompteurReel(45270.31F);
        instance.save(con);
        assertEquals(instance.getCompteurReel(), 45270.31F, 0.01);
        instance.setCompteurReel(40787.0F);
        instance.save(con);
    }

    /**
     * Test of setDateControleTechnique method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetDateControleTechnique() throws Exception {
        System.out.println("setDateControleTechnique");
        Connection con = ConnexionMySQL.newConnexion();
        Vehicule instance = Vehicule.getByImmatriculation(con, "ED-592-CY");
        instance.setDateControleTechnique("2020/05/05 00:00:00");
        instance.save(con);
        assertEquals(Utils.stringToTimestamp("2020/05/05 00:00:00.0"), instance.getDateControleTechnique());
        instance.setDateControleTechnique("2020/01/01 00:00:00");
        instance.save(con);
    }    

    /**
     * Test of getImmatriculations method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetImmatriculations() throws Exception {
        System.out.println("getImmatriculations");
        Connection con = ConnexionMySQL.newConnexion();
        ArrayList<String> result = Vehicule.getImmatriculations(con);
        assertEquals(8, result.size());
        assertEquals("ED-592-CY", result.get(0));
        assertEquals("EM-862-ML", result.get(7));
    }

    /**
     * Test of getKmMoyenFlotte method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetKmMoyenFlotte() throws Exception {
        System.out.println("getKmMoyenFlotte");
        Connection con = ConnexionMySQL.newConnexion();
        double result = Vehicule.getKmMoyenFlotte(con);
        assertEquals(66377.625, result, 0.001);
    }

    /**
     * Test of getKmMoyenMensuelFlotte method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetKmMoyenMensuelFlotte() throws Exception {
        System.out.println("getKmMoyenMensuelFlotte");
        Connection con = ConnexionMySQL.newConnexion();
        double result = Vehicule.getKmMoyenMensuelFlotte(con);
        assertEquals(13275.525, result, 0.00001);
    }

    /**
     * Test of getAgeMoyenFlotte method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAgeMoyenFlotte() throws Exception {
        System.out.println("getAgeMoyenFlotte");
        Connection con = ConnexionMySQL.newConnexion();
        int result = Vehicule.getAgeMoyenFlotte(con);
        assertEquals(150, result);  // le 30 Mai 2018
    }

    /**
     * Test of size method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testSize() throws Exception {
        System.out.println("size");
        Connection con = ConnexionMySQL.newConnexion();
        int result = Vehicule.size(con);
        assertEquals(8, result);
    }

    /**
     * Test of getConsoMoyenneFlotte method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetConsoMoyenneFlotte() throws Exception {
        System.out.println("getConsoMoyenneFlotte");
        Connection con = ConnexionMySQL.newConnexion();
        double result = Vehicule.getConsoMoyenneFlotte(con);
        assertEquals(7.21, result, 0.01);
    }

    /**
     * Test of getConsoMoyenneMensuelleFlotte method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetConsoMoyenneMensuelleFlotte() throws Exception {
        System.out.println("getConsoMoyenneMensuelleFlotte");
        Connection con = ConnexionMySQL.newConnexion();
        double result = Vehicule.getConsoMoyenneMensuelleFlotte(con);
        assertEquals(1.44, result, 0.01);
    }

    /**
     * Test of getTempsAlcis method, of class Vehicule.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTempsAlcis() throws Exception {
        System.out.println("getTempsAlcis");
        Connection con = ConnexionMySQL.newConnexion();
        int result = Vehicule.getTempsAlcis(con, "EE-300-QM");
        // au garage depuis le 20 mars
        // (12 + 30 + 28)*24*60 = 100 800 mn au 29 Mai
        // (12 + 30 + 31 + 30)*24*60 = 148 320 au 31 Juin
        assertTrue(result > 100800);
        assertTrue(result < 148320);
    }
}
