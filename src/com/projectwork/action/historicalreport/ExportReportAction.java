package com.projectwork.action.historicalreport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.dto.HistoricReportDTO;
import com.projectwork.impl.HistoricReportServiceImpl;


public class ExportReportAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = -2008727408704683578L;

    private HttpServletRequest request;

    private InputStream fileInputStream;
    
    private String fileName;

    private static Logger logger = Logger.getLogger(ExportReportAction.class);

    /**
     * Exports displayed report in pdf format
     * 
     * @return success: Report successfully exported
     * @return error: No records found for entered search criteria.
     * @throws Exception
     */

    
    public String execute() throws Exception
    {
        List<HistoricReportDTO> historicalReportdtoList = null;
        historicalReportdtoList = (List<HistoricReportDTO>)request.getSession().getAttribute("historicalReportdtoList");
        JasperReportBuilder report = null;

        if (!(historicalReportdtoList.isEmpty()))
        {
            HistoricReportServiceImpl historicReportServiceObject = new HistoricReportServiceImpl();
            report = historicReportServiceObject.exportHistoricalReport(historicalReportdtoList);
            
            if(report == null)
            {
                return RETURN_ERROR;
            }

            else
            {
                try
                {
                    java.util.Date date = new java.util.Date();

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    long currentTime = calendar.getTimeInMillis();
                    String currentTimeStamp = Long.toString(currentTime);
                    
                    String exportFileName = "historyReport" + currentTimeStamp + ".pdf";
                    String histReportPath = request.getRealPath("") + File.separator + exportFileName;
                    report.toPdf(new FileOutputStream(histReportPath));
                    fileInputStream = new FileInputStream(new File(histReportPath));
                    
                    fileName = new File(histReportPath).getName();
                    
                }
                catch (DRException e)
                {
                    logger.error("DRException occurred");
                }
                catch (FileNotFoundException e)
                {
                    logger.error("FileNotFoundException occurred");
                }

                return RETURN_SUCCESS;
            }
        }
            return RETURN_ERROR;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest)
    {
        this.request = httpServletRequest;
    }

    public InputStream getFileInputStream()
    {
        return fileInputStream;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

}
