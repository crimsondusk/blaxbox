package fi.utu.ville.exercises;

public class BlaxDivisionOperator extends BlaxNumericOperator
{
	@Override public String getSymbol() { return "/"; }
	@Override public String getName() { return "Division"; }
	@Override float getNumericResult (float a, float b) { return a / b; }
}