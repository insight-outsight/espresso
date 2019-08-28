package org.ootb.espresso.facilities;


public class IDGenerator {

    public static Long next() {
        return System.currentTimeMillis()/100-15441612620L;
    }

    public static void main(String[] args) {
        System.out.println(next());
        System.out.println(next());
        System.out.println(next());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis()/1000);
        System.out.println(System.currentTimeMillis()/1000-1544161262);
        System.out.println(16000000000L-15441612620L);
    }
    
}
