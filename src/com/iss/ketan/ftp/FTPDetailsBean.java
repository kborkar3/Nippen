/**
 * 
 */
package com.iss.ketan.ftp;

/**
 * @author ketan
 * 
 */
public class FTPDetailsBean
{
	private String ftpPath;
	private String ftpArchievePath;
	private String ftpMethod;
	private String userName;
	private String password;
	private String companyID;

	public String getFtppath()
	{
		return ftpPath;
	}

	public void setFtppath(String ftppath)
	{
		this.ftpPath = ftppath;
	}

	public String getFtpMethod()
	{
		return ftpMethod;
	}

	public void setFtpMethod(String ftpMethod)
	{
		this.ftpMethod = ftpMethod;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Class:FTPDetailsBean\n");
		sb.append("FTP Path:");
		sb.append(getFtppath());
		sb.append('\n');

		sb.append("FTP Method:");
		sb.append(getFtpMethod());
		sb.append('\n');

		sb.append("FTP User Name:");
		sb.append(getUserName());
		sb.append('\n');

		sb.append("FTP Company ID:");
		sb.append(getCompanyID());
		sb.append('\n');

		sb.append("FTP Archieve Path:");
		sb.append(getFtpArchievePath());
		sb.append('\n');

		return sb.toString();
	}

	public String getCompanyID()
	{
		return companyID;
	}

	public void setCompanyID(String companyID)
	{
		this.companyID = companyID;
	}

	public String getFtpArchievePath()
	{
		return ftpArchievePath;
	}

	public void setFtpArchievePath(String ftpArchievePath)
	{
		this.ftpArchievePath = ftpArchievePath;
	}

}
