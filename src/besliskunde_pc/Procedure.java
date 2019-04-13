/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besliskunde_pc;

import java.util.ArrayList;

/**
 *
 * @author jwpennem
 */
public class Procedure {
    // Jus: ik bekijk nu alles per dag, daarna zien we wel met die zaterdag en donderdag 
    double time = 0;  //Jus: ik zou alles rekenen met minuten en niet omzetten naar uren anders gaat het verwarrend worden
    double einduur = 540; //Jus: 15minuten per patient --> 32 slots+ 4 onbezette slots op de middag geeft: 36*15=540
    double callTime; 
    double appointmentTime = 0;   
    double arrivalTimeUrgent= 600; 
    double arrivalTimeElective=600;
    double departureTimeElective= 600; 
    double departureTimeUrgent=600;
    int numberOfUrgent = 0; 
    int numberOfElectives = 0; 
    int numberOfPatients=0;
    double interarrivaltimecall; // tijd tussen 2 bellers
    double r;
    Patient patient; 
    ArrayList<Patient> electivePatients= new ArrayList<>();
    String change= "No";
    int accountDelay=0;
    
    public void system(){
        while((time<einduur)&&(numberOfUrgent!=0)){    //Jus: het programma eindigt wanneer de tijd is verstreken maar als er nog
                                            // urgent patients in de wachtrij zitten moeten die wel nog worden afgewerkt
                                            // vraag: wat als iemand belt na de slots maar ze zijn nog bezig met een urgent? 
                                                     // nemen we die dan al op voor de volgende dag?
            if((callTime<departureTimeElective)&&(callTime<arrivalTimeElective)&&(callTime<departureTimeUrgent)&&(callTime<arrivalTimeUrgent)){       // Jus: hierna komt een appointment maken --> signavio: "FCFS making appointment"
                time=callTime;                   // Jus: update time
                patient = new Patient();
                electivePatients.add(patient);
                patient.setCalltime(callTime);
                numberOfElectives++;
                patient.setCategory("Elective");
                numberOfPatients++;
                appointmentTime=setAppointmentElective(appointmentTime);
                patient.setAppointmenttime(appointmentTime);
                // in signavio staat hier: set arrival maar ik denk niet dat we dat hier kunnen doen anders gaan we al te ver zitten met die arrival
                r = Math.random();                       //genereer het random getal
                interarrivaltimecall=  -Math.log(r);            // tijd tussen bellers VRAAG: welke verdeling?
                callTime= time+interarrivaltimecall;        
            }                       
            else if((departureTimeUrgent<callTime)&&(departureTimeUrgent<arrivalTimeUrgent)&&(departureTimeUrgent<arrivalTimeElective)&&(departureTimeUrgent<departureTimeElective)){  //Jus: hierna komt een departure van een patient --> Signavio: "Departure event"
                time= departureTimeUrgent;
                
            }
            else if((departureTimeElective<callTime)&&(departureTimeElective<arrivalTimeElective)&&(departureTimeElective<arrivalTimeUrgent)&&(departureTimeElective<departureTimeUrgent)){  //Jus: hierna komt een departure van een patient --> Signavio: "Departure event"
                time= departureTimeElective;
                
            }
            else if((arrivalTimeUrgent<callTime)&&(arrivalTimeUrgent<departureTimeUrgent)&&(arrivalTimeUrgent<arrivalTimeElective)&&(arrivalTimeUrgent<departureTimeElective)){  // Jus: hierna komt het arrival event --> Signavio: "Arrival event 2"
                time= arrivalTimeUrgent; 
                numberOfPatients++;
                numberOfUrgent++;
                patient = new Patient();
                patient.setCategory("Urgent");
                patient.setArrivaltime(arrivalTimeUrgent);
                electivePatients.add(patient);
                                  
            }
            else if((arrivalTimeElective<callTime)&&(arrivalTimeElective<departureTimeElective)&&(arrivalTimeElective<arrivalTimeUrgent)&&(arrivalTimeElective<departureTimeUrgent)){  // Jus: hierna komt het arrival event --> Signavio: "Arrival event 2"
                time= arrivalTimeElective; 
                double patientAppointmentTime=0;
                do{
                    for(int x=0;x<electivePatients.size();x++){
                        Patient pat= electivePatients.get(x);
                        if(pat.getArrivaltime()==0){
                            electivePatients.get(x).setArrivaltime(arrivalTimeElective);
                            patientAppointmentTime= electivePatients.get(x).getAppointmenttime();
                            
                            change= "YES";
                           
                        }  
                    }
                }while(change=="No");
                change="No";
                
                if(patientAppointmentTime<arrivalTimeElective){
                    accountDelay+= (arrivalTimeElective-patientAppointmentTime);
                    //...
                }
                else if(patientAppointmentTime<arrivalTimeElective)
                {
                    ///...
                }
                else {
                    accountDelay=+0;
                    //add patient to the queue
                    //schedule next arrival event
                }
                
                
                
                
                
                                  
            }
        }
    }
    
    
    
    
    public double setAppointmentElective(double previousElectiveAppointmentTime){ 
        if((previousElectiveAppointmentTime==15)||(previousElectiveAppointmentTime==60)){ // Jus: dit is een voorbeeldje: als bv. slot 3 en slot 6 niet mogen 
                    previousElectiveAppointmentTime+=30;
                    }                                                                    // worden gescheduled dan zal er +30 worden gedaan ipv +15 (huidige was:15 voor de vorige appointment, om nu up te daten --> +30)
                else if(previousElectiveAppointmentTime==225){
                    previousElectiveAppointmentTime+=75;                              // volgende empty slot is na de namiddag
                } 
                else if(previousElectiveAppointmentTime==525){
                    previousElectiveAppointmentTime= 0;                               // dit is dan al voor de volgende dag --> je begint terug van nul
                }
                else{
                    previousElectiveAppointmentTime+=15;                              // in de gewone gevallen 
                }
        return previousElectiveAppointmentTime;
    }
    
    
    
    
}
