package com.projectwork.impl.sql;

public interface ParameterServiceSQLIfc
{
    String TABLE = "PARAMETER";

    final class COLUMNS
    {
        public static final String PARAMETER_INDEX = "PARAMETER_INDEX";

        public static final String PARAMETER_NAME = "PARAMETER_NAME";

        public static final String PARAMETER_RYGB = "PARAMETER_RYGB";

        public static final String PARAMETER_TYPE = "PARAMETER_TYPE";

        public static final String PARAMETER_FORMAT = "PARAMETER_FORMAT";

    }

    String GET_PARAMETER_DATA = "SELECT " + COLUMNS.PARAMETER_NAME + " FROM " + TABLE;

    String GET_PARAMETER_NAME = "SELECT " + COLUMNS.PARAMETER_NAME + " FROM " + TABLE + " WHERE "
            + COLUMNS.PARAMETER_INDEX + " = ? ";

    String GET_PARAMETER_DETAILS = "SELECT " + COLUMNS.PARAMETER_NAME + " , " + COLUMNS.PARAMETER_RYGB + " , "
            + COLUMNS.PARAMETER_TYPE + " , " + COLUMNS.PARAMETER_FORMAT + " FROM " + TABLE + " WHERE "
            + COLUMNS.PARAMETER_INDEX + " = ? ";
    
    String GET_PARAMETER_INDEX = "SELECT " + COLUMNS.PARAMETER_INDEX + " FROM " + TABLE + " WHERE "
            + COLUMNS.PARAMETER_NAME + " = ? ";

}
