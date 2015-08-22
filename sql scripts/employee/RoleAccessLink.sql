
DROP TABLE ROLE_ACCESS_LINK;

CREATE TABLE ROLE_ACCESS_LINK
(
	EMP_ID 			VARCHAR(120),
	ROLE_INDEX 		NUMBER,
	AF_INDEX 		NUMBER,
	CREATE_DATE		TIMESTAMP
);

ALTER TABLE ROLE_ACCESS_LINK ADD CONSTRAINT ROLE_ACCESS_LINK_PK PRIMARY KEY (EMP_ID, ROLE_INDEX, AF_INDEX);

COMMENT ON TABLE ROLE_ACCESS_LINK  IS 'ROLE_ACCESS_LINK';

INSERT INTO ROLE_ACCESS_LINK (EMP_ID, ROLE_INDEX, AF_INDEX, CREATE_DATE) VALUES(1, 11, 31, SYSDATE);

