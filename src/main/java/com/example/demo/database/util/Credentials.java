package com.example.demo.database.util;

import java.util.Properties;

public class Credentials {
    static public void setPass(Properties dbProperties) {
        dbProperties.setProperty("user", System.getProperty("DB_USER"));
        dbProperties.setProperty("password", System.getProperty("DB_USER_PASS"));
    }
}
