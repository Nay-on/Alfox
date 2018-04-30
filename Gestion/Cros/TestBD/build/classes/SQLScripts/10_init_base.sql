drop schema if exists alfox;
create schema alfox;
use alfox;

create table donneesHisto (
    ID int(10) not null auto_increment, 
    Mode varchar(255) not null, 
    Datation datetime not null, 
    Vitesse int(10) not null, 
    Regime int(10) not null, 
    Consommation int(10) not null, 
    VitesseMax int(10) not null, 
    RegimeMax int(10) not null, 
    ConsoMax int(10) not null, 
    NbDefauts int(10) not null, 
    Defaut1 int(10) not null,
    Defaut2 int(10) not null,
    Defaut3 int(10) not null,
    Defaut4 int(10) not null, 
    LatitudeGPS decimal(9,6) not null, 
    LongitudeGPS decimal(9,6) not null, 
    DistanceParcourue bigint(20) not null, 
    VehiculeID int(10) not null, 
    primary key (ID)   
) ;

create table donneesTR (
    ID int(10) not null auto_increment, 
    Mode varchar(255) not null, 
    Datation datetime not null, 
    Vitesse int(10) not null, 
    Regime int(10) not null, 
    Consommation int(10) not null, 
    VitesseMax int(10) not null, 
    RegimeMax int(10) not null, 
    ConsoMax int(10) not null, 
    NbDefauts int(10) not null, 
    Defaut1 int(10) not null,
    Defaut2 int(10) not null,
    Defaut3 int(10) not null,
    Defaut4 int(10) not null,
    Latitude decimal(9,6) not null, 
    Longitude decimal(9,6) not null, 
    DistanceParcourue bigint(20) not null, 
    SeqNumber int(10) not null,
    Snr decimal(5,2) not null,
    Rssi decimal(5,2) not null,
    AvgSnr decimal(5,2) not null,
    Device varchar(255) not null,
    primary key (ID)
) ;

create table vehicule (
    ID int(10) not null auto_increment, 
    Marque varchar(255) not null, 
    Modele varchar(255) not null, 
    Immatriculation varchar(255) not null unique, 
    DateMiseEnService datetime not null, 
    Motorisation varchar(255) not null, 
    IdSigfox varchar(255) unique, 
    DateVidange datetime, 
    KmVidange int(10), 
    HorsZone tinyint(1) not null, 
    TauxUtilisation int(10) not null, 
    AProbleme tinyint(1) not null, 
    CompteurReel decimal(9,3) not null, 
    DateControleTechnique datetime not null, 
    primary key (ID)
) ;

create table zoneLimite (
    ID int(10) not null auto_increment, 
    Nom varchar(255) not null unique, 
    primary key (ID)
) ;

create table position (
    ID int(10) not null auto_increment, 
    ZoneLimiteID int(10) not null, 
    Ordre int(10) not null, 
    Latitude decimal(9,6) not null, 
    Longitude decimal(9,6) not null, 
    primary key (ID)
) ;


create table loueur (
    ID int(10) not null auto_increment, 
    Nom varchar(255) not null, 
    Prenom varchar(255) not null, 
    Telephone varchar(255) unique, 
    Mail varchar(255) not null unique, 
    primary key (ID)
) ;

create table contrat (
    ID int(10) not null auto_increment, 
    LoueurID int(10) not null, 
    VehiculeID int(10) not null, 
    ZoneLimiteID int(10), 
    Numero varchar(255) not null unique, 
    DateCreation datetime not null, 
    Modele varchar(255), 
    Infos varchar(255), 
    primary key (ID)
) ;

create table `user` (
    ID int(10) not null auto_increment, 
    Role varchar(255) not null unique, 
    Mdp varchar(255) not null, 
    Mail varchar(255) not null unique, 
    primary key (ID)
) ;

alter table donneesHisto 
    add index FKDonneesHis493650 (VehiculeID), 
    add constraint FKDonneesHis493650 foreign key (VehiculeID) references vehicule (ID);

alter table donneesTR 
    add index FKDonneesTR404677 (VehiculeID), 
    add constraint FKDonneesTR404677 foreign key (VehiculeID) references vehicule (ID);

alter table contrat 
    add index FKContrat5291 (ZoneLimiteID), 
    add constraint FKContrat5291 foreign key (ZoneLimiteID) references zoneLimite (ID);

alter table position 
    add index FKPosition217606 (ZoneLimiteID), 
    add constraint FKPosition217606 foreign key (ZoneLimiteID) references zoneLimite (ID);

alter table contrat 
    add index FKContrat433214 (VehiculeID), 
    add constraint FKContrat433214 foreign key (VehiculeID) references vehicule (ID);

alter table contrat 
    add index FKContrat81038 (LoueurID), 
    add constraint FKContrat81038 foreign key (LoueurID) references loueur (ID);

alter table loueur
    add constraint nomprenom unique (Nom, Prenom);




use alfox;
insert into `user` (`Role`, `Mdp`, `Mail`) values 
    ('responsable','636D61CF9094A62A81836F3737D9C0DA','responsable@gmail.com'),
    ('maintenance','57CB773AE7A82C8C8AAE12FA8F8D7ABD','maintenance@gmail.com');



use alfox;
insert into vehicule (Marque, Modele, Immatriculation, DateMiseEnService, Motorisation, IdSigfox, DateVidange, KmVidange, HorsZone, TauxUtilisation, AProbleme, CompteurReel, DateControleTechnique) values
    ('Mercedes', 'Vito', 'ED-592-CY', '2018/01/01', 'Diesel', 1, '2018/01/01', 40787, false, 100, false, 40787, '2020/01/01'),
    ('Mercedes', 'Vito', 'ED-593-VS', '2018/01/01', 'Diesel', 2, '2018/01/01', 76618, true, 5, false, 76618, '2020/01/01'),
    ('Mercedes', 'Vito', 'EE-239-QM', '2018/01/01', 'Diesel', 3, '2018/01/01', 112201, false, 100, false, 112201, '2020/01/01'),
    ('Mercedes', 'Vito', 'EE-300-QM', '2018/01/01', 'Diesel', 4, '2018/01/01', 82700, true, 5, false, 82700, '2020/01/01'),
    ('Renault', 'Talisman', 'EK-462-GX', '2018/01/01', 'Diesel', 5, '2018/01/01', 66320, false, 100, false, 66320, '2020/01/01'),
    ('Renault', 'Talisman', 'EM-045-BC', '2018/01/01', 'Diesel', 6, '2018/01/01', 67791, true, 5, false, 67791, '2020/01/01'),
    ('Renault', 'Talisman', 'EM-150-BE', '2018/01/01', 'Diesel', 7, '2018/01/01', 32778, false, 100, false, 32778, '2020/01/01'),
    ('Renault', 'Talisman', 'EM-862-ML', '2018/01/01', 'Diesel', 8, '2018/01/01', 51826, true, 5, false, 51826, '2020/01/01');




use alfox;
insert into loueur (Nom, Prenom, Telephone, Mail) values
    ('Magritte', 'René', '0607080910', 'yvesmagritte@gmail.com'),
    ('Magritte', 'Bernard', '0607080911', 'bernardmagritte@gmail.com'),
    ('Magritte', 'Bernard1', '0607080912', 'bernardmagritte2@gmail.com'),
    ('Bon', 'Jean', '0708091011', 'jeanbon@gmail.com');




use alfox;
insert into zoneLimite (Nom) values
    ('Toulouse'),
    ('Alcis');




use alfox;
insert into position (ordre, ZoneLimiteID, latitude, longitude) values
    (1, 1, 43.546231, 1.350065),
    (2, 1, 43.612015, 1.367059),
    (3, 1, 43.659971, 1.396928),
    (4, 1, 43.668415, 1.469369),
    (5, 1, 43.620218, 1.511941),
    (6, 1, 43.565016, 1.518121),
    (7, 1, 43.536650, 1.468683),
    (8, 1, 43.532667, 1.403108),
    (1, 2, 43.604014, 1.526581),
    (2, 2, 43.601590, 1.524203),
    (3, 2, 43.601920, 1.530292),
    (4, 2, 43.600355, 1.528461);




use alfox;
insert into contrat (Numero, DateCreation, Modele, Infos, LoueurID, VehiculeID, ZoneLimiteID) values
    ('C1', '2017/01/01', 'annuel', '', 1, 1, 1),
    ('C2', '2018/02/02', 'annuel', '', 2, 2, 1);



use alfox;
insert into donneesHisto (Mode, Datation, Vitesse, Regime, Consommation, VitesseMax, RegimeMax, ConsoMax, 
                NbDefauts, Defaut1, Defaut2, Defaut3, Defaut4, LatitudeGPS, LongitudeGPS, DistanceParcourue, VehiculeID) values
    ('STANDARD', '2018/01/03 17:42:37', 50, 1800, 62, 136, 3600, 250, 0, 0, 0, 0, 0, 40.564353, 50.654734, 7, 1),
    ('DEGRADE', '2018/01/03 17:42:47', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40.564453, 50.635434, 0, 1),
    ('STANDARD', '2018/01/03 17:47:38', 50, 1800, 62, 136, 3600, 250, 0, 0, 0, 0, 0, 40.564453, 50.654734, 7, 2),
    ('STANDARD', '2018/01/03 17:47:48', 50, 1800, 62, 136, 3600, 250, 0, 0, 0, 0, 0, 40.564453, 50.654734, 7, 2);




use alfox;
insert into donneesTR(Mode, Datation, Vitesse, Regime, Consommation, VitesseMax, RegimeMax, ConsoMax, 
NbDefauts, Defaut1, Defaut2, Defaut3, Defaut4, Latitude, Longitude, DistanceParcourue, VehiculeID) values
('STANDARD', '2018/03/20 00:00:00', 35, 1479, 6, 114, 3205, 30, 3, 75, 5, 0, 0, 57.761382, 11.700803, 20, 1), 
('STANDARD', '2018/03/20 00:10:00', 12, 2275, 8, 96, 3938, 22, 1, 153, 0, 0, 0, 19.368961, 33.811088, 16, 2), 
('STANDARD', '2018/03/20 00:20:00', 36, 3236, 9, 109, 3380, 19, 3, 67, 143, 35, 0, 40.283732, 59.196649, 15, 2), 
('STANDARD', '2018/03/20 00:30:00', 77, 2588, 10, 85, 3690, 25, 3, 101, 77, 200, 0, 39.836279, 48.398641, 13, 2), 
('STANDARD', '2018/03/20 00:40:00', 28, 1233, 7, 2, 3309, 16, 1, 53, 0, 0, 0, 39.495084, 25.329905, 1, 2), 
('STANDARD', '2018/03/20 00:50:00', 110, 3235, 9, 78, 3281, 32, 4, 158, 147, 185, 136, 42.870164, 17.555737, 9, 2), 
('STANDARD', '2018/03/20 01:00:00', 119, 3406, 9, 113, 3382, 32, 0, 0, 0, 0, 0, 51.210434, 34.768429, 2, 1), 
('STANDARD', '2018/03/20 01:10:00', 26, 1589, 10, 62, 3495, 22, 4, 71, 29, 26, 199, 13.930618, 4.677368, 18, 1), 
('STANDARD', '2018/03/20 01:20:00', 93, 3351, 8, 127, 3275, 24, 0, 0, 0, 0, 0, 39.978997, 11.612148, 1, 2), 
('STANDARD', '2018/03/20 01:30:00', 101, 3016, 6, 87, 3637, 26, 0, 0, 0, 0, 0, 7.121746, 27.077451, 10, 1), 
('STANDARD', '2018/03/20 01:40:00', 92, 2527, 4, 7, 3064, 15, 0, 0, 0, 0, 0, 11.460107, 44.546092, 14, 2), 
('STANDARD', '2018/03/20 01:50:00', 94, 1118, 5, 54, 3828, 17, 2, 41, 107, 0, 0, 1.390853, 42.069018, 21, 1), 
('STANDARD', '2018/03/20 02:00:00', 25, 3106, 9, 31, 3318, 26, 3, 12, 78, 99, 0, 55.440029, 43.474757, 17, 1), 
('STANDARD', '2018/03/20 02:10:00', 61, 2925, 7, 65, 3317, 19, 1, 108, 0, 0, 0, 21.080105, 45.811321, 17, 2), 
('STANDARD', '2018/03/20 02:20:00', 30, 1878, 5, 88, 3280, 28, 4, 5, 50, 149, 103, 44.088613, 15.702187, 18, 2), 
('STANDARD', '2018/03/20 02:30:00', 50, 1859, 6, 112, 3840, 22, 4, 168, 77, 174, 38, 33.279531, 47.687653, 19, 1), 
('STANDARD', '2018/03/20 02:40:00', 1, 2305, 9, 68, 3300, 34, 1, 29, 0, 0, 0, 29.673571, 19.302319, 21, 1), 
('STANDARD', '2018/03/20 02:50:00', 20, 2131, 6, 71, 3266, 17, 3, 63, 27, 91, 0, 43.587098, 48.081508, 18, 1), 
('STANDARD', '2018/03/20 03:00:00', 51, 3160, 9, 44, 3525, 15, 2, 129, 56, 0, 0, 56.972042, 10.149447, 18, 2), 
('STANDARD', '2018/03/20 03:10:00', 60, 1181, 4, 109, 3568, 15, 2, 102, 175, 0, 0, 14.107648, 34.298124, 7, 1), 
('STANDARD', '2018/03/20 03:20:00', 51, 2125, 9, 34, 3893, 29, 3, 108, 100, 21, 0, 8.847662, 44.420526, 14, 2), 
('STANDARD', '2018/03/20 03:30:00', 127, 1785, 4, 8, 3495, 18, 1, 27, 0, 0, 0, 52.409395, 20.524854, 8, 2), 
('STANDARD', '2018/03/20 03:40:00', 86, 2519, 8, 16, 3910, 17, 3, 150, 107, 190, 0, 17.575551, 2.645822, 2, 2), 
('STANDARD', '2018/03/20 03:50:00', 23, 3213, 4, 35, 3228, 15, 1, 70, 0, 0, 0, 7.500776, 24.398652, 8, 2), 
('STANDARD', '2018/03/20 04:00:00', 104, 2357, 7, 57, 3297, 24, 0, 0, 0, 0, 0, 30.525105, 46.870545, 5, 2), 
('STANDARD', '2018/03/20 04:10:00', 66, 2929, 5, 69, 3837, 34, 4, 165, 43, 117, 147, 58.432558, 34.576981, 10, 2), 
('STANDARD', '2018/03/20 04:20:00', 92, 1547, 9, 98, 3991, 25, 3, 28, 1, 121, 0, 18.452672, 2.208411, 6, 2), 
('STANDARD', '2018/03/20 04:30:00', 63, 2030, 4, 110, 3734, 21, 2, 93, 123, 0, 0, 16.424461, 9.632325, 21, 2), 
('STANDARD', '2018/03/20 04:40:00', 120, 2419, 9, 72, 3234, 21, 3, 25, 11, 61, 0, 48.824806, 54.086084, 7, 1), 
('STANDARD', '2018/03/20 04:50:00', 52, 2116, 5, 73, 3146, 15, 4, 89, 46, 123, 108, 42.978276, 9.664544, 8, 1), 
('STANDARD', '2018/03/20 05:00:00', 128, 2551, 10, 129, 3919, 25, 0, 0, 0, 0, 0, 19.936005, 12.660527, 2, 1), 
('STANDARD', '2018/03/20 05:10:00', 34, 2393, 10, 78, 3860, 16, 1, 189, 0, 0, 0, 55.794979, 29.176736, 19, 2), 
('STANDARD', '2018/03/20 05:20:00', 88, 2867, 4, 12, 3141, 31, 3, 191, 97, 78, 0, 15.536413, 22.069037, 20, 1), 
('STANDARD', '2018/03/20 05:30:00', 0, 2188, 6, 100, 3092, 33, 2, 6, 139, 0, 0, 52.050988, 28.412365, 11, 2), 
('STANDARD', '2018/03/20 05:40:00', 19, 1282, 8, 74, 3062, 32, 1, 91, 0, 0, 0, 32.546705, 18.194164, 4, 2), 
('STANDARD', '2018/03/20 05:50:00', 46, 2167, 6, 6, 3955, 23, 3, 114, 89, 196, 0, 32.707533, 17.102933, 8, 1);