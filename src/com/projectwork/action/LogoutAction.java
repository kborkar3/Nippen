package com.projectwork.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.constants.TestProjectConstantsIfc;

public class LogoutAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = -6138389050355326996L;
    /**
     * 
     */
    private HttpServletRequest request;

    /**
     * This method will end the current session and user will be navigated to
     * login page
     * 
     * @return success: Session is invalidated
     * @throws Exception
     */

    public String execute() throws Exception

    {
        //  Delete temporary folder created for storing images. 
        
        String tempHeaderLocation = (String) request.getSession().getAttribute("imageFileDirPath");

        if (tempHeaderLocation != null)
        {
            deleteDir(new File(tempHeaderLocation));
        }

        request.getSession().removeAttribute(USER_STATUS);
        request.getSession().invalidate();
        return RETURN_SUCCESS;
    }

    /**
     * This method will delete temporary folder and all files inside it which was created for storing header and graph images.
     * 
     * @return success: Directory is deleted
     *
     */
    
    public boolean deleteDir(File dir)
    {
        if (dir.isDirectory())
        {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++)
            {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success)
                {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public void setServletRequest(HttpServletRequest httpServletRequest)
    {
        this.request = httpServletRequest;
    }
}
