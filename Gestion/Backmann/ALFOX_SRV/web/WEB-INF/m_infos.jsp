<%-- 
    Document   : infos.jsp
    Description  : page d'infos de la maintenance
    Created on : Mars 2018
--%>
<%@page import="persistence.ConnexionMySQL"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Accueil</title> 
        <%@ include file="/includes/header.jspf" %>
    </head>
    <body>
        <div data-role="page" id="page1">
            <div class="header" data-role="header" data-id="main-header" data-tap-toggle="false" 
                 data-theme="a" data-position="fixed" data-fullscreen="true">
                <h1><img id="logoHeader" src="images/alcisLogo.png"/>Infos</h1>
                <p class="mode" >Mode : NORMAL</p>
                <a href="#panelVehicules" 
                   class="ui-btn ui-btn-icon-notext ui-corner-all ui-icon-gear ui-btn-right">
                </a>
            </div>
            <div role="main" class="ui-content">
                <center>
                    <p class="mess">Nombre de message restants : 3/4</p>
                    <form class="form">
                        <div class="ui-field-contain">
                            <script type='javascript'>
                                <label class="label" for="select-native-1">Véhicule :</label>
                                <select name="select-native-1" id="select-native-1">
                                    <option value="1"></option>
                                    <option value="2">EF-324-ES</option>
                                    <option value="3">JF-311-DE</option>
                                    <option value="4">FE-899-EX</option>
                                </select>
                            </script>
                        </div>
                        <input value="2018-03-14" id="date" type="date">
                    </form>
                    <table data-role="table" id="movie-table-custom" data-mode="reflow" class="table-stripe movie-list ui-responsive">
                    <thead>
                        <tr>
                          <th data-priority="1">Num</th>
                          <th data-priority="2">Date</th>
                          <th data-priority="2">Km</th>
                          <th data-priority="3">VitMoy</th>
                          <th data-priority="3">Conso</th>
                          <th data-priority="4">Latitude</th>
                          <th data-priority="4">Longitude</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                              <td>1</td>
                              <td>12/02/2018 16:12</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>2</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>3</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>4</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>5</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>6</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>7</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>8</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>9</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>10</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                    
                        <tr>
                              <td>11</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>12</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                    <tr>
                              <td>123</td>
                              <td>12/02/2018 16:12</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>124</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>125</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>129</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>130</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>131</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>132</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>133</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>134</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>135</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                    
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>134</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>135</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>136</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>137</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>138</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>139</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>140</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>141</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>142</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>143</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                        <tr>
                              <td>144</td>
                              <td>12/02/2018 16:22</td>
                              <td>12534</td>
                              <td>54</td>
                              <td>6,3</td>
                              <td>36,123456</td>
                              <td>01,123456</td>
                        </tr>
                    </tbody>
                    </table>
                </center>
            </div>
            <div id="panelVehicules" data-role="panel" data-position="right"  
                 data-position-fixed="true" data-display="push">
                <p>Changement de mode :</p>
                <div data-role="collapsibleset" data-inset="false">
                    <a href="#positionWindow" class="ui-btn ui-btn-a ui-icon-arrow-r ui-btn-icon-left ui-shadow ui-corner-all" data-rel="popup" data-position-to="window">
                        <h4>DORMIR</h4>
                        <div data-role="popup" id="positionWindow" class="ui-content">
                            <p align="center">Etes-vous sûr de vouloir passer en mode DORMIR ?</p>
                            <button class="ok" style="width: 200px">OK</button>
                            <button class="annuler" style="width: 200px">Annuler</button>
                        </div>
                    </a>
                    <a href="#positionWindow1" class="ui-btn ui-btn-a ui-icon-arrow-r ui-btn-icon-left ui-shadow ui-corner-all" data-rel="popup" data-position-to="window">
                        <h4>GPS</h4>
                        <div data-role="popup" id="positionWindow1" class="ui-content">
                            <p align="center">Etes-vous sûr de vouloir passer en mode GPS ?</p>
                            <button class="ok" style="width: 200px">OK</button>
                            <button class="annuler" style="width: 200px">Annuler</button>
                        </div>
                    </a>
                    <a href="#positionWindow2" class="ui-btn ui-btn-a ui-icon-arrow-r ui-btn-icon-left ui-shadow ui-corner-all" data-rel="popup" data-position-to="window">
                        <h4>Demande GPS</h4>
                        <div data-role="popup" id="positionWindow2" class="ui-content">
                            <p align="center">Etes-vous sûr de vouloir passer en mode Demande GPS ?</p>
                            <button class="ok" style="width: 200px">OK</button>
                            <button class="annuler" style="width: 200px">Annuler</button>
                        </div>
                    </a>
                    <a href="#positionWindow3" class="ui-btn ui-btn-a ui-icon-arrow-r ui-btn-icon-left ui-shadow ui-corner-all" data-rel="popup" data-position-to="window">
                        <h4>RESET</h4>
                        <div data-role="popup" id="positionWindow3" class="ui-content">
                            <p align="center">Etes-vous sûr de vouloir passer en mode RESET ?</p>
                            <button class="ok" style="width: 200px">OK</button>
                            <button class="annuler" style="width: 200px">Annuler</button>
                        </div>
                    </a>
                </div>     
            </div>
            <br/><br/> 
            <%@include file="/includes/footer.jspf" %>
        </div>
    </body>
</html>