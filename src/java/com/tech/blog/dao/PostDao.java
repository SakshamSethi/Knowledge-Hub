/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tech.blog.dao;

import com.tech.blog.entities.Category;
import com.tech.blog.entities.Post;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 91821
 */
public class PostDao {

    Connection con;

    public PostDao(Connection con) {
        this.con = con;
    }

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> list = new ArrayList<Category>();

        try {

            String q = "Select* from categories";
            Statement st = this.con.createStatement();
            ResultSet set = st.executeQuery(q);

            while (set.next()) {
                int cid = set.getInt("cid");
                String name = set.getString("name");
                String desString = set.getString("description");

                Category c = new Category(cid, name, desString);
                list.add(c);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
        return list;
    }

    public boolean savePost(Post p) {
        boolean f = false;
        try {

            String q = "insert into posts(pTitle,pContent,pCode,pPic,catId,userId) values(?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1, p.getpTitle());
            pstmt.setString(2, p.getpContent());
            pstmt.setString(3, p.getpCode());
            pstmt.setString(4, p.getpPic());
            pstmt.setInt(5, p.getCatId());
            pstmt.setInt(6, p.getUserId());
            pstmt.executeUpdate();
            f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }

    public List<Post> getALlPosts(int currentPage) {
        List<Post> list = new ArrayList<>();
        int offset = (currentPage - 1) * 6;
        //Fetch all the posts from database 
        try {

            PreparedStatement p = con.prepareStatement("select* from posts order by pid desc limit ? offset ?");
            p.setInt(1, 6);
            p.setInt(2, offset);
            ResultSet set = p.executeQuery();

            while (set.next()) {
                int pid = set.getInt("pid");
                String pTitle = set.getString("pTitle");
                String pContent = set.getString("pContent");
                String pCode = set.getString("pCode");
                String pPic = set.getString("pPic");
                Timestamp date = set.getTimestamp("pDate");
                int catId = set.getInt("catId");
                int userId = set.getInt("userId");

                Post post = new Post(pid, pTitle, pContent, pCode, pPic, date, catId, userId);
                list.add(post);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Post> getPostByCatId(int catId , int currentPage) {
        List<Post> list = new ArrayList<>();

        //Fetch all the posts from database belonging to a specific Category 
        try {
int offset = (currentPage - 1) * 4;
            PreparedStatement p = con.prepareStatement("select* from posts where catId = ? order by pid desc limit ? offset ?");
            p.setInt(1, catId);
            p.setInt(2, 4);
            p.setInt(3, offset );
            ResultSet set = p.executeQuery();

            while (set.next()) {
                int pid = set.getInt("pid");
                String pTitle = set.getString("pTitle");
                String pContent = set.getString("pContent");
                String pCode = set.getString("pCode");
                String pPic = set.getString("pPic");
                Timestamp date = set.getTimestamp("pDate");

                int userId = set.getInt("userId");

                Post post = new Post(pid, pTitle, pContent, pCode, pPic, date, catId, userId);
                list.add(post);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Post getPostByPostId(int postId) {
        Post post = null;

        String q = "select* from posts where pid = ?";
        try {

            PreparedStatement p = this.con.prepareStatement(q);
            p.setInt(1, postId);

            ResultSet set = p.executeQuery();
            if (set.next()) {
                String pTitle = set.getString("pTitle");
                String pContent = set.getString("pContent");
                String pCode = set.getString("pCode");
                String pPic = set.getString("pPic");
                Timestamp date = set.getTimestamp("pDate");
                int catId = set.getInt("catId");
                int userId = set.getInt("userId");

                post = new Post(pTitle, pContent, pCode, pPic, date, catId, userId);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return post;
    }
    public List<Post> getLatestPosts() {
        List<Post> list = new ArrayList<>();

        //Fetch all the posts from database belonging to a specific Category 
        try {

            PreparedStatement p = con.prepareStatement("select* from posts order by pDate desc limit 6 ");
            
            ResultSet set = p.executeQuery();

            while (set.next()) {
                int pid = set.getInt("pid");
                String pTitle = set.getString("pTitle");
                String pContent = set.getString("pContent");
                String pCode = set.getString("pCode");
                String pPic = set.getString("pPic");
                Timestamp date = set.getTimestamp("pDate");

                int userId = set.getInt("userId");
                int catId = set.getInt("catId");
                Post post = new Post(pid, pTitle, pContent, pCode, pPic, date, catId, userId);
                list.add(post);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
       public List<Post> getPostByUserId(int userId,int cid) {
        List<Post> list = new ArrayList<>();

        //Fetch all the posts from database belonging to a specific User 
        try {

            PreparedStatement p = con.prepareStatement("select* from posts where userId = ? and catId= ?");
            p.setInt(1, userId);
            p.setInt(2, cid);
            ResultSet set = p.executeQuery();

            while (set.next()) {
                int pid = set.getInt("pid");
                String pTitle = set.getString("pTitle");
                String pContent = set.getString("pContent");
                String pCode = set.getString("pCode");
                String pPic = set.getString("pPic");
                Timestamp date = set.getTimestamp("pDate");

               

                Post post = new Post(pid, pTitle, pContent, pCode, pPic, date, cid, userId);
                list.add(post);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
        public List<Post> getAllPostByUserId(int userId) {
        List<Post> list = new ArrayList<>();

        //Fetch all the posts from database belonging to a specific User 
        try {

            PreparedStatement p = con.prepareStatement("select* from posts where userId = ?" );
            p.setInt(1, userId);
           
            ResultSet set = p.executeQuery();

            while (set.next()) {
                int pid = set.getInt("pid");
                String pTitle = set.getString("pTitle");
                String pContent = set.getString("pContent");
                String pCode = set.getString("pCode");
                String pPic = set.getString("pPic");
                Timestamp date = set.getTimestamp("pDate");
                int cid = set.getInt("catId");
               

                Post post = new Post(pid, pTitle, pContent, pCode, pPic, date, cid, userId);
                list.add(post);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
        
       public boolean deletePost(int postId) {
    boolean success = false;
        
    System.out.println(postId);
    try {
        PreparedStatement p = con.prepareStatement("delete from posts where pid = ?");
        p.setInt(1, postId);
        
        // Use executeUpdate() to execute the DELETE statement and check the affected rows.
        // executeUpdate() returns the number of rows affected, so we can use it to determine success.
        int rowsAffected = p.executeUpdate();

        if (rowsAffected > 0) {
            success = true;
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return success;
}
       public boolean updatePost(int postId , String postTitle , String postContetn , String postCode) {
    boolean success = false;
        
   
    try {
        PreparedStatement p = con.prepareStatement("update posts set pTitle=? , pContent=? ,pCode=? where pid = ?");
      
        p.setString(1, postTitle);
        p.setString(2, postContetn);
        p.setString(3, postCode);
        p.setInt(4, postId);
        
        // Use executeUpdate() to execute the DELETE statement and check the affected rows.
        // executeUpdate() returns the number of rows affected, so we can use it to determine success.
        int rowsAffected = p.executeUpdate();

        if (rowsAffected > 0) {
            success = true;
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return success;
}
           public int getNumberOfPosts() {
        int numberOfPosts = 0;
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
          
            String query = "SELECT COUNT(*) AS numPosts FROM posts";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                numberOfPosts = rs.getInt("numPosts");
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

        return numberOfPosts;
    }

}
