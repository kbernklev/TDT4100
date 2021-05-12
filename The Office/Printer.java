package patterns.delegation.office;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Printer {

	Map<Employee, List<String>> printer_history = new HashMap<>();
	
	void printDocument(String document, Employee employee) {
		if(printer_history.containsKey(employee)) {
			printer_history.get(employee).add(document);
		}
		else {
			List<String> ny_liste = new ArrayList<>();
			ny_liste.add(document);
			printer_history.put(employee, ny_liste);
		}
	}
	
	List<String> getPrintHistory(Employee employee) {
		if(printer_history.containsKey(employee)) {
			return printer_history.get(employee);
		}
		else {
			return new ArrayList<>();
		}
	}
}
