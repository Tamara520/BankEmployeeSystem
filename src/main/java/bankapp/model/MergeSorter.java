/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * MergeSorter - RECURSIVE merge sort of employees by full name.
 * Time: O(n log n) in every case. Extra space: O(n). Stable.
 */
public final class MergeSorter {

    private MergeSorter() { }

    /**
     * Returns a new list sorted alphabetically by full name.
     *
     * @param list the employees to sort (not changed)
     * @return a new sorted list
     */
    public static List<Employee> sort(List<Employee> list) {
        if (list.size() <= 1) {                       // base case
            return new ArrayList<>(list);
        }
        int mid = list.size() / 2;
        List<Employee> left  = sort(list.subList(0, mid));            // recursive call
        List<Employee> right = sort(list.subList(mid, list.size()));  // recursive call
        return merge(left, right);
    }

    /** Joins two sorted lists into one sorted list. */
    private static List<Employee> merge(List<Employee> left, List<Employee> right) {
        List<Employee> result = new ArrayList<>(left.size() + right.size());
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            // "<=" keeps equal names in their original order (stable sort)
            if (left.get(i).compareByName(right.get(j)) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }
        while (i < left.size())  { result.add(left.get(i++)); }
        while (j < right.size()) { result.add(right.get(j++)); }
        return result;
    }
}

