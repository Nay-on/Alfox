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
insert into vehicule (Marque, Modele, Immatriculation, DateMiseEnService, Motorisation, IdSigfox, DateVidange, KmVidange, HorsZone, TauxUtilisation, AProbleme, CompteurReel, DateControleTechnique) values
    ('Audi', 'RS6', 'AA-000-BB', '2018-01-01', 'Essence', 0001, '2018-01-01', 30000, false, 100, false, 21526.15, '2020-01-01'),
    ('Mercedes', 'C63', 'BB-000-CC', '2018-02-02', 'Essence', 0002, '2018-02-02', 50000, true, 5, false, 500.65, '2020-02-02');