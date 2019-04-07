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
public class Besliskunde_pc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("Besliskunde project");
                
        System.out.println("normal distribution: "+Distributions.Normal_distribution(11.0, 5.0));
        
        System.out.println("Poisson distribution: "+Distributions.Poisson_distribution(11.0));
        
        System.out.println("Bernouilli distribution: "+Distributions.Bernouilli_distribution(0.2));//0.2 prob that answer will be 0
        
        System.out.println("Uniform distribution: "+Distributions.Uniform_distribution(2, 7));
        
        System.out.println("Triangular distribution: "+Distributions.Triangular_distribution(1, 9, 6));
    }
    
}
