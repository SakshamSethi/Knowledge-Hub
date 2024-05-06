/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tech.blog.entities;

import java.io.Serializable;
 

/**
 *
 * @author user
 */
public class lesson {
    
    private  int id;
    private String Title, Tag ,Problem,Solve,Root,learnt,area,knowledgeUnitCategory,knowunit;

    public lesson(int id, String Title, String Tag,String knowledgeUnitCategory,String knowunit, String Problem, String Solve,String Root,String learnt,String area) {
        this.id = id;
        this.Title = Title;
        this.Tag = Tag;
        this.knowledgeUnitCategory=knowledgeUnitCategory;
        this.knowunit=knowunit;
        this.Problem=Problem;
        this.Solve=Solve;
        this.Root=Root;
        this.learnt=learnt;
        this.area=area;
    }

    public lesson(String Title,String Tag,String knowledgeUnitCategory,String knowunit, String Problem, String Solve,String Root,String learnt,String area ) {
        this.Title = Title;
        this.Tag = Tag;
        this.knowledgeUnitCategory=knowledgeUnitCategory;
        this.knowunit=knowunit;
        this.Problem=Problem;
        this.Solve=Solve;
        this.Root=Root;
        this.learnt=learnt;
        this.area=area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
     public String getTag() {
        return Tag;
    }

    public void setTag(String Tag) {
        this.Tag = Tag;
    }
    //lesson less=new lesson("Exploration and Production","Refining and Petrochemicals","Environmental Management","Health, Safety, and Environment (HSE)","Geology and Geophysics","Corporate Governance","Business and Finance");
    public String getknowledgeUnitCategory(){
        return knowledgeUnitCategory;
    }
    public void setknowledgeUnitCategory(String knowledgeUnitCategory){
        this.knowledgeUnitCategory=knowledgeUnitCategory;
    }
    public String getknowunit(){
        return knowunit;
    }
    public void setknowunit(String knowunit){
        this.knowunit=knowunit;
    }
    public String getProblem() {
        return Problem;
    }
    public void setProblem(String Problem) {
        this.Problem = Problem;
    }
    public String getSolve() {
        return Solve;
    }

    public void setSolve(String Solve) {
        this.Solve = Solve;
    }
   public String getRoot() {
        return Root;
    }

    public void setRoot(String Root) {
        this.Root = Root;
    }
    public String getlearnt() {
        return learnt;
    }

    public void setlearnt(String learnt) {
        this.learnt = learnt;
    }
    public String getarea() {
        return area;
    }

    public void setarea(String area) {
        this.area = area;
    }
}
