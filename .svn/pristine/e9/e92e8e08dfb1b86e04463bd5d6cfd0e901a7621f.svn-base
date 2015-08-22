package com.projectwork.impl.sql;

public interface UnitCompanyLinkServicesSQLIfc
{
    String TABLE = "COMPANY_UNIT_LINK";

    final class COLUMNS
    {
        public static final String UNIT_ID = "UNIT_ID";

        public static final String COMPANY_ID = "COMPANY_ID";

        public static final String CREATE_DATE = "CREATE_DATE";

        public static final String MODIFIED_DATE = "MODIFIED_DATE";
    }

    String CREATE_COMPANY_UNIT_LINK_QUERY = " INSERT INTO " + TABLE + " (" + COLUMNS.UNIT_ID + " , " + COLUMNS.COMPANY_ID + " , "
            + COLUMNS.CREATE_DATE + " ," + COLUMNS.MODIFIED_DATE + ") " + " VALUES (?,?,SYSDATE,SYSDATE)";
    
    String GET_UNITS_ASSIGNED_TO_COMPANY = "SELECT " + COLUMNS.UNIT_ID +  " FROM " + TABLE + " WHERE " + COLUMNS.COMPANY_ID + " = ? ";
    
    String REMOVE_UNITS_ASSIGNED_TO_COMPANY = "DELETE FROM " + TABLE + " WHERE " + COLUMNS.UNIT_ID + " = ?  AND " + COLUMNS.COMPANY_ID +  " = ? ";
    
    String REMOVE_ALL_UNITS_ASSIGNED_TO_COMPANY = "DELETE FROM " + TABLE + " WHERE " + COLUMNS.COMPANY_ID +  " = ? ";
}

