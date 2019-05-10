/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besliskunde_pc;



import com.opencsv.CSVWriter; 
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

      /*
        SimulationBench simulation1 = new SimulationBench();
        simulation1.initialization();
        simulation1.Simulatie(3);
        */
        
        
        
        SimulationBlocking simulation1=new SimulationBlocking();
        simulation1.initialization();
        simulation1.Simulatie(800);
        
        //CODE VOOR NAAR EXCEL
             
            
        
            //WRITEN ELECTIVE WT
            String csv1 = "dataElectives_S3_14slots_blocking.csv";
            CSVWriter writer = new CSVWriter(new FileWriter(csv1));
            
            //kolomnamen
            String[] header = new String[1];
            header[0] = "Elective waiting times";
            writer.writeNext(header);
            
            //rijen
            String[] line = new String[header.length];
            System.out.println("Size WTElectives: "+ simulation1.WTElectives.size());
            for(int i = 0; i < simulation1.WTElectives.size(); i++){
                line[0] = Double.toString(simulation1.WTElectives.get(i));
                line[0] = line[0].replace(".", ",");
                writer.writeNext(line);
            }    
            
            writer.close();
            System.out.println("CSV1 gemaakt");
            
            
            //RUNNING AVERAGES
            String csvB = "dataElectivesRA_S3_14slots_blocking.csv";
            CSVWriter schrijver = new CSVWriter(new FileWriter(csvB));
            
            //kolomnamen
            String[] hoofd = new String[1];
            hoofd[0] = "Running average electives";
            schrijver.writeNext(hoofd);
            
            String[] lijn = new String[hoofd.length];
            for(int i = 0; i < simulation1.runningAverageAppointmentWaitingTimeElectives().size(); i++){
                lijn[0] = Double.toString(simulation1.runningAverageAppointmentWaitingTimeElectives().get(i));
                lijn[0] = lijn[0].replace(".", ",");  
                
                schrijver.writeNext(lijn);
            }
            
            schrijver.close();
            System.out.println("CSV2 gemaakt");
            
            
            
            //WRITEN URGENT WT
            String csv2 = "dataUrgents_S3_14slots_blocking.csv";
            CSVWriter writer2 = new CSVWriter(new FileWriter(csv2));
            
            //kolomnamen
            String[] header2 = new String[1];
            header2[0] = "Urgent waiting times";
            writer2.writeNext(header2);
            
            //rijen
            String[] line2 = new String[header2.length];
            System.out.println("Size WTUrgents: "+ simulation1.WTUrgents.size());
            for(int i = 0; i < simulation1.WTUrgents.size(); i++){
                line2[0] = Double.toString(simulation1.WTUrgents.get(i));
                line2[0] = line2[0].replace(".", ",");
                
                writer2.writeNext(line2);
            }
            
            writer2.close();
            System.out.println("CSV3 gemaakt");
            
            
            //RUNNING AVERAGE
            String csvD = "dataUrgentsRA_S3_14slots_blocking.csv";
            CSVWriter schrijver2 = new CSVWriter(new FileWriter(csvD));
            
            //kolomnamen
            String[] hoofd2 = new String[1];
            hoofd2[0] = "Running average urgents";
            schrijver2.writeNext(hoofd2);
            
            String[] lijn2 = new String[hoofd2.length];
            for(int i = 0; i < simulation1.runningAverageScanWaitingTimeUrgent().size(); i++){
                lijn2[0] = Double.toString(simulation1.runningAverageScanWaitingTimeUrgent().get(i));
                lijn2[0] = lijn2[0].replace(".", ",");  
                
                schrijver2.writeNext(lijn2);
            }
            
            schrijver2.close();
            System.out.println("CSV4 gemaakt");
            
           
           //CORRELATION BEREKENEN
            String corr = "Correlation_S3_14slots_blocking.csv";
            CSVWriter corrCSV = new CSVWriter(new FileWriter(corr));
            
            //kolomnamen
            String[] hoofding = new String[1];
            hoofding[0] = "Week gemiddelde electives";
            corrCSV.writeNext(hoofding);
            
            String[] regel = new String[hoofding.length];
            for(int i = 0; i < simulation1.gemiddeldesBerekenenPerWeekElectives().size(); i++){
                regel[0] = Double.toString(simulation1.gemiddeldesBerekenenPerWeekElectives().get(i));
                regel[0] = regel[0].replace(".", ",");  
                
                corrCSV.writeNext(regel);
            }
            
            corrCSV.close();
            System.out.println("Correlations1 gemaakt");  
            
            String corr2 = "Correlation2_S3_14slots_blocking.csv";
            CSVWriter corrCSV2 = new CSVWriter(new FileWriter(corr2));
            
            //kolomnamen
            String[] hoofding2 = new String[1];
            hoofding2[0] = "Week gemiddelde urgents";
            corrCSV.writeNext(hoofding2);
            
            String[] regel2 = new String[hoofding2.length];
            for(int i = 0; i < simulation1.gemiddeldesBerekenenPerWeekUrgents().size(); i++){            
                regel2[0] = Double.toString(simulation1.gemiddeldesBerekenenPerWeekUrgents().get(i));
                regel2[0] = regel2[0].replace(".", ",");  
            
                corrCSV2.writeNext(regel2);
            }
            
            corrCSV2.close();
            System.out.println("Correlations2 gemaakt");  
        
        
           
            
            
            
            
            
            
        /*
            //WRITEN SERVICE LENGTH VOOR CONTROL VARIATE
            String csv3 = "dataServiceLength.csv";
            CSVWriter writer = new CSVWriter(new FileWriter(csv3));
            
            //kolomnamen
            String[] header = new String[1];
            header[0] = "Service lengths";
            writer.writeNext(header);
           
            //rijen
            String[] line = new String[header.length];
            for(int i = 0; i < simulation1.serviceLength.size(); i++){
                line[0] = Double.toString(simulation1.serviceLength.get(i));
                line[0] = line[0].replace(".", ",");
                writer.writeNext(line);
        
            */
        
        
        /*
        SimulationBailey simulation2 = new SimulationBailey();
        simulation2.initialization();
        simulation2.Simulatie(2);
        
        //CODE VOOR NAAR EXCEL
             
            //WRITEN ELECTIVE WT
            String csv3 = "dataElectivesBailey.csv";
            CSVWriter writer3 = new CSVWriter(new FileWriter(csv3));
            
            //kolomnamen
            String[] header3 = new String[1];
            header3[0] = "Elective waiting times";
            writer3.writeNext(header3);
            
            //rijen
            String[] line3 = new String[header3.length];
            for(int i = 0; i < simulation2.WTElectives.size(); i++){
                line3[0] = Double.toString(simulation2.WTElectives.get(i));
                line3[0] = line3[0].replace(".", ",");
                writer3.writeNext(line3);
            }
            
            writer3.close();
            System.out.println("CSV gemaakt");
            
            //WRITEN URGENT WT
            String csv4 = "dataUrgentsBailey.csv";
            CSVWriter writer4 = new CSVWriter(new FileWriter(csv4));
            
            //kolomnamen
            String[] header4 = new String[1];
            header4[0] = "Urgent waiting times";
            writer4.writeNext(header4);
            
            //rijen
            String[] line4 = new String[header4.length];
            for(int i = 0; i < simulation2.WTUrgents.size(); i++){
                line4[0] = Double.toString(simulation2.WTUrgents.get(i));
                line4[0] = line4[0].replace(".", ",");
                writer4.writeNext(line4);
            }
            
            writer4.close();
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
        simulation1.Simulatie(2);*/
        
        //8. SIMULATIE RULE BAILEY ALLES    
        
        /*SimulationBailey simulation1=new SimulationBailey();
        simulation1.initialization();
        simulation1.Simulatie(2);*/
        
        
        //VOORBEELDEN DISTRIBUTIES    
        /*  System.out.println("Besliskunde project");
        System.out.println("normal distribution: "+Distributions.Normal_distribution(11.0, 5.0));
        System.out.println("Poisson distribution: "+Distributions.Poisson_distribution(11.0));
        System.out.println("Bernouilli distribution: "+Distributions.Bernouilli_distribution(0.2));//0.2 prob that answer will be 0
        System.out.println("Uniform distribution: "+Distributions.Uniform_distribution(2, 7));
        System.out.println("Triangular distribution: "+Distributions.Triangular_distribution(1, 9, 6));*/
    
    
    }
    
}
