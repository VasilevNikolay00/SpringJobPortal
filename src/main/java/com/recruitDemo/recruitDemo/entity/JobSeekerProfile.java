package com.recruitDemo.recruitDemo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "job_seeker_profile")
public class JobSeekerProfile {

    @Id
    private Integer userAccountId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_account_id")
    @MapsId
    private User userId;

    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String country;
    private String workAuthorization;
    private String employmentType;
    private String resume;

    @Column(nullable = true, length = 64)
    private String profilePhoto;

    @OneToMany(targetEntity = Skills.class, cascade = CascadeType.ALL, mappedBy = "jobSeekerProfile")
    private List<Skills> skills;

    public JobSeekerProfile() {
    }

    public JobSeekerProfile(User userId) {
        this.userId = userId;
    }

    public JobSeekerProfile(Integer userAccountId, User userId, String firstName, String lastName, String city, String state, String country, String workAuthorization, String employmentType, String resume, String profilePhoto, List<Skills> skills) {
        this.userAccountId = userAccountId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.workAuthorization = workAuthorization;
        this.employmentType = employmentType;
        this.resume = resume;
        this.profilePhoto = profilePhoto;
        this.skills = skills;
    }

    public Integer getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Integer userAccountId) {
        this.userAccountId = userAccountId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWorkAuthorization() {
        return workAuthorization;
    }

    public void setWorkAuthorization(String workAuthorization) {
        this.workAuthorization = workAuthorization;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public List<Skills> getSkills() {
        return skills;
    }

    public void setSkills(List<Skills> skills) {
        this.skills = skills;
    }

    @Transient
    public String getPhotosImagePath() {
        if (profilePhoto == null || userAccountId == null) return null;
        return "/photos/candidate/" + userAccountId + "/" + profilePhoto;
    }

    @Override
    public String toString() {
        return "JobSeekerProfile{" +
                "userAccountId=" + userAccountId +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", workAuthorization='" + workAuthorization + '\'' +
                ", employmentType='" + employmentType + '\'' +
                ", resume='" + resume + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                '}';
    }
}

//@Entity
//@Table(name = "job_seeker_profile")
//public class JobSeekerProfile {
//
//    @Id
//    private Integer userAccountId;
//
//    @OneToOne
//    @JoinColumn(name = "user_account_id")
//    @MapsId
//    private User userId;
//
//    private String firstName;
//    private String lastName;
//    private String city;
//    private String state;
//    private String country;
//    private String workAuthorization;
//    private String employmentType;
//    private String resume;
//
//    @Column(nullable = true, length = 64)
//    private String profilePhoto;
//
//    @OneToMany(targetEntity = Skills.class, cascade = CascadeType.ALL, mappedBy = "jobSeekerProfile")
//    private List<Skills> skills;
//
//    public JobSeekerProfile() {
//    }
//
//    public JobSeekerProfile(User userId) {
//        this.userId = userId;
//    }
//
//    public JobSeekerProfile(Integer userAccountId, User userId, String firstName, String lastName, String city, String state, String country, String workAuthorization, String employmentType, String resume, String profilePhoto, List<Skills> skills) {
//        this.userAccountId = userAccountId;
//        this.userId = userId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.city = city;
//        this.state = state;
//        this.country = country;
//        this.workAuthorization = workAuthorization;
//        this.employmentType = employmentType;
//        this.resume = resume;
//        this.profilePhoto = profilePhoto;
//        this.skills = skills;
//    }
//
//    public Integer getUserAccountId() {
//        return userAccountId;
//    }
//
//    public void setUserAccountId(Integer userAccountId) {
//        this.userAccountId = userAccountId;
//    }
//
//    public User getUserId() {
//        return userId;
//    }
//
//    public void setUserId(User userId) {
//        this.userId = userId;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getWorkAuthorization() {
//        return workAuthorization;
//    }
//
//    public void setWorkAuthorization(String workAuthorization) {
//        this.workAuthorization = workAuthorization;
//    }
//
//    public String getEmploymentType() {
//        return employmentType;
//    }
//
//    public void setEmploymentType(String employmentType) {
//        this.employmentType = employmentType;
//    }
//
//    public String getResume() {
//        return resume;
//    }
//
//    public void setResume(String resume) {
//        this.resume = resume;
//    }
//
//    public String getProfilePhoto() {
//        return profilePhoto;
//    }
//
//    public void setProfilePhoto(String profilePhoto) {
//        this.profilePhoto = profilePhoto;
//    }
//
//    public List<Skills> getSkills() {
//        return skills;
//    }
//
//    public void setSkills(List<Skills> skills) {
//        this.skills = skills;
//    }
//
//    @Transient
//    public String getPhotosImagePath() {
//        if (profilePhoto == null || userAccountId == null) return null;
//        return "/photos/candidate/" + userAccountId + "/" + profilePhoto;
//    }
//
//
//
//    @Override
//    public String toString() {
//        return "JobSeekerProfile{" +
//                "userAccountId=" + userAccountId +
//                ", userId=" + userId +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", city='" + city + '\'' +
//                ", state='" + state + '\'' +
//                ", country='" + country + '\'' +
//                ", workAuthorization='" + workAuthorization + '\'' +
//                ", employmentType='" + employmentType + '\'' +
//                ", resume='" + resume + '\'' +
//                ", profilePhoto='" + profilePhoto + '\'' +
//                '}';
//    }
//}