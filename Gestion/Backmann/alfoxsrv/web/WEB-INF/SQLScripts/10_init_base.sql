# -----------------------------------------------------------------------
#           Crée la base de données 'alfox' et l'initialise
#                   avec des données de test
# -----------------------------------------------------------------------

drop schema if exists alfox;
create schema alfox;
use alfox;

create table donneesHisto (
    ID int(10) not null auto_increment, 
    Mode varchar(255), 
    Datation datetime, 
    Vitesse int(10), 
    Regime int(10), 
    Consommation int(10), 
    VitesseMax int(10), 
    RegimeMax int(10), 
    ConsoMax int(10), 
    NbDefauts int(10), 
    Defaut1 int(10),
    Defaut2 int(10),
    Defaut3 int(10),
    Defaut4 int(10), 
    LatitudeGPS decimal(9,6), 
    LongitudeGPS decimal(9,6), 
    DistanceParcourue bigint(20), 
    VehiculeID int(10) not null, 
    primary key (ID)   
);

create table donneesTR (
    ID int(10) not null auto_increment, 
    SeqNumber int(10),
    Mode varchar(255), 
    Datation datetime, 
    Vitesse int(10), 
    Regime int(10),
    Consommation int(10),
    VitesseMax int(10),
    RegimeMax int(10), 
    ConsoMax int(10), 
    NbDefauts int(10),
    Defaut1 int(10),
    Defaut2 int(10),
    Defaut3 int(10),
    Defaut4 int(10),
    Latitude decimal(9,6),
    Longitude decimal(9,6),
    Radius int(10),
    DistanceParcourue bigint(20),
    Snr decimal(5,2),
    Rssi decimal(5,2),
    AvgSnr decimal(5,2), 
    VehiculeID int(10) not null, 
    primary key (ID)
);

create table boitier (
    SigfoxID varchar(255) not null, 
    ModeActuel varchar(255), 
    ModeDemande varchar(255), 
    NbMsgDownlink int(10), 
    CommValide tinyint(1),
    VehiculeID int(10) not null, 
    primary key (VehiculeID)
);

create table vehicule (
    ID int(10) not null auto_increment, 
    Marque varchar(255), 
    Modele varchar(255), 
    Immatriculation varchar(255) not null unique, 
    DateMiseEnService datetime, 
    Motorisation varchar(255),
    DateVidange datetime, 
    KmVidange int(10), 
    HorsZone tinyint(1), 
    TauxUtilisation int(10), 
    AProbleme tinyint(1), 
    CompteurReel decimal(9,3), 
    DateControleTechnique datetime, 
    primary key (ID)
);

create table zoneLimite (
    ID int(10) not null auto_increment, 
    Nom varchar(255) not null unique, 
    primary key (ID)
);

create table position (
    ID int(10) not null auto_increment, 
    ZoneLimiteID int(10) not null, 
    Ordre int(10) not null, 
    Latitude decimal(9,6) not null, 
    Longitude decimal(9,6) not null, 
    primary key (ID)
);

create table loueur (
    ID int(10) not null auto_increment, 
    Nom varchar(255) not null, 
    Prenom varchar(255) not null, 
    Telephone varchar(255)  not null unique, 
    Mail varchar(255)  not null unique, 
    primary key (ID)
);

create table contrat (
    ID int(10) not null auto_increment, 
    LoueurID int(10) not null, 
    VehiculeID int(10) not null,
    ZoneLimiteID int(10), 
    Numero varchar(255) not null unique, 
    DateCreation datetime, 
    Modele varchar(255), 
    Infos varchar(255), 
    primary key (ID)
);

create table user (
    ID int(10) not null auto_increment, 
    Role varchar(255) not null unique, 
    Mdp varchar(255) not null, 
    Mail varchar(255) not null unique, 
    primary key (ID)
);

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

# ----------------------------------------------------------------------------
#                   initialisation avec des données de test
# ----------------------------------------------------------------------------
insert into user (Role, Mdp, Mail) values 
    ('responsable','636D61CF9094A62A81836F3737D9C0DA','responsable@gmail.com'),
    ('maintenance','57CB773AE7A82C8C8AAE12FA8F8D7ABD','maintenance@gmail.com');

insert into boitier (SigfoxID, ModeActuel, ModeDemande, NbMsgDownlink, CommValide, VehiculeID) values
    ('1D2289', 'NORMAL', 'NORMAL', 0, true, 1),
    ('1D188E', 'NORMAL', 'NORMAL', 0, true, 2),
    ('3', 'NORMAL', 'NORMAL', 0, true, 3),
    ('4', 'NORMAL', 'NORMAL', 0, true, 4),
    ('5', 'NORMAL', 'NORMAL', 0, true, 5),
    ('6', 'NORMAL', 'NORMAL', 0, true, 6),
    ('7', 'NORMAL', 'NORMAL', 0, true, 7),
    ('8', 'NORMAL', 'NORMAL', 0, true, 8);

insert into vehicule (Marque, Modele, Immatriculation, DateMiseEnService, Motorisation, DateVidange, KmVidange, HorsZone, TauxUtilisation, AProbleme, CompteurReel, DateControleTechnique) values
    ('Mercedes', 'Vito', 'ED-592-CY', '2018/01/01', 'Diesel', '2018/01/01', 40787, false, 100, false, 40787, '2020/01/01'),
    ('Mercedes', 'Vito', 'ED-593-VS', '2018/01/01', 'Diesel', '2018/01/01', 76618, true, 5, false, 76618, '2020/01/01'),
    ('Mercedes', 'Vito', 'EE-239-QM', '2018/01/01', 'Diesel', '2018/01/01', 112201, false, 100, false, 112201, '2020/01/01'),
    ('Mercedes', 'Vito', 'EE-300-QM', '2018/01/01', 'Diesel', '2018/01/01', 82700, true, 5, false, 82700, '2020/01/01'),
    ('Renault', 'Talisman', 'EK-462-GX', '2018/01/01', 'Diesel', '2018/01/01', 66320, false, 100, false, 66320, '2020/01/01'),
    ('Renault', 'Talisman', 'EM-045-BC', '2018/01/01', 'Diesel', '2018/01/01', 67791, true, 5, false, 67791, '2020/01/01'),
    ('Renault', 'Talisman', 'EM-150-BE', '2018/01/01', 'Diesel', '2018/01/01', 32778, false, 100, false, 32778, '2020/01/01'),
    ('Renault', 'Talisman', 'EM-862-ML', '2018/01/01', 'Diesel', '2018/01/01', 51826, true, 5, false, 51826, '2020/01/01');

insert into loueur (Nom, Prenom, Telephone, Mail) values
    ('Magritte', 'René', '0607080910', 'yvesmagritte@gmail.com'),
    ('Magritte', 'Bernard', '0607080911', 'bernardmagritte@gmail.com'),
    ('Magritte', 'Bernard1', '0607080912', 'bernardmagritte2@gmail.com'),
    ('Bon', 'Jean', '0708091011', 'jeanbon@gmail.com');

insert into zoneLimite (Nom) values
    ('Alcis'),
    ('Toulouse');

insert into position (ordre, ZoneLimiteID, latitude, longitude) values
    (1, 1, 43.604014, 1.526581),
    (2, 1, 43.601590, 1.524203),
    (3, 1, 43.601920, 1.530292),
    (4, 1, 43.600355, 1.528461),
    (1, 2, 43.546231, 1.350065),
    (2, 2, 43.612015, 1.367059),
    (3, 2, 43.659971, 1.396928),
    (4, 2, 43.668415, 1.469369),
    (5, 2, 43.620218, 1.511941),
    (6, 2, 43.565016, 1.518121),
    (7, 2, 43.536650, 1.468683),
    (8, 2, 43.532667, 1.403108);

insert into contrat (Numero, DateCreation, Modele, Infos, LoueurID, VehiculeID, ZoneLimiteID) values
    ('C1', '2017/01/01', 'annuel', '', 1, 1, 2),
    ('C2', '2018/02/02', 'annuel', '', 2, 2, 2),
    ('C3', '2018/02/02', 'annuel', '', 2, 3, 2),
    ('C4', '2018/02/02', 'annuel', '', 2, 4, 2),
    ('C5', '2018/02/02', 'annuel', '', 2, 5, 2),
    ('C6', '2018/02/02', 'annuel', '', 2, 6, 2),
    ('C7', '2018/02/02', 'annuel', '', 2, 7, 2),
    ('C8', '2018/02/02', 'annuel', '', 2, 8, 2);

insert into donneesHisto (Mode, Datation, Vitesse, Regime, Consommation, VitesseMax, RegimeMax, ConsoMax, 
                NbDefauts, Defaut1, Defaut2, Defaut3, Defaut4, LatitudeGPS, LongitudeGPS, DistanceParcourue, VehiculeID) values
    ('NORMAL', '2018/01/03 17:42:37', 50, 34, 62, 136, 36, 67, 0, 0, 0, 0, 0, 40.564353, 50.654734, 7, 1),
    ('NORMAL', '2018/01/03 17:42:47', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40.564453, 50.635434, 0, 1),
    ('NORMAL', '2018/01/03 17:47:38', 62, 32, 68, 136, 36, 68, 0, 0, 0, 0, 0, 40.564453, 50.654734, 7, 2),
    ('NORMAL', '2018/01/03 17:47:48', 112, 45, 75, 136, 36, 72, 0, 0, 0, 0, 0, 40.564453, 50.654734, 7, 2);

insert into donneesTR (Mode, Datation, Vitesse, Regime, Consommation, VitesseMax, RegimeMax, ConsoMax, 
                NbDefauts, Defaut1, Defaut2, Defaut3, Defaut4, Latitude, Longitude, DistanceParcourue, SeqNumber, Snr, Rssi, AvgSnr, VehiculeID) values
    ('NORMAL', '2018/03/20 00:00:00', 35,  2400, 60, 114, 32, 30, 3,  75,   5,   0,   0, 57.761382, 11.700803, 20, 1, 123.45, 123.56, 123.78, 1),
    ('NORMAL', '2018/03/20 00:10:00', 12,  2200, 68,  96, 39, 22, 1, 153,   0,   0,   0, 19.368961, 33.811088, 16, 2, 123.45, 123.56, 123.78, 1),
    ('NORMAL', '2018/03/20 00:20:00', 36,  3200, 69, 109, 33, 19, 3,  67, 143,  35,   0, 40.283732, 59.196649, 15, 3, 123.45, 123.56, 123.78, 1), 
    ('NORMAL', '2018/03/20 00:30:00', 77,  4300, 10,  85, 36, 25, 3, 101,  77, 200,   0, 39.836279, 48.398641, 13, 4, 123.45, 123.56, 123.78, 1),
    ('NORMAL', '2018/03/20 00:40:00', 28,  3300, 70,   2, 33, 16, 1,  53,   0,   0,   0, 39.495084, 25.329905, 1, 5, 123.45, 123.56, 123.78, 1), 
    ('NORMAL', '2018/03/20 00:50:00', 110, 4500, 69,  78, 32, 32, 4, 158, 147, 185, 136, 42.870164, 17.555737, 9, 6, 123.45, 123.56, 123.78, 1),
    ('NORMAL', '2018/03/20 01:00:00', 119, 4700, 89, 113, 33, 32, 0,   0,   0,   0,   0, 51.210434, 34.768429, 2, 7, 123.45, 123.56, 123.78, 1), 
    ('NORMAL', '2018/03/20 01:10:00', 26,  2100, 65,  62, 34, 22, 4,  71,  29,  26, 199, 43.593468,  1.414471, 18, 8, 123.45, 123.56, 123.78, 1), 
    ('NORMAL', '2018/03/20 01:00:00', 119, 4100, 69, 113, 33, 32, 0,   0,   0,   0,   0, 51.210434, 34.768429, 2, 37, 123.45, 123.56, 123.78, 2), 
    ('NORMAL', '2018/03/20 01:10:00', 26,  2400, 60,  62, 34, 22, 4,  71,  29,  26, 199, 13.930618,  4.677368, 18, 38, 123.45, 123.56, 123.78, 2), 
    ('NORMAL', '2018/03/20 01:20:00', 93,  3600, 80, 127, 32, 24, 0,   0,   0,   0,   0, 39.978997, 11.612148,  1, 9, 123.45, 123.56, 123.78, 2), 
    ('NORMAL', '2018/03/20 01:30:00', 101, 3900, 86,  87, 36, 26, 0,   0,   0,   0,   0, 7.121746,  27.077451,  10, 10, 123.45, 123.56, 123.78, 2), 
    ('NORMAL', '2018/03/20 01:40:00', 92,  3600, 74,   7, 30, 15, 0,   0,   0,   0,   0, 43.597975, 1.469489, 14, 11, 123.45, 123.56, 123.78, 2),
    ('NORMAL', '2018/03/20 01:50:00', 94,  3600, 65,  54, 38, 17, 2,  41, 107,   0,   0, 1.390853,  42.069018, 21, 12, 123.45, 123.56, 123.78, 3), 
    ('NORMAL', '2018/03/20 02:00:00', 25,  2100, 69,  31, 33, 26, 3,  12,  78,  99,   0, 55.440029, 43.474757, 17, 13, 123.45, 123.56, 123.78, 3), 
    ('NORMAL', '2018/03/20 02:10:00', 61,  30, 72,  65, 33, 19, 1, 108,   0,   0,   0, 21.080105, 45.811321, 17, 14, 123.45, 123.56, 123.78, 3), 
    ('NORMAL', '2018/03/20 02:20:00', 30,  23, 65,  88, 32, 28, 4,   5,   0, 149, 103, 43.604000, 1.512187, 18, 15, 123.45, 123.56, 123.78, 3),
    ('NORMAL', '2018/03/20 02:30:00', 50,  28, 66, 112, 38, 22, 4, 168,  77, 174,  38, 43.601547, 1.527085, 19, 16, 123.45, 123.56, 123.78, 4), 
    ('NORMAL', '2018/03/20 02:40:00', 1,   19, 69,  68, 33, 34, 1,  29,   0,   0,   0, 43.601547, 1.527085, 21, 17, 123.45, 123.56, 123.78, 4), 
    ('NORMAL', '2018/03/20 02:50:00', 20,  21, 66,  71, 32, 17, 3,  63,  27,  91,   0, 43.601547, 1.527085, 18, 18, 123.45, 123.56, 123.78, 4), 
    ('NORMAL', '2018/03/20 03:00:00', 51,  24, 69,  44, 35, 15, 2, 129,  56,   0,   0, 43.601547, 1.527085, 18, 19, 123.45, 123.56, 123.78, 4),
    ('NORMAL', '2018/03/20 03:10:00', 50,  23, 66, 112, 38, 22, 4, 168,  77, 174,  38, 43.601547, 1.527085, 19, 39, 123.45, 123.56, 123.78, 4), 
    ('NORMAL', '2018/03/20 03:20:00', 1,   21, 69,  68, 33, 34, 1,  29,   0,   0,   0, 43.601547, 1.527085, 21, 40, 123.45, 123.56, 123.78, 4), 
    ('NORMAL', '2018/03/20 03:30:00', 20,  23, 66,  71, 32, 17, 3,  63,  27,  91,   0, 43.601547, 1.527085, 18, 41, 123.45, 123.56, 123.78, 4), 
    ('NORMAL', '2018/03/20 03:40:00', 51,  26, 69,  44, 35, 15, 2, 129,  56,   0,   0, 43.601547, 1.527085, 18, 42, 123.45, 123.56, 123.78, 4),
    ('NORMAL', '2018/03/20 03:10:00', 60,  28, 64, 109, 35, 15, 2, 102, 175,   0,   0, 14.107648, 34.298124, 7, 20, 123.45, 123.56, 123.78, 5), 
    ('NORMAL', '2018/03/20 03:20:00', 51,  24, 69,  34, 38, 29, 3, 108, 100,  21,   0, 8.847662,  44.420526, 14, 21, 123.45, 123.56, 123.78, 5), 
    ('NORMAL', '2018/03/20 03:30:00', 127, 40, 74,   8, 34, 18, 1,  27,  0,    0,   0, 52.409395, 20.524854, 8, 22, 123.45, 123.56, 123.78, 5), 
    ('NORMAL', '2018/03/20 03:40:00', 86,  36, 84,  16, 39, 17, 3, 150, 107, 190,   0, 17.575551,  2.645822, 2, 23, 123.45, 123.56, 123.78, 5),
    ('NORMAL', '2018/03/20 03:50:00', 23,  21, 64,  35, 28, 15, 1,  70,   0,   0,   0, 7.500776,  24.398652, 8, 24, 123.45, 123.56, 123.78, 6), 
    ('NORMAL', '2018/03/20 04:00:00', 104, 38, 78,  57, 32, 24, 0,   0,   0,   0,   0, 30.525105, 46.870545, 5, 25, 123.45, 123.56, 123.78, 6), 
    ('NORMAL', '2018/03/20 04:10:00', 66,  32, 75,  69, 38, 34, 4, 165,  43, 117, 147, 58.432558, 34.576981, 10, 26, 123.45, 123.56, 123.78, 6), 
    ('NORMAL', '2018/03/20 04:20:00', 92,  37, 69,  98, 39, 25, 3,  28,   1, 121,   0, 18.452672,  2.208411, 6, 27, 123.45, 123.56, 123.78, 6),
    ('NORMAL', '2018/03/20 04:30:00', 63,  33, 64, 110, 37, 21, 2,  93, 123,   0,   0, 16.424461,  9.632325, 21, 28, 123.45, 123.56, 123.78, 7), 
    ('NORMAL', '2018/03/20 04:40:00', 120, 42, 89,  72, 32, 21, 3,  25,  11,  61,   0, 48.824806, 54.086084, 7, 29, 123.45, 123.56, 123.78, 7), 
    ('NORMAL', '2018/03/20 04:50:00', 52,  31, 65,  73, 31, 15, 4,  89,  6,  123, 108, 42.978276,  9.664544, 8, 30, 123.45, 123.56, 123.78, 7), 
    ('NORMAL', '2018/03/20 05:00:00', 128, 41, 10, 129, 39, 25, 0,   0,   0,   0,   0, 19.936005, 12.660527, 2, 31, 123.45, 123.56, 123.78, 7), 
    ('NORMAL', '2018/03/20 05:10:00', 34,  31, 64,  78, 38, 16, 1, 189,   0,   0,   0, 55.794979, 29.176736, 19, 32, 123.45, 123.56, 123.78, 8), 
    ('NORMAL', '2018/03/20 05:20:00', 88,  35, 64,  12, 31, 31, 3, 191,  97,  78,   0, 15.536413, 22.069037, 20, 33, 123.45, 123.56, 123.78, 8), 
    ('NORMAL', '2018/03/20 05:30:00', 0,   21, 66, 100, 30, 33, 2,   6, 139,   0,   0, 52.050988, 28.412365, 11, 34, 123.45, 123.56, 123.78, 8), 
    ('NORMAL', '2018/03/20 05:40:00', 19,  22, 68,  74, 30, 32, 1,  91,   0,   0,   0, 32.546705, 18.194164, 4, 35, 123.45, 123.56, 123.78, 8), 
    ('NORMAL', '2018/03/20 05:50:00', 46,  26, 66,   6, 39, 23, 3, 114,  89, 196,   0, 32.707533, 17.102933, 8, 36, 123.45, 123.56, 123.78, 8);
