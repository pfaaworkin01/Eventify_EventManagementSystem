package BudgetManagement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BudgetFileManager {

    //private static final String FILE_PATH = "Budget_Data.txt";

    private static String getFilePath(String eventName) {
        return eventName + "_Budget_Data.txt";
    }

    public static void saveBudgetInfo(String eventName, List<DepartmentBudget> departmentBudgets) {
        String filePath = getFilePath(eventName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (DepartmentBudget department : departmentBudgets) {
                writer.write("Department: " + department.getDepartmentName() + "\n");
                writer.write("Allocated Budget: " + department.getAllocatedBudget() + "\n");
                writer.write("Expenses:\n");
                for (Expense expense : department.getExpenses()) {
                    writer.write("  - " + expense.toString() + "\n");
                }
                writer.write("\n");
            }
            System.out.println("Budget information saved successfully for event: " + eventName);
        } catch (IOException e) {
            System.out.println("Error saving budget information for event: " + eventName + " - " + e.getMessage());
        }
    }



    public static List<DepartmentBudget> loadBudgetInfo(String eventName) {
        String filePath = getFilePath(eventName);
        List<DepartmentBudget> departmentBudgets = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Budget file created for event: " + eventName);
                }
            } catch (IOException e) {
                System.out.println("Error creating budget file for event: " + eventName + " - " + e.getMessage());
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            DepartmentBudget currentDepartment = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Department: ")) {
                    if (currentDepartment != null) {
                        departmentBudgets.add(currentDepartment);
                    }
                    String departmentName = line.substring(12);
                    currentDepartment = new DepartmentBudget(departmentName, 0);
                } else if (line.startsWith("Allocated Budget: ")) {
                    if (currentDepartment != null) {
                        double allocatedBudget = Double.parseDouble(line.substring(18));
                        currentDepartment.setAllocatedBudget(allocatedBudget);
                    }
                } else if (line.startsWith("  - Expense: ")) {
                    if (currentDepartment != null) {
                        String[] expenseDetails = line.substring(12).split(", Amount: |, Description: ");
                        String expenseName = expenseDetails[0];
                        double amount = Double.parseDouble(expenseDetails[1]);
                        String description = expenseDetails[2];
                        currentDepartment.addExpense(expenseName, amount, description);
                    }
                }
            }
            if (currentDepartment != null) {
                departmentBudgets.add(currentDepartment);
            }
        } catch (IOException e) {
            System.out.println("Error loading budget information for event: " + eventName + " - " + e.getMessage());
        }
        return departmentBudgets;
    }
    public static void clearBudgetData(String eventName) {
        String filePath = getFilePath(eventName);
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Budget data cleared successfully for event: " + eventName);
            } else {
                System.out.println("Error: Unable to clear budget data for event: " + eventName);
            }
        } else {
            System.out.println("No budget data found to clear for event: " + eventName);
        }
    }
}


