
DROP TABLE STORE;

CREATE TABLE STORE
(
	STORE_ID					VARCHAR(120),
	STORE_NAME					VARCHAR(120),
	STORE_ADDREESS_LINE_1		VARCHAR(120),
	STORE_ADDREESS_LINE_2		VARCHAR(120),
	STORE_ADDRESS_CITY			VARCHAR(120),
	STORE_ADDRESS_COUNTRY		VARCHAR(120),
	STORE_ADDRESS_REGION		VARCHAR(120),
	STORE_ADDRESS_PINCODE		NUMBER
	STORE_ADDRESS_CONTACTNO		VARCHAR(120),
	STORE_ADDRESS_CONTACTNO_1	VARCHAR(120),
	STORE_ADDRESS_CONTACTNO_2	VARCHAR(120),
	STORE_ADDRESS_CONTACTNO_3	VARCHAR(120),
);

ALTER TABLE STORE ADD CONSTRAINT STORE_PK PRIMARY KEY (STORE_ID);

COMMENT ON TABLE STORE  IS 'STORE';

INSERT INTO STORE
(STORE_ID, STORE_NAME, STORE_ADDREESS_LINE_1, STORE_ADDREESS_LINE_2, STORE_ADDRESS_CITY, STORE_ADDRESS_COUNTRY, STORE_ADDRESS_REGION, 
STORE_ADDRESS_PINCODE, STORE_ADDRESS_CONTACTNO, STORE_ADDRESS_CONTACTNO_1, STORE_ADDRESS_CONTACTNO_2, STORE_ADDRESS_CONTACTNO_3)
VALUES (1, 'STORE 1', 'LINE 1', 'LINE 2', 'CITY', 'COUNTRY' 'REGION', 111111, '0000000000', '1111111111', '2222222222', '3333333333');

