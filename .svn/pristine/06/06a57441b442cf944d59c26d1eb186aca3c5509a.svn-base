package com.iss.ketan.imp;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Vector;

import com.cp.timer.FTPDownloadFile;
import com.iss.ketan.ftp.FTPDetailsBean;
import com.iss.ketan.util.commandprocess.AbstractProcess;

/**
 * 
 */

/**
 * @author ketan
 * 
 */
public class WebImportFTPFileReader extends AbstractProcess
{
	@Override
	public boolean processTask()
	{
		WebImportCommandBean commandData = (WebImportCommandBean) getComand().getCommandData();

		FTPDetailsBean getfTPBean = commandData.getfTPBean();

		// this method will use of CP code and get the local file name
		// null indicates no need to process
		File fileName = getTheLocalFileName(getfTPBean);
		
		if (fileName==null)
		{
			return false;
		}
		commandData.setLocalFileName(fileName);
		ArrayList<String> allLines = readCompleteFile(fileName);

		ArrayList<ArrayList<String>> raw2DArray = tokenLines(allLines);

		commandData.getRawStringTableArray().addAll(raw2DArray);

		return true;
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
				list.add(split[i]);
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

			if (readLine != null)
			{
				if (readLine.length() > 0)
				{
					ans.add(readLine);
				}
			}

		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

		return ans;

	}

	/**
	 * @param getfTPBean
	 * @return
	 */
	private File getTheLocalFileName(FTPDetailsBean getfTPBean)
	{
		try
		{
			Vector<File> downlaod = new FTPDownloadFile().downlaod(getfTPBean);
			
			if (downlaod.size()>0)
			{
				return downlaod.get(0);
				
			}
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
