<%-- 
    Document   : CallBackBiDir
    Created on : 9 avr. 2018, 11:46:50
    Author     : snir2g2
--%>

<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.metier.*"%>
<%@page import="com.persistence.*"%>

<%--
UPLINK CALLBACK
Each callback type shares a set of common variables :
time (int): the event timestamp (in seconds since the Unix Epoch)
device (string): device identifier (in hexadecimal ? up to 8 characters)

DATA
You can enter a configuration to define custom variables that will be replaced 
by the parsed data. You can then use these variables in your callback. 

This callback type defines the reception of a user message from a device. 

Common variable available for each subtype are :
data (string): the user data (in hexadecimal)
seqNumber (int): the sequence number of the message if available

Here are the subtypes for this type :

BIDIR
ack (bool): true if this message needs to be acknowledged, false else.

The client can decide not to send any answer to the device. 
There are 2 ways to do so :
    respond to the callback with the HTTP NO_CONTENT code (204).
    respond with a json data containing the noData field ex :
        { "0CB3" : 
            {
                "noData" : true
            }
        }

SERVICE
This callback type defines the reception of an operational message from a device. 

Common variables are:
device (string): device identifier (in hexadecimal ? up to 8 characters)
signal (float): the signal to noise ratio (in dB ? Float value)

Here are the subtypes for this type :

STATUS
batt (float): the voltage of the battery (in Volt ? Float value)
temp (float): the temperature of the device (in °C ? Float value)
seqNumber: the sequence number of the message if available

GEOLOC
radius (int): the radius limit in meter of the zone in which the device is located.
seqNumber (int): the sequence number of the message if available
lat (float): the estimated latitude of the device within a circle based on the 
             GPS data or the Sigfox Geolocation service
lng (float): the estimated longitude of the device within a circle based on the 
             GPS data or the Sigfox Geolocation service
source (int): Define from which source the geolocation has been computed :
                    1 Location computed using the GPS data inside the payload
                    2 Location computed by Atlas Network
                    3 Location computed by Atlas POI
                    4 Location computed by Atlas HD

DOWNLINK CALLBACK
When a message needs to be acknowledged, the callback selected for the downlink 
data must send data in the response. It must contain the 8 bytes data that will 
be sent to the device asking for acknowledgment. 
The data is json formatted, and must be structured as the following :
{
    "device_id" : { "downlinkData" : "deadbeefcafebabe"}
}
    
With device_id beeing replaced by the corresponding device id, in hexadecimal 
format, up to 8 digits. The downlink data must be 8 bytes in hexadecimal format.

--%>

<%
    Connection con = (Connection) session.getAttribute("con");
    if (con == null) {
        con = ConnexionMySQL.newConnexion();
        session.setAttribute("con", con);
    }
    
    String callBackType = request.getParameter("callBackType");
    int seqNumber = Integer.parseInt(request.getParameter("seqNumber"));
        String sigfoxID = request.getParameter("id");
        // la date et l'heure du message SIGFOX
        // the event timestamp (in seconds since the Unix Epoch)
        // le constructeur attends des millisecondes
        Date date = new Date(Long.parseLong(request.getParameter("time")) * 1000);
        Timestamp datation = new Timestamp(date.getTime());
    
    if (callBackType.equals("DATA")) {
        // traitement de la trame utile des données
        String data = request.getParameter("data");
        // le saveData regarde si l'enregistrement pour ce message 
        // existe déjà (création ou update)
        DonneesTR.saveData(con,sigfoxID,seqNumber,datation,data);
    }
    else if (callBackType.equals("GEOLOC")) {
        double latitude = Double.parseDouble(request.getParameter("lat"));
        double longitude = Double.parseDouble(request.getParameter("lng"));
        int    radius = Integer.parseInt(request.getParameter("radius"));
        float  snr = Float.parseFloat(request.getParameter("snr"));
        float  rssi = Float.parseFloat(request.getParameter("rssi"));
        float  avgSnr = Float.parseFloat(request.getParameter("avgSnr"));
        // le saveGeo regarde si l'enregistrement pour ce message 
        // existe déjà (création ou update)
        DonneesTR.saveGeo(con,sigfoxID,seqNumber,datation,snr,rssi,avgSnr,
                                                    radius,latitude,longitude);
    }

%>