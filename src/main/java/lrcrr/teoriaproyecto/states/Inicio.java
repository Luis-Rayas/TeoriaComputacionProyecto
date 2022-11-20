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
public class Inicio implements State{
    private Lexer lexer;

    @Override
    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }

    @Override
    public Boolean leerCaracter(Character c) throws Exception {
        Boolean finished = false;
        Boolean error = false;
        if(Character.isSpaceChar(c) || c.equals('$')){
            //No hay cambio
            lexer.setEstado(this);
            lexer.setLastState(lexer.getEstado());
            finished = true;
        } else 
        if(Character.isAlphabetic(c.charValue()) && validState()){//Es un identificador            
            lexer.setEstado(new Identificador());
            lexer.getEstado().setLexer(lexer);
        } else
        if(Character.isDigit(c.charValue()) && validState()){ //Es un numero
            lexer.setEstado(new Numero());
            lexer.getEstado().setLexer(lexer);
        } else
        if(c.equals('=') && validState()){ //Igual
            lexer.setEstado(new Igual() );
            lexer.getEstado().setLexer(lexer);
        } else
        if(c.equals(';') && validState()){ //Punto y coma
            lexer.setEstado(new PuntoComa());
            lexer.getEstado().setLexer(lexer);
        } else
        if(c.equals('"') && validState()){ //String literal
            lexer.setEstado(new StringLiteral());
            lexer.getEstado().setLexer(lexer);
        } else 
        if(c.charValue() == 39 && validState()){ //Char literal
            lexer.setEstado(new CharLiteral());
            lexer.getEstado().setLexer(lexer);
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
        return StateType.Inicio;
    }

    @Override
    public Boolean validState() {
        return true;
    }
    
}
