import java.util.Stack;

public class BlaxNumericOperator extends BlaxBaseNumericOperator
{
	enum OperatorType
	{
		Addition,
		Subtraction,
		Multiplication,
		Division,
		Modulo
	};

	private OperatorType m_optype;
	static private String[] OpSymbols = {"+", "-", "*", "/", "mod"};
	static private String[] OpNames = {"Addition", "Subtraction", "Multiplication", "Division", "Modulo"};

	@Override
	public String toString()
	{
		return OpSymbols[m_optype.ordinal()];
	}

	@Override
	public String name()
	{
		return OpNames[m_optype.ordinal()];
	}

	BlaxNumericOperator (OperatorType op)
	{
		m_optype = op;
	}

	@Override
	public float doNumericOperation (float a, float b)
	{
		switch (m_optype)
		{
		default:
		case Addition: return a + b;
		case Subtraction: return a - b;
		case Multiplication: return a * b;
		case Division: return a / b;
		case Modulo: return a % b;
		}
	}
};