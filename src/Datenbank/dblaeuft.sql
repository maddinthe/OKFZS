CREATE DATABASE db_okfzs

WITH OWNER = postgres
ENCODING = 'UTF8'
TABLESPACE=pg_default
LC_COLLATE='German_Germany.1252'
LC_CTYPE='German_Germany.1252'
CONNECTION LIMIT = -1;

\c db_okfzs

create table t_Person(
	PID			serial,
	Anrede		varchar(30) NOT NULL,
	Name		varchar(30) NOT NULL,
	Vorname		varchar(30),
	GebTag		Date,
	Anschrift	varchar(30),
	PLZ			INTEGER,
	Ort			varchar(30),
	UST_ID		varchar(12),
	CONSTRAINT PK_t_Person PRIMARY KEY(PID),
	CONSTRAINT Anrede check(Anrede IN ('Herr','Frau','Firma'))
);
create table t_Erreichbarkeit(
	EID				serial,
	fk_t_Person_PID	integer not null,
	Tel				varchar(20),
	Handy			varchar(20),
	EMail			text,
	Text			text,
	CONSTRAINT PK_t_Erreichbarkeit PRIMARY KEY(EID),
	CONSTRAINT FK_t_Person_PID FOREIGN KEY (fk_t_Person_PID) REFERENCES t_Person(PID) ON UPDATE CASCADE ON DELETE CASCADE
);
create table t_Notiz(
	NID				serial,
	fk_t_Person_PID	integer not null,
	Text			text,
	Datum			Date,
	CONSTRAINT PK_t_Notiz PRIMARY KEY(NID),
	CONSTRAINT FK_t_Person_PID FOREIGN KEY (fk_t_Person_PID) REFERENCES t_Person(PID) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE t_Verkaeufer(
	fk_t_Person_PID		INTEGER NOT NULL,		
	Anmeldename		VARCHAR(30),
	Passwort		VARCHAR(30),
	inaktivSeit		DATE,
	CONSTRAINT PK_t_Verkaeufer PRIMARY KEY(fk_t_Person_PID),
	CONSTRAINT FK_t_Person_PID FOREIGN KEY (fk_t_Person_PID) REFERENCES t_Person(PID) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE t_admins(
	fk_t_verkaeufer_fk_t_person_pid integer NOT NULL,
	CONSTRAINT PK_t_admins_fk_t_verkaeufer_fk_t_person_pid PRIMARY KEY (fk_t_verkaeufer_fk_t_person_pid),
	CONSTRAINT FK_t_verkaeufer_fk_t_person_pid FOREIGN KEY (fk_t_verkaeufer_fk_t_person_pid) REFERENCES t_verkaeufer(fk_t_person_pid) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE t_KFZ(
	FIN			varchar(17) NOT NULL,
	Hersteller	varchar(30) NOT NULL,
	Modell		varchar(30) NOT NULL,
	KFZ_Brief	varchar(20) NOT NULL,
	Leistung	integer		NOT NULL,
	Farbe		varchar(30) NOT NULL,
	EZ			Date 		NOT NULL,
	Plakette	varchar(30) NOT NULL,
	Kraftstoff	varchar(30) NOT NULL,
	CONSTRAINT PK_t_KFZ_FIN PRIMARY KEY(FIN)
);
CREATE TABLE t_Sonderausstattung(
	SID			SERIAL NOT NULL,
	Art			VARCHAR(30) NOT NULL,
	CONSTRAINT PK_t_Sonderausstattung_SID PRIMARY KEY(SID)
);
CREATE TABLE t_Ausstattungsliste(
	fk_t_Sonderausstattung_SID			INTEGER NOT NULL,
	fk_t_KFZ_FIN			VARCHAR(17) NOT NULL,
	CONSTRAINT PK_t_Ausstattungsliste PRIMARY KEY(fk_t_Sonderausstattung_SID,fk_t_KFZ_FIN),
	CONSTRAINT FK_t_Sonderausstattung_SID FOREIGN KEY (fk_t_Sonderausstattung_SID) REFERENCES t_Sonderausstattung(SID) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FK_t_KFZ_FIN FOREIGN KEY (fk_t_KFZ_FIN) REFERENCES t_KFZ(FIN) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE t_Vorgang(
	VID 					serial NOT NULL,
	fk_t_Person_PID				INTEGER,  --Kaeufer
	fk_t_Verkaeufer_PID_EK 			INTEGER NOT NULL, --Einkaeufer
	fk_t_Verkaeufer_PID_VK 			INTEGER, 	--Verkaeufer
	fk_t_KFZ_FIN				VARCHAR(17) NOT NULL,
	Epreis 					numeric(7,2) NOT NULL,
	Vpreis 					numeric(7,2),
	KM 					INTEGER,
	Schaeden 				text,
	VKDatum 				Date,
	EKDatum 				Date,
	KennZ 					varchar(10),
	rabattgrund 			text,
	TUEV 					Date,
	SonstVereinb 			text,
	VPreisPlan 				numeric(7,2),
	CONSTRAINT PK_t_Vorgang PRIMARY KEY(VID),
	CONSTRAINT FK_t_Person_PID FOREIGN KEY (fk_t_Person_PID) REFERENCES t_Person(PID)ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FK_t_Verkaeufer_PID_EK FOREIGN KEY(fk_t_Verkaeufer_PID_EK) REFERENCES t_Verkaeufer(fk_t_person_PID) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FK_t_Verkaeufer_PID_VK FOREIGN KEY(fk_t_Verkaeufer_PID_VK) REFERENCES t_Verkaeufer(fk_t_person_PID) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FK_t_KFZ_FIN FOREIGN KEY(fk_t_KFZ_FIN) REFERENCES t_KFZ(FIN) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE t_Aktion(
	fk_t_Person_PID 		INTEGER NOT NULL,
	fk_t_KFZ_FIN 			varchar(17) NOT NULL,
	Datum 				Date NOT NULL,
	Text 				text NOT NULL,
	CONSTRAINT PK_t_Aktion PRIMARY KEY(fk_t_Person_PID,fk_t_KFZ_FIN),
	CONSTRAINT FK_t_Person_PID FOREIGN KEY(fk_t_Person_PID) REFERENCES t_Person(PID) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FK_t_KFZ_FIN FOREIGN KEY(fk_t_KFZ_FIN) REFERENCES t_KFZ(FIN)  ON UPDATE CASCADE ON DELETE CASCADE
);