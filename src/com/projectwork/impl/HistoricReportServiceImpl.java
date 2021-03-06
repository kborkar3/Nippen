package com.projectwork.impl;

import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;

import org.apache.log4j.Logger;

import com.projectwork.bean.HistoricReportBean;
import com.projectwork.dto.HistoricReportDTO;
import com.projectwork.impl.sql.DataTableServiceSQLIfc;

public class HistoricReportServiceImpl extends DatabaseConnectionServiceImpl implements DataTableServiceSQLIfc
{
    private static Logger logger = Logger.getLogger(HistoricReportServiceImpl.class);

    LoginServiceImpl loginObject = new LoginServiceImpl();

    /**
     * This method will get historical report data for selected search criteria
     * 
     * @param String
     * @param List<String>
     * @param String
     * @param String
     * @return List<HistoricReportBean>
     * @throws Exception
     */

    public List<HistoricReportBean> getHistoricReportData(String feederName, List<String> selectedParametersList,
            String startDate, String endDate)
    {
        logger.info("HistoricReportServiceImpl >> getHistoricReportData");

        List<HistoricReportBean> historicalReportList = new ArrayList<HistoricReportBean>();
        List<String> selectedParameterIndexesList = new ArrayList<String>();
        List<Integer> selectedParameterIndexesIntegerList = new ArrayList<Integer>();

        FeederServiceImpl feederServiceImplObj = new FeederServiceImpl();

        // Get indexes for parameters selected from historical report search
        // page
        selectedParameterIndexesList = feederServiceImplObj.getParameterIndexesList(selectedParametersList);

        // Convert list of indexes in string format to list in integer format
        
        Iterator<String> iterator = selectedParameterIndexesList.iterator();

        while (iterator.hasNext())
        {
            String object = iterator.next();
            
            selectedParameterIndexesIntegerList.add(Integer.parseInt(object));
        }

        // Get record for each feeder-parameter pair

        historicalReportList = getHistoricReportDetails(feederName, selectedParameterIndexesIntegerList, startDate,
                endDate);

        return historicalReportList;
    }

    /**
     * This method will get historical report data for selected search criteria
     * 
     * @param String
     * @param Integer
     * @param String
     * @param String
     * @return HistoricReportBean
     * @throws Exception
     */

    public List<HistoricReportBean> getHistoricReportDetails(String feederName,
            List<Integer> selectedParameterIndexesIntegerList, String startDate, String endDate)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        short recordFound = 0;
        Connection con = null;
        Statement stmt = null;
        String sql = null;
        String parameterName = null;
        Integer parameterIndex = 0;

        List<HistoricReportBean> historicalReportList = new ArrayList<HistoricReportBean>();
        HistoricReportBean bean = new HistoricReportBean();

        try
        {
            for (int i = 0; i < selectedParameterIndexesIntegerList.size(); i++)
            {
                logger.info("HistoricReportServiceImpl >> getHistoricReportDetails");

                parameterIndex = selectedParameterIndexesIntegerList.get(i);

                // Get parameter name for parameter index and add to retrieved
                // bean.
                parameterName = loginObject.getParameterName(parameterIndex);

                con = getConnection();
                stmt = con.createStatement();

                /* sql = GET_HISTORIC_REPORT_DATA; */
                sql = GET_HISTORIC_REPORT_PARAMETER_DATA;

                ps = con.prepareStatement(sql);

                ps.setString(1, startDate);
                ps.setString(2, endDate);
                ps.setString(3, feederName);
                ps.setInt(4, parameterIndex);

                rs = ps.executeQuery();

                while (rs.next())
                {
                    bean = new HistoricReportBean();

                    bean.setValue(rs.getInt(DataTableServiceSQLIfc.DATA_TABLE_COLUMNS.VAL));
                    bean.setDateTimeStamp(getJAVADate(rs
                            .getTimestamp(DataTableServiceSQLIfc.DATA_TABLE_COLUMNS.DATE_TIME_STAMP)));
                    bean.setFeederName(rs.getString(DataTableServiceSQLIfc.FEEDER_PROFILE_COLUMNS.FEEDER_NAME));
                    bean.setParameterName(parameterName);
                    historicalReportList.add(bean);
                    recordFound++;
                }
            }

            if (recordFound == 0)
            {
                logger.error("No records found");
            }
        }

        catch (SQLException se)
        {
            logger.error("SQL Exception occured while searching for graph records " + se.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Exception occured while searching for graph records" + e.getMessage());
        }

        finally
        {
            try
            {
                rs.close();
                stmt.close();
                con.close();
            }
            catch (SQLException e)
            {
                logger.error("Exception in closing DB resources");
            }
        }
        return historicalReportList;
    }

    /**
     * This method will export historical report to pdf format
     * 
     * @param List<HistoricReportDTO>
     * @return boolean
     * @throws Exception
     */

    public JasperReportBuilder exportHistoricalReport(List<HistoricReportDTO> historicalReportdtoList)
    {
        logger.info("HistoricReportServiceImpl >> exportHistoricalReport");

        JasperReportBuilder report = DynamicReports.report();

        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);

        StyleBuilder titleStyle = stl.style(boldCenteredStyle).setBorder(stl.pen1Point())
                .setBackgroundColor(Color.LIGHT_GRAY);

        report.columns(
                Columns.column("Date", "dateStamp", DataTypes.stringType()).setHorizontalAlignment(
                        HorizontalAlignment.LEFT), Columns.column("Time", "timeStamp", DataTypes.stringType()),
                Columns.column("Feeder Name", "feederName", DataTypes.stringType()),
                Columns.column("Parameter Name", "parameterName", DataTypes.stringType()),
                Columns.column("Value", "value", DataTypes.stringType()))
                .title(Components.text("Historical Report Results").setHorizontalAlignment(HorizontalAlignment.CENTER)
                        .setStyle(titleStyle)).pageFooter(Components.pageXofY()).setDataSource(historicalReportdtoList);
        
        return report;
    }

}
