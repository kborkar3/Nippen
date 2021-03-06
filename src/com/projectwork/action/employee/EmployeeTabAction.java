package com.projectwork.action.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.constants.TestProjectConstantsIfc;

public class EmployeeTabAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = -6443237909363409615L;
    private HttpServletRequest request;
    
    /**
     * Navigates to Add Employee jsp when Add employee tab is clicked.
     * 
     * @param 
     * @return success: Successful entry of new employee record in the db     
     * @throws Exception
     */
    
    public String execute() throws Exception

    {
        String forwardString = RETURN_LOGIN_ERROR;
        
    //  Redirect the user to login screen if session has expired
        
        HttpSession session = request.getSession(false);
        if (session == null)
        {
            request.getSession().removeAttribute(USER_STATUS);
            return forwardString;
        }
        return RETURN_SUCCESS;
    }
    
    public void setServletRequest(HttpServletRequest httpServletRequest)
    {
        this.request = httpServletRequest;
    }
}
