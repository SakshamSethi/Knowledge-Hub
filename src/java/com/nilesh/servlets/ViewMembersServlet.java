package com.nilesh.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

 
public class ViewMembersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        List<String> members = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/group_management", "DESKTOP-O6BU8RH", "");
            PreparedStatement statement = connection.prepareStatement("SELECT member_name FROM members WHERE group_id = ?");
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                members.add(resultSet.getString("member_name"));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("members", members);
        request.getRequestDispatcher("viewMembers.jsp").forward(request, response);
    }
}
