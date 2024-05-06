/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tech.blog.entities;

//Iss Message class ka jo object hoga usmai ayesi info rhegi jo ek particular 
// detail (baat/message) batari hogi user ko
// jaise ki kisi ek msg ka content , msg ka type ki error msg hai ya kuch or ,etc
// toh jab bhi user ko kuch msg dena hoga toh hum iss class ka object banayenge :)
public class Message {
    
    private String content;
    private String type;
    private String cssClass; // css ki konsi class use krni hai message ko dikhane k liye(design wise)

    public Message(String content, String type, String cssClass) {
        this.content = content;
        this.type = type;
        this.cssClass = cssClass;
    }
    // Getters and setter

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }
    
    
    
}
