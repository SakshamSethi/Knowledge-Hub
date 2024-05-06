/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tech.blog.entities;

import java.sql.Timestamp;

/**
 *
 * @author 91821
 */
public class Repository {
    
    private  int id , userId;
    private String Title ,FileName , Tag ,Remark;
       private Timestamp dateTime;

    public Timestamp getDateTime() {
        return dateTime;
    }

    public Repository(int id, String Title, Timestamp dateTime , String FileNameString) {
        this.id = id;
        this.Title = Title;
        this.dateTime = dateTime;
        this.FileName = FileNameString;
    }

    public Repository(int id, int userId, String Title, String FileName, String Tag, String Remark, Timestamp dateTime) {
        this.id = id;
        this.userId = userId;
        this.Title = Title;
        this.FileName = FileName;
        this.Tag = Tag;
        this.Remark = Remark;
        this.dateTime = dateTime;
    }

   

    public Repository(String Title, String FileName, String Tag, String Remark,int userId) {
        this.Title = Title;
        this.FileName = FileName;
        this.Tag = Tag;
        this.Remark = Remark;
        this.userId = userId;
    }

    public Repository(String Title, String FileName, String Tag, String Remark, Timestamp dateTime,int userId) {
        this.Title = Title;
        this.FileName = FileName;
        this.Tag = Tag;
        this.Remark = Remark;
        this.dateTime = dateTime;
        this.userId=userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String Tag) {
        this.Tag = Tag;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

   
}
