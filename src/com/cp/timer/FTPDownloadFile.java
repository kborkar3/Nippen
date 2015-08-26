package com.cp.timer;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import org.apache.commons.net.ftp.FTPClient;

import com.iss.ketan.ftp.FTPDetailsBean;

public class FTPDownloadFile
{

	public static void main(String[] args) throws MalformedURLException
	{
		FTPDetailsBean ftpDetails = new FTPDetailsBean();

		ftpDetails.setFtppath("ftp://inftp.skillnetinc.com:21/alkesh");
		ftpDetails.setUserName("aeo");
		ftpDetails.setPassword("aeo123");

		new FTPDownloadFile().downlaod(ftpDetails);
	}

	public Vector<File> downlaod(FTPDetailsBean ftpDetails) throws MalformedURLException
	{
		Vector<File> downloadDirectory = new Vector<File>();
		String server = ftpDetails.getFtppath();// "inftp.skillnetinc.com";

		URL url = new URL(server);

		String host = url.getHost();

		int port = url.getPort();

		if (port == -1)
		{
			port = 21;
		}

		// String server =ftpDetails.getFtppath();// "inftp.skillnetinc.com";
		String user = ftpDetails.getUserName();
		String pass = ftpDetails.getPassword();

		FTPClient ftpClient = new FTPClient();

		try
		{
			// connect and login to the server
			ftpClient.connect(host, port);
			ftpClient.login(user, pass);

			// use local passive mode to pass firewall
			ftpClient.enterLocalPassiveMode();

			System.out.println("Connected");

			downloadDirectory = FTPUtil.downloadDirectory(ftpClient, url);

			// log out and disconnect from the server
			ftpClient.logout();
			ftpClient.disconnect();

			System.out.println("Disconnected");
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}

		if (downloadDirectory == null)
		{
			downloadDirectory = new Vector<File>();
		}

		return downloadDirectory;
	}

}