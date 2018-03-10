package de.rieckpil.user.boundary;

import javax.enterprise.inject.Model;
import javax.validation.constraints.Size;

@Model
public class Index {

    @Size(min = 2, max = 5)
    private String hello;

    private String lastName;

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Object save() {
        System.out.println("-saved on serfver- " + this.hello + " -- " + this.lastName);
        return null;
    }


}
