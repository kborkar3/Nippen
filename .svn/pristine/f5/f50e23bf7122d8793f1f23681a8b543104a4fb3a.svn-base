package com.projectwork.action.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.bean.CompanyBean;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.impl.CompanyServiceImpl;

public class ShowCompanyTabAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = -4410850580090739079L;
    List<CompanyBean> companyList = new ArrayList<CompanyBean>();
    private String companyName;
    private HttpServletRequest request;

    /**
     * Navigates to show Company jsp when Disable company tab is clicked.
     * 
     * @param 
     * @return success: Successful entry of new company record in the db     
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

        CompanyServiceImpl companyServiceObject = new CompanyServiceImpl();
        companyList = companyServiceObject.getCompanyNames();

        if (companyList.isEmpty())
        {
            return RETURN_FAIL;
        }
        else
        {
            Collections.sort(companyList, new Comparator<CompanyBean>()
            {
                public int compare(CompanyBean c1, CompanyBean c2)
                {
                    return c1.getCompanyName().compareTo(c2.getCompanyName());
                }
            });
            
            return RETURN_SUCCESS;
        }
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

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
}
