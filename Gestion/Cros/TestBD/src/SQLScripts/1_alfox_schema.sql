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
    LatitudeGPS float not null, 
    LongitudeGPS float not null, 
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
    Latitude float not null, 
    Longitude float not null, 
    DistanceParcourue bigint(20) not null, 
    VehiculeID int(10) not null, 
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
    CompteurReel float not null, 
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
    Latitude float not null, 
    Longitude float not null, 
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
