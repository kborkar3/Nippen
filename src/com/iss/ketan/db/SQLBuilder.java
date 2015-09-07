package com.iss.ketan.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class is responsible for building quries and processing results
 * 
 * @author Ketan Borkar
 * 
 */
public abstract class SQLBuilder extends SQLBuilderUtil
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String COMA_STRING = SQL_COMMA;
	private HashMap fieldMethodArgTypeLink = null;

	protected static HashMap fieldMethodLink = new HashMap();

	private String getDeleteQry()
	{
		final StringBuffer qry = new StringBuffer("delete from ");
		qry.append(getTableName());
		qry.append(' ');
		String condition2 = getCondition();
		if (condition2 != null)
		{
			qry.append(condition2);
		}

		return qry.toString();
	}

	private String getUpdateQry()
	{
		final StringBuffer sb = new StringBuffer(SQL_UPDATE);
		sb.append(getTableName());
		sb.append(" set ");
		HashMap keyValues = getKeyValues();
		Iterator it = getKeyValues().keySet().iterator();

		while (it.hasNext())
		{
			Object fieldName = (Object) it.next();
			sb.append(fieldName);
			sb.append(SQL_CHAR_EQUAL_TO);
			sb.append(keyValues.get(fieldName));
			sb.append(' ');
			sb.append(COMA_STRING);

		}

		deleteLastComma(sb);
		String condition = getCondition();

		if (condition != null)
		{
			sb.append(condition);
		}
		ensureSemiColonAtEnd(sb);
		return sb.toString();
	}

	/**
	 * if fieldNames is null then all fields will be used Here Condition can be
	 * null
	 * 
	 */
	private String getSelectQry()
	{

		ArrayList fieldNames = getFieldNames();
		String condition = getCondition();

		final StringBuffer sb = new StringBuffer("select ");
		if (fieldNames != null)
		{

			for (int i = 0; i < fieldNames.size(); i++)
			{
				sb.append(fieldNames.get(i));
				sb.append(COMA_STRING);
			}

			// by mistake if arraylist is empty then also query should execute
			if (fieldNames.size() > 0)
			{
				deleteLastComma(sb);
			}
			else
			{
				sb.append(" * ");
			}

		}
		else
		{
			sb.append(" * ");
		}

		sb.append(" from ");
		sb.append(getTableName());
		sb.append(' ');

		if (condition != null)
		{
			sb.append(condition);
		}

		ensureSemiColonAtEnd(sb);
		return sb.toString();
	}

	private String getInsertQry()
	{

		final StringBuffer qry = new StringBuffer("insert into ");
		qry.append(getTableName());
		qry.append('(');

		HashMap keyValues = getKeyValues();

		Iterator it = keyValues.keySet().iterator();

		while (it.hasNext())
		{
			Object fieldName = (Object) it.next();
			qry.append(fieldName);
			qry.append(COMA_STRING);

		}

		deleteLastComma(qry);

		qry.append(')');
		qry.append(" values ");
		qry.append('(');

		it = keyValues.keySet().iterator();

		while (it.hasNext())
		{
			Object fieldNames = (Object) it.next();
			final Object object = keyValues.get(fieldNames);
			// System.out.println(fieldNames +" "+object);

			qry.append(object);
			qry.append(COMA_STRING);

		}
		deleteLastComma(qry);

		qry.append(')');
		qry.append(';');

		ensureSemiColonAtEnd(qry);

		return qry.toString();
	}

	public SQLBuilderIfc[] acceptResult(Object resultSet) throws Exception
	{
		if (resultSet instanceof ResultSet)
		{
			ResultSet rs = (ResultSet) resultSet;

			final ArrayList results = new ArrayList();
			final ArrayList fields = buildFields(rs);
			final HashMap links = getFieldMethodLink();

			final HashMap methodTypes = getFieldMethodParametersTypes();

			try
			{
				while (rs.next())
				{
					final SQLBuilderIfc holder = processResults();
					for (int i = 0; i < fields.size(); i++)
					{
						String colName = fields.get(i).toString();
						Object value = rs.getObject(colName);

						try
						{
							updateValue(links, methodTypes, holder, colName.toLowerCase(), value);
						}
						catch (Exception e)
						{

							e.printStackTrace();
						}
					}
					results.add(holder);
				}

			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			return (SQLBuilderIfc[]) results.toArray(new SQLBuilderIfc[results.size()]);
		}
		return null;
	}

	public String getSQL()
	{
		if (isSelectMode())
		{
			return getSelectQry();
		}
		else if (isUpdateMode())
		{
			return getUpdateQry();
		}
		else if (isDeleteMode())
		{
			return getDeleteQry();
		}
		return getInsertQry();
	}

	/**
	 * 
	 * subclass should return a instance of HashMap. considering memory aspect
	 * the instance should be static
	 * 
	 * @return
	 */
	public abstract HashMap getTypeHashMapInstance();

	public HashMap getFieldMethodParametersTypes()
	{

		try
		{
			if (fieldMethodArgTypeLink == null)
			{
				fieldMethodArgTypeLink = getTypeHashMapInstance();
				fieldMethodArgTypeLink = updateParaType(getFieldMethodLink(), this);
			}
			return fieldMethodArgTypeLink;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		// JOptionPane.showMessageDialog(null, "ERROR");
		return null;
	}

	public final StringBuffer deleteLastWord(StringBuffer sb, String wordToDelete)
	{
		int length = wordToDelete.length();
		for (int i = 0; i < length; i++)
		{
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb;
	}

	public final StringBuffer deleteLastComma(StringBuffer sb)
	{
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		return sb;
	}

	public final Object clone() throws CloneNotSupportedException
	{
		Object clone1 = super.clone();

		return clone1;

	}

	/**
	 * Implemetor can provide default behaviour for the unknowwn columns
	 * 
	 * @param val
	 */
	public void setAnyColumn(Object val)
	{
		throw new RuntimeException("Subclass need to implement this method");
	}

	public static void main(String[] args)
	{
		
		System.out.println("SQLBuilder.main()");
		SQLBuilder s = new SQLBuilder()
		{

			public HashMap getFieldMethodLink()
			{
				// TODO Auto-generated method stub
				return null;
			}

			public SQLBuilderIfc processResults()
			{
				// TODO Auto-generated method stub
				return null;
			}

			public String getTableName()
			{
				// TODO Auto-generated method stub
				return "test_table";
			}

			public HashMap getTypeHashMapInstance()
			{
				// TODO Auto-generated method stub
				return null;
			}
		};

		s.addFieldData("test", "ketan");
		s.setSelectMode();
		s.getSQL();
	}
}
