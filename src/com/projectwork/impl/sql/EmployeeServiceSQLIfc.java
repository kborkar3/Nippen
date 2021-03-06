package com.projectwork.impl.sql;

import com.projectwork.impl.sql.CompanyServiceSQLIfc.COLUMNS;

public interface EmployeeServiceSQLIfc
{

    String TABLE = "EMPLOYEE";

    final class COLUMNS
    {
        public static final String EMP_ID = "EMP_ID";

        public static final String ENCRYPTED_PWD = "ENCRYPTED_PWD";
        
        public static final String EMP_FIRST_NAME  = "EMP_FIRST_NAME";
        
        public static final String EMP_LAST_NAME  = "EMP_LAST_NAME";
        
        public static final String EMP_LOCALE  = "EMP_LOCALE";
        
        public static final String EMP_ADDRESS_LINE_1  = "EMP_ADDRESS_LINE_1";
        
        public static final String EMP_ADDRESS_LINE_2  = "EMP_ADDRESS_LINE_2";

        public static final String EMP_ADDRESS_CITY  = "EMP_ADDRESS_CITY";
        
        public static final String EMP_ADDRESS_COUNTRY  = "EMP_ADDRESS_COUNTRY";
        
        public static final String EMP_ADDRESS_PINCODE  = "EMP_ADDRESS_PINCODE";

        public static final String EMP_ADDRESS_CONTACTNO  = "EMP_ADDRESS_CONTACTNO";
        
        public static final String ROLE_INDEX  = "ROLE_INDEX";
        
        public static final String COMPANY_ID  = "COMPANY_ID";
        
        public static final String CREATE_DATE  = "CREATE_DATE";
        
        public static final String MODIFIED_DATE  = "MODIFIED_DATE";
        
        public static final String EMP_STATUS  = "EMP_STATUS";

        public static final String PASSWORD_RESET_PIN  = "PASSWORD_RESET_PIN";
        
    }
             
    String VALIDATE_USER = " SELECT COUNT(*) FROM " + TABLE + " WHERE " + COLUMNS.EMP_ID + "=? AND "+ COLUMNS.ENCRYPTED_PWD +" =?";
    
    String CHECK_USER = " SELECT COUNT(*) FROM " + TABLE + " WHERE " + COLUMNS.EMP_ID + "=? ";
    
    String CHECK_PASSWORD_RESET_PIN = " SELECT COUNT(*) FROM " + TABLE + " WHERE " + COLUMNS.EMP_ID + "=? AND "+ COLUMNS.PASSWORD_RESET_PIN +" =1";
    
    String VALIDATE_TEMPORARY_PASSWORD = " SELECT COUNT(*) FROM " + TABLE + " WHERE " + COLUMNS.EMP_ID + "=? AND "+ COLUMNS.ENCRYPTED_PWD +" =?";
    
    String SET_TEMPORARY_PASSWORD = "UPDATE " + TABLE + " SET " + COLUMNS.PASSWORD_RESET_PIN + " =1  , " + COLUMNS.ENCRYPTED_PWD +" =? " +" WHERE " + COLUMNS.EMP_ID + " =? ";
    
    String RESET_FORGOTTEN_PASSWORD = "UPDATE " + TABLE + " SET " + COLUMNS.PASSWORD_RESET_PIN + " =0  , " + COLUMNS.ENCRYPTED_PWD +" =? " +" WHERE " + COLUMNS.EMP_ID + " =? ";
    
    String CREATE_EMPLOYEE_QUERY = " INSERT INTO " + TABLE + " (" + COLUMNS.EMP_ID + " , " + COLUMNS.ENCRYPTED_PWD + ","
                    + COLUMNS.EMP_FIRST_NAME + " ," + COLUMNS.EMP_LAST_NAME + " ," + COLUMNS.EMP_LOCALE + " ,"
                    + COLUMNS.EMP_ADDRESS_LINE_1 + " ," + COLUMNS.EMP_ADDRESS_LINE_2 + " ," + COLUMNS.EMP_ADDRESS_CITY + " ,"
                    + COLUMNS.EMP_ADDRESS_COUNTRY + " ," + COLUMNS.EMP_ADDRESS_PINCODE + " ," + COLUMNS.EMP_ADDRESS_CONTACTNO + " ,"
                    + COLUMNS.ROLE_INDEX + " ," + COLUMNS.COMPANY_ID + " ," + COLUMNS.CREATE_DATE + " ,"
                    + COLUMNS.MODIFIED_DATE + " ," + COLUMNS.EMP_STATUS+ " ," + COLUMNS.PASSWORD_RESET_PIN +  ") "
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,SYSDATE,1,0)";
    
    String GET_COMPANY_ID = " SELECT " + COLUMNS.COMPANY_ID + " FROM " + TABLE + " WHERE " + COLUMNS.EMP_ID + "=? ";
    
}
