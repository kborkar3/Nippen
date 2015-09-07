package com.iss.ketan.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.print.PageFormat;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.RepaintManager;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.iss.ketan.util.math.MaxMin;

/**
 * 
 * Please use getInstance Method to getRef to this class. Due to earlier
 * implemention it is not possible to make it singleton
 * 
 */
public class KetanUtilities extends Object
{

	private static final KetanUtilitiesAddOn KETAN_UTILITIES_ADD_ON = new KetanUtilitiesAddOn();

	private static Logger logger = Logger.getLogger(KetanUtilities.class);

	protected KetanUtilities()
	{

	}

	private static final char dash = '-', zero = '0', colon = ':';

	/**
	 * This method creates new Vector and Adds All elements in newly created
	 * vector
	 * 
	 * @param An
	 *            object array.
	 * @return Vector containing all elements of object array
	 */
	public static Vector convertToVector(Object[] anArray)
	{
		return getInstance().convertToVectorInstance(anArray);
	}

	public Vector convertToVectorInstance(Object[] anArray)

	{
		if (anArray == null)
			return null;

		final int len = anArray.length;

		final Vector v = new Vector(len);

		for (int i = 0; i < len; i++)
			v.addElement(anArray[i]);

		return v;
	}

	/**
	 * This method creates new Vector and Adds Vector by calling
	 * convertToVector(Object[i])
	 * 
	 * 
	 * @param An
	 *            object array.
	 * @return Vector containing Vectors of object array
	 */

	public static Vector convertToVector(Object[][] anArray)
	{
		return getInstance().convertToVectorInstance(anArray);

	}

	public Vector convertToVectorInstance(Object[][] anArray)
	{

		if (anArray == null)
			return null;

		final int len = anArray.length;

		final Vector v = new Vector(len);

		for (int i = 0; i < len; i++)
			v.addElement(convertToVector(anArray[i]));

		return v;
	}

	/**
	 * Disables Double Buffering
	 * 
	 * 
	 * @param A
	 *            Component
	 */

	public static final void disableDoubleBuffering(final Component c)
	{
		final RepaintManager currentManager = RepaintManager.currentManager(c);
		currentManager.setDoubleBufferingEnabled(false);
	}

	/**
	 * Enables Double Buffering
	 * 
	 * 
	 * @param A
	 *            Component
	 */

	public static final void enableDoubleBuffering(final Component c)
	{
		final RepaintManager currentManager = RepaintManager.currentManager(c);
		currentManager.setDoubleBufferingEnabled(true);
	}

	/**
	 * This method returns top level parent component
	 * 
	 * if the compoent is not added in any container then this returns a window
	 * Object by calling JOptionPane.getRootFrame() method
	 * 
	 * 
	 * @param A
	 *            component.
	 * @return Window
	 */

	public static Window getParent(Component comp)
	{

		Window w = (Window) getParent1(comp);

		return (w == null) ? JOptionPane.getRootFrame() : w;

	}

	private static Component getParent1(Component parent)
	{

		if (parent == null)
			return null;

		if (parent instanceof Dialog || parent instanceof Frame)
		{
			return parent;
		}
		else
		{
			return getParent1(parent.getParent());
		}

	}

	/**
	 * This method returns total number meters This method first calls to
	 * Hashtble.keys(); then it loads the hashtable (one by one) for the keys
	 * present by adding .sets to keys then quries the hashtable for "metertype"
	 * gets the int by parsing to int.
	 * <p>
	 * increases meter no if int is same as meterno. If inconsistancy found
	 * returns Integer.MAX_VALUE; This method has been deprecated use
	 * FeederConfiguration.countMeters
	 * 
	 * 
	 * 
	 * 
	 * @param int MeterType.
	 * @param Hashtable
	 *            .
	 * @return int
	 */

	
	/**
	 * Prints a line text on graphics object for printing
	 * 
	 * 
	 * 
	 * @param String
	 *            which to be printed
	 * @param Graphics2D
	 *            on which string to be printed
	 * @param PageFormat
	 * @param int Y co-ordinate
	 * @param int alignemnt user
	 *        SwingUtilities.LEFT,SwingUtilities.CENTER,SwingUtilities.RIGHT
	 * @param boolean shouldTranslate true translates Graphics2D by 0, Y
	 *        coordinate
	 * 
	 */

	public static void printLineOfText(final String toPrint, Graphics2D g, PageFormat pf, final int y, int alignement, final boolean shouldTranslate)
	{
		final FontMetrics fm = g.getFontMetrics();
		final double pageWidth = pf.getImageableWidth();
		final int stringWidth = fm.stringWidth(toPrint);

		double x = 0;

		if (alignement == SwingUtilities.CENTER)
		{
			x = (pageWidth - stringWidth) / 2D;
			x += (pf.getImageableX() / 2);
		}
		else if (alignement == SwingUtilities.LEFT)
		{
			x = pf.getImageableX();
		}
		else if (alignement == SwingUtilities.RIGHT)
		{
			x = pageWidth - stringWidth;
			x += pf.getImageableX();
		}
		else
			x = 0;

		g.drawString(toPrint, (float) x, y);

		if (shouldTranslate)
		{
			g.translate(0, fm.getHeight() + y);
		}
	}

	/**
	 * This method removes the object from an Object[]
	 * <p>
	 * returns Object[] whose length will be sourceArray.length-1
	 * 
	 * This method call equals method to compare and removes first equal element
	 * from source array
	 * 
	 * 
	 * 
	 * 
	 * @param Object
	 *            [] Source Array
	 * @param Object
	 *            To remove
	 * @return Object[] by removing
	 */

	public static Object removeElementFromArray(final Object oldArray, final Object elementToRemove)
	{

		final int oldLen = Array.getLength(oldArray);

		int indexToRemove = -1;

		for (int i = 0; i < oldLen; i++)
		{
			if (Array.get(oldArray, i).equals(elementToRemove))
			{
				indexToRemove = i;
				break;
			}
		}

		if (indexToRemove == -1)
			return oldArray;

		return removeElementFromArray(oldArray, indexToRemove);

	}

	/**
	 * Adds element to an array
	 * 
	 * @param oldArray
	 *            An array to which element to add
	 * @return newly created array
	 */

	public static Object addElementToArray(final Object oldArray, final Object elementToAdd)
	{
		final Object resultArray = Array.newInstance(oldArray.getClass().getComponentType(), 1);
		Array.set(resultArray, 0, elementToAdd);

		return addArrayToArray(oldArray, resultArray);

	}

	/**
	 * sets the array length by preserving the array elements If targetSize is
	 * less than the actual length array size will be reduce and if the target
	 * size is more then the array length will be incremented.
	 * 
	 * @param oldArray
	 *            array whole length has to be changed targetSize
	 * @return newly created array
	 */

	public static Object redimPreserveArray(final Object oldArray, int targetSize)
	{
		final Class typeClass = oldArray.getClass().getComponentType();
		final Object resultArray = Array.newInstance(typeClass, targetSize);
		final int oldLen = Array.getLength(oldArray);
		final int len = (targetSize > oldLen) ? oldLen : targetSize;
		System.arraycopy(oldArray, 0, resultArray, 0, len);
		return resultArray;
	}

	/**
	 * This method clones array by creating new instance.<br>
	 * If srcArr is Vector then it creates new Vector and put elements
	 */
	public static Object cloneArray(final Object srcArr)
	{
		if (srcArr instanceof Vector)
		{
			Vector old = (Vector) srcArr;
			Vector ans = new Vector();
			final int size = old.size();

			for (int i = 0; i < size; i++)
			{
				Object obj = old.elementAt(i);
				if (obj instanceof Vector)
				{
					obj = cloneArray(obj);
				}

				ans.add(obj);

			}

			return ans;

		}
		final Class typeClass = srcArr.getClass().getComponentType();
		final int len = Array.getLength(srcArr);

		final Object resultArray = Array.newInstance(typeClass, len);

		System.arraycopy(srcArr, 0, resultArray, 0, len);

		return resultArray;

	}

	public static double[] addElementToDouble(final double[] oldArray, final double element)
	{
		return (double[]) addArrayToArray(oldArray, new double[]
		{ element }, -1);
	}

	public static int[] addElementToInt(final int[] oldArray, final int element)
	{
		return (int[]) addElementToInt(oldArray, element, -1);
	}

	public static int[] addElementToInt(final int[] oldArray, final int element, final int location)
	{
		return (int[]) addArrayToArray(oldArray, new int[]
		{ element }, location);
	}

	public static long[] addElementToLong(final long[] oldArray, final long element)
	{
		return addElementToLong(oldArray, element, -1);
	}

	public static long[] addElementToLong(final long[] oldArray, final long element, final int location)
	{
		return (long[]) addArrayToArray(oldArray, new long[]
		{ element }, location);
	}

	public static char[] addElementToChar(final char[] oldArray, final char element)
	{
		return (char[]) addArrayToArray(oldArray, new char[]
		{ element });
	}

	public static Object addArrayToArray(final Object oldArray, final Object anotherArray)
	{
		return addArrayToArray(oldArray, anotherArray, -1);
	}

	public static Object addArrayToArray(final Object oldArray, final Object anotherArray, int location)
	{
		final Class typeClass = oldArray.getClass().getComponentType();

		final int oldLen = Array.getLength(oldArray);
		final int newLen = Array.getLength(anotherArray);

		final int len = oldLen + newLen;

		final Object resultArray = Array.newInstance(typeClass, len);

		if (location == -1)
		{
			location = oldLen;
		}

		System.arraycopy(oldArray, 0, resultArray, 0, location);
		System.arraycopy(anotherArray, 0, resultArray, location, newLen);
		System.arraycopy(oldArray, location, resultArray, location + newLen, oldLen - location);

		return resultArray;

	}

	public static Object removeElementFromArray(final Object oldArray, final int indexToRemove)
	{
		final int len = Array.getLength(oldArray);

		final Class typeClass = oldArray.getClass().getComponentType();
		final Object newArray = Array.newInstance(typeClass, len - 1);

		System.arraycopy(oldArray, 0, newArray, 0, indexToRemove);
		System.arraycopy(oldArray, indexToRemove + 1, newArray, indexToRemove, len - indexToRemove - 1);

		return newArray;

	}

	/**
	 * Removes duplicate values from int array
	 */
	public static int[] removeDuplicatesFromIntArray(int[] oldArray)
	{
		int len = oldArray.length;

		for (int i = 0; i < len; i++)
		{
			final int meterID = oldArray[i];
			for (int j = i + 1; j < len; j++)
			{
				if (meterID == oldArray[j])
				{
					oldArray = (int[]) removeElementFromArray(oldArray, j);
					len = oldArray.length;
					i = 0;
				}
			}
		}

		return oldArray;
	}
	
	/**
	 * Removes duplicate values from int array
	 */
	public static double[] removeDuplicatesFromDoubleArray(double[] oldArray)
	{
		int len = oldArray.length;

		for (int i = 0; i < len; i++)
		{
			final double meterID = oldArray[i];
			for (int j = i + 1; j < len; j++)
			{
				if (meterID == oldArray[j])
				{
					oldArray = (double[]) removeElementFromArray(oldArray, j);
					len = oldArray.length;
					i = 0;
				}
			}
		}

		return oldArray;
	}


	public static File getFileByDate_Time()
	{
		return getFileByDate_Time(false);
	}

	public static File getFileByDate_Time(boolean isAlarm)
	{
		final String s = ((isAlarm) ? "export" + File.separator + "alarm" : "export" + File.separator + "report");
		return getFileByDate_Time(s);
	}

	public static File getFileByDate_Time(final String folder)
	{
		final Calendar cal = Calendar.getInstance();
		final String filename = Integer.toString(cal.get(Calendar.DATE)) + "-" + Integer.toString(cal.get(Calendar.MONTH) + 1) + "-" + Integer.toString(cal.get(Calendar.YEAR)) + "@" + Integer.toString(cal.get(Calendar.HOUR_OF_DAY)) + "." + Integer.toString(cal.get(Calendar.MINUTE)) + "." + Integer.toString(cal.get(Calendar.SECOND)) + ".xls";

		if (folder != null)
		{
			final String folderName = folder + File.separator;
			new File(folderName).mkdirs();
			return new File(folderName + filename);
		}
		else
			return new File(filename);
	}

	/*public static void copyKey(final Object oldMeterNo, final Object newMeterNo, final File fileName)
	{
		final Object obj = IO.readAny(oldMeterNo, fileName);
		IO.writeAny(newMeterNo, obj, "scale");
	}*/
/*
	public static void copyKey(int oldMeterNo, final int newMeterNo, final String fileName)
	{
		copyKey(Integer.toString(oldMeterNo), Integer.toString(newMeterNo), new File(fileName));
	}

	public static void copyKey(String oldMeterNo, String newMeterNo, final String fileName)
	{
		copyKey(oldMeterNo, newMeterNo, new File(fileName));
	}*/

	public static boolean copyFile(final String source, final String destiny) // copy
	// file
	// but
	// does
	// not
	// create
	// dirs
	{
		return copyFile(new File(source), new File(destiny));
	}

	public static boolean copyFileOrFolder(final File source, final File destiny)
	{

		if (source.isDirectory())
		{
			destiny.mkdir();

			final File[] f = source.listFiles();
			final int len = f.length;
			for (int i = 0; i < len; i++)
			{
				final File temp = f[i];

				if (temp.isFile())
				{
					copyFile(temp, new File(destiny.getAbsolutePath() + "\\" + temp.getName()));
				}
				else
					copyFileOrFolder(temp, new File(destiny.getAbsolutePath() + "\\" + temp.getName()));
			}

		}
		else
			return copyFile(source, destiny);
		return true;

	}

	// copy
	// file
	// but
	// does
	// not
	// create
	// dirs
	public static boolean copyFile(final File source, final File destiny)
	{
		if (source.isDirectory() || destiny.isDirectory())
			return false;

		if (source.equals(destiny))
			return false;

		try
		{
			destiny.createNewFile();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			e = null;
		}

		try
		{
			final FileInputStream fin = new FileInputStream(source);
			final long len = source.length();

			destiny.createNewFile();
			final FileOutputStream fout = new FileOutputStream(destiny);

			final byte b[] = new byte[1024];

			for (long i = 0; i < len; i += 1024)
			{
				final int readCount = fin.read(b);

				if (readCount == 1024)
				{
					fout.write(b);
				}
				else
				{
					fout.write(b, 0, readCount);
					break;
				}
			}
			fin.close();
			fout.close();

			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * Converts java.awt.Color into HTML Style color @param Color @return String
	 */
	public static String getHtmlColor(final Color c)
	{
		if (c == null)
			return null;

		final StringBuffer sb = new StringBuffer(10);
		sb.append('#');
		final int red = c.getRed();
		final int green = c.getGreen();
		final int blue = c.getBlue();

		if (red < 10)
			sb.append(zero);
		sb.append(Integer.toHexString(red));

		if (green < 10)
			sb.append(zero);
		sb.append(Integer.toHexString(green));

		if (blue < 10)
			sb.append(zero);
		sb.append(Integer.toHexString(blue));

		return sb.toString().toUpperCase();
	}

	public static String getDateStr(final Calendar m)
	{
		return getDateStr(m, new StringBuffer()).toString();
	}

	public static String getTimeStr(final Calendar m)
	{
		return getTimeStr(m, new StringBuffer()).toString();
	}

	public static StringBuffer getTimeStr(final Calendar m, final StringBuffer sb)
	{
		final int hh = m.get(Calendar.HOUR_OF_DAY);
		final int mm = m.get(Calendar.MINUTE);
		final int ss = m.get(Calendar.SECOND);

		if (hh < 10)
			sb.append(zero);
		sb.append(hh);
		sb.append(colon);

		if (mm < 10)
			sb.append(zero);
		sb.append(mm);
		sb.append(colon);

		if (ss < 10)
			sb.append(zero);
		sb.append(ss);
		return sb;
	}

	public static StringBuffer getDateStr(final Calendar m, final StringBuffer sb)
	{
		if (m == null)
			return null;
		if (sb == null)
			return null;

		final int dd = m.get(Calendar.DATE);
		final int month = m.get(Calendar.MONTH) + 1;
		final int year = m.get(Calendar.YEAR);

		if (dd < 10)
			sb.append(zero);
		sb.append(dd);
		sb.append(dash);

		if (month < 10)
			sb.append(zero);
		sb.append(month);
		sb.append(dash);

		sb.append(year);

		return sb;

	}

	public static String getDateTimeStr(final Calendar m)
	{
		return getDateTimeStr(m, new StringBuffer()).toString();
	}

	public static StringBuffer getDateTimeStr(final Calendar m, final StringBuffer sb)
	{
		if (m == null)
			return null;

		getDateStr(m, sb);
		sb.append(' ');
		getTimeStr(m, sb);

		return sb;

	}

	public static final Calendar resetTimeTo0(final Calendar c)
	{
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c;
	}

	public static int search(final int[] anArray, int toSearch)
	{
		return search(anArray, toSearch, -1);
	}

	public static int search(final int[] anArray, int toSearch, int startPos)
	{
		try
		{
			final int len = anArray.length;
			startPos++;

			for (int i = startPos; i < len; i++)
			{
				if (anArray[i] == toSearch)
				{
					return i;
				}
			}
			return -1;

		}
		catch (Exception e)
		{
			// e.printStackTrace();e=null;
		}
		return -1;

	}

	public static int search(final char[] anArray, char toSearch)
	{
		final int len = anArray.length;

		for (int i = 0; i < len; i++)
		{
			if (anArray[i] == toSearch)
			{
				return i;
			}
		}
		return -1;
	}

	public static int search(final float[] anArray, final float toSearch)
	{
		final int len = anArray.length;

		for (int i = 0; i < len; i++)
		{
			if (anArray[i] == toSearch)
			{
				return i;
			}
		}
		return -1;
	}

	public static int search(final double[] anArray, final double toSearch)
	{
		final int len = anArray.length;

		for (int i = 0; i < len; i++)
		{
			if (anArray[i] == toSearch)
			{
				return i;
			}
		}
		return -1;
	}

	public static int search(final long[] anArray, final long toSearch)
	{
		return search(anArray, toSearch, -1);
	}

	public static int search(final long[] anArray, final long toSearch, int startPos)
	{
		try
		{
			final int len = anArray.length;
			startPos++;
			for (int i = startPos; i < len; i++)
			{
				if (anArray[i] == toSearch)
				{
					return i;
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			e = null;
		}

		return -1;
	}

	public static int search(final Object[] anArray, Object toSearch)
	{
		return search(anArray, toSearch, 0, null);
	}

	public static int search(final Object[] anArray, Object toSearch, boolean[] elementsToIgnore)
	{
		return search(anArray, toSearch, 0, elementsToIgnore);
	}

	public static int search(final Object[] anArray, Object toSearch, int startPos)
	{
		return search(anArray, toSearch, 0, null);
	}

	public static int search(final Object[] anArray, Object toSearch, int startPos, boolean[] elementsToIgnore)
	{
		final int len = anArray.length;

		if (elementsToIgnore == null)
		{
			elementsToIgnore = new boolean[len];
		}

		for (int i = startPos; i < len; i++)
		{
			try
			{
				if (elementsToIgnore[i])
					continue;
			}
			catch (Exception e)
			{ /* e.printStackTrace();e=null; */
			}

			if (anArray[i].equals(toSearch))
			{
				return i;
			}
		}
		return -1;
	}

	public static double max(final double[] values)
	{
		double max = values[0];

		final int len = values.length;

		for (int i = 0; i < len; i++)
		{
			if (max < values[i])
			{
				max = values[i];
			}
		}

		return max;
	}

	public static double min(final double[] values)
	{
		double min = values[0];

		final int len = values.length;

		for (int i = 0; i < len; i++)
		{
			if (min > values[i])
			{
				min = values[i];
			}
		}

		return min;
	}

	public static double[] decideScale(final double maxValue, final boolean isPositiveside)
	{
		return decideScale(maxValue, new double[3], 1.2D, isPositiveside);
	}

	public static double[] decideScale(final double valueToScale, final double[] scale, double bufferMargin, final boolean isPositiveside)
	{
		if (valueToScale == Double.MAX_VALUE)
		{
			return scale;
		}

		final int index = isPositiveside ? 1 : 0;

		scale[index] = valueToScale * bufferMargin;

		scale[index] = StrictMath.round(scale[index]);

		while (scale[index] % 4 != 0)
		{
			if (isPositiveside)
			{
				scale[index]++;
			}
			else
			{
				scale[index]--;
			}

		}

		if (scale[index] == 0 && (valueToScale > 0 && valueToScale < 1))
		{
			scale[index] = isPositiveside ? 1.5 : 0;
		}
		else if (scale[index] == 0 && (valueToScale < 0 && valueToScale > -1))
		{
			scale[index] = isPositiveside ? 0 : -1.5;
		}

		scale[2] = scale[1] - scale[0];

		return scale;

	}

	public static void copyArray(Object srcArray, Object destArray)
	{
		System.arraycopy(srcArray, 0, destArray, 0, Array.getLength(srcArray));
	}

	public static boolean validateEmailID(Component parent, final String temp1)
	{
		return validateEmailID(parent, temp1, false);
	}

	public static boolean validateEmailID(Component parent, final String temp1, final boolean showMsg)
	{
		if (temp1 == null)
		{
			if (showMsg)
				JOptionPane.showMessageDialog(parent, "Email ID cannot Blank.");

			return false;
		}

		if (temp1.length() == 0)
		{
			if (showMsg)
				JOptionPane.showMessageDialog(parent, "Email ID cannot Blank.");
			return false;
		}

		final StringTokenizer st = new StringTokenizer(temp1, ",");

		while (st.hasMoreTokens())
		{
			final String temp = st.nextToken();
			int index = 0;

			if (temp.length() == 0)
			{
				if (showMsg)
					JOptionPane.showMessageDialog(parent, "Please enter a valid Email ID.");
				return false;
			}

			if (temp.endsWith("."))
			{
				if (showMsg)
					JOptionPane.showMessageDialog(parent, "Email ID cannot end with '.'");
				return false;
			}
			else if ((index = temp.indexOf('@')) == -1 || temp.indexOf('.') == -1)
			{
				if (showMsg)
					JOptionPane.showMessageDialog(parent, "Email ID must contain '@' and '.'");

				return false;
			}
			else if (temp.indexOf('.', index + 1) == -1)
			{
				if (showMsg)
					JOptionPane.showMessageDialog(parent, "Email ID must have '.' after '@'.");

				return false;
			}
			else if (temp.indexOf("@", index + 1) > -1)
			{
				if (showMsg)
					JOptionPane.showMessageDialog(parent, "Email ID cannot  contain more than one '@'.");

				return false;
			}
			else if (temp.indexOf("@.") > -1)
			{
				if (showMsg)
					JOptionPane.showMessageDialog(parent, "Email ID cannot  contain '@.' together.");
				return false;
			}
			else if (temp.indexOf("..") > -1)
			{
				if (showMsg)
					JOptionPane.showMessageDialog(parent, "Email ID cannot  contain '..' together.");
				return false;
			}
			if (temp.indexOf('/') > -1 || temp.indexOf('\\') > -1 || temp.indexOf(';') > -1 || temp.indexOf('"') > -1 || temp.indexOf('~') > -1 || temp.indexOf('!') > -1 || temp.indexOf('#') > -1 || temp.indexOf('$') > -1 || temp.indexOf('%') > -1 || temp.indexOf('^') > -1 || temp.indexOf('&') > -1 || temp.indexOf('*') > -1 || temp.indexOf('(') > -1 || temp.indexOf(')') > -1 || temp.indexOf('+') > -1 || temp.indexOf('|') > -1 || temp.indexOf('{') > -1 || temp.indexOf('}') > -1 || temp.indexOf('[') > -1 || temp.indexOf(']') > -1 || temp.indexOf('<') > -1 || temp.indexOf('>') > -1)
			{
				if (showMsg)
					JOptionPane.showMessageDialog(parent, "Email id cannot contain / \\ ; \" ~ ! # $ % ^ & * ( ) + { } [ ] | < > ");
				return false;
			}
		}
		return true;
	}

	public static boolean writeLog(String txt, String fileName)
	{
		try
		{
			final File f = new File(fileName);
			writeLog(txt, f);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			e = null;
			return false;
		}
	}

	public static boolean writeLog(String txt, File fileName)
	{
		try
		{
			final File f = fileName;
			if (!f.exists())
			{
				f.createNewFile();
			}
			final FileOutputStream fout = new FileOutputStream(f.getAbsolutePath(), true);
			fout.write(txt.getBytes());
			fout.write("\r\n".getBytes());
			fout.close();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			e = null;
			return false;
		}
	}

	public static Calendar convertStringToDate(final String dateToParse)
	{
		final Calendar c = Calendar.getInstance();
		resetTimeTo0(c);
		try
		{

			final String date = dateToParse.replace(' ', '-').replace(':', '-');
			final StringTokenizer st = new StringTokenizer(date, "-");
			c.set(Calendar.DATE, Integer.parseInt(st.nextToken()));
			c.set(Calendar.MONTH, Integer.parseInt(st.nextToken()) - 1);
			c.set(Calendar.YEAR, Integer.parseInt(st.nextToken()));

			if (dateToParse.indexOf(' ') > -1)
			{
				c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(st.nextToken()));
				c.set(Calendar.MINUTE, Integer.parseInt(st.nextToken()));
				c.set(Calendar.SECOND, Integer.parseInt(st.nextToken()));
			}

			return c;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(" e " + e);
			return null;
		}
	}

	public static Calendar convertStringToTime(final String dateToParse)
	{
		final Calendar c = Calendar.getInstance();
		resetTimeTo0(c);
		try
		{

			final String date = dateToParse.replace(' ', '-').replace(':', '-');
			final StringTokenizer st = new StringTokenizer(date, "-");

			c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(st.nextToken()));
			if (st.hasMoreElements())
			{
				c.set(Calendar.MINUTE, Integer.parseInt(st.nextToken()));
			}
			if (st.hasMoreElements())
			{
				c.set(Calendar.SECOND, Integer.parseInt(st.nextToken()));
			}

			return c;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(" e " + e);
			return null;
		}
	}

	public static Object moveArrayElements(final Object oldArray, final int direction)
	{
		final int oldLen = Array.getLength(oldArray);
		if (direction == SwingConstants.LEFT)
		{
			return moveArrayElements(oldArray, 1, oldLen - 1, direction, 1);
		}
		else
		{
			return moveArrayElements(oldArray, 0, oldLen - 1, direction, 1);
		}

	}

	public static Object moveArrayElements(final Object oldArray, int offset, final int noOfElements, final int direction, final int jump)
	{

		if (direction == SwingConstants.LEFT)
		{
			if (offset <= 0)
				offset = 1;

			System.arraycopy(oldArray, offset, oldArray, offset - jump, noOfElements);
		}
		else if (direction == SwingConstants.RIGHT)
		{
			System.arraycopy(oldArray, offset, oldArray, offset + jump, noOfElements);
		}

		return oldArray;
	}

	/**
	 * sets barr true as per arr barr[arr[i]]=true;
	 */
	public static void setBooleanArray(boolean[] barr, int[] arr)
	{
		final int len = arr.length;

		final int len1 = barr.length;

		for (int i = 0; i < len1; i++)
		{
			barr[i] = false;
		}

		for (int i = 0; i < len; i++)
		{
			try
			{
				barr[arr[i]] = true;
			}
			catch (Exception ey)
			{
				ey = null;
			}

		}
	}

	public static void printArray(final Object array)
	{

		if (true)
		{
			return;
		}
			
		if (array==null)
		{
			return;
		}
				logger.info("---------------");

				final Class class1 = array.getClass();
				if (!class1.isArray())
				{
					logger.debug(array);
					return;
				}
				final int len = Array.getLength(array);

				if (len == 0)
				{
					logger.info("Noting to print");
				}

				StringBuffer sb = new StringBuffer(len * 25);
				for (int i = 0; i < len; i++)
				{
					final Object obj = Array.get(array, i);
					if (obj.getClass().isArray())
					{
						printArray(obj);
					}
					else
					{
						sb.append(i);
						sb.append(' ');
						sb.append(obj);
						sb.append(" | ");
						sb.append('\r');
						sb.append('\n');
					}
				}
				logger.info(sb.toString());
				logger.info("---------------");

			}

	public final static int[] filterIntArray(final int anArray[], final int constraints)
	{
		final int blen = anArray.length;
		int counter = 0;

		for (int i = 0; i < blen; i++)
			if (anArray[i] == constraints)
				counter++;

		final int len = blen - counter;

		final int[] copyOfIndex = new int[len];

		for (int i = 0, j = 0; i < blen; i++)
		{
			if (anArray[i] != constraints)
			{
				copyOfIndex[j] = anArray[i];
				j++;
			}
		}

		return copyOfIndex;
	}

	public final static double[] filterDoubleArray(final double anArray[], final double constraints)
	{
		final int blen = anArray.length;
		int counter = 0;

		for (int i = 0; i < blen; i++)
		{
			if (anArray[i] == constraints)
				counter++;
		}

		final int len = blen - counter;

		final double[] copyOfIndex = new double[len];

		for (int i = 0, j = 0; i < blen; i++)
		{
			if (anArray[i] != constraints)
			{
				copyOfIndex[j] = anArray[i];
				j++;
			}
		}

		return copyOfIndex;
	}

	public static String replaceAll(String srcString, String toFind, String replacement)
	{
		boolean b = true;
		int index = 0;
		final StringBuffer sb = new StringBuffer(srcString);
		final int len = toFind.length();
		while (b)
		{
			index = srcString.indexOf(toFind, index);
			if (index == -1)
			{
				break;
			}

			sb.delete(index, index + len);
			sb.insert(index, replacement);
			index++;
		}
		return sb.toString();
	}

	/**
	 * Reads a text file and fills Hashtable with lines by using splitter
	 * constraints to seperate key and values
	 */
	public static Hashtable txt2Hashtable(String fileName, final String splitter)
	{
		return txt2Hashtable(new File(fileName), splitter);
	}

	/**
	 * Reads a text file and fills Hashtable with lines by using splitter
	 * constraints to seperate key and values
	 */
	public static Hashtable txt2Hashtable(File f, final String splitter)
	{
		final String str[] = txt2StrArr(f);
		final Hashtable ht = new Hashtable();
		if (str == null)
			return ht;

		final int len = str.length;
		for (int i = 0; i < len; i++)
		{
			final String temp = str[i];

			final int index = temp.indexOf(splitter);
			if (index > -1)
			{
				final String key = temp.substring(0, index);
				final String value = temp.substring(index + splitter.length());
				ht.put(key, value);
			}
		}

		return ht;
	}

	public static void hashtable2Txt(Hashtable ht, File f, final String splitter)
	{
		try
		{
			final BufferedWriter bw = new BufferedWriter(new FileWriter(f));

			final Enumeration en = ht.keys();

			while (en.hasMoreElements())
			{
				String key = (String) en.nextElement();
				final String value = ht.get(key).toString();
				bw.write(key + "=" + value);
				bw.newLine();
			}

			bw.close();

		}
		catch (Exception e)
		{
			e.printStackTrace();
			e = null;
		}

	}

	/**
	 * This methods reads text file and create String[] and return it also
	 * ignores lines starting with //
	 */
	public static String[] txt2StrArr(File f)
	{
		return txt2StrArr(f, "//");
	}

	/**
	 * This methods reads text file and create String[] and return it also
	 * ignores lines for ignoreLine
	 */
	public static String[] txt2StrArr(File f, String ignoreLine)
	{
		return getInstance().txt2StrArrInstance(f, ignoreLine);
	}

	public String[] converString2Array(String toConvert, String delimiter)
	{
		StringTokenizer st = new StringTokenizer(toConvert, delimiter);
		int countTokens = st.countTokens();
		String[] ans = new String[countTokens];

		for (int i = 0; i < countTokens; i++)
		{
			ans[i] = st.nextToken();
		}
		return ans;
	}

	public String[] txt2StrArrInstance(File f, String ignoreLine)
	{
		try
		{
			final RandomAccessFile buf = new RandomAccessFile(f, "r");
			final Vector v = new Vector();
			String str = buf.readLine();
			System.err.println(str);
			while (str != null)
			{
				if (ignoreLine == null)
				{
					v.add(str);
				}
				else if (!str.startsWith(ignoreLine))
				{
					v.add(str);
				}
				str = buf.readLine();
			}
			final int len = v.size();
			final String[] strArr = new String[len];
			for (int i = 0; i < len; i++)
			{
				strArr[i] = (String) v.elementAt(i);
			}
			v.clear();
			buf.close();
			return strArr;

		}
		catch (Exception e)
		{
			// e.printStackTrace();e=null;
		}
		return null;
	}

	/**
	 * This method creates new double array having three elements viz. {min
	 * scale value, max scale value, differenece} bufferening should be absolute
	 * i.e. for 20 % mention only 0.2
	 */

	public final static double[] getScale(MaxMin mm, final double bufferning)
	{
		return getScale(mm.getMin(), mm.getMax(), bufferning);
	}

	/**
	 * This method creates new double array having three elements viz. {min
	 * scale value, max scale value, differenece} bufferening should be absolute
	 * i.e. for 20 % mention only 0.2
	 */
	public final static double[] getScale(final double min, final double max, final double bufferning)
	{
		final double scale[] = new double[3];

		if (max < 0 && min < 0)
		{
			KetanUtilities.decideScale(max, scale, 1 - bufferning, true);

			if (min < 0)
				KetanUtilities.decideScale(min, scale, 1 + bufferning, false);
			else
				KetanUtilities.decideScale(min, scale, 1 - bufferning, false);

		}
		else
		{

			KetanUtilities.decideScale(max, scale, 1 + bufferning, true);

			if (min < 0)
				KetanUtilities.decideScale(min, scale, 1 + bufferning, false);
			else
				KetanUtilities.decideScale(min, scale, 1 - bufferning, false);
		}

		return scale;
	}

	/**
	 * Returns today's seconds from 00 hrs
	 */
	public final static int getTodaysSec()
	{
		return getTotlSec(Calendar.getInstance());
	}

	public final static int getTotlSec(Calendar c)
	{
		int hh = c.get(Calendar.HOUR_OF_DAY);
		int mm = c.get(Calendar.MINUTE);
		int ss = c.get(Calendar.SECOND);

		return ((hh * 60 * 60) + (mm * 60) + ss);

		// return 0;
	}

	/**
	 * This method plainly writes data to CSV File nothing will be formatted.
	 * whatever is in vector will be wriiten to file. By Default it will append
	 * to the end
	 * 
	 */
	public final static void writeCSV(String destination, Vector data)
	{
		try
		{
			new File(destination).createNewFile();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			e = null;
		}
		try
		{

			FileOutputStream fout = new FileOutputStream(destination, true);
			StringBuffer sb = new StringBuffer();
			final int len = data.size();

			for (int i = 0; i < len; i++)
			{
				sb.append(data.elementAt(i));
				sb.append(',');
			}
			fout.write(sb.toString().getBytes());
			fout.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			e = null;
		}
	}

	public boolean txtArrToFile(Object[] txt, File destination)
	{
		try
		{
			if (txt == null || txt.length == 0)
			{
				return false;
			}
			destination.delete();
			destination.createNewFile();
			RandomAccessFile writer = new RandomAccessFile(destination, "rw");
			for (int i = 0; i < txt.length; i++)
			{
				Object object = txt[i];
				if (object != null)
				{
					writer.write(object.toString().getBytes());
					writer.write("\r\n".getBytes());
				}

			}
			return true;
		}
		catch (Exception e)
		{
		}
		return false;
	}

	public static KetanUtilities getInstance()
	{
		return KETAN_UTILITIES_ADD_ON;
	}

	/**
	 * This method is special method for sorting Object array. it should NOT be
	 * used to sort primitive types. it calls the method on the given object and
	 * using comparator it sorts the array
	 * 
	 * This method is processor hungry
	 * 
	 * @param array
	 * @param methodName
	 */
	public void sortObjectArray(Object[] array, final String methodName)
	{

		Method method1 = null;
		try
		{
			Object obj=null;
			method1 = array[0].getClass().getMethod(methodName, null);
		}
		catch (SecurityException e1)
		{
			e1.printStackTrace();
		}
		catch (NoSuchMethodException e1)
		{
			e1.printStackTrace();
		}
		final Method method = method1;
		Arrays.sort(array, new Comparator()
		{

			public int compare(Object o1, Object o2)
			{
				try
				{

					Object obj=null;
					Object invoke1 = method.invoke(o1, new Object[] {obj});
					Object invoke2 = method.invoke(o2, new Object[] {obj});

					if (invoke1 instanceof Comparable)
					{
						return ((Comparable) invoke1).compareTo(invoke2);
					}

					if (invoke1.equals(invoke2))
					{
						return 0;
					}

					Object[] a = new Object[]
					{ invoke1, invoke2 };
					Arrays.sort(a);

					if (a[0].equals(invoke1))
					{
						return -1;
					}
					else
					{
						return 1;
					}

				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				return 0;
			}

		});

	}

	public static void main(String[] args)
	{
		String[] array = new String[]
		{ "s", "ss", "a" };
		KetanUtilities.getInstance().sortObjectArray(array, "toString");
		printArray(array);
	}

	public boolean CheckForNull(Object value)
	{
		return CheckForNull(value, null);
	}

	public boolean CheckForNull(Object value, String msg)
	{
		boolean ans = true;
		if (value == null)
		{
			ans = false;
		}

		if (value instanceof String)
		{
			value = ((String) value).trim();

			if (((String) value).length() == 0)
			{
				ans = false;
			}

		}

		if (!ans)
		{
			if (msg != null)
			{
				JOptionPane.showMessageDialog(null, msg);
			}

		}

		return ans;
	}

	public boolean checkForBlankVector(AbstractList data)
	{
		for (int i = 0; i < data.size(); i++)
		{
			Object object = data.get(i);

			boolean checkForNull = CheckForNull(object);
			if (checkForNull)
			{
				return false;
			}
		}

		return true;

	}

	public int countDuplicateElementsIncollection(Collection c, Object toCount)
	{
		int size = c.size();
		Object[] array = c.toArray();
		int count = 0;
		for (int i = 0; i < size; i++)
		{
			Object object = array[i];
			if (object != null)
			{
				if (object.equals(toCount))
				{
					count++;
				}
			}

		}
		return count;
	}

	public Vector getkeysForValue(Hashtable ht, Object value)
	{
		Vector v = new Vector();

		Enumeration keys = ht.keys();

		while (keys.hasMoreElements())
		{
			Object key = (Object) keys.nextElement();

			Object valueFromHT = ht.get(key);

			if (value.equals(valueFromHT))
			{
				v.add(key);
			}

		}

		return v;
	}

	
}
