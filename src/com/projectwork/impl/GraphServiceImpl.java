package com.projectwork.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.projectwork.bean.DataTableBean;
import com.projectwork.impl.sql.DataTableServiceSQLIfc;

public class GraphServiceImpl extends DatabaseConnectionServiceImpl implements DataTableServiceSQLIfc
{
    private static Logger logger = Logger.getLogger(GraphServiceImpl.class);

    
    /**
     * This method will get meter details for selected feeder
     * 
     * @param String
     * @param String
     * @return List<DataTableBean>
     * @throws Exception
     */

    public ArrayList<DataTableBean> getGraphParameterData(String feeder, Date startDate, Date endDate)
    {
        ArrayList<DataTableBean> dataTableList = new ArrayList<DataTableBean>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        short recordFound = 0;
        Connection con = null;
        Statement stmt = null;
        String sql = null;
        
        try
        {
            logger.info("GraphServiceImpl >> getGraphParameterData");

            con = getConnection();
            stmt = con.createStatement();
            
            if(startDate.equals(endDate))
            {
                sql = GET_GRAPH_DATA_SAME_DAY;
                ps = con.prepareStatement(sql);
                
                ps.setDate(1, startDate);
                ps.setString(2, feeder);
            }
            else
            {
                sql = GET_GRAPH_DATA;
                ps = con.prepareStatement(sql);
                
                ps.setDate(1, startDate);
                ps.setDate(2, endDate);
                ps.setString(3, feeder);
            }
            
            rs = ps.executeQuery();

            while (rs.next())
            {
                DataTableBean bean = new DataTableBean();
                bean.setValue(rs.getInt(DataTableServiceSQLIfc.DATA_TABLE_COLUMNS.VAL));
                bean.setDateTimeStamp(getJAVADate(rs
                        .getTimestamp(DataTableServiceSQLIfc.DATA_TABLE_COLUMNS.DATE_TIME_STAMP)));
                bean.setParameterIndex(rs.getInt(DataTableServiceSQLIfc.DATA_TABLE_COLUMNS.PARAMETER_INDEX));

                dataTableList.add(bean);
                ++recordFound;
            }

            if (recordFound == 0)
            {
                logger.info("No records found for duration " + startDate + " and " + endDate);
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while searching for graph records " + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while searching for graph records" + e.getMessage());
        }

        finally
        {
            try
            {
                rs.close();
                stmt.close();
                con.close();
            }
            catch (SQLException e)
            {
                logger.error("Exception in closing DB resources");
            }
        }
        return dataTableList;
    }
}
