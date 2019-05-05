/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besliskunde_pc;

//import com.opencsv.CSVWriter; --> WAS EEN FOUTMELDING
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

/**
 *
 * @author Lynn
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

      
        
        //CODE VOOR NAAR EXCEL
        /*        
          ArrayList<Double> arrayVanCallTimes = new ArrayList<>();
            
            double callTime = 0;
            double interCallingTime;
            
            for(int i = 0; i < 40; i++){
            interCallingTime = (Distributions.Exponential_distribution(28.345))*540;
            callTime = callTime + interCallingTime;    
                if(callTime <540) {
                    arrayVanCallTimes.add(callTime);    
                }
            
            int amountOfElectivesCallingThatDay = arrayVanCallTimes.size();
            System.out.println("Arraysize: " + arrayVanCallTimes.size());
            }
            
            for(int i = 0; i < arrayVanCallTimes.size(); i++){  
                System.out.println("Call time " + arrayVanCallTimes.get(i));
            }
            
            //WRITEN 
            String csv = "data.csv";
            CSVWriter writer = new CSVWriter(new FileWriter(csv));
            
            //kolomnamen
            String[] header = new String[1];
            header[0] = "Arrival times";
            writer.writeNext(header);
            
            //rijen
            String[] line = new String[header.length];
            for(int i = 0; i < arrayVanCallTimes.size(); i++){
                line[0] = Double.toString(arrayVanCallTimes.get(i));
                line[0] = line[0].replace(".", ",");
                writer.writeNext(line);
            }
            
            writer.close();
            System.out.println("CSV gemaakt");
            
            */
        
        
        

        
       // 2. SIMULATIE URGENT SLOTS rule 1--> WERKT! bij first come first serve wordt er rekening gehouden met wanneer de urgent slots vallen!
       /*Patient patient1=new Patient();
       Simulation simulatie1= new Simulation();
       simulatie1.initialization();
       simulatie1.setPatientDataCall(105, 240, 115, 4, patient1);
       
       System.out.println(patient1.getAppointmenttime());
       System.out.println(patient1.getCalltime());
       System.out.println(patient1.getCategory()); 
       System.out.println(patient1.getArrivaltime());
       */ 
       
       
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
        
       
        
        
        //4. SIMULATIE RULE 1 ALLES    
        
        Simulation simulation1=new Simulation();
        simulation1.initialization();
        simulation1.Simulatie(2);
     
    
    
        // 5. simulatie RULE 1 van appointment met overslaan middag --> WERKT!
        /* Patient patient1=new Patient();
        Simulation simulatie1= new Simulation();
        simulatie1.initialization();
        simulatie1.setPatientDataCall(225, 540, 115, 1, patient1);
        System.out.println(patient1.getAppointmenttime());
        System.out.println(patient1.getCalltime());
        System.out.println(patient1.getCategory()); 
        System.out.println(patient1.getArrivaltime());]*/
    
    


        // 6. simuatie RULE 1 urgentArrival --> WERKT!
        /*
        Patient patient1=new Patient();
        Simulation simulatie1= new Simulation();
        simulatie1.initialization();
        simulatie1.setPatientDataUrgentArrival(240, 6, 1, patient1);
        System.out.println(patient1.getAppointmenttime());
        System.out.println(patient1.getArrivaltime());
        System.out.println(patient1.getCategory());
        */
       
        //7. SIMULATIE RULE BENCHING ALLES    
        /*
        SimulationBench simulation1=new SimulationBench();
        simulation1.initialization();
        simulation1.Simulatie(2);
        */
        
        //VOORBEELDEN DISTRIBUTIES    
        /*  System.out.println("Besliskunde project");
        System.out.println("normal distribution: "+Distributions.Normal_distribution(11.0, 5.0));
        System.out.println("Poisson distribution: "+Distributions.Poisson_distribution(11.0));
        System.out.println("Bernouilli distribution: "+Distributions.Bernouilli_distribution(0.2));//0.2 prob that answer will be 0
        System.out.println("Uniform distribution: "+Distributions.Uniform_distribution(2, 7));
        System.out.println("Triangular distribution: "+Distributions.Triangular_distribution(1, 9, 6));*/
    
    
    }
    
}
