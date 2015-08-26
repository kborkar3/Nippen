package com.projectwork.action.graph;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.bean.DataTableBean;
import com.projectwork.bean.FeederBean;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.impl.GraphServiceImpl;
import com.projectwork.impl.LoginServiceImpl;

public class DisplayGraphAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = -8947960968238585697L;

    private HttpServletRequest request;

    private String feeder;

    private String startDate;

    private String endDate;

    private List<FeederBean> feederList = new ArrayList<FeederBean>();
    
    private static Logger logger = Logger.getLogger(DisplayGraphAction.class);

    /**
     * Graph is generated and displayed based on search criteria.
     * 
     * @param
     * @return success: Graph DTO retrieved and displayed on screen
     * @return error: No records found for entered search criteria.
     * @throws Exception
     */

    public String execute() throws Exception
    {

        /*
         * JQuery UI is in dd/MM/yyyy format. So first parse and format input
         * string to the format.
         */

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false);
        Date sdate = (Date)formatter.parse(startDate);
        Date edate = (Date)formatter.parse(endDate);
        startDate = formatter.format(sdate);
        endDate = formatter.format(edate);
        
        /* Now covert dates in dd/MM/yyyy format to dd-MMM-yy format used by db */

        SimpleDateFormat newformatter = new SimpleDateFormat(DATE_FORMAT);
        newformatter.setLenient(false);
        startDate = newformatter.format(sdate);
        endDate = newformatter.format(edate);

        java.util.Date usd = new SimpleDateFormat(DATE_FORMAT).parse(startDate);
        java.util.Date ued = new SimpleDateFormat(DATE_FORMAT).parse(endDate);
        java.sql.Date sqlStartDate = new java.sql.Date(usd.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(ued.getTime());

        GraphServiceImpl graphServiceObject = new GraphServiceImpl();

        /*
         * Get all dates between start date and end date and store it in
         * 'datesInRangeList' for future if all the dates in between also to be
         * shown.
         */

       // List<String> datesInRangeList = getDaysBetweenDates(sdate, edate);

        ArrayList<DataTableBean> graphParametersList = graphServiceObject.getGraphParameterData(feeder, sqlStartDate,
                sqlEndDate);
        
        
        if (!(graphParametersList.isEmpty()))
        {
            Collections.sort(graphParametersList, new Comparator<DataTableBean>()
            {
                public int compare(DataTableBean m1, DataTableBean m2)
                {
                    return m1.getDateTimeStamp().compareTo(m2.getDateTimeStamp());
                }
            });

            LoginServiceImpl loginObject = new LoginServiceImpl();
            
            // Get Parameter Names from indexes to show parameter values in graph
            String parameterName = null;
            
            Iterator<DataTableBean> iterator = graphParametersList.iterator();

            while (iterator.hasNext())
            {
                DataTableBean bean = iterator.next();
                parameterName = loginObject.getParameterName(bean.getParameterIndex());
                bean.setParameterName(parameterName);
            }
            
            String graphImagePath = getGraphFilePath();

            File file = new File(graphImagePath);
            FileOutputStream out = new FileOutputStream(file);
            JFreeChart chart = null;
            
            if(sqlStartDate.equals(sqlEndDate))
            {
                chart  = createDayChart(graphParametersList);
            }
            else
            {
                chart = createChart(graphParametersList);
            }
            
            
            HttpServletResponse response = ServletActionContext.getResponse();
            
            int width = 500;
            int height = 350;
            final ChartRenderingInfo info = new ChartRenderingInfo
                                           (new StandardEntityCollection());
            response.setContentType("image/png");
            OutputStream out2=response.getOutputStream();
            
            ChartUtilities.writeChartAsPNG(out2, chart, width, height,info);
            
            try
            {
                //ChartUtilities.writeChartAsPNG(out, chart, 900, 400);
                out.flush();
                out.close();
            }
            catch (IOException ioEx)
            {
                logger.error("Error writing PNG file " + ioEx.getMessage());
            }
            
            return RETURN_SUCCESS;
        } 

        else
        {
            request.getSession().setAttribute("nographdatafound", "nographdatafound");
            return "redirect";
        }
    }
    
    /**
     * This method will create path for graph image.
     * 
     * @param
     * @return success: relative path for graph image
     */
    
    
    private String getGraphFilePath()
    {
        String contextPath = request.getRealPath(File.separator);

        String userName = (String)request.getSession().getAttribute(USER_NAME);
        String currentTimeStamp = (String)request.getSession().getAttribute("currentTimeStamp");
        
        String imageFileDirPath = contextPath + userName + "_tempProjectImages" + currentTimeStamp;
        
        File imageFileDir = new File(imageFileDirPath);
        
        //  Create a sub directory inside system directory for storing header images  
        
        if(!(imageFileDir.exists()))
        {
            imageFileDir.mkdir(); 
        }

        String graphImageName = "graph" + currentTimeStamp + ".png";
        
        String graphImagePath = imageFileDirPath + File.separator + graphImageName;

        //  Save path to graph file in session to display header image. 
        
        String pathForJsp = userName + "_tempProjectImages" + currentTimeStamp + File.separator + graphImageName;
        
        request.getSession().setAttribute("graphImagePath", pathForJsp);
        
        return graphImagePath;

    }
    
    
    /**
     * This method is called if start date and end date are same and it will return chart based on graph parameters.
     * 
     * @param
     * @return success: chart
     */
    
    private JFreeChart createDayChart(ArrayList<DataTableBean> graphParametersList)
    {

        ArrayList<DataTableBean> newGraphParametersList = (ArrayList)graphParametersList.clone();

        Date sdate = null;

        TimeSeriesCollection dataset = new TimeSeriesCollection();

        TimeSeries[] newTimer = new TimeSeries[newGraphParametersList.size()];

        Set<String> newGraphParametersSet = new HashSet<String>();

        for (int i = 0; i < newGraphParametersList.size(); i++)
        {
            boolean isAdded = false;

            String currentParam = newGraphParametersList.get(i).getParameterName();

            newTimer[i] = new TimeSeries(currentParam);

            newGraphParametersSet.add(currentParam);

            for (int j = 0; j < graphParametersList.size(); j++)
            {
                if (currentParam.equals(graphParametersList.get(j).getParameterName()))
                {
                    sdate = graphParametersList.get(j).getDateTimeStamp().getTime();
                    
                    System.out.println(currentParam + "\t" + sdate + "\t" + graphParametersList.get(j).getValue());

                    newTimer[i].addOrUpdate(new Hour(sdate), graphParametersList.get(j).getValue());

                    graphParametersList.remove(j);
                    j--;

                    isAdded = true;
                }
            }

            if (isAdded)
            {
                dataset.addSeries(newTimer[i]);
            }

        }

        JFreeChart chart = ChartFactory.createTimeSeriesChart("Parameter Data", "Time", "Parameter Value", dataset,
                true, true, false);

        return chart;
    }
    
    /**
     * This method is called if start date and end date are different and it will return chart based on graph parameters.
     * 
     * @param
     * @return success: chart
     */
    

    private JFreeChart createChart(ArrayList<DataTableBean> graphParametersList)
    {

        ArrayList<DataTableBean> newGraphParametersList = (ArrayList<DataTableBean>)graphParametersList.clone();

        Date sdate = null;

        TimeSeriesCollection dataset = new TimeSeriesCollection();

        TimeSeries[] newTimer = new TimeSeries[newGraphParametersList.size()];

        Set<String> newGraphParametersSet = new HashSet<String>();

        for (int i = 0; i < newGraphParametersList.size(); i++)
        {
            boolean isAdded = false;

            String currentParam = newGraphParametersList.get(i).getParameterName();

            newTimer[i] = new TimeSeries(currentParam);

            newGraphParametersSet.add(currentParam);

            for (int j = 0; j < graphParametersList.size(); j++)
            {
                if (currentParam.equals(graphParametersList.get(j).getParameterName()))
                {
                    sdate = graphParametersList.get(j).getDateTimeStamp().getTime();

                    newTimer[i].addOrUpdate(new Day(sdate), graphParametersList.get(j).getValue());

                    graphParametersList.remove(j);
                    j--;

                    isAdded = true;
                }
            }

            if (isAdded)
            {
                dataset.addSeries(newTimer[i]);
            }

        }

        JFreeChart chart = ChartFactory.createTimeSeriesChart("Parameter Data", "Date", "Parameter Value", dataset,
                true, true, false);
        
        return chart;
    }


    /**
     * This method will get all dates between start date and end date selected
     * by the user from search page
     * 
     * @param : start date and end date
     * @return : List of all dates between start date and end date
     */

    public static List<String> getDaysBetweenDates(Date sdate, Date edate)
    {
        List<String> dates = new ArrayList<String>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(sdate);

        SimpleDateFormat newformatter = new SimpleDateFormat(DATE_FORMAT);
        newformatter.setLenient(false);

        while (calendar.getTime().before(edate))
        {
            Date result = calendar.getTime();
            dates.add(newformatter.format(result));
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    /**
     * This method will convert calendar date to java date
     * 
     * @param : timestamp
     * @return : date in java.util.date format
     */

    public static java.util.Date toDate(Calendar cal)
    {

        Timestamp timestamp = null;
        if (cal != null)
        {
            timestamp = new Timestamp(cal.getTimeInMillis());
        }

        long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
        return new java.util.Date(milliseconds);
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

    public List<FeederBean> getFeederList()
    {
        return feederList;
    }

    public void setFeederList(List<FeederBean> feederList)
    {
        this.feederList = feederList;
    }
    
    
   
    

}