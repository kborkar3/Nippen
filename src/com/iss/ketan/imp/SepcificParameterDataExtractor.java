package com.iss.ketan.imp;

public class SepcificParameterDataExtractor
{
	private int startIndex = -1;
	private int endIndex = -1;
	private WebImportHeaderBean headerBean;

	public SepcificParameterDataExtractor(long meterIndex, WebImportHeaderBean headerBean)
	{
		this.headerBean = headerBean;

		final int dataSize = headerBean.getDataSize();

		for (int i = 0; i < dataSize; i++)
		{
			long meterIndex2 = headerBean.getMeterIndex(i);

			if (meterIndex == meterIndex2)
			{
				if (startIndex == -1)
				{
					startIndex = i;
				}
			}
			else if (startIndex > -1)
			{
				endIndex = i;
				break;
			}
		}
		
		if (startIndex>-1 && endIndex==-1)
		{
			endIndex=startIndex;
		}

	}

	/**
	 * @param refToExcel
	 * @return
	 */
	public double getValue(String refToExcel)
	{
		try
		{
			final int refToExcel2 = Integer.parseInt(refToExcel);

			if (refToExcel2 < 1)
			{
				return 1;
			}
			return getValue(refToExcel2);
		}
		catch (Exception e)
		{
		}
		return 1;

	}

	public double getValue(int refToExcel)
	{

		double ans = Double.MAX_VALUE;

		if (startIndex == -1 || endIndex == -1)
		{
			return ans;
		}

		try
		{
			for (int i = startIndex; i < endIndex; i++)
			{
				final int index = headerBean.getParameterIndex(i);

				if (index == refToExcel)
				{
					ans = headerBean.getParameterData(i);
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return ans;
	}
}