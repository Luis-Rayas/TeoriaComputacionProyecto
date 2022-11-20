/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lrcrr.teoriaproyecto.states;

import lrcrr.teoriaproyecto.Analyzers.Lexer;

/**
 *
 * @author Desarrollo
 */
public class StringLiteral implements State{

    private Lexer lexer;
    
    @Override
    public Boolean leerCaracter(Character c) throws Exception {
        Boolean finished = false;
        Boolean error = false;
        if(c.equals("\"") && validState()){ //String literal
            lexer.setLastState(lexer.getEstado());
            lexer.setEstado(new StringLiteralFinal());
            lexer.getEstado().setLexer(lexer);
        } 
        lexer.addCaminoRecorrido(estadoActual().name() + "=> " + lexer.getEstado().estadoActual().name() + " ::= " + c);  
        return finished;
    }

    @Override
    public StateType estadoActual() {
        return StateType.StringLiteral;
    }

    @Override
    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }

    @Override
    public Boolean validState() {
        return lexer.getEstado().estadoActual().equals(StateType.StringLiteral);
    }
    
}
