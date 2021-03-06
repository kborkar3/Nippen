package com.projectwork.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.iss.ketan.util.FormatData;
import com.projectwork.bean.DataTableBean;
import com.projectwork.bean.FeederBean;
import com.projectwork.bean.ParameterBean;
import com.projectwork.impl.sql.DataTableServiceSQLIfc;
import com.projectwork.impl.sql.FeederProfileServiceSQLIfc;
import com.projectwork.impl.sql.MeterParameterLinkServiceSQLIfc;
import com.projectwork.impl.sql.ParameterServiceSQLIfc;

public class FeederServiceImpl extends DatabaseConnectionServiceImpl implements FeederProfileServiceSQLIfc,
        ParameterServiceSQLIfc, MeterParameterLinkServiceSQLIfc, DataTableServiceSQLIfc
{
    private static Logger logger = Logger.getLogger(FeederServiceImpl.class);
    
    /**
     * This method will get real time parameter values for selected meter
     * 
     * @param String
     * @param int
     * @return List<ParameterBean>
     * @throws Exception
     */
    
    public List<ParameterBean> getParameterData(String feederName, int companyID)
    {
        logger.info("FeederServiceImpl >> getParameterData");

        List<ParameterBean> parameterList = new ArrayList<ParameterBean>();
        List<ParameterBean> parameterDetailsList = new ArrayList<ParameterBean>();
        List<Integer> meterParameterAssociateions = new ArrayList<Integer>();
        String formatedParameterValue = null;
        Integer meterVersion = 0;
        
        LoginServiceImpl loginObject = new LoginServiceImpl();

        // Get Meter Version from FEEDER_PROFILE table

        ArrayList<FeederBean> meterDetails = getMeterDetails(feederName, companyID);

        if (!(meterDetails.isEmpty()))
        {
            meterVersion = meterDetails.get(0).getMeterVersion();

            // Get Parameter Index from METER_PARAMETER_LINK table

            meterParameterAssociateions = loginObject.getMeterParameterAssociations(meterVersion);

            // Get Parameter Name from PARAMETER table

            if (!(meterParameterAssociateions.isEmpty()))
            {
                
                Iterator<Integer> iterator = meterParameterAssociateions.iterator();

                while (iterator.hasNext())
                {
                    Integer parameterIndex = iterator.next();
                    ParameterBean bean = new ParameterBean();

                    // Get Real Time Data to be displayed on Meter Display page

                    DataTableBean dataTablebean = getRealTimeData(parameterIndex, feederName,
                            companyID);
                    
                    if(!(dataTablebean == null))
                    {
                        if (dataTablebean.getDateTimeStamp() != null)
                        {
                            // Get parameter details for retrieved parameter index
                            
                            parameterDetailsList = loginObject.getParameterDetails(parameterIndex);
                            bean.setParameterName(parameterDetailsList.get(0).getParameterName());
                            bean.setParameterRYGB(parameterDetailsList.get(0).getParameterRYGB());
                            bean.setParameterType(parameterDetailsList.get(0).getParameterType());
                            bean.setParameterFormat(parameterDetailsList.get(0).getParameterFormat());
                            bean.setDateTimeStamp(dataTablebean.getDateTimeStamp());
                            
                            String paramFormat = parameterDetailsList.get(0).getParameterFormat();
                            
                            if (paramFormat.equals("P"))
                            {
                                formatedParameterValue = FormatData.formatValue(
                                        Float.toString(dataTablebean.getValue()), "0.000", true);
                                Float floatedValue = Float.parseFloat(formatedParameterValue);
                                bean.setParameterValue(floatedValue);
                            }
                            else
                            {
                                bean.setParameterValue(dataTablebean.getValue());
                            }
                            
                            parameterList.add(bean);
                        }
                    }

                }
            }
        }

        return parameterList;
    }
    
    /**
     * This method will get meter version based on feeder name and company ID
     * 
     * @param String
     * @param int
     * @return Integer
     * @throws Exception
     */

    public long getMeterVersion(String feederName, int companyID)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        short recordFound = 0;
        Connection con = null;
        Statement stmt = null; 
        long meterVersion = 0;

        String sql = null;
        try
        {
            logger.info("FeederServiceImpl >> getMeterVersion");

            con = getConnection();
            stmt = con.createStatement();

            sql = GET_METER_VERSION;

            ps = con.prepareStatement(sql);
            ps.setString(1, feederName);
            ps.setInt(2, companyID);

            rs = ps.executeQuery();

            while (rs.next())
            {
                meterVersion = rs.getLong(FeederProfileServiceSQLIfc.COLUMNS.METER_VERSION);
                ++recordFound;
            }

            if (recordFound == 0)
            {
                logger.error("No Meters found");
                return -1;
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while retrieving meter version " + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while retrieving meter version " + e.getMessage());
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
        return meterVersion;
    }
    
    /**
     * This method will get meter details for selected feeder
     * 
     * @param String
     * @param int
     * @return List<ParameterBean>
     * @throws Exception
     */

    public ArrayList<FeederBean> getMeterDetails(String feederName, int companyID)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        short recordFound = 0;
        Connection con = null;
        Statement stmt = null;

        ArrayList<FeederBean> meterDetails = new ArrayList<FeederBean>();

        String sql = null;
        try
        {
            logger.info("FeederServiceImpl >> getMeterDetails");

            con = getConnection();
            stmt = con.createStatement();

            sql = GET_METER_DETAILS;

            ps = con.prepareStatement(sql);
            ps.setString(1, feederName);
            ps.setInt(2, companyID);

            rs = ps.executeQuery();

            while (rs.next())
            {
                FeederBean bean = new FeederBean();

                bean.setMeterVersion(rs.getInt(FeederProfileServiceSQLIfc.COLUMNS.METER_VERSION));
                bean.setMeterIndex(rs.getInt(FeederProfileServiceSQLIfc.COLUMNS.METER_INDEX));
                bean.setCompanyID(rs.getInt(FeederProfileServiceSQLIfc.COLUMNS.COMP_ID));
                bean.setMeterID(rs.getInt(FeederProfileServiceSQLIfc.COLUMNS.METER_ID));

                meterDetails.add(bean);

                ++recordFound;
            }

            if (recordFound == 0)
            {
                logger.info("No Meters found");
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while retrieving meter version " + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while retrieving meter version " + e.getMessage());
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

        return meterDetails;
    }
    
    /**
     * This method will get real time data for selected meter for each parameter
     * 
     * @param Integer
     * @param String
     * @param int
     * @return DataTableBean
     * @throws Exception
     */

    private DataTableBean getRealTimeData(Integer parameterIndex, String feederName, int companyID)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        short recordFound = 0;
        Connection con = null;
        Statement stmt = null;
        String sql = null;

        DataTableBean bean = new DataTableBean();

        try
        {
            logger.info("FeederServiceImpl >> getRealTimeData");

            con = getConnection();
            stmt = con.createStatement();

            sql = GET_REAL_TIME_DATA;

            ps = con.prepareStatement(sql);
            ps.setInt(1, parameterIndex);
            ps.setString(2, feederName);

            rs = ps.executeQuery();

            while (rs.next())
            {
                bean = new DataTableBean();

                bean.setValue(rs.getInt(DataTableServiceSQLIfc.DATA_TABLE_COLUMNS.VAL));
                bean.setDateTimeStamp(getJAVADate(rs
                        .getTimestamp(DataTableServiceSQLIfc.DATA_TABLE_COLUMNS.DATE_TIME_STAMP)));

                recordFound++;
            }

            if (recordFound == 0)
            {
                logger.error("Feeder data not found");
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while retrieving feeder data " + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while retrieving feeder data " + e.getMessage());
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
        return bean;
    }

    
    /**
     * This method will get list of parameter indexes for list of selected parameters
     * 
     * @param List<String>
     * @return List<String>
     * @throws Exception
     */

    public List<String> getParameterIndexesList(List<String> selectedParametersList)
    {
        logger.info("FeederServiceImpl >> getParameterIndexesList");

        List<String> selectedParameterIndexesList = new ArrayList<String>();
        String selectedParameterIndex = null;
        
        Iterator<String> iterator = selectedParametersList.iterator();
        while (iterator.hasNext())
        {
            String parameterName = iterator.next();
            selectedParameterIndex = getParameterIndex(parameterName);
            selectedParameterIndexesList.add(selectedParameterIndex);
        }
        
        return selectedParameterIndexesList;
    }
    
    /**
     * This method will get parameter indexes for parameters name
     * 
     * @param String
     * @return String
     * @throws Exception
     */

    private String getParameterIndex(String parameterName)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        short recordFound = 0;
        Connection con = null;
        Statement stmt = null;
        String sql = null;
        String parameterIndex = null;

        try
        {
            logger.info("FeederServiceImpl >> getParameterIndex");

            con = getConnection();
            stmt = con.createStatement();

            sql = GET_PARAMETER_INDEX;
            ps = con.prepareStatement(sql);
            ps.setString(1, parameterName);

            rs = ps.executeQuery();

            while (rs.next())
            {
                parameterIndex = rs.getString(ParameterServiceSQLIfc.COLUMNS.PARAMETER_INDEX);
                ++recordFound;
            }

            if (recordFound == 0)
            {
                logger.error("Parameter index not available for parameter "+parameterName);
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while retrieving parameter index " + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while retrieving parameter index " + e.getMessage());
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

        return parameterIndex;
    }
    
    
    /**
     * This method will get parameter indexes for parameters name
     * 
     * @param String
     * @return List<Integer>
     * @throws Exception
     */

    public List<Integer> getMeterVersionFromIndex(Integer parameterIndex)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        String sql = null;
        int meterVersion = 0;

        List<Integer> meterParameterAssociateions = new ArrayList<Integer>();

        try
        {

            logger.info("FeederServiceImpl >> getMeterVersionFromIndex");

            con = getConnection();
            stmt = con.createStatement();

            sql = GET_METER_VERSION_FROM_INDEX;

            ps = con.prepareStatement(sql);
            ps.setInt(1, parameterIndex);

            rs = ps.executeQuery();

            while (rs.next())
            {
                meterVersion = rs.getInt(1);
                meterParameterAssociateions.add(meterVersion);
            }

            if (meterParameterAssociateions.size() == 0)
            {
                logger.error("Meter version not available for parameter index " + parameterIndex);
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while retrieving parameter index " + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while retrieving parameter index " + e.getMessage());
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

        return meterParameterAssociateions;
    }

}
