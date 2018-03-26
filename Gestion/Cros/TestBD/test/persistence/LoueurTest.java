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
public class LoueurTest {
    
    public LoueurTest() {
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
     * Test of create method, of class Loueur.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Connection con = ConnexionMySQL.newConnexion();
        String nom = "Picasso";
        String prenom = "Pablo";
        String telephone = "0610090807";
        String mail = "pablopicasso@gmail.com";
        Loueur result = Loueur.create(con, nom, prenom, telephone, mail);
        assertEquals("Picasso", result.getNom());
        result.delete(con);
    }

    /**
     * Test of save method, of class Loueur.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Connection con = ConnexionMySQL.newConnexion();
        Loueur instance = Loueur.getByNom(con, "Magritte", "René");
        instance.setMail("nouveaumail@gmail.com");
        instance.save(con);
        instance = Loueur.getByNom(con, "Magritte", "René");
        assertEquals("nouveaumail@gmail.com", instance.getMail());
    }

    /**
     * Test of getByNom method, of class Loueur.
     */
    @Test
    public void testGetByNom() throws Exception {
        System.out.println("getByNom");
        Connection con = ConnexionMySQL.newConnexion();
        Loueur result = Loueur.getByNom(con, "Magritte", "René");
        assertEquals("Magritte", result.getNom());
    }

    /**
     * Test of getNom method, of class Loueur.
     */
    @Test
    public void testGetNom() throws Exception {
        System.out.println("getNom");
        Connection con = ConnexionMySQL.newConnexion();
        Loueur instance = Loueur.getByNom(con, "Magritte", "René");
        assertEquals("Magritte", instance.getNom());
    }

    /**
     * Test of getPrenom method, of class Loueur.
     */
    @Test
    public void testGetPrenom() throws Exception {
        System.out.println("getPrenom");
        Connection con = ConnexionMySQL.newConnexion();
        Loueur instance = Loueur.getByNom(con, "Magritte", "René");
        assertEquals("René", instance.getPrenom());
    }

    /**
     * Test of getTelephone method, of class Loueur.
     */
    @Test
    public void testGetTelephone() throws Exception {
        System.out.println("getTelephone");
        Connection con = ConnexionMySQL.newConnexion();
        Loueur instance = Loueur.getByNom(con, "Magritte", "René");
        assertEquals("0607080910", instance.getTelephone());
    }

    /**
     * Test of getMail method, of class Loueur.
     */
    @Test
    public void testGetMail() throws Exception {
        System.out.println("getMail");
        Connection con = ConnexionMySQL.newConnexion();
        Loueur instance = Loueur.getByNom(con, "Magritte", "Bernard");
        assertEquals("bernardmagritte@gmail.com", instance.getMail());
    }

    /**
     * Test of setTelephone method, of class Loueur.
     */
    @Test
    public void testSetTelephone() throws Exception {
        System.out.println("setTelephone");
        Connection con = ConnexionMySQL.newConnexion();
        Loueur instance = Loueur.getByNom(con, "Magritte", "René");
        instance.setTelephone("0606060606");
        assertEquals("0606060606", instance.getTelephone());
    }

    /**
     * Test of setMail method, of class Loueur.
     */
    @Test
    public void testSetMail() throws Exception {
        System.out.println("setMail");
        Connection con = ConnexionMySQL.newConnexion();
        Loueur instance = Loueur.getByNom(con, "Magritte", "René");
        instance.setMail("nouveaumail@gmail.com");
        assertEquals("nouveaumail@gmail.com", instance.getMail());
    }
    
}
