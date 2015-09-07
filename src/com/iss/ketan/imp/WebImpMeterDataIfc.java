/**
 * 
 */
package com.iss.ketan.imp;

import java.util.Collection;

/**
 * @author ketan
 * 
 */
public interface WebImpMeterDataIfc
{

	public WebImpParameterDataIfc getParameterData(String paramId);

	public void addParameterData(String paramId, WebImpParameterDataIfc data);

	public Collection<WebImpParameterDataIfc> getAllParameterData();

	public String getMeterType();

	public void setMeterType(String meterType);

}
