
DROP TABLE ACCESS_FUNCTION;

CREATE TABLE ACCESS_FUNCTION
(
	AF_INDEX 		NUMBER,
	AF_NAME			VARCHAR(10),
	CREATE_DATE		TIMESTAMP,
	MODIFIED_DATE	TIMESTAMP	
);

ALTER TABLE ACCESS_FUNCTION ADD CONSTRAINT ACCESS_FUNCTION_PK PRIMARY KEY (AF_INDEX);

COMMENT ON TABLE ACCESS_FUNCTION  IS 'ACCESS_FUNCTION';

INSERT INTO ACCESS_FUNCTION (AF_INDEX, AF_NAME, CREATE_DATE, MODIFIED_DATE) VALUES(1, 'One', SYSDATE, SYSDATE);
INSERT INTO ACCESS_FUNCTION (AF_INDEX, AF_NAME, CREATE_DATE, MODIFIED_DATE) VALUES(2, 'Two', SYSDATE, SYSDATE);
