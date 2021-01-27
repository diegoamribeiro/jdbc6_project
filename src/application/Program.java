package application;

import db.DB;
import db.DbException;

import java.sql.*;


public class Program {
    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;

        try{
            connection = DB.getConnection();

            connection.setAutoCommit(false);

            statement = connection.createStatement();

            int rows1 = statement.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

//            int x = 1;
//            if (x < 2){
//                throw new SQLException("Fake error! ");
//            }

            int rows2 = statement.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            connection.commit();

            System.out.println("Rows1 = " + rows1);
            System.out.println("Rows2 = " + rows2);


        }catch (SQLException sqlException){
            try {
                connection.rollback();
                throw new DbException("transaction rolledback! Caused by: " + sqlException.getMessage());
            } catch (SQLException throwables) {
                throw new DbException("Error try to rollback! Caused by: " + throwables.getMessage());
            }
        }finally {
            DB.closeStatement(statement);
            DB.closeConnection();
        }

    }
}
