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
public class UrgentSlots {    
    
    
    
    ////////////////////////////////////////////////////////////////////////////
    //STRATEGY1
    
    //TO TEST 10 urgent time slots + Strategy 1, FCFS
    public static ArrayList<int[]> testSignificanceSlots10(){
        
        int[] day1 = new int[]{ 225, 525 }; 
        int[] day2 = new int[]{ 225, 525 };
        int[] day3 = new int[]{ 225, 525 };
        int[] day4 = new int[]{ 225 };
        int[] day5 = new int[]{ 225, 525 };
        int[] day6 = new int[]{ 225 };
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
    }
    //TO TEST 12 urgent time slots + Strategy 1, FCFS
    public static ArrayList<int[]> testSignificanceSlots12(){
        
        int[] day1 = new int[]{ 210, 225, 525 }; 
        int[] day2 = new int[]{ 225, 525 };
        int[] day3 = new int[]{ 210, 225, 525 };
        int[] day4 = new int[]{ 225 };
        int[] day5 = new int[]{ 225, 525 };
        int[] day6 = new int[]{ 225 };
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
    }
    
    //TO TEST 14 urgent time slots + Strategy 1, FCFS
    public static ArrayList<int[]> testSignificanceSlots14(){
        
        int[] day1 = new int[]{ 210, 225, 525 }; 
        int[] day2 = new int[]{ 210, 225, 525 };
        int[] day3 = new int[]{ 210, 225, 525 };
        int[] day4 = new int[]{ 225 };
        int[] day5 = new int[]{ 210, 225, 525 };
        int[] day6 = new int[]{ 225 };
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
    }
    
    //TO TEST 16 urgent time slots + Strategy 1, FCFS
    public static ArrayList<int[]> testSignificanceSlots16(){
        
        int[] day1 = new int[]{ 210, 225, 525 }; 
        int[] day2 = new int[]{ 210, 225, 525 };
        int[] day3 = new int[]{ 210, 225, 525 };
        int[] day4 = new int[]{ 210, 225 };
        int[] day5 = new int[]{ 210, 225, 525 };
        int[] day6 = new int[]{ 210, 225 };
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
    }
    
     //TO TEST 16 urgent time slots + Strategy 1, FCFS
    public static ArrayList<int[]> testSignificanceSlots18(){
        
        int[] day1 = new int[]{ 210, 225, 510, 525 }; 
        int[] day2 = new int[]{ 210, 225, 525 };
        int[] day3 = new int[]{ 210, 225, 510, 525 };
        int[] day4 = new int[]{ 210, 225 };
        int[] day5 = new int[]{ 210, 225, 525 };
        int[] day6 = new int[]{ 210, 225 };
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
    }
    ////////////////////////////////////////////////////////////////////////////
    
    
    //TEST VOOR JUSTINE
    public static ArrayList<int[]> getUrgentSlotsStrategy100(){
    
        int[] day1 = new int[]{ 210, 225, 525 }; 
        int[] day2 = new int[]{ 210, 225, 525 };
        int[] day3 = new int[]{ 210, 225, 525 };
        int[] day4 = new int[]{ 225 };
        int[] day5 = new int[]{ 210, 225, 525 };
        int[] day6 = new int[]{ 225 };
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
    
    
    ///////////////////////////////////////////////////////////////////////
    //STRATEGY2
    public static ArrayList<int[]> getUrgentSlotsStrategy2with10slots(){
    
        int[] day1 = new int[]{ 150, 375 }; 
        int[] day2 = new int[]{ 150, 375 };
        int[] day3 = new int[]{ 150, 375 };
        int[] day4 = new int[]{ 150 };
        int[] day5 = new int[]{ 150, 375 };
        int[] day6 = new int[]{ 150 };
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
    
    
    public static ArrayList<int[]> getUrgentSlotsStrategy2with12slots(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 105, 225, 405 }; 
        int[] day2 = new int[]{ 150, 375 };
        int[] day3 = new int[]{ 105, 225, 405 };
        int[] day4 = new int[]{ 150 };
        int[] day5 = new int[]{ 150, 375 };
        int[] day6 = new int[]{ 150 };
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
 
    public static ArrayList<int[]> getUrgentSlotsStrategy2with14slots(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 105, 225, 405 }; 
        int[] day2 = new int[]{ 105, 225, 405 };
        int[] day3 = new int[]{ 105, 225, 405 };
        int[] day4 = new int[]{ 150 };
        int[] day5 = new int[]{ 105, 225, 405 };
        int[] day6 = new int[]{ 150 };
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
 
    
    ///////////////////////////////////////////////////////////////////////////
    //STRATEGY 3   
    
    public static ArrayList<int[]> getUrgentSlotsStrategy3with10slots(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 90, 195 }; 
        int[] day2 = new int[]{ 90, 195 };
        int[] day3 = new int[]{ 90, 195 };
        int[] day4 = new int[]{ 90 };
        int[] day5 = new int[]{ 90, 195 };
        int[] day6 = new int[]{ 90 };
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
 
    public static ArrayList<int[]> getUrgentSlotsStrategy3with12slots(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 90, 195, 360 }; 
        int[] day2 = new int[]{ 90, 195 };
        int[] day3 = new int[]{ 90, 195, 360 };
        int[] day4 = new int[]{ 90 };
        int[] day5 = new int[]{ 90, 195 };
        int[] day6 = new int[]{ 90 };
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
 
    public static ArrayList<int[]> getUrgentSlotsStrategy3with14slots(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 90, 195, 360 }; 
        int[] day2 = new int[]{ 90, 195, 360 };
        int[] day3 = new int[]{ 90, 195, 360 };
        int[] day4 = new int[]{ 90 };
        int[] day5 = new int[]{ 90, 195, 360 };
        int[] day6 = new int[]{ 90 };
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
    
}
