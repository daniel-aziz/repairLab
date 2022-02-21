package DAO;

import Beans.Repair;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface Dao {
    //add a new repair to DB
    Boolean addRepair(Repair repair) throws SQLException;
    //get list of repairs as list collection
    List<Repair> getRepairList();
    //get a list of repairs as Set collection
    Set<Repair> getRepairSet();
}
