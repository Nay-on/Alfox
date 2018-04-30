<%-- 
    Document   : statistiques.jsp
    Description  : page statistique pour le responsable
    Created on : Mars 2018
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Statistiques</title> 
        <%@ include file="/includes/header.jspf" %>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
          google.charts.load('current', {packages: ['corechart']});
          google.charts.setOnLoadCallback(drawChart1);
          google.charts.setOnLoadCallback(drawChart2);
          google.charts.setOnLoadCallback(drawChart3);

          function drawChart1() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Element');
            data.addColumn('number', 'VitMoy');
            data.addRows([
              ['01', 66],
              ['02', 72],
              ['03', 35],
              ['04', 48],
              ['05', 54],
              ['06', 48]
            ]);
            // Instantiate and draw the chart.
            var chart = new google.visualization.ColumnChart(document.getElementById('stat1'));
            var options = {
                width: 300,
                height: 300,
                legend: 'none',
                colors: ['orange'],
                backgroundColor: '#EEEEEE'
            };
            chart.draw(data, options);
          }
          function drawChart2() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Element');
            data.addColumn('number', 'Km');
            data.addRows([
              ['01', 1200],
              ['02', 1300],
              ['03', 700],
              ['04', 950],
              ['05', 2000],
              ['06', 1335]
            ]);
            // Instantiate and draw the chart.
            var chart = new google.visualization.ColumnChart(document.getElementById('stat2'));
            var options = {
                width: 300,
                height: 300,
                legend: 'none',
                colors: ['orange'],
                backgroundColor: '#EEEEEE'
            };
            chart.draw(data, options);
          }
          function drawChart3() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Element');
            data.addColumn('number', 'Conso');
            data.addRows([
              ['01', 5.6],
              ['02', 5.9],
              ['03', 6.7],
              ['04', 6.2],
              ['05', 5.9],
              ['06', 6.1]
            ]);
            // Instantiate and draw the chart.
            var chart = new google.visualization.ColumnChart(document.getElementById('stat3'));
            var options = {
                width: 300,
                height: 300,
                legend: 'none',
                colors: ['orange'],
                backgroundColor: '#EEEEEE'
            };
            chart.draw(data, options);
          }
        </script>
    </head>
    <body>
        <div data-role="page" id="page1">
            <div class="header" data-role="header" data-id="main-header" data-tap-toggle="false" 
                 data-theme="a" data-position="fixed" data-fullscreen="true">
                <h1><img id="logoHeader" src="images/alcisLogo.png"/>Statistiques</h1>
            </div>
            
            <div role="main" class="ui-content">
                <center>
                    <br/><br/><br/>
                    <br/><br/><br/>
                    <div class="grid">
                        <div class="card">
                            <div class="graphTitre">Vitesse Moyenne</div>
                            <div id="stat1"></div>
                        </div>
                        <div class="graph">
                            <div class="graphTitre">Kilométrage</div>
                            <div id="stat2"></div>
                        </div>
                        <div class="graph">
                            <div class="graphTitre">Consommation Moyenne</div>
                            <div id="stat3"></div>
                        </div>
                    </div>
                </center>
            </div>        
            <%@include file="/includes/footer.jspf" %>
        </div>
    </body>
</html>
