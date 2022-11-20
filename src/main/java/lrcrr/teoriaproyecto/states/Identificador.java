/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lrcrr.teoriaproyecto.states;

import lrcrr.teoriaproyecto.Interface.StateType;
import lrcrr.teoriaproyecto.Interface.State;
import lrcrr.teoriaproyecto.Analyzers.Lexer;
import lrcrr.teoriaproyecto.Token;
import lrcrr.teoriaproyecto.TokenType;

/**
 *
 * @author Desarrollo
 */
public class Identificador implements State {

    private Lexer lexer;

    @Override
    public StateType estadoActual() {
        return StateType.Identificador;
    }

    @Override
    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }

    @Override
    public Boolean leerCaracter(Character c) throws Exception {
        Boolean finished = false;
        Boolean error = false;
        if(Character.isSpaceChar(c) || c.equals('$')){
            lexer.setLastState(lexer.getEstado());
            lexer.setEstado(new Inicio());
            lexer.getEstado().setLexer(lexer);
            finished = true;
        } else 
        if(Character.isAlphabetic(c.charValue()) || c.equals('_') && validState()){//Es un identificador            
            //no hay cambio
        } else
        if(Character.isDigit(c.charValue()) && validState()){ //Es un numero
            //no hay cambio
        } else
        if(c.equals('=') && validState()){ //Igual            
            lexer.setLastState(lexer.getEstado());
            lexer.setEstado(new Igual() );
            lexer.getEstado().setLexer(lexer);
            finished = true;
        } else
        if(c.equals(';') && validState()){ //Punto y coma            
            lexer.setLastState(lexer.getEstado());
            lexer.setEstado(new PuntoComa());
            lexer.getEstado().setLexer(lexer);
            finished = true;
        } else
        if(c.equals('"') && validState()){ //String literal            
            lexer.setLastState(lexer.getEstado());
            lexer.setEstado(new StringLiteral());
            lexer.getEstado().setLexer(lexer);
            finished = true;
        } else 
        if(c.charValue() == 39 && validState()){ //Char literal            
            lexer.setLastState(lexer.getEstado());
            lexer.setEstado(new CharLiteral());
            lexer.getEstado().setLexer(lexer);
            finished = true;
        } else {
            error = true;
        }
        if(error){
            throw new Exception("No se esperaba el caracter: " + c + "\n " + estadoActual().name());
        }
        lexer.addCaminoRecorrido(estadoActual().name() + "=> " + lexer.getEstado().estadoActual().name() + " ::= \'" + c + "\'");  
        return finished;
    }

    @Override
    public Boolean validState() {
        return lexer.getEstado().estadoActual().equals(StateType.Identificador) || lexer.getEstado().estadoActual().equals(StateType.Inicio);
    }

}
