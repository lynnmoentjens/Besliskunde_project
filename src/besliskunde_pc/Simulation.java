/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besliskunde_pc;

import java.util.ArrayList;
import java.util.Collections;

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
    Patient[] patients = new Patient[10000];
    
    private int numberOfUrgent;
    //private int numberOfUrgentInSystem; moet worden verminderd bij departure
    private int totalNumberOfPatients;
    //private int numberOfElectivesArrived;
   // private int numberOfElectivesInSystem; // moet worden verminderd bij departure
    private int numberOfElectivesForTomorrow;
    private int numberOfPatients;
   
    private double callTime;
    private double appointmentTime; //appointmentTime van de vorige die belt elective
    private double scheduleTimeUrgent;
    //private double arrivalTimeElective;
    private double arrivalTimeUrgent;
    //private double departureTimeElective;
    //private double departureTimeUrgent; 

   
   // private int numberOfAlreadyCallersThatDay;
    private double lastScheduledAppointment;
   
    public void initialization(){
        
        week=1;
        day=1;
        lengthDay= 540; // we beginnen op maandag
        time=0.0;
        
        numberOfUrgent=0;
        //numberOfUrgentInSystem=0;
        totalNumberOfPatients=0;
        //numberOfElectivesArrived=0;
        //numberOfElectivesInSystem=0;
        numberOfElectivesForTomorrow=0;
        numberOfPatients=0;
        
        callTime=0;
        appointmentTime=-15;
        //scheduleTimeUrgent=0;
        //arrivalTimeElective=0;
        //departureTimeUrgent=5000;
        //arrivalTimeUrgent wordt bepaalt begin van de dag
        
        //numberOfAlreadyCallersThatDay=0;

        lastScheduledAppointment=0;
}
    
    
      public void Simulatie(int amountOfWeeksSimulation){
        
        while(week<=amountOfWeeksSimulation){
            //ophalen van de gegevens van de vorige dag
            //apppointmentTimes
            appointmentTime+=(numberOfElectivesForTomorrow*15);
            numberOfPatients=numberOfElectivesForTomorrow; 
            numberOfElectivesForTomorrow=0;
            System.out.println("AppointmentTIme door gisteren algepland:"+appointmentTime);
            System.out.println("NumberOfPatientsThatDay al door vorige dag schedule"+numberOfPatients);
            
            //electives intercallingtime per dag berekening
            int amountOfElectivesCallingThatDay= Distributions.Poisson_distribution(28.345);
            double timeThatDayCalling=540;
            double interCallingTime= timeThatDayCalling/amountOfElectivesCallingThatDay;
            System.out.println("AantalElectivesBellendiedag"+amountOfElectivesCallingThatDay);
            System.out.println("TijdTussenBellen"+interCallingTime);
            
            
            //lijst maken met arrivaltimes van de urgent patients
            ArrayList<Double> arrayVanArrivalTimes;
            arrayVanArrivalTimes = new ArrayList<>();
            int amountOfUrgentArrivingThatDay;
            if(day==4||day==6){
                amountOfUrgentArrivingThatDay= Distributions.Poisson_distribution(1.25);
                for(int i=0;i<amountOfUrgentArrivingThatDay;i++){
                    double randomgetal;
                    randomgetal = Distributions.Exponential_distribution(1.25)*lengthDay;
                    arrayVanArrivalTimes.add(randomgetal);
                }
                Collections.sort(arrayVanArrivalTimes);
                if(amountOfUrgentArrivingThatDay==0){
                    arrivalTimeUrgent=Double.POSITIVE_INFINITY;
                }
                else{
                    arrivalTimeUrgent=arrayVanArrivalTimes.get(0);
                }
            }
            else{
                amountOfUrgentArrivingThatDay= Distributions.Poisson_distribution(2.5);
                for(int i=0;i<amountOfUrgentArrivingThatDay;i++){
                    double randomgetal;
                    randomgetal = Distributions.Exponential_distribution(2.5)*lengthDay;
                    arrayVanArrivalTimes.add(randomgetal);
                }
                Collections.sort(arrayVanArrivalTimes);
                if(amountOfUrgentArrivingThatDay==0){
                    arrivalTimeUrgent=Double.POSITIVE_INFINITY;
                }else{
                    arrivalTimeUrgent=arrayVanArrivalTimes.get(0);
                }
            }
            System.out.println("aantalUrgentArrivingthatDay"+amountOfUrgentArrivingThatDay);
            System.out.println("ArrivalTimeEeersteUrgent"+arrivalTimeUrgent);
            int numberOfPatientsThatHaveToUrgentArriveOrElectiveCall=amountOfUrgentArrivingThatDay+amountOfElectivesCallingThatDay;

            while(time<540||numberOfPatients<numberOfPatientsThatHaveToUrgentArriveOrElectiveCall) // maakt niet uit 240 want u urgentArrivals zijn al bepaald en je mag bellen in namiddag op donderdag
            {// lengthDay --> opnieuw bekijken want je kan op halve ook nog bellen --> oplossing zoeken 
                //AppointmentMaken
                if((day!=6)&&(callTime<arrivalTimeUrgent)){
                    System.out.println("Dag in de week"+day);
                    time=callTime; 
                    System.out.println("Elective Belt");
                    System.out.println( "time = " + time);
                    totalNumberOfPatients++; //over het hele programma/alle weken
                    //numberOfElectivesInSystem++;
                    numberOfPatients++; // per dag 
                    System.out.println("numberof patients = " + numberOfPatients);
                    System.out.println("total = " + totalNumberOfPatients);
                    Patient nieuwePatient=new Patient();
                    //numberOfAlreadyCallersThatDay++; //bekijken of dit nodig is
                    System.out.println("AppointmentTime"+appointmentTime);
                    nieuwePatient= setPatientDataCall(appointmentTime, lengthDay, callTime, day, nieuwePatient); //onderaan 
                    patients[totalNumberOfPatients-1]= nieuwePatient; // je begint op 0
                    System.out.println("Appointmenttime"+nieuwePatient.getAppointmenttime());
                    System.out.println("ArrivalTime"+nieuwePatient.getArrivaltime());
                    System.out.println("CallTime"+nieuwePatient.getCalltime());
                    
                    callTime = time+interCallingTime;  
                    System.out.println("calltime = " + callTime);
                    System.out.println("___________________________________________");
                    if(lastScheduledAppointment<nieuwePatient.getAppointmenttime()){
                        lastScheduledAppointment=nieuwePatient.getAppointmenttime();
                    }  
                }
                /*//departureTimeUrgent
                else if((departureTimeUrgent<callTime)&&(departureTimeUrgent<arrivalTimeUrgent)&&(departureTimeUrgent<arrivalTimeElective)&&(departureTimeUrgent<departureTimeElective)){  //Jus: hierna komt een departure van een patient --> Signavio: "Departure event"
                //anneleen
                
                
                }
                //departureTimeElective
                else if((departureTimeElective<callTime)&&(departureTimeElective<arrivalTimeElective)&&(departureTimeElective<arrivalTimeUrgent)&&(departureTimeElective<departureTimeUrgent)){  //Jus: hierna komt een departure van een patient --> Signavio: "Departure event"
                //anneleen 
                
                
                }*/
                //arrivalTimeUrgent
                else if((numberOfUrgent<amountOfUrgentArrivingThatDay)&&(arrivalTimeUrgent<callTime)&&(numberOfUrgent<=amountOfUrgentArrivingThatDay)){
                    time= arrivalTimeUrgent; 
                    System.out.println("Urgent patient komt aan");
                    System.out.println("TimeUrgentArrival="+time);
                    numberOfUrgent++;
                    numberOfPatients++;
                    totalNumberOfPatients++;
                    System.out.println("totalUrgentThatday="+numberOfUrgent);
                    System.out.println("totalThatday="+numberOfPatients);
                    System.out.println("total="+totalNumberOfPatients);
                    Patient nieuwePatient = new Patient();
                    nieuwePatient = setPatientDataUrgentArrival(arrivalTimeUrgent, day, week, nieuwePatient);
                    patients[totalNumberOfPatients-1]=nieuwePatient;
                    System.out.println("Appointmenttime Urgent"+nieuwePatient.getAppointmenttime());
                    System.out.println("ArrivalTime Urgent"+nieuwePatient.getArrivaltime());
                    
                    
                    if(lastScheduledAppointment<nieuwePatient.getAppointmenttime()){
                        lastScheduledAppointment=nieuwePatient.getAppointmenttime();
                    }
                    if(numberOfUrgent<amountOfUrgentArrivingThatDay){
                        arrivalTimeUrgent= arrayVanArrivalTimes.get(numberOfUrgent);//arrivalTime van de volgende
                    }
                    System.out.println("ArrivalTime volgende"+arrivalTimeUrgent);
                    System.out.println("___________________________________________");
                }
                //arrivalTimeElective
                /*else if((arrivalTimeElective<callTime)&&(arrivalTimeElective<departureTimeElective)&&(arrivalTimeElective<arrivalTimeUrgent)&&(arrivalTimeElective<departureTimeUrgent)){
                    numberOfElectivesArrived++;
                    int rightNumber= numberOfElectives-numberOfAlreadyCallersThatDay+numberOfElectivesArrived;
                    electivePatients[rightNumber].setArrivaltime(arrivalTimeElective);
                    double interarrivalTime= Distributions.Poisson_distribution(28.345);   
                    arrivalTimeUrgent = time+interarrivalTime;
                }*/
              
            }
        System.out.println("gaat naar departure");
        double departureTimeVorige=0;
        for(int i=0;i<=lastScheduledAppointment-1;i=i+15){ ///tijd overlopen
            System.out.println(totalNumberOfPatients-numberOfPatients);
            for(int j=(totalNumberOfPatients-numberOfPatients);j<numberOfPatients-numberOfElectivesForTomorrow;j++){
                if(patients[j].getAppointmenttime()==i){
                    double appointmentTimeDeze;
                    appointmentTimeDeze = patients[j].getAppointmenttime();
                    double arrivalTimeDeze;
                    arrivalTimeDeze= patients[j].getArrivaltime();
                    double departureTime;
                    String category= patients[j].getCategory();
                    double serviceLength= determineServiceTime(category);
                    if(departureTimeVorige>=appointmentTimeDeze&&departureTimeVorige>=arrivalTimeDeze){
                        patients[j].setServiceLength(serviceLength);
                        departureTime=departureTimeVorige+serviceLength;
                        patients[j].setDeparturetime(departureTime);
                        departureTimeVorige=departureTime;
                    }
                    else if(arrivalTimeDeze>=appointmentTimeDeze&&arrivalTimeDeze>=departureTimeVorige){
                        patients[j].setServiceLength(serviceLength);
                        departureTime=arrivalTimeDeze+serviceLength;
                        patients[j].setDeparturetime(departureTime);
                        departureTimeVorige=departureTime;
                    }
                    else if(appointmentTimeDeze>=arrivalTimeDeze&&appointmentTimeDeze>=departureTimeVorige){
                        patients[j].setServiceLength(serviceLength);
                        departureTime=appointmentTimeDeze+determineServiceTime(category);
                        patients[j].setDeparturetime(departureTime);
                        departureTimeVorige=departureTime;
                    }
                    System.out.println(j);
                    System.out.println(patients[j].getAppointmenttime());
                    System.out.println(patients[j].getArrivaltime());
                    System.out.println(patients[j].getDeparturetime());
                }
                
            }
        }
        //nodige parameters op nul zetten
        updateParametersAtEndOfDay(day, week);   
            
                  
        }
        //berekeningen maken 
       /* makeCalculationsElectives();
        makeCalculationsUrgent();*/
        
    }
    
    private void updateParametersAtEndOfDay(int today, int thisWeek){
        day++;
        time=0;
        if(today==6){
            week++;
            lengthDay=540; 
            day=1;
            
        }
        else if(today==3||today==5){
            lengthDay=240; 
            
        }
        else{
            lengthDay=540;
            
        }
        //numberOfElectivesArrived=0;
        //numberOfAlreadyCallersThatDay=0;
        appointmentTime=-15;
        numberOfUrgent=0;
        numberOfPatients=0;
        callTime=0;
        scheduleTimeUrgent=0;
        
    }
    
    public Patient setPatientDataCall(double appointmentTimePrevious, double lengthDay,double timeMomentCalling, int day, Patient nieuwePatient){
        
        nieuwePatient.setCalltime(timeMomentCalling);
        nieuwePatient.setCategory("Elective");
        
        appointmentTime= appointmentTimePrevious;
        appointmentTime+=15;
        while(appointmentTime<=timeMomentCalling){ // als je vandaag plant moet het sowieso na het huidige uur zijn 
            appointmentTime+=15;
        }
        nieuwePatient.setAppointmenttime(appointmentTime);
        nieuwePatient.setDay(day);
        
        //Speciale gevallen --> middag/avond en urgent slots
        if((appointmentTime==15)||(appointmentTime==60)){ // Jus: dit is een voorbeeldje: als bv. slot 2 en slot 5 niet mogen 
            appointmentTime+=15;
            nieuwePatient.setAppointmenttime(appointmentTime);
        }  
        else if(lengthDay!=240&&appointmentTime>=540){ //voor volgende dag als vandaag volle dag
            double appointmentNextDay=0;
            appointmentNextDay=appointmentTime-540;
            nieuwePatient.setAppointmenttime(appointmentNextDay);
            nieuwePatient.setDay(day+1); 
            numberOfElectivesForTomorrow++;
        }
        else if(lengthDay!=240&&(appointmentTime==240||appointmentTime==255||appointmentTime==270||appointmentTime==285)){ //alleen voor volle dagen
            appointmentTime=300; // volgende empty slot is na de namiddag
            nieuwePatient.setAppointmenttime(appointmentTime); 
        } 
        else if(lengthDay==240&&appointmentTime>=240){ // voor volgende dag als vandaag halve dag
            double appointmentNextDay=0;
            appointmentNextDay=appointmentTime-240;
            nieuwePatient.setAppointmenttime(appointmentNextDay);
            nieuwePatient.setDay(day+1);     
            numberOfElectivesForTomorrow++;
        }
        double afwijkingArrivalTime = Distributions.Normal_distribution(0, 2.5);
        nieuwePatient.setArrivaltime(appointmentTime+afwijkingArrivalTime);

        return nieuwePatient;
    }
    public Patient setPatientDataUrgentArrival(double arrivalTime, int today, int thisWeek, Patient nieuwePatient){
        
        nieuwePatient.setCategory("Urgent");
        nieuwePatient.setArrivaltime(arrivalTime);
        nieuwePatient.setDay(today);
        nieuwePatient.setWeek(thisWeek);
        if(numberOfUrgent==1&&arrivalTime<=15){
            nieuwePatient.setAppointmenttime(15);
        }
        else if(numberOfUrgent==2&&arrivalTime<=60){
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
        else if(numberOfUrgent>3){
            scheduleTimeUrgent+=15;
            nieuwePatient.setAppointmenttime(scheduleTimeUrgent);
        }
        return nieuwePatient;
    }
    
    public double determineServiceTime(String category){
        double serviceTime=0;
        if(category.equals("Elective")){
            serviceTime= Distributions.Normal_distribution(15, 3);
        }
        else{
            //service time for urgents patients
            double numberFrequency = Math.random(); //getal aanmaken tussen [0.0 ; 1.0[
            
            if(numberFrequency>=0.0 && numberFrequency<0.7) // brain scan
                serviceTime = Distributions.Normal_distribution(15, 2.5);
            
            else if(numberFrequency>=0.7 && numberFrequency<0.8) //spine lumbar scan
                serviceTime= Distributions.Normal_distribution(17.5, 1);
            
            else if (numberFrequency>=0.8 && numberFrequency<0.9) //spine cervical scan
                serviceTime= Distributions.Normal_distribution(22.5, 2.5);
            
            else if (numberFrequency>=0.9 && numberFrequency<0.95) // abdomen scan
                serviceTime= Distributions.Normal_distribution(30,1);
            
            else
                serviceTime= Distributions.Normal_distribution(30, 4.5); //others
            
        }
        return serviceTime;
    }/*
    private double averageAppointmentWaitingTimeElectives(){ // performance measure 1 --> average appointment waiting time elective
        
        double sumWaitingTillApp=0;
        double averageAppointmentWaitingTime = 0;
        //double sumDelays=0;
        
        
        for(int i=1;i<electivePatients.length;i++){
/*begint een array niet bij 0?   double waitingTillAppointment= electivePatients[i].getAppointmenttime()-electivePatients[i].getCalltime();
            sumWaitingTillApp+=waitingTillAppointment;
        }
            
            //double ServiceTime= (electivePatients[i].getDeparturetime()-electivePatients[i-1].getDeparturetime());
            //double waitingAfterAppointment= electivePatients[i-1].getDeparturetime()-electivePatients[i].getAppointmenttime(); 
            //if(waitingAfterAppointment<0){
            //    waitingAfterAppointment=0;
            //sumDelays+=waitingAfterAppointment; 
            //System.out.println(sumWaitingTillApp);
            //System.out.println(sumDelays); 
            
            return averageAppointmentWaitingTime = sumWaitingTillApp/electivePatients.length;
            
        }
        
    
    
    private double scanWaitingTimeUrgent(){ //performance measure 2
        double sumScanTime=0;
        double averageScanTime = 0;
        
        Patient[] urgentPatients = new Patient[1000];
        
        for(int i = 0 ; i<patients.length ; i++){
            for(int j = 0 ; j<urgentPatients.length ; j++){
                if(patients[i].getCategory().equals("Urgent"))
                    urgentPatients[j] = patients[i];
                //WEET NIET OF DIT KLOPT
            }
            
        }
        
        for(int i=0;i<urgentPatients.length;i++){
            double waitingForScanTime= urgentPatients[i].getArrivaltime()-urgentPatients[i].getScanTime();;
            sumScanTime+=waitingForScanTime;
        }
        //HEB HIERVOOR "GETSCANTIME" GEBRUIKT, MAAR DAT MOET NOG AANGEPAST WORDEN
        
        return averageScanTime = sumScanTime/urgentPatients.length;
    }

    
    private int overtimeRequired(){ // minder belangrijk --> used to break ties
        
    }
    
    private int scanWaitingTimeElectives(){ // minder belangrijk --> used to break ties
    }
    */
    
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

    public Patient[] getpatients() {
        return patients;
    }

    public void setpatients(Patient[] patients) {
        this.patients = patients;
    }


    public int getNumberOfUrgent() {
        return numberOfUrgent;
    }

    public void setNumberOfUrgent(int numberOfUrgent) {
        this.numberOfUrgent = numberOfUrgent;
    }

    /*public int getNumberOfUrgentInSystem() {
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
    }*/

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

    /*public double getArrivalTimeElective() {
        return arrivalTimeElective;
    }

    public void setArrivalTimeElective(double arrivalTimeElective) {
        this.arrivalTimeElective = arrivalTimeElective;
    }*/

    public double getArrivalTimeUrgent() {
        return arrivalTimeUrgent;
    }

    public void setArrivalTimeUrgent(double arrivalTimeUrgent) {
        this.arrivalTimeUrgent = arrivalTimeUrgent;
    }

    /*public double getDepartureTimeElective() {
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


    public int getNumberOfAlreadyCallersThatDay() {
        return numberOfAlreadyCallersThatDay;
    }

    public void setNumberOfAlreadyCallersThatDay(int numberOfCallersThatDay) {
        this.numberOfAlreadyCallersThatDay = numberOfCallersThatDay;
    }*/


    
}

