/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankapp.model;

/**
 * Employee - one person working in the bank.
 * Fields are private and final (encapsulation); there are no setters.
 */
public class Employee {

    private final String firstName;
    private final String lastName;
    private final String jobTitle;
    private final ManagerType managerType;
    private final Department department;

    public Employee(String firstName, String lastName, String jobTitle,
                    ManagerType managerType, Department department) {
        this.firstName   = firstName;
        this.lastName    = lastName;
        this.jobTitle    = jobTitle;
        this.managerType = managerType;
        this.department  = department;
    }

    public String getFirstName()        { return firstName; }
    public String getLastName()         { return lastName; }
    public String getJobTitle()         { return jobTitle; }
    public ManagerType getManagerType() { return managerType; }
    public Department getDepartment()   { return department; }

    /** Full name used for sorting and searching, e.g. "Kate Summer". */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Alphabetical comparison by full name, ignoring upper/lower case.
     * Used by both the merge sort and the binary search.
     *
     * @param other the employee to compare with
     * @return negative, zero or positive, like String.compareTo
     */
    public int compareByName(Employee other) {
        return getFullName().compareToIgnoreCase(other.getFullName());
    }

    @Override
    public String toString() {
        return String.format("%-20s | %-17s | %-17s | %s",
                getFullName(), managerType, department, jobTitle);
    }
}
