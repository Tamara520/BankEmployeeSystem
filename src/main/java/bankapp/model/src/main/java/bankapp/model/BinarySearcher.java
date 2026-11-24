/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankapp.model;

import java.util.List;

/**
 * BinarySearcher - RECURSIVE binary search by full name on a SORTED list.
 * Time: O(log n).
 */
public final class BinarySearcher {

    private BinarySearcher() { }

    /**
     * Searches the sorted list for a full name, ignoring upper/lower case.
     *
     * @param sorted list sorted by full name
     * @param name   full name to find, e.g. "Kate Summer"
     * @return the employee, or null if the name is not in the list
     */
    public static Employee search(List<Employee> sorted, String name) {
        return search(sorted, name.trim(), 0, sorted.size() - 1);
    }

    private static Employee search(List<Employee> sorted, String name, int low, int high) {
        if (low > high) {                              // base case: not found
            return null;
        }
        int mid = low + (high - low) / 2;
        int cmp = name.compareToIgnoreCase(sorted.get(mid).getFullName());
        if (cmp == 0) {                                // base case: found
            return sorted.get(mid);
        } else if (cmp < 0) {
            return search(sorted, name, low, mid - 1);  // search left half
        } else {
            return search(sorted, name, mid + 1, high); // search right half
        }
    }
}

