package SQL;

import java.sql.SQLException;

public class DataBaseManager {
    //connection DB
    public static final String URL="jdbc:mysql://localhost:3306?createDatabaseIfNotExist=FALSE";
    public static final String USER_NAME="root";
    public static final String PASSWORD="12345678";

    //create & drop database
    private static final String CREATE_DB="CREATE SCHEMA if not exists droneLab";
    private static final String DROP_DB="DROP droneLab";

    //create & drop tables
    private static final String CREATE_TABLE="CREATE TABLE if not exists `droneLab`.`repairs` " +
            "(`id` INT NOT NULL AUTO_INCREMENT," +
            "`memo` VARCHAR(150) NOT NULL," +
            "`sn` VARCHAR(50) NOT NULL," +
            "`entered` DATE NOT NULL," +
            "`readyOn` DATE NOT NULL," +
            "`isImportant` BOOLEAN NOT NULL," +
            "`isPoped` BOOLEAN NOT NULL," +
            "PRIMARY KEY (`id`));";

    public static void createDataBase(){
        try {
            DButils.runQuery(CREATE_DB);
        } catch (SQLException  throwables) {
            throwables.printStackTrace();
        }
    }

    public static void createTables(){
        try {
            DButils.runQuery(CREATE_TABLE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
