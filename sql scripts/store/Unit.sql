
DROP TABLE UNIT;

CREATE TABLE UNIT
(
	UNIT_ID 		VARCHAR(10),
	UNIT_NAME		VARCHAR(120),
	CREATE_DATE		TIMESTAMP,
	MODIFIED_DATE	TIMESTAMP
);

ALTER TABLE UNIT ADD CONSTRAINT UNIT_PK PRIMARY KEY (UNIT_ID);

COMMENT ON TABLE UNIT  IS 'UNIT';




