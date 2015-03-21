package fi.utu.ville.exercises;

import java.util.Stack;
import java.lang.NumberFormatException;

abstract public class BlaxNumericOperator implements BlaxOperator
{
	@Override
	public int getNumOperands()
	{
		return 2;
	}

	@Override
	public boolean doOperation (Stack<String> values)
	{
		float a, b;

		try
		{
			a = Float.parseFloat (values.pop());
			b = Float.parseFloat (values.pop());

			float res = getNumericResult (a, b);
			int dumb = (int) res;
			values.push (Float.toString (res));
		}
		catch (NumberFormatException e)
		{
			return false;
		}

		return true;
	}

	abstract float getNumericResult (float a, float b);
};