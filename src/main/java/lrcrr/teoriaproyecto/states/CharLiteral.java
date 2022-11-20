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
public class CharLiteral implements State{

    private Lexer lexer;
    @Override
    public Boolean leerCaracter(Character c) throws Exception {
        lexer.setLastState(lexer.getEstado());
        lexer.setEstado(new CharLiteralIntermedio());
        lexer.getEstado().setLexer(lexer);
        lexer.addCaminoRecorrido(estadoActual().name() + "=> " + lexer.getEstado().estadoActual().name() + " ::= " + c);  
        return false;
    }

    @Override
    public StateType estadoActual() {
        return StateType.CharLiteral;
    }

    @Override
    public Boolean validState() {
        return lexer.getEstado().estadoActual().equals(StateType.Inicio);
    }

    @Override
    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }

}
