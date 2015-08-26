package com.projectwork.action.company;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.impl.CompanyServiceImpl;

public class AddNewCompanyAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = -1458088810197886825L;

    private String companyID;

    private String companyName;

    private String companyAddress;

    private String companyCity;

    private String geoID;

    private Integer pinCode;

    private String contactNo1;

    private String contactNo2;

    private HttpServletRequest request;

    private File companyLogo;

    private String userImageContentType;

    private String userImageFileName;

    /**
     * Accepts company addition form and inserts into db
     * 
     * @param
     * @return success: Successful entry of new company record in the db
     * @return error: Failure
     * @throws Exception
     */

    public String execute() throws Exception
    {
        
        CompanyServiceImpl companyServiceObject = new CompanyServiceImpl();

        boolean isRecordInserted = false;

        isRecordInserted = companyServiceObject.addNewCompany(companyID, companyName, companyAddress, companyCity,
                geoID, pinCode, contactNo1, contactNo2, companyLogo);

        if (isRecordInserted)
        {
            return RETURN_SUCCESS;
        }
        else
        {
            return RETURN_ERROR;
        }
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

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyAddress()
    {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress)
    {
        this.companyAddress = companyAddress;
    }

    public String getCompanyCity()
    {
        return companyCity;
    }

    public void setCompanyCity(String companyCity)
    {
        this.companyCity = companyCity;
    }

    public String getGeoID()
    {
        return geoID;
    }

    public void setGeoID(String geoID)
    {
        this.geoID = geoID;
    }

    public Integer getPinCode()
    {
        return pinCode;
    }

    public void setPinCode(Integer pinCode)
    {
        this.pinCode = pinCode;
    }

    public String getContactNo1()
    {
        return contactNo1;
    }

    public void setContactNo1(String contactNo1)
    {
        this.contactNo1 = contactNo1;
    }

    public String getContactNo2()
    {
        return contactNo2;
    }

    public void setContactNo2(String contactNo2)
    {
        this.contactNo2 = contactNo2;
    }

    public String getUserImageContentType()
    {
        return userImageContentType;
    }

    public void setUserImageContentType(String userImageContentType)
    {
        this.userImageContentType = userImageContentType;
    }

    public String getUserImageFileName()
    {
        return userImageFileName;
    }

    public void setUserImageFileName(String userImageFileName)
    {
        this.userImageFileName = userImageFileName;
    }

    public File getCompanyLogo()
    {
        return companyLogo;
    }

    public void setCompanyLogo(File companyLogo)
    {
        this.companyLogo = companyLogo;
    }

}
