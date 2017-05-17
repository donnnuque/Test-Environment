package com.lotus.util;

public class ArithmeticParser {
	
	private String input;
	
	int pos = -1;
	int character;
	
	public ArithmeticParser() { }

	public int parse(String input) {
		
		pos = -1;
		this.input= input; 
		
        nextChar();
        int number = parseAS();
        
        return number;
    }
	
	private void nextChar() {
		
		character = (++pos < input.length()) ? input.charAt(pos) : -1;
    }
	
	private boolean parseOperator(int operator) {
        
    	if (character == operator) {
            nextChar();
            return true;
        }
    	
        return false;
    }

    private int parseAS() {
        
    	int number = parseMD();
        
        for (;;) {
        	
            if (parseOperator('+')) {
            	
            	number += parseMD();
            }
            else if (parseOperator('-')) {
            	
            	number -= parseMD();
            }
            else {
            	
            	return number;
            }
        }
    }

    private int parseMD() {
    	
        int number = parseNumber();
        
        for (;;) {
        	
            if (parseOperator('*')) {
            	
            	number *= parseNumber();
            }
            else if (parseOperator('/')) {
            	
            	number /= parseNumber();
            }
            else {
            	
            	return number;
            }
        }
    }

    private int parseNumber() {
    	
        int number = 0;
        int startPos = this.pos;
        
        if ((character >= '0' && character <= '9')) {
            
        	while ((character >= '0' && character <= '9')) {
            	nextChar();
        	}
        	
        	number = Integer.parseInt(input.substring(startPos, this.pos));
        }

        return number;
    }
}
