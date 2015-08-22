package com.iss.ketan.imp;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.iss.ketan.ftp.FTPDetailsBean;
import com.iss.ketan.util.commandprocess.CommandController;
import com.iss.ketan.util.commandprocess.ProcessIfc;

/**
 * 
 */

/**
 * 
 */
public final class WebImporInitalizer extends Object
{

	private ArrayList<FTPDetailsBean> ftpDetails = new ArrayList<FTPDetailsBean>();

	public WebImporInitalizer()
	{
		loadFTPConfigs();
	}

	/**
	 * 
	 */
	private void loadFTPConfigs()
	{

		FTPDetailsBean ftpDetails1 = new FTPDetailsBean();

		ftpDetails1.setFtppath("ftp://inftp.skillnetinc.com:21/alkesh");
		ftpDetails1.setUserName("aeo");
		ftpDetails1.setPassword("aeo123");

		ftpDetails.add(ftpDetails1);

	}

	public static WebImporInitalizer getInstance()
	{
		return instance;
	}

	public void startImportTask()
	{
		loadFTPConfigs();

	}

	private static final WebImporInitalizer instance = new WebImporInitalizer();

	public static void main(String[] args)
	{
		ArrayList<FTPDetailsBean> ftpDetails2 = WebImporInitalizer.getInstance().getFtpDetails();
		final int size = ftpDetails2.size();
	//	ExecutorService executor = Executors.newFixedThreadPool(size);

		for (int i = 0; i < size; i++)
		{

			WebImportCommand cmd = new WebImportCommand(WebImporInitalizer.getInstance().getFtpDetails().get(i));

			Vector<ProcessIfc> processes = new Vector<ProcessIfc>();
			processes.add(new WebImportFTPFileReader());
			processes.add(new WebImportFirstLvLDataProcessr());

			CommandController cc = new CommandController(cmd, "test", processes);
			   cc.runTask();
			//executor.execute(cc);
		}
		/*executor.shutdown();
		while (!executor.isTerminated())
		{
		}*/

	}

	public ArrayList<FTPDetailsBean> getFtpDetails()
	{
		return ftpDetails;
	}

	public void setFtpDetails(ArrayList<FTPDetailsBean> ftpDetails)
	{
		this.ftpDetails = ftpDetails;
	}
}
