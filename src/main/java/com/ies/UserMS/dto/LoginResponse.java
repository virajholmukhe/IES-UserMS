package com.ies.UserMS.dto;


public class LoginResponse {
    private Integer userID;
    private String name;
    private String role;
    private boolean isValidLogin;
    private boolean isPasswordChanged;
    private DashboardResponse dashboard;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isValidLogin() {
        return isValidLogin;
    }

    public void setValidLogin(boolean validLogin) {
        isValidLogin = validLogin;
    }

    public boolean isPasswordChanged() {
        return isPasswordChanged;
    }

    public void setPasswordChanged(boolean passwordChanged) {
        isPasswordChanged = passwordChanged;
    }

    public DashboardResponse getDashboard() {
        return dashboard;
    }

    public void setDashboard(DashboardResponse dashboard) {
        this.dashboard = dashboard;
    }
}
