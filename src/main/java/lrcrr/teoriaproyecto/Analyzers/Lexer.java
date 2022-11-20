/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lrcrr.teoriaproyecto.Analyzers;

import java.util.ArrayList;
import java.util.List;
import lrcrr.teoriaproyecto.Token;
import lrcrr.teoriaproyecto.TokenType;
import lrcrr.teoriaproyecto.states.Inicio;
import lrcrr.teoriaproyecto.Interface.State;
import lrcrr.teoriaproyecto.Interface.StateType;

/**
 *
 * @author Desarrollo
 */
public class Lexer {

    private State estado;
    private State lastState;
    private List<Token> tokens;
    private StringBuilder caminoRecorrido;

    public Lexer() {
        this.tokens = new ArrayList<>();
        this.estado = new Inicio();
        this.lastState = null;
        this.estado.setLexer(this);
        this.caminoRecorrido = new StringBuilder();
    }

    public void analisisLexico(String texto) {
        this.estado = new Inicio();
        this.estado.setLexer(this);
        this.lastState = estado;
        tokens.clear();
        caminoRecorrido.setLength(0);
        Character letra;
        StringBuilder palabra = new StringBuilder();
        Boolean palabraTerminada;
        texto = texto + "$";
        for (int i = 0; i < texto.length(); i++) {
            letra = texto.charAt(i);
            try {
                palabraTerminada = estado.leerCaracter(letra);
                if (letra.equals('$')) {
                    palabraTerminada = true;
                }
                if (palabraTerminada) {
                    if (!letra.equals('$')
                            && lastState.estadoActual().equals(estado.estadoActual())
                            || palabra.toString().isEmpty()) {
                        palabra.append(letra);
                    }
                    if (estado.estadoActual().equals(StateType.PuntoComa)
                            || estado.estadoActual().equals(StateType.Igual)
                            && !letra.equals('$')) {
                        i--;
                    } 
                    if (letra.equals('$') && estado.estadoActual().equals(StateType.Inicio) && lastState.estadoActual().equals(StateType.Inicio)) {
                        //addToken(new Token(TokenType.FinCadena, letra.toString()));
                        continue;
                    } else {
                        Token token;
                        switch (lastState.estadoActual()) {
                            case Identificador:
                                token = new Token(TokenType.Identificador, palabra.toString());
                                break;
                            case Numero:
                                token = new Token(TokenType.Numero, palabra.toString());
                                break;
                            case NumeroDecimal:
                                token = new Token(TokenType.NumeroDecimal, palabra.toString());
                                break;
                            case NumeroExponencialFinal:
                                token = new Token(TokenType.NumeroExponencial, palabra.toString());
                                break;
                            case PuntoComa:
                                token = new Token(TokenType.PuntoComa, palabra.toString());
                                break;
                            case Igual:
                                token = new Token(TokenType.Igual, palabra.toString());
                                break;
                            case StringLiteralFinal:
                                token = new Token(TokenType.Cadena, palabra.toString());
                                break;
                            case CharLiteralFinal:
                                token = new Token(TokenType.Caracter, palabra.toString());
                                break;
                            case Inicio:
                                continue;
                            default:
                                token = new Token(TokenType.Error, palabra.toString());
                                palabra.setLength(0);
                        }
                        addToken(token);
                        palabra.setLength(0);
                    }
                } else {
                    palabra.append(letra);
                }
            } catch (Exception e) {
                e.printStackTrace();
                palabra.append(letra);
                addToken(new Token(TokenType.Error, palabra.toString(), e.getMessage()));
                palabra.setLength(0);
                estado = new Inicio();
                estado.setLexer(this);
                lastState = estado;
            }
        }
    }

    public State getLastState() {
        return lastState;
    }

    public void setLastState(State lastState) {
        this.lastState = lastState;
    }

    public State getEstado() {
        return estado;
    }

    public void setEstado(State estado) {
        this.estado = estado;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public StringBuilder getCaminoRecorrido() {
        return caminoRecorrido;
    }

    public void setCaminoRecorrido(StringBuilder caminoRecorrido) {
        this.caminoRecorrido = caminoRecorrido;
    }

    public void addToken(Token token) {
        this.tokens.add(token);
    }

    public void addCaminoRecorrido(String caminito) {
        this.caminoRecorrido.append(caminito).append("\n");
    }

}
