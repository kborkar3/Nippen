package com.cp.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class FileDownloadWithTimeSchedule
{

   /* public static void main(String args[])
    {
        // Displaying current time
        System.out.println("Time now is -> " + new Date());

        // Creating timer which executes once after five seconds
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TaskExampleRepeating(), 10000, 5000);
    }*/
}

class TaskExampleRepeating extends TimerTask

{
    public void run()
    {
        System.out.println(new Date() + " ->" + " I will repeat every 1 min seconds.");
        FTPDownloadFile downloadFile = new FTPDownloadFile();
        //downloadFile.downlaod();
    }
}