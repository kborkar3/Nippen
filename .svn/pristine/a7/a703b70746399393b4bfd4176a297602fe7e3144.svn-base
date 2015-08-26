package com.projectwork.action;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.impl.LoginServiceImpl;

public class ResetPasswordAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = 8168154931936723680L;

    private String userName;

    private String currentPassword;

    private String newpassword;

    private HttpServletRequest request;

    private static Logger logger = Logger.getLogger(ResetPasswordAction.class);

    /**
     * This method will check if user is present in database and will show
     * appropriate response
     * 
     * @param
     * @return success: Successful validation
     * @return error: Invalid User credentials
     * @throws Exception
     */

    public String execute() throws Exception

    {
        LoginServiceImpl loginObject = new LoginServiceImpl();
        
        String userName = (String)request.getSession().getAttribute("userName");

        boolean isTempararyPasswordValid = loginObject.validateLoginCredentials(userName, currentPassword);
        
        if(isTempararyPasswordValid)
        {
            boolean isPasswordReset = loginObject.updateForgottenPassword(userName, newpassword);
            if(isPasswordReset)
            {
                return RETURN_SUCCESS;
            }
        }
        return RETURN_ERROR;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest)
    {
        this.request = httpServletRequest;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getCurrentPassword()
    {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword)
    {
        this.currentPassword = currentPassword;
    }

    public String getNewpassword()
    {
        return newpassword;
    }

    public void setNewpassword(String newpassword)
    {
        this.newpassword = newpassword;
    }

}
