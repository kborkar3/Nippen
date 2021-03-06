package com.projectwork.action.config; 

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.bean.FeederBean;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.impl.LoginServiceImpl;

public class ConfigureTabAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = 2842481076946547939L;

    private HttpServletRequest request;
    
    private List<FeederBean> feederList = new ArrayList<FeederBean>();
    
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
        
        int companyID = (Integer)request.getSession().getAttribute("CompanyID");
        feederList = new LoginServiceImpl().getFeederData(companyID);
        
        return RETURN_SUCCESS;
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

}
