/**
 * 
 */
package com.iss.ketan.imp;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author ketan
 * 
 */
public class WebImpMeterData implements WebImpMeterDataIfc
{

	private String meterType = null;

	private final HashMap<String, WebImpParameterDataIfc> meterData = new HashMap<String, WebImpParameterDataIfc>();

	private HashMap<String, WebImpParameterDataIfc> getMeterData()
	{
		return meterData;
	}

	public WebImpParameterDataIfc getParameterData(String paramId)
	{
		return getMeterData().get(paramId);
	}

	public void addParameterData(String paramId, WebImpParameterDataIfc data)
	{
		getMeterData().put(paramId, data);
	}

	public Collection<WebImpParameterDataIfc> getAllParameterData()
	{
		return getMeterData().values();
	}

	public String getMeterType()
	{
		return meterType;
	}

	public void setMeterType(String meterType)
	{
		this.meterType = meterType;
	}

}
