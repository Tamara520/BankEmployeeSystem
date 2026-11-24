/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankapp.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * EmployeeRepository - keeps all employees in memory and loads them from the file.
 * This is the main MODEL class: it holds the data and the rules, and knows
 * nothing about the console.
 */
public class EmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();
    private final List<Employee> newlyAdded = new ArrayList<>();
    private boolean sorted = false;

    /**
     * Reads the CSV file. The first line is the header and is skipped.
     * Columns: First name, Last name, Gender, Email, Salary, Department,
     *          Position, Job title, Company
     *
     * @param fileName path of the file
     * @return number of employees loaded
     * @throws IOException if the file cannot be read
     */
    public int loadFromFile(String fileName) throws IOException {
        employees.clear();
        newlyAdded.clear();
        sorted = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();               // skip header
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;                              // skip blank lines
                }
                String[] cols = line.split(",", -1);
                if (cols.length < 9) {
                    continue;                              // skip broken lines
                }
                Department dept = Department.fromLabel(cols[5]);
                if (dept == null) {
                    continue;                              // unknown department
                }
                String jobTitle = cols[7].trim();
                employees.add(new Employee(cols[0].trim(), cols[1].trim(), jobTitle,
                        ManagerType.fromJobTitle(jobTitle), dept));
            }
        }
        return employees.size();
    }

    /** Sorts the list with the recursive merge sort. */
    public void sort() {
        List<Employee> result = MergeSorter.sort(employees);
        employees.clear();
        employees.addAll(result);
        sorted = true;
    }

    /**
     * Finds an employee by full name with the recursive binary search.
     * The list is sorted first if needed, because binary search needs order.
     *
     * @param fullName name to find
     * @return the employee, or null if not found
     */
    public Employee search(String fullName) {
        if (!sorted) {
            sort();
        }
        return BinarySearcher.search(employees, fullName);
    }

    /** True if an employee with this full name already exists. */
    public boolean exists(String fullName) {
        return search(fullName) != null;
    }

    /** Adds a new employee and keeps the list sorted. */
    public void add(Employee employee) {
        employees.add(employee);
        newlyAdded.add(employee);
        sort();
    }

    /**
     * Builds the employee hierarchy. Employees are ordered by seniority
     * (Head Manager first), so the most senior person becomes the root.
     *
     * @return the tree
     */
    public EmployeeTree buildTree() {
        List<Employee> bySeniority = new ArrayList<>(employees);
        // stable sort: same rank keeps alphabetical order
        bySeniority.sort((a, b) -> Integer.compare(
                a.getManagerType().getRank(), b.getManagerType().getRank()));
        EmployeeTree tree = new EmployeeTree();
        for (Employee e : bySeniority) {
            tree.insert(e);
        }
        return tree;
    }

    public List<Employee> getAll()        { return Collections.unmodifiableList(employees); }
    public List<Employee> getNewlyAdded() { return Collections.unmodifiableList(newlyAdded); }
    public int size()                     { return employees.size(); }
    public boolean isEmpty()              { return employees.isEmpty(); }
}


