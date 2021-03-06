/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplog;

/**
 *
 * Date 17/04/2018
 * @author Mohamad Harah
 */
public class Staff extends User {
    /**
     * Staff object class with attributes setters, getters, empty and loaded constructors
     */
    private double salary;
    private String position;

    public Staff() {
        super();
    }

    public Staff(double salary, String position, String userName, String password, String firstName, String lastName) {
        super(userName, password, firstName, lastName);
        this.salary = salary;
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Staff{" + "salary=" + salary + ", position=" + position + '}';
    }
    
}
