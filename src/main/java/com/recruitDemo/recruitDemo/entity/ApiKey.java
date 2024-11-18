package com.recruitDemo.recruitDemo.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="api_keys")
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="api_key")
    private String apiKey;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="creation_date")
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="deletion_date")
    private Date deletionDate;

    private String access;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="userId", referencedColumnName = "userId")
    private User userId;

    public ApiKey() {
    }

    public ApiKey(Integer id, String apiKey, Date creationDate, Date deletionDate, String access, User userId) {
        this.id = id;
        this.apiKey = apiKey;
        this.creationDate = creationDate;
        this.deletionDate = deletionDate;
        this.access = access;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDeletionDate() {
        return deletionDate;
    }

    public void setDeletionDate(Date deletionDate) {
        this.deletionDate = deletionDate;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ApiKey{" +
                "id=" + id +
                ", apiKey='" + apiKey + '\'' +
                ", creationDate=" + creationDate +
                ", deletionDate=" + deletionDate +
                ", access='" + access + '\'' +
                ", userId=" + userId +
                '}';
    }
}
