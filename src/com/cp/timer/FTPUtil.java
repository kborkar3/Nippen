package com.cp.timer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Vector;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * This utility class implements a method that downloads a directory completely
 * from a FTP server, using Apache Commons Net API.
 * 
 * @author www.codejava.net
 */
public class FTPUtil
{

	/**
	 * Download a whole directory from a FTP server.
	 * 
	 * @param ftpClient
	 *            an instance of org.apache.commons.net.ftp.FTPClient class.
	 * @param parentDir
	 *            Path of the parent directory of the current directory being
	 *            downloaded.
	 * @param currentDir
	 *            Path of the current directory being downloaded.
	 * @param saveDir
	 *            path of directory where the whole remote directory will be
	 *            downloaded and saved.
	 * @throws IOException
	 *             if any network or IO error occurred.
	 */
	public static Vector<File> downloadDirectory(FTPClient ftpClient, URL url) throws IOException
	{
		Vector<File> ans = new Vector<File>();
		String path = url.getPath();
		FTPFile[] subFiles = ftpClient.listFiles(path);

		if (subFiles != null && subFiles.length > 0)
		{
			for (FTPFile aFile : subFiles)
			{
				String currentFileName = aFile.getName();

				File filePath = File.createTempFile("FTP", ".txt");

				ans.add(filePath);
				// download the file
				// boolean success = downloadSingleFile(ftpClient,
				// "/alkesh/Hi.txt", filePath);
				boolean success = downloadSingleFile(ftpClient, url.getFile() + "/" + aFile.getName(), filePath);
				if (success)
				{
					System.out.println("DOWNLOADED the file: " + filePath);
				}
				else
				{
					System.out.println("COULD NOT download the file: " + filePath);
				}
			}
		}

		return ans;
	}

	/**
	 * Download a single file from the FTP server
	 * 
	 * @param ftpClient
	 *            an instance of org.apache.commons.net.ftp.FTPClient class.
	 * @param remoteFilePath
	 *            path of the file on the server
	 * @param savePath
	 *            path of directory where the file will be stored
	 * @return true if the file was downloaded successfully, false otherwise
	 * @throws IOException
	 *             if any network or IO error occurred.
	 */
	public static boolean downloadSingleFile(FTPClient ftpClient, String remoteFilePath, File downloadFile) throws IOException
	{
		OutputStream outputStream = null;

		try
		{
			outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			return ftpClient.retrieveFile(remoteFilePath, outputStream);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		finally
		{
			if (outputStream != null)
			{
				outputStream.close();
				return true;
			}
		}
	}
}