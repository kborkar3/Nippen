/**
 * 
 */
package com.iss.ketan.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.iss.ketan.db.SQLBuilderIfc;
import com.iss.ketan.db.custommeter.CustomParameterData;
import com.projectwork.impl.DatabaseConnectionServiceImpl;

/**
 * @author ketan
 * 
 */
public class WebImportHeaderBean implements WebImportSQLBuilderIfc
{

	public static final String meterSeparator = "DIN";
	public static final String STATUS_INDICATOR = "STATUS";

	private long currentMeterIndex = 0;
	private final ArrayList<Long> meterIndex = new ArrayList<Long>();
	private final ArrayList<Integer> parameterIndex = new ArrayList<Integer>();
	private final ArrayList<CustomParameterData> parameterConfig = new ArrayList<CustomParameterData>();
	private final ArrayList<Double> parameterData = new ArrayList<Double>();

	private final String companyID;

	/**
	 * 
	 */
	public WebImportHeaderBean(ArrayList<String> completeLine, String companyID)
	{
		this.companyID = companyID;
		processLine(completeLine);
	}

	private void processLine(ArrayList<String> completeLine)
	{
		final int size = completeLine.size();
		for (int i = 3; i < size; i++)
		{
			String token = completeLine.get(i);

			if (token.equalsIgnoreCase(meterSeparator))
			{
				// this will indicate DIN or status
				currentMeterIndex++;
				getMeterIndex().add(-1L);
				getParameterIndex().add(-1);
				getParameterConfig().add(null);
				getParameterData().add(0D);
				continue;
			}
			else if (token.equalsIgnoreCase(STATUS_INDICATOR))
			{
				// this will indicate DIN or status
				getMeterIndex().add(-1L);
				getParameterIndex().add(-1);
				getParameterConfig().add(null);
				getParameterData().add(0D);
				continue;
			}
			String[] split = token.split("_");

			final int parameterIndex;
			if (split.length < 2)
			{
				// this will be potential flaw
				parameterIndex = i;
			}
			else
			{
				parameterIndex = Integer.parseInt(split[1]);
			}

			// this will indicate DIN or status
			getMeterIndex().add(currentMeterIndex);
			this.getParameterIndex().add(parameterIndex);
			getParameterConfig().add(this.getParameterInfo(parameterIndex));
			getParameterData().add(0D);
		}
		System.out.println("WebImportHeaderBean.processLine()");

		// need to get meter big number so that we can persist it correctly.

		updateMeterIndex(getMeterIndex());

	}

	/**
	 * @param meterIndex2
	 */
	private void updateMeterIndex(ArrayList<Long> meterIndex2)
	{

		final String companyID2 = getCompanyID();
		for (int i = 0; i < meterIndex2.size(); i++)
		{
			long meterIdFromCSV = meterIndex2.get(i);

			if (meterIdFromCSV == -1)
			{
				continue;
			}

			try
			{
				final WebImportSQLBuilder sqlExecutor = new WebImportSQLBuilder(FEEDER_PROFILE);
				sqlExecutor.addFieldData(METER_ID, meterIdFromCSV);
				sqlExecutor.addFieldData(COMP_ID, companyID2);
				sqlExecutor.setMode(SQLBuilderIfc.SELECT_MODE);
				WebImportSQLBuilder executeSQL = (WebImportSQLBuilder) WebImportSQLBuilder.executeSQL(sqlExecutor);

				ArrayList<HashMap<String, Object>> results = executeSQL.getResults();

				if (results.size() > 0)
				{
					Object meterIndex = results.get(1).get(METER_INDEX);

					if (meterIndex == null)
					{
						createNewFeeder(sqlExecutor);
						continue;
					}
					else
					{
						long meterIndexL = Long.parseLong((String) meterIndex);
						meterIndex2.set(i, meterIndexL);
					}

				}
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}

		}

	}

	/**
	 * @param sqlExecutor
	 */
	private void createNewFeeder(WebImportSQLBuilder sqlExecutor)
	{
		sqlExecutor.addFieldData(FEEDER_NAME, "Feeder - " + new Random().nextInt());
		sqlExecutor.addFieldData(UNIT_ID, "0");
		sqlExecutor.addFieldData(METER_INDEX, System.currentTimeMillis());
		sqlExecutor.setMode(SQLBuilderIfc.INSERT_MODE);
		WebImportSQLBuilder.executeSQL(sqlExecutor);

	}

	private CustomParameterData getParameterInfo(int refToExcelIndex)
	{
		final CustomParameterData cmm = new CustomParameterData();
		try
		{

			cmm.setSelectMode();
			
			cmm.setRefToExcelIndex(refToExcelIndex);

			DatabaseConnectionServiceImpl con = DatabaseConnectionServiceImpl.getInstance();

			SQLBuilderIfc[] fireSelectSQL = (SQLBuilderIfc[]) con.fireSelectSQL(cmm.getSQL(), cmm.getClass());

			return (CustomParameterData) fireSelectSQL[0];

		}
		catch (Exception e)
		{
			// e.printStackTrace();

		}
		return cmm;
	}

	private ArrayList<Long> getMeterIndex()
	{
		return meterIndex;
	}

	private ArrayList<Integer> getParameterIndex()
	{
		return parameterIndex;
	}

	private ArrayList<CustomParameterData> getParameterConfig()
	{
		return parameterConfig;
	}

	public long getMeterIndex(int index)
	{
		return meterIndex.get(index);
	}

	public int getParameterIndex(int index)
	{
		return parameterIndex.get(index);
	}

	public CustomParameterData getParameterConfig(int index)
	{
		return parameterConfig.get(index);
	}

	private ArrayList<Double> getParameterData()
	{
		return parameterData;
	}

	public double getParameterData(int index)
	{
		return parameterData.get(index);
	}

	public void setParameterData(int index, double data)
	{
		if (index < 0)
		{
			return;
		}
		ArrayList<Double> parameterData2 = getParameterData();

		int size = parameterData2.size();

		if (!(index < size))
		{
			for (int i = size; i < index; i++)
			{
				parameterData2.add(null);
			}
		}

		parameterData2.add(index, data);
	}

	public int getDataSize()
	{
		return meterIndex.size();
	}

	public String getCompanyID()
	{
		return companyID;
	}

}
