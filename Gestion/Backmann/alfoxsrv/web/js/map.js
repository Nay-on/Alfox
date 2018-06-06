
var map;

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
    $.ajax({
        url: 'alfoxControl.jsp?action=r_getVehiculesPositions',
        type: 'POST',
        data: {},
        dataType: 'html',
        success: function (data) {
            var tabInfos = data.split("||");
            for (var i = 1; i < tabInfos.length - 1; i = i + 3) {
                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(tabInfos[i+1], tabInfos[i+2]),
                    map: map
                });
            }
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
    