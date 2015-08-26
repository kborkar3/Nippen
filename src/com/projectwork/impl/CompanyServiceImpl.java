package com.projectwork.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.projectwork.bean.CompanyBean;
import com.projectwork.impl.sql.CompanyServiceSQLIfc;

public class CompanyServiceImpl extends DatabaseConnectionServiceImpl implements CompanyServiceSQLIfc
{
    private static Logger logger = Logger.getLogger(CompanyServiceImpl.class);
    
    /**
     * This method will add new company
     * 
     * @param String
     * @param String
     * @param String
     * @param String
     * @param String
     * @param int
     * @param String
     * @param String
     * @return boolean
     * @throws Exception
     */
    
    public boolean addNewCompany(String companyID, String companyName, String companyAddress, String companyCity,
            String geoID, Integer pinCode, String contactNo1, String contactNo2, File companyLogo)
    {
        boolean isRecordInserted = false;
        PreparedStatement ps = null;
        int rs = 0;
        Connection con = null;
        Statement stmt = null;

        String sql = null;
        try
        {
            logger.info("CompanyServiceImpl >> addNewCompany");
            
            con = getConnection();
            stmt = con.createStatement();

            sql = CREATE_COMPANY_QUERY;
            ps = con.prepareStatement(sql);

            ps.setString(1, companyID);
            ps.setString(2, companyName);
            ps.setString(3, companyAddress);
            ps.setString(4, "");
            ps.setString(5, "");
            ps.setString(6, geoID);
            ps.setString(7, geoID);
            ps.setString(8, geoID);
            ps.setString(9, geoID);
            ps.setString(10, geoID);
            ps.setInt(11, pinCode);
            ps.setString(12, contactNo1);
            ps.setString(13, contactNo2);
            ps.setString(14, contactNo2);
            ps.setString(15, contactNo2);
            ps.setInt(16, 1);

            InputStream is = null;
            is = new FileInputStream(companyLogo);
            ps.setBinaryStream(17, is);

            rs = ps.executeUpdate();

            if (rs == 1)
            {
                isRecordInserted = true;
                logger.info("Company added successfully.");
            }
            else
            {
                logger.info("Record for company could not be inserted.");
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while adding new company " + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while adding new company " + e.getMessage());
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

    /**
     * This method will get all company names
     * 
     * @param 
     * @return List<CompanyBean>
     * @throws Exception
     */
    
    public List<CompanyBean> getCompanyNames()
    {
        List<CompanyBean> companyList = new ArrayList<CompanyBean>();

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        short recordFound = 0;

        String sql = null;
        try
        {
            logger.info("CompanyServiceImpl >> getCompanyNames");
            
            con = getConnection();
            stmt = con.createStatement();

            sql = GET_ACTIVE_COMPANY_NAMES;
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next())
            {
                CompanyBean bean = new CompanyBean();
                bean.setCompanyID((rs.getInt(CompanyServiceSQLIfc.COLUMNS.COMPANY_ID)));
                bean.setCompanyName((rs.getString(CompanyServiceSQLIfc.COLUMNS.COMPANY_NAME)));
                companyList.add(bean);
                ++recordFound;
            }

            if (recordFound == 0)
            {
                companyList = null;
                logger.info("No active company found");
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while retrieving company records" + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while retrieving company records " + e.getMessage());
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

        return companyList;
    }
    
    /**
     * This method will disable selected company
     * 
     * @param String
     * @return boolean
     * @throws Exception
     */

    public boolean disableCompany(String companyName)
    {

        boolean isCompanyDisabled = false;

        PreparedStatement ps = null;
        int rs = 0;
        Connection con = null;
        Statement stmt = null;

        String sql = null;
        try
        {
            logger.info("CompanyServiceImpl >> disableCompany");
            
            con = getConnection();
            stmt = con.createStatement();

            sql = DISABLE_COMPANY;
            ps = con.prepareStatement(sql);

            ps.setString(1, companyName);

            rs = ps.executeUpdate();

            if (rs == 1)
            {
                isCompanyDisabled = true;
                logger.info("Company disabled successfully.");
            }
            else
            {
                logger.info("Company records could not be found for disabeling.");
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while disabling company records" + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while disabling company records " + e.getMessage());
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

        return isCompanyDisabled;
    }
    
    
    /**
     * This method will write company logo in a temporary image file for displaying in header
     * 
     * @param int
     * @param String
     * @return String
     * @throws Exception
     */

    public boolean getCompanyLogo(int companyID,String fullpath)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        String sql = null;
        boolean isLogoCreated = false;

        try
        {
            logger.info("CompanyServiceImpl >> getCompanyLogo");
            
            con = getConnection();
            stmt = con.createStatement();

            sql = GET_COMPANY_LOGO;
            ps = con.prepareStatement(sql);

            ps.setInt(1, companyID);

            rs = ps.executeQuery();

            while (rs.next())
            {
                Blob b = rs.getBlob(1);
                byte barr[] = b.getBytes(1, (int)b.length());
                
                File file = new File(fullpath);
                FileOutputStream fout = new FileOutputStream(file);
                fout.write(barr);
                fout.close();
                
                isLogoCreated = true;
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while getting company logo" + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while getting company logo" + e.getMessage());
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
        return isLogoCreated;
    }
    
    
    /**
     * This method will get latest company ID for generating next one
     * 
     * @return int
     * @throws Exception
     */

    public int getNextCompanyID()
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        String sql = null;
        int nextCompanyID=0;

        try
        {
            logger.info("CompanyServiceImpl >> getLatestCompanyID");
            
            con = getConnection();
            stmt = con.createStatement();

            sql = GET_COMPANY_ID;
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            if (rs.next())
            {
                nextCompanyID = rs.getInt(CompanyServiceSQLIfc.COLUMNS.COMPANY_ID);
                nextCompanyID++;
            }
            else
            {
                nextCompanyID = 1;
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while getting company logo" + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while getting company logo" + e.getMessage());
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
        return nextCompanyID;
    }
    
    
    /**
     * This method will get company name
     * 
     * @param int
     * @param String
     * @return String
     * @throws Exception
     */

    public String getCompanyNameDetails(int companyID)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        String sql = null;
        
        String companyName= null;
        
        try
        {
            logger.info("CompanyServiceImpl >> getCompanyNameDetails");
            
            con = getConnection();
            stmt = con.createStatement();

            sql = GET_COMPANY_NAME;
            ps = con.prepareStatement(sql);

            ps.setInt(1, companyID);

            rs = ps.executeQuery();

            while (rs.next())
            {
                companyName = rs.getString(CompanyServiceSQLIfc.COLUMNS.COMPANY_NAME);
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while getting company logo" + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while getting company logo" + e.getMessage());
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
        return companyName;
    }
}
