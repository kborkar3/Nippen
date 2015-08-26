package com.projectwork.action.graph;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.bean.FeederBean;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.impl.LoginServiceImpl;

public class GraphTabAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = 6056865629196180497L;
    private HttpServletRequest request;
    private List<FeederBean> feederList = new ArrayList<FeederBean>();
    
    /**
     * Search criteria for graph is displayed once Graph tab is clicked and feeder list is populated.
     * 
     * @param
     * @return success: feeder list data is retrieved from database.
     * @return error: feeder list is empty.
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
        
        //  Get company ID from session and retrieve feeder list
        
        int companyID = (Integer)request.getSession().getAttribute("CompanyID");
        feederList = new LoginServiceImpl().getFeederData(companyID);
        
        if (!(feederList.isEmpty()))
        {

            forwardString = RETURN_SUCCESS;
        }
        else
        {
            request.setAttribute("isRedirected", "true");
            forwardString = RETURN_ERROR;
        }
        
        return forwardString;
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
