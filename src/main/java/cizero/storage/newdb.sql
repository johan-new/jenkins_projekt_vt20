CREATE DATABASE dbContacts;
USE dbContacts;
CREATE TABLE tblContactbook (fldFName VARCHAR(30), fldLName VARCHAR(30), fldTNr VARCHAR(20), fldEmail VARCHAR(20));
ALTER TABLE tblContactbook ADD PRIMARY KEY (fldFName,fldLName,fldTNr,fldEmail);
INSERT INTO tblContactbook VALUES ('Bill','Jobs','001523234','steve@gates.com');
INSERT INTO tblContactbook VALUES ('Melanie','Carter','0015244334','ceo@bugs.com');
