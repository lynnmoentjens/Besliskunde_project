/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besliskunde_pc;

import static java.lang.System.exit;
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
    Patient[] patients = new Patient[1000000000];
    
    private int numberOfUrgent;
    //private int numberOfUrgentInSystem; moet worden verminderd bij departure
    private int totalNumberOfPatients;
    //private int numberOfElectivesArrived;
   // private int numberOfElectivesInSystem; // moet worden verminderd bij departure
    private int numberOfElectivesForTomorrow;
    private int numberOfPatients;
    private int numberOfElectivesHaveCalled;
    private int totalNumberOfElectives;
    private int totalNumberOfUrgents;
   
    
    private double callTime;
    private double scheduleTimeUrgent;
    //private double arrivalTimeElective;
    private double arrivalTimeUrgent;
    //private double departureTimeElective;
    //private double departureTimeUrgent; 
    LastFilledSlotElectiveRule1 laatsteSlot=new LastFilledSlotElectiveRule1(-15,1,1);

   
   // private int numberOfAlreadyCallersThatDay;
    private double lastScheduledAppointment;
    private double timeNextUrgent;    
    private double timeArrived;
    ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
    ArrayList <Double> interArrival = new ArrayList();
    public ArrayList<Double> WTUrgents = new ArrayList<>();
    public ArrayList<Double> WTElectives = new ArrayList<>();
    
    
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
        totalNumberOfElectives = 0;
        totalNumberOfUrgents = 0;
        lastScheduledAppointment=0;
        urgentSlotsADay = UrgentSlots.testSignificanceSlots14();
        numberOfElectivesHaveCalled=0;
        
}
    
    
      public void Simulatie(int amountOfWeeksSimulation){
        
        
        while(week<=amountOfWeeksSimulation){
             // calltimes van electives genereren:
             //System.out.println("DAG:"+day);
             //System.out.println("WEEK: "+week );
            if(day!=6){
               double interarrival = 0;
                double tijd=0;
        
                //System.out.println("BEGIN DAG");
                for(int i = 0; i < 40; i++){
                    interarrival = (Distributions.Exponential_distribution(28.345))*540;
                    //System.out.println("interarrival "+ interarrival);
                    tijd+=interarrival;
                    if((tijd) < 540){
                        interArrival.add(interarrival); 
                        //System.out.println("tijd call patient "+ (i+1)+": "+tijd);
                    }            
                }
                //System.out.println("aantal electives " + interArrival.size());

                //eerste callTime
                callTime+=interArrival.get(0); 
            }
            else if(day==6){
                callTime= Double.POSITIVE_INFINITY;
            }
            
            //electives intercallingtime per dag berekening
            //int amountOfElectivesCallingThatDay= Distributions.Poisson_distribution(28.345);
            /*double timeThatDayCalling=540;
            double interCallingTime= timeThatDayCalling/callTimes.size(); //amountOfElectivesCallingThatDay;
            System.out.println("AantalElectivesBellendiedag"+callTimes.size()); //amountOfElectivesCallingThatDay);
            System.out.println("TijdTussenBellen"+interCallingTime);*/
            
             //lijst maken met arrivaltimes van de urgent patients
            ArrayList<Double> arrayVanArrivalTimes = new ArrayList<>();
            int amountOfUrgentArrivingThatDay;
            double interarrival2 = 0;
            double tijd2 =0;
           
            if(day==4||day==6){
               
                        for(int i=0; i < 10; i++){
                 
                        interarrival2 = (Distributions.Exponential_distribution(1.25))*540;
                        tijd2 = tijd2 + interarrival2;
                             if((tijd2) < 240){
                             arrayVanArrivalTimes.add(tijd2);
                             //System.out.println("urgenttime "+  tijd2);
                        }
                }
                
                amountOfUrgentArrivingThatDay = arrayVanArrivalTimes.size();
                //System.out.println("Totaal urgents: " + amountOfUrgentArrivingThatDay);
                
                
                Collections.sort(arrayVanArrivalTimes);
                if(amountOfUrgentArrivingThatDay==0){ // de eerste
                    arrivalTimeUrgent=Double.POSITIVE_INFINITY;
                }
                else{
                    arrivalTimeUrgent=arrayVanArrivalTimes.get(0);
                }
            }
            
            else{
              
                    for(int i=0; i < 10; i++){
                    interarrival2 = (Distributions.Exponential_distribution(2.5))*540;
                    tijd2 = tijd2 + interarrival2;
                    
                        if (tijd2 < 540){
                        arrayVanArrivalTimes.add(tijd2);
                        //System.out.println("urgenttime :" + tijd2);
                    }
                    
                }
                    
                amountOfUrgentArrivingThatDay = arrayVanArrivalTimes.size();
                //System.out.println("Totaal urgents : "+ amountOfUrgentArrivingThatDay);
                
                
                Collections.sort(arrayVanArrivalTimes);
                if(amountOfUrgentArrivingThatDay==0){
                    arrivalTimeUrgent=Double.POSITIVE_INFINITY;
                }else{
                    arrivalTimeUrgent=arrayVanArrivalTimes.get(0);
                }
            }

            //System.out.println(arrayVanArrivalTimes.size());
            //System.out.println("Size van de array " + arrayVanArrivalTimes.size());
            //System.out.println("aantalUrgentArrivingthatDay"+amountOfUrgentArrivingThatDay);
            /*for(int i=0;i<arrayVanArrivalTimes.size();i++){
                System.out.println("getallenUrgent"+arrayVanArrivalTimes.get(i));
            }*/
            //System.out.println("ArrivalTimeEersteUrgent"+arrivalTimeUrgent);
            int numberOfPatientsThatHaveToUrgentArriveOrElectiveCall=amountOfUrgentArrivingThatDay+interArrival.size();//amountOfElectivesCallingThatDay;

            //System.out.println("aantalpatientsdiedag"+numberOfPatientsThatHaveToUrgentArriveOrElectiveCall);
            boolean beidendoubleinfinity=false;
            
            //System.out.println("eerste CallTime die dag "+ callTime);
            
            int a = 0;
            while((callTime<540||numberOfPatients<=numberOfPatientsThatHaveToUrgentArriveOrElectiveCall-1)&&(beidendoubleinfinity==false)) // maakt niet uit 240 want u urgentArrivals zijn al bepaald en je mag bellen in namiddag op donderdag
            {// lengthDay --> opnieuw bekijken want je kan op halve ook nog bellen --> oplossing zoeken 
                //AppointmentMaken
                //System.out.println("Day"+day);
                //System.out.println("Week"+week);
                //System.out.println("________________________________________");
                //System.out.println("Volgende calltime patient elective "+callTime);
                //System.out.println("Volgende arrivalTime Urgent"+arrivalTimeUrgent);
                numberOfElectivesHaveCalled++;
                if((day!=6)&&(callTime<arrivalTimeUrgent)){
                    //System.out.println("Dag in de week"+day);
                    time=callTime; 
                    //System.out.println("ELECTIVE CALLS");
                    //System.out.println( "time = " + time);
                    totalNumberOfPatients++; //over het hele programma/alle weken
                    numberOfPatients++; // per dag 
                    totalNumberOfElectives++;
                    //System.out.println("numberof patients al aangekomen/gebeld die dag= " + numberOfPatients);
                    //System.out.println("total aantal patienten over alle weken " + totalNumberOfPatients);
                    Patient nieuwePatient=new Patient();
                    //numberOfAlreadyCallersThatDay++; //bekijken of dit nodig is
                    //System.out.println("AppointmentTimeVorige patient"+laatsteSlot.getTime());
                    //System.out.println("Appointment Week vorige patient "+laatsteSlot.getWeek());
                    //System.out.println("Appointment Day vorige patient"+laatsteSlot.getDay());
                    nieuwePatient.setWeekCall(week);
                    nieuwePatient.setDayCall(day);
                    //System.out.println("DE DAG!!!!!!"+ nieuwePatient.getDayCall());
                    nieuwePatient.setCalltime(callTime);
                    nieuwePatient.setCategory("Elective");
                    nieuwePatient= setPatientDataCall(lengthDay, callTime, day, nieuwePatient); //onderaan 
                    //System.out.println("AppointmentTime Deze patient"+laatsteSlot.getTime());
                    //System.out.println("Appointment Week Deze patient "+laatsteSlot.getWeek());
                    //System.out.println("Appointment Day Deze patient"+laatsteSlot.getDay());
                    patients[totalNumberOfPatients-1]= nieuwePatient; // je begint op 0
                    //System.out.println("appointment day"+ nieuwePatient.getDayAppointment());
                    //System.out.println("Appointmenttime Deze"+nieuwePatient.getAppointmenttime());
                    //System.out.println("ArrivalTime"+nieuwePatient.getArrivaltime());
                    //System.out.println("CallTime deze patient"+nieuwePatient.getCalltime());
                    //System.out.println(" CallWeek deze patient"+nieuwePatient.getWeekCall());
                    //System.out.println("CallDay deze patient"+nieuwePatient.getDayCall());
                    //System.out.println("aantalAlgebeld"+numberOfElectivesHaveCalled);
                    if(numberOfElectivesHaveCalled<interArrival.size()){
                        callTime += interArrival.get(numberOfElectivesHaveCalled);
                    }
                    else{
                        callTime= Double.POSITIVE_INFINITY;
                    }
                    //System.out.println("calltime next= " +callTime );
                    //System.out.println("___________________________________________");
                    if(lastScheduledAppointment<nieuwePatient.getAppointmenttime()){
                        lastScheduledAppointment=nieuwePatient.getAppointmenttime();
                    } 
                    a++;
                }
                else if((numberOfUrgent<arrayVanArrivalTimes.size())&&(arrivalTimeUrgent<callTime)){
                    
                    time= arrivalTimeUrgent; 
                    //System.out.println("Urgent patient komt aan");
                    //System.out.println("TimeUrgentArrival="+time);
                    numberOfUrgent++;
                    numberOfPatients++;
                    totalNumberOfPatients++;
                    totalNumberOfUrgents++;
                    //System.out.println("totalUrgentThatday="+numberOfUrgent);
                    //System.out.println("totalThatday="+numberOfPatients);
                    //System.out.println("total="+totalNumberOfPatients);
                    //System.out.println("laatstegescheduled voor methode"+ scheduleTimeUrgent);
                    Patient nieuwePatient = new Patient();
                    
                    nieuwePatient = setPatientDataUrgentArrival(arrivalTimeUrgent, day, week, nieuwePatient);
                    patients[totalNumberOfPatients-1]=nieuwePatient;
                    //System.out.println("Appointmenttime Urgent"+nieuwePatient.getAppointmenttime());
                    //System.out.println("ArrivalTime Urgent"+nieuwePatient.getArrivaltime());
                    
                    
                    //System.out.println("aantal Urgent"+numberOfUrgent);
                    
                    //System.out.println("Mogelijke volgende aankomst"+arrayVanArrivalTimes.get(numberOfUrgent));
                    if(lastScheduledAppointment<nieuwePatient.getAppointmenttime()){
                        lastScheduledAppointment=nieuwePatient.getAppointmenttime();
                    }
                    if(numberOfUrgent<amountOfUrgentArrivingThatDay){
                        //System.out.println("number of urgent:" + numberOfUrgent);
                        arrivalTimeUrgent= arrayVanArrivalTimes.get(numberOfUrgent);//arrivalTime van de volgende
                    }
                    //System.out.println("ArrivalTime volgende"+arrivalTimeUrgent);
                    //System.out.println("___________________________________________");
                    if(numberOfUrgent==arrayVanArrivalTimes.size()){
                        arrivalTimeUrgent=Double.POSITIVE_INFINITY;
                    }
                }

              if(arrivalTimeUrgent==Double.POSITIVE_INFINITY&&callTime==Double.POSITIVE_INFINITY){
                  beidendoubleinfinity=true;
              }
            }
        //System.out.println("____------_____");
        //System.out.println("DEPARTURES");
        double departureTimeVorige=0;
        double randomNumber;
        for(double i=0;i<=lastScheduledAppointment;i=i+15){ ///tijd overlopen
            //System.out.println(totalNumberOfPatients-numberOfPatients);
            //System.out.println("laatste gescheduled"+ lastScheduledAppointment);
            //System.out.println("totaal patienten"+totalNumberOfPatients);
            //System.out.println("totaal patienten die dag"+ numberOfPatients);
            //System.out.println("verschil"+(totalNumberOfPatients-numberOfPatients));
            for(int j=(totalNumberOfPatients-numberOfPatients);j<totalNumberOfPatients;j++){ //aanpassing nog nodig voor electives die er morgen in zitten: op zich zou je die wel al de vorige dag kunnen berekenen // maar dan moet het aantal patienten wel elke dag op 0 beginnen en niet al op het aantal geschedulde
                randomNumber= Math.random();
                if(patients[j].getAppointmenttime()==i){
                    if((patients[j].getWeekAppointment()==week)&&(patients[j].getDayAppointment()==day)){
                    double appointmentTimeDeze;
                    appointmentTimeDeze = patients[j].getAppointmenttime();
                    double arrivalTimeDeze;
                    arrivalTimeDeze= patients[j].getArrivaltime();
                    double departureTime;
                    String category= patients[j].getCategory();
                    //System.out.println("category"+ patients[j].getCategory());
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
                        //System.out.println("niet komen opdagen");
                        departureTime=appointmentTimeDeze; 
                        patients[j].setDeparturetime(departureTime);
                        departureTimeVorige=appointmentTimeDeze;
                        patients[j].setArrivaltime(appointmentTimeDeze);
                        patients[j].setServiceLength(0);
                    }
                    /*System.out.println("Number"+(j+1));
                    System.out.println("appointmentTime"+patients[j].getAppointmenttime());
                    System.out.println("ArrivalTime"+patients[j].getArrivaltime());
                    System.out.println("ServiceLength"+patients[j].getServiceLength());
                    System.out.println("DepartureTime"+patients[j].getDeparturetime());
                    System.out.println("________________________________________________");*/
                }
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
        timeArrived=0;
        if(day>=7){
            week++;
            lengthDay=540; 
            day=1;
            urgentSlotsADay = UrgentSlots.testSignificanceSlots14();
            callTime=0;
            
            
            
            
        }
        else if(day==4||day==6){
            lengthDay=240; 
            callTime=0;
            
        }
        else{
            lengthDay=540;
            callTime=0;
            
        }
        interArrival.clear();
        //numberOfElectivesArrived=0;
        //numberOfAlreadyCallersThatDay=0;
        numberOfUrgent=0;
        numberOfPatients=0;
        arrivalTimeUrgent=0;
        numberOfElectivesHaveCalled=0;
        
        scheduleTimeUrgent=0;
        //System.out.println("De Dag is nu "+day);
        //System.out.println("DAG UPGEDATE");
    }
    
    public Patient setPatientDataCall(double lengthDay,double timeMomentCalling, int day, Patient nieuwePatient){
        double afwijkingArrivalTime = Distributions.Normal_distribution(0, 2.5);
 
        nieuwePatient.setDayCall(day);
        //System.out.println("DAG DAT PATIENT BELT"+nieuwePatient.getDayCall());
       
        //System.out.println("WEEK VAN VORIGE SLOT"+laatsteSlot.getWeek());
        //System.out.println("DAG VAN VORIGE SLOT"+laatsteSlot.getDay());
        //System.out.println("UUR VAN DE VORIGE SLOT"+laatsteSlot.getTime());
        //System.out.println("DEZE DAG"+ day);
        
        //System.out.println("TIJD DAT ER GEBELD WORDT: zou moeten gelijk zijn aan de vorige"+nieuwePatient.getCalltime());
         //System.out.println("DAG DAT ER GEBELD WORDT"+nieuwePatient.getDayCall());
          //System.out.println("WEEK DAT ER GEBELD WORDT"+nieuwePatient.getWeekCall());
        boolean update=false;
        if(week==laatsteSlot.getWeek()){ // als je inzelfde week zit maar al een dag bent opgeschoven
            //System.out.println("DE LAATSTE GEVULDE SLOT LIGT IN DEZE WEEK");
            if(day>laatsteSlot.getDay()){
                //System.out.println("DE HUIDIGE DAG IS LATER DAN DIE VAN DE LAATSTE APPOINTMENT ");
                laatsteSlot.setDay(day);
                update=true;
                laatsteSlot.setTime(0);
                
            }
        }
        else if(week>= laatsteSlot.getWeek()){ // als we al verder zijn dan de week dat we hebben laatst gescheduled
            laatsteSlot.setWeek(week);
            laatsteSlot.setDay(1);
            laatsteSlot.setTime(0);
            update=true;
            //System.out.println("DE HUIDIGE WEEK IS LATER DAN DE VORIGE GESCHEDULDE SLOT");
            
        }
        if(day>=7){
            laatsteSlot.setDay(1);
            laatsteSlot.setWeek((laatsteSlot.getWeek()+1));
        }
        
        double tijd=0;
        if(update!=true){
            laatsteSlot.setTime((laatsteSlot.getTime()+15));
            //System.out.println("De appointmentTime is nu: "+ laatsteSlot.getTime());
        }

        if(laatsteSlot.getDay()==day&&laatsteSlot.getWeek()==week) // zelfde dag en week
            while(laatsteSlot.getTime()<=time){ 
                    laatsteSlot.setTime((laatsteSlot.getTime()+15));
            }
         //System.out.println("De appointmentTime is nu: "+ laatsteSlot.getTime());

        ArrayList<int[]> urgenteSlots= UrgentSlots.testSignificanceSlots14();
        int[] urgentSlotsForToday = urgenteSlots.get(laatsteSlot.getDay());  //OPMERKING: MOET NOG CONTROLEREN OF HET DAY-1 IS OF GEWOON DAY!
        
        
        for(int i = 0 ; i<urgentSlotsForToday.length ; i++){
        //System.out.println(" slot urgent "+(i+1)+": "+urgentSlotsForToday[i]);
            if(laatsteSlot.getTime()==urgentSlotsForToday[i]){
                //System.out.println("Gelijk");
                laatsteSlot.setTime((laatsteSlot.getTime()+15));
                }
             //System.out.println("De appointmentTime is nu: "+ laatsteSlot.getTime());
        }
         //System.out.println("De appointmentTime is nu: "+ laatsteSlot.getTime());
         
        //APPOINTMENT OP VOLGENDE VOLLE DAG ZETTEN INDIEN VANDAAG VOLLE DAG IS
        if(laatsteSlot.getDay()!=4&&laatsteSlot.getDay()!=6&&laatsteSlot.getTime()>=540){ 
            //System.out.println("de dag is op zijn einde ma- di - woe- vrij");
            laatsteSlot.setDay((laatsteSlot.getDay()+1));
            if(laatsteSlot.getDay()>=7){
                laatsteSlot.setDay(1);
                laatsteSlot.setWeek((laatsteSlot.getWeek()+1));
                
            }
            laatsteSlot.setTime(0);
             //System.out.println("De appointmentTime is nu: "+ laatsteSlot.getTime());
                
            }
        else if((laatsteSlot.getDay()==4||laatsteSlot.getDay()==6)&&laatsteSlot.getTime()>=240){
            //System.out.println("De dag is op zijn einde op dag 6 of 4 ");
            laatsteSlot.setDay((laatsteSlot.getDay()+1));
            if(laatsteSlot.getDay()>=7){
                laatsteSlot.setWeek((laatsteSlot.getWeek()+1));
                laatsteSlot.setDay(1);
            }
            laatsteSlot.setTime(0);
             //System.out.println("De appointmentTime is nu: "+ laatsteSlot.getTime());
             
        }
        
        //BIJ VOLLE DAGEN GEEN AFSPRAKEN TIJDENS DE MIDDAG
        if(laatsteSlot.getDay()!=4&&laatsteSlot.getDay()!=6&&(laatsteSlot.getTime()==240||laatsteSlot.getTime()==255||laatsteSlot.getTime()==270||laatsteSlot.getTime()==285)){
            laatsteSlot.setTime(300); // volgende empty slot is na de namiddag
            //System.out.println("De appointmentTime is nu: "+ laatsteSlot.getTime());
        } 
        

        //System.out.println("upgedate"+ update);
        nieuwePatient.setWeekAppointment(laatsteSlot.getWeek());
        //System.out.println("appointmnet week patient"+ nieuwePatient.getWeekAppointment());
        nieuwePatient.setDayAppointment(laatsteSlot.getDay());
        //System.out.println("appointment dag patient"+ nieuwePatient.getDayAppointment());
        nieuwePatient.setAppointmenttime(laatsteSlot.getTime());
        //System.out.println("appointment time patient"+ nieuwePatient.getAppointmenttime());
        nieuwePatient.setArrivaltime(nieuwePatient.getAppointmenttime()+afwijkingArrivalTime);
        //System.out.println("arrivalTime "+ nieuwePatient.getArrivaltime());
         
        return nieuwePatient;
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
        
        //System.out.println("laatste appointment"+scheduleTimeUrgent);
        double vorigeTime= scheduleTimeUrgent;
        int[] urgentSlotsForToday = urgentSlotsADay.get(today);
        //System.out.println("lengte urgentslosts"+urgentSlotsForToday.length);
        for(int i=0;i<urgentSlotsForToday.length;i++){
            //System.out.println(urgentSlotsForToday[i]);
        }
        
        boolean change=false;
        boolean changing=false;
        //System.out.println(urgentSlotsForToday[0]);
        while(scheduleTimeUrgent== vorigeTime&&urgentSlotsForToday[urgentSlotsForToday.length-1]!=0){
            //System.out.println("change "+change);
            //System.out.println("aantal slots"+ urgentSlotsForToday.length);
            for(int i=0;i<urgentSlotsForToday.length;i++){
                //System.out.println("Volgende tijd"+urgentSlotsForToday[i]);
                //System.out.println("aankomst"+ arrivalTime);
                //System.out.println("verandering "+change);
                if((urgentSlotsForToday[i]!=0)){
                    //System.out.println("verschillend van nul");
                    if((urgentSlotsForToday[i]>=arrivalTime&&changing==false)){
                        scheduleTimeUrgent=urgentSlotsForToday[i];
                        //System.out.println("nieuwe schedule "+scheduleTimeUrgent);
                        urgentSlotsForToday[i]=0;
                        //System.out.println("de slot op 0 zetten"+ urgentSlotsForToday[i]);
                        //System.out.println("tijd" + scheduleTimeUrgent);                        
                        changing=true;
                        //System.out.println("veranderd?"+changing);
                    }
                    else if(urgentSlotsForToday[i]<arrivalTime){
                        urgentSlotsForToday[i]=0;
                    }
                    
            } 
        }} 

        //System.out.println(scheduleTimeUrgent);
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
        //System.out.println("Som tijd tot appointment"+sumWaitingTillApp);
        // alleen die waarvan de appointment time en day, week vandaag zijn 
        //Aanpassing nodig --> moet electives alleen zijn 
        for(int i=0;i<totalNumberOfPatients;i++){
            if(patients[i].getCategory().equals("Elective")){
                /*System.out.println("--------------------------------------");
                System.out.println("Patient "+(i+1));
                System.out.println("Appointmenttime"+patients[i].getAppointmenttime());
                System.out.println("appointmentWeek "+patients[i].getWeekAppointment());
                System.out.println("Appointment day"+ patients[i].getDayAppointment());
                System.out.println("CallTime"+patients[i].getCalltime());
                System.out.println("CallWeek "+patients[i].getWeekCall());
                System.out.println("Callday"+ patients[i].getDayCall());*/
                double waitingTillAppointment=0;
                numberOfElectives++;
                    int aantalWekenTussen= patients[i].getWeekCall()- patients[i].getWeekAppointment();
                    for(int j=0; j<aantalWekenTussen-1; j++){
                        waitingTillAppointment+=10080; // week er bij 
                       /* System.out.println("een week tussen");
                        System.out.println("Huidige wachttijd"+ waitingTillAppointment);*/
                    }
                    if(patients[i].getWeekCall()!=patients[i].getWeekAppointment())//verschillende week
                    {
                        int aantalDagenTussenVerschillendeWeek = patients[i].getDayAppointment()+(6-patients[i].getDayCall());
                        for(int k=0;k<aantalDagenTussenVerschillendeWeek-1;k++){// volle dagen er tussen 
                            waitingTillAppointment+=1440;
                            /*System.out.println("een dag tussen");
                            System.out.println("Huidige wachttijd"+ waitingTillAppointment);*/
                        }
                        waitingTillAppointment+=1440; //zondag
                        
                        waitingTillAppointment+= 480+patients[i].getAppointmenttime(); // dag van appointment: smorgens+apptime
                        
                        if(patients[i].getDayCall()==6||patients[i].getDayCall()==4){
                            waitingTillAppointment+= (720+(240-patients[i].getCalltime())); // dag van de Call: avond+resterende tijd na call
                            /*System.out.println("appointmentday tijd wachten halve dag");
                            System.out.println("Huidige wachttijd"+ waitingTillAppointment);*/
                        }
                        else{
                            waitingTillAppointment+= (420+(540-patients[i].getCalltime()));
                            /*System.out.println("appointmentday tijd wachten volle dag");
                            System.out.println("Huidige wachttijd"+ waitingTillAppointment);*/
                        }
                        
                    }
                    else { //zelfde week
                        if(patients[i].getDayCall()==patients[i].getDayAppointment()){// zelfde dag
                            //System.out.println("de dag bellen is gelijk aan de dag appointment");
                            waitingTillAppointment= patients[i].getAppointmenttime()- patients[i].getCalltime();
                            //System.out.println("Huidige wachttijd"+ waitingTillAppointment);
                        }
                        else{
                            int aantalDagenTussen= patients[i].getDayAppointment()-patients[i].getDayCall();
                            for(int l=0;l<aantalDagenTussen-1;l++){
                                waitingTillAppointment+=1440;
                                /*System.out.println("een dag tussen");
                                System.out.println("Huidige wachttijd"+ waitingTillAppointment);*/
                            }
                            waitingTillAppointment+= 480+patients[i].getAppointmenttime(); // dag van appointment: smorgens+apptime
                        
                            if(patients[i].getDayCall()==6||patients[i].getDayCall()==4){
                                //System.out.println("dag van call berkeneing volle dag ");
                                waitingTillAppointment+= (720+(240-patients[i].getCalltime())); // dag van de Call: avond+resterende tijd na call
                                //System.out.println("Huidige wachttijd"+ waitingTillAppointment);
                            }
                            else{
                                waitingTillAppointment+= (420+(540-patients[i].getCalltime()));
                                /*System.out.println("dag van call berkening halve dag");
                                System.out.println("wachttijd huidig"+waitingTillAppointment);*/
                            }
                           //System.out.println("wachttijd huidig"+waitingTillAppointment); 
                        }
                        //System.out.println("Som tijd tot appointment: nog niet uit for en 2 keer if"+waitingTillAppointment);
                    }
                    
                    
                  sumWaitingTillApp+=waitingTillAppointment;  
                  //1 array met waiting times
                  if(week == 200 && day == 6){
                  WTElectives.add(waitingTillAppointment);
                  }
                  patients[i].setWaitingTimeElective(waitingTillAppointment);
                  //System.out.println("Som tijd tot appointment: nog niet uit for en if"+sumWaitingTillApp);  
                }
            //System.out.println("Som tijd tot appointment: nog niet uit for"+sumWaitingTillApp);
            }
            
        // }
            
            /*System.out.println("Som tijd tot appointment"+sumWaitingTillApp);
            System.out.println("aantal Electives berkeneing"+numberOfElectives);*/
            averageAppointmentWaitingTime = sumWaitingTillApp/numberOfElectives;
            //System.out.println("gemiddelde wachttijd"+averageAppointmentWaitingTime);
            
            return averageAppointmentWaitingTime;
            
        }
    
    public void printWaitingTimesUrgent(){
        for(int i=0; i<totalNumberOfPatients;i++){
            if(patients[i].getCategory().equals("Urgent")){
                double waitingTime=(patients[i].getAppointmenttime()-patients[i].getArrivaltime());
                System.out.println("Patient "+(i+1)+" "+waitingTime);
            }
            
        }
    }
     public ArrayList<Double> runningAverageAppointmentWaitingTimeElectives(){ // performance measure 1 --> average appointment waiting time elective
        
        double sumWaitingTillApp=0;
        double averageAppointmentWaitingTime = 0;
        int numberOfElectives=0;
        ArrayList<Double> appointmentWaitingTimeElectives = new ArrayList<>();
        //double sumDelays=0;
        //System.out.println("Som tijd tot appointment"+sumWaitingTillApp);
        //Aanpassing nodig --> moet electives alleen zijn 
        for(int i=0;i<totalNumberOfPatients;i++){
            if(patients[i].getCategory().equals("Elective")){
                /*System.out.println("--------------------------------------");
                System.out.println("Patient "+(i+1));
                System.out.println("Appointmenttime"+patients[i].getAppointmenttime());
                System.out.println("appointmentWeek "+patients[i].getWeekAppointment());
                System.out.println("Appointment day"+ patients[i].getDayAppointment());
                System.out.println("CallTime"+patients[i].getCalltime());
                System.out.println("CallWeek "+patients[i].getWeekCall());
                System.out.println("Callday"+ patients[i].getDayCall());*/
                double waitingTillAppointment=0;
                numberOfElectives++;
                    int aantalWekenTussen= patients[i].getWeekCall()- patients[i].getWeekAppointment();
                    for(int j=0; j<aantalWekenTussen-1; j++){
                        waitingTillAppointment+=10080; // week er bij 
                       /* System.out.println("een week tussen");
                        System.out.println("Huidige wachttijd"+ waitingTillAppointment);*/
                    }
                    if(patients[i].getWeekCall()!=patients[i].getWeekAppointment())//verschillende week
                    {
                        int aantalDagenTussenVerschillendeWeek = patients[i].getDayAppointment()+(6-patients[i].getDayCall());
                        for(int k=0;k<aantalDagenTussenVerschillendeWeek-1;k++){// volle dagen er tussen 
                            waitingTillAppointment+=1440;
                            /*System.out.println("een dag tussen");
                            System.out.println("Huidige wachttijd"+ waitingTillAppointment);*/
                        }
                        waitingTillAppointment+=1440; //zondag
                        
                        waitingTillAppointment+= 480+patients[i].getAppointmenttime(); // dag van appointment: smorgens+apptime
                        
                        if(patients[i].getDayCall()==6||patients[i].getDayCall()==4){
                            waitingTillAppointment+= (720+(240-patients[i].getCalltime())); // dag van de Call: avond+resterende tijd na call
                            /*System.out.println("appointmentday tijd wachten halve dag");
                            System.out.println("Huidige wachttijd"+ waitingTillAppointment);*/
                        }
                        else{
                            waitingTillAppointment+= (420+(540-patients[i].getCalltime()));
                            /*System.out.println("appointmentday tijd wachten volle dag");
                            System.out.println("Huidige wachttijd"+ waitingTillAppointment);*/
                        }
                        
                    }
                    else { //zelfde week
                        if(patients[i].getDayCall()==patients[i].getDayAppointment()){// zelfde dag
                            //System.out.println("de dag bellen is gelijk aan de dag appointment");
                            waitingTillAppointment= patients[i].getAppointmenttime()- patients[i].getCalltime();
                            //System.out.println("Huidige wachttijd"+ waitingTillAppointment);
                        }
                        else{
                            int aantalDagenTussen= patients[i].getDayAppointment()-patients[i].getDayCall();
                            for(int l=0;l<aantalDagenTussen-1;l++){
                                waitingTillAppointment+=1440;
                                /*System.out.println("een dag tussen");
                                System.out.println("Huidige wachttijd"+ waitingTillAppointment);*/
                            }
                            waitingTillAppointment+= 480+patients[i].getAppointmenttime(); // dag van appointment: smorgens+apptime
                        
                            if(patients[i].getDayCall()==6||patients[i].getDayCall()==4){
                                //System.out.println("dag van call berkeneing volle dag ");
                                waitingTillAppointment+= (720+(240-patients[i].getCalltime())); // dag van de Call: avond+resterende tijd na call
                                //System.out.println("Huidige wachttijd"+ waitingTillAppointment);
                            }
                            else{
                                waitingTillAppointment+= (420+(540-patients[i].getCalltime()));
                                /*System.out.println("dag van call berkening halve dag");
                                System.out.println("wachttijd huidig"+waitingTillAppointment);*/
                            }
                           //System.out.println("wachttijd huidig"+waitingTillAppointment); 
                        }
                        //System.out.println("Som tijd tot appointment: nog niet uit for en 2 keer if"+waitingTillAppointment);
                    }
                    
                    
                  sumWaitingTillApp+=waitingTillAppointment;  
                  //1 array met waiting times
                  //patients[i].setWaitingTimeElective(waitingTillAppointment);
                  //System.out.println("Som tijd tot appointment: nog niet uit for en if"+sumWaitingTillApp);
                  averageAppointmentWaitingTime = sumWaitingTillApp/numberOfElectives;
                  appointmentWaitingTimeElectives.add(averageAppointmentWaitingTime);
                }
            //System.out.println("Som tijd tot appointment: nog niet uit for"+sumWaitingTillApp);
            }
            
        // }
            
            /*System.out.println("Som tijd tot appointment"+sumWaitingTillApp);
            System.out.println("aantal Electives berkeneing"+numberOfElectives);*/
            
            //System.out.println("gemiddelde wachttijd"+averageAppointmentWaitingTime);
            
            return appointmentWaitingTimeElectives;
            
        }
     
    public double calculateVarianceAppointmentWaitingTimeElective(){
        double sum = 0;
        for (int i = 0; i < totalNumberOfElectives; i++){
        sum = sum + (runningAverageAppointmentWaitingTimeElectives().get(i) - averageAppointmentWaitingTimeElectives())*(runningAverageAppointmentWaitingTimeElectives().get(i)-averageAppointmentWaitingTimeElectives());
    }
        double totalVarianceAppointmentWaitingTimeElective = sum/(totalNumberOfElectives-1);
        return totalVarianceAppointmentWaitingTimeElective;
    }
    
  
    private double averageScanWaitingTime(){
        double averageWaitingTime=0;
        double waitingTime=0;
        int amountOfElectives=0;
        for(int i=0;i<totalNumberOfPatients;i++){
           if((patients[i].getCategory().equals("Elective"))&&(patients[i].getServiceLength()!=0)){
                waitingTime+=(patients[i].getDeparturetime()-patients[i].getServiceLength())-patients[i].getArrivaltime();
                amountOfElectives++;     
            }
        }
        averageWaitingTime=waitingTime/amountOfElectives;
        return averageWaitingTime;
    }
    
    private double [] runningAverageScanWaitingTimeElective(){
        double averageWaitingTime=0;
        double waitingTime=0;
        int amountOfElectives=0;
        double [] arrayScanWaitingTimeElective = new double[10000000];
        for(int i=0;i<totalNumberOfPatients;i++){
            if(patients[i].getCategory().equals("Elective")){
                waitingTime+=(patients[i].getDeparturetime()-patients[i].getServiceLength())-patients[i].getArrivaltime();
                amountOfElectives++;
                averageWaitingTime=waitingTime/amountOfElectives; 
                arrayScanWaitingTimeElective[i] = averageWaitingTime;
            }
        }
        
        return arrayScanWaitingTimeElective;
    }
    
    public double calculateVarianceScanWaitingTimeElective(){
        double sum = 0;
        for(int i = 0; i < totalNumberOfElectives; i++){
           sum = sum + (runningAverageScanWaitingTimeElective()[i] - scanWaitingTimeUrgent())*(runningAverageScanWaitingTimeElective()[i] - scanWaitingTimeUrgent());
           
        }
        double totalVarianceScanWaitingTimeElective = sum/(totalNumberOfUrgents-1);
        return totalVarianceScanWaitingTimeElective;
   }
    
    
    public ArrayList<Double> gemiddeldesBerekenenPerWeekUrgents(){
        ArrayList<Double> gemiddeldesUrgents = new ArrayList<>();
        double som = 0;
        int patienten = 0;
        double weekGemiddelde = 0;
        for(int j = 1; j <= 200; j++){
            for(int i=0;i<totalNumberOfPatients;i++){
            if(patients[i].getCategory().equals("Urgent")){
                
                double waitingForScanTime= patients[i].getDeparturetime()-patients[i].getServiceLength()-patients[i].getArrivaltime();
                //1 lange array om dan te printen in CSV
                
                
                    if((patients[i].getWeekAppointment()==j)){
                        System.out.println("wachttijd"+ waitingForScanTime);
                        patienten++;
                        som = som + waitingForScanTime;
                        System.out.println("De huidge som " +som);
                    }
                    
                    }
            }
                    weekGemiddelde = som/patienten;
                    gemiddeldesUrgents.add(weekGemiddelde);
                    System.out.println(" gemiddelde" + weekGemiddelde);
                    System.out.println("____________________");
        som = 0;
        patienten = 0;
        
        }
        return gemiddeldesUrgents;
    }
    
    
    public void gemiddeldesBerekenenPerWeekElectives(){
        ArrayList<Double> gemiddeldesElectives = new ArrayList<>();
        double som = 0;
        int patienten = 0;
        double weekGemiddelde = 0;
        for(int j = 1; j <= 200; j++){
            for(int i=0;i<totalNumberOfPatients;i++){
            if(patients[i].getCategory().equals("Elective")){
                    if((patients[i].getWeekAppointment()==j)){
                        patienten++;
                        som = som + patients[i].getWaitingTimeElective();
                    }
                    weekGemiddelde = som/patienten;
                    System.out.println(weekGemiddelde);
                    gemiddeldesElectives.add(weekGemiddelde);
            }
            som = 0;
            patienten = 0;
        }
        }
    }
    
    
    
    private double scanWaitingTimeUrgent(){ //performance measure 2
        double sumScanTime=0;
        double averageScanTime = 0;
        int aantalUrgent=0;
              
        for(int i=0;i<totalNumberOfPatients;i++){
            if(patients[i].getCategory().equals("Urgent")){
                double waitingForScanTime= patients[i].getAppointmenttime()-patients[i].getArrivaltime();
                //1 lange array om dan te printen in CSV
                if(week==200 && day==6){
                    
                        WTUrgents.add(waitingForScanTime);
                }
                
                
                sumScanTime+=waitingForScanTime;
                /*System.out.println("number"+(i+1));
                System.out.println("wait for scan urgent"+waitingForScanTime);
                System.out.println("som van wachttijden"+ sumScanTime);*/
                aantalUrgent++;
            }
            
        }
        averageScanTime = sumScanTime/aantalUrgent;
        //System.out.println(averageScanTime);
        return averageScanTime ;
    }
    
    public ArrayList<Double> runningAverageScanWaitingTimeUrgent(){ //performance measure 2
        double sumScanTime=0;
        double averageScanTime = 0;
        int aantalUrgent=0;
        ArrayList<Double> arrayWaitingTimeUrgent = new ArrayList<>();
              
        for(int i=0;i<totalNumberOfPatients;i++){
            if(patients[i].getCategory().equals("Urgent")){
                double waitingForScanTime= patients[i].getDeparturetime()-patients[i].getServiceLength()-patients[i].getArrivaltime();
                //1 lange array om dan te printen in CSV
                //WTUrgents.add(waitingForScanTime);
                patients[i].setWaitingTimeUrgent(waitingForScanTime);
                
                sumScanTime+=waitingForScanTime;
                /*System.out.println("number"+(i+1));
                System.out.println("wait for scan urgent"+waitingForScanTime);
                System.out.println("som van wachttijden"+ sumScanTime);*/
                aantalUrgent++;
                averageScanTime = sumScanTime/aantalUrgent;
                arrayWaitingTimeUrgent.add(averageScanTime);
            }
            
        }
        
        
        return arrayWaitingTimeUrgent ;
    }
    
   public ArrayList<Double> controlvariable(){
       ArrayList<Double> serviceLength = new ArrayList<>();
       for(int i=0; i < totalNumberOfPatients; i++){
           serviceLength.add(patients[i].getServiceLength());
       }
       return serviceLength;
   } 
    
     
   public double calculateVarianceScanWaitingTimeUrgent(){
        double sum = 0;
        for(int i = 0; i < totalNumberOfUrgents; i++){
           sum = sum + (runningAverageScanWaitingTimeUrgent().get(i) - scanWaitingTimeUrgent())*(runningAverageScanWaitingTimeUrgent().get(i) - scanWaitingTimeUrgent());
           
        }
        double totalVarianceScanWaitingTimeUrgent = sum/(totalNumberOfUrgents-1);
        return totalVarianceScanWaitingTimeUrgent;
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

