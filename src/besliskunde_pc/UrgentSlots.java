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
    
    //kan je zo afhankelijk van welke strategie je wilt de arrays ophalen 
    //en hou je toch al de mogelijke strategieen bij
    //hier staan enkele voorbeelden van mogelijke strategieen:
    
    public static ArrayList<int[]> getUrgentSlotsStrategy100(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 210,225, 525 }; // 3 urgent slots on day 1
        int[] day2 = new int[]{ 210,225, 525 };
        int[] day3 = new int[]{ 210,225, 525 };
        int[] day4 = new int[]{ 225};
        int[] day5 = new int[]{ 210,225, 525 };
        int[] day6 = new int[]{ 225};
               
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
    
    //STRATEGY 1 - 10 SLOTS
    public static ArrayList<int[]> getUrgentSlotsStrategy1(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 240,540 }; 
        int[] day2 = new int[]{ 240,540 };
        int[] day3 = new int[]{ 240,540 };
        int[] day4 = new int[]{ 240};
        int[] day5 = new int[]{ 240,540 };
        int[] day6 = new int[]{ 240};
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
    
    
    public static ArrayList<int[]> getUrgentSlotsStrategy2(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 225, 240,540 }; 
        int[] day2 = new int[]{ 240,540 };
        int[] day3 = new int[]{ 240,540 };
        int[] day4 = new int[]{ 240};
        int[] day5 = new int[]{ 240,540 };
        int[] day6 = new int[]{ 240};
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
 
    public static ArrayList<int[]> getUrgentSlotsStrategy3(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 225,240,540 }; 
        int[] day2 = new int[]{ 240,540 };
        int[] day3 = new int[]{ 240,540 };
        int[] day4 = new int[]{ 240};
        int[] day5 = new int[]{ 240,540 };
        int[] day6 = new int[]{ 225,240};
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
 
    public static ArrayList<int[]> getUrgentSlotsStrategy4(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 225,240,540 }; 
        int[] day2 = new int[]{ 225,240,540 };
        int[] day3 = new int[]{ 240,540 };
        int[] day4 = new int[]{ 240};
        int[] day5 = new int[]{ 240,540 };
        int[] day6 = new int[]{ 225,240};
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
    
    public static ArrayList<int[]> getUrgentSlotsStrategy5(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 225,240,540 }; 
        int[] day2 = new int[]{ 225,240,540 };
        int[] day3 = new int[]{ 225,240,540 };
        int[] day4 = new int[]{ 240};
        int[] day5 = new int[]{ 240,540 };
        int[] day6 = new int[]{ 225,240};
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
 
    public static ArrayList<int[]> getUrgentSlotsStrategy6(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 225,240,540 }; 
        int[] day2 = new int[]{ 225,240,540 };
        int[] day3 = new int[]{ 225,240,540 };
        int[] day4 = new int[]{ 225,240};
        int[] day5 = new int[]{ 240,540 };
        int[] day6 = new int[]{ 225,240};
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
 
    public static ArrayList<int[]> getUrgentSlotsStrategy7(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 225,240,540 }; 
        int[] day2 = new int[]{ 225,240,540 };
        int[] day3 = new int[]{ 225,240,540 };
        int[] day4 = new int[]{ 225,240};
        int[] day5 = new int[]{ 225,240,540 };
        int[] day6 = new int[]{ 225,240};
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
    
    public static ArrayList<int[]> getUrgentSlotsStrategy8(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 225,240,525,540 }; 
        int[] day2 = new int[]{ 225,240,540 };
        int[] day3 = new int[]{ 225,240,540 };
        int[] day4 = new int[]{ 225,240};
        int[] day5 = new int[]{ 225,240,540 };
        int[] day6 = new int[]{ 225,240};
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
    
    public static ArrayList<int[]> getUrgentSlotsStrategy9(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 225,240,525,540 }; 
        int[] day2 = new int[]{ 225,240,525,540 };
        int[] day3 = new int[]{ 225,240,540 };
        int[] day4 = new int[]{ 225,240};
        int[] day5 = new int[]{ 225,240,540 };
        int[] day6 = new int[]{ 225,240};
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
    
    public static ArrayList<int[]> getUrgentSlotsStrategy10(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 225,240,525,540 }; 
        int[] day2 = new int[]{ 225,240,525,540 };
        int[] day3 = new int[]{ 225,240,525,540 };
        int[] day4 = new int[]{ 225,240};
        int[] day5 = new int[]{ 225,240,540 };
        int[] day6 = new int[]{ 225,240};
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
    
    public static ArrayList<int[]> getUrgentSlotsStrategy11(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 225,240,525,540 }; 
        int[] day2 = new int[]{ 225,240,525,540 };
        int[] day3 = new int[]{ 225,240,525,540 };
        int[] day4 = new int[]{ 225,240};
        int[] day5 = new int[]{ 225,240,525,540 };
        int[] day6 = new int[]{ 225,240};
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
    
    //BEGINNEN BIJ STRATEGIE 2
    public static ArrayList<int[]> getUrgentSlotsStrategy12(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 165,390 }; 
        int[] day2 = new int[]{ 165,390 };
        int[] day3 = new int[]{ 165,390 };
        int[] day4 = new int[]{ 240 };
        int[] day5 = new int[]{ 165,390 };
        int[] day6 = new int[]{ 240 };
               
        ArrayList<int[]> urgentSlotsADay = new ArrayList<int[]>();
        
        urgentSlotsADay.add(day1);
        urgentSlotsADay.add(day2);
        urgentSlotsADay.add(day3);
        urgentSlotsADay.add(day4);
        urgentSlotsADay.add(day5);
        urgentSlotsADay.add(day6);
        
        return urgentSlotsADay;
}
    
    public static ArrayList<int[]> getUrgentSlotsStrategy13(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 120,240,420 }; 
        int[] day2 = new int[]{ 165,390 };
        int[] day3 = new int[]{ 165,390 };
        int[] day4 = new int[]{ 240 };
        int[] day5 = new int[]{ 165,390 };
        int[] day6 = new int[]{ 240 };
               
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
