/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankapp.view;

import bankapp.model.Department;
import bankapp.model.Employee;
import bankapp.model.ManagerType;
import bankapp.model.MenuOption;
import java.util.List;
import java.util.Scanner;

/**
 * ConsoleView - the VIEW: everything the user sees or types goes through here.
 * It has no business logic; it only prints and reads.
 */
public class ConsoleView {

    private final Scanner scanner = new Scanner(System.in);

    public void showTitle() {
        System.out.println("==============================================");
        System.out.println("   CCT Bank - Employee Management System");
        System.out.println("==============================================");
    }

    public String askFileName() {
        System.out.print("Please enter the filename to read: ");
        return scanner.nextLine().trim();
    }

    /** Prints the menu straight from the MenuOption enum. */
    public void showMenu() {
        System.out.println("\nDo you wish to SORT or SEARCH:");
        for (MenuOption option : MenuOption.values()) {
            System.out.println(option.getNumber() + ". " + option.getLabel());
        }
    }

    /** Reads a whole number; asks again until the input is a number. */
    public int askNumber(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                showError("Please type a whole number.");
            }
        }
    }

    public String askText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println("  !! " + message);
    }

    private void showHeader() {
        System.out.println(String.format("%-4s %-20s | %-17s | %-17s | %s",
                "#", "Name", "Manager Type", "Department", "Job Title"));
        System.out.println("-".repeat(90));
    }

    /** Prints the first 'limit' employees of the list. */
    public void showEmployees(List<Employee> list, int limit) {
        showHeader();
        int count = Math.min(limit, list.size());
        for (int i = 0; i < count; i++) {
            System.out.println(String.format("%-4d %s", i + 1, list.get(i)));
        }
        System.out.println("(" + count + " of " + list.size() + " employees shown)");
    }

    public void showSearchResult(Employee e) {
        System.out.println("  Name         : " + e.getFullName());
        System.out.println("  Manager Type : " + e.getManagerType());
        System.out.println("  Department   : " + e.getDepartment());
        System.out.println("  Job Title    : " + e.getJobTitle());
    }

    /** Lets the user pick a manager type from the enum; repeats until valid. */
    public ManagerType askManagerType() {
        ManagerType[] types = ManagerType.values();
        System.out.println("Choose a Manager Type:");
        for (int i = 0; i < types.length; i++) {
            System.out.println("  " + (i + 1) + ". " + types[i]);
        }
        while (true) {
            int n = askNumber("Manager Type number: ");
            if (n >= 1 && n <= types.length) {
                return types[n - 1];
            }
            showError("Invalid choice. Enter a number from 1 to " + types.length + ".");
        }
    }

    /** Lets the user pick an existing department from the enum; repeats until valid. */
    public Department askDepartment() {
        Department[] depts = Department.values();
        System.out.println("Choose a Department:");
        for (int i = 0; i < depts.length; i++) {
            System.out.println("  " + (i + 1) + ". " + depts[i]);
        }
        while (true) {
            int n = askNumber("Department number: ");
            if (n >= 1 && n <= depts.length) {
                return depts[n - 1];
            }
            showError("Invalid choice. Enter a number from 1 to " + depts.length + ".");
        }
    }

    /** Prints the tree level by level, then its height and node count. */
    public void showTree(List<List<Employee>> levels, int height, int nodes) {
        System.out.println("Employee hierarchy (level-order traversal):");
        for (int i = 0; i < levels.size(); i++) {
            System.out.println("\nLevel " + i + ":");
            for (Employee e : levels.get(i)) {
                System.out.println("   - " + e.getFullName() + " (" + e.getManagerType()
                        + ", " + e.getDepartment() + ")");
            }
        }
        System.out.println("\nTree height : " + height);
        System.out.println("Total nodes : " + nodes);
    }
}


