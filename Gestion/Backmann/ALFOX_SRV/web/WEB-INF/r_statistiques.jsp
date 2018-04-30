<!DOCTYPE html>
<html>
    <head>
        <title>Statistiques</title> 
        <%@ include file="/includes/header.jspf" %>
    </head>
    <body>
        <div data-role="page" id="page1">
            <div class="header" data-role="header" data-id="main-header" data-tap-toggle="false" 
                 data-theme="a" data-position="fixed" data-fullscreen="true">
                <h1><img id="logoHeader" src="../images/alcisLogo.png"/>Statistiques</h1>
            </div>
            
            <div role="main" class="ui-content">
                <center>
                    <br/><br/><br/>
                    <br/><br/><br/>
                    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                    <script type="text/javascript">
                      google.charts.load('current', {packages: ['corechart']});
                      google.charts.setOnLoadCallback(drawChart1);
                      google.charts.setOnLoadCallback(drawChart2);
                      google.charts.setOnLoadCallback(drawChart3);
                      google.charts.setOnLoadCallback(drawChart4);

                      function drawChart1() {
                        var data = new google.visualization.DataTable();
                        data.addColumn('string', 'Element');
                        data.addColumn('number', 'Km');
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
                        data.addColumn('number', 'Conso');
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
                        data.addColumn('number', 'VitM');
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
                      function drawChart4() {
                        var data = new google.visualization.DataTable();
                        data.addColumn('string', 'Element');
                        data.addColumn('number', 'VitMoy');
                        data.addRows([
                          ['01', 5.6],
                          ['02', 5.9],
                          ['03', 6.7],
                          ['04', 6.2],
                          ['05', 5.9],
                          ['06', 6.1]
                        ]);
                        // Instantiate and draw the chart.
                        var chart = new google.visualization.ColumnChart(document.getElementById('stat4'));
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
                    <form class="form" >
                        <div class="ui-field-contain">
                            <label class="label" for="select-native-1">Véhicule :</label>
                            <select name="select-native-1" id="select-native-1">
                                <option value="1">DF-412-EZ</option>
                                <option value="2">EF-324-ES</option>
                                <option value="3">JF-311-DE</option>
                                <option value="4">FE-899-EX</option>
                            </select>
                        </div>
                    </form>
                    <br/><br/><br/>
                    <div class="grid">
                        <div class="card">
                            <div class="graphTitre">Kilométrage</div>
                            <div id="stat1"></div>
                        </div>
                        <div class="graph">
                            <div class="graphTitre">Consommation Moyenne</div>
                            <div id="stat2"></div>
                        </div>
                        <div class="graph">
                            <div class="graphTitre">Vitesse Maximale</div>
                            <div id="stat3"></div>
                        </div>
                        <div class="graph">
                            <div class="graphTitre">Vitesse Moyenne</div>
                            <div id="stat4"></div>
                        </div>
                    </div>
                </center>
            </div>
                <%@include file="/includes/footer.jspf"%>
            </div>
        </div>
    </body>
</html>
