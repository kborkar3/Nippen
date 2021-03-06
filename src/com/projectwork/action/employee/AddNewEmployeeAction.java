package com.projectwork.action.employee;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.impl.EmployeeServiceImpl;

public class AddNewEmployeeAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = -3274619693172671909L;

    private String employeeID;

    private String employeePassword;

    private String employeeFirstName;

    private String employeeLastName;

    private String employeeAddress;

    private String employeeCity;

    private String employeeCountry;

    private String pinCode;

    private String contactNo;

    private String roleIndex;

    private String companyID;

    private String employeeLocale;

    private HttpServletRequest request;

    /**
     * Accepts employee addition form and inserts into db
     * 
     * @param
     * @return success: Successful entry of new employee record in the db
     * @return error: Failure
     * @throws Exception
     */

    public String execute() throws Exception
    {
        EmployeeServiceImpl employeeServiceObject = new EmployeeServiceImpl();
        boolean isRecordInserted = false;

        isRecordInserted = employeeServiceObject.addNewEmployee(employeeID, employeePassword, employeeFirstName,
                employeeLastName, employeeAddress, employeeCity, employeeCountry, pinCode, contactNo, roleIndex,
                companyID, employeeLocale);

        if (isRecordInserted)
        {
            return RETURN_SUCCESS;
        }
        
        return RETURN_ERROR;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest)
    {
        this.request = httpServletRequest;
    }

    public String getCompanyID()
    {
        return companyID;
    }

    public void setCompanyID(String companyID)
    {
        this.companyID = companyID;
    }

    public String getEmployeeID()
    {
        return employeeID;
    }

    public void setEmployeeID(String employeeID)
    {
        this.employeeID = employeeID;
    }

    public String getEmployeePassword()
    {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword)
    {
        this.employeePassword = employeePassword;
    }

    public String getEmployeeFirstName()
    {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName)
    {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName()
    {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName)
    {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeAddress()
    {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress)
    {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeCity()
    {
        return employeeCity;
    }

    public void setEmployeeCity(String employeeCity)
    {
        this.employeeCity = employeeCity;
    }

    public String getEmployeeCountry()
    {
        return employeeCountry;
    }

    public void setEmployeeCountry(String employeeCountry)
    {
        this.employeeCountry = employeeCountry;
    }

    public String getPinCode()
    {
        return pinCode;
    }

    public void setPinCode(String pinCode)
    {
        this.pinCode = pinCode;
    }

    public String getContactNo()
    {
        return contactNo;
    }

    public void setContactNo(String contactNo)
    {
        this.contactNo = contactNo;
    }

    public String getRoleIndex()
    {
        return roleIndex;
    }

    public void setRoleIndex(String roleIndex)
    {
        this.roleIndex = roleIndex;
    }

    public String getEmployeeLocale()
    {
        return employeeLocale;
    }

    public void setEmployeeLocale(String employeeLocale)
    {
        this.employeeLocale = employeeLocale;
    }

}
