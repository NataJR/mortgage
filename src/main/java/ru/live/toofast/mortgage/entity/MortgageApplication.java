package ru.live.toofast.mortgage.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MortgageApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String status;

    private String declineReason;

    public MortgageApplication() {
    }

    public MortgageApplication(Long id, String name, String status, String declineReason) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.declineReason = declineReason;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeclineReason() {
        return declineReason;
    }

    public void setDeclineReason(String declineReason) {
        this.declineReason = declineReason;
    }
}
