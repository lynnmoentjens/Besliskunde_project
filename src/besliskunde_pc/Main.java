/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besliskunde_pc;

import java.util.ArrayList;

/**
 *
 * @author Lynn
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // 1. EXPONENTIAL DISTRIBUTION
        /*
        double getal=1.25;
        System.out.println(Distributions.Exponential_distribution(getal));
        */

        
       // 2. SIMULATIE URGENT SLOTS rule 1--> WERKT! bij first come first serve wordt er rekening gehouden met wanneer de urgent slots vallen!
       /*Patient patient1=new Patient();
       Simulation simulatie1= new Simulation();
       simulatie1.initialization();
       simulatie1.setPatientDataCall(105, 540, 115, 2, patient1);
       
       System.out.println(patient1.getAppointmenttime());
       System.out.println(patient1.getCalltime());
       System.out.println(patient1.getCategory()); 
       System.out.println(patient1.getArrivaltime());*/
        
       
       
       // 3. SIMULATIE URGENT SLOTS rule 2 --> WERKT VOOR EEN DEEL
       //OPM: werk volledig alleen indien in de setPatientDataCall het numberOfPatients voor die dag op 2 wordt gezet
       //indien er een ander aantal numberOfPatients zijn wilt het niet werken, 
       //maar denk dat het wel kan kloppen alsde de rest van de simulatie het doet
       /*
       Patient patient2=new Patient();
       SimulationBailey simulatie2= new SimulationBailey();
       simulatie2.initialization();
       simulatie2.setPatientDataCall(300, 540, 49, 1, patient2);
       
       System.out.println(patient2.getAppointmenttime());
       System.out.println(patient2.getScheduleTimeElective());
       System.out.println(patient2.getCalltime());
       System.out.println(patient2.getCategory()); 
       System.out.println(patient2.getArrivaltime());*/
        
       
        
        
        // 4. SIMULATIE RULE 1 ALLES    
        /* 
        Simulation simulation1=new Simulation();
        simulation1.initialization();
        simulation1.Simulatie(1);*/
     
    
    
        // 5. simulatie RULE 1 van appointment met overslaan middag --> WERKT!
        /* Patient patient1=new Patient();
        Simulation simulatie1= new Simulation();
        simulatie1.initialization();
        simulatie1.setPatientDataCall(225, 540, 115, 1, patient1);
        System.out.println(patient1.getAppointmenttime());
        System.out.println(patient1.getCalltime());
        System.out.println(patient1.getCategory()); 
        System.out.println(patient1.getArrivaltime());]*/
    
    


        // 6. simuatie RULE 1 urgentArrival 
        Patient patient1=new Patient();
        Simulation simulatie1= new Simulation();
        simulatie1.initialization();
        simulatie1.setPatientDataUrgentArrival(121, 1, 1, patient1);
        System.out.println(patient1.getAppointmenttime());
        System.out.println(patient1.getArrivaltime());
        System.out.println(patient1.getCategory());
        
       
        
        //VOORBEELDEN DISTRIBUTIES    
        /*  System.out.println("Besliskunde project");
        System.out.println("normal distribution: "+Distributions.Normal_distribution(11.0, 5.0));
        System.out.println("Poisson distribution: "+Distributions.Poisson_distribution(11.0));
        System.out.println("Bernouilli distribution: "+Distributions.Bernouilli_distribution(0.2));//0.2 prob that answer will be 0
        System.out.println("Uniform distribution: "+Distributions.Uniform_distribution(2, 7));
        System.out.println("Triangular distribution: "+Distributions.Triangular_distribution(1, 9, 6));*/
    
    
    }
    
}
