package edu.maranatha.entity;

import java.io.Serializable;

/**
 *
 * @author Robby
 */
public class Department implements Serializable {

    private String name;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

}
