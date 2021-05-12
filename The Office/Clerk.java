package patterns.delegation.office;

import java.util.function.BinaryOperator;

public class Clerk implements Employee{
	
	private Printer printer;
	private int taskCount = 0;

	
	public Clerk(Printer printer){
		this.printer = printer;
	}

	@Override
	public double doCalculations(BinaryOperator<Double> operation, double value1, double value2) {
		double result;
		result = operation.apply(value1, value2);
		this.taskCount++;
		return result;
	}

	@Override
	public void printDocument(String document) {
		printer.printDocument(document, this);
		taskCount++;
	}

	@Override
	public int getTaskCount() {
		return taskCount;
	}

	@Override
	public int getResourceCount() {
		return 1;
	}

}
