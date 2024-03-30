package ru.kashtanov.graduation_work.models;

import javax.persistence.*;
import java.util.Objects;

/**
 * Student is an entity that accomplishes the student role in the project
 */
@Entity
@Table(name = "students")
public class Student extends UserEntity {
    private String firstName;
    private String secondName;
    @ManyToOne
    private Tutor tutor;


    public Student() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName)
                && Objects.equals(secondName, student.secondName) && Objects.equals(tutor, student.tutor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, secondName, tutor);
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", tutor=" + tutor +
                '}';
    }
}
