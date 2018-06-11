
var map;
var markers = [];

// --------------------- callback ----------------------- 

$(function () {
    $("#listeZones li").on("click", centrerZone);
    $("#panelVehicules li").on("click", centrerVehicule);
});

function initialize() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 11,
        center: {lat: 43.601245, lng: 1.445555}
    });
    // dessine les zones sur la map
    $.ajax({
        url: 'alfoxControl.jsp?action=r_getZones',
        type: 'POST',
        data: {},
        dataType: 'html',
        success: function (data) {
            var tabZones = data.split("##");
            for (i = 1; i < tabZones.length - 1; i++) {
                var tabInfos = tabZones[i].split("||");
                var nom = tabInfos[1];
                poly = new google.maps.Polyline({
                    strokeColor: '#FF0000',
                    strokeOpacity: 1.0,
                    strokeWeight: 2
                });
                poly.setMap(map);
                var path = poly.getPath();
                for (var i = 2; i < tabInfos.length - 1; i = i + 2) {
                    path.push(new google.maps.LatLng(tabInfos[i], tabInfos[i+1]));
                }
                // la 1er coordonnée pour fermer le polygone
                path.push(new google.maps.LatLng(tabInfos[2], tabInfos[3]));
            }
        }
    });
    // place les véhicules sur la map
    $.ajax({
        url: 'alfoxControl.jsp?action=r_getVehiculesPositions',
        type: 'POST',
        data: {},
        dataType: 'html',
        success: function (data) {
            var tabInfos = data.split("||");
            for (var i = 1; i < tabInfos.length - 1; i = i + 6) {
                var mode;
                var couleur = 'green';
               
                // tabInfos[i] = isDehors ; tabInfos[i+1] = aProbleme
                switch (tabInfos[i+1]) {
                    case 'true':
                        // Si panne alors marqueur orange
                        couleur = 'orange';
                        break;
                    default:
                        couleur = couleur;
                }
                
                switch (tabInfos[i]) {
                    case 'true':
                        // Si hors zone marqueur rouge
                        // Si panne + hors zone marqueur rouge
                        couleur = 'red';
                        break;
                    default :
                        couleur = couleur;
                }
                
                switch (tabInfos[i+2]) {
                    case 'NORMAL':
                        mode = 'N';
                        break;
                    case ('GPS' || 'DMD_GPS'):
                        mode = 'G';
                        break;
                    case 'INIT':
                        mode = 'I';
                        break;
                    case 'DEGRADE':
                        mode = 'D';
                        break;
                    case 'DORMIR':
                        mode = 'S';
                        break;
                }
                              
                var marker = new google.maps.Marker({
                    icon: {
                    path: google.maps.SymbolPath.CIRCLE,
                    scale: 10,
                    fillOpacity: 1,
                    fillColor: couleur,
                    strokeColor: couleur
                    },
                    position: new google.maps.LatLng(tabInfos[i+4], tabInfos[i+5]),
                    map: map,
                    title: tabInfos[i+3],
                    label: {text: mode, color: "white", fontWeight: "bold", fontSize: "18px"}
                });
                
                markers.push(marker);
            }
            // pour lire un marker
            //markers[0].getTitle();
            // markers[0].setPosition(new google.maps.LatLng(43.612189, 1.336579));
        }
    });
}

function centrerVehicule() {
    var immatriculation = $(this).attr("id");

    $.ajax({
        url: 'alfoxControl.jsp?action=r_getVehiculesPositions',
        type: 'POST',
        data: {immatriculation: immatriculation},
        dataType: 'html',
        success: function (data) {
            var tabInfos = data.split("||");
            newLat = parseFloat(tabInfos[1]);
            newLng = parseFloat(tabInfos[2]);
            map.setCenter({
		lat : newLat,
		lng : newLng
            });
            $("#panelVehicules").panel("close");
        }
    });
}

function centrerZone() {
    //alert($(this).attr("id"));
    var nomZone = $(this).attr("id");

    $.ajax({
        url: 'alfoxControl.jsp?action=r_getCenterByZoneName',
        type: 'POST',
        data: {zoneName: nomZone},
        dataType: 'html',
        success: function (data) {
            var tabInfos = data.split("||");
            newLat = parseFloat(tabInfos[1]);
            newLng = parseFloat(tabInfos[2]);
            map.setCenter({
		lat : newLat,
		lng : newLng
            });
            $("#panelZones").panel("close");
        }
    });
}
    