package com.example.dod_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    // Укажите IP-адрес вашего сервера MySQL и имя вашей базы данных
    private static final String DB_URL = "http://localhost:3306/news";

    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    public List<news> getAllNews() {
        List<news> newsList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Устанавливаем соединение с базой данных
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM news";
            // Выполняем SQL-запрос
            rs = stmt.executeQuery(sql);
            // Обрабатываем результат запроса
            while (rs.next()) {
                // Создаем объект News на основе данных из результата запроса
                news news = new news(
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("image_url")
                );
                // Добавляем объект News в список новостей
                newsList.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Закрываем ресурсы
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return newsList;
    }

    // Другие методы для добавления, обновления, удаления новостей и т. д.
}
