/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lrcrr.teoriaproyecto;

import lrcrr.teoriaproyecto.TokenType;

/**
 *
 * @author Desarrollo
 */
public class Token {
    private TokenType type;
    private String content;
    private String message;

    public Token() {
    }    
    
    public Token(TokenType type, String content) {
        this.type = type;
        this.content = content;
    }

    public Token(TokenType type, String content, String message) {
        this.type = type;
        this.content = content;
        this.message = message;
    }
    
    

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    

    @Override
    public String toString() {
        return "Token{\n"
                + "type=" + type + ","
                + "\n content=" + content + '}'+ "\n";
    }   
    
}
