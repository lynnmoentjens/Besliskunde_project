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
public class System {
    // Jus: ik bekijk nu alles per dag,daarna zien we wel met die zaterdag en donderdag 
    double T=0;  //Jus: ik zou alles rekenen met minuten en niet omzetten naar uren anders gaat het verwarrend worden
    double einduur= 540; //Jus: 15minuten per patient --> 32 slots+ 4 onbezette slots op de middag geeft: 36*15=540
    double Tc; //Jus: call time
    double Tr; //Jus: arrival time
    double Td; //Jus: departure time
    int Nu= 0; //Jus: urgent patients in system
    int Ne= 0; //Jus: elective patients in system
    double Ta=0;   // Jus: dit is Ta?? ik ben verward door die Appointment time! dit is het startuur van het volgende empty slot
    double r;
    double interarrivaltimecall; // tijd tussen 2 bellers
    
    public void system(){
        while((T<einduur)&&(Nu!=0)){    //Jus: het programma eindigt wanneer de tijd is verstreken maar als er nog
                                            // urgent patients in de wachtrij zitten moeten die wel nog worden afgewerkt
                                            // vraag: wat als iemand belt na de slots maar ze zijn nog bezig met een urgent? 
                                                     // nemen we die dan al op voor de volgende dag?
            if((Tc<Td)&&(Tc<Tr)){       // Jus: hierna komt een appointment maken --> signavio: "FCFS making appointment"
                T=Tc;                   // Jus: update time
                Ne++;
                if((Ta==15)||(Ta==60)){ // Jus: dit is een voorbeeldje: als bv. slot 3 en slot 6 niet mogen 
                    Ta+=30;  }                           // worden gescheduled dan zal er +30 worden gedaan ipv +15 (huidige was:15 voor de vorige appointment, om nu up te daten --> +30)
                else if(Ta==225){
                    Ta+=75;                              // volgende empty slot is na de namiddag
                } 
                else if(Ta==525){
                    Ta= 0;                               // dit is dan al voor de volgende dag --> je begint terug van nul
                }
                else{
                    Ta+=15;                              // in de gewone gevallen
                }
                // in signavio staat hier: set arrival maar ik denk niet dat we dat hier kunnen doen anders gaan we al te ver zitten met die arrival
                r = Math.random();                       //genereer het random getal
                interarrivaltimecall=  -Math.log(r);            // tijd tussen bellers VRAAG: welke verdeling?
                Tc= T+interarrivaltimecall;        
            }                       
            else if((Td<Tc)&&(Td<Tr)){  //Jus: hierna komt een departure van een patient --> Signavio: "Departure event"
                
            }
            else if((Tr<Tc)&&(Tr<Td)){  // Jus: hierna komt het arrival event --> Signavio: "Arrival event 2"
                
            }
        }
    }
    
}
