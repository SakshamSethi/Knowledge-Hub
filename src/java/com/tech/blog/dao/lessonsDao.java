/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tech.blog.dao;

import com.tech.blog.entities.lesson;
import com.tech.blog.helper.ConnectionProvider;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Part;

public class lessonsDao {

    Connection con;

    public lessonsDao(Connection con) {
        this.con = con;
    }

    public boolean saveLesson(lesson less) {
        boolean f = false;

        try {

//             
            //query
            String q = "insert into lesson(title,tag,knowledgeUnitCategory,problem,solve,root,lessonLearnt,knowledgeUnit,areaOfOrganization) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1, less.getTitle());
            pstmt.setString(2, less.getTag());
            pstmt.setString(3, less.getknowledgeUnitCategory());
            pstmt.setString(4, less.getProblem());
            pstmt.setString(5, less.getSolve());
            pstmt.setString(6, less.getRoot());
            pstmt.setString(7, less.getknowunit());
            pstmt.setString(8, less.getarea());
            pstmt.setString(9, less.getlearnt());

            
             
            
            
            //firing query
            int row = pstmt.executeUpdate();

            if (row > 0) {

                f = true;

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return f;

    }

    public List<lesson> getAllLessons() {
        List<lesson> Lessons = new ArrayList<>();

        try {

            Connection con = ConnectionProvider.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from lesson");

            while (rs.next()) {
                String lTitle = rs.getString("title");
                String lTag = rs.getString("tag");
                String lknowledgeUnitCategory = rs.getString("knowledgeUnitCategory");
                String lknowunit = rs.getString("knowledgeUnit");
                String lProblem = rs.getString("problem");
                String lSolve = rs.getString("solve");
                String lRoot = rs.getString("root");
                String llearnt = rs.getString("lessonLearnt");
                String larea = rs.getString("areaOfOrganization");

               Lessons.add(new lesson(lTitle, lTag, lknowledgeUnitCategory, lknowunit, lProblem, lSolve, lRoot, llearnt, larea));
            }

        } catch (Exception e) {

        }

        return Lessons;

    }

    public int getNumberOfLessons() {
        int count = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            String query = "SELECT COUNT(*) AS numLess FROM lesson";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("numLess");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return count;
    }

}
