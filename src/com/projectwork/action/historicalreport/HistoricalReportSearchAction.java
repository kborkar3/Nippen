package com.projectwork.action.historicalreport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.bean.FeederBean;
import com.projectwork.bean.HistoricReportBean;
import com.projectwork.bean.ParameterBean;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.dto.HistoricReportDTO;
import com.projectwork.impl.HistoricReportServiceImpl;

public class HistoricalReportSearchAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = -5078342923662965523L;

    private String feeder;

    private String startDate;

    private String endDate;

    private HttpServletRequest request;

    List<HistoricReportDTO> historicalReportdtoList = new ArrayList<HistoricReportDTO>();

    private List<FeederBean> feederList = new ArrayList<FeederBean>();

    private List<ParameterBean> parameterList = new ArrayList<ParameterBean>();

    private String feederMap;

    private String parameterMap;

    private Map<String, ArrayList<String>> feederParametereMap;

    private static Logger logger=Logger.getLogger(HistoricalReportSearchAction.class);
    
    /**
     * Historical report is generated and displayed based on search criteria.
     * 
     * @param
     * @return success: Report DTO retrieved and displayed on screen
     * @return error: No records found for entered search criteria.
     * @throws Exception
     */
    
    public String execute() throws Exception
    {
        logger.info("Selected parameters are : " + parameterMap);

        List<String> selectedParametersList = Arrays.asList(parameterMap.split("\\s*,\\s*"));

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false);
        Date sdate = (Date)formatter.parse(startDate);
        Date edate = (Date)formatter.parse(endDate);
        startDate = formatter.format(sdate);
        endDate = formatter.format(edate);

        SimpleDateFormat newformatter = new SimpleDateFormat(DATE_FORMAT);
        newformatter.setLenient(false);
        startDate = newformatter.format(sdate);
        endDate = newformatter.format(edate);

        HistoricReportServiceImpl historicReportServiceObject = new HistoricReportServiceImpl();

        List<HistoricReportBean> historicalReportList = historicReportServiceObject.getHistoricReportData(feederMap,
                selectedParametersList, startDate, endDate);

        if (!(historicalReportList.isEmpty()))
        {
            Collections.sort(historicalReportList, new Comparator<HistoricReportBean>()
                    {
                        public int compare(HistoricReportBean m1, HistoricReportBean m2)
                        {
                            return m1.getDateTimeStamp().compareTo(m2.getDateTimeStamp());
                        }
                    });
            
            Iterator<HistoricReportBean> iterator = historicalReportList.iterator();
            historicalReportdtoList = new ArrayList<HistoricReportDTO>();

            while (iterator.hasNext())
            {
                HistoricReportDTO dto = getHistoricReportDTO(iterator.next());
                historicalReportdtoList.add(dto);
            }
        }
        
        else
        {
            request.getSession().setAttribute("nohistoricdatafound", "nohistoricdatafound");
            return "redirect";
        }

        request.getSession().setAttribute("historicalReportdtoList", historicalReportdtoList);
        return RETURN_SUCCESS;
    }

    protected boolean isEmpty(String value)
    {
        if (value == null || value.trim().equals(""))
        {
            return true;
        }
        return false;
    }

    public HistoricReportDTO getHistoricReportDTO(HistoricReportBean bean)
    {
        HistoricReportDTO dto = new HistoricReportDTO();

        SimpleDateFormat newformatter = new SimpleDateFormat(DATE_FORMAT);
        dto.setDateStamp(newformatter.format(bean.getDateTimeStamp().getTime()));

        SimpleDateFormat timeformatter = new SimpleDateFormat(TIME_FORMAT);
        dto.setTimeStamp(timeformatter.format(bean.getDateTimeStamp().getTime()));

        dto.setFeederName(bean.getFeederName());
        dto.setValue(new Integer(bean.getValue()).toString());

        dto.setParameterName(bean.getParameterName());

        return dto;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest)
    {
        this.request = httpServletRequest;
    }

    public String getFeeder()
    {
        return feeder;
    }

    public void setFeeder(String feeder)
    {
        this.feeder = feeder;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public List<HistoricReportDTO> getHistoricalReportdtoList()
    {
        return historicalReportdtoList;
    }

    public void setHistoricalReportdtoList(List<HistoricReportDTO> historicalReportdtoList)
    {
        this.historicalReportdtoList = historicalReportdtoList;
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

    public Map<String, ArrayList<String>> getFeederParametereMap()
    {
        return feederParametereMap;
    }

    public void setFeederParametereMap(Map<String, ArrayList<String>> feederParametereMap)
    {
        this.feederParametereMap = feederParametereMap;
    }
}
