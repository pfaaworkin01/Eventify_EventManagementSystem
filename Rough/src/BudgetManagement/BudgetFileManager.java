package BudgetManagement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BudgetFileManager {

    private static final String FILE_PATH = "Budget_Data.txt";

    public static void saveBudgetInfo(List<DepartmentBudget> departmentBudgets) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (DepartmentBudget department : departmentBudgets) {
                writer.write("Department: " + department.getDepartmentName() + "\n");
                writer.write("Allocated Budget: " + department.getAllocatedBudget() + "\n");
                writer.write("Expenses:\n");
                for (Expense expense : department.getExpenses()) {
                    writer.write("  - " + expense.toString() + "\n");
                }
                writer.write("\n");
            }
            System.out.println("Budget information saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving budget information: " + e.getMessage());
        }
    }
}
