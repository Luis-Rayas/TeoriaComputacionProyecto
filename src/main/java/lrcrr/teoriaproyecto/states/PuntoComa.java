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
public class PuntoComa implements State{
    private Lexer lexer;
    
    @Override
    public Boolean leerCaracter(Character c) throws Exception {
        lexer.setEstado(new Inicio());
        lexer.getEstado().setLexer(lexer);
        lexer.setLastState(this);
        lexer.addCaminoRecorrido(estadoActual().name() + "=> " + lexer.getEstado().estadoActual().toString() + " ::= " + c);  
        return true;
    }

    @Override
    public StateType estadoActual() {
        return StateType.PuntoComa;
    }

    @Override
    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }

    @Override
    public Boolean validState() {
        return lexer.getEstado().estadoActual().equals(StateType.Inicio);
    }
    
}
