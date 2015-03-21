package fi.utu.ville.exercises;
import java.util.Stack;

/*
 * One operand of an expression.
 */
public class BlaxOperand implements BlaxExprBlock
{
	private String m_value;
	private int m_inputNumber = -1;

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

	public void setInputNumber (int a)
	{
		m_inputNumber = a;
	}

	public int getInputNumber()
	{
		return m_inputNumber;
	}

	public void setValue (String value)
	{
		m_value = value;
	}

	BlaxOperand (String value)
	{
		setValue (value);
	}
};