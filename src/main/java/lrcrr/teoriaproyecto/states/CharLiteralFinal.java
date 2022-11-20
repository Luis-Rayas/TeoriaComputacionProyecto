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
public class CharLiteralFinal implements State{
    private Lexer lexer;

    @Override
    public Boolean leerCaracter(Character c) throws Exception {        
        lexer.setEstado(new Inicio());
        lexer.getEstado().setLexer(lexer);
        lexer.setLastState(new CharLiteralFinal());
        lexer.addCaminoRecorrido(estadoActual().name() + "=> " + lexer.getEstado().estadoActual().name() + " ::= " + c);  
        return true;             
    }

    @Override
    public StateType estadoActual() {
        return StateType.CharLiteralFinal;
    }

    @Override
    public Boolean validState() {
        return true;
    }

    @Override
    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }
    
}
