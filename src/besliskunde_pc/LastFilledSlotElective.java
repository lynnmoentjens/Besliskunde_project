/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besliskunde_pc;

/**
 *
 * @author aelaurey
 */
public class LastFilledSlotElective {
    private double time;
    private int week;
    private int day;

    public LastFilledSlotElective(double time, int week, int day) {
        this.time = time;
        this.week = week;
        this.day = day;
    }

    public void setTimeWeekDay(double lastTime, int lastWeek, int lastDay){
        time=lastTime;
        week=lastWeek;
        day=lastDay;
    }
    
    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    
    
}
