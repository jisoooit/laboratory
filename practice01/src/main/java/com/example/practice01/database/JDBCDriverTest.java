package com.example.practice01.database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDriverTest {
    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";//"org.h2.Driver";
        String url ="jdbc:mysql://localhost:3306/jisu?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul"; // "jdbc:h2:~/JDBC";
        String user = "root"; //"sa";
        String password =  "";//"";

        try {
            Class.forName(driver); // JDBC 드라이버 객체 생성
            System.out.println("... JDBC Driver 로딩 성공!");

            DriverManager.getConnection(url, user, password); // JDBC 드라이버를 통해 DB 연결
            System.out.println("... H2 DB 연결 성공!");
        } catch(ClassNotFoundException e) {
            System.out.println("[ ClassNotFoundException ] JDBC Driver 로딩 실패!");
        } catch(SQLException e) {
            System.out.println("[ SQLException ] H2 DB 연결 실패!");
        } catch(Exception e) {
            System.out.println("[ Exception ] 예외 발생!");
        }
    }
}
