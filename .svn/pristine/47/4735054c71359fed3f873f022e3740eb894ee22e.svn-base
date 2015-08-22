package com.projectwork.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.projectwork.bean.FeederBean;
import com.projectwork.bean.ParameterBean;
import com.projectwork.impl.sql.EmployeeServiceSQLIfc;
import com.projectwork.impl.sql.FeederProfileServiceSQLIfc;
import com.projectwork.impl.sql.MeterParameterLinkServiceSQLIfc;
import com.projectwork.impl.sql.ParameterServiceSQLIfc;

public class LoginServiceImpl extends DatabaseConnectionServiceImpl implements FeederProfileServiceSQLIfc,
        EmployeeServiceSQLIfc, ParameterServiceSQLIfc, MeterParameterLinkServiceSQLIfc
{
    private static Logger logger = Logger.getLogger(LoginServiceImpl.class);

    /**
     * This method will validate user credentials and will navigate to main
     * screen if validation is successful.
     * @param String
     * @param String
     * @return true: if validation is successful.
     * @throws Exception
     */

    public boolean validateLoginCredentials(String loginid, String password)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        String sql = null;

        boolean isUserPresent = false;

        String encryptedPassword = getDBLevelEncryptedString(password);

        try
        {
            logger.info("LoginServiceImpl >> validateLoginCredentials");

            con = getConnection();
            stmt = con.createStatement();

            sql = VALIDATE_USER;
            ps = con.prepareStatement(sql);

            ps.setString(1, loginid);
            ps.setString(2, encryptedPassword);

            rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count <= 0)
            {
                logger.error("Failed to determine if user " + loginid + " exits.");
            }
            else
            {
                logger.info("Validation for user : " + loginid + " is successful");
                isUserPresent = true;
            }

        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while validating user " + se.getMessage());
        }

        catch (Exception e)
        {
            logger.error("Exception occured while validating user " + e.getMessage());
        }

        finally
        {
            try
            {
                rs.close();
                stmt.close();
                con.close();
            }
            catch (Exception e)
            {
                logger.error("Exception in closing DB resources");
            }
        }
        return isUserPresent;
    }

    /**
     * This method will check if user is present in database.
     * 
     * @param String
     * @return true: if user is present.
     * @throws Exception
     */

    public boolean isUserPresent(String loginid)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        String sql = null;

        boolean isUserPresent = false;

        try
        {
            logger.info("LoginServiceImpl >> isUserPresent");

            con = getConnection();
            stmt = con.createStatement();

            sql = CHECK_USER;
            ps = con.prepareStatement(sql);

            ps.setString(1, loginid);

            rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count <= 0)
            {
                logger.error("User " + loginid + " does not exist.");
            }
            else
            {
                logger.info("User : " + loginid + " is not present in the database");
                isUserPresent = true;
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while validating user " + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Failed to determine if user " + loginid + " exits.");
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
        return isUserPresent;
    }
    
    
    
    /**
     * This method will check if user is present in database.
     * 
     * @param String
     * @return true: if user is present.
     * @throws Exception
     */

    public boolean isPasswordResetFlagPresent(String loginid)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        String sql = null;

        boolean isPasswordResetRequired = false;

        try
        {
            logger.info("LoginServiceImpl >> isUserPresent");

            con = getConnection();
            stmt = con.createStatement();

            sql = CHECK_PASSWORD_RESET_PIN;
            ps = con.prepareStatement(sql);

            ps.setString(1, loginid);

            rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count <= 0)
            {
                logger.error("User " + loginid + " does not exist.");
            }
            else
            {
                logger.info("User : " + loginid + " is not present in the database");
                isPasswordResetRequired = true;
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while validating user " + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Failed to determine if user " + loginid + " exits.");
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
        return isPasswordResetRequired;
    }
    
    
    /**
     * This method will set password reset flag to 1 if user has forgotten password. 
     * 
     * @param String
     * @return true: if user is present.
     * @throws Exception
     */

    public boolean setPasswordResetPin(String loginid, String temporayPassword)
    {
        PreparedStatement ps = null;
        int rs = 0;
        Connection con = null;
        Statement stmt = null;
        String sql = null;

        boolean isPasswordSet = false;
        
        String encryptedPassword = getDBLevelEncryptedString(temporayPassword);

        try
        {
            logger.info("LoginServiceImpl >> setPasswordResetPin");

            con = getConnection();
            stmt = con.createStatement();

            sql = SET_TEMPORARY_PASSWORD;
            ps = con.prepareStatement(sql);

            ps.setString(1, encryptedPassword);
            ps.setString(2, loginid);

            rs = ps.executeUpdate();

            if (rs == 1)
            {
                isPasswordSet = true;
                logger.info("Password reset pin set successfully");
            }
            else
            {
                logger.info("Password reset pin could not be updated.");
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while validating user " + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Failed to determine if user " + loginid + " exits.");
        }
        finally
        {
            try
            {
                stmt.close();
                con.close();
            }
            catch (SQLException e)
            {
                logger.error("Exception in closing DB resources");
            }
        }
        return isPasswordSet;
    }
    
    
    
    /**
     * This method will set password reset flag to 1 if user has forgotten password. 
     * 
     * @param String
     * @return true: if user is present.
     * @throws Exception
     */

    public boolean updateForgottenPassword(String loginid, String password)
    {
        PreparedStatement ps = null;
        int rs = 0;
        Connection con = null;
        Statement stmt = null;
        String sql = null;

        boolean isPasswordUpdated = false;
        
        String encryptedPassword = getDBLevelEncryptedString(password);

        try
        {
            logger.info("LoginServiceImpl >> updateForgottenPassword");

            con = getConnection();
            stmt = con.createStatement();

            sql = RESET_FORGOTTEN_PASSWORD;
            ps = con.prepareStatement(sql);

            ps.setString(1, encryptedPassword);
            ps.setString(2, loginid);

            rs = ps.executeUpdate();

            if (rs == 1)
            {
                isPasswordUpdated = true;
                logger.info("Password updated successfully");
            }
            else
            {
                logger.info("Password reset pin could not be updated.");
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while validating user " + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Failed to determine if user " + loginid + " exits.");
        }
        finally
        {
            try
            {
                stmt.close();
                con.close();
            }
            catch (SQLException e)
            {
                logger.error("Exception in closing DB resources");
            }
        }
        return isPasswordUpdated;
    }


    /**
     * This method gets feeder data
     * 
     * @param int
     * @return List<FeederBean>
     * @throws Exception
     */

    public List<FeederBean> getFeederData(int companyID)
    {
        List<FeederBean> feederList = new ArrayList<FeederBean>();

        PreparedStatement ps = null;
        ResultSet rs = null;
        short recordFound = 0;
        Connection con = null;
        Statement stmt = null;

        String sql = null;
        try
        {
            logger.info("LoginServiceImpl >> getFeederData");

            con = getConnection();
            stmt = con.createStatement();

            sql = GET_FEEDER_DATA;
            ps = con.prepareStatement(sql);
            ps.setInt(1, companyID);

            rs = ps.executeQuery();

            while (rs.next())
            {
                FeederBean bean = new FeederBean();
                bean.setFeeder(rs.getString(FeederProfileServiceSQLIfc.COLUMNS.FEEDER_NAME));
                feederList.add(bean);
                ++recordFound;
            }

            if (recordFound == 0)
            {
                logger.error("No Meters found");
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

        return feederList;
    }

    /**
     * This method gets parameter data
     * 
     * @param String
     * @param int
     * @return List<ParameterBean>
     * @throws Exception
     */

    public List<ParameterBean> getParameterData(String feederName, int companyID)
    {
        logger.info("LoginServiceImpl >> getParameterData");

        List<ParameterBean> parameterList = new ArrayList<ParameterBean>();
        List<Integer> meterParameterAssociateions = new ArrayList<Integer>();
        String parameterName = null;
        FeederServiceImpl feederServiceObj = new FeederServiceImpl();

        // Get Meter Version from FEEDER_PROFILE table

        long meterVersion = feederServiceObj.getMeterVersion(feederName, companyID);

        // Get Parameter Index from METER_PARAMETER_LINK table

        meterParameterAssociateions = getMeterParameterAssociations(meterVersion);

        // Get Parameter Name from PARAMETER table

        if (!(meterParameterAssociateions.isEmpty()))
        {
            int meterParameterAssociateionsSize = meterParameterAssociateions.size();

            for (int i = 0; i < meterParameterAssociateionsSize; i++)
            {
                ParameterBean bean = new ParameterBean();
                parameterName = getParameterName(meterParameterAssociateions.get(i));
                bean.setParameterName(parameterName);
                parameterList.add(bean);
            }
        }

        return parameterList;
    }
    
    /**
     * This method gets parameter name based on parameter index
     * 
     * @param Integer
     * @return String
     * @throws Exception
     */

    public String getParameterName(Integer parameterIndex)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        short recordFound = 0;
        Connection con = null;
        Statement stmt = null;
        String sql = null;
        String parameterName = null;

        try
        {
            logger.info("LoginServiceImpl >> getParameterName");

            con = getConnection();
            stmt = con.createStatement();

            sql = GET_PARAMETER_NAME;
            ps = con.prepareStatement(sql);
            ps.setInt(1, parameterIndex);

            rs = ps.executeQuery();

            while (rs.next())
            {
                parameterName = rs.getString(ParameterServiceSQLIfc.COLUMNS.PARAMETER_NAME);
                ++recordFound;
            }

            if (recordFound == 0)
            {
                logger.info("No parameters found for parameter index " + parameterIndex);
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while retrieving parameter name " + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while retrieving parameter name " + e.getMessage());
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
        return parameterName;
    }

    /**
     * This method gets parameter name based on parameter index
     * 
     * @param Integer
     * @return List<ParameterBean>
     * @throws Exception
     */
    
    public List<ParameterBean> getParameterDetails(Integer parameterIndex)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        short recordFound = 0;
        Connection con = null;
        Statement stmt = null;
        String sql = null;

        List<ParameterBean> parameterDetailsList = new ArrayList<ParameterBean>();

        try
        {
            logger.info("LoginServiceImpl >> getParameterDetails");

            con = getConnection();
            stmt = con.createStatement();

            sql = GET_PARAMETER_DETAILS;
            ps = con.prepareStatement(sql);
            ps.setInt(1, parameterIndex);

            rs = ps.executeQuery();

            while (rs.next())
            {
                ParameterBean bean = new ParameterBean();

                bean.setParameterName(rs.getString(ParameterServiceSQLIfc.COLUMNS.PARAMETER_NAME));
                bean.setParameterRYGB(rs.getString(ParameterServiceSQLIfc.COLUMNS.PARAMETER_RYGB));
                bean.setParameterType(rs.getString(ParameterServiceSQLIfc.COLUMNS.PARAMETER_TYPE));
                bean.setParameterFormat(rs.getString(ParameterServiceSQLIfc.COLUMNS.PARAMETER_FORMAT));

                parameterDetailsList.add(bean);
                ++recordFound;
            }

            if (recordFound == 0)
            {
                logger.info("No parameters found for parameter index " + parameterIndex);
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while retrieving parameter bean " + se.getMessage());
        }
        
        catch (Exception e)
        {
            logger.error("Exception occured while retrieving parameter bean " + e.getMessage());
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

        return parameterDetailsList;
    }
    
    /**
     * This method gets parameter associated with meter version
     * 
     * @param Integer
     * @return List<Integer>
     * @throws Exception
     */

    public List<Integer> getMeterParameterAssociations(double meterVersion)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        String sql = null;
        int parameterIndex = 0;

        List<Integer> meterParameterAssociateions = new ArrayList<Integer>();

        try
        {
            logger.info("LoginServiceImpl >> getMeterParameterAssociateions");
            
            con = getConnection();
            stmt = con.createStatement();

            sql = GET_PARAMETER_INDEX_FROM_VERSION;

            ps = con.prepareStatement(sql);
            ps.setDouble(1, meterVersion);

            rs = ps.executeQuery();

            while (rs.next())
            {
                parameterIndex = rs.getInt(1);
                meterParameterAssociateions.add(parameterIndex);
            }

            if (meterParameterAssociateions.size() == 0)
            {
                logger.error("No Meter parameter associations found");
                return null;
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while retrieving parameter bean " + se.getMessage());
        }
        
        catch (Exception e)
        {
            logger.error("Exception occured while retrieving parameter bean " + e.getMessage());
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
    
    
    /**
     * This method gets company ID for logged in user
     * 
     * @param String
     * @return int
     * @throws Exception
     */

    public int getCompanyID(String loginid)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        String sql = null;

        int companyID = 0;

        try
        {
            logger.info("LoginServiceImpl >> getCompanyID");
            
            con = getConnection();
            stmt = con.createStatement();

            sql = GET_COMPANY_ID;
            ps = con.prepareStatement(sql);

            ps.setString(1, loginid);

            rs = ps.executeQuery();

            while (rs.next())
            {
                companyID = rs.getInt(1);
                logger.info("Company ID for User : " + loginid + " is " + companyID);
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while retrieving company ID for logged in user " + se.getMessage());
        }
        
        catch (Exception e)
        {
            logger.error("Exception occured while retrieving company ID for logged in user " + e.getMessage());
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
        return companyID;
    }

   
}
