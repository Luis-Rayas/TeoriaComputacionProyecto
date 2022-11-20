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
public class Numero implements State{

    private Lexer lexer;
    @Override
    public Boolean leerCaracter(Character c) throws Exception {
        Boolean finished = false;
        Boolean error = false;
        if((Character.isSpaceChar(c.charValue()) || c.equals('$')) && validState()){
            lexer.setLastState(this);
            lexer.setEstado(new Inicio());
            lexer.getEstado().setLexer(lexer);
            finished = true;
        } else
        if(Character.isDigit(c.charValue()) && validState()){ //Es un numero
            lexer.setLastState(lexer.getEstado());
            lexer.setEstado(new Numero());
            lexer.getEstado().setLexer(lexer);
        } else 
        if(c.equals('.')){
            lexer.setLastState(lexer.getEstado());
            lexer.setEstado(new NumeroDecimal());
            lexer.getEstado().setLexer(lexer);
        } else 
        if(c.equals('E')){ //Exponencial
            lexer.setLastState(lexer.getEstado());
            lexer.setEstado(new NumeroExponencial());
            lexer.getEstado().setLexer(lexer);
        } else
        {
            error = true;
        }
        if(error){
            throw new Exception("No se esperaba el caracter: " + c + "\n " + estadoActual().name());
        }
        lexer.addCaminoRecorrido(estadoActual().name() + "=> " + lexer.getEstado().estadoActual().name() + " ::= " + c);  
        return finished;
    }

    @Override
    public StateType estadoActual() {
        return StateType.Numero;
    }

    @Override
    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }

    @Override
    public Boolean validState() {
        return lexer.getEstado().estadoActual().equals(StateType.Inicio) || lexer.getEstado().estadoActual().equals(StateType.Numero);
    }
    
    
}
