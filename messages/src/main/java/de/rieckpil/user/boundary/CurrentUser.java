/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rieckpil.user.boundary;

import javax.inject.Named;

/**
 *
 * @author rieckpil
 */
@Named("currentUser")
public class CurrentUser {
    
    private String name = "rieckpil";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
