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
public class Procedure {
    // Jus: ik bekijk nu alles per dag, daarna zien we wel met die zaterdag en donderdag 
    double time = 0;  //Jus: ik zou alles rekenen met minuten en niet omzetten naar uren anders gaat het verwarrend worden
    double einduur = 540; //Jus: 15minuten per patient --> 32 slots+ 4 onbezette slots op de middag geeft: 36*15=540
    double callTime; 
    double appointmentTime = 0;   
    double arrivalTime=600; 
    double departureTime= 600; 
    int numberOfUrgent = 0; 
    int numberOfElectives = 0; 
    double interarrivaltimecall; // tijd tussen 2 bellers
    double r;
    Patient patient; 
    
    public void system(){
        while((time<einduur)&&(numberOfUrgent!=0)){    //Jus: het programma eindigt wanneer de tijd is verstreken maar als er nog
                                            // urgent patients in de wachtrij zitten moeten die wel nog worden afgewerkt
                                            // vraag: wat als iemand belt na de slots maar ze zijn nog bezig met een urgent? 
                                                     // nemen we die dan al op voor de volgende dag?
            if((callTime<departureTime)&&(callTime<arrivalTime)){       // Jus: hierna komt een appointment maken --> signavio: "FCFS making appointment"
                time=callTime;                   // Jus: update time
                patient = new Patient();
                patient.setCalltime(callTime);
                numberOfElectives++;
                if((appointmentTime==15)||(appointmentTime==60)){ // Jus: dit is een voorbeeldje: als bv. slot 3 en slot 6 niet mogen 
                    appointmentTime+=30;
                    patient.setAppointmenttime(appointmentTime);
                    }                           // worden gescheduled dan zal er +30 worden gedaan ipv +15 (huidige was:15 voor de vorige appointment, om nu up te daten --> +30)
                else if(appointmentTime==225){
                    appointmentTime+=75;                              // volgende empty slot is na de namiddag
                    patient.setAppointmenttime(appointmentTime);
                } 
                else if(appointmentTime==525){
                    appointmentTime= 0;                               // dit is dan al voor de volgende dag --> je begint terug van nul
                     patient.setAppointmenttime(appointmentTime);
                }
                else{
                    appointmentTime+=15;                              // in de gewone gevallen
                     patient.setAppointmenttime(appointmentTime);
                }
                // in signavio staat hier: set arrival maar ik denk niet dat we dat hier kunnen doen anders gaan we al te ver zitten met die arrival
                r = Math.random();                       //genereer het random getal
                interarrivaltimecall=  -Math.log(r);            // tijd tussen bellers VRAAG: welke verdeling?
                callTime= time+interarrivaltimecall;        
            }                       
            else if((departureTime<callTime)&&(departureTime<arrivalTime)){  //Jus: hierna komt een departure van een patient --> Signavio: "Departure event"
                time= departureTime;
                
            }
            else if((arrivalTime<callTime)&&(arrivalTime<departureTime)){  // Jus: hierna komt het arrival event --> Signavio: "Arrival event 2"
                time= arrivalTime; 
                                  
            }
        }
    }
    
}
