/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tech.blog.dao;

import com.repo.helper.helper;
import com.tech.blog.entities.Repository;
import com.tech.blog.helper.ConnectionProvider;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

/**
 *
 * @author 91821
 */
public class KnowledgeRepoDao {

    Connection con;

    public KnowledgeRepoDao(Connection con) {
        this.con = con;
    }

        public List<Repository> getLatestDocList() {
        List<Repository> list = new ArrayList<>();

        //Fetch all the posts from database belonging to a specific Category 
        try {

            PreparedStatement p = con.prepareStatement("select* from repository order by Date desc limit 6 ");
            
            ResultSet set = p.executeQuery();

            while (set.next()) {
                int id = set.getInt("id");
                String Title = set.getString("Title");
                Timestamp date = set.getTimestamp("Date");
                 String FileName = set.getString("File");
 
                Repository repo = new  Repository(id, Title, date,FileName);
                list.add(repo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
    
    
    
    public boolean saveDocument(Repository repo) {
        boolean f = false;

        try {

//                Thread.sleep(3000);
            //query
            String q = "insert into repository(Title,Tags,File,Remarks,userId) values(?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1, repo.getTitle());
            pstmt.setString(2, repo.getTag());
            pstmt.setString(3, repo.getFileName());
            pstmt.setString(4, repo.getRemark());
            pstmt.setInt(5, repo.getUserId());

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

    public List<Repository> getAllDocuments() {
        List<Repository> Documents = new ArrayList<>();

        try {

            Connection con = ConnectionProvider.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from repository");

            while (rs.next()) {
                String dTitle = rs.getString("Title");
                String dTag = rs.getString("Tags");
                String dFile = rs.getString("File");
                String dRemarks = rs.getString("Remarks");
                Timestamp dateTime = rs.getTimestamp("Date");
                int userId = rs.getInt("userId");
                Documents.add(new Repository(dTitle, dFile, dTag, dRemarks, dateTime, userId));
            }

        } catch (Exception e) {

        }

        return Documents;

    }

    public int getNumberOfRepositories() {
        int count = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            String query = "SELECT COUNT(*) AS numRepos FROM repository";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("numRepos");
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

    public List<Repository> getDocumentsByUser(int userId) {
        List<Repository> Documents = new ArrayList<>();

        try {

            Connection con = ConnectionProvider.getConnection();

            PreparedStatement pstmt = con.prepareStatement("select* from repository where userId = ?");
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String dTitle = rs.getString("Title");
                String dTag = rs.getString("Tags");
                String dFile = rs.getString("File");
                String dRemarks = rs.getString("Remarks");
                Timestamp dateTime = rs.getTimestamp("Date");
                int docId = rs.getInt("id");
                Documents.add(new Repository(docId, userId, dTitle, dFile, dTag, dRemarks, dateTime));
            }

        } catch (Exception e) {

        }

        return Documents;

    }

    public boolean deleteDocumentById(int docId) {
        boolean f = false;
        try {

            PreparedStatement pstmt = con.prepareStatement("delete from repository where id = ?");
            pstmt.setInt(1, docId);

            int row = pstmt.executeUpdate();

            if (row > 0) {

                f = true;

            }

        } catch (SQLException ex) {
            Logger.getLogger(KnowledgeRepoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

}
