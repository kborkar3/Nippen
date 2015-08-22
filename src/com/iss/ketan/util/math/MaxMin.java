package com.iss.ketan.util.math;

public final class MaxMin extends Object implements java.io.Serializable
{
	public transient static final int TYPE_MAX=0, TYPE_MIN=1;

	private double maxValue=0;
	private double minValue=0;

	/*
	@deprecated do not use this constructor
	*/
	public MaxMin(int type)
	{
		reset();
	}

	public MaxMin()
	{
		reset();
	}

	public double update(final double newVal)
	{
		maxValue=(newVal>maxValue) ? newVal : maxValue;
		minValue=(newVal<minValue) ? newVal : minValue;

		return maxValue;
	}

	/*
		returns max value or Double.NEGATIVE_INFINITY if not updated
	*/
	public double getMax()
	{
		return maxValue;
	}

	/*
		returns max value or Double.MAX_VALUE if not updated
	*/
	public double getMin()
	{
		return minValue;
	}

	/*
		resets all values and ready to calculate new max or min
	*/
	public void reset()
	{
		maxValue=Double.NEGATIVE_INFINITY;
		minValue=Double.MAX_VALUE;
	}

	public String toString()
	{
		return "com.iss.ketan.util.math.MaxMin"+" maxValue "+maxValue+" minValue "+minValue;
	}
}
