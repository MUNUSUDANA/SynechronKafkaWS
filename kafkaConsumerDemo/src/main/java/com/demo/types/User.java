
package com.demo.types;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


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
@Generated("jsonschema2pojo")
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(User.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("userId");
        sb.append('=');
        sb.append(((this.userId == null)?"<null>":this.userId));
        sb.append(',');
        sb.append("userName");
        sb.append('=');
        sb.append(((this.userName == null)?"<null>":this.userName));
        sb.append(',');
        sb.append("age");
        sb.append('=');
        sb.append(((this.age == null)?"<null>":this.age));
        sb.append(',');
        sb.append("notification");
        sb.append('=');
        sb.append(((this.notification == null)?"<null>":this.notification));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
