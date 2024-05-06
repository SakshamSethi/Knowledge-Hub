/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.tech.blog.servlets;

import com.tech.blog.dao.lessonsDao;
import com.tech.blog.entities.lesson;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;
import com.tech.blog.helper.Helper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author 91821
 */
@MultipartConfig
public class AddLessonServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        String Title = request.getParameter("Title");
        String Tag = request.getParameter("Tag");
        String knowledgeUnitCategory = request.getParameter("KnowledgeUnitCategory");
        String knowunit = request.getParameter("knowunit");
        String Problem = request.getParameter("Problem");
        String Solve = request.getParameter("Solve");
        String Root = request.getParameter("Root");
         String learnt = request.getParameter("learnt");
          String area = request.getParameter("area");
      
          System.out.println("Title"+Title);
          System.out.println("Title"+Tag);
          System.out.println("Title"+knowledgeUnitCategory);
          System.out.println("Title"+knowunit);
          System.out.println("Title"+Problem);
          System.out.println("Title"+Solve);
          System.out.println("Title"+Root);
          System.out.println("Title"+learnt);
          System.out.println("Title"+area);
          
        
        lesson l = new lesson(Title,Tag,knowledgeUnitCategory,knowunit,Problem,Solve,Root,learnt,area);
        lessonsDao dao = new lessonsDao(ConnectionProvider.getConnection());
        
        boolean saved = dao.saveLesson(l);
        
        System.out.println("is saved?"+saved);
        
        if (saved) {
            out.print("saved");
        } else {
            out.print("error");
        }
    }
}

    
    
         
       
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
