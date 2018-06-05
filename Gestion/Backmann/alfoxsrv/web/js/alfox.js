

// --------------------- callback ----------------------- 

$(function() {
    $("#infosSelectImmatriculation").on("change", infosNewSelect);
    //$("#infosSelectImmatriculation").on("change", infosMode);
    $("#dateSelect").on("change", infosNewSelect);
    $("#listeZones").on("click", centrerZone);
});

function infosNewSelect() {
    isi = document.getElementById("infosSelectImmatriculation");
    ds  = document.getElementById("dateSelect");
    md = document.getElementsByClassName("mode");
    // alert(isi[isi.selectedIndex].value + " " + ds.value);
    // $('#infosTR').html(isi[isi.selectedIndex].value + " " + ds.value);

    $.ajax({
        url  : 'alfoxControl.jsp?action=r_infosByImmaAndDate',
        type : 'POST',
        data :  {immatriculation: isi[isi.selectedIndex].value, date: ds.value},
        dataType : 'html',
        success: function(data) {
           $('#infosTR').html(data);
           $('.mode').html(data);

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

/*function infosMode(){
    
    isi = document.getElementById("infosSelectImmatriculation");
    md = document.getElementsByClassName("mode");
    alert($(md.value));
    $.ajax({
        url  : 'alfoxControl.jsp?action=ajax_infosMode',
        type : 'POST',
        data :  {immatriculation: isi[isi.selectedIndex].value, mode: md.value},
        dataType : 'html',
        success: function(data) {
           $('.mode').html(data);
        }
    });
}*/

    