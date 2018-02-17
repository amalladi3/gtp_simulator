import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulator {
    public static final int TIME_PERIOD_LENGTH = 4; //minutes
    public static final int NUM_TIME_PERIODS = 15;

    public static final int SHORT_JOB_LENGTH = 2; //minute
    public static final int LONG_JOB_LENGTH = 5;

    public static final int LONG_JOB_PERCENTAGE = 35; //35 out of every 100 jobs are long

    public static final int USER_PERCENTAGE = 100; //0-100, chance of user showing up in a given  minute


    public static void main(String[] args) {
        Random rand = new Random();
        int numPrinters = Resource.PRINTER_COUNT;
        int randUser = 0;
        Resource printers = new Resource();
        Queue queue = new Queue();
        User[] usersActive = new User[numPrinters];
        List<User> completed = new ArrayList<User>();
        System.out.println("Simulation Started, set up successful!");
        System.out.println("--------------------------------------------");

        for (int i = 0; i < TIME_PERIOD_LENGTH*NUM_TIME_PERIODS; i++) {
            randUser = rand.nextInt(100);
            if (randUser < USER_PERCENTAGE) {
                boolean isColor = (rand.nextInt(10) < 2);
                boolean isShort = (rand.nextInt(100) < LONG_JOB_PERCENTAGE);
                User user = new User(isShort, isColor, i);
                System.out.println("New user enters queue at time " + i
                        + " (Short:" + isShort + ", Color:" + isColor + ")");
                user.setEntryTime(i);
                queue.enqueue(user);
            }

            for (int j = 0; j < numPrinters; j++) {
                int duration = LONG_JOB_LENGTH;
                if (usersActive[j] != null) {
                    if (usersActive[j].isShortJob()) {
                        duration = SHORT_JOB_LENGTH;
                    }
                    if (i >= (usersActive[j].getEntryTime() + duration)) {
                        printers.release(j);
                        usersActive[j].setExitTime(i);
                        completed.add(usersActive[j]);
                        usersActive[j] = null;
                    }
                }
            }



            while ((printers.getNumFree() > 0) && queue.getNumWaiting() > 0) {
                User user = queue.dequeue();
                user.setPrinterID(printers.claim(user));
                usersActive[user.getPrinterID()] = user;
            }
        }
        System.out.println("--------------------------------------------");
        System.out.println(queue.getNumWaiting() + "Users left waiting");
        System.out.println("--------------------------------------------");


        double totalWaitTime = 0;
        double totalWaitTimeShortJob = 0;
        double totalWaitTimeLongJob = 0;
        double totalWaitTimeColor = 0;
        double totalWaitTimeBW = 0;

        int numShort = 0;
        int numColor = 0;

        for (int i = 0; i < completed.size(); i++) {
            User user = completed.get(i);
            int waitTime = user.getExitTime() - user.getEntryTime();
            totalWaitTime += (waitTime);

            if (user.isShortJob()) {
                totalWaitTimeShortJob += waitTime;
                numShort++;
            } else {
                totalWaitTimeLongJob += waitTime;
            }

            if (user.isColorJob()) {
                totalWaitTimeColor += waitTime;
                numColor++;
            } else {
                totalWaitTimeBW += waitTime;
            }
        }
        double averageOverall = totalWaitTime / completed.size();
        double averageShort = totalWaitTimeShortJob / numShort;
        double averageLong = totalWaitTimeLongJob / (completed.size() - numShort);
        double averageColor = totalWaitTimeColor / numColor;
        double averageBW = totalWaitTimeBW / (completed.size() - numColor);


        System.out.println("Users who did finish their jobs waited an average of " + averageOverall + " minutes");
        System.out.println("Users who printed in color waited an average of " + averageColor + " minutes");
        System.out.println("Users who printed in BW waited an average of " + averageBW + " minutes");
        System.out.println("Users who printed short jobs waited an average of " + averageShort + " minutes");
        System.out.println("Users who printed long jobs waited an average of " + averageLong + " minutes");



    }




}


/*
if user comes in, add to queue


if user is done with printer, release


if printer is free, claim
 */
