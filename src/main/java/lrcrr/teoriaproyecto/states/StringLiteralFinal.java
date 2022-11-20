/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lrcrr.teoriaproyecto.states;

import lrcrr.teoriaproyecto.Interface.StateType;
import lrcrr.teoriaproyecto.Interface.State;
import lrcrr.teoriaproyecto.Analyzers.Lexer;

/**
 *
 * @author Desarrollo
 */
public class StringLiteralFinal implements State{

    private Lexer lexer;
    
    @Override
    public Boolean leerCaracter(Character c) throws Exception {     
        lexer.setLastState(this);
        lexer.setEstado(new Inicio());
        lexer.getEstado().setLexer(lexer);                
        lexer.addCaminoRecorrido(estadoActual().name() + "=> " + lexer.getEstado().estadoActual().name() + " ::= " + c);
        return true;
    }

    @Override
    public StateType estadoActual() {
        return StateType.StringLiteralFinal;
    }

    @Override
    public Boolean validState() {
        return lexer.getEstado().equals(StateType.StringLiteral) || lexer.getEstado().estadoActual().equals(StateType.StringLiteralFinal);
    }

    @Override
    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }
    
}
