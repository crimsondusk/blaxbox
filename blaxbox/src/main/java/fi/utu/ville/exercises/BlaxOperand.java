import java.util.Stack;

/*
 * One operand of an expression.
 */
public class BlaxOperand implements BlaxExprBlock
{
	private String m_value;
	private boolean m_isConstant = false;

	@Override
	public boolean doOperation (Stack<String> values)
	{
		values.push (m_value);
		return true;
	}

	public String getValue()
	{
		return m_value;
	}

	public boolean isConstant()
	{
		return m_isConstant;
	}

	public void setValue (String value, boolean isConstant)
	{
		m_value = value;
		m_isConstant = isConstant;
	}

	BlaxOperand (String value, boolean isConstant)
	{
		setValue (value, isConstant);
	}
};