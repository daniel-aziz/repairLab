package Tester;

import Beans.Repair;
import DBDAO.RepairsDB;
import SQL.ConnectionPool;
import SQL.DataBaseManager;
import Utils.ArtUtils;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class DBtest {
    public static void main(String[] args) throws SQLException {
        ConnectionPool.getInstance().openAllConnections();
        DataBaseManager.createDataBase();
        DataBaseManager.createTables();



      //  RepairsDB rdb = new RepairsDB();
         /*
        Repair repair = new Repair();
        repair.setPoped(false);
        repair.setEntered(Date.valueOf("2021-05-12"));
        repair.setImportent(false);
        repair.setSn("34534");
        repair.setMemo("broken body");
        repair.setReadyOn(Date.valueOf("2021-06-09"));

        rdb.addRepair(repair);
        */

        /*
        List<Repair> repairs = rdb.getRepairList();
        repairs.forEach(System.out::println); // LIKE A BOSS

        */

        //Repair repair = rdb.getSingleById(3);
        //System.out.println(repair);

//        rdb.deleteRepairById(3);
//        List<Repair> repairs = rdb.getRepairList();
//        repairs.forEach(System.out::println); // LIKE A BOSS
//
//
//
//        System.out.println(ArtUtils.finished);

    }
}
