package ru.twice.forge.udalit;

import java.util.concurrent.TimeUnit;

/**
 * Created by twice on 24.03.16.
 */
public class Sleeper {
    public static void sleep(long time){
        try{
            TimeUnit.MILLISECONDS.sleep(time);
        }catch(InterruptedException e){e.printStackTrace();}
    }
}
