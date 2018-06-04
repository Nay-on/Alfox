

// --------------------- callback ----------------------- 
$(function() {
    $("#infosSelectImmatriculation").on("change", infosNewSelect);
    $("#dateSelect").on("change", infosNewSelect);
    $("#listeZones").on("click", centrerZone);
});

function infosNewSelect() {
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
           $('#infosTR').html(data);
        },
        error : function(resultat, statut, erreur) {
            $('.progressBar').hide();
            $('#popupTextSendPseudo').text("Impossible de vous envoyer votre pseudo !");
            $('#popupSendPseudo').popup( "option", "dismissible", true );
            return false;
        }
    });
}

function centrerZone() {
    alert($(this).attr("id"));
}