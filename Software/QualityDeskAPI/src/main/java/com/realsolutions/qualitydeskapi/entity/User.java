package com.realsolutions.qualitydeskapi.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "FullName", nullable = false, length = 100)
    private String fullName;

    @Column(name = "Mail", nullable = false , length = 100)
    private String mail;

    @Column(name = "Note", length = 100)
    private String note;

    @Column(name = "Password", length = 45)
    private String password;

    @Column(name = "IsCardDefined")
    private Boolean isCardDefined;

    @Column(name = "CreatedDate")
    private Instant createdDate;

    @Column(name = "LastUpdatedDate")
    private Instant lastUpdatedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getCardDefined() {
        return isCardDefined;
    }

    public void setCardDefined(Boolean cardDefined) {
        isCardDefined = cardDefined;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
}
