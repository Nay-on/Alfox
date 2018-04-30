package ALFOX;

/**
 * pour l'instant le GPS est fixe, pas de simulation
 * @author jpdms
 */
public class GPS {
    // datation de l'instant d'acquisition
    private int heureGPS = 0;
    private int minuteGPS = 0;
    private int secondeGPS = 0;
    
    // position au lycée LIVH
    private float latitude  = -43.61591f;
    private float longitude = 1.309508f;

    public void maj() {
        // fixer une nouvelle heure courante
        HTR.maj();
        heureGPS = HTR.getHeure();
        minuteGPS = HTR.getMinute();
        secondeGPS = HTR.getSeconde();
    }
    
    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public int getHeureGPS() {
        return heureGPS;
    }

    public int getMinuteGPS() {
        return minuteGPS;
    }

    public int getSecondeGPS() {
        return secondeGPS;
    }
}
