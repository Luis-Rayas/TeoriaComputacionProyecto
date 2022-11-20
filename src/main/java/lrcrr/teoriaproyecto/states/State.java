/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lrcrr.teoriaproyecto.states;

import lrcrr.teoriaproyecto.Analyzers.Lexer;

/**
 *
 * @author Desarrollo
 */
public interface State {
    
    void leerCaracter(String palabra, Character c) throws Exception;    
    
    StateType estadoActual();
    
    Boolean validState();
    
    void setLexer(Lexer lexer);
}
