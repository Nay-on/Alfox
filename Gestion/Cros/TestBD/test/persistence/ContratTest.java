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
public class ContratTest {
    
    public ContratTest() {
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
     * Test of create method, of class Contrat.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Connection con = ConnexionMySQL.newConnexion();
        String numero = "A3";
        Timestamp dateCreation = Utils.stringToTimestamp("2017/10/15 12:00:00");
        String modele = "m1";
        String infos = "";
        String expResult = "A3";
        Contrat result = Contrat.create(con, numero, dateCreation, modele, infos, 2, 2, 1);
        assertEquals(expResult, result.getNumero());
        
        result.delete(con);
        
        numero = "A4";
        dateCreation = Utils.stringToTimestamp("2017/10/15 12:00:00");
        modele = "m1";
        infos = "";
        expResult = "A4";
        result = Contrat.create(con, numero, dateCreation, modele, infos, 1, 1, 1);
        assertEquals(expResult, result.getNumero());
        
        result.delete(con);
    }

    /**
     * Test of save method, of class Contrat.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat result = Contrat.getByNumero(con, "C1");
        result.setInfos("nouvelle info");
        result.save(con);
        result = Contrat.getByNumero(con, "C1");
        assertEquals("nouvelle info", result.getInfos());
    }

    /**
     * Test of getByNumero method, of class Contrat.
     */
    @Test
    public void testGetByNumero() throws Exception {
        Connection con = ConnexionMySQL.newConnexion();
        String numero = "C1";
        System.out.println("getByNumero");
        String expResult = "C1";
        Contrat result = Contrat.getByNumero(con, numero);
        assertEquals(expResult, result.getNumero());
        //on cherche un contrat qui n'existe pas ; on attend null
        assertNull(Contrat.getByNumero(con, "X2"));
    }

    /**
     * Test of getNumero method, of class Contrat.
     */
    @Test
    public void testGetNumero() throws Exception {
        System.out.println("getNumero");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat instance = Contrat.getByNumero(con, "C1");
        String expResult = "C1";
        String result = instance.getNumero();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDate method, of class Contrat.
     */
    @Test
    public void testGetDate() throws Exception {
        System.out.println("getDate");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat instance = Contrat.getByNumero(con, "C1");
        Timestamp expResult = Utils.stringToTimestamp("2017/01/01 00:00:00");
        Timestamp result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class Contrat.
     */
    @Test
    public void testGetType() throws Exception {
        System.out.println("getType");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat instance = Contrat.getByNumero(con, "C1");
        String expResult = "annuel";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInfos method, of class Contrat.
     */
    @Test
    public void testGetInfos() throws Exception{
        System.out.println("getInfos");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat instance = Contrat.getByNumero(con, "C1");
        String expResult = "";
        String result = instance.getInfos();
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method, of class Contrat.
     */
    @Test
    public void testSetType() throws Exception {
        System.out.println("setType");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat instance = Contrat.getByNumero(con, "C1");
        String modele = "mensuel";
        instance.setType(modele);
        instance.save(con);
        assertEquals(instance.getType(), modele);
    }

    /**
     * Test of setInfos method, of class Contrat.
     */
    @Test
    public void testSetInfos() throws Exception {
        System.out.println("setInfos");
        Connection con = ConnexionMySQL.newConnexion();
        Contrat instance = Contrat.getByNumero(con, "C1");
        String infos = "Ce contrat est valable un an";
        instance.setInfos(infos);
        instance.save(con);
        assertEquals(instance.getInfos(), infos);
    }
    
}
