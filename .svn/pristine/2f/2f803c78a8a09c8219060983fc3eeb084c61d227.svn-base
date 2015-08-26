package com.iss.ketan.util;

import java.lang.reflect.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public final class KetanUtilitiesAddOn extends KetanUtilities
{
	/**
	This sort algorithm is based on sort with relation. That is a group of variables are required to be sorted taking into consideration of one variable.
	<br> Object array must be a array of class and fieldName is the name of variable on which sorting has to be done
	<br>Imp: DO not use this method to sort large array as this method requires significant time and processor
	*/
	public static Object sort(Object array, String fieldName) throws Exception
	{
		final Class class1=array.getClass();
		if (!class1.isArray())
		{
			System.out.println(" ERROR! Not a instance of Array");
			return array;
		}

		final int len=Array.getLength(array);


		Object elementToSort=Array.get(array,0).getClass().getField(fieldName).get(Array.get(array,0));


		final Object resultArray=Array.newInstance(elementToSort.getClass(), len);
		final Object bkArray=Array.newInstance(elementToSort.getClass(), len);
		final Object arraytoReturn=Array.newInstance(Array.get(array,0).getClass(), len);


		for (int i=0;i<len;i++)
		{
			Object meterConfiguration=Array.get(array,i);

			final Object temp=meterConfiguration.getClass().getField(fieldName).get(meterConfiguration);
			Array.set(resultArray,i,temp);
			Array.set(bkArray,i,temp);
		}

		java.util.Arrays.sort((Object[])resultArray);
		
		com.iss.ketan.util.KetanUtilities.printArray(bkArray);
		System.out.println(" -----");
		com.iss.ketan.util.KetanUtilities.printArray(resultArray);
		
		boolean [] bool=new boolean[len];
		for (int i=0;i<len;i++)
		{
			int index=search((Object[])bkArray,Array.get(resultArray,i),bool);						
			if (index==-1)	continue;

			bool[index]=true;		

			Object b=Array.get(array,index);


			Array.set(arraytoReturn,i,b);
		}
		return arraytoReturn;
	}

	public static int min(final int[] values)
	{
		int min=values[0];

		final int len=values.length;

		for (int i=0;i<len;i++)
		{
			if (min>values[i])
			{
				min=values[i];
			}
		}
		return min;
	}

	public static void autoAdjTableHeader(JTable table)
	{
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumnModel tcm=table.getColumnModel();

		int len=tcm.getColumnCount();

		for (int i=0;i<len;i++)
		{
			TableColumn tc=tcm.getColumn(i);
			TableCellRenderer tcr=tc.getCellRenderer();
			Object val=tc.getHeaderValue();

			Component c;
			if (tcr==null)		c=table;			
			else c=tcr.getTableCellRendererComponent(table, val,false,false,0,0);

			FontMetrics fm=c.getFontMetrics(c.getFont());
			int len1=fm.stringWidth(val.toString());
			
			len1+=10;
			if (len1<100)
			{
				len1=100;
			}
			//tc.setMaxWidth(len1);
			//tc.setMinWidth(len1);
			
			tc.setPreferredWidth(len1);
		}
		//return table;

	}
}