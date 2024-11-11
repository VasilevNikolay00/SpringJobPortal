package com.recruitDemo.recruitDemo.entity;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="job_seeker_save",uniqueConstraints ={@UniqueConstraint(columnNames = {"userId","job"})} )
public class JobSeekerSave implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="job", referencedColumnName = "jobPostId")
    private JobActivity job;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="userId", referencedColumnName = "user_account_id")
    private JobSeekerProfile userId;

    public JobSeekerSave() {
    }

    public JobSeekerSave(int id, JobActivity job, JobSeekerProfile userId) {
        this.id = id;
        this.job = job;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JobActivity getJob() {
        return job;
    }

    public void setJob(JobActivity job) {
        this.job = job;
    }

    public JobSeekerProfile getUserId() {
        return userId;
    }

    public void setUserId(JobSeekerProfile userId) {
        this.userId = userId;
    }



    @Override
    public String toString() {
        return "JobSeekerSave{" +
                "id=" + id +
                ", jobActivityId=" + job +
                ", userId=" + userId +
                '}';
    }
}
