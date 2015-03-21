package fi.utu.ville.exercises;

public class BlaxSubtractionOperator extends BlaxNumericOperator
{
	@Override public String getSymbol() { return "-"; }
	@Override public String getName() { return "Subtraction"; }
	@Override float getNumericResult (float a, float b) { return a - b; }
}