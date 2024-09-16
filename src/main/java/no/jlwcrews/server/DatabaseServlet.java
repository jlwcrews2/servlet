package no.jlwcrews.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String sql = "select id, name from customer";
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app", "appuser", "pirate");
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();
            List<Customer> customerList = new ArrayList<>();

            while (result.next()) {
                customerList.add(
                        new Customer(
                                result.getInt("id"),
                                result.getString("name")));
            }
            resp.setContentType("application/json");
            resp.getWriter().write(objectMapper.writeValueAsString(customerList));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer newCust = objectMapper.readValue(req.getReader().lines().collect(Collectors.joining()), Customer.class);
        String sql = "insert into customer (id, name) values (?,?)";
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app", "appuser", "pirate");
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newCust.id());
            pstmt.setString(2, newCust.name());
            pstmt.execute();
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
