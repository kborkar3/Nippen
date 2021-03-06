package com.projectwork.action.unit;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.bean.CompanyBean;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.impl.CompanyServiceImpl;
import com.projectwork.impl.UnitServiceImpl;

public class AddNewUnitAction extends ActionSupport implements TestProjectConstantsIfc
{
    /**
     * 
     */
    private static final long serialVersionUID = 7515430686300087906L;
    private String unitID;
    private String unitName;
    private String companyID;
    private int companyIDInteger;
    
    List<CompanyBean> companyList = new ArrayList<CompanyBean>();

    public String execute() throws Exception
    {
        UnitServiceImpl unitServicObject = new UnitServiceImpl();

        boolean isRecordInserted = false;
        boolean isUnitCompanyLinkAdded = false;
        
        CompanyServiceImpl companyObj = new CompanyServiceImpl();
        companyList = companyObj.getCompanyNames();

        isRecordInserted = unitServicObject.addNewUnit(unitID, unitName);

        if (isRecordInserted)
        {
            companyIDInteger = Integer.parseInt(companyID);
            
            isUnitCompanyLinkAdded = unitServicObject.addNewUnitCompanyLink(unitID, companyIDInteger);
            
            if(isUnitCompanyLinkAdded)
            {
                return RETURN_SUCCESS;
            }
            
            else
            {
                addActionError("Company Unit link is invalid");
            }
        }
        else
        {
            addActionError("Entered Unit is already present.");
        }
        
        return RETURN_ERROR;
    }

    protected boolean isEmpty(String value)
    {
        if (value == null || value.trim().equals(""))
        {
            return true;
        }
        return false;
    }

    protected boolean isNumber(String input)
    {
        try
        {
            Integer.parseInt(input);
        }
        catch (NumberFormatException ex)
        {
            return false;
        }
        return true;
    }

    public String getUnitID()
    {
        return unitID;
    }

    public void setUnitID(String unitID)
    {
        this.unitID = unitID;
    }

    public String getUnitName()
    {
        return unitName;
    }

    public void setUnitName(String unitName)
    {
        this.unitName = unitName;
    }

    public String getCompanyID()
    {
        return companyID;
    }

    public void setCompanyID(String companyID)
    {
        this.companyID = companyID;
    }
    
    public List<CompanyBean> getCompanyList()
    {
        return companyList;
    }

    public void setCompanyList(List<CompanyBean> companyList)
    {
        this.companyList = companyList;
    }

}
