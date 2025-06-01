package ru.astondevs.week3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USER = "Nikita";
    private static final String PASSWORD = "1234";

    private Connection connection;

    public MySQLConnection() {
        try {
            // Загружаем драйвер (не обязательно с JDBC 4.0+, драйвер загружается автоматически)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Устанавливаем соединение
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Подключение успешно");
        } catch (ClassNotFoundException e) {
            System.err.println("Драйвер MySQL не найден: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Ошибка при подключении: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Соединение закрыто");
            } catch (SQLException e) {
                System.err.println("Ошибка при закрытии соединения: " + e.getMessage());
            }
        }
    }

    // Пример использования
    public static void main(String[] args) {
        MySQLConnection db = new MySQLConnection();
        // Используйте db.getConnection() для выполнения запросов
        // ...
        db.close();
    }
}