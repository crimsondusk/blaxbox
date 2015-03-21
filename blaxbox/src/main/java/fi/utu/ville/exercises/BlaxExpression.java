package fi.utu.ville.exercises;

import java.util.Stack;
import java.util.ArrayList;

interface BlaxExprBlock
{
	boolean doOperation (Stack<String> values); // do something on the given stack of values
}

interface BlaxOperator extends BlaxExprBlock
{
	int numOperands(); // how many operands does the operator require? (typically 2)
	String toString(); // how to represent the operator?
	String name(); // name of this operator
};

public class BlaxExpression
{
	private ArrayList<BlaxExprBlock> ops;
	private Stack<String> values;

	static ArrayList<BlaxOperator> allOperators;

	static public void AddOperator (BlaxOperator op)
	{
		allOperators.add (op);
	}

	BlaxExpression (ArrayList<BlaxExprBlock> ops_)
	{
		ops = ops_;
	}

	public String evaluate()
	{
		values = new Stack<String>();

		for (int i = ops.size() - 1; i >= 0; --i)
			ops.get (i).doOperation (values);

		return values.pop();
	}

	/* main() for testing
	public static void main (String[] args)
	{
		ArrayList<BlaxExprBlock> ops = new ArrayList<BlaxExprBlock>();
		ops.add (new BlaxNumericOperator (BlaxNumericOperator.OperatorType.Division));
		ops.add (new BlaxOperand ("25", true));
		ops.add (new BlaxOperand ("0", true));
		BlaxExpression expr = new BlaxExpression (ops);
		System.out.println (expr.evaluate());
	} */
};
