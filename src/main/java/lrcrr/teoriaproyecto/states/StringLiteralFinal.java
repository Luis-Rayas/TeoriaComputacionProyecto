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
        Boolean finished = true;
        Boolean error = false;
        if((Character.isSpaceChar(c) || c.equals('$')) && validState()){
            lexer.setEstado(new Inicio());
            lexer.getEstado().setLexer(lexer);
            lexer.setLastState(this);
        } else
        if(Character.isDigit(c.charValue()) && validState()){ //Es un numero
            lexer.setLastState(lexer.getEstado());
            lexer.setEstado(new NumeroExponencialFinal());
            lexer.getEstado().setLexer(lexer);
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
