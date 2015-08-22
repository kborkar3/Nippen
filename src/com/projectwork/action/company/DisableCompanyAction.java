package com.projectwork.action.company;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.impl.CompanyServiceImpl;

public class DisableCompanyAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = -1001232906809959814L;

    private HttpServletRequest request;

    private String companyName;

    /**
     * Company name can be removed from jsp by setting flag to disabled
     * 
     * @param
     * @return success: Company record disabled in the db
     * @return error: Failure
     * @throws Exception
     */

    public String execute() throws Exception

    {
        CompanyServiceImpl companyServiceObject = new CompanyServiceImpl();

        boolean isCompanyDisabled = false;
        isCompanyDisabled = companyServiceObject.disableCompany(companyName);

        if (isCompanyDisabled)
        {
            return RETURN_SUCCESS;
        }

        return RETURN_ERROR;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest)
    {
        this.request = httpServletRequest;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyName()
    {
        return companyName;
    }
}