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
public class UserTest {
    
    public UserTest() {
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
     * Test of create method, of class User.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Connection con = ConnexionMySQL.newConnexion();
        String role = "technicien";
        String mdp = "technicien";
        String mail = "technicien@gmail.com";
        User result = User.create(con, role, mdp, mail);
        assertEquals("technicien@gmail.com", result.getMail());
        result.delete(con);
    }

    /**
     * Test of save method, of class User.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Connection con = ConnexionMySQL.newConnexion();
        User instance = User.getByMotDePasse(con, "responsable");
        instance.setMail("nouveau@gmail.com");
        instance.save(con);
        instance = User.getByMotDePasse(con, "responsable");
        assertEquals("nouveau@gmail.com", instance.getMail());
    }

    /**
     * Test of getByMotDePasse method, of class User.
     */
    @Test
    public void testGetByMotDePasse() throws Exception {
        System.out.println("getByMotDePasse");
        Connection con = ConnexionMySQL.newConnexion();
        User result = User.getByMotDePasse(con, "maintenance");
        assertEquals("maintenance", result.getRole());
    }

    /**
     * Test of getRole method, of class User.
     */
    @Test
    public void testGetRole() throws Exception {
        System.out.println("getRole");
        Connection con = ConnexionMySQL.newConnexion();
        User instance = User.getByMotDePasse(con, "maintenance");
        assertEquals("maintenance", instance.getRole());
    }

    /**
     * Test of getMdp method, of class User.
     */
    @Test
    public void testGetMdp() throws Exception {
        System.out.println("getMdp");
        Connection con = ConnexionMySQL.newConnexion();
        User instance = User.getByMotDePasse(con, "responsable");
        assertEquals("responsable", instance.getMdp());
    }

    /**
     * Test of getMail method, of class User.
     */
    @Test
    public void testGetMail() throws Exception {
        System.out.println("getMail");
        Connection con = ConnexionMySQL.newConnexion();
        User instance = User.getByMotDePasse(con, "maintenance");
        assertEquals("maintenance@free.fr", instance.getMail());
    }

    /**
     * Test of setRole method, of class User.
     */
    @Test
    public void testSetRole() throws Exception {
        System.out.println("setRole");
        Connection con = ConnexionMySQL.newConnexion();
        User instance = User.getByMotDePasse(con, "responsable");
        instance.setRole("recrue");
        instance.save(con);
        assertEquals(instance.getRole(), "recrue");
        instance.setRole("responsable");
        instance.save(con);
    }

    /**
     * Test of setMail method, of class User.
     */
    @Test
    public void testSetMail() throws Exception {
        System.out.println("setMail");
        Connection con = ConnexionMySQL.newConnexion();
        User instance = User.getByMotDePasse(con, "responsable");
        instance.setMail("nouveaumail@gmail.com");
        instance.save(con);
        assertEquals(instance.getMail(), "nouveaumail@gmail.com");
    }

    /**
     * Test of setMdp method, of class User.
     */
    @Test
    public void testSetMdp() throws Exception {
        System.out.println("setMdp");
        Connection con = ConnexionMySQL.newConnexion();
        User instance = User.getByMotDePasse(con, "responsable");
        instance.setMdp("nouveau");
        instance.save(con);
        assertEquals(instance.getMdp(), "nouveau");
    }
}
