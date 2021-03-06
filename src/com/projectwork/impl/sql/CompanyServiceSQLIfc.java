package com.projectwork.impl.sql;

public interface CompanyServiceSQLIfc
{
    String TABLE = "COMPANY";

    final class COLUMNS
    {
        public static final String COMPANY_ID = "COMPANY_ID";

        public static final String COMPANY_NAME = "COMPANY_NAME";
        
        public static final String COMPANY_ADDRESS_LINE_1 = "COMPANY_ADDRESS_LINE_1";
        
        public static final String COMPANY_ADDRESS_LINE_2 = "COMPANY_ADDRESS_LINE_2";
        
        public static final String COMPANY_ADDRESS_CITY = "COMPANY_ADDRESS_CITY";
        
        public static final String GEO_ID_1 = "GEO_ID_1";
        
        public static final String GEO_ID_2 = "GEO_ID_2";
        
        public static final String GEO_ID_3 = "GEO_ID_3";
        
        public static final String GEO_ID_4 = "GEO_ID_4";
        
        public static final String GEO_ID_5 = "GEO_ID_5";
        
        public static final String COMPANY_ADDRESS_PINCODE = "COMPANY_ADDRESS_PINCODE";
        
        public static final String COMPANY_ADDRESS_CONTACTNO = "COMPANY_ADDRESS_CONTACTNO";
        
        public static final String COMPANY_ADDRESS_CONTACTNO_1 = "COMPANY_ADDRESS_CONTACTNO_1";
        
        public static final String COMPANY_ADDRESS_CONTACTNO_2 = "COMPANY_ADDRESS_CONTACTNO_2";
        
        public static final String COMPANY_ADDRESS_CONTACTNO_3 = "COMPANY_ADDRESS_CONTACTNO_3";
        
        public static final String COMPANY_STATUS = "COMPANY_STATUS";
        
        public static final String CREATE_DATE = "CREATE_DATE";
        
        public static final String MODIFIED_DATE = "MODIFIED_DATE";
        
        public static final String COMPANY_LOGO = "COMPANY_LOGO";
        
    }
    
    String CREATE_COMPANY_QUERY = " INSERT INTO " + TABLE + " (" + COLUMNS.COMPANY_ID + " , " + COLUMNS.COMPANY_NAME + ","
            + COLUMNS.COMPANY_ADDRESS_LINE_1 + " ," + COLUMNS.COMPANY_ADDRESS_LINE_2 + " ," + COLUMNS.COMPANY_ADDRESS_CITY + " ,"
            + COLUMNS.GEO_ID_1 + " ," + COLUMNS.GEO_ID_2 + " ," + COLUMNS.GEO_ID_3 + " ,"
            + COLUMNS.GEO_ID_4 + " ," + COLUMNS.GEO_ID_5 + " ," + COLUMNS.COMPANY_ADDRESS_PINCODE + " ,"
            + COLUMNS.COMPANY_ADDRESS_CONTACTNO + " ," + COLUMNS.COMPANY_ADDRESS_CONTACTNO_1 + " ," + COLUMNS.COMPANY_ADDRESS_CONTACTNO_2 + " ,"
            + COLUMNS.COMPANY_ADDRESS_CONTACTNO_3 + " ," + COLUMNS.COMPANY_STATUS+ " ," + COLUMNS.CREATE_DATE + " ,"
            + COLUMNS.MODIFIED_DATE + " ," + COLUMNS.COMPANY_LOGO + ") "
            + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,SYSDATE,?)";
    
    String GET_ACTIVE_COMPANY_NAMES= "SELECT " + COLUMNS.COMPANY_ID + " , " + COLUMNS.COMPANY_NAME +  " FROM " + TABLE + " WHERE " + COLUMNS.COMPANY_STATUS + " = 1 ";
    
    String DISABLE_COMPANY = "UPDATE " + TABLE + " SET " + COLUMNS.COMPANY_STATUS + " = 0 " + " WHERE " + COLUMNS.COMPANY_NAME + " =? ";
    
    String GET_COMPANY_LOGO= "SELECT " + COLUMNS.COMPANY_LOGO + " FROM " + TABLE + " WHERE " + COLUMNS.COMPANY_ID + " =? ";
    
    String GET_COMPANY_NAME= "SELECT " + COLUMNS.COMPANY_NAME + " FROM " + TABLE + " WHERE " + COLUMNS.COMPANY_ID + " =? ";
    
    String GET_COMPANY_ID= "SELECT " + COLUMNS.COMPANY_ID + " FROM " + TABLE + " ORDER BY " + COLUMNS.COMPANY_ID + " DESC ";

}
