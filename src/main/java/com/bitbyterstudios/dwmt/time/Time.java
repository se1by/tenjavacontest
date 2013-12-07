package com.bitbyterstudios.dwmt.time;

public class Time {

    private long seconds;

    public Time(long seconds) {
        this.seconds = seconds;
    }

    public void add(long toAdd) {
        seconds += toAdd;
    }

    @Override
    public String toString() {
        int years = 0;
        int months = 0;
        int weeks = 0;
        int days = 0;
        int hours = 0;
        int minutes = 0;
        int seconds;

        long toParse = this.seconds;

        while (toParse > 29030400) {          //29030400 = seconds in a year
            years++;
            toParse -= 29030400;
        }

        while (toParse > 2419200) {           //2419200 = seconds in a month
            months++;
            toParse -= 2419200;
        }

        while (toParse > 604800) {            // I think you got it
            weeks++;
            toParse -= 604800;
        }

        while (toParse > 86400) {
            days++;
            toParse -= 86400;
        }

        while (toParse > 3600) {
            hours++;
            toParse -= 3600;
        }

        while (toParse > 60) {
            minutes++;
            toParse -= 60;
        }

        seconds = (int) toParse;

        StringBuilder sb = new StringBuilder();
        sb.append(years);

        if (years == 1) {
            sb.append(" year, ");
        } else {
            sb.append(" years, ");
        }

        sb.append(months);
        if (months == 1) {
            sb.append(" month, ");
        } else {
            sb.append(" months, ");
        }

        sb.append(weeks);
        if (weeks == 1) {
            sb.append(" week, ");
        } else {
            sb.append(" weeks, ");
        }

        sb.append(days);
        if (days == 1) {
            sb.append(" day, ");
        } else {
            sb.append(" days, ");
        }

        sb.append(hours);
        if (hours == 1) {
            sb.append(" hour, ");
        } else {
            sb.append(" hours, ");
        }

        sb.append(minutes);
        if (minutes == 1) {
            sb.append(" minute, ");
        } else {
            sb.append(" minutes, ");
        }

        sb.append(seconds);
        if (seconds == 1) {
            sb.append(" second");
        } else {
            sb.append(" seconds");
        }

        return sb.toString();
    }

    public long getSeconds() {
        return seconds;
    }
}
