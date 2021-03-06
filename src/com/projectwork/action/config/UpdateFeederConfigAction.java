package com.projectwork.action.config;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.bean.FeederBean;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.dto.FeederDTO;
import com.projectwork.impl.UnitServiceImpl;

public class UpdateFeederConfigAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = -5344671102617121586L;

    private HttpServletRequest request;

    private List<FeederBean> feederList;

    private List<FeederDTO> feederDetailsList;

    private List<String> leftUnitList;

    private List<String> rightUnitList;

    private String leftUnit;

    private String rightUnit;

    private String feeder;

    private String meterID;

    private String meterIndex;

    private String companyName;

    public String execute() throws Exception

    {
        String forwardString = RETURN_LOGIN_ERROR;

        // Redirect the user to login screen if session has expired

        HttpSession session = request.getSession(false);
        if (session == null)
        {
            request.getSession().removeAttribute(USER_STATUS);
            return forwardString;
        }

        if (!isEmpty(leftUnit))
        {
            leftUnitList = Arrays.asList(leftUnit.split("\\s*,\\s*"));
        }
        else
        {
            leftUnitList = null;
        }

        int companyID = (Integer)request.getSession().getAttribute("CompanyID");

        UnitServiceImpl unitServiceObj = new UnitServiceImpl();
        boolean isUpdated = unitServiceObj.updateAssignedUnitsForCompany(companyID, leftUnitList);

        if (isUpdated)
        {
            return RETURN_SUCCESS;
        }

        return RETURN_ERROR;

    }

    public void setServletRequest(HttpServletRequest httpServletRequest)
    {
        this.request = httpServletRequest;
    }

    public List<FeederBean> getFeederList()
    {
        return feederList;
    }

    public void setFeederList(List<FeederBean> feederList)
    {
        this.feederList = feederList;
    }

    public String getFeeder()
    {
        return feeder;
    }

    public void setFeeder(String feeder)
    {
        this.feeder = feeder;
    }

    public List<FeederDTO> getFeederDetailsList()
    {
        return feederDetailsList;
    }

    public void setFeederDetailsList(List<FeederDTO> feederDetailsList)
    {
        this.feederDetailsList = feederDetailsList;
    }

    public List<String> getLeftUnitList()
    {
        return leftUnitList;
    }

    public void setLeftUnitList(List<String> leftUnitList)
    {
        this.leftUnitList = leftUnitList;
    }

    public List<String> getRightUnitList()
    {
        return rightUnitList;
    }

    public void setRightUnitList(List<String> rightUnitList)
    {
        this.rightUnitList = rightUnitList;
    }

    public String getLeftUnit()
    {
        return leftUnit;
    }

    public void setLeftUnit(String leftUnit)
    {
        this.leftUnit = leftUnit;
    }

    public String getRightUnit()
    {
        return rightUnit;
    }

    public void setRightUnit(String rightUnit)
    {
        this.rightUnit = rightUnit;
    }

    public String getMeterID()
    {
        return meterID;
    }

    public void setMeterID(String meterID)
    {
        this.meterID = meterID;
    }

    public String getMeterIndex()
    {
        return meterIndex;
    }

    public void setMeterIndex(String meterIndex)
    {
        this.meterIndex = meterIndex;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    /**
     * This method checks for null value
     * 
     * @param : String
     * @return true: String is null
     * @return false: String is not null
     */

    protected boolean isEmpty(String value)
    {
        if (value == null || value.trim().equals(""))
        {
            return true;
        }
        return false;
    }

}