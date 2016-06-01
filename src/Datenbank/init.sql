create table t_Person( --Legt die Tabelle t_Person an, mit folgenden Attributen an:
	PID			serial, --eine Personen-ID vom Typ serial
	Anrede		varchar(30) NOT NULL, --eine Anrede vom Typ varchar() mit der Länge 30 der nicht null sein darf
	Name		varchar(30) NOT NULL, -- ein Nachname vom Typ varchar() mit der Länge 30 der nicht null sein darf
	Vorname		varchar(30),--ein Vorname vom Typ varchar() mit der Länge 30
	GebTag		Date,  --ein Geburtstag vom Typ Date
	Anschrift	varchar(30), --ein Vorname vom Typ varchar() mit der Länge 30
	PLZ			INTEGER, --eine Postleitzahl vom Typ Integer
	Ort			varchar(30),   --ein Ort vom Typ varchar() mit der Länge 30
	UST_ID		varchar(12), --eine Umsatzsteuer-ID vom Typ varchar() mit der Länge 12
	CONSTRAINT PK_t_Person PRIMARY KEY(PID), --Setzt PID als Primärschlüssel
	CONSTRAINT Anrede check(Anrede IN ('Herr','Frau','Firma')) --in Anrede dürfen nur Herr,Frau oder Firma stehen
);
create table t_Erreichbarkeit( --Legt die Tabelle t_Erreichbarkeit an, mit folgenden Attributen an:
	EID				serial, --eine Erreichbarkeits-ID vom Typ serial
	fk_t_Person_PID	integer not null, -- der Fremdschlüssel PID aus t_Person vom Typ Integer der nicht null sein darf
	Tel				varchar(20), --eine Telefonnummer vom Typ varchar() mit der Länge 20
	Handy			varchar(20), --eine Handynummer vom Typ varchar() mit der Länge 20
	EMail			text, --eine E-Mail-Adresse vom Typ text
	Text			text, --Die Details der Erreichbarkeiten vom Typ text
	CONSTRAINT PK_t_Erreichbarkeit PRIMARY KEY(EID), --Setzt EID als Primärschlüssel
	CONSTRAINT FK_t_Person_PID FOREIGN KEY (fk_t_Person_PID) REFERENCES t_Person(PID) ON UPDATE CASCADE ON DELETE CASCADE --setzt die PID aus t_Person als Fremdschlüssel mit Kaskadierung
);
create table t_Notiz( --Legt die Tabelle t_Notiz an, mit folgenden Attributen an:
	NID				serial, --eine Notiz-ID vom Typ serial
	fk_t_Person_PID	integer not null, -- der Fremdschlüssel PID aus t_Person vom Typ Integer der nicht null sein darf
	Text			text, --Die Beschreibung der Notiz vom Typ text
	Datum			Date, --Erstellungsdatum der Notiz vom Typ Date
	CONSTRAINT PK_t_Notiz PRIMARY KEY(NID),  --Setzt NID als Primärschlüssel
	CONSTRAINT FK_t_Person_PID FOREIGN KEY (fk_t_Person_PID) REFERENCES t_Person(PID) ON UPDATE CASCADE ON DELETE CASCADE --setzt die PID aus t_Person als Fremdschlüssel mit Kaskadierung
);
CREATE TABLE t_Verkaeufer( --Legt die Tabelle t_Verkaeufer an, mit folgenden Attributen an:
	fk_t_Person_PID		INTEGER NOT NULL,	-- der Fremdschlüssel PID aus t_Person vom Typ Integer der nicht null sein darf
	Anmeldename		VARCHAR(30), --ein Anmeldename vom Typ varchar() mit der Länge 30
	Passwort		VARCHAR(30), --ein Passwort vom Typ varchar() mit der Länge 30
	inaktivSeit		DATE, --Datum seid wann der Verkaeufer inaktiv vom Typ Date
	CONSTRAINT PK_t_Verkaeufer PRIMARY KEY(fk_t_Person_PID), --Setzt PID aus t_Person als Primärschlüssel
	CONSTRAINT FK_t_Person_PID FOREIGN KEY (fk_t_Person_PID) REFERENCES t_Person(PID) ON UPDATE CASCADE ON DELETE CASCADE --setzt die PID aus t_Person als Fremdschlüssel mit Kaskadierung
);
CREATE TABLE t_Admins( --Legt die Tabelle t_Admins an, mit folgenden Attributen:
	fk_t_verkaeufer_fk_t_person_pid integer NOT NULL, 	-- der Fremdschlüssel PID aus t_Person vom Typ Integer der nicht null sein darf
	CONSTRAINT PK_t_admins_fk_t_verkaeufer_fk_t_person_pid PRIMARY KEY (fk_t_verkaeufer_fk_t_person_pid), --Setzt PID aus t_Person als Primärschlüssel
	CONSTRAINT FK_t_verkaeufer_fk_t_person_pid FOREIGN KEY (fk_t_verkaeufer_fk_t_person_pid) REFERENCES t_verkaeufer(fk_t_person_pid) ON UPDATE CASCADE ON DELETE CASCADE --setzt die PID aus t_Person als Fremdschlüssel mit Kaskadierung
);

CREATE TABLE t_KFZ( --Legt die Tabelle t_KFZ an, mit folgenden Attributen:
	FIN			varchar(17) NOT NULL, --eine Fahrzeug-Identifikationsnummer vom Typ varchar mit der Länge 17 der nicht null sein darf
	Hersteller	varchar(30) NOT NULL, --ein Hersteller vom Typ varchar() mit der Länge 30 der nicht null sein darf
	Modell		varchar(30) NOT NULL, --ein Modell vom Typ varchar() mit der Länge 30 der nicht null sein darf
	KFZ_Brief	varchar(20) NOT NULL, --eine KFZ-Brief-Nummer vom Typ varchar() mit der Länge 20 der nicht null sein darf
	Leistung	integer		NOT NULL, -- die Leistung vom Typ Integer der nicht null sein darf
	Farbe		varchar(30) NOT NULL, --eine Farbe vom Typ varchar() mit der Länge 30 der nicht null sein darf
	EZ			Date 		NOT NULL,  --ein Erstzulassungsdatum vom Typ Date der nicht null sein darf
	Plakette	varchar(30) NOT NULL,  --eine Umweltplakette vom Typ varchar() mit der Länge 30 der nicht null sein darf
	Kraftstoff	varchar(30) NOT NULL,  --eine Kraftstoffart vom Typ varchar() mit der Länge 30 der nicht null sein darf
	CONSTRAINT PK_t_KFZ_FIN PRIMARY KEY(FIN) --Setzt FIN als Primärschlüssel
);
CREATE TABLE t_Sonderausstattung( --Legt die Tabelle t_Sonderausstattung an, mit folgenden Attributen:
	SID			SERIAL NOT NULL,  --eine Sonderausstattungs-ID vom Typ serial der nicht null sein darf
	Art			VARCHAR(100) NOT NULL, --ein Hersteller vom Typ varchar() mit der Länge 100 der nicht null sein darf
	CONSTRAINT PK_t_Sonderausstattung_SID PRIMARY KEY(SID) --Setzt SID als Primärschlüssel
);
CREATE TABLE t_Ausstattungsliste( --Legt die Tabelle t_Ausstattungsliste an, mit folgenden Attributen:
	fk_t_Sonderausstattung_SID			INTEGER NOT NULL, -- der Fremdschlüssel SID aus t_Sonderausstattung vom Typ Integer der nicht null sein darf
	fk_t_KFZ_FIN			VARCHAR(17) NOT NULL, -- der Fremdschlüssel FIN aus t_KFZ vom Typ varchar() mit der Länge 17 der nicht null sein darf
	CONSTRAINT PK_t_Ausstattungsliste PRIMARY KEY(fk_t_Sonderausstattung_SID,fk_t_KFZ_FIN), --Setzt SID aus t_Sonderausstattung und die FIN aus t_KFZ als zusammengesetzten Primärschlüssel
	CONSTRAINT FK_t_Sonderausstattung_SID FOREIGN KEY (fk_t_Sonderausstattung_SID) REFERENCES t_Sonderausstattung(SID) ON UPDATE CASCADE ON DELETE CASCADE, --Setzt SID aus t_Sonderausstattung als Fremdschlüssel mit Kaskadierung
	CONSTRAINT FK_t_KFZ_FIN FOREIGN KEY (fk_t_KFZ_FIN) REFERENCES t_KFZ(FIN) ON UPDATE CASCADE ON DELETE CASCADE --Setzt FIN aus t_KFZ als Fremdschlüssel mit Kaskadierung
);
CREATE TABLE t_Vorgang(  --Legt die Tabelle t_Vorgang an, mit folgenden Attributen:
	VID 					serial NOT NULL, --eine Vorgangs-ID vom Typ serial der nicht null sein darf
	fk_t_Person_PID				INTEGER,  -- der Fremdschlüssel PID aus t_Person vom Typ Integer (der zukünftige Käufer)
	fk_t_Verkaeufer_PID_EK 			INTEGER NOT NULL, --der Fremdschlüssel PID aus t_Person vom Typ Integer der nicht null sein darf (der zukünftige Einkäufer)
	fk_t_Verkaeufer_PID_VK 			INTEGER, 	--der Fremdschlüssel PID aus t_Person vom Typ Integer  (der zukünftige Verkäufer)
	fk_t_KFZ_FIN				VARCHAR(17) NOT NULL, --der Fremdschlüssel FIN aus t_KFZ vom Typ varchar() mit der Länge 17 der nicht null sein darf
	Epreis 					numeric(7,2) NOT NULL, --der Einkaufspreis vom Typ numeric (7,2) der nicht null sein darf
	Vpreis 					numeric(7,2), --der Verkaufspreis vom Typ numeric (7,2) der nicht null sein darf
	KM 					INTEGER, --der Kilometerstand vom Typ Integer
	Schaeden 				text,  --vorhandene Schäden am KFZ vom Typ text
	VKDatum 				Date, --das Verkaufsdatum vom Typ Date
	EKDatum 				Date, --das Einkaufsdatum vom Typ Date
	KennZ 					varchar(10), --das Kennzeichen vom Typ varchar() mit der Länge 10
	rabattgrund 			text, --Rabattgründe beim Kauf vom Typ text
	TUEV 					Date, --Datum des letzten TUEVs des KFZ vom Typ Date
	SonstVereinb 			text, --sonstige Vereinbarungen beim Kauf vom Typ text
	VPreisPlan 				numeric(7,2),--der geplante Verkaufspreis vom Typ numeric (7,2)
	CONSTRAINT PK_t_Vorgang PRIMARY KEY(VID), --Setzt VID als Primärschlüssel
	CONSTRAINT FK_t_Person_PID FOREIGN KEY (fk_t_Person_PID) REFERENCES t_Person(PID)ON UPDATE CASCADE ON DELETE CASCADE, --Setzt PID aus t_Person als Fremdschlüssel (Käufer) mit Kaskadierung
	CONSTRAINT FK_t_Verkaeufer_PID_EK FOREIGN KEY(fk_t_Verkaeufer_PID_EK) REFERENCES t_Verkaeufer(fk_t_person_PID) ON UPDATE CASCADE ON DELETE CASCADE, --Setzt PID aus t_Person als Fremdschlüssel (Einkäufer) mit Kaskadierung
	CONSTRAINT FK_t_Verkaeufer_PID_VK FOREIGN KEY(fk_t_Verkaeufer_PID_VK) REFERENCES t_Verkaeufer(fk_t_person_PID) ON UPDATE CASCADE ON DELETE CASCADE, --Setzt PID aus t_Person als Fremdschlüssel (Verkäufer) mit Kaskadierung
	CONSTRAINT FK_t_KFZ_FIN FOREIGN KEY(fk_t_KFZ_FIN) REFERENCES t_KFZ(FIN) ON UPDATE CASCADE ON DELETE CASCADE --Setzt FIN aus t_KFZ als Fremdschlüssel mit Kaskadierung
);
CREATE TABLE t_Aktion( --Legt die Tabelle t_Vorgang an, mit folgenden Attributen:
	fk_t_Person_PID 		INTEGER NOT NULL,  -- der Fremdschlüssel PID aus t_Person vom Typ Integer der nicht null sein darf
	fk_t_KFZ_FIN 			varchar(17) NOT NULL, -- der Fremdschlüssel FIN aus t_KFZ vom Typ varchar() mit der Länge 17 der nicht null sein darf
	Datum 				Date NOT NULL, -- Das Datum der durchzuführenden Aktion vom Typ Date der nicht null sein darf
	Text 				text NOT NULL,  -- Der Text zu der Aktion vom Typ text der nicht null sein darf
	CONSTRAINT PK_t_Aktion PRIMARY KEY(fk_t_Person_PID,fk_t_KFZ_FIN),  --Setzt PID aus t_Person und FIN aus t_KFZ als zusammengesetzten Primärschlüssel
	CONSTRAINT FK_t_Person_PID FOREIGN KEY(fk_t_Person_PID) REFERENCES t_Person(PID) ON UPDATE CASCADE ON DELETE CASCADE, ----Setzt PID aus t_Person als Fremdschlüssel mit Kaskadierung
	CONSTRAINT FK_t_KFZ_FIN FOREIGN KEY(fk_t_KFZ_FIN) REFERENCES t_KFZ(FIN)  ON UPDATE CASCADE ON DELETE CASCADE --Setzt FIN aus t_KFZ als Fremdschlüssel mit Kaskadierung
);