package edu.mum.waa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@SequenceGenerator(name="seq", initialValue=20)
public class TmCheckAndRetreat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq")
    private long id;

    @ManyToOne
    private Student student;

    private LocalDate localDateTime;

    private boolean retreat;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDate localDateTime) {
        this.localDateTime = localDateTime;
    }

    public boolean isRetreat() {
        return retreat;
    }

    public void setRetreat(boolean retreat) {
        this.retreat = retreat;
    }
}
