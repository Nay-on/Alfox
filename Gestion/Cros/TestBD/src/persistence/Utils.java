/*
 * Fichier : Utils.java
 * Description : Classe librairie
 * Cette classe propose quelques fonctions utiles
 * formatage des données en provenance de requête SQL
 * et en particulier l'encryptage du mot de passe par la méthode 'MD5'
 */

package persistence;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.*;
import java.util.Date;
import java.util.Random;

/**
 * Static tools for BDClass for BD
 * @author  LVH
 * @since   02/2014 
 * 
 * @version 0.1.0
 *
 */
public final class Utils {
    /**
     * DateFormat for all Date type
     */
    static private SimpleDateFormat staticDateStandard = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    /**
     * Static tools function class
     */
    private Utils() { }
    /*
     * encode un mot de passe, pas de décryptage possible
     * pour le comparer il faut encoder la proposition
    */
    
    public static String encryptPassword( String password ) {
       String encrypted = "";
       try {
           MessageDigest digest = MessageDigest.getInstance("MD5"); 
           byte[] passwordBytes = password.getBytes(); 
           digest.reset();
           digest.update(passwordBytes);
           byte[] message = digest.digest();
           StringBuilder hexString = new StringBuilder();
           for ( int i=0; i < message.length; i++)
               hexString.append( Integer.toHexString(0xFF & message[i]));
           encrypted = hexString.toString();
       }
       catch( Exception e ) { }
       return encrypted.toUpperCase(); 
   }
    
    /**
     * A function that parse the result of a Date column request and
     * return the Date or 'null' if the result is an empty string (for DB)
     * @param  pString   the String to parse (can be null)
     * @return the Date conversion or 'null'
     * @throws ParseException Conversion pb from String to Date
     */ 
    public static Date getDate(String pString) throws ParseException {
        if ((pString == null) || (pString.equals(""))) {
            return null;
        }
        else {
            return staticDateStandard.parse(pString);
        }
    }
    
    /**
     * A function that parse the result of a Double column request and
     * return the Double or 'null' if the result is an empty string (for DB XML)
     * @param  pString   the String to parse (can be null)
     * @return the Double conversion or 'null'
     */ 
    public static Double getDouble(String pString) {
        if ((pString == null) || (pString.equals(""))) {
            return null;
        }
        else {
            return Double.valueOf(pString);
        }
    }
    
    /**
     * A function that parse the result of a Integer column request and
     * return the Integer or 'null' if the result is an empty string (for DB XML)
     * @param  pString   the String to parse (can be null)
     * @return the Integer conversion or 'null'
     */ 
    public static Integer getInteger(String pString) {
        if ((pString == null) || (pString.equals(""))) {
            return null;
        }
        else {
            return Integer.valueOf(pString);
        }
    }
    
    /**
     * A function that gives the string conversion for a SQL request
     * patch some value because of bugs in the SQL INPUT instruction
     * @param pVal   the value to convert 
     *               (can be String, Date, Integer, Double, Boolean or null)
     * @return the string conversion or "NULL" if pVal = null
     */ 
    static public String toString(Object pVal) {
        String lResult = null;
        if (pVal != null) {
// ---------------------------------------------------------------------
//                          MySQL changes            
// ---------------------------------------------------------------------
            if (pVal instanceof String) {
                lResult = "'" + pVal + "'";
            }
            if (pVal instanceof Date) {
                lResult = "'" + staticDateStandard.format((Date)pVal) + "'";
            }
            if (pVal instanceof Timestamp) {
                lResult = "'" + staticDateStandard.format((Date)pVal) + "'";
            }
            if (pVal instanceof Integer) { 
                lResult = String.valueOf((Integer)pVal);
            }
            if (pVal instanceof Double) {
                // rounded to two decimal places
                double roundedValue = Math.rint(((Double)pVal) * 100.0) / 100.0;
                lResult = String.valueOf(roundedValue);
            }
            if (pVal instanceof Boolean) { 
                lResult = String.valueOf((Boolean)pVal);
            }
        }
        else {
            // all empty string are NULL value
            lResult = "NULL";
        }
        return  lResult;
    }

/*  ------------------------ Debug functions -----------------------------  */
    /**
     * A function that limit String parameter to lMax chars
     * @param  pS  the String parameter to limit in length
     * @return the lMax cars begin of the String
     */
    static public String toShortString(String pS) {
        final int lMax = 14;
        String lS = "                     ";
        if (pS.length() < lMax) {
            pS = pS + lS.substring(0, lMax - pS.length());
        }
        if (pS.length() > lMax) {
            pS = pS.substring(0, lMax - 1) + ".'";
        }
        return pS;
    }
    
    // formatage d'une date "2017-23-12" > "12/23/2017"
    static public String formatDate(String _date) {
        return _date.substring(6, 8) + '/' + _date.substring(4, 6) + '/' 
                                                      + _date.substring(0, 4);
    }
    
    // formatage d'une stampDate "2014-02-03 15:12:56.0" > "03/02/2014 à 15h12mn56s"
    static public String formatStampDate(String _date) {
        return _date.substring(8, 10) + '/' + _date.substring(5, 7) + '/' 
            + _date.substring(0, 4)
            + " à " 
            + _date.substring(11, 13) + 'h' + _date.substring(14, 16) + "mn" 
            + _date.substring(17, 19) + 's';
    }

    // création d'un mot de passe aléatoire d'une longueur donnée
    static public String newPassword(int longueur) {
        String minus = "abcdefghijklmnopqrstuvwxyz";
        String majus = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";  
        String numbers = "1234567890";
        String pwd = "";
        Random rand = new Random();
   
        while (pwd.length() < longueur) {
            int rPick = rand.nextInt(4);
            
            switch (rPick) {
                case 0 :
                    pwd+=minus.charAt(rand.nextInt(25));
                    break;
                case 1 :
                    pwd+=minus.charAt(rand.nextInt(25));
                    break;
                case 2 :
                    pwd+=majus.charAt(rand.nextInt(25));
                    break;
                case 3 :
                    pwd+=numbers.charAt(rand.nextInt(9));
                    break;
            }
        }
        return pwd;
    }
    
    // Test
    public static void main(String[] argv) {
        System.out.println(newPassword(6));
        System.out.println(newPassword(6));
        System.out.println(newPassword(6));
    }
}
