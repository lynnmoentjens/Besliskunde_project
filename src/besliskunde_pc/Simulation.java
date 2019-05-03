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
    private double scheduleTimeUrgent;
    //private double arrivalTimeElective;
    private double arrivalTimeUrgent;
    //private double departureTimeElective;
    //private double departureTimeUrgent; 
    LastFilledSlotElective laatsteSlot=new LastFilledSlotElective(-15,1,1);

   
   // private int numberOfAlreadyCallersThatDay;
    private double lastScheduledAppointment;
    private double timeNextUrgent;    
    private double timeArrived;
    ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
   
    public void initialization(){
        
        week=1;
        day=1;
        lengthDay= 540; // we beginnen op maandag
        time=0.0;
        
        numberOfUrgent=0;
        totalNumberOfPatients=0;
        numberOfElectivesForTomorrow=0;
        numberOfPatients=0;
        timeNextUrgent = 0;
        callTime=0;
        scheduleTimeUrgent=0;
        lastScheduledAppointment=0;
        urgentSlotsADay = UrgentSlots.getUrgentSlotsStrategy100();
}
    
    
      public void Simulatie(int amountOfWeeksSimulation){
        
        
        while(week<=amountOfWeeksSimulation){

            //electives intercallingtime per dag berekening
            int amountOfElectivesCallingThatDay= Distributions.Poisson_distribution(28.345);
            double timeThatDayCalling=540;
            double interCallingTime= timeThatDayCalling/amountOfElectivesCallingThatDay;
            System.out.println("AantalElectivesBellendiedag"+amountOfElectivesCallingThatDay);
            System.out.println("TijdTussenBellen"+interCallingTime);
            
            
            //lijst maken met arrivaltimes van de urgent patients
            ArrayList<Double> arrayVanArrivalTimes = new ArrayList<>();
            int amountOfUrgentArrivingThatDay;
            if(day==4||day==6){
                amountOfUrgentArrivingThatDay= Distributions.Poisson_distribution(1.25);
                for(int i=0;i<amountOfUrgentArrivingThatDay;i++){
                    int getal = (int) (Math.random() * (1000));
                    double randomgetal = (double) getal/1000;
                    timeArrived = -(Math.log(1-randomgetal))/amountOfUrgentArrivingThatDay;
                    timeArrived = timeArrived * 60;
                    timeNextUrgent = timeNextUrgent + timeArrived;
                   
                    arrayVanArrivalTimes.add(timeNextUrgent);
                    
                    if (i == amountOfUrgentArrivingThatDay){
                        timeArrived = 0;
                    } 
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
                    int getal = (int) (Math.random() * (1000));
                    double randomgetal = (double) getal/1000;
                    timeArrived = -(Math.log(1-randomgetal))/amountOfUrgentArrivingThatDay;
                    timeArrived = timeArrived*60;
                    timeNextUrgent = timeNextUrgent + timeArrived;
                    
                    arrayVanArrivalTimes.add(timeNextUrgent);
                    
                    if (i == amountOfUrgentArrivingThatDay){
                        timeArrived = 0;
                    }
                }
                Collections.sort(arrayVanArrivalTimes);
                if(amountOfUrgentArrivingThatDay==0){
                    arrivalTimeUrgent=Double.POSITIVE_INFINITY;
                }else{
                    arrivalTimeUrgent=arrayVanArrivalTimes.get(0);
                }
            }
            
            System.out.println(arrayVanArrivalTimes.size());
            //System.out.println("Size van de array " + arrayVanArrivalTimes.size());
            System.out.println("aantalUrgentArrivingthatDay"+amountOfUrgentArrivingThatDay);
            for(int i=0;i<arrayVanArrivalTimes.size();i++){
                System.out.println("getallenUrgent"+arrayVanArrivalTimes.get(i));
            }
            System.out.println("ArrivalTimeEersteUrgent"+arrivalTimeUrgent);
            int numberOfPatientsThatHaveToUrgentArriveOrElectiveCall=amountOfUrgentArrivingThatDay+amountOfElectivesCallingThatDay;

            System.out.println("aantalpatientsdiedag"+numberOfPatientsThatHaveToUrgentArriveOrElectiveCall);
            boolean beidendoubleinfinity=false;
            
            
            
            while((callTime<540||numberOfPatients<=numberOfPatientsThatHaveToUrgentArriveOrElectiveCall-1)&&(beidendoubleinfinity==false)) // maakt niet uit 240 want u urgentArrivals zijn al bepaald en je mag bellen in namiddag op donderdag
            {// lengthDay --> opnieuw bekijken want je kan op halve ook nog bellen --> oplossing zoeken 
                //AppointmentMaken
                System.out.println("Day"+day);
                System.out.println("Week"+week);
                System.out.println("________________________________________");
                System.out.println("calltime"+callTime);
                System.out.println("arrivalTimeUrgent"+arrivalTimeUrgent);
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
                    System.out.println("AppointmentTimeVorige"+laatsteSlot.getTime());
                    nieuwePatient= setPatientDataCall(lengthDay, callTime, day, nieuwePatient); //onderaan 
                    patients[totalNumberOfPatients-1]= nieuwePatient; // je begint op 0
                    System.out.println("Appointmenttime Deze"+nieuwePatient.getAppointmenttime());
                    System.out.println("ArrivalTime"+nieuwePatient.getArrivaltime());
                    System.out.println("CallTime"+nieuwePatient.getCalltime());
                    
                    callTime = time+interCallingTime;  
                    System.out.println("calltime = " + callTime);
                    System.out.println("___________________________________________");
                    if(lastScheduledAppointment<nieuwePatient.getAppointmenttime()){
                        lastScheduledAppointment=nieuwePatient.getAppointmenttime();
                    } 
                    
                }
                else if((numberOfUrgent<arrayVanArrivalTimes.size())&&(arrivalTimeUrgent<callTime)){
                    
                    time= arrivalTimeUrgent; 
                    System.out.println("Urgent patient komt aan");
                    System.out.println("TimeUrgentArrival="+time);
                    numberOfUrgent++;
                    numberOfPatients++;
                    totalNumberOfPatients++;
                    System.out.println("totalUrgentThatday="+numberOfUrgent);
                    System.out.println("totalThatday="+numberOfPatients);
                    System.out.println("total="+totalNumberOfPatients);
                    System.out.println("laatstegescheduled voor methode"+ scheduleTimeUrgent);
                    Patient nieuwePatient = new Patient();
                    nieuwePatient = setPatientDataUrgentArrival(arrivalTimeUrgent, day, week, nieuwePatient);
                    patients[totalNumberOfPatients-1]=nieuwePatient;
                    System.out.println("Appointmenttime Urgent"+nieuwePatient.getAppointmenttime());
                    System.out.println("ArrivalTime Urgent"+nieuwePatient.getArrivaltime());
                    
                    
                    System.out.println("aantal Urgent"+numberOfUrgent);
                    
                    //System.out.println("Mogelijke volgende aankomst"+arrayVanArrivalTimes.get(numberOfUrgent));
                    if(lastScheduledAppointment<nieuwePatient.getAppointmenttime()){
                        lastScheduledAppointment=nieuwePatient.getAppointmenttime();
                    }
                    if(numberOfUrgent<amountOfUrgentArrivingThatDay){
                        System.out.println("number of urgent:" + numberOfUrgent);
                        arrivalTimeUrgent= arrayVanArrivalTimes.get(numberOfUrgent);//arrivalTime van de volgende
                    }
                    System.out.println("ArrivalTime volgende"+arrivalTimeUrgent);
                    System.out.println("___________________________________________");
                    if(numberOfUrgent==arrayVanArrivalTimes.size()){
                        arrivalTimeUrgent=Double.POSITIVE_INFINITY;
                    }
                }

              if(arrivalTimeUrgent==Double.POSITIVE_INFINITY&&callTime==Double.POSITIVE_INFINITY){
                  beidendoubleinfinity=true;
              }
            }
        System.out.println("gaat naar departure");
        double departureTimeVorige=0;
        double randomNumber;
        for(double i=0;i<=lastScheduledAppointment;i=i+15){ ///tijd overlopen
            //System.out.println(totalNumberOfPatients-numberOfPatients);
            System.out.println("laatste gescheduled"+ lastScheduledAppointment);
            System.out.println("totaal patienten"+totalNumberOfPatients);
            System.out.println("totaal patienten die dag"+ numberOfPatients);
            System.out.println("verschil"+(totalNumberOfPatients-numberOfPatients));
            for(int j=(totalNumberOfPatients-numberOfPatients);j<totalNumberOfPatients;j++){ //aanpassing nog nodig voor electives die er morgen in zitten: op zich zou je die wel al de vorige dag kunnen berekenen // maar dan moet het aantal patienten wel elke dag op 0 beginnen en niet al op het aantal geschedulde
                randomNumber= Math.random();
                if(patients[j].getAppointmenttime()==i){
                    double appointmentTimeDeze;
                    appointmentTimeDeze = patients[j].getAppointmenttime();
                    double arrivalTimeDeze;
                    arrivalTimeDeze= patients[j].getArrivaltime();
                    double departureTime;
                    String category= patients[j].getCategory();
                    System.out.println("category"+ patients[j].getCategory());
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
                    if(randomNumber<=0.02){
                        System.out.println("niet komen opdagen");
                        departureTime=appointmentTimeDeze; 
                        patients[j].setDeparturetime(departureTime);
                        departureTimeVorige=appointmentTimeDeze;
                        patients[j].setArrivaltime(appointmentTimeDeze);
                        patients[j].setServiceLength(0);
                    }
                    System.out.println("Number"+(j+1));
                    System.out.println("appointmentTime"+patients[j].getAppointmenttime());
                    System.out.println("ArrivalTime"+patients[j].getArrivaltime());
                    System.out.println("ServiceLength"+patients[j].getServiceLength());
                    System.out.println("DepartureTime"+patients[j].getDeparturetime());
                    System.out.println("________________________________________________");
                }
                
            }
        }
        //nodige parameters op nul zetten
          
        System.out.println("UPDATE");
        System.out.println("MEASURE 1= gemiddelde appointmentWaitingTime Elect"+averageAppointmentWaitingTimeElectives());
        System.out.println("MEASURE 2= urgent gemiddelde scan waiting time"+scanWaitingTimeUrgent());
        updateParametersAtEndOfDay(day, week);      
        }
        
       
    }
    
    private void updateParametersAtEndOfDay(int today, int thisWeek){
        day++;
        time=0;
        if(today==6){
            week++;
            lengthDay=540; 
            day=1;
            urgentSlotsADay = UrgentSlots.getUrgentSlotsStrategy100();
            
        }
        else if(today==3||today==5){
            lengthDay=240; 
            
        }
        else{
            lengthDay=540;
            
        }
        //numberOfElectivesArrived=0;
        //numberOfAlreadyCallersThatDay=0;
        numberOfUrgent=0;
        numberOfPatients=0;
        arrivalTimeUrgent=0;
        if(day==6){
            callTime=Double.POSITIVE_INFINITY;
        }
        else{
            callTime=0;
        }
        scheduleTimeUrgent=0;
        System.out.println("Dag upgedate");
        
        
    }
    
    public Patient setPatientDataCall(double lengthDay,double timeMomentCalling, int day, Patient nieuwePatient){
        double afwijkingArrivalTime = Distributions.Normal_distribution(0, 2.5);
        nieuwePatient.setCalltime(timeMomentCalling);
        nieuwePatient.setCategory("Elective");
 
        nieuwePatient.setDayCall(day);
        System.out.println("beldag"+nieuwePatient.getDayCall());
       
        System.out.println("laastste slot week"+laatsteSlot.getWeek());
        System.out.println("laatsteSlot day"+laatsteSlot.getDay());
        System.out.println("dag"+ day);
        boolean update=false;
        if(week==laatsteSlot.getWeek()){ // als je inzelfde week zit maar al een dag bent opgeschoven
            if(day> laatsteSlot.getDay()){
                laatsteSlot.setDay(day);
                update=true;
            }
        }
        else if(week> laatsteSlot.getWeek()){ // als we al verder zijn dan de week dat we hebben laatst gescheduled
            laatsteSlot.setWeek(week);
            update=true;
            
        }
        System.out.println("upgedate week "+ laatsteSlot.getWeek());
        System.out.println("upgedate dag"+ laatsteSlot.getDay());
        laatsteSlot = TestenInDieDagDieWeek(laatsteSlot, update);
        update=false;

        System.out.println("upgedate"+ update);
        nieuwePatient.setWeekAppointment(laatsteSlot.getWeek());
        System.out.println("appointmnet week patient"+ nieuwePatient.getWeekAppointment());
        nieuwePatient.setDayAppointment(laatsteSlot.getDay());
        System.out.println("appointment dag patient"+ nieuwePatient.getDayAppointment());
        nieuwePatient.setAppointmenttime(laatsteSlot.getTime());
        System.out.println("appointment time patient"+ nieuwePatient.getAppointmenttime());
        nieuwePatient.setArrivaltime(nieuwePatient.getAppointmenttime()+afwijkingArrivalTime);
        System.out.println("arrivalTime "+ nieuwePatient.getArrivaltime());
         
        return nieuwePatient;
        }
        
    public LastFilledSlotElective TestenInDieDagDieWeek(LastFilledSlotElective e, boolean update){
        double tijd= e.getTime();
        if(update==true){ //nieuwe dag of nieuweWeek
            tijd=0;
        }
        else if(update==false){ // geen verandering gebeurt dus de dag van calling en week is zelfde als de appointment
            if( e.getDay()==day&&e.getWeek()==week) // zelfde dag en week
            {
                while(tijd<=time){ 
                    tijd+=15;
                }
            }
        //}
        }
        //KIJKEN OF ER URGENTS SLOTS IN DE WEG ZIJN
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        urgentSlotsADay = UrgentSlots.getUrgentSlotsStrategy100(); //STRATEGIE MANUEEL KIEZEN
        
        int[] urgentSlotsForToday = urgentSlotsADay.get(day);  //OPMERKING: MOET NOG CONTROLEREN OF HET DAY-1 IS OF GEWOON DAY!
        
        for(int i = 0 ; i<urgentSlotsForToday.length ; i++){
            if(tijd==urgentSlotsForToday[i]){
                    tijd+=15;
                }
            }
        //APPOINTMENT OP VOLGENDE VOLLE DAG ZETTEN INDIEN VANDAAG VOLLE DAG IS
        if(e.getDay()!=4&&e.getDay()!=6&&tijd>=540){ 
            e.setDay((e.getDay()+1));
            tijd=0;
                
            }
        else if((e.getDay()==4||e.getDay()==6)&&tijd>240){
            if(day!=6){
                e.setDay((e.getDay()+1));
                tijd=0;
            }
            else{
                e.setDay(1);
                e.setWeek((e.getWeek()+1));
                tijd=0;
            }
        }
        
        //BIJ VOLLE DAGEN GEEN AFSPRAKEN TIJDENS DE MIDDAG
        if(e.getDay()!=4&&e.getDay()!=6&&(tijd==240||tijd==255||tijd==270||tijd==285)){
            tijd=300; // volgende empty slot is na de namiddag
            System.out.println("vorige tijd"+ e.getTime());
               if(tijd<=e.getTime()){
                   tijd+=15;
               } 
            } 
        if(tijd<=e.getTime()){
                   tijd+=15;}
        System.out.println("vorige tijd"+ e.getTime());     
        e.setTime(tijd);
        return e;
    } 
    
    
    public Patient setPatientDataUrgentArrival(double arrivalTime, int today, int thisWeek, Patient nieuwePatient){
        
        nieuwePatient.setCategory("Urgent");
        nieuwePatient.setArrivaltime(arrivalTime);
        nieuwePatient.setDayCall(today);
        nieuwePatient.setWeekCall(thisWeek);
        nieuwePatient.setDayAppointment(today);
        nieuwePatient.setWeekAppointment(thisWeek);
        
        //KIEZEN WELKE STRATEGIE JE WILT GEBRUIKEN --> MANUEEL AANPASSEN
        //STRATEGIE MANUEEL GEKOZEN
        
        System.out.println("laatste appointment"+scheduleTimeUrgent);
        double vorigeTime= scheduleTimeUrgent;
        int[] urgentSlotsForToday = urgentSlotsADay.get(today);
        System.out.println("lengte urgentslosts"+urgentSlotsForToday.length);
        for(int i=0;i<urgentSlotsForToday.length;i++){
            System.out.println(urgentSlotsForToday[i]);
        }
        
        boolean change=false;
        boolean changing=false;
        System.out.println(urgentSlotsForToday[0]);
        while(scheduleTimeUrgent== vorigeTime&&urgentSlotsForToday[urgentSlotsForToday.length-1]!=0){
            System.out.println("change "+change);
            System.out.println("aantal slots"+ urgentSlotsForToday.length);
            for(int i=0;i<urgentSlotsForToday.length;i++){
                System.out.println("Volgende tijd"+urgentSlotsForToday[i]);
                System.out.println("aankomst"+ arrivalTime);
                System.out.println("verandering "+change);
                if((urgentSlotsForToday[i]!=0)){
                    System.out.println("verschillend van nul");
                    if((urgentSlotsForToday[i]>=arrivalTime&&changing==false)){
                        scheduleTimeUrgent=urgentSlotsForToday[i];
                        System.out.println("nieuwe schedule "+scheduleTimeUrgent);
                        urgentSlotsForToday[i]=0;
                        System.out.println("de slot op 0 zetten"+ urgentSlotsForToday[i]);
                        System.out.println("tijd" + scheduleTimeUrgent);                        
                        changing=true;
                        System.out.println("veranderd?"+changing);
                    }
                    else if(urgentSlotsForToday[i]<arrivalTime){
                        urgentSlotsForToday[i]=0;
                    }
                    
            } 
        }} 

        System.out.println(scheduleTimeUrgent);
        if(changing==false){
            if(scheduleTimeUrgent<540){
                scheduleTimeUrgent=540;
            }
            else{
                scheduleTimeUrgent+=15;
            }
        }
        
        nieuwePatient.setAppointmenttime(scheduleTimeUrgent);        
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
    }
    
    
    private double averageAppointmentWaitingTimeElectives(){ // performance measure 1 --> average appointment waiting time elective
        
        double sumWaitingTillApp=0;
        double averageAppointmentWaitingTime = 0;
        int numberOfElectives=0;
        //double sumDelays=0;
        System.out.println("Som tijd tot appointment"+sumWaitingTillApp);
        //Aanpassing nodig --> moet electives alleen zijn 
        for(int i=0;i<totalNumberOfPatients;i++){
            if(patients[i].getCategory().equals("Elective")){
                System.out.println("--------------------------------------");
                System.out.println("Patient "+(i+1));
                System.out.println("Appointmenttime"+patients[i].getAppointmenttime());
                System.out.println("CallTime"+patients[i].getCalltime());
                double waitingTillAppointment;
                int amountOfDaysNext=0;
                amountOfDaysNext=patients[i].getDayAppointment() - patients[i].getDayCall();
                System.out.println("day = " + day);
                System.out.println("amountOfDaysNext = " + amountOfDaysNext);
                int dag=patients[i].getDayAppointment();
                System.out.println("dag = "+ dag);
                if(amountOfDaysNext==0)
                {
                    System.out.println("if amount of days is zero");
                    waitingTillAppointment= patients[i].getAppointmenttime()- patients[i].getCalltime();
                }
                else{
                    System.out.println("if amount of days next is not zero");
                    waitingTillAppointment=patients[i].getAppointmenttime(); // appointment time
                    dag--; 
                    
                    while(amountOfDaysNext!=0){
                        System.out.println("zit je hier vast??");
                        if(dag==4||dag==6){
                            waitingTillAppointment+= 1200;
                            amountOfDaysNext--;
                        }
                        else{
                            waitingTillAppointment+= 900;
                            amountOfDaysNext--;
                        }
                    }
                    if(dag==day){
                        if(day==4||day==6)
                        {
                            waitingTillAppointment+=(240-patients[i].getCalltime());
                        }
                        else{
                            waitingTillAppointment+=(540-patients[i].getCalltime());
                        }
                    }
                }
                
                System.out.println("Wachttijd deze patient"+waitingTillAppointment);
                sumWaitingTillApp+=waitingTillAppointment;
                System.out.println("Som tijd tot appointment"+sumWaitingTillApp);
                numberOfElectives++;
            }
            
        }
            
            System.out.println("Som tijd tot appointment"+sumWaitingTillApp);
            averageAppointmentWaitingTime = sumWaitingTillApp/numberOfElectives;
            System.out.println("gemiddelde wachttijd"+averageAppointmentWaitingTime);
            
            return averageAppointmentWaitingTime;
            
        }
    private double averageScanWaitingTime(){
        double averageWaitingTime=0;
        double waitingTime=0;
        int amountOfElectives=0;
        for(int i=0;i<totalNumberOfPatients;i++){
            if(patients[i].getCategory().equals("Elective")){
                waitingTime+=(patients[i].getDeparturetime()-patients[i].getServiceLength())-patients[i].getArrivaltime();
                amountOfElectives++;     
            }
        }
        averageWaitingTime=waitingTime/amountOfElectives;
        return averageWaitingTime;
    }
    
    private double scanWaitingTimeUrgent(){ //performance measure 2
        double sumScanTime=0;
        double averageScanTime = 0;
        int aantalUrgent=0;
              
        for(int i=0;i<totalNumberOfPatients;i++){
            if(patients[i].getCategory().equals("Urgent")){
                double waitingForScanTime= patients[i].getDeparturetime()-patients[i].getServiceLength()-patients[i].getArrivaltime();
                sumScanTime+=waitingForScanTime;
                System.out.println("number"+(i+1));
                System.out.println("wait for scan urgent"+waitingForScanTime);
                System.out.println("som van wachttijden"+ sumScanTime);
                aantalUrgent++;
            }
            
        }
        averageScanTime = sumScanTime/aantalUrgent;
        System.out.println(averageScanTime);
        return averageScanTime ;
    }

    
    private int overtimeRequired(){ // minder belangrijk --> used to break ties
        return 2;
    }
    
    private int scanWaitingTimeElectives(){ // minder belangrijk --> used to break ties
        return 2;
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

    public double getCallTime() {
        return callTime;
    }

    public void setCallTime(double callTime) {
        this.callTime = callTime;
    }


    public double getScheduleTimeUrgent() {
        return scheduleTimeUrgent;
    }

    public void setScheduleTimeUrgent(double scheduleTimeUrgent) {
        this.scheduleTimeUrgent = scheduleTimeUrgent;
    }

    public double getArrivalTimeUrgent() {
        return arrivalTimeUrgent;
    }

    public void setArrivalTimeUrgent(double arrivalTimeUrgent) {
        this.arrivalTimeUrgent = arrivalTimeUrgent;
    }    
}