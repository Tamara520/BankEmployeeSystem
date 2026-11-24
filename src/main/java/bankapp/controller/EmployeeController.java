/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankapp.controller;

import bankapp.model.Department;
import bankapp.model.Employee;
import bankapp.model.EmployeeRepository;
import bankapp.model.EmployeeTree;
import bankapp.model.ManagerType;
import bankapp.model.MenuOption;
import bankapp.view.ConsoleView;
import java.io.IOException;

/**
 * EmployeeController - the CONTROLLER: reads the user's choice from the view,
 * asks the model to do the work, and sends the result back to the view.
 */
public class EmployeeController {

    private static final int SHOW_LIMIT = 20;
    private static final int MIN_TREE_RECORDS = 20;

    private final EmployeeRepository repository;
    private final ConsoleView view;

    public EmployeeController(EmployeeRepository repository, ConsoleView view) {
        this.repository = repository;
        this.view = view;
    }

    /** Starts the program: load the file, then run the menu loop. */
    public void run() {
        view.showTitle();
        loadFile();

        boolean running = true;
        while (running) {
            view.showMenu();
            MenuOption option = MenuOption.fromNumber(view.askNumber("> "));
            if (option == null) {
                view.showError("Invalid option. Please choose a number from the menu.");
                continue;
            }
            view.showMessage(option.getLabel() + " selected\n");

            switch (option) {
                case SORT               -> sortEmployees();
                case SEARCH             -> searchEmployee();
                case ADD_RECORDS        -> addEmployee();
                case CREATE_BINARY_TREE -> createTree();
                case EXIT               -> {
                    view.showMessage("Goodbye!");
                    running = false;
                }
            }
        }
    }

    /** Asks for a file name until the file is read successfully. */
    private void loadFile() {
        while (true) {
            String fileName = view.askFileName();
            try {
                int count = repository.loadFromFile(fileName);
                if (count == 0) {
                    view.showError("The file has no valid employee records. Try another file.");
                    continue;
                }
                view.showMessage("File read successfully (" + count + " employees loaded)");
                return;
            } catch (IOException e) {
                view.showError("Could not read '" + fileName + "'. Check the name and try again.");
            }
        }
    }

    private void sortEmployees() {
        repository.sort();
        view.showMessage("Employees sorted alphabetically (recursive merge sort):");
        view.showEmployees(repository.getAll(), SHOW_LIMIT);
    }

    private void searchEmployee() {
        String name = view.askText("Enter the full name to search (e.g. Kate Summer): ");
        if (name.isEmpty()) {
            view.showError("Name cannot be empty.");
            return;
        }
        Employee found = repository.search(name);
        if (found == null) {
            view.showError("'" + name + "' was not found.");
        } else {
            view.showMessage("Employee found (recursive binary search):");
            view.showSearchResult(found);
        }
    }

    private void addEmployee() {
        String first = askName("First name: ");
        String last  = askName("Last name: ");
        if (repository.exists(first + " " + last)) {
            view.showError("'" + first + " " + last + "' already exists. Record not added.");
            return;
        }
        ManagerType type = view.askManagerType();
        Department dept  = view.askDepartment();

        repository.add(new Employee(first, last, type.getLabel(), type, dept));
        view.showMessage("\nRecord added. Newly added records:");
        view.showEmployees(repository.getNewlyAdded(), Integer.MAX_VALUE);
    }

    /** Asks for a name with letters, spaces, hyphens or apostrophes only. */
    private String askName(String prompt) {
        while (true) {
            String name = view.askText(prompt);
            if (name.matches("[A-Za-z][A-Za-z '\\-]{0,29}")) {
                // Capitalise first letter: "kate" -> "Kate"
                return Character.toUpperCase(name.charAt(0)) + name.substring(1);
            }
            view.showError("Use letters only (1-30 characters).");
        }
    }

    private void createTree() {
        if (repository.size() < MIN_TREE_RECORDS) {
            view.showError("At least " + MIN_TREE_RECORDS + " records are needed. Now: "
                    + repository.size() + ".");
            return;
        }
        EmployeeTree tree = repository.buildTree();
        view.showTree(tree.levelOrder(), tree.height(), tree.countNodes());
    }
}
