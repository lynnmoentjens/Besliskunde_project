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
    
    public static ArrayList<int[]> getUrgentSlotsStrategy1(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 225,240, 540 }; // 3 urgent slots on day 1
        int[] day2 = new int[]{ 225,240, 540 };
        int[] day3 = new int[]{ 225,240, 540 };
        int[] day4 = new int[]{ 240};
        int[] day5 = new int[]{ 225,240, 540 };
        int[] day6 = new int[]{ 240};
               
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
    
    public static ArrayList<int[]> getUrgentSlotsStrategy2(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 120,240, 360 }; // 3 urgent slots on day 1
        int[] day2 = new int[]{ 120,240, 360 };
        int[] day3 = new int[]{ 120,240, 360 };
        int[] day4 = new int[]{ 240};
        int[] day5 = new int[]{ 120,240, 360 };
        int[] day6 = new int[]{ 240};
               
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
    
    
    public static ArrayList<int[]> getUrgentSlotsStrategy3(){
    
    //define here how many urgent slots a day and when
        int[] day1 = new int[]{ 105,210, 315 }; // 3 urgent slots on day 1
        int[] day2 = new int[]{ 105,210, 315 };
        int[] day3 = new int[]{ 105,210, 315 };
        int[] day4 = new int[]{ 105};
        int[] day5 = new int[]{ 105,210, 315 };
        int[] day6 = new int[]{ 105};
               
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
    
    
}
