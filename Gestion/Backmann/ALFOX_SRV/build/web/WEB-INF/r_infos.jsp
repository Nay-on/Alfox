<%-- 
    Document   : infos.jsp
    Description  : page accueil du responsable
    Created on : Mars 2018
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Acceuil</title> 
        <%@ include file="/includes/header.jspf" %>
    </head>
    <body>
        <div data-role="page" id="page1">
            <div class="header" data-role="header" data-id="main-header" data-tap-toggle="false" 
                 data-position="fixed">
                <h1><img id="logoHeader" src="images/alcisLogo.png"/>Infos</h1>
            </div>
            
            <div role="main" class="ui-content">
                <center>
                    <br/><br/><br/><br/><br/><br/>
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
                    </tbody>
                    </table>
                </center>
            </div>        
            <%@include file="/includes/footer.jspf" %>
        </div>
    </body>
</html>