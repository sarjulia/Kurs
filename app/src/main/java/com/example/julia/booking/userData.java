package com.example.julia.booking;

public class userData {
    private Object name;
    private Object surname;
    private Object mail;
    private Object tel;
    private Object login;
    private Object pass;
    private Object role;

    public userData(Object name, Object surname, Object mail,Object tel,Object login,Object pass,Object role)
    {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.tel = tel;
        this.login = login;
        this.pass = pass;
        this.role = role;

    }

    public Object getName() {
        return name;
    }
    public Object getSurname() {
        return surname;
    }
    public Object getMail()
    {
        return  mail;
    }
    public Object getTel()
    {
        return tel;
    }
    public Object getLogin() {
        return login;
    }
    public Object getPass()
    {
        return pass;
    }
    public Object getRole() {
        return role;
    }
    public void setName(Object name) {
        this.name = name;
    }

}
