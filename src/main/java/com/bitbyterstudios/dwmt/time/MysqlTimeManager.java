package com.bitbyterstudios.dwmt.time;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class MysqlTimeManager implements ITimeManager{

    private HashMap<String, Time> cachedTime;

    private Connection conn;
    private String host;
    private String port;
    private String database;
    private String user;
    private String pass;

    private String pps = "INSERT INTO times(user, time) VALUES(1, 2) ON DUPLICATE KEY UPDATE time=2";

    public MysqlTimeManager(String host, String port, String database, String user, String pass) {      //WIP!
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port
                    + "/" + database + "?user=" + user + "&password=" + pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Time getTime(String name) {
        if (cachedTime.containsKey(name)) {
            return cachedTime.get(name);
        }
        return null;//TODO: ADD MYSQL ACCESS
    }

    public void setTime(String name, Time time) {
        cachedTime.put(name, time);
        //TODO: ADD MYSQL ACCESS
    }

    @Override
    public void setTime(String name, long seconds) {

    }

    @Override
    public boolean hasTime(String name) {
        return false;
    }

    public void subtract(String name, long seconds) {
        getTime(name).add(seconds * -1);
    }

    @Override
    public void transfer(String giver, String receiver, int seconds) {
        subtract(giver, seconds);
        subtract(receiver, seconds * -1);
    }

    @Override
    public void save(String name) {

    }

    @Override
    public void saveCache() {
        //TODO: ADD MYSQL ACCESS
    }
}
