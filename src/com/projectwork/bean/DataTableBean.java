package com.projectwork.bean;

import java.util.Calendar;

public class DataTableBean
{
    private String storeID;
    private int meterIndex;         
    private int parameterIndex;
    private String parameterName;
    private float value;                 
    private int consumptionValue;   
    private int dataType;           
    private Calendar dateTimeStamp;     
    private Calendar createDate;
    
    public String getStoreID()
    {
        return storeID;
    }
    public void setStoreID(String storeID)
    {
        this.storeID = storeID;
    }
    public int getMeterIndex()
    {
        return meterIndex;
    }
    public void setMeterIndex(int meterIndex)
    {
        this.meterIndex = meterIndex;
    }
    public int getParameterIndex()
    {
        return parameterIndex;
    }
    public void setParameterIndex(int parameterIndex)
    {
        this.parameterIndex = parameterIndex;
    }
    public float getValue()
    {
        return value;
    }
    public void setValue(int value)
    {
        this.value = value;
    }
    public int getConsumptionValue()
    {
        return consumptionValue;
    }
    public void setConsumptionValue(int consumptionValue)
    {
        this.consumptionValue = consumptionValue;
    }
    public int getDataType()
    {
        return dataType;
    }
    public void setDataType(int dataType)
    {
        this.dataType = dataType;
    }
    public Calendar getDateTimeStamp()
    {
        return dateTimeStamp;
    }
    public void setDateTimeStamp(Calendar dateTimeStamp)
    {
        this.dateTimeStamp = dateTimeStamp;
    }
    public Calendar getCreateDate()
    {
        return createDate;
    }
    public void setCreateDate(Calendar createDate)
    {
        this.createDate = createDate;
    }
    public String getParameterName()
    {
        return parameterName;
    }
    public void setParameterName(String parameterName)
    {
        this.parameterName = parameterName;
    }
    public void setValue(float value)
    {
        this.value = value;
    }         
}
