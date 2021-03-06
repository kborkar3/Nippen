package com.projectwork.action.historicalreport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.bean.FeederBean;
import com.projectwork.bean.ParameterBean;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.impl.LoginServiceImpl;

public class HistoricalReportTabAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private HttpServletRequest request;

    private String feederMap;

    private String parameterMap;

    private Map<String, ArrayList<String>> feederParametereMap;

    private String feederName;

    private String parameterName;

    private List<FeederBean> feederList = new ArrayList<FeederBean>();

    private List<ParameterBean> parameterList = new ArrayList<ParameterBean>();

    private List<String> parameterNamesList = new ArrayList<String>();

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

        if (!(feederList.isEmpty()))
        {
            feederParametereMap = new HashMap<String, ArrayList<String>>();

            for (int i = 0; i < feederList.size(); i++)
            {
                parameterList = new LoginServiceImpl().getParameterData(feederList.get(i).getFeeder(), companyID);

                if (!(parameterList.isEmpty()))
                {
                    for (int j = 0; j < parameterList.size(); j++)
                    {
                        parameterNamesList.add(parameterList.get(j).getParameterName());
                    }

                    feederParametereMap.put(feederList.get(i).getFeeder(), new ArrayList<String>(parameterNamesList));
                }
            }
            
            forwardString = RETURN_SUCCESS;
        }
        else
        {
            request.setAttribute("isRedirected", "true");
            forwardString = RETURN_ERROR;
        }
        
        return forwardString;

    }

    public String getFeederName()
    {
        return feederName;
    }

    public void setFeederName(String feederName)
    {
        this.feederName = feederName;
    }

    public List<ParameterBean> getParameterList()
    {
        return parameterList;
    }

    public void setParameterList(List<ParameterBean> parameterList)
    {
        this.parameterList = parameterList;
    }

    public List<FeederBean> getFeederList()
    {
        return feederList;
    }

    public void setFeederList(List<FeederBean> feederList)
    {
        this.feederList = feederList;
    }

    public String getParameterName()
    {
        return parameterName;
    }

    public void setParameterName(String parameterName)
    {
        this.parameterName = parameterName;
    }

    public List<String> getParameterNamesList()
    {
        return parameterNamesList;
    }

    public void setParameterNamesList(List<String> parameterNamesList)
    {
        this.parameterNamesList = parameterNamesList;
    }

    public Map<String, ArrayList<String>> getFeederParametereMap()
    {
        return feederParametereMap;
    }

    public void setFeederParametereMap(Map<String, ArrayList<String>> feederParametereMap)
    {
        this.feederParametereMap = feederParametereMap;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest)
    {
        this.request = httpServletRequest;
    }

    public String getFeederMap()
    {
        return feederMap;
    }

    public void setFeederMap(String feederMap)
    {
        this.feederMap = feederMap;
    }

    public String getParameterMap()
    {
        return parameterMap;
    }

    public void setParameterMap(String parameterMap)
    {
        this.parameterMap = parameterMap;
    }

}
