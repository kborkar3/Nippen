package com.projectwork.impl.sql;

public interface UnitServiceSQLIfc
{
    String TABLE = "UNIT";

    final class COLUMNS
    {
        public static final String UNIT_ID = "UNIT_ID";

        public static final String UNIT_NAME = "UNIT_NAME";

        public static final String CREATE_DATE = "CREATE_DATE";

        public static final String MODIFIED_DATE = "MODIFIED_DATE";
    }

    String CREATE_UNIT_QUERY = " INSERT INTO " + TABLE + " (" + COLUMNS.UNIT_ID + " , " + COLUMNS.UNIT_NAME + " , "
            + COLUMNS.CREATE_DATE + " ," + COLUMNS.MODIFIED_DATE + ") " + " VALUES (?,?,SYSDATE,SYSDATE)";
    
    String GET_UNIT_NAME_FROM_ID = "SELECT " + COLUMNS.UNIT_NAME + " FROM " + TABLE + " WHERE "
            + COLUMNS.UNIT_ID + " = ? ";
    
    String GET_UNIT_ID_FROM_NAME = "SELECT " + COLUMNS.UNIT_ID + " FROM " + TABLE + " WHERE "
            + COLUMNS.UNIT_NAME + " = ? ";
    
    String GET_UNIT_ID_NAME_PAIRS = "SELECT " + COLUMNS.UNIT_ID + " , " + COLUMNS.UNIT_NAME + " FROM " + TABLE ;

}
