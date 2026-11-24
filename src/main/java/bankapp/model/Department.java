/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankapp.model;

/**
 * Department - the departments that exist in the bank.
 * New employees can only be added to one of these departments.
 */
public enum Department {
    IT_DEVELOPMENT("IT Development"),
    FINANCE("Finance"),
    ACCOUNTING("Accounting"),
    SALES("Sales"),
    MARKETING("Marketing"),
    HR("HR"),
    OPERATIONS("Operations"),
    CUSTOMER_SERVICE("Customer Service"),
    TECHNICAL_SUPPORT("Technical Support");

    private final String label;

    Department(String label) {
        this.label = label;
    }

    public String getLabel() { return label; }

    /**
     * Finds the department for a name read from the file.
     *
     * @param name department name from the file
     * @return the matching department, or null if it does not exist
     */
    public static Department fromLabel(String name) {
        if (name != null) {
            for (Department d : values()) {
                if (d.label.equalsIgnoreCase(name.trim())) {
                    return d;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() { return label; }
}


