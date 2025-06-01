package ru.astondevs;

import ru.astondevs.week3.MyIO;
import ru.astondevs.week3.MySQLConnection;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        /*MyIO.rewriteToFile("так я чет не понял");
        MyIO.writeToFile("так я чет не понял");*/
        MySQLConnection mySQLConnection = new MySQLConnection();
        Connection connection = mySQLConnection.getConnection();
    }
}