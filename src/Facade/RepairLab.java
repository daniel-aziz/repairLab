package Facade;

import Beans.Repair;
import DBDAO.RepairsDB;
import Threads.RepairScanner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class RepairLab {
    //fields
    //get input from user
    private Scanner scanner = new Scanner(System.in);
    //list of repairs with sort by eta time
    private List<Repair> repairs;
    //a thread that will use to run background service
    //each time a drone is ready, pop up a message
    RepairScanner repairScanner;

    //Repairs DB
    RepairsDB rdb;

    //c'tor
    public RepairLab() {
        repairs = new ArrayList<>();
        repairScanner = new RepairScanner(repairs);
        //start the task
        new Thread(repairScanner).start();
        //open connection to DB
        rdb = new RepairsDB();
        //run the menu...
        repairMenu();

    }

    private void repairMenu() {
        int choice=0; //1 create , 2 view, 3 quit
        do{
            System.out.println("REPAIR MENU (1-3)");
            System.out.println("-----------------");
            System.out.println("1 - Create a new repair");
            System.out.println("2 - View Repairs");
            System.out.println("3 - quit");
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    handleNewRepair();
                    break;

                case 2:
                    handleRepairList();
                    break;

                case 3:
                    handleEndProgram();
                    break;

                default:
                    System.out.println("Wrong choice");
            }
        } while (choice!=3);
    }

    private void handleNewRepair() {
        System.out.println("\nCreate a new repair (ETA):\n-----------------------");
        //enter date tmie for ready on (eta for fix)
        System.out.println("enter a day of month:");
        int day = scanner.nextInt();
        System.out.println("enter month:");
        int month = scanner.nextInt();
        System.out.println("enter year:");
        int year=scanner.nextInt();
        System.out.println("enter hours (0-23):");
        int hour = scanner.nextInt();
        System.out.println("enter monutes (0-59):");
        int minutes = scanner.nextInt();
        System.out.println("enter memo:");
        scanner.nextLine(); //clean the buffer
        String memo = scanner.nextLine();
        System.out.println("Enter S/N:");
        String sn = scanner.nextLine();
        System.out.println("Set as important ? (y/n)");
        char c = scanner.next().charAt(0);
        boolean importent = (c == 'Y' || c== 'y');

        //handle ready on set the estimated date of fix to user input date
        Calendar readyOn = Calendar.getInstance();
        readyOn.set(Calendar.DAY_OF_MONTH,day);
        readyOn.set(Calendar.MONTH,month);
        readyOn.set(Calendar.YEAR,year);
        readyOn.set(Calendar.HOUR,hour);
        readyOn.set(Calendar.MINUTE,minutes);

        //Repair repair = new Repair(readyOn,memo,sn,importent,false);

        //local
        //repairs.add(repair);

        //SQL
    }

    private void handleRepairList() {
        /*
        //junior
        for (Repair item:repairs){
            System.out.println(item);
        }

         */

        //senios - LIKE A BOSS
        repairs.forEach(System.out::println);
    }

    private void handleEndProgram() {
        SQL.ConnectionPool cp = null;
        try {
            /*
            cp = SQL.ConnectionPool.getInstance();
            cp.closeAllConnection();
            */

            //Like a BOSS
            SQL.ConnectionPool.getInstance().closeAllConnection();

        } catch (SQLException | InterruptedException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("Chao Bella !!!!");
        System.exit(200);

    }


    //method

}
