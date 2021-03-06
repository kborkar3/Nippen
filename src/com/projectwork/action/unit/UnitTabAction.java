package com.projectwork.action.unit;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.bean.CompanyBean;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.impl.CompanyServiceImpl;

public class UnitTabAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = 180187302250294797L;

    private HttpServletRequest request;
    List<CompanyBean> companyList = new ArrayList<CompanyBean>();
    
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
        
        CompanyServiceImpl companyObj = new CompanyServiceImpl();
        companyList = companyObj.getCompanyNames();
        
        return RETURN_SUCCESS;
    }
    
    public void setServletRequest(HttpServletRequest httpServletRequest)
    {
        this.request = httpServletRequest;
    }

    public List<CompanyBean> getCompanyList()
    {
        return companyList;
    }

    public void setCompanyList(List<CompanyBean> companyList)
    {
        this.companyList = companyList;
    }
}
