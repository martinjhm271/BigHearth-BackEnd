-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2018-04-30 01:37:25.434

-- tables


-- Table: Roles
CREATE TABLE Roles (
    rol_id int  NOT NULL,
    name varchar(15)  NOT NULL,
    CONSTRAINT Roles_pk PRIMARY KEY (rol_id)
);

-- Table: Event
CREATE TABLE Event (
    id int  NOT NULL,
    name varchar(200)  NOT NULL,
    image bytea  ,
    type varchar(50)  NOT NULL,
    numberVolunteers int  NOT NULL,
    maxVolunteers int  NOT NULL,
    description text  ,
    date date  NOT NULL,
    organization int  NOT NULL,
    latitude decimal(50,20)  NOT NULL,
    longitude decimal(50,20)  NOT NULL,
    CONSTRAINT Event_pk PRIMARY KEY (id)
);

-- Table: Organization
CREATE TABLE Organization (
    commercialName varchar(15)  NOT NULL,
    businessName varchar(15)  NOT NULL,
    state varchar(15)  NOT NULL,
    city varchar(15)  NOT NULL,
    address varchar(15)  NOT NULL,
    description text ,
    nit int  NOT NULL,
    volunteersMade int  NOT NULL,
    photo bytea  ,
    orgPassword varchar(512)  NOT NULL,
    mail varchar(50)  NOT NULL,
    CONSTRAINT Organization_pk PRIMARY KEY (nit)
);

-- Table: Volunteer
CREATE TABLE Volunteer (
    vol_id int  NOT NULL,
    name varchar(20)  NOT NULL,
    lastname varchar(20)  NOT NULL,
    gender char(20)  NOT NULL,
    bornDate date  NOT NULL,
    hours int  NOT NULL,
    state varchar(30)  NOT NULL,
    city varchar(30)  NOT NULL,
    address varchar(60)  NOT NULL,
    description text  ,
    volunteersMade int  NOT NULL,
    photo bytea  ,
    mail varchar(50)  NOT NULL,
    volPassword varchar(512)  NOT NULL,
    interest varchar(512) ,
    CONSTRAINT Volunteer_pk PRIMARY KEY (vol_id)
);

-- Table: Requirement
CREATE TABLE Requirement (
    id int  NOT NULL,
    name varchar(15)  NOT NULL,
    quantity int  NOT NULL,
    event_id int  NOT NULL,
    CONSTRAINT Requirements_ak_1 UNIQUE (event_id) NOT DEFERRABLE  INITIALLY IMMEDIATE,
    CONSTRAINT Requirement_pk PRIMARY KEY (id)
);

-- Table: Review
CREATE TABLE Review (
    id int  NOT NULL,
    comment text  NOT NULL,
    score int  NOT NULL,
    event_id int  NOT NULL,
    CONSTRAINT Review_pk PRIMARY KEY (id)
);

-- Table: Rol_User
CREATE TABLE Rol_User (
    mail varchar(50)  NOT NULL,
    rol_id int  NOT NULL,
    CONSTRAINT Rol_User_pk PRIMARY KEY (mail)
);



-- Table: Volunteer_Event
CREATE TABLE Volunteer_Event (
    volunteer_id int  NOT NULL,
    event_id int  NOT NULL,
    CONSTRAINT Volunteer_Event_pk PRIMARY KEY (volunteer_id,event_id)
);

-- foreign keys
-- Reference: Event_Organization (table: Event)
ALTER TABLE Event ADD CONSTRAINT Event_Organization
    FOREIGN KEY (organization)
    REFERENCES Organization (nit)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Organization_Rol_User (table: Organization)
ALTER TABLE Organization ADD CONSTRAINT Organization_Rol_User
    FOREIGN KEY (mail)
    REFERENCES Rol_User (mail)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Requirement_Event (table: Requirement)
ALTER TABLE Requirement ADD CONSTRAINT Requirement_Event
    FOREIGN KEY (event_id)
    REFERENCES Event (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Review_Event (table: Review)
ALTER TABLE Review ADD CONSTRAINT Review_Event
    FOREIGN KEY (event_id)
    REFERENCES Event (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Rol_User_Roles (table: Rol_User)
ALTER TABLE Rol_User ADD CONSTRAINT Rol_User_Roles
    FOREIGN KEY (rol_id)
    REFERENCES Roles (rol_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Volunteer_Event_Event (table: Volunteer_Event)
ALTER TABLE Volunteer_Event ADD CONSTRAINT Volunteer_Event_Event
    FOREIGN KEY (event_id)
    REFERENCES Event (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Volunteer_Event_Volunteer (table: Volunteer_Event)
ALTER TABLE Volunteer_Event ADD CONSTRAINT Volunteer_Event_Volunteer
    FOREIGN KEY (volunteer_id)
    REFERENCES Volunteer (vol_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Volunteer_Rol_User (table: Volunteer)
ALTER TABLE Volunteer ADD CONSTRAINT Volunteer_Rol_User
    FOREIGN KEY (mail)
    REFERENCES Rol_User (mail)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

