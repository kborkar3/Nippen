package com.projectwork.impl.sql;

public interface MeterParameterLinkServiceSQLIfc
{
    String TABLE = "METER_PARAMETER_LINK";

    final class COLUMNS
    {
        public static final String METER_VERSION = "METER_VERSION";

        public static final String PARAMETER_INDEX = "PARAMETER_INDEX";
                
    }
             
    String GET_PARAMETER_INDEX_FROM_VERSION= "SELECT " + COLUMNS.PARAMETER_INDEX + " FROM " + TABLE + " WHERE " + COLUMNS.METER_VERSION + " = ? " ;
    
    String GET_METER_VERSION_FROM_INDEX  = "SELECT " + COLUMNS.METER_VERSION + " FROM " + TABLE + " WHERE " + COLUMNS.PARAMETER_INDEX + " = ? " ;
    
}
