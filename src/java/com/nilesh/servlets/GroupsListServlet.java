package com.nilesh.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

  
public class GroupsListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Group> groups = new ArrayList<>();
     
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/group_management", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `groups`");
            while (resultSet.next()) {
                int groupId = resultSet.getInt("id");
                String groupName = resultSet.getString("group_name");
                System.out.println("Group ID: " + groupId + ", Group Name: " + groupName);
                groups.add(new Group(groupId, groupName));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("groups", groups);
        request.getRequestDispatcher("groups-list.jsp").forward(request, response);
    }

    public class Group {
        private int id;
        private String groupName;

        public Group(int id, String groupName) {
            this.id = id;
            this.groupName = groupName;
        }

        public int getId() {
            return id;
        }

        public String getGroupName() {
            return groupName;
        }
    }
}
