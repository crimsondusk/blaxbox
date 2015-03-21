import java.util.Stack;
import java.util.ArrayList;
import java.util.Random;

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
	private BlaxExpressionProfile profile;
	private Random rng;

	static ArrayList<BlaxOperator> allOperators;

	static public void AddOperator (BlaxOperator op)
	{
		allOperators.add (op);
	}

	BlaxExpression (BlaxExpressionProfile profile_)
	{
		profile = profile_;

		// decide some values
		rng = new Random (System.currentTimeMillis());
		int numOps = rng.nextInt (profile.maxOperators - profile.minOperators) + profile.minOperators;
		int numOperands = numOps + 1; // how many numbers are involved in the operation?
		int numBlocks = numOps + numOperands; // how many blocks in total?
		int operandsLeft = numOperands;
		int blocksLeft = numBlocks;
		int opsLeft = numOps;
		ops = new ArrayList<BlaxExprBlock>();

		// Predict the stack size as we fill in the operation. We may not add an operator if stack
		// count is not at least 2 as we can't use a binary operation on less than 2 values, duh)
		int stackCount = 0;

		for (int i = 0; i < numBlocks; ++i)
		{
			if (stackCount >= 2 && rng.nextInt (numBlocks - i) < opsLeft)
			{
				BlaxOperator op = new BlaxNumericOperator (BlaxNumericOperator.OperatorType.values()[rng.nextInt (4)]);
				ops.add (op);
				opsLeft--;
				stackCount--;
				System.out.println (op.toString());
			}
			else
			{
				String strval = Integer.toString (rng.nextInt (21));
				ops.add (new BlaxOperand (strval, true));
				System.out.println (strval);
				stackCount++;
			}
		}
	}

	public String evaluate()
	{
		values = new Stack<String>();

		for (BlaxExprBlock block : ops)
		{
			boolean r = block.doOperation (values);

			if (r == false)
				return "<no answer>";
		}

		return values.pop();
	}

	public static void main (String[] args)
	{
		BlaxExpression expr = new BlaxExpression (new BlaxExpressionProfile());
		System.out.println ("= " + expr.evaluate());
	}
};
