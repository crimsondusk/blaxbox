import java.util.Stack;
import java.lang.NumberFormatException;

public abstract class BlaxBaseNumericOperator implements BlaxOperator
{
	public abstract float doNumericOperation (float a, float b);

	@Override
	public int numOperands()
	{
		return 2;
	}

	@Override
	public boolean doOperation (Stack<String> values)
	{
		float value1, value2;

		try
		{
			value1 = Float.parseFloat (values.pop());
			value2 = Float.parseFloat (values.pop());
		}
		catch (NumberFormatException e)
		{
			return false;
		}

		float result = doNumericOperation (value1, value2);
		values.push (Float.toString (result));
		return true;
	}
};
