/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.Serializable;

/**
 *
 * @author letie
 */
public class User implements Serializable {

    private String id;
    private String roleId;
    private String name;
    private String email;
    private String address;
    private String phoneNum;
    private String username;
    private String password;
    private String avatarUrl;

    public User() {
    }

    public User(String id, String roleId, String name, String address, String email, String phoneNum, String username, String password, String avatarUrl) {
        this.id = id;
        this.roleId = roleId;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.username = username;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}