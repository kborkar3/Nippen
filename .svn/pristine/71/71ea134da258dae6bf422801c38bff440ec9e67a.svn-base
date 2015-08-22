package com.projectwork.action.company;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.impl.CompanyServiceImpl;

public class CompanyTabAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = -3690450951982328566L;

    private HttpServletRequest request;

    private int companyID;

    /**
     * Navigates to Add Company jsp when Add company tab is clicked.
     * 
     * @param
     * @return success: Successful entry of new company record in the db
     * @throws Exception
     */

    public String execute() throws Exception

    {
        String forwardString = RETURN_LOGIN_ERROR; // Redirect the user to login screen if session has expired
        
        HttpSession session = request.getSession(false);
        if (session == null)
        {
            request.getSession().removeAttribute(USER_STATUS);
            return forwardString;
        }
        
        CompanyServiceImpl companyServiceObj = new CompanyServiceImpl();
        companyID = companyServiceObj.getNextCompanyID();

        return RETURN_SUCCESS;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest)
    {
        this.request = httpServletRequest;
    }

    public int getCompanyID()
    {
        return companyID;
    }

    public void setCompanyID(int companyID)
    {
        this.companyID = companyID;
    }
}
