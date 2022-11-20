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
public class CharLiteralIntermedio implements State{

    private Lexer lexer;
    @Override
    public Boolean leerCaracter(Character c) throws Exception {
        if(c.equals("\'")){
            lexer.setLastState(lexer.getEstado());
            lexer.setEstado(new CharLiteralFinal());
            lexer.getEstado().setLexer(lexer);
            lexer.addCaminoRecorrido(estadoActual().name() + "=> " + lexer.getEstado().estadoActual().name() + " ::= " + c);  
            return false;    
        } else {
             throw new Exception("No se esperaba el caracter: " + c + "\n " + estadoActual().name());
        }        
    }

    @Override
    public StateType estadoActual() {
        return StateType.CharLiteralIntermedio;
    }

    @Override
    public Boolean validState() {
        return lexer.getEstado().estadoActual().equals(StateType.CharLiteral);
    }

    @Override
    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }
    
}
