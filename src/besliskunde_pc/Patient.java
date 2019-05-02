
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
    private int weekCall;
    private int dayCall;
    private double serviceLength;
    private double scheduleTimeElective;
    private int weekAppointment;
    private int dayAppointment;
    
    

    public double getScheduleTimeElective() {
        return scheduleTimeElective;
    }

    public void setScheduleTimeElective(double scheduleTimeElective) {
        this.scheduleTimeElective = scheduleTimeElective;
    }

    public double getServiceLength() {
        return serviceLength;
    }

    public void setServiceLength(double serviceLength) {
        this.serviceLength = serviceLength;
    }

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

    public int getWeekCall() {
        return weekCall;
    }

    public void setWeekCall(int weekCall) {
        this.weekCall = weekCall;
    }

    public int getDayCall() {
        return dayCall;
    }

    public void setDayCall(int dayCall) {
        this.dayCall = dayCall;
    }

    public int getWeekAppointment() {
        return weekAppointment;
    }

    public void setWeekAppointment(int weekAppointment) {
        this.weekAppointment = weekAppointment;
    }

    public int getDayAppointment() {
        return dayAppointment;
    }

    public void setDayAppointment(int dayAppointment) {
        this.dayAppointment = dayAppointment;
    }

    

}
