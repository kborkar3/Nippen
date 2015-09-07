/**
 * 
 */
package com.iss.ketan.imp;

import java.io.File;
import java.util.ArrayList;

import com.iss.ketan.ftp.FTPDetailsBean;

/**
 * @author ketan
 */
public class WebImportCommandBean implements WebImportProcessStateIfc
{
    private int processState = 0;

    private final ArrayList<ArrayList<String>> rawStringTableArray = new ArrayList<ArrayList<String>>();

    private final ArrayList<WebImpMeterDataIfc> meterDataArray = new ArrayList<WebImpMeterDataIfc>();

    private FTPDetailsBean fTPBean;

    private File localFileName = null;

    public WebImportCommandBean(FTPDetailsBean fTPBean)
    {
        this.setfTPBean(fTPBean);
    }

    public int getProcessState()
    {
        return processState;
    }

    public void setProcessState(int processState)
    {
        this.processState = processState;
    }

    public ArrayList<WebImpMeterDataIfc> getMeterDataArray()
    {
        return meterDataArray;
    }

    public FTPDetailsBean getfTPBean()
    {
        return fTPBean;
    }

    public void setfTPBean(FTPDetailsBean fTPBean)
    {
        this.fTPBean = fTPBean;
    }

    public ArrayList<ArrayList<String>> getRawStringTableArray()
    {
        return rawStringTableArray;
    }

    public File getLocalFileName()
    {
        return localFileName;
    }

    public void setLocalFileName(File localFileName)
    {
        this.localFileName = localFileName;
    }
}
