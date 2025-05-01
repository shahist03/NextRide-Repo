package com.app.app.entity.evalution;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "customer_visit")
public class CustomerVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 40)
    private String name;

    @Column(name = "mobile", nullable = false, length = 10)
    private String mobile;

    @Column(name = "email_id", nullable = false)
    private String emailId;

    @Column(name="pin_code", nullable = false)
    private int pinCode;

    @Column(name = "date_of_visit")
    private LocalDate dateOfVisit;

    public void setDateOfVisit(LocalDate dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    @Column(name = "time_of_visit")
    private LocalTime time_Of_Visit;

    @ManyToOne
    @JoinColumn(name = "agents_id",nullable = true)
    private Agents agents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agents getAgents() {
        return agents;
    }

    public void setAgents(Agents agents) {
        this.agents = agents;
    }

    public LocalTime getTime_Of_Visit() {
        return time_Of_Visit;
    }

    public void setTime_Of_Visit(LocalTime time_Of_Visit) {
        this.time_Of_Visit = time_Of_Visit;
    }



    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
}