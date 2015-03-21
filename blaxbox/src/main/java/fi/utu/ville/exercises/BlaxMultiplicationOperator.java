package fi.utu.ville.exercises;

public class BlaxMultiplicationOperator extends BlaxNumericOperator
{
	@Override public String getSymbol() { return "*"; }
	@Override public String getName() { return "Multiplication"; }
	@Override float getNumericResult (float a, float b) { return a * b; }
}