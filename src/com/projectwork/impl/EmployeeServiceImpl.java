package com.projectwork.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.projectwork.impl.sql.EmployeeServiceSQLIfc;


public class EmployeeServiceImpl extends DatabaseConnectionServiceImpl implements EmployeeServiceSQLIfc
{
    private static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    /**
     * This method will get real time parameter values for selected meter
     * 
     * @param String
     * @param String
     * @param String
     * @param String
     * @param String
     * @param String
     * @param String
     * @param String
     * @param String
     * @param String
     * @param String
     * @param String
     * @return boolean
     * @throws Exception
     */
    
    public boolean addNewEmployee(String employeeID, String employeePassword, String employeeFirstName, String employeeLastName, String employeeAddress,
     String employeeCity, String employeeCountry, String pinCode, String contactNo, String roleIndex, String companyID, String employeeLocale)
    {
        boolean isRecordInserted = false;
        PreparedStatement ps = null;
        int rs = 0;
        Connection con = null;
        Statement stmt = null;

        String sql = null;
        
        String encryptedPassword = getDBLevelEncryptedString(employeePassword);
        
        try
        {
            logger.info("EmployeeServiceImpl >> addNewEmployee");
            
            con = getConnection();
            stmt = con.createStatement();

            sql = CREATE_EMPLOYEE_QUERY;
            ps = con.prepareStatement(sql);

            ps.setString(1, employeeID);
            ps.setString(2, encryptedPassword);
            ps.setString(3, employeeFirstName);
            ps.setString(4, employeeLastName);
            ps.setString(5, employeeLocale);
            ps.setString(6, employeeAddress);
            ps.setString(7, employeeAddress);
            ps.setString(8, employeeCity);
            ps.setString(9, employeeCountry);
            ps.setString(10, pinCode);
            ps.setString(11, contactNo);
            ps.setString(12, roleIndex);
            ps.setString(13, companyID);
            
            rs = ps.executeUpdate();
            
            if(rs==1)
            {
                isRecordInserted = true;
                logger.info("Record for Employee inserted successfully.");
            }
            else
            {
                logger.info("Record for Employee could not be inserted.");
            }
            
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while adding new employee " + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while adding new employee " + e.getMessage());
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

        return isRecordInserted;
    }
}
