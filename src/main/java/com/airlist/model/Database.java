package com.airlist.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.sql.*;

public class Database {

    private static final String URL = System.getenv("DATABASE_URL");

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException("PostgreSQL JDBC Driver not found");
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static List<String[]> retrieveRecords() {
        List<String[]> records = new ArrayList<>();
        String sql = "SELECT * FROM companies";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String[] record = new String[8];
                record[0] = resultSet.getString("id");
                record[1] = resultSet.getString("arrival_date");
                record[2] = resultSet.getString("company_name");
                record[3] = resultSet.getString("next_date");
                record[4] = resultSet.getString("status");
                record[5] = resultSet.getString("tag");
                record[6] = resultSet.getString("role");
                record[7] = resultSet.getString("description");
                records.add(record);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        records.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] record1, String[] record2) {
                int tag1 = Integer.parseInt(record1[5]);
                int tag2 = Integer.parseInt(record2[5]);

                if (tag1 == tag2) {
                    return 0;
                }
                if (tag1 == 2) {
                    return -1;
                }
                if (tag2 == 2) {
                    return 1;
                }
                if (tag1 == 1) {
                    return -1;
                }
                if (tag2 == 1) {
                    return 1;
                }
                return Integer.compare(tag1, tag2);
            }
        });

        return records;
    }

    public static boolean insertRecord(String arrivalDate, String companyName, String nextDate, String status, int tag, String role, String description) {
        String sql = "INSERT INTO companies (arrival_date, company_name, next_date, status, tag, role, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, arrivalDate);
            statement.setString(2, companyName);
            statement.setString(3, nextDate);
            statement.setString(4, status);
            statement.setInt(5, tag);
            statement.setString(6, role);
            statement.setString(7, description);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateRecord(int id, String arrivalDate, String companyName, String nextDate, String status, int tag, String role, String description) {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE companies SET ");
        List<Object> parameters = new ArrayList<>();

        if (arrivalDate != null) {
            sqlBuilder.append("arrival_date = ?, ");
            parameters.add(arrivalDate);
        }
        if (companyName != null) {
            sqlBuilder.append("company_name = ?, ");
            parameters.add(companyName);
        }
        if (nextDate != null) {
            sqlBuilder.append("next_date = ?, ");
            parameters.add(nextDate);
        }
        if (status != null) {
            sqlBuilder.append("status = ?, ");
            parameters.add(status);
        }
        if (tag != 0) { // Assuming 0 is not a valid tag value, change as needed
            sqlBuilder.append("tag = ?, ");
            parameters.add(tag);
        }
        if (role != null) {
            sqlBuilder.append("role = ?, ");
            parameters.add(role);
        }
        if (description != null) {
            sqlBuilder.append("description = ?, ");
            parameters.add(description);
        }

        sqlBuilder.setLength(sqlBuilder.length() - 2);

        sqlBuilder.append(" WHERE id = ?");
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlBuilder.toString())) {

            // Set parameters
            int parameterIndex = 1;
            for (Object parameter : parameters) {
                statement.setObject(parameterIndex++, parameter);
            }
            statement.setInt(parameterIndex, id);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean login(String username, String password) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count == 1) {
                        System.out.println("Login successful for username: " + username + " and password: " + password);
                        return true;
                    } else {
                        System.out.println("No user found for username: " + username + " and password: " + password);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
