/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besliskunde_pc;

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

    
   // simulatie van appointment met overslaan middag
       Patient patient1=new Patient();
       Simulation simulatie1= new Simulation();
       simulatie1.initialization();
       simulatie1.setPatientDataCall(225, 540, 115, 1, patient1);
       System.out.println(patient1.getAppointmenttime());
       System.out.println(patient1.getCalltime());
       System.out.println(patient1.getCategory()); 
       System.out.println(patient1.getArrivaltime());
    
    //simuatie urgentArrival 
    /*Patient patient1=new Patient();
       Simulation simulatie1= new Simulation();
       simulatie1.initialization();
       simulatie1.setPatientDataUrgentArrival(16, 1, 1, patient1);
       System.out.println(patient1.getAppointmenttime());
       System.out.println(patient1.getArrivaltime());
       System.out.println(patient1.getCategory());*/
        
        
        
        
        
        
        
    /*  System.out.println("Besliskunde project");
        System.out.println("normal distribution: "+Distributions.Normal_distribution(11.0, 5.0));
        System.out.println("Poisson distribution: "+Distributions.Poisson_distribution(11.0));
        System.out.println("Bernouilli distribution: "+Distributions.Bernouilli_distribution(0.2));//0.2 prob that answer will be 0
        System.out.println("Uniform distribution: "+Distributions.Uniform_distribution(2, 7));
        System.out.println("Triangular distribution: "+Distributions.Triangular_distribution(1, 9, 6));
    
    */    
    }
    
}
