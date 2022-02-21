package DBDAO;

import Beans.Repair;
import DAO.Dao;
import SQL.ConnectionPool;
import SQL.DButils;

import java.sql.*;
import java.util.*;

public class RepairsDB implements Dao {
    /*
    ███████╗ ██████╗ ██╗
    ██╔════╝██╔═══██╗██║
    ███████╗██║   ██║██║
    ╚════██║██║▄▄ ██║██║
    ███████║╚██████╔╝███████╗
    ╚══════╝ ╚══▀▀═╝ ╚══════╝
     */

    private static final String ADD_REPAIR = "INSERT INTO `droneLab`.`repairs` (`memo`,`sn`,`entered`,`readyOn`, `isImportant`, `isPoped`) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_REPAIR = "UPDATE `droneLab`.`repairs` set memo=?, readyOn=?, isImportant=?, isPoped=? WHERE id=?";
    private static final String GET_ONE_REPAIR_BY_ID = "SELECT * FROM `droneLab`.`repairs` WHERE id=?";
    private static final String GET_ALL_REPAIR_BY_SN = "";
    private static final String GET_ALL_REPAIR = "SELECT * FROM `droneLab`.`repairs`";
    private static final String DELETE_BY_ID = "DELETE FROM `droneLab`.`repairs` where id=?";
    private static final String IS_EXISTS = "SELECT count(*) FROM school.lecturer WHERE name=? AND email=?";
    //connection to the data base
    Connection connection;

    public RepairsDB() {
        try {
            ConnectionPool.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Boolean addRepair(Repair repair) throws SQLException {
        try {
            //get connection to the database
            connection = ConnectionPool.getInstance().getConnection();

            //create a prepared sql statement
            PreparedStatement statement = connection.prepareStatement(ADD_REPAIR);
            statement.setString(1, repair.getMemo());
            statement.setString(2, repair.getSn());
            statement.setDate(3, repair.getEntered());
            statement.setDate(4, repair.getReadyOn());
            statement.setBoolean(5, repair.isImportent());
            statement.setBoolean(6, repair.isPoped());
            statement.execute();
            return true;
        } catch (InterruptedException | SQLException e) {
            return false;
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    private boolean isExists(String name, String email) {
        try {
            //open connection
            connection = ConnectionPool.getInstance().getConnection();
            //prepare sql statment
            PreparedStatement statement = connection.prepareStatement(IS_EXISTS);
            //set name
            statement.setString(1, name);
            //set email
            statement.setString(2, email);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.getInt(1) > 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    //better repair method....
    public boolean addRepair2(Repair repair) throws SQLException {
        //create an empty map, int as key objects as value
        Map<Integer, Object> params = new HashMap<>();
        //our params
        //get into place 1, object of getMemo(string)
        params.put(1, repair.getMemo());
        params.put(2, repair.getSn());
        params.put(3, repair.getEntered());
        params.put(4, repair.getReadyOn());
        params.put(5, repair.isImportent());
        params.put(6, repair.isPoped());
        return DButils.runBetterQuery(ADD_REPAIR, params);
    }


    public boolean updateDrone(Repair repair) throws SQLException {
        try {
            //get connection to the database
            connection = ConnectionPool.getInstance().getConnection();

            //create a prepared sql statement
            PreparedStatement statement = connection.prepareStatement(UPDATE_REPAIR);
            statement.setString(1, repair.getMemo());
            statement.setDate(2, repair.getReadyOn());
            statement.setBoolean(3, repair.isImportent());
            statement.setBoolean(4, repair.isPoped());
            statement.setInt(5, repair.getId());
            statement.execute();
            return true;
        } catch (InterruptedException | SQLException e) {
            return false;
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public List<Repair> getRepairList() {
        List<Repair> repairs = new ArrayList<>();

        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ALL_REPAIR);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Repair repair = new Repair(resultSet.getInt(1), resultSet.getDate(5), resultSet.getString(2), resultSet.getString(3), resultSet.getBoolean(6), resultSet.getBoolean(7));
                //System.out.println(repair);
                repairs.add(repair);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return repairs;
    }

    public Repair getSingleById(int id) {
        Repair repair = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ONE_REPAIR_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                repair = new Repair(resultSet.getInt("id"), resultSet.getDate("entered"), resultSet.getString("memo"), resultSet.getString("sn"), resultSet.getBoolean("isImportant"), resultSet.getBoolean("isPoped"));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return repair;
    }

    public void deleteRepairById(int id) {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setInt(1, id);
            statement.execute();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public Set<Repair> getRepairSet() {
        return null;
    }


}
