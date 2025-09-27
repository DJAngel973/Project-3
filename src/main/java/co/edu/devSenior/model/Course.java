package co.edu.devSenior.model;

/**
 * Represents a course with id and name.
 * */
public class Course {
    private String id;
    private String name;
    private Integer capacity;

    public Course() {}

    public Course(String id, String name, Integer capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public Integer getCapacity() {return capacity;}

    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setCapacity(Integer capacity) {this.capacity = capacity;}
}