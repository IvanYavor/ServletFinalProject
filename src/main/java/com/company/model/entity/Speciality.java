package com.company.model.entity;

public class Speciality {
    private Integer id;
    private String name;
    private String description;
    private String faculty;

    public Speciality() {
    }

    public Speciality(Integer id, String name, String description, String faculty) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.faculty = faculty;
    }

    public Speciality(String name, String description, String faculty) {
        this.name = name;
        this.description = description;
        this.faculty = faculty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
