# CCT Bank – Employee Management System

**Module:** Algorithms & Constructs – CA2 (Individual)
**Student:** Tamara Tsolmon
**Language:** Java 17 (Maven, NetBeans)

A console application for a bank's HR team. It reads the staff file, sorts and
searches employees with recursive algorithms, adds new staff with validation,
and builds a binary tree of the staff hierarchy.

## Features
| Menu | What it does | Algorithm / structure |
|---|---|---|
| 1. SORT | Sorts all employees by name, shows the first 20 | Recursive merge sort – O(n log n) |
| 2. SEARCH | Finds an employee, shows Manager Type and Department | Recursive binary search – O(log n) |
| 3. ADD RECORDS | Adds a new employee with input validation | Regex + enum choices + duplicate check |
| 4. Create a binary tree | Level-order tree; shows levels, height and node count | Queue-based insertion, recursive height/count |
| 5. EXIT | Closes the program | – |

## Architecture (MVC)
- **Model** (`bankapp.model`): data and algorithms – Employee, EmployeeRepository, MergeSorter, BinarySearcher, EmployeeTree, and the enums MenuOption, ManagerType, Department
- **View** (`bankapp.view`): ConsoleView – all input and output
- **Controller** (`bankapp.controller`): EmployeeController – connects the menu to the model
- **Main**: creates the Model and View and starts the Controller

If the console is replaced by a GUI, only the View changes.

## Why these choices?
- **Merge sort vs quick sort:** merge sort is always O(n log n) and stable; quick sort can fall to O(n²). The cost is O(n) extra memory, which is fine for a staff list.
- **Binary search vs linear search:** O(log n) vs O(n). For 46 names it needs at most 6 comparisons. The list is sorted first so binary search is correct.
- **Level-order tree:** filling the tree level by level with a Queue keeps it complete and balanced. Staff are inserted by seniority, so the Head Manager is the root.
- **Enums:** menu options, manager types and departments are fixed sets, so enums stop invalid values and make the switch statement safe.

## Input validation
- Names: letters, spaces, `'` or `-` only, 1–30 characters
- Manager type and department: chosen by number from the enum lists
- Menu: non-numbers and out-of-range numbers show an error, no crash
- Duplicate names are rejected

## How to run
1. Open the project in NetBeans (File → Open Project).
2. Make sure `Applicants_Form - Sample data file for read.txt` is next to `pom.xml`.
3. Press **Run** (F6).
4. When asked, type: `Applicants_Form - Sample data file for read.txt`

## Sample results
- 46 employees loaded from the file
- After adding 1 employee: tree has **47 nodes**, **height 6**

## Use of AI
Generative AI (Claude) was used for support with code structure, explanations and
the poster layout. All code was tested, understood and committed by me. Details are
in the separate AI-use document.
