package fi.utu.ville.exercises;

/*
 * Describes what kind of expressions to generate
 */
class BlaxExpressionProfile
{
	public String[] operators = {"+", "-", "*", "/", "mod"};
	public int minOperators = 1;
	public int maxOperators = 3;
	public int numInputs = 2;
};
