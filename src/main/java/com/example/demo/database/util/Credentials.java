package com.example.demo.database.util;

import java.util.Properties;

public class Credentials {
    static public void setPass(Properties dbProperties) {
        dbProperties.setProperty("user", System.getenv("DB_USER"));
        dbProperties.setProperty("password", System.getenv("DB_USER_PASS"));
//        dbProperties.setProperty("user", "app_potpal");
//        dbProperties.setProperty("password", "84P5*q9^4N@NkR^j&jofu@7p");
    }
}
