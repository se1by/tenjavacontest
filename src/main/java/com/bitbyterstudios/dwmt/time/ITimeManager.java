package com.bitbyterstudios.dwmt.time;

public interface ITimeManager {

    public Time getTime(String name);
    public void setTime(String name, Time time);
    public void setTime(String name, long seconds);
    public boolean hasTime(String name);
    public void subtract(String name, long seconds);
    public void transfer(String giver, String receiver, int seconds);
    public void save(String name);
    public void saveCache();
}
