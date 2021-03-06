
DROP TABLE COMPANY;

CREATE TABLE COMPANY
(
	COMPANY_ID 						NUMBER,
	COMPANY_NAME					VARCHAR(120) NOT NULL,	
	COMPANY_ADDRESS_LINE_1			VARCHAR(120),
	COMPANY_ADDRESS_LINE_2			VARCHAR(120),
	COMPANY_ADDRESS_CITY			VARCHAR(120),
	GEO_ID_1						NUMBER,
	GEO_ID_2						NUMBER,
	GEO_ID_3						NUMBER,
	GEO_ID_4						NUMBER,
	GEO_ID_5						NUMBER,
	COMPANY_ADDRESS_PINCODE			NUMBER,
	COMPANY_ADDRESS_CONTACTNO		VARCHAR(120),
	COMPANY_ADDRESS_CONTACTNO_1		VARCHAR(120),
	COMPANY_ADDRESS_CONTACTNO_2		VARCHAR(120),
	COMPANY_ADDRESS_CONTACTNO_3		VARCHAR(120),
	COMPANY_STATUS					NUMBER,
	COMPANY_LOGO					BLOB,
	CREATE_DATE						TIMESTAMP,
	MODIFIED_DATE					TIMESTAMP
	
);

ALTER TABLE COMPANY ADD CONSTRAINT COMPANY_PK PRIMARY KEY (COMPANY_ID);

COMMENT ON TABLE COMPANY  IS 'COMPANY';

INSERT INTO "TESTPROJECT"."COMPANY" (COMPANY_ID, COMPANY_NAME, COMPANY_ADDRESS_LINE_1, GEO_ID_1, GEO_ID_2, GEO_ID_3, GEO_ID_4, GEO_ID_5, COMPANY_ADDRESS_PINCODE, COMPANY_ADDRESS_CONTACTNO, COMPANY_ADDRESS_CONTACTNO_1, COMPANY_ADDRESS_CONTACTNO_2, COMPANY_ADDRESS_CONTACTNO_3, COMPANY_STATUS, CREATE_DATE, MODIFIED_DATE) 
VALUES (5, 'Company5', 'CompanyAddress5', '1234', '1234', '1234', '1234', '1234', '213213', '324234234', '213123123123', '213123123123', '213123123123', '1', TO_TIMESTAMP('07-APR-15 08.40.56.000000000 PM', 'DD-MON-RR HH.MI.SS.FF AM'), TO_TIMESTAMP('07-APR-15 08.40.56.000000000 PM', 'DD-MON-RR HH.MI.SS.FF AM'));

INSERT INTO "TESTPROJECT"."COMPANY" (COMPANY_ID, COMPANY_NAME, COMPANY_ADDRESS_LINE_1, GEO_ID_1, GEO_ID_2, GEO_ID_3, GEO_ID_4, GEO_ID_5, COMPANY_ADDRESS_PINCODE, COMPANY_ADDRESS_CONTACTNO, COMPANY_ADDRESS_CONTACTNO_1, COMPANY_ADDRESS_CONTACTNO_2, COMPANY_ADDRESS_CONTACTNO_3, COMPANY_STATUS, CREATE_DATE, MODIFIED_DATE) 
VALUES (6, 'Company6', 'CompanyAddress6', '1234', '1234', '1234', '1234', '1234', '213213', '324234234', '213123123123', '213123123123', '213123123123', '1', TO_TIMESTAMP('07-APR-15 08.40.56.000000000 PM', 'DD-MON-RR HH.MI.SS.FF AM'), TO_TIMESTAMP('07-APR-15 08.40.56.000000000 PM', 'DD-MON-RR HH.MI.SS.FF AM'));

INSERT INTO "TESTPROJECT"."COMPANY" (COMPANY_ID, COMPANY_NAME, COMPANY_ADDRESS_LINE_1, GEO_ID_1, GEO_ID_2, GEO_ID_3, GEO_ID_4, GEO_ID_5, COMPANY_ADDRESS_PINCODE, COMPANY_ADDRESS_CONTACTNO, COMPANY_ADDRESS_CONTACTNO_1, COMPANY_ADDRESS_CONTACTNO_2, COMPANY_ADDRESS_CONTACTNO_3, COMPANY_STATUS, CREATE_DATE, MODIFIED_DATE) 
VALUES (7, 'Company7', 'CompanyAddress7', '1234', '1234', '1234', '1234', '1234', '213213', '324234234', '213123123123', '213123123123', '213123123123', '1', TO_TIMESTAMP('07-APR-15 08.40.56.000000000 PM', 'DD-MON-RR HH.MI.SS.FF AM'), TO_TIMESTAMP('07-APR-15 08.40.56.000000000 PM', 'DD-MON-RR HH.MI.SS.FF AM'));

INSERT INTO "TESTPROJECT"."COMPANY" (COMPANY_ID, COMPANY_NAME, COMPANY_ADDRESS_LINE_1, GEO_ID_1, GEO_ID_2, GEO_ID_3, GEO_ID_4, GEO_ID_5, COMPANY_ADDRESS_PINCODE, COMPANY_ADDRESS_CONTACTNO, COMPANY_ADDRESS_CONTACTNO_1, COMPANY_ADDRESS_CONTACTNO_2, COMPANY_ADDRESS_CONTACTNO_3, COMPANY_STATUS, CREATE_DATE, MODIFIED_DATE) 
VALUES (8, 'Company8', 'CompanyAddress8', '1234', '1234', '1234', '1234', '1234', '213213', '324234234', '213123123123', '213123123123', '213123123123', '1', TO_TIMESTAMP('07-APR-15 08.40.56.000000000 PM', 'DD-MON-RR HH.MI.SS.FF AM'), TO_TIMESTAMP('07-APR-15 08.40.56.000000000 PM', 'DD-MON-RR HH.MI.SS.FF AM'));

INSERT INTO "TESTPROJECT"."COMPANY" (COMPANY_ID, COMPANY_NAME, COMPANY_ADDRESS_LINE_1, GEO_ID_1, GEO_ID_2, GEO_ID_3, GEO_ID_4, GEO_ID_5, COMPANY_ADDRESS_PINCODE, COMPANY_ADDRESS_CONTACTNO, COMPANY_ADDRESS_CONTACTNO_1, COMPANY_ADDRESS_CONTACTNO_2, COMPANY_ADDRESS_CONTACTNO_3, COMPANY_STATUS, CREATE_DATE, MODIFIED_DATE) 
VALUES (9, 'Company9', 'CompanyAddress9', '1234', '1234', '1234', '1234', '1234', '213213', '324234234', '213123123123', '213123123123', '213123123123', '1', TO_TIMESTAMP('07-APR-15 08.40.56.000000000 PM', 'DD-MON-RR HH.MI.SS.FF AM'), TO_TIMESTAMP('07-APR-15 08.40.56.000000000 PM', 'DD-MON-RR HH.MI.SS.FF AM'));

commit;


