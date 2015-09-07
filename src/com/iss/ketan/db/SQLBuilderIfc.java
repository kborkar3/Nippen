package com.iss.ketan.db;

import java.io.Serializable;
import java.util.HashMap;

public interface SQLBuilderIfc extends Serializable, Cloneable
{

	/**
	 * modes
	 * 
	 */
	public static final int INSERT_MODE = 0;
	public static final int SELECT_MODE = 1;
	public static final int UPDATE_MODE = 2;
	public static final int DELETE_MODE = 3;

	public String getSQL();

	public boolean isUpdateMode();

	public boolean isInsertMode();

	public boolean isSelectMode();

	public boolean isDeleteMode();

	public SQLBuilderIfc[] acceptResult(Object resultSet) throws Exception;

	public HashMap getFieldMethodLink();

	public HashMap getFieldMethodParametersTypes();

}
