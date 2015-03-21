package fi.utu.ville.exercises;

public class BlaxAdditionOperator extends BlaxNumericOperator
{
	@Override public String getSymbol() { return "+"; }
	@Override public String getName() { return "Addition"; }
	@Override float getNumericResult (float a, float b) { return a + b; }
}