package de.rieckpil.user.entity;

import javax.json.Json;
import javax.json.JsonObject;

public class User {

    private long id;
    private String lastName;
    private String firstName;

    public User(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public User(JsonObject json) {
        this.lastName = json.getString("lastName", "not defined");
        this.firstName = json.getString("firstName", "not defined");
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
