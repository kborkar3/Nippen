package com.projectwork.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.projectwork.bean.UnitBean;
import com.projectwork.impl.sql.UnitCompanyLinkServicesSQLIfc;
import com.projectwork.impl.sql.UnitServiceSQLIfc;

public class UnitServiceImpl extends DatabaseConnectionServiceImpl implements UnitServiceSQLIfc,
        UnitCompanyLinkServicesSQLIfc
{
    private static Logger logger = Logger.getLogger(UnitServiceImpl.class);

    /**
     * This method will check for company-Unit associations and return Unit
     * names for configure Unit page
     * 
     * @param String
     * @return List
     * @throws Exception
     */

    public List<String> getAssignedUnitNamesforCompany(int companyID)
    {
        List<String> assignedUnitIDsforCompany = new ArrayList<String>();
        assignedUnitIDsforCompany = getAssignedUnitsforCompany(companyID);

        String unitName = null;
        List<String> assignedUnitNamesforCompany = new ArrayList<String>();

        int assignedUnitIDsforCompanySize = assignedUnitIDsforCompany.size();

        for (int i = 0; i < assignedUnitIDsforCompanySize; i++)
        {
            unitName = getUnitNamesFromID(assignedUnitIDsforCompany.get(i));
            assignedUnitNamesforCompany.add(unitName);
        }

        return assignedUnitNamesforCompany;
    }

    /**
     * This method will return Unit names which are not assigned to company for
     * configure Unit page
     * 
     * @param String
     * @return List
     * @throws Exception
     */

    public List<String> getUnAssignedUnitNamesforCompany(int companyID)
    {
        List<String> unAssignedUnitNamesforCompany = new ArrayList<String>();

        // Get all Unit IDs and their names
        List<UnitBean> unitIDNamesList = new ArrayList<UnitBean>();
        unitIDNamesList = getUnitIDNamesList();

        // Get all Unit IDs associated with company

        List<String> assignedUnitIDsforCompany = new ArrayList<String>();
        assignedUnitIDsforCompany = getAssignedUnitsforCompany(companyID);

        int unitIDNamesListSize = unitIDNamesList.size();

        for (int i = 0; i < unitIDNamesListSize; i++)
        {
            if (!(assignedUnitIDsforCompany.contains(unitIDNamesList.get(i).getUnitID())))
            {
                unAssignedUnitNamesforCompany.add(unitIDNamesList.get(i).getUnitName());
            }
        }

        return unAssignedUnitNamesforCompany;
    }

    /**
     * This method will check for company-Unit associations and return Unit
     * names for configure Unit page
     * 
     * @param int
     * @param List<String>
     * @param List<String>
     * @return boolean
     * @throws Exception
     */

    public boolean updateAssignedUnitsForCompany(int companyID, List<String> leftUnitList)
    {
        boolean isUnitUpdated = false;
        boolean isNewMappingAdded= false;
        boolean isExistingMappingRemoved= false;
        
        List<String> assignedUnitIDsforCompany = new ArrayList<String>();
        List<String> leftUnitIDNamesPairList = new ArrayList<String>();
        String unitID = null;

        // 1. Get currently assigned units ids
        assignedUnitIDsforCompany = getAssignedUnitsforCompany(companyID); 

        if(leftUnitList.isEmpty())
        {
            isExistingMappingRemoved = removeAllExistingUnitCompanyLink(companyID);
            return isExistingMappingRemoved;
        }
        
        // 2. Get unit ids for left list
        
        for (int i = 0; i < leftUnitList.size(); i++)
        {
            unitID = getUnitIDFromName(leftUnitList.get(i));
            leftUnitIDNamesPairList.add(unitID); 
        }

        // 3. Update existing associations with new ones.

        // 3.1 Add new mappings if associations are added

        Set<String> set1 = new HashSet<String>();
        Set<String> set2 = new HashSet<String>();
        
        set1.addAll(assignedUnitIDsforCompany);
        set2.addAll(leftUnitIDNamesPairList);
        
        set2.removeAll(set1);

        for (String unit : set2)
        {
            isNewMappingAdded = addNewUnitCompanyLink(unit, companyID);
            
            if(!isNewMappingAdded)
            {
                break;
            }
            
            isUnitUpdated = true;
        }

        // 3.2 Remove mappings if associations are removed

        set1 = new HashSet<String>();
        set2 = new HashSet<String>();

        set1.addAll(assignedUnitIDsforCompany);
        set2.addAll(leftUnitIDNamesPairList);
        
        set1.removeAll(set2);
        
        for (String unit : set1)
        {
            isExistingMappingRemoved = removeExistingUnitCompanyLink(unit, companyID);
            
            if(!isExistingMappingRemoved)
            {
                break;
            }
            
            isUnitUpdated = true;
        }

        return isUnitUpdated;
    }

    /**
     * This method will add new company
     * 
     * @param String
     * @param String
     * @return boolean
     * @throws Exception
     */
    public boolean addNewUnit(String unitID, String unitName)
    {
        boolean isRecordInserted = false;
        PreparedStatement ps = null;
        int rs = 0;
        Connection con = null;
        Statement stmt = null;

        String sql = null;

        try
        {
            logger.info("UnitServiceImpl >> addNewUnit");

            con = getConnection();
            stmt = con.createStatement();

            sql = CREATE_UNIT_QUERY;
            ps = con.prepareStatement(sql);

            ps.setString(1, unitID);
            ps.setString(2, unitName);

            rs = ps.executeUpdate();

            if (rs == 1)
            {
                isRecordInserted = true;
                logger.info("Record for Unit inserted successfully.");
            }
            else
            {
                logger.info("Record for Unit could not be inserted.");
            }

        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while adding new unit " + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while adding new unit" + e.getMessage());
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
     * This method will add new company-unit association
     * 
     * @param String
     * @param int
     * @return boolean
     * @throws Exception
     */
    public boolean addNewUnitCompanyLink(String unitID, int companyID)
    {
        boolean isRecordInserted = false;
        PreparedStatement ps = null;
        int rs = 0;
        Connection con = null;
        Statement stmt = null;

        String sql = null;

        try
        {
            logger.info("UnitServiceImpl >> addNewUnitCompanyLink");

            con = getConnection();
            stmt = con.createStatement();

            sql = CREATE_COMPANY_UNIT_LINK_QUERY;
            ps = con.prepareStatement(sql);

            ps.setString(1, unitID);
            ps.setInt(2, companyID);

            rs = ps.executeUpdate();

            if (rs == 1)
            {
                isRecordInserted = true;
                logger.info("Record for Unit inserted successfully.");
            }
            else
            {
                logger.info("Record for Unit could not be inserted.");
            }

        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while adding new unit " + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while adding new unit" + e.getMessage());
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
     * This method will remove existing company-unit association for given unit id and company id
     * 
     * @param int
     * @return boolean
     * @throws Exception
     */
    public boolean removeAllExistingUnitCompanyLink(int companyID)
    {
        boolean isRecordRemoved = false;
        PreparedStatement ps = null;
        int rs = 0;
        Connection con = null;
        Statement stmt = null;

        String sql = null;

        try
        {
            logger.info("UnitServiceImpl >> removeExistingUnitCompanyLink");

            con = getConnection();
            stmt = con.createStatement();

            sql = REMOVE_ALL_UNITS_ASSIGNED_TO_COMPANY;
            ps = con.prepareStatement(sql);

            ps.setInt(1, companyID);

            rs = ps.executeUpdate();

            if (rs == 1)
            {
                isRecordRemoved = true;
                logger.info("Record for Unit removed successfully.");
            }
            else
            {
                logger.info("Record for Unit could not be removed.");
            }

        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while removing mapping " + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while removing mapping" + e.getMessage());
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
        return isRecordRemoved;
    }
    
    
    /**
     * This method will remove existing company-unit association for given unit id and company id
     * 
     * @param String
     * @param int
     * @return boolean
     * @throws Exception
     */
    public boolean removeExistingUnitCompanyLink(String unitID, int companyID)
    {
        boolean isRecordRemoved = false;
        PreparedStatement ps = null;
        int rs = 0;
        Connection con = null;
        Statement stmt = null;

        String sql = null;

        try
        {
            logger.info("UnitServiceImpl >> removeExistingUnitCompanyLink");

            con = getConnection();
            stmt = con.createStatement();

            sql = REMOVE_UNITS_ASSIGNED_TO_COMPANY;
            ps = con.prepareStatement(sql);

            ps.setString(1, unitID);
            ps.setInt(2, companyID);

            rs = ps.executeUpdate();

            if (rs == 1)
            {
                isRecordRemoved = true;
                logger.info("Record for Unit removed successfully.");
            }
            else
            {
                logger.info("Record for Unit could not be removed.");
            }

        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while removing mapping " + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while removing mapping" + e.getMessage());
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
        return isRecordRemoved;
    }

    /**
     * This method will get Unit ID associated with Company from Company-Unit
     * link table
     * 
     * @param String
     * @param String
     * @return boolean
     * @throws Exception
     */
    public List<String> getAssignedUnitsforCompany(int companyID)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        String sql = null;
        short recordFound = 0;

        List<String> assignedUnitsforCompany = new ArrayList<String>();

        try
        {
            logger.info("UnitServiceImpl >> getAssignedUnitsforCompany");

            con = getConnection();
            stmt = con.createStatement();

            sql = GET_UNITS_ASSIGNED_TO_COMPANY;
            ps = con.prepareStatement(sql);

            ps.setInt(1, companyID);

            rs = ps.executeQuery();

            while (rs.next())
            {
                assignedUnitsforCompany.add(rs.getString(UnitCompanyLinkServicesSQLIfc.COLUMNS.UNIT_ID));
                ++recordFound;
            }

            if (recordFound == 0)
            {
                logger.info("No units are assigned for the company");
            }

        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while checking units assigned to company" + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while checking units assigned to company" + e.getMessage());
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
        return assignedUnitsforCompany;
    }

    /**
     * This method will get Unit Names for Unit IDs
     * 
     * @param String
     * @return String
     * @throws Exception
     */
    public String getUnitNamesFromID(String unitID)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        String sql = null;
        short recordFound = 0;
        String unitName = null;

        try
        {
            logger.info("UnitServiceImpl >> getUnitNamesList");

            con = getConnection();
            stmt = con.createStatement();

            sql = GET_UNIT_NAME_FROM_ID;
            ps = con.prepareStatement(sql);

            ps.setString(1, unitID);

            rs = ps.executeQuery();

            if (rs.next())
            {
                unitName = rs.getString(UnitServiceSQLIfc.COLUMNS.UNIT_NAME);
                ++recordFound;
            }

            if (recordFound == 0)
            {
                logger.info("Unit name not found for unit " + unitID);
            }

        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while getting Unit name" + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while getting Unit name" + e.getMessage());
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
        return unitName;
    }

    /**
     * This method will get Unit IDs for Unit Names
     * 
     * @param String
     * @return String
     * @throws Exception
     */
    public String getUnitIDFromName(String unitName)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        String sql = null;
        short recordFound = 0;
        String unitID = null;

        try
        {
            logger.info("UnitServiceImpl >> getUnitIDFromName");

            con = getConnection();
            stmt = con.createStatement();

            sql = GET_UNIT_ID_FROM_NAME;
            ps = con.prepareStatement(sql);

            ps.setString(1, unitName);

            rs = ps.executeQuery();

            if (rs.next())
            {
                unitID = rs.getString(UnitServiceSQLIfc.COLUMNS.UNIT_ID);
                ++recordFound;
            }

            if (recordFound == 0)
            {
                logger.info("Unit ID not found for unit " + unitName);
            }

        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while getting Unit ID" + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while getting Unit ID" + e.getMessage());
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
        return unitID;
    }

    /**
     * This method will get all Unit IDs
     * 
     * @param String
     * @param String
     * @return boolean
     * @throws Exception
     */
    public List<UnitBean> getUnitIDNamesList()
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        String sql = null;
        short recordFound = 0;

        List<UnitBean> unitIDNamesList = new ArrayList<UnitBean>();

        try
        {
            logger.info("UnitServiceImpl >> getUnitIDNamesList");

            con = getConnection();
            stmt = con.createStatement();

            sql = GET_UNIT_ID_NAME_PAIRS;
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next())
            {
                UnitBean bean = new UnitBean();

                bean.setUnitID(rs.getString(UnitServiceSQLIfc.COLUMNS.UNIT_ID));
                bean.setUnitName(rs.getString(UnitServiceSQLIfc.COLUMNS.UNIT_NAME));

                unitIDNamesList.add(bean);

                ++recordFound;
            }

            if (recordFound == 0)
            {
                logger.info("No records found ");
            }

        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while getting Unit name" + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while getting Unit name" + e.getMessage());
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
        return unitIDNamesList;
    }

}
