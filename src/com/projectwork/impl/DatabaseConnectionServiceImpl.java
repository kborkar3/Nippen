package com.projectwork.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.iss.ketan.db.SQLBuilderIfc;

public class DatabaseConnectionServiceImpl {

	private static Logger logger = Logger.getLogger(DatabaseConnectionServiceImpl.class);

	private static DatabaseConnectionServiceImpl singleton = new DatabaseConnectionServiceImpl();

	public static DatabaseConnectionServiceImpl getInstance() {
		return singleton;
	}

	public Connection getConnection() throws SQLException {
		Context ctx = null;
		Connection con = null;

		Context initContext;
		try {
			initContext = new InitialContext();
			ctx = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/UCPPool");

			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return con;
	}

	public synchronized SQLBuilderIfc[] fireInsertSQL(String sql, Class sQLBuilderIfc) throws Exception {
		// sql = removeSemiColonFromSQL(sql);
		sql = sql.replace(';', ' ');

		logger.debug("SQL--> " + sql);

		ResultSet rs = null;
		Statement stmt = null;
		Connection con = null;
		SQLBuilderIfc[] acceptResult = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (con != null) {
				con.close();
			}

		}

		return acceptResult;

	}

	public synchronized SQLBuilderIfc[] fireSelectSQL(String sql, Class sQLBuilderIfc) throws Exception {
		// sql = removeSemiColonFromSQL(sql);
		sql = sql.replace(';', ' ');

		logger.debug("SQL--> " + sql);

		ResultSet rs = null;
		Statement stmt = null;
		Connection con = null;
		SQLBuilderIfc[] acceptResult = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			acceptResult = ((SQLBuilderIfc) sQLBuilderIfc.newInstance()).acceptResult(rs);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (con != null) {
				con.close();
			}

		}

		return acceptResult;

	}

	/**
	 * This method converts plain string to encrypted string
	 * 
	 * @param String
	 * @return String
	 * @throws Exception
	 */

	protected String getDBLevelEncryptedString(String plainString) {
		logger.info("LoginServiceImpl >> getDBLevelEncryptedString");

		StringEncryptionUtility encryptionUtilObj = new StringEncryptionUtility();
		return encryptionUtilObj.getDBLevelEncodedString(plainString);
	}

	/**
	 * This method will get parameter indexes for parameters name
	 * 
	 * @param String
	 * @return List<Integer>
	 * @throws Exception
	 */

	public Calendar getJAVADate(Timestamp date) {
		Calendar cal = null;
		if (date != null) {
			cal = Calendar.getInstance();
			cal.setTimeInMillis(date.getTime());

		}
		return cal;
	}

	/**
	 * This method checks for null value
	 * 
	 * @param :
	 *            String
	 * @return true: String is null
	 * @return false: String is not null
	 */

	protected boolean isEmpty(String value) {
		if (value == null || value.trim().equals("")) {
			return true;
		}
		return false;
	}

}
