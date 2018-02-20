/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatprojectcommon;

import java.io.Serializable;

/**
 *
 * @author ElsOoObkey
 */
public class Message implements Serializable{
    private String body;
    private String from;
    private String to;
    private String font;
    private String color;
    private String fontSize; 
    public Message(String body, String from, String to, String font, String color , String fontSize) {
        this.body = body;
        this.from = from;
        this.to = to;
        this.font = font;
        this.color = color;
        this.fontSize=fontSize;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    
    
}
