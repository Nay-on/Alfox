package ALFOX;

import java.util.GregorianCalendar;

/**
 *
 * @author jpdms
 */
public class HTR {
    // datation e l'instant présent
    private static GregorianCalendar date = new GregorianCalendar();
    
    public static void maj() {
        date = new GregorianCalendar();
    }
    
    public static int getAnnee() {
        return date.get(java.util.Calendar.YEAR);
    }
    
    // Janvier retourne 1
    public static int getMois() {
        return date.get(java.util.Calendar.MONTH + 1);
    }
    
    public static int getJour() {
        return date.get(java.util.Calendar.DAY_OF_MONTH);
    }
    
    public static int getHeure() {
        return date.get(java.util.Calendar.HOUR_OF_DAY);
    }
    
    public static int getMinute() {
        return date.get(java.util.Calendar.MINUTE);
    }
    
    public static int getSeconde() {
        return date.get(java.util.Calendar.SECOND);
    }
}
