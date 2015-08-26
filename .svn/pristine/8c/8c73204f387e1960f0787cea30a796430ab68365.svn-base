package com.projectwork.impl.sql;

public interface FeederProfileServiceSQLIfc
{

    String TABLE = "FEEDER_PROFILE";

    final class COLUMNS
    {
        public static final String METER_VERSION = "METER_VERSION";

        public static final String METER_INDEX = "METER_INDEX";

        public static final String FEEDER_NAME = "FEEDER_NAME";

        public static final String METER_ID = "METER_ID";

        public static final String COMP_ID = "COMP_ID";

        public static final String UNIT_ID = "UNIT_ID";

    }

    String GET_FEEDER_DATA = "SELECT " + COLUMNS.FEEDER_NAME + " FROM " + TABLE + " WHERE " + COLUMNS.COMP_ID + " = ? ";

    String GET_METER_VERSION = "SELECT " + COLUMNS.METER_VERSION + " FROM " + TABLE + " WHERE " + COLUMNS.FEEDER_NAME
            + "= ? " + " AND " + COLUMNS.COMP_ID + " =  ? ";

    String GET_METER_DETAILS = "SELECT " + COLUMNS.METER_INDEX + " , " + COLUMNS.METER_VERSION + " , "
            + COLUMNS.METER_ID + " , " + COLUMNS.COMP_ID + " FROM " + TABLE + " WHERE " + COLUMNS.FEEDER_NAME + "= ? "
            + " AND " + COLUMNS.COMP_ID + " =  ? ";

}
