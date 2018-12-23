package com.example.julia.booking;

public class currentUserData {
    private static String login = null;
    private static String name = null;
    private static String role = null;

    public static void setCurrentUserData(String name2, String login2, String role2)
    {
        login = login2;
        name = name2;
        role = role2;
    }
    public static String getLogin()
    {
        return login;
    }
    public static String getName()
    {
        return name;
    }
    public static String getRole()
    {
        return role;
    }

}
