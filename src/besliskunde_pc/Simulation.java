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
public class Simulation {
    private int week; 
    private int day;
    private double lengthDay;
    private double time;
    
    private Patient patient;
    Patient electivePatients[] = new Patient[1000000000];
    Patient urgentPatients[] = new Patient[1000000000];
    
    private int numberOfUrgent;
    private int numberOfUrgentInSystem; //moet worden verminderd bij departure
    private int numberOfElectives;
    private int numberOfElectivesArrived;
    private int numberOfElectivesInSystem; // moet worden verminderd bij departure
   
    private double callTime;
    private double appointmentTime; //appointmentTime van de vorige die belt elective
    private double scheduleTimeUrgent;
    private double arrivalTimeElective;
    private double arrivalTimeUrgent;
    private double departureTimeElective;
    private double departureTimeUrgent; 
    private double serviceTime;
    
    private int numberOfAlreadyCallersThatDay;
    private int aantalSlotsElectives;
    
    public void initialization(){
        
        week=1;
        day=1;
        lengthDay= 540; // we beginnen op maandag
        time=0.0;
        numberOfElectives=0;
        numberOfUrgent=0;
        numberOfUrgentInSystem=0;
        appointmentTime=-15;
        //nog aan te vullen
}
    
    
    public void Simulatie(int amountOfWeeksSimulation){
        
        while(week<=amountOfWeeksSimulation){
            //ophalen van de gegevens van de vorige dag
            double scheduledAppointmentsAtBeginDay=0;
            for(int i=1; i<numberOfElectives;i++){
                if(electivePatients[i].getDay()==day){
                    scheduledAppointmentsAtBeginDay++;
                }
            }
            appointmentTime+=(scheduledAppointmentsAtBeginDay*15);
            
            int amountOfElectivesCallingThatDay= Distributions.Poisson_distribution(28.345);
            double timeThatDay=540;
            double interCallingTime= timeThatDay/amountOfElectivesCallingThatDay;
            
            while((time<lengthDay)&&(numberOfUrgentInSystem!=0)&&(numberOfElectivesInSystem!=0)) //electives
            {
                //AppointmentMaken
                if((day!=6)&&(callTime<departureTimeElective)&&(callTime<arrivalTimeElective)&&(callTime<departureTimeUrgent)&&(callTime<arrivalTimeUrgent)){
                    time=callTime; 
                    numberOfElectives++;
                    numberOfElectivesInSystem++;
                    Patient nieuwePatient=new Patient();
                    numberOfAlreadyCallersThatDay++; //bekijken of dit nodig is
                    nieuwePatient= setPatientDataCall(appointmentTime, lengthDay, callTime, day, nieuwePatient); //onderaan 
                    electivePatients[numberOfElectives]= nieuwePatient;
                    callTime = time+interCallingTime;  
                    
                }
                //departureTimeUrgent
                else if((departureTimeUrgent<callTime)&&(departureTimeUrgent<arrivalTimeUrgent)&&(departureTimeUrgent<arrivalTimeElective)&&(departureTimeUrgent<departureTimeElective)){  //Jus: hierna komt een departure van een patient --> Signavio: "Departure event"
                //anneleen
                
                
                }
                //departureTimeElective
                else if((departureTimeElective<callTime)&&(departureTimeElective<arrivalTimeElective)&&(departureTimeElective<arrivalTimeUrgent)&&(departureTimeElective<departureTimeUrgent)){  //Jus: hierna komt een departure van een patient --> Signavio: "Departure event"
                //anneleen 
                
                
                }
                //arrivalTimeUrgent
                else if((arrivalTimeUrgent<callTime)&&(arrivalTimeUrgent<departureTimeUrgent)&&(arrivalTimeUrgent<arrivalTimeElective)&&(arrivalTimeUrgent<departureTimeElective)){
                    time= arrivalTimeUrgent; 
                    numberOfUrgent++;
                    Patient nieuwePatient = new Patient();
                    nieuwePatient = setPatientDataUrgentArrival(arrivalTimeUrgent, day, week);
                    
                    double interarrivalTime= Distributions.Poisson_distribution(1.25);   
                    arrivalTimeUrgent = time+interarrivalTime;
                }
                //arrivalTimeElective
                else if((arrivalTimeElective<callTime)&&(arrivalTimeElective<departureTimeElective)&&(arrivalTimeElective<arrivalTimeUrgent)&&(arrivalTimeElective<departureTimeUrgent)){
                    numberOfElectivesArrived++;
                    int rightNumber= numberOfElectives-numberOfAlreadyCallersThatDay+numberOfElectivesArrived;
                    electivePatients[rightNumber].setArrivaltime(arrivalTimeElective);
                    double interarrivalTime= Distributions.Poisson_distribution(28.345);   
                    arrivalTimeUrgent = time+interarrivalTime;
                }
              
            }
            
        //nodige parameters op nul zetten
        updateParametersAtEndOfDay(day, week);   
            
                  
        }
        //berekeningen maken 
        makeCalculationsElectives();
        makeCalculationsUrgent();
        
    }
    
    private void updateParametersAtEndOfDay(int today, int thisWeek){
        day++;
        time=0;
        if(today==6){
            week++;
            lengthDay=540; 
            day=1;
            aantalSlotsElectives=30;
        }
        else if(today==3||today==5){
            lengthDay=240; 
            aantalSlotsElectives=14;
        }
        else{
            lengthDay=540;
            aantalSlotsElectives=30;
        }
        numberOfElectivesArrived=0;
        numberOfAlreadyCallersThatDay=0;
        appointmentTime=-15;
        
    }
    
    public Patient setPatientDataCall(double appointmentTimePrevious, double lengthDay,double timeMomentCalling, int day, Patient nieuwePatient){
        
        nieuwePatient.setCalltime(timeMomentCalling);
        nieuwePatient.setCategory("Elective");
        
        appointmentTime= appointmentTimePrevious;
        appointmentTime+=15;
        while(appointmentTime<timeMomentCalling){ // als je vandaag plant moet het sowieso na het huidige uur zijn 
            appointmentTime+=15;
        }
        nieuwePatient.setAppointmenttime(appointmentTime);
        nieuwePatient.setDay(day);
        
        //Speciale gevallen --> middag/avond en urgent slots
        if((appointmentTime==15)||(appointmentTime==60)){ // Jus: dit is een voorbeeldje: als bv. slot 2 en slot 5 niet mogen 
            appointmentTime+=15;
            nieuwePatient.setAppointmenttime(appointmentTime);
        }  
        else if(appointmentTime>=540){ //dag is vol exact
            double appointmentNextDay=0;
            appointmentNextDay=appointmentTime-540;
            nieuwePatient.setAppointmenttime(appointmentNextDay);
            nieuwePatient.setDay(day+1);     
        }
        else if(lengthDay!=240&&(appointmentTime==240||appointmentTime==255||appointmentTime==270||appointmentTime==285)){ //alleen voor volle dagen
            appointmentTime=300; // volgende empty slot is na de namiddag
            nieuwePatient.setAppointmenttime(appointmentTime); 
        } 


        return nieuwePatient;
    }
    private Patient setPatientDataUrgentArrival(double arrivalTime, int today, int thisWeek){
        Patient nieuwePatient = new Patient();
        nieuwePatient.setCategory("Urgent");
        nieuwePatient.setArrivaltime(arrivalTime);
        nieuwePatient.setDay(today);
        nieuwePatient.setWeek(thisWeek);
        if(numberOfUrgent==1){
            nieuwePatient.setAppointmenttime(15);
        }
        else if(numberOfUrgent==2){
            nieuwePatient.setAppointmenttime(60);
        }
        else if(numberOfUrgent==3){
            if(today==1||today==2||today==3||today==5){
                nieuwePatient.setAppointmenttime(540);
                scheduleTimeUrgent=540;
            }
            else{
                nieuwePatient.setAppointmenttime(240);
                scheduleTimeUrgent=240;
            }
        }
        else if(numberOfUrgent>2){
            scheduleTimeUrgent+=15;
            nieuwePatient.setAppointmenttime(scheduleTimeUrgent);
        }
        return nieuwePatient;
    }
    private void makeCalculationsElectives(){
        double sumWaitingTillApp=0;
        double sumDelays=0;
        for(int i=1;i<electivePatients.length;i++){
            double waitingTillAppointment= electivePatients[i].getAppointmenttime()-electivePatients[i].getCalltime();
            sumWaitingTillApp+=waitingTillAppointment;
            double ServiceTime= (electivePatients[i].getDeparturetime()-electivePatients[i-1].getDeparturetime());
            double waitingAfterAppointment= electivePatients[i-1].getDeparturetime()-electivePatients[i].getAppointmenttime(); 
            if(waitingAfterAppointment<0){
                waitingAfterAppointment=0;
            }
            sumDelays+=waitingAfterAppointment; 
            //nog verder aanvullen met wat nodig is

        }
        System.out.println(sumWaitingTillApp);
        System.out.println(sumDelays); 
    }
    
    private void makeCalculationsUrgent(){
        //nog verder aanvullen
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

    public double getLengthDay() {
        return lengthDay;
    }

    public void setLengthDay(double lengthDay) {
        this.lengthDay = lengthDay;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient[] getElectivePatients() {
        return electivePatients;
    }

    public void setElectivePatients(Patient[] electivePatients) {
        this.electivePatients = electivePatients;
    }

    public Patient[] getUrgentPatients() {
        return urgentPatients;
    }

    public void setUrgentPatients(Patient[] urgentPatients) {
        this.urgentPatients = urgentPatients;
    }

    public int getNumberOfUrgent() {
        return numberOfUrgent;
    }

    public void setNumberOfUrgent(int numberOfUrgent) {
        this.numberOfUrgent = numberOfUrgent;
    }

    public int getNumberOfUrgentInSystem() {
        return numberOfUrgentInSystem;
    }

    public void setNumberOfUrgentInSystem(int numberOfUrgentInSystem) {
        this.numberOfUrgentInSystem = numberOfUrgentInSystem;
    }

    public int getNumberOfElectives() {
        return numberOfElectives;
    }

    public void setNumberOfElectives(int numberOfElectives) {
        this.numberOfElectives = numberOfElectives;
    }

    public int getNumberOfElectivesArrived() {
        return numberOfElectivesArrived;
    }

    public void setNumberOfElectivesArrived(int numberOfElectivesArrived) {
        this.numberOfElectivesArrived = numberOfElectivesArrived;
    }

    public int getNumberOfElectivesInSystem() {
        return numberOfElectivesInSystem;
    }

    public void setNumberOfElectivesInSystem(int numberOfElectivesInSystem) {
        this.numberOfElectivesInSystem = numberOfElectivesInSystem;
    }

    public double getCallTime() {
        return callTime;
    }

    public void setCallTime(double callTime) {
        this.callTime = callTime;
    }

    public double getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(double appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public double getScheduleTimeUrgent() {
        return scheduleTimeUrgent;
    }

    public void setScheduleTimeUrgent(double scheduleTimeUrgent) {
        this.scheduleTimeUrgent = scheduleTimeUrgent;
    }

    public double getArrivalTimeElective() {
        return arrivalTimeElective;
    }

    public void setArrivalTimeElective(double arrivalTimeElective) {
        this.arrivalTimeElective = arrivalTimeElective;
    }

    public double getArrivalTimeUrgent() {
        return arrivalTimeUrgent;
    }

    public void setArrivalTimeUrgent(double arrivalTimeUrgent) {
        this.arrivalTimeUrgent = arrivalTimeUrgent;
    }

    public double getDepartureTimeElective() {
        return departureTimeElective;
    }

    public void setDepartureTimeElective(double departureTimeElective) {
        this.departureTimeElective = departureTimeElective;
    }

    public double getDepartureTimeUrgent() {
        return departureTimeUrgent;
    }

    public void setDepartureTimeUrgent(double departureTimeUrgent) {
        this.departureTimeUrgent = departureTimeUrgent;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(double serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getNumberOfAlreadyCallersThatDay() {
        return numberOfAlreadyCallersThatDay;
    }

    public void setNumberOfAlreadyCallersThatDay(int numberOfCallersThatDay) {
        this.numberOfAlreadyCallersThatDay = numberOfCallersThatDay;
    }

    public int getAantalSlotsElectives() {
        return aantalSlotsElectives;
    }

    public void setAantalSlotsElectives(int aantalSlotsElectives) {
        this.aantalSlotsElectives = aantalSlotsElectives;
    }
    
}

