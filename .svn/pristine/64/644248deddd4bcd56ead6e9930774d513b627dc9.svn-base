package com.projectwork.action;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.impl.LoginServiceImpl;

public class ForgotPasswordAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = 8168154931936723680L;
    private String userName;
    private HttpServletRequest request;
    
    private static Logger logger=Logger.getLogger(ForgotPasswordAction.class);

   /**
     * This method will check if user is present in database and will show appropriate response
     * 
     * @param 
     * @return success: Successful validation
     * @return error: Invalid User credentials 
     * @throws Exception
     */
    
    public String execute() throws Exception

    {
        LoginServiceImpl loginObject = new LoginServiceImpl();

        boolean isUserPresent = loginObject.isUserPresent(userName);

        if (isUserPresent)
        {
            //  Generate temporary password
            
            String temporaryPassword = generateTemparyPassword();
            
            request.setAttribute("forgottenUser", userName);
            request.setAttribute("temporaryPassword", temporaryPassword);
            
        //  Set password reset pin to 1
            
            boolean isPasswordReset = loginObject.setPasswordResetPin(userName, temporaryPassword);
            
            if(isPasswordReset)
            {
                addActionError(getText("forgotPassword.message"));
            }
            
        }
        else
        {
            addActionError(getText("forgotPassword.errormessage"));
        }
        
        return RETURN_SUCCESS;
    }

    private String generateTemparyPassword()
{
        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
        int PASSWORD_LENGTH = 7;
        Random random = new Random();

        String pw = "";
        for (int i=0; i<PASSWORD_LENGTH; i++)
        {
            int index = (int)(random.nextDouble() *letters.length());
            pw += letters.substring(index, index+1);
        }
        return pw;
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


}
