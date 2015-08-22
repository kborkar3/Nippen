package com.projectwork.bean;

import java.util.Calendar;

public class HistoricReportBean
{
    private String storeID;

    private int meterIndex;

    private int parameterIndex;
    
    private String parameterName;

    private int value;

    private int consumptionValue;

    private int dataType;

    private Calendar dateTimeStamp;

    private Calendar createDate;

    private int meterVersion;

    private String feederName;

    private int meterId;

    private int compId;

    private int unitId;

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

    public int getValue()
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

    public int getMeterVersion()
    {
        return meterVersion;
    }

    public void setMeterVersion(int meterVersion)
    {
        this.meterVersion = meterVersion;
    }

    public String getFeederName()
    {
        return feederName;
    }

    public void setFeederName(String feederName)
    {
        this.feederName = feederName;
    }

    public int getMeterId()
    {
        return meterId;
    }

    public void setMeterId(int meterId)
    {
        this.meterId = meterId;
    }

    public int getCompId()
    {
        return compId;
    }

    public void setCompId(int compId)
    {
        this.compId = compId;
    }

    public int getUnitId()
    {
        return unitId;
    }

    public void setUnitId(int unitId)
    {
        this.unitId = unitId;
    }

    public String getParameterName()
    {
        return parameterName;
    }

    public void setParameterName(String parameterName)
    {
        this.parameterName = parameterName;
    }
}
