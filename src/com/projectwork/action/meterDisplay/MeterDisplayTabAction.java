package com.projectwork.action.meterDisplay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.bean.FeederBean;
import com.projectwork.bean.ParameterBean;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.dto.ParameterDTO;
import com.projectwork.impl.FeederServiceImpl;
import com.projectwork.impl.LoginServiceImpl;

public class MeterDisplayTabAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = -8972434199921066620L;

    private HttpServletRequest request;

    private List<FeederBean> feederList = new ArrayList<FeederBean>();

    private List<String> feederListSearch = new ArrayList<String>();

    private String feeder;

    private String feederName;

    private List<ParameterBean> parameterList = new ArrayList<ParameterBean>();

    List<ParameterDTO> parameterdtoList = new ArrayList<ParameterDTO>();

    List<ParameterDTO> parameterdtoLeftList = null;

    List<ParameterDTO> parameterdtoRightList = null;

    String lastUpdatedTime = null;

    static Logger logger = Logger.getLogger(MeterDisplayTabAction.class);

    public String execute() throws Exception

    {
        String forwardString = RETURN_LOGIN_ERROR;

        // Redirect the user to login screen if session has expired

        HttpSession session = request.getSession(false);
        if (session == null)
        {
            request.getSession().removeAttribute(USER_STATUS);
            return forwardString;
        }
        
        parameterdtoList = null;

        int companyID = (Integer)request.getSession().getAttribute("CompanyID");
        feederList = new LoginServiceImpl().getFeederData(companyID);
        
        Iterator<FeederBean> feederIterator = feederList.iterator();

        while (feederIterator.hasNext())
        {
            FeederBean bean = feederIterator.next();
            feederListSearch.add(bean.getFeeder());
        }


        FeederServiceImpl feederServiceObj = new FeederServiceImpl();

        if (!(isEmpty(feederName)))
        {
            parameterList = feederServiceObj.getParameterData(feederName, companyID);

            if (parameterList.isEmpty())
            {
                request.setAttribute("nodata", "nodata");
            }
            request.setAttribute("selectedFeeder", feederName);

            feeder = null;
        }

        if (!(isEmpty(feeder)))
        {
            parameterList = feederServiceObj.getParameterData(feeder, companyID);

            if (parameterList.isEmpty())
            {
                request.setAttribute("nodata", "nodata");
            }

            request.setAttribute("selectedFeeder", feeder);
        }

        if (!(parameterList.isEmpty()))
        {
            parameterdtoLeftList = new ArrayList<ParameterDTO>();
            parameterdtoRightList = new ArrayList<ParameterDTO>();

            Iterator<ParameterBean> iterator = parameterList.iterator();
            parameterdtoList = new ArrayList<ParameterDTO>();

            while (iterator.hasNext())
            {
                ParameterDTO dto = getParameterDTO(iterator.next());
                parameterdtoList.add(dto);
            }

            int parameterdtoListSize = parameterdtoList.size();
            if (parameterdtoListSize > 5)
            {
                int divider = parameterdtoListSize / 2;

                for (int j = 0; j < divider; j++)
                {
                    parameterdtoLeftList.add(parameterdtoList.get(j));
                }

                for (int k = divider; k < parameterdtoListSize; k++)
                {
                    parameterdtoRightList.add(parameterdtoList.get(k));
                }

            }

            else
            {
                parameterdtoLeftList = parameterdtoList;
                parameterdtoRightList = null;
            }

            lastUpdatedTime = parameterdtoList.get(0).getTimeStamp();
        }

        request.setAttribute("parameterdtoLeftList", parameterdtoLeftList);
        request.setAttribute("parameterdtoRightList", parameterdtoRightList);
        request.setAttribute("lastUpdatedTime", lastUpdatedTime);

        return RETURN_SUCCESS;
    }

    public ParameterDTO getParameterDTO(ParameterBean bean)
    {
        ParameterDTO dto = new ParameterDTO();

        dto.setParameterName(bean.getParameterName());
        dto.setParameterValue(Float.toString(bean.getParameterValue()));
        dto.setParameterRYGB(bean.getParameterRYGB());

        SimpleDateFormat newformatter = new SimpleDateFormat(TIME_FORMAT);
        dto.setTimeStamp(newformatter.format(bean.getDateTimeStamp().getTime()));

        return dto;
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

    public List<String> getFeederListSearch()
    {
        return feederListSearch;
    }

    public void setFeederListSearch(List<String> feederListSearch)
    {
        this.feederListSearch = feederListSearch;
    }

    public String getFeeder()
    {
        return feeder;
    }

    public void setFeeder(String feeder)
    {
        this.feeder = feeder;
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

    public List<ParameterDTO> getParameterdtoList()
    {
        return parameterdtoList;
    }

    public void setParameterdtoList(List<ParameterDTO> parameterdtoList)
    {
        this.parameterdtoList = parameterdtoList;
    }

    public List<ParameterDTO> getParameterdtoLeftList()
    {
        return parameterdtoLeftList;
    }

    public void setParameterdtoLeftList(List<ParameterDTO> parameterdtoLeftList)
    {
        this.parameterdtoLeftList = parameterdtoLeftList;
    }

    public List<ParameterDTO> getParameterdtoRightList()
    {
        return parameterdtoRightList;
    }

    public void setParameterdtoRightList(List<ParameterDTO> parameterdtoRightList)
    {
        this.parameterdtoRightList = parameterdtoRightList;
    }

    /**
     * This method checks for null value
     * 
     * @param : String
     * @return true: String is null
     * @return false: String is not null
     */

    protected boolean isEmpty(String value)
    {
        if (value == null || value.trim().equals(""))
        {
            return true;
        }
        return false;
    }

}
