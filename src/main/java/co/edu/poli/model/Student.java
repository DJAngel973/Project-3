package co.edu.poli.model;

/**
 * Represents a student with id, name, and email.
 * */
public class Student {
    private String id;
    private String name;
    private String email;

    /**
     * Default constructor.
     * */
    public Student() {
    }

    /**
     * Constructs a student with the given id, name, and email.
     * @param id Student's identifier
     * @param name Student's name
     * @param email Student's email
     * */
    public Student(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
