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
public class NumeroExponencialFinal implements State{

    private Lexer lexer;
    @Override
    public Boolean leerCaracter(Character c) throws Exception {
        Boolean finished = false;
        Boolean error = false;
        if(Character.isSpaceChar(c.charValue()) && validState()){
            lexer.setEstado(new Inicio());
            lexer.getEstado().setLexer(lexer);
            lexer.setLastState(lexer.getEstado());
            finished = true;
        } else
        if(Character.isDigit(c.charValue()) && validState()){ //Es un numero
            lexer.setLastState(lexer.getEstado());
            lexer.setEstado(new NumeroExponencialFinal());
            lexer.getEstado().setLexer(lexer);
        } else
        {
            error = true;
        }
        if(error){
            throw new Exception("No se esperaba el caracter: " + c + "\n " + estadoActual().name());
        }
        lexer.addCaminoRecorrido(estadoActual().name() + "=> " + lexer.getEstado().estadoActual().name() + " ::= \'" + c + "\'");
        return finished;
    }

    @Override
    public StateType estadoActual() {
        return StateType.NumeroExponencialFinal;
    }

    @Override
    public Boolean validState() {
        return lexer.getEstado().estadoActual().equals(StateType.NumeroExponencial) || lexer.getEstado().estadoActual().equals(StateType.NumeroExponencialConSigno);
    }

    @Override
    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }
    
}
