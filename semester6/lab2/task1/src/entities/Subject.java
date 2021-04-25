package entities;

import java.util.Objects;

public class Subject {
    private Long id;
    private String name;
    private int hours;

    public Subject(Long id, String name, int hours) {
        this.id = id;
        this.name = name;
        this.hours = hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return getHours() == subject.getHours() && getId().equals(subject.getId()) && getName().equals(subject.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getHours());
    }

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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
