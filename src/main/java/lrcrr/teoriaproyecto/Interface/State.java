/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lrcrr.teoriaproyecto.Interface;

import lrcrr.teoriaproyecto.Analyzers.Lexer;

/**
 *
 * @author Desarrollo
 */
public interface State {
    
    Boolean leerCaracter(Character c) throws Exception;    
    
    StateType estadoActual();
    
    Boolean validState();
    
    void setLexer(Lexer lexer);
}
