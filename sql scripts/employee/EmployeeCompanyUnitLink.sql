
DROP TABLE EMPLOYEE_COMPANY_UNIT_LINK;

CREATE TABLE EMPLOYEE_COMPANY_UNIT_LINK
(
	EMP_ID 			NUMBER,
	COMP_ID			VARCHAR(120),
	UNIT_ID			VARCHAR(120),
	CREATE_DATE		TIMESTAMP,
	MODIFIED_DATE	TIMESTAMP
);

ALTER TABLE EMPLOYEE_COMPANY_UNIT_LINK ADD CONSTRAINT EMPLOYEE_COMPANY_UNIT_LINK_PK PRIMARY KEY (EMP_ID, COMP_ID, UNIT_ID);

COMMENT ON TABLE EMPLOYEE_COMPANY_UNIT_LINK  IS 'EMPLOYEE_COMPANY_UNIT_LINK';

INSERT INTO EMPLOYEE_COMPANY_UNIT_LINK (EMP_ID, COMP_ID, UNIT_ID, CREATE_DATE, MODIFIED_DATE) VALUES(1, 1, 41, SYSDATE, SYSDATE);
INSERT INTO EMPLOYEE_COMPANY_UNIT_LINK (EMP_ID, COMP_ID, UNIT_ID, CREATE_DATE, MODIFIED_DATE) VALUES(2, 1, 41, SYSDATE, SYSDATE);

