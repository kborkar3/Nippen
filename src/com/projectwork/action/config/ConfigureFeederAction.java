package com.projectwork.action.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.bean.FeederBean;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.dto.FeederDTO;
import com.projectwork.impl.CompanyServiceImpl;
import com.projectwork.impl.FeederServiceImpl;
import com.projectwork.impl.UnitServiceImpl;

public class ConfigureFeederAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = 510147009711309219L;

    private HttpServletRequest request;

    private List<FeederBean> feederList = new ArrayList<FeederBean>();

    private List<FeederDTO> feederDetailsList = new ArrayList<FeederDTO>();

    private List<String> leftUnitList = new ArrayList<String>();

    private List<String> rightUnitList = new ArrayList<String>();

    private String leftUnit;

    private String rightUnit;

    private String feeder;
    
    private String meterID;
    
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
        
        return RETURN_SUCCESS;
    }

    public String initializeList()
    {
        int companyID = (Integer)request.getSession().getAttribute("CompanyID");

        CompanyServiceImpl companyServiceObj = new CompanyServiceImpl();
        String companyName = companyServiceObj.getCompanyNameDetails(companyID);

        FeederServiceImpl feederObj = new FeederServiceImpl();
        ArrayList<FeederBean> meterDetailsList = feederObj.getMeterDetails(feeder, companyID);

        // List of assigned Units

        UnitServiceImpl unitServiceObj = new UnitServiceImpl();
        leftUnitList = unitServiceObj.getAssignedUnitNamesforCompany(companyID);

        // List of unassigned Units

        rightUnitList = unitServiceObj.getUnAssignedUnitNamesforCompany(companyID);

        Iterator<FeederBean> iterator = meterDetailsList.iterator();

        while (iterator.hasNext())
        {
            FeederDTO dto = getFeederDTO(iterator.next(), companyName);
            feederDetailsList.add(dto);
        }

        request.setAttribute("feederDetailsList", feederDetailsList);

        return NONE;

    }

    public FeederDTO getFeederDTO(FeederBean bean, String companyName)
    {
        FeederDTO dto = new FeederDTO();
        dto.setFeeder(feeder);
        dto.setMeterIndex(new Integer(bean.getMeterIndex()).toString());
        dto.setMeterVersion(new Integer(bean.getMeterVersion()).toString());
        dto.setCompanyID(new Integer(bean.getCompanyID()).toString());
        dto.setMeterID(new Integer(bean.getMeterID()).toString());
        dto.setCompanyName(companyName);

        return dto;
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

}
