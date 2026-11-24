/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankapp;

import bankapp.controller.EmployeeController;
import bankapp.model.EmployeeRepository;
import bankapp.view.ConsoleView;

/**
 * Main - starts the application by connecting Model, View and Controller (MVC).
 */
public class Main {

    public static void main(String[] args) {
        EmployeeRepository model = new EmployeeRepository();
        ConsoleView view = new ConsoleView();
        EmployeeController controller = new EmployeeController(model, view);
        controller.run();
    }
}
