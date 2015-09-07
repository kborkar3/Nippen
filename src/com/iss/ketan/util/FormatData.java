package com.iss.ketan.util;

public final class FormatData extends Object
{
	public FormatData()
	{
	}

	private static String normalPattern = "0.00";
	private static final String pfPattern = "0.000";
	private static final java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
	private static final java.text.DecimalFormat pfDf = new java.text.DecimalFormat(pfPattern);

	private static final String[] ext =
	{ "", " K", " M", " G", " T", " L", " C" };
	// 0 1 2 3 4 5 6
	private static final java.text.FieldPosition fp = new java.text.FieldPosition(df.INTEGER_FIELD);
	private static final StringBuffer sb = new StringBuffer();
	
	// Formats
	
	private static final double slab[][] =
	{
	{ 0D, 1000D },
	{ 1000D, 1000000D },
	{ 1000000D, 1000000000D },
	{ 100000000D, 1000000000000D },
	{ 1000000000000D, Double.MAX_VALUE }, };

	private static final int len = slab.length;
	
	
	

	public static void setPfChar(char lagging, char leading)
	{
		final StringBuffer sb = new StringBuffer();
		sb.append(' ');
		sb.append(lagging);

		getExt()[5] = sb.toString();

		sb.setLength(0);
		sb.append(' ');
		sb.append(leading);

		getExt()[6] = sb.toString();
	}

	// Alkesh: To format data
	
	public synchronized static String formatValue(final double fl, final String pattern, final boolean isPF)
	{
		sb.setLength(0);

		int extIndex = 0;

		final double fl1 = Math.abs(fl);
		if (isPF)
		{

			if (fl == 1 || fl == 0)
			{
				return pfDf.format(fl1, sb, fp).toString();
			}
			else if (fl > 0)
			{
				pfDf.format(fl1, sb, fp);
				sb.append(getExt()[5]);
				return sb.toString();
			}
			else
			{
				pfDf.format(fl1, sb, fp);
				sb.append(getExt()[6]);
				return sb.toString();
			}

		}

		if (pattern != null)
			df.applyPattern(pattern);
		else
			df.applyPattern(normalPattern);

		for (int i = 0; i < len; i++)
		{
			if (fl1 >= slab[i][0] && fl1 < slab[i][1])
			{
				final double div = (slab[i][0] != 0) ? slab[i][0] : 1;
				df.format(fl / div, sb, fp);
				extIndex = i;
				break;
			}
		}

		sb.append(getExt()[extIndex]);
		return sb.toString();

	}// formatValue

	// -----------------------------------------------------------------------------------------------------------------------------

	public static double getValue(String value)
	{
		value = value.toUpperCase();
		final double fl;

		if (value.indexOf('K') > -1)
		{
			fl = Double.parseDouble(value.replace('K', ' ').trim());

			return fl * 1000D;
		}
		else if (value.indexOf('M') > -1)
		{
			fl = Double.parseDouble(value.replace('M', ' ').trim());
			return fl * 1000000D;
		}
		else if (value.indexOf('G') > -1)
		{
			fl = Double.parseDouble(value.replace('G', ' ').trim());
			return fl * 1000000000D;
		}
		else if (value.indexOf('T') > -1)
		{
			fl = Double.parseDouble(value.replace('T', ' ').trim());
			return fl * 1000000000000D;
		}
		else if (value.indexOf('L') > -1)
		{
			fl = Double.parseDouble(value.replace('L', ' ').trim());
			return fl * 1F;
		}
		else if (value.indexOf('C') > -1)
		{
			fl = Double.parseDouble(value.replace('C', ' ').trim());
			return fl * -1F;
		}
		else
		{
			return Double.parseDouble(value);
		}

	}// getmultiliper

	// ---------------------------------------------------------------------------------------------------------------------------------------

	public static String formatValue(double fl)
	{
		return formatValue(fl, null, false);
	}

	public static String formatValue(double fl, boolean isPF)
	{
		return formatValue(fl, null, isPF);
	}

	public static String formatValue(double fl, String pattern)
	{
		return formatValue(fl, pattern, false);
	}

	public static String formatValue(final String str)
	{
		return formatValue(str, null, false);
	}

	public static String formatValue(final String str, final String pattern)
	{
		return formatValue(str, pattern, false);
	}

	public static String formatValue(final String str, final boolean isPf)
	{
		return formatValue(str, null, isPf);
	}

	public static String formatValue(final String str, final String pattern, boolean isPF)
	{
		try
		{
			final double fl = Double.parseDouble(str);
			return formatValue(fl, pattern, isPF);
		}
		catch (Exception e)
		{
			e = null;
			return str;
		}
	}

	// Commented by Alkesh
	
	/*public static double[] decideScale(final double maxValue, double current[], final double buffer)
	{
		if (current == null)
			current = new double[3];

		if (maxValue == Double.MAX_VALUE)
		{
			current[0] = 0;
			current[1] = 0;
			current[2] = 0;
		}

		if (maxValue >= current[1])
		{
			current[1] = maxValue * (buffer + 1);
			current[1] = Math.round(current[1]);

			while (current[1] % 4 != 0)
				current[1]++;

		}
		else if (maxValue <= current[0])
		{
			if (maxValue < 0)
				current[0] = maxValue * (buffer + 1);
			else
				current[0] = maxValue * (1 - buffer);

			current[0] = Math.round(current[0]);

			while (current[0] % 4 != 0)
				current[0]--;

		}

		return current;
	}*/

	public synchronized static String formatValue_WO_KMGT(final double fl, final String pattern)
	{
		sb.setLength(0);

		if (pattern != null)
			df.applyPattern(pattern);
		else
			df.applyPattern(normalPattern);

		return df.format(fl, sb, fp).toString();

	}

	public static String formatValue_WO_KMGT(final String toFormat)
	{
		return formatValue_WO_KMGT(toFormat, null);
	}

	public static String formatValue_WO_KMGT(final double toFormat)
	{
		return formatValue_WO_KMGT(toFormat, null);
	}

	public static String formatValue_WO_KMGT(final String toFormat, final String pattern)
	{
		try
		{
			return formatValue_WO_KMGT(Double.parseDouble(toFormat), pattern);
		}
		catch (Exception e)
		{
			e = null;
			return toFormat;
		}

	}

	/*public static String formatValue(final String toFormat, final FormatRuleIfc rule)
	{
		String ans = toFormat;
		try
		{
			boolean required = rule.isKMGTRequired();
			boolean pf = rule.isPF();
			char[] fchars = rule.getPFchars();
			String formattingPattern = rule.getFormattingPattern();
			String postFix = rule.getPostFix();
			String preFix = rule.getPreFix();

			if (fchars != null)
			{
				setPfChar(fchars[0], fchars[1]);
			}

			if (required)
			{
				ans = formatValue_WO_KMGT(toFormat, formattingPattern);
			}
			else
			{
				ans = formatValue(toFormat, formattingPattern, pf);
			}
			if (preFix != null)
			{
				ans = preFix + ans;
			}
			if (postFix != null)
			{
				ans = ans + postFix;
			}
		}
		catch (Exception e)
		{
		}
		return ans;
	}*/

	public static void setDefaultPattern(final String pattern)
	{
		normalPattern = pattern;
		df.applyPattern(normalPattern);
	}

	public static void main(String[] args)
	{
		
			String formatValue_WO_KMGT = FormatData.formatValue_WO_KMGT(0.9968650937080383,"0.000");
			System.out.println("FormatData.main()"+formatValue_WO_KMGT);
	}

	public static String[] getExt()
	{
		return ext;
	}
}
