$(function() {
    $("#infosSelectImmatriculation").on("change", infosNewSelect); //appelle la méthode infosNewSelect() au changement de la liste déroulante
    $("#dateSelect").on("change", infosNewSelect); //appelle la méthode infosNewSelect() au changement de la date
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
        }
    });
}

