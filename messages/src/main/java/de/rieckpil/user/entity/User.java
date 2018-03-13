package de.rieckpil.user.entity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @NotNull
    private String lastName;

    @NotNull
    private String firstName;

    public User(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public User(JsonObject json) {
        this.lastName = json.getString("lastName", "not defined");
        this.firstName = json.getString("firstName", "not defined");
    }

    public User() {

    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("lastName", this.lastName)
                .add("firstName", this.firstName)
                .build();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
