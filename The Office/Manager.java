package patterns.delegation.office;

import java.util.Collection;
import java.util.function.BinaryOperator;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;

public class Manager implements Employee{
	
	int i = 0;
	int taskCount = 0;
	int resourceCount = 1;
	List<Employee> ansatte = new ArrayList<>();
	Iterator<Employee> iterator;
	
	public Manager(Collection<Employee> employees) {
		ansatte.addAll(employees);
		if (employees.size()==0) {
			throw new IllegalArgumentException("The list is empty.");
		}
		this.iterator = ansatte.iterator();
	}

	@Override
	public double doCalculations(BinaryOperator<Double> operation, double value1, double value2) {
		double result;
		if(!iterator.hasNext()) {
			iterator = ansatte.iterator();
		}
		result = iterator.next().doCalculations(operation, value1, value2);
		taskCount++;
		return result;
	}

	@Override
	public void printDocument(String document) {
		if(!iterator.hasNext()) {
			iterator = ansatte.iterator();
		}
		iterator.next().printDocument(document);
		taskCount++;
	}

	@Override
	public int getTaskCount() {
		return taskCount;
	}

	@Override
	public int getResourceCount() {
		for (int i=0; i<ansatte.size(); i++) {
			resourceCount += ansatte.get(i).getResourceCount();
		}
		return resourceCount;
	}
	
	public static void main(String[] args) {
		Collection<Employee> list_1 = new ArrayList<>();
		Printer print = new Printer();
		Clerk a = new Clerk(print);
		Clerk b = new Clerk(print);
		list_1.add(a);
		list_1.add(b);
		Manager boss = new Manager(list_1);
		boss.printDocument("dokument");
		boss.doCalculations((x, y) -> x+y, 10.0, 30.0);
		System.out.println((double) boss.getTaskCount() / boss.getResourceCount());
		Manager deputy = new Manager(Arrays.asList(boss));
		deputy.printDocument("hallo");
		System.out.println((double) deputy.getTaskCount() / deputy.getResourceCount());
		
	}

}
