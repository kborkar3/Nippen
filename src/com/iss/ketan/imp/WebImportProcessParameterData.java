/**
 * 
 */
package com.iss.ketan.imp;

import com.iss.ketan.db.custommeter.CustomParameterData;

/**
 * @author ketan
 * 
 */
public class WebImportProcessParameterData
{

	/**
	 * @param sepcificParameterDataExtractor
	 * @param data
	 * @param parameterConfig
	 * @param level
	 */
	public double processSingleParameterData(SepcificParameterDataExtractor sepcificParameterDataExtractor, double data, CustomParameterData parameterConfig, int level)
	{
		
		
		double ans = data;

		if (parameterConfig == null)
		{
			return ans;
		}
		if (sepcificParameterDataExtractor == null)
		{
			return ans;
		}
		if (level == WebImportFirstLvLDataProcessr.FIRST_LVL_PROCESS)
		{
			final double mf = parameterConfig.getMultiplingFactor();
			if (mf != 0)
			{
				ans = data * mf;
			}

		}
		else if (level == WebImportFirstLvLDataProcessr.SECOND_LVL_PROCESS)
		{
			String multiply1pInternalID = parameterConfig.getMultiply1PInternalID();
			String multiply2pInternalID = parameterConfig.getMultiply2PInternalID();
			double value1 = sepcificParameterDataExtractor.getValue(multiply1pInternalID);
			double value2 = sepcificParameterDataExtractor.getValue(multiply2pInternalID);

			if (value1 != Double.MAX_VALUE)
			{
				ans = ans * value1;
			}
			if (value2 != Double.MAX_VALUE)
			{
				ans = ans * value2;
			}
		}
		else if (level == WebImportFirstLvLDataProcessr.THIRD_LVL_PROCESS)
		{
			String divide1pInternalID = parameterConfig.getDivide1PInternalID();
			String divide2pInternalID = parameterConfig.getDivide2PInternalID();
			double value1 = sepcificParameterDataExtractor.getValue(divide1pInternalID);
			double value2 = sepcificParameterDataExtractor.getValue(divide2pInternalID);

			if (value1 != Double.MAX_VALUE)
			{
				ans = ans * value1;
			}
			if (value2 != Double.MAX_VALUE)
			{
				ans = ans * value2;
			}
		}
		return ans;

	}

	public double processSingleParameterData(SepcificParameterDataExtractor sepcificParameterDataExtractor, String data, CustomParameterData parameterConfig, int level)
	{
		double rawValue = Double.MAX_VALUE;

		try
		{
			rawValue = Double.parseDouble(data);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return processSingleParameterData(sepcificParameterDataExtractor, rawValue, parameterConfig, level);

	}

}
