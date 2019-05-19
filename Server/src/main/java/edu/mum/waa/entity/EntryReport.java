package edu.mum.waa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EntryReport {
    @Id
    private Long id;

    private String student_id;

    private String name;

    private Integer attendant;

    private Double percent;

    private Double extraCredit;

    public EntryReport(Long id, String student_id, String name, Integer attendant, Double percent, Double extraCredit) {
        this.id = id;
        this.student_id = student_id;
        this.name = name;
        this.attendant = attendant;
        this.percent = percent;
        this.extraCredit = extraCredit;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttendant() {
        return attendant;
    }

    public void setAttendant(Integer attendant) {
        this.attendant = attendant;
    }

    public Double getExtraCredit() {
        return extraCredit;
    }

    public void setExtraCredit(Double extraCredit) {
        this.extraCredit = extraCredit;
    }
}
