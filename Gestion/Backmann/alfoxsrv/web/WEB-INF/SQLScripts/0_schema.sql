# ------------------------------------------------------------------------------
# ------------------------------------------------------------------------------
#                   Base de données alfox - Fevrier 2018
# ------------------------------------------------------------------------------
# ------------------------------------------------------------------------------    

drop schema if exists alfox;
create schema alfox;
use alfox;

create table Contrat (
    ID int(10) not null auto_increment, 
    ZoneLimiteID int(10) not null, 
    LoueurID int(10) not null, 
    VehiculeID int(10) not null, 
    Numero varchar(255) not null, 
    DateCreation date not null, 
    Type varchar(255), 
    Infos varchar(255), 
    primary key (ID)
) engine=InnoDB character set utf8;

create table DonneesHisto (
    ID int(10) not null auto_increment, 
    Mode int(10), 
    Datation date, 
    Vitesse int(10) not null, 
    Regime int(10) not null, 
    Consommation int(10) not null, 
    VitesseMax int(10) not null, 
    RegimeMax int(10) not null,
    ConsoMax int(10) not null, 
    NbDefauts int(10) not null, 
    Defauts int(10) not null, 
    LatitudeGPS float not null, 
    LongitudeGPS float not null, 
    DistanceParcourue bigint(19) not null, 
    VehiculeID int(10) not null, 
    primary key (ID)
) engine=InnoDB character set utf8;

create table DonneesTR (
    ID int(10) not null auto_increment, 
    Mode int(10) not null, 
    Datation date not null, 
    Vitesse int(10) not null, 
    Regime int(10) not null, 
    Consommation int(10) not null, 
    VitesseMax int(10) not null, 
    RegimeMax int(10) not null, 
    ConsoMax int(10) not null, 
    NbDefauts int(10) not null, 
    Defauts int(10) not null, 
    Latitude float not null, 
    Longitude float not null,   
    DistanceParcourue bigint(19) not null, 
    VehiculeID int(10) not null, 
    primary key (ID)
) engine=InnoDB character set utf8;

create table Loueur (
    ID int(10) not null auto_increment, 
    Nom varchar(255) not null, 
    Prenom varchar(255) not null, 
    Telephone varchar(255), 
    Mail varchar(255), 
    primary key (ID)
) engine=InnoDB character set utf8;

create table Position (
    ID int(10) not null auto_increment, 
    ZoneLimiteID int(10) not null, 
    Ordre int(10) not null,
    Latitude float not null, 
    Longitude float not null, 
    primary key (ID)
) engine=InnoDB character set utf8;

create table `User` (
    ID int(10) not null auto_increment, 
    Role varchar(255) not null, 
    Mdp varchar(255) not null, 
    Mail varchar(255) not null, 
    primary key (ID)
) engine=InnoDB character set utf8;

create table Vehicule (
    ID int(10) not null auto_increment, 
    Immatriculation varchar(255) not null, 
    DateMiseEnService date,
    Marque varchar(255) not null,
    Modele varchar(255) not null,
    Motorisation varchar(255),
    IdSigfox varchar(255), 
    DateControleTechnique date,
    DateVidange date,
    KmVidange date, 
    HorsZone tinyint(1) not null, 
    TauxUtilisation int(10) not null, 
    AProbleme tinyint(1) not null, 
    CompteurRéel float not null, 
    DonneesTRID int(10) not null, 
    primary key (ID)
) engine=InnoDB character set utf8;

create table ZoneLimite (
    ID int(10) not null auto_increment, 
    ContratID int(10),
    Nom varchar(255) not null, 
    ZoneLimiteID int(10) not null, 
    primary key (ID)
) engine=InnoDB character set utf8;

alter table Contrat add index FKContrat5291 (ZoneLimiteID), add constraint FKContrat5291 foreign key (ZoneLimiteID) references ZoneLimite (ID);
alter table DonneesHisto add index FKDonneesHis493650 (VehiculeID), add constraint FKDonneesHis493650 foreign key (VehiculeID) references Vehicule (ID);
alter table DonneesTR add index FKDonneesTR404677 (VehiculeID), add constraint FKDonneesTR404677 foreign key (VehiculeID) references Vehicule (ID);
alter table Position add index FKPosition217606 (ZoneLimiteID), add constraint FKPosition217606 foreign key (ZoneLimiteID) references ZoneLimite (ID);
alter table Contrat add index FKContrat433214 (VehiculeID), add constraint FKContrat433214 foreign key (VehiculeID) references Vehicule (ID);
alter table Contrat add index FKContrat81038 (LoueurID), add constraint FKContrat81038 foreign key (LoueurID) references Loueur (ID);

