/**
 * 
 */
package com.iss.ketan.imp;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;

import com.iss.ketan.db.SQLBuilder;
import com.iss.ketan.db.SQLBuilderIfc;
import com.projectwork.impl.DatabaseConnectionServiceImpl;

/**
 * @author ketan
 * 
 */
public class WebImportSQLBuilder extends SQLBuilder implements WebImportSQLBuilderIfc {
	public WebImportSQLBuilder() {
		this.tableName = null;
	}

	private final ArrayList<HashMap<String, Object>> results = new ArrayList<HashMap<String, Object>>();
	final String tableName;

	/**
	 * 
	 */
	public WebImportSQLBuilder(String tableName) {
		this.tableName = tableName;
	}

	public HashMap getFieldMethodLink() {
		return null;
	}

	public HashMap getTypeHashMapInstance() {
		return null;
	}

	public SQLBuilderIfc processResults() {
		return null;
	}

	public String getTableName() {
		return tableName;
	}

	/*
	 * (non-Javadoc) override to enable only select mode
	 */
	public final void setMode(int mode) {

		super.setMode(mode);
	}

	public static Serializable executeSQL(SQLBuilderIfc sql) {
		if (sql.getSQL().toLowerCase().startsWith("insert")) {
			try {
				executeInsertSQL(sql);
				return null;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		try {
			return DatabaseConnectionServiceImpl.getInstance().fireSelectSQL(sql.getSQL(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new SQLBuilderIfc[0];
	}

	public static Serializable executeInsertSQL(SQLBuilderIfc sql) {
		try {
			return DatabaseConnectionServiceImpl.getInstance().fireInsertSQL(sql.getSQL(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new SQLBuilderIfc[0];
	}

	public SQLBuilderIfc[] acceptResult(Object resultSet) throws Exception {
		if (resultSet instanceof ResultSet) {
			ResultSet rs = (ResultSet) resultSet;

			try {
				ResultSetMetaData metaData = rs.getMetaData();
				final int columnCount = metaData.getColumnCount();

				while (rs.next()) {

					HashMap<String, Object> row = new HashMap<String, Object>();
					for (int i = 1; i <= columnCount; i++) {
						String colName = metaData.getColumnName(i);
						Object value = rs.getObject(i);

						row.put(colName, value);

					}
					getResults().add(row);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return new SQLBuilderIfc[] { this };
	}

	public ArrayList<HashMap<String, Object>> getResults() {
		return results;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getSQL();
	}
}
