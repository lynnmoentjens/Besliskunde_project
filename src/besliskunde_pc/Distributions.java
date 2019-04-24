/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besliskunde_pc;

import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.Random;

/**
 *
 * @author Lynn
 */
public class Distributions {
    
    
    
    
    public static int Normal_distribution(double mean, double stdev) { //Static so you don't need to create an instance of the class to access it in the main
   // TO MODEL BASED ON CUMULATIVE DENSITY FUNCTION OF NORMAL DISTRIBUTION BASED ON BOOK OF SHELDON ROSS, Simulation, The polar method, p80.
    
   Random rand = new Random(); //making random object
   
    double v1, v2, t;
    int x;
    do
    {
        v1 = (float)(rand.nextInt(1000)) * 2;
        v1 = v1/1000;
        v1 = v1-1;
        v2 = (float)(rand.nextInt(1000)) * 2;
        v2 = v2/1000;
        v2 = v2-1;
        t=(v1*v1)+(v2*v2);
    }
    while(t>=1||t==0);
    double multiplier = sqrt(-2*log(t)/t);
    x = (int)(v1 * multiplier * stdev + mean);
    return x;
    
    
}
    public static double Exponential_distribution(double lambda){
        return  Math.log(1-Math.random())/(-lambda);    
    }

    public static int Poisson_distribution(double lambda){      // INVERSION METHOD POISSON DISTRIBUTION
    
    Random rand = new Random();
        
    double k, L;
    double j1, j2, j3;
    int p;
    
    int returnValue;
    
    j1 = (float)(rand.nextInt(1000))/1000;
    k = 0;
    L = exp(-lambda);
    j3 = 0;
    do
    {
        j2 = L * pow(lambda, k);
        p = 1;
        for (int i6 = 0; i6 <= k; i6++)
        {   if (i6 == 0)
            p = 1;
        else
            p = p*i6;
            
        }
        j2 = j2/p;
        j3 = j3+j2;
        k++;
    } while (j1 >= j3);
    
    returnValue= (int)k-1;
    
    return returnValue;
    
}
  public static int Bernouilli_distribution(double prob){     // INVERSION METHOD BERNOUILLI DISTRIBUTION
    
    Random rand = new Random();
      
    double j1;
      
    j1 = (float)(rand.nextInt(1000))/1000;
    if (j1 < prob)
        return 0;
    else
        return 1;
    
    
}
  
  
  public static int Uniform_distribution(double a, double b){ // INVERSION METHOD UNIFORM DISTRIBUTION
   int x;
   Random rand = new Random();
      
   double j1;
      
    j1 = (float)(rand.nextInt(1000))/1000;
    x = (int)(a + (b-a) * j1);
    
    return x;
}
  
  
  public static int Triangular_distribution(int a, int b, int c){ // INVERSION METHOD TRIANGULAR DISTRIBUTION
   double mean, stdev;
    double x, L;
    
    mean = (a+b+c)/3;
    stdev = (pow(a,2)+pow(b,2)+pow(c,2)-a*b-a*c-b*c)/18;
    stdev = sqrt(stdev);
    
    Random rand = new Random();
    double j1;
    j1 = (float)(rand.nextInt(1000))/1000;
    
    x = a;
    
    do
    {   if (x <= b)
        L = pow((x-a),2)/((c-a)*(b-a));
    else
        L = 1-(pow(c-x,2)/((c-a)*(c-b)));
        
    x++;
    } 
    
    while (j1 >= L);
    
    return (int)x-1;
    
}

    
}
