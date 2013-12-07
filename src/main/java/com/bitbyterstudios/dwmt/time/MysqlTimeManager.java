package com.bitbyterstudios.dwmt.time;

import java.util.HashMap;

public class MysqlTimeManager implements ITimeManager{

    private HashMap<String, Time> cachedTime;

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
    public void saveCache() {
        //TODO: ADD MYSQL ACCESS
    }
}
