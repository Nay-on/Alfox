/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  snir2g1
 * Created: 13 mars 2018
 */

use alfox;
insert into donneesTR (Mode, Datation, Vitesse, Regime, Consommation, VitesseMax, RegimeMax, ConsoMax, 
                NbDefauts, Defaut1, Defaut2, Defaut3, Defaut4, Latitude, Longitude, DistanceParcourue, VehiculeID) values
    ('STANDARD', '2018-03-03 17:42:37', 50, 1800, 62, 136, 3600, 250, 0, 0, 0, 0, 0, 40.5643453, 50.6354734, 7, 1),
    ('DEGRADE', '2018-03-03 17:42:47', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40.5643453, 50.6354734, 0, 1),
    ('STANDARD', '2018-01-03 17:47:38', 50, 1800, 62, 136, 3600, 250, 0, 0, 0, 0, 0, 40.5643453, 50.6354734, 7, 2),
    ('STANDARD', '2018-01-03 17:47:48', 50, 1800, 62, 136, 3600, 250, 0, 0, 0, 0, 0, 40.5643453, 50.6354734, 7, 2);