package com.iss.ketan.imp;

import java.io.File;
import java.io.RandomAccessFile;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import com.iss.ketan.db.custommeter.CustomParameterData;
import com.iss.ketan.ftp.FTPDetailsBean;
import com.iss.ketan.util.commandprocess.AbstractProcess;

/**
 * 
 */

/**
 * @author ketan
 * 
 */
public class WebImportFirstLvLDataProcessr extends AbstractProcess implements WebImportSQLBuilderIfc
{
	public static final int FIRST_LVL_PROCESS = 1;
	public static final int SECOND_LVL_PROCESS = 2;
	public static final int THIRD_LVL_PROCESS = 3;

	private final WebImportProcessParameterData wippd = new WebImportProcessParameterData();

	@Override
	public boolean processTask()
	{
		WebImportCommandBean commandData = (WebImportCommandBean) getComand().getCommandData();

		File theLocalFileName =commandData.getLocalFileName();
		
		//commandData.setLocalFileName(theLocalFileName);
		// ArrayList<String> readCompleteFile =
		// readCompleteFile(theLocalFileName);
		ArrayList<String> readCompleteFile = readCompleteFile(theLocalFileName);

		ArrayList<ArrayList<String>> tokenLines = tokenLines(readCompleteFile);

		if (tokenLines.size() < 2)
		{
			return true;
		}
		commandData.getRawStringTableArray().clear();
		commandData.getRawStringTableArray().addAll(tokenLines);

		ArrayList<ArrayList<String>> raw2DArray = commandData.getRawStringTableArray();

		createFirstLevelMeterData(raw2DArray);

		return true;
	}

	/**
	 * @param raw2dArray
	 */
	private void createFirstLevelMeterData(ArrayList<ArrayList<String>> raw2dArray)
	{

		final ArrayList<String> headerRow = raw2dArray.get(0);

		final String companyID = getCompanyID();
		WebImportHeaderBean headerBean = processHeader(headerRow, companyID);

		HashMap<Long, SepcificParameterDataExtractor> map = new HashMap<Long, SepcificParameterDataExtractor>();

		int size = headerBean.getDataSize();

		for (int i = 1; i < size; i++)
		{
			long meterIndex = headerBean.getMeterIndex(i);

			if (meterIndex == -1)
			{
				continue;
			}

			SepcificParameterDataExtractor sepcificParameterDataExtractor = map.get(meterIndex);

			if (sepcificParameterDataExtractor == null)
			{
				sepcificParameterDataExtractor = new SepcificParameterDataExtractor(meterIndex, headerBean);
				map.put(meterIndex, sepcificParameterDataExtractor);
			}

		}

		for (int i = 1; i < raw2dArray.size(); i++)
		{
			// process each row

			try
			{
				final ArrayList<String> arrayList = raw2dArray.get(i);

				final long date = extractDateTime(arrayList);

				final int dataSize = headerBean.getDataSize();

				for (int j = 4; j < dataSize; j++)
				{
					final String data = arrayList.get(j);
					final long meterIndex = headerBean.getMeterIndex(j);
					final int parameterIndex = headerBean.getParameterIndex(j);
					final CustomParameterData parameterConfig = headerBean.getParameterConfig(j);
					SepcificParameterDataExtractor sepcificParameterDataExtractor = map.get(meterIndex);
					// since we need to apply MF first we can not complete
					// process at one go
					double processSingleParameterData = wippd.processSingleParameterData(sepcificParameterDataExtractor, data, parameterConfig, FIRST_LVL_PROCESS);
					headerBean.setParameterData(j, processSingleParameterData);

				}

				for (int j = 4; j < dataSize; j++)
				{
					final long meterIndex = headerBean.getMeterIndex(j);
					final int parameterIndex = headerBean.getParameterIndex(j);
					final CustomParameterData parameterConfig = headerBean.getParameterConfig(j);
					SepcificParameterDataExtractor sepcificParameterDataExtractor = map.get(meterIndex);

					double parameterData = headerBean.getParameterData(j);

					double processSingleParameterData = wippd.processSingleParameterData(sepcificParameterDataExtractor, parameterData, parameterConfig, SECOND_LVL_PROCESS);

					headerBean.setParameterData(j, processSingleParameterData);

				}

				for (int j = 4; j < dataSize; j++)
				{
					saveEachParameter(date, headerBean, j);

				}

			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}

	}

	/**
	 * @param date
	 * @param headerBean
	 * @param j
	 */
	private void saveEachParameter(final long date, WebImportHeaderBean headerBean, int j)
	{

		final long meterIndex = headerBean.getMeterIndex(j);
		final int parameterIndex = headerBean.getParameterIndex(j);
		final double parameterData = headerBean.getParameterData(j);
		
		System.out.println("WebImportFirstLvLDataProcessr.saveEachParameter()");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d H:m:s");

		WebImportSQLBuilder sql = new WebImportSQLBuilder(WebImportSQLBuilder.FEEDER_DATA_TABLE);

		sql.setMode(WebImportSQLBuilder.INSERT_MODE);
		

		sql.addFieldData(METER_INDEX, meterIndex);
		sql.addFieldData(PARAMETER_INDEX, parameterIndex);
		sql.addFieldData(PARAMETER_DATA, parameterData);
		
		String s="to_timestamp('"+sdf.format(date)+"','yyyy-mm-dd hh24:mi:ss')";
		sql.addFieldDataBlindly(DATE_TIME_STAMP, s);
		
		 s="to_timestamp('"+sdf.format(new Date(System.currentTimeMillis()))+"','yyyy-mm-dd hh24:mi:ss')";
		
		sql.addFieldDataBlindly(CREATE_TIME_STAMP, s);

		System.out.println("WebImportFirstLvLDataProcessr.saveEachParameter()" + sql.getSQL());
		 WebImportSQLBuilder.executeSQL(sql);
		// sql.addFieldData(me, meterIndex);

	}

	private String getCompanyID()
	{
		WebImportCommandBean commandData = (WebImportCommandBean) getComand().getCommandData();

		if (commandData == null)
		{
			return "0";
		}

		if (commandData.getfTPBean() != null)
		{
			String companyID = commandData.getfTPBean().getCompanyID();

			return companyID;

		}

		return "0";
	}

	/**
	 * @param headerRow
	 * @return
	 */
	private WebImportHeaderBean processHeader(ArrayList<String> headerRow, String companyId)
	{
		return new WebImportHeaderBean(headerRow, companyId);
	}

	private long extractDateTime(ArrayList<String> arrayList)
	{
		try
		{
			String string = arrayList.get(0) + " " + arrayList.get(1);

			DateFormat format = new SimpleDateFormat("d/M/y H:m:s");
			Date date = format.parse(string);
			System.out.println(date); // Sat Jan 02 00:00:00 GMT 2010
			return date.getTime();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * @param allLines
	 * @return
	 */
	private ArrayList<ArrayList<String>> tokenLines(ArrayList<String> allLines)
	{

		ArrayList<ArrayList<String>> ans = new ArrayList<ArrayList<String>>();

		for (int i = 0; i < allLines.size(); i++)
		{
			String string = allLines.get(i);

			String[] split = string.split(",");

			ArrayList<String> list = new ArrayList<String>();

			for (int j = 0; j < split.length; j++)
			{
				list.add(split[j]);
			}
			ans.add(list);

		}

		return ans;
	}

	/**
	 * @param fileName
	 * @return
	 */
	private ArrayList<String> readCompleteFile(File fileName)
	{
		final ArrayList<String> ans = new ArrayList<String>();
		try
		{
			RandomAccessFile raf = new RandomAccessFile(fileName, "r");

			String readLine = raf.readLine();

			while (readLine != null)
			{

				if (readLine.length() > 0)
				{
					ans.add(readLine);
				}
				readLine = raf.readLine();
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return ans;

	}

	/**
	 * @param getfTPBean
	 * @return
	 */
	private File getTheLocalFileName(FTPDetailsBean getfTPBean)
	{
		// CP Code

		// FTP
		return null;
	}

	public static void main(String[] args)
	{
		// WebImportFirstLvLDataProcessr webImportFirstLvLDataProcessr = new
		// WebImportFirstLvLDataProcessr();

		// webImportFirstLvLDataProcessr.setCommandObj(new
		// WebImportCommand(null));

		// webImportFirstLvLDataProcessr.processTask();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		String string = timestamp.toString();

		System.out.println("WebImportFirstLvLDataProcessr.main()" + string);
	}
}
