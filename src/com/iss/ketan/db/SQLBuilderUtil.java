package com.iss.ketan.db;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger; 

import com.iss.ketan.util.FormatData;
import com.iss.ketan.util.SQLConstants;

public abstract class SQLBuilderUtil implements SQLBuilderIfc, SQLConstants
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int mode = 0;

	/** this HashMap used for stores key values pair for insert query */
	private final HashMap keyValues = new HashMap();

	/** used to store field names for select qry */
	private ArrayList fieldNames = new ArrayList();

	/**
	 * condition is used for select,delete,update qry
	 */
	private String condition;

	private static final Logger logger = Logger.getLogger(SQLBuilderUtil.class);

	protected SQLBuilderUtil()
	{

	}

	public int getMode()
	{
		return mode;
	}

	/**
	 * if fieldnames are null then all fields will be returns if condition is
	 * null add results will be returns
	 * 
	 * @param fieldNames
	 * @param condition
	 */
	public void setSelectMode(ArrayList fieldNames, String condition)
	{
		setMode(SELECT_MODE);
		this.fieldNames = fieldNames;
		this.condition = condition;
	}

	public void setInsertMode()
	{
		setMode(INSERT_MODE);

	}

	/**
	 * will return all results This is like select * from table_name without
	 * Condition
	 */
	public void setSelectMode()
	{
		// final HashMap methodTypes = getFieldMethodParametersTypes();
		setSelectMode(null, null);
	}

	public void setSelectMode(String condition)
	{
		// final HashMap methodTypes = getFieldMethodParametersTypes();
		setSelectMode(null, condition);
	}

	public void setUpdateMode(String condition)
	{
		setMode(UPDATE_MODE);
		setCondition(condition);
	}

	public void setDeleteMode(String condition)
	{
		setMode(DELETE_MODE);
		setCondition(condition);
	}

	public void setDeleteMode()
	{

		setMode(DELETE_MODE);
	}

	public boolean isUpdateMode()
	{
		return getMode() == (UPDATE_MODE);
	}

	public boolean isDeleteMode()
	{
		return getMode() == DELETE_MODE;
	}

	public boolean isInsertMode()
	{
		return getMode() == INSERT_MODE;
	}

	public boolean isSelectMode()
	{
		return getMode() == SELECT_MODE;
	}

	public void addFieldData(String fieldName, Object value)
	{
		if (fieldName == null)
		{
			keyValues.remove(fieldName);
		}
		try
		{
			if (value instanceof String)
			{
				addFieldData(fieldName, (String) value);
			}
			else
				addFieldData(fieldName, value.toString());
		}
		catch (Exception e)
		{
			System.out.println(fieldName + " " + value + " eSQLBuilderUtil 94:" + e);
			// e.printStackTrace();
		}

	}

	public void addFieldData(String fieldName, long value)
	{
		setValue(fieldName, Long.toString(value));
	}

	public void addFieldData(String fieldName, double value)
	{
		if (value != Double.MAX_VALUE)
		{
			setValue(fieldName, FormatData.formatValue_WO_KMGT(value, "0.0000"));
		}
		else
			keyValues.remove(fieldName);

	}

	public void addFieldData(String fieldName, boolean value)
	{
		setValue(fieldName, new Boolean(value).toString());
	}

	public void addFieldData(String fieldName, int value)
	{
		setValue(fieldName, Integer.toString(value));
	}
	
	public void addFieldDataBlindly(String fieldName, String value)
	{

				setValue(fieldName, value);
	}


	public void addFieldData(String fieldName, String value)
	{

		if (value == null)
		{
			return;
		}
		else if (value.length() == 0)
		{
			return;
		}

		if (!value.startsWith("'"))
		{
			value = "'" + value;
		}
		if (!value.endsWith("'"))
		{
			value = value + "'";
		}
		setValue(fieldName, value);
	}

	private synchronized void setValue(Object fieldName, Object value)
	{
		if (value == null)
		{
			keyValues.remove(fieldName);
		}
		keyValues.put(fieldName, value);
	}

	protected void updateValue(HashMap links, HashMap methodTypes, SQLBuilderIfc holder, String colName, Object value)
	{

		if (value == null)
		{
			// System.out.println("Value is null returning");
			return;

		}
		colName = colName.trim();
		String methodNameToCall = (String) links.get(colName);

		if (methodNameToCall == null)
		{
			logger.fatal("Field Linking Error.Link is missing for-->" + colName);

			methodNameToCall = "setAnyColumn";
			return;

		}
		Class[] parameterTypes = (Class[]) methodTypes.get(methodNameToCall);

		if (parameterTypes == null)
		{
			parameterTypes = new Class[]
			{ Object.class };
		}

		Method m = null;
		try
		{
			m = holder.getClass().getMethod(methodNameToCall, parameterTypes);
		}
		catch (SecurityException e1)
		{

			e1.printStackTrace();
		}
		catch (NoSuchMethodException e1)
		{
			System.out.println("Some ERROR while calling:" + methodNameToCall);
			e1.printStackTrace();
		}

		// System.out.println("SQLBuilderUtil.updateValue()1111>>> "+value);
		final Method temp = m;
		try
		{
			Class types = temp.getParameterTypes()[0];

			if (!types.isInstance(value))
			{
				if (types.isPrimitive())
				{
					if (types.isAssignableFrom(Boolean.TYPE))
					{
						if (value.toString().equals("0"))
						{
							value = Boolean.FALSE;
						}
						else
						{
							value = Boolean.TRUE;
						}
					}
					else if (types.isAssignableFrom(Double.TYPE))
					{
						try
						{
							value = new Double(Double.parseDouble(value.toString()));
						}
						catch (Exception e)
						{
						}

					}

					else if (types.isAssignableFrom(Long.TYPE))
					{
						try
						{
							value = new Long((long) Double.parseDouble(value.toString()));
						}
						catch (Exception e)
						{
						}

					}
					else if (types.isAssignableFrom(Integer.TYPE))
					{
						try
						{
							value = new Integer((int) Double.parseDouble(value.toString()));
						}
						catch (Exception e)
						{
						}
					}
				}
			}

			// System.out.println("SQLBuilderUtil.updateValue()>>>  "+value);
			temp.invoke(holder, new Object[]
			{ value });
		}
		catch (Exception e)
		{
			logger.fatal(temp.getName() + " " + value + " methodNameToCall " + methodNameToCall, e);
			e.printStackTrace();

		}
	}

	protected ArrayList buildFields(ResultSet rs)
	{

		try
		{
			ArrayList fields = new ArrayList();
			final ResultSetMetaData rsmd = rs.getMetaData();
			final int colCount = rsmd.getColumnCount();
			for (int i = 0; i < colCount; i++)
			{
				fields.add(rsmd.getColumnName(i + 1));
			}
			return fields;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	protected StringBuffer ensureSemiColonAtEnd(StringBuffer sb)
	{
		if (sb.charAt(sb.length() - 1) != ';')
		{
			sb.append(';');
		}
		return sb;
	}

	public static HashMap updateParaType(HashMap methods, SQLBuilderIfc holder) throws Exception
	{

		final HashMap result = new HashMap();
		Iterator en = methods.values().iterator();
		Method m[] = holder.getClass().getMethods();
		while (en.hasNext())
		{
			Object object = (Object) en.next();

			for (int i = 0; i < m.length; i++)
			{
				if (m[i].getName().equals(object))
				{
					Class types[] = m[i].getParameterTypes();
					result.put(object, types);
				}
			}

		}

		return result;

	}

	public abstract SQLBuilderIfc processResults();

	public abstract String getTableName();

	public HashMap getKeyValues()
	{
		return keyValues;
	}

	public ArrayList getFieldNames()
	{
		return fieldNames;
	}

	public String getCondition()
	{
		if (condition == null && getMode() != UPDATE_MODE)
		{
			HashMap keyValues2 = getKeyValues();
			Iterator iterator = keyValues2.keySet().iterator();
			StringBuffer sb = new StringBuffer();

			boolean b = false;

			while (iterator.hasNext())
			{

				if (!b)
				{
					sb.append(" where ");
				}

				Object next = iterator.next();
				Object value = keyValues2.get(next);

				sb.append(next);
				sb.append(" = ");
				sb.append(getSafeValue(value));

				sb.append(" and ");
				b = true;
			}

			if (b)
			{
				int size = sb.length();
				sb.delete(size - 5, size);
			}

			return sb.toString();

		}
		return condition;
	}

	/**
	 * @param value
	 * @return
	 */
	private Object getSafeValue(Object value)
	{
		if (value instanceof String)
		{
			String str = value.toString();
			try
			{
				Double.parseDouble(str);
				return str;
			}
			catch (Exception e)
			{

			}

			try
			{
				Long.parseLong(str);
				return str;
			}
			catch (Exception e)
			{

			}
			StringBuffer sb = new StringBuffer();

			if (!((String) value).startsWith("\'"))
			{
				sb.append('\'');
			}

			sb.append(value);
			if (!((String) value).endsWith("\'"))
			{
				sb.append('\'');
			}
			return sb.toString();

		}
		return value;
	}

	public void setMode(int mode)
	{
		this.mode = mode;
	}

	public void setFieldNames(ArrayList fieldNames)
	{
		this.fieldNames = fieldNames;
	}

	public void setCondition(String condition)
	{
		if (condition != null)
		{
			if (condition.toLowerCase().indexOf(" where ") == -1)
			{
				condition = " where " + condition;
			}
		}

		this.condition = condition;
	}

	public void reset()
	{
		setMode(INSERT_MODE);
		setFieldNames(new ArrayList());
		setCondition(null);

	}

	/**
	 * this method always returns null subclass is responsible for implementing
	 * this method
	 * 
	 * @return null
	 */
	public String getCreateTableSQL()
	{
		return null;
	}

	public String[] getAlterTableSQL()
	{
		return null;
	}

}
