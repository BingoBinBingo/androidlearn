package com.example.root.andriodlearn;

class UserBean {
    private String username;
    private String password;
    UserBean(String username, String password)
    {
        this.username=username;
        this.password=password;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
//    }

    String getJson() {
        return "{\"username\":\""+username+"\",\"password\":\""+password+"\"}";
    }
}
