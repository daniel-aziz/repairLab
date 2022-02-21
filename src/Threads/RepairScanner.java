package Threads;

import Beans.Repair;
import Utils.ArtUtils;

import java.util.Calendar;
import java.util.List;

public class RepairScanner implements Runnable {

    private List<Repair> repairs;

    public RepairScanner(List<Repair> repairs) {
        this.repairs = repairs;
    }

    @Override
    public void run() {
        //thread sleep -> 1 minute
        while (true) {
            //iterate on entire collection
            for (Repair item:repairs){
                //first of all, did the repair was already shown (popped)
                if (!item.isPoped()){
                    //          01/01/2020                 01/01/2021
                    /*
                    if (item.getReadyOn().before(Calendar.getInstance())){
                        //print the drone info, it is ready
                        System.out.println(item);
                        // set popped to true, because we have already popped the info
                        item.setPoped(true);
                        //check if we have a VIP person (not natan)
                        if (item.isImportent()){
                            //thread !!!!!!!!!!!!!!!!!!!!!
                            //anonymous thread
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(30*1000);
                                        System.out.println(ArtUtils.vip);
                                        System.out.println(item);
                                        Thread.sleep(30*1000);
                                        System.out.println(ArtUtils.vip);
                                        System.out.println(item);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        }
                    }*/
                }
            }

            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException err) {
                System.out.println("Repair scanner has been stopped");
            }
        }
    }
}
