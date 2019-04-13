/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besliskunde_pc;

/**
 *
 * @author jwpennem
 */
public class Patient {
    private String category;
    private double calltime;
    private double appointmenttime;
    private double arrivaltime; 
    private double departuretime;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getCalltime() {
        return calltime;
    }

    public void setCalltime(double calltime) {
        this.calltime = calltime;
    }

    public double getAppointmenttime() {
        return appointmenttime;
    }

    public void setAppointmenttime(double appointmenttime) {
        this.appointmenttime = appointmenttime;
    }

    public double getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(double arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public double getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(double departuretime) {
        this.departuretime = departuretime;
    }
       
    
    
}
