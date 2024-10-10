
package com.demo.types;

import java.util.LinkedHashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * User
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "userId",
    "userName",
    "age",
    "notification"
})
public class User {

    /**
     * The unique identifier for a user
     * (Required)
     * 
     */
    @JsonProperty("userId")
    @JsonPropertyDescription("The unique identifier for a user")
    private Integer userId;
    /**
     * Name of the user
     * (Required)
     * 
     */
    @JsonProperty("userName")
    @JsonPropertyDescription("Name of the user")
    private String userName;
    /**
     * The age of the user
     * (Required)
     * 
     */
    @JsonProperty("age")
    @JsonPropertyDescription("The age of the user")
    private Double age;
    /**
     * Notifications way for the user
     * 
     */
    @JsonProperty("notification")
    @JsonDeserialize(as = java.util.LinkedHashSet.class)
    @JsonPropertyDescription("Notifications way for the user")
    private Set<String> notification = new LinkedHashSet<String>();

    /**
     * The unique identifier for a user
     * (Required)
     * 
     */
    @JsonProperty("userId")
    public Integer getUserId() {
        return userId;
    }

    /**
     * The unique identifier for a user
     * (Required)
     * 
     */
    @JsonProperty("userId")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User withUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Name of the user
     * (Required)
     * 
     */
    @JsonProperty("userName")
    public String getUserName() {
        return userName;
    }

    /**
     * Name of the user
     * (Required)
     * 
     */
    @JsonProperty("userName")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * The age of the user
     * (Required)
     * 
     */
    @JsonProperty("age")
    public Double getAge() {
        return age;
    }

    /**
     * The age of the user
     * (Required)
     * 
     */
    @JsonProperty("age")
    public void setAge(Double age) {
        this.age = age;
    }

    public User withAge(Double age) {
        this.age = age;
        return this;
    }

    /**
     * Notifications way for the user
     * 
     */
    @JsonProperty("notification")
    public Set<String> getNotification() {
        return notification;
    }

    /**
     * Notifications way for the user
     * 
     */
    @JsonProperty("notification")
    public void setNotification(Set<String> notification) {
        this.notification = notification;
    }

    public User withNotification(Set<String> notification) {
        this.notification = notification;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userId", userId).append("userName", userName).append("age", age).append("notification", notification).toString();
    }

}
