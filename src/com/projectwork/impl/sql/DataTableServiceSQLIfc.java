package com.projectwork.impl.sql;

public interface DataTableServiceSQLIfc
{
    String DATA_TABLE = "DATA_TABLE";

    String FEEDER_PROFILE = "FEEDER_PROFILE";

    final class DATA_TABLE_COLUMNS
    {
        public static final String STORE_ID = "STORE_ID";

        public static final String METER_INDEX = "METER_INDEX";

        public static final String PARAMETER_INDEX = "PARAMETER_INDEX";

        public static final String VAL = "VAL";

        public static final String CONSUMPTION_VALUE = "CONSUMPTION_VALUE";

        public static final String DATA_TYPE = "DATA_TYPE";

        public static final String DATE_TIME_STAMP = "DATE_TIME_STAMP";

        public static final String CREATE_DATE = "CREATE_DATE";

    }

    final class FEEDER_PROFILE_COLUMNS
    {
        public static final String METER_VERSION = "METER_VERSION";

        public static final String METER_INDEX = "METER_INDEX";

        public static final String FEEDER_NAME = "FEEDER_NAME";

        public static final String METER_ID = "METER_ID";

        public static final String COMP_ID = "COMP_ID";

        public static final String UNIT_ID = "UNIT_ID";

    }

    String GET_GRAPH_DATA = "SELECT " + DATA_TABLE_COLUMNS.VAL + " , " + DATA_TABLE_COLUMNS.DATE_TIME_STAMP + " , " + DATA_TABLE_COLUMNS.PARAMETER_INDEX +
            " FROM " + FEEDER_PROFILE + " , "
            + DATA_TABLE + " WHERE SUBSTR ("
            + DATA_TABLE_COLUMNS.DATE_TIME_STAMP + ",1,9) BETWEEN ? AND ? " + " AND "
                    + FEEDER_PROFILE_COLUMNS.FEEDER_NAME + " =  ? " + " AND "
                    + " DATA_TABLE.METER_INDEX = FEEDER_PROFILE.METER_INDEX";
    
    String GET_GRAPH_DATA_SAME_DAY = "SELECT " + DATA_TABLE_COLUMNS.VAL + " , " + DATA_TABLE_COLUMNS.DATE_TIME_STAMP + " , " + DATA_TABLE_COLUMNS.PARAMETER_INDEX +
            " FROM " + FEEDER_PROFILE + " , "
            + DATA_TABLE + " WHERE SUBSTR ("
            + DATA_TABLE_COLUMNS.DATE_TIME_STAMP + ",1,9) = ? " + " AND "
                    + FEEDER_PROFILE_COLUMNS.FEEDER_NAME + " =  ? " + " AND "
                    + " DATA_TABLE.METER_INDEX = FEEDER_PROFILE.METER_INDEX";

    String GET_GRAPH_PARAMETER_DATA = "SELECT " + DATA_TABLE_COLUMNS.VAL + " , " + DATA_TABLE_COLUMNS.DATE_TIME_STAMP
            + " , " + DATA_TABLE_COLUMNS.PARAMETER_INDEX + " FROM " + FEEDER_PROFILE + " , "
            + DATA_TABLE + " WHERE SUBSTR ("
            + DATA_TABLE_COLUMNS.DATE_TIME_STAMP + ",1,9) BETWEEN ? AND ? " + " AND "
                    + FEEDER_PROFILE_COLUMNS.FEEDER_NAME + " =  ? " + " AND "
                    + " DATA_TABLE.METER_INDEX = FEEDER_PROFILE.METER_INDEX";

    String GET_HISTORIC_REPORT_DATA = " SELECT " + DATA_TABLE_COLUMNS.DATE_TIME_STAMP + " , "
            + FEEDER_PROFILE_COLUMNS.FEEDER_NAME + " , " + DATA_TABLE_COLUMNS.VAL + " FROM " + FEEDER_PROFILE + " , "
            + DATA_TABLE + " WHERE SUBSTR (" + DATA_TABLE_COLUMNS.DATE_TIME_STAMP + ",1,9) BETWEEN ? AND ? " + " AND "
            + FEEDER_PROFILE_COLUMNS.FEEDER_NAME + " =  ? " + " AND "
            + " DATA_TABLE.METER_INDEX = FEEDER_PROFILE.METER_INDEX";

    String GET_HISTORIC_REPORT_PARAMETER_DATA = " SELECT " + DATA_TABLE_COLUMNS.DATE_TIME_STAMP + " , "
            + FEEDER_PROFILE_COLUMNS.FEEDER_NAME + " , " + DATA_TABLE_COLUMNS.VAL + " FROM " + FEEDER_PROFILE + " , "
            + DATA_TABLE + " WHERE SUBSTR (" + DATA_TABLE_COLUMNS.DATE_TIME_STAMP + ",1,9) BETWEEN ? AND ? " + " AND "
            + FEEDER_PROFILE_COLUMNS.FEEDER_NAME + " =  ? " + " AND "
            + " DATA_TABLE.METER_INDEX = FEEDER_PROFILE.METER_INDEX" + " AND " + " DATA_TABLE.PARAMETER_INDEX = ?";

    String GET_REAL_TIME_DATA = " SELECT "
            + DATA_TABLE_COLUMNS.DATE_TIME_STAMP
            + " , "
            + DATA_TABLE_COLUMNS.VAL
            + " FROM "
            + FEEDER_PROFILE
            + " , "
            + DATA_TABLE
            + " WHERE  DATA_TABLE.METER_INDEX = FEEDER_PROFILE.METER_INDEX AND DATA_TABLE.STORE_ID = FEEDER_PROFILE.COMP_ID "
            + " AND " + DATA_TABLE_COLUMNS.PARAMETER_INDEX + " = ? " + " AND " + FEEDER_PROFILE_COLUMNS.FEEDER_NAME
            + " = ? " + " AND " + DATA_TABLE_COLUMNS.DATE_TIME_STAMP + " > = (SYSDATE - 0.1/24) " + " ORDER BY "
            + DATA_TABLE_COLUMNS.DATE_TIME_STAMP + " DESC ";

}
