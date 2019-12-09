package com.example.itprom.dto;

public class DepartmentDto {

    private Long id;

    private String name;

    private String note;

    private String parentDepartmentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getParentDepartmentName() {
        return parentDepartmentName;
    }

    public void setParentDepartmentName(String parentDepartmentName) {
        this.parentDepartmentName = parentDepartmentName;
    }
}
