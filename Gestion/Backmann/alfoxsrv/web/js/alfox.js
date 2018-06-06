

// --------------------- callback ----------------------- 

$(function() {
    $("#infosSelectImmatriculation").on("change", infosNewImmaSelect);
    $("#dateSelect").on("change", infosNewDateSelect);
    $("#listeZones").on("click", centrerZone);
});

function infosNewImmaSelect() {
    isi = document.getElementById("infosSelectImmatriculation");
    
    $.ajax({
        url  : 'alfoxControl.jsp?action=r_infosLastDateByImmatriculation',
        type : 'POST',
        data :  {immatriculation: isi[isi.selectedIndex].value},
        dataType : 'html',
        success: function(data) {
            var tabInfos = data.split("||");
            $('#dateSelect').val(tabInfos[1]);
            infosNewDateSelect();
        }
    });
}

function infosNewDateSelect() {
    isi = document.getElementById("infosSelectImmatriculation");
    ds  = document.getElementById("dateSelect");
    // alert(isi[isi.selectedIndex].value + " " + ds.value);
    // $('#infosTR').html(isi[isi.selectedIndex].value + " " + ds.value);

    $.ajax({
        url  : 'alfoxControl.jsp?action=r_infosByImmaAndDate',
        type : 'POST',
        data :  {immatriculation: isi[isi.selectedIndex].value, date: ds.value},
        dataType : 'html',
        success: function(data) {
            var tabInfos = data.split("||");
            $('#infosTR').html(tabInfos[0]);
            $('.mode').html("Mode : " + tabInfos[1]);
        }
        /*error : function(resultat, statut, erreur) {
            $('.progressBar').hide();
            $('#popupTextSendPseudo').text("Impossible de vous envoyer votre pseudo !");
            $('#popupSendPseudo').popup( "option", "dismissible", true );
            return false;
        }*/
    });
}

function centrerZone(map, lat, lg) {
    //alert($(this).attr("id"));
    $('#map').setCenter(lat, lg);
}
    