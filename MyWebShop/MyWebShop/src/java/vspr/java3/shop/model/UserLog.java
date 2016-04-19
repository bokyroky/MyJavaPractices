/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vspr.java3.shop.model;

import java.sql.Timestamp;
import java.util.Date;




/**
 *
 * @author Box
 */
public class UserLog {
    private String username;
    private String userIPAdress;
    private Timestamp logDateTime;
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the userIPAdress
     */
    public String getUserIPAdress() {
        return userIPAdress;
    }

    /**
     * @param userIPAdress the userIPAdress to set
     */
    public void setUserIPAdress(String userIPAdress) {
        this.userIPAdress = userIPAdress;
    }

    /**
     * @return the logDateTime
     */
    public Timestamp getLogDateTime() {
        return logDateTime;
    }

    /**
     * @param logDateTime the logDateTime to set
     */
    public void setLogDateTime(Timestamp logDateTime) {
        this.logDateTime = logDateTime;
    }

    

    
    

    
    
   
}
