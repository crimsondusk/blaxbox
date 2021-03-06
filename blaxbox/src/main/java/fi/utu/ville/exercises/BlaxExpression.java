package fi.utu.ville.exercises;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;
import java.lang.Math;

interface BlaxExprBlock
{
	boolean doOperation (Stack<String> values); // do something on the given stack of values
}

interface BlaxOperator extends BlaxExprBlock
{
	int getNumOperands(); // how many operands does the operator require? (typically 2)
	String getSymbol(); // how to represent the operator?
	String getName(); // name of this operator
};

public class BlaxExpression
{
	private ArrayList<BlaxExprBlock> ops;
	private Stack<String> values;
	private BlaxExpressionProfile profile;
	private Random rng;
	private ArrayList<BlaxOperand> inputs;
	private ArrayList<BlaxOperator> operators;
	private int numInputsUsed = 0;

	public static BlaxOperator[] allOperators = {
		new BlaxAdditionOperator(),
		new BlaxSubtractionOperator(),
		new BlaxMultiplicationOperator(),
		new BlaxDivisionOperator(),
	};

	public static int calcMinOperandCount (ArrayList<BlaxOperator> operators)
	{
		int minOperandCount = 10000;

		for (BlaxOperator op : operators)
			minOperandCount = Math.min (op.getNumOperands(), minOperandCount);

		return minOperandCount;
	}

	BlaxExpression (BlaxExpressionProfile profile_)
	{
		profile = profile_;

		// decide some values
		rng = new Random (System.currentTimeMillis());
		int numOps = (profile.maxOperators != profile.minOperators)
			? rng.nextInt (profile.maxOperators - profile.minOperators) + profile.minOperators
			: profile.minOperators;
		int numOperands = 1; // how many numbers are involved in the operation?
		ops = new ArrayList<BlaxExprBlock>();
		inputs = new ArrayList<BlaxOperand>();
		operators = new ArrayList<BlaxOperator>();
		numInputsUsed = 0;

		// Decide which operators to use
		for (int i = 0; i < numOps; ++i)
		{
			BlaxOperator op;

			do
				op = allOperators[rng.nextInt (allOperators.length)];
			while (profile.operators.contains (op.getName()) == false);

			operators.add (op);
			numOperands += op.getNumOperands() - 1;
		}

		int minOperandCount = calcMinOperandCount (operators);
		int operandsLeft = numOperands;
		int numBlocks = numOps + numOperands; // how many blocks in total?
		int opsLeft = numOps;
		int inputsLeft = profile.numInputs;

		// Predict the stack size as we fill in the operation. We may not add an operator if stack
		// count is not at least 2 as we can't use a binary operation on less than 2 values, duh)
		int stackCount = 0;

		for (int i = 0; i < numBlocks; ++i)
		{
			if (stackCount >= minOperandCount && rng.nextInt (numBlocks - i) < opsLeft)
			{
				BlaxOperator op;

				do
					op = operators.get (rng.nextInt (operators.size()));
				while (op.getNumOperands() - 1 >= stackCount);

				ops.add (op);
				System.out.println ("" + i + ". " + op.getSymbol());
				opsLeft--;
				stackCount -= (op.getNumOperands() - 1);

				// minOperandCount may change now, update it
				minOperandCount = calcMinOperandCount (operators);
			}
			else
			{
				String strval = Integer.toString (rng.nextInt (10) + 1);
				BlaxOperand operand = new BlaxOperand (strval);

				// With some odds, this number becomes an input number
				if (rng.nextInt (operandsLeft) < inputsLeft)
				{
					operand.setInputNumber (profile.numInputs - inputsLeft);
					inputs.add (operand);
					inputsLeft--;
					numInputsUsed++;
					System.out.println ("" + i + ". [" + operand.getInputNumber() + "]");
				}
				else
					System.out.println ("" + i + ". " + strval);

				ops.add (operand);
				stackCount++;
				operandsLeft--;
			}
		}

		while (inputs.size() < profile.numInputs)
		{
			BlaxOperand op = new BlaxOperand("");
			op.setInputNumber (inputs.size());
			inputs.add (op);
		}
	}

	void setInput (int inputId, String value)
	{
		inputs.get (inputId).setValue (value);
	}

	public String evaluate()
	{
		// If the inputs are valid, they will be parsed properly here. If not, stop now.
		try
		{
			for (int i = 0; i < numInputsUsed; ++i)
				Double.parseDouble (inputs.get (i).getValue());
		}
		catch (NumberFormatException e)
		{
			return "";
		}

		values = new Stack<String>();

		for (BlaxExprBlock block : ops)
		{
			boolean r = block.doOperation (values);

			if (r == false)
				return "<no answer>";
		}

		return values.pop();
	}
};
