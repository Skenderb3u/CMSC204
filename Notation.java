

import java.io.*;
import java.util.*;
import java.lang.Object;

public class Notation {
	
	private static final int ADDITIVE = 0;
    private static final int MULTIPLICATIVE = 5;
    private static final int PARENTHESES = -5;

    /**
     * Coverts postfix expression to infix expression.
     * 
     * @param postfix the postfix expression in string format.
     * @return the infix expression in string format.
     * @throws InvalidNotationFormatException if the postfix expression format is invalid.
     * @throws StackUnderflowException 
     * @throws StackOverflowException 
     */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException, StackUnderflowException, StackOverflowException {
		MyStack stack = new MyStack();
		

		for (int i = 0; i < postfix.length(); i++) {
			char c = postfix.charAt(i);
			// Push operands
			if (c != '+' && c != '-' && c != '*' && c != '/') {
				stack.push(c);
			}

			// We assume that input is
			// a valid postfix and expect
			// an operator.
			else {
				if (stack.size() < 2) {
					throw new InvalidNotationFormatException("Stack size is too small!");
				}
				String op1 = stack.top().toString();
				stack.pop();
				String op2 = stack.top().toString();
				stack.pop();
				stack.push("(" + op2 + postfix.charAt(i) + op1 + ")");
			}
		}

		// There must be a single element
		// in stack now which is the required
		// infix.
		return stack.top().toString();
	}

	/**
	 * Evaluate postfix expression from a string to a double.
	 * 
	 * @param postfixExpr the postfix expression in string format.
	 * @return the evaluation of the postfix expression as a double.
	 * @throws InvalidNotationFormatException if the postfix expression is invalid.
	 * @throws StackOverflowException 
	 * @throws StackUnderflowException 
	 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException, StackOverflowException, StackUnderflowException {
		MyStack stack = new MyStack();
		double first = 0.0;
		double second = 0.0;
		double value = 0.0;
		
		for (int i = 0; i < postfixExpr.length(); i++) {
			char c = postfixExpr.charAt(i);
			
			//If space, do nothing.
			if (c == ' ') {
				
			}
			//If operand or left parenthesis, push onto stack.
			else if (Character.isDigit(c) || Character.isLetter(c) || c == '(') {
				stack.push(c);
			}
			//If operator, pop top 2 values, if fewer than 2, throw an error.
			else if (c == '+' || c == '-' || c == '*' || c == '/') {
				
				if (stack.size() < 2) {
					throw new InvalidNotationFormatException("Should have more than 2 values!");
				} else {
					first = Double.valueOf(stack.pop().toString());
					second = Double.valueOf(stack.pop().toString());
					
					if (c == '+') {
						value = second + first;
					} else if (c == '-') {
						value = second - first;
					} else if (c == '*') {
						value = second * first;
					} else if (c == '/') {
						value = second / first;
					}
					stack.push(value);
				}
			}
		}
		
		if (stack.size() > 1) {
			throw new InvalidNotationFormatException("Should have only one value!");
		} else {
			return value;
			}
		
	}

	/**Converts Infix to Postfix notation
	 * @param <T>
	 * 
	 * @param infix the infix expression in string format.
	 * @return the postfix notation in string format.
	 * @throws StackOverflowException, QueueOverflowException 
	 * @throws StackUnderflowException 
	 */

	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException, StackOverflowException, QueueOverflowException, StackUnderflowException {

		//Initializing empty queue, stack
		MyQueue queue = new MyQueue();
		MyStack stack = new MyStack();

		for (int i = 0; i < infix.length(); i++) {
			char c = infix.charAt(i);
			
			//If the current characater is a space, do nothing.
			if (c == ' ') {
				
			}
			//If character is a digit or letter, add to postfix queue.
			else if (Character.isDigit(c) || Character.isLetter(c)) {
				queue.enqueue(c);
			}
			//If character is left paranthesis, push to stack.
			else if (c == '(') {
				stack.push(c);
			}
			/* If character is an operator,
			 * Pop operators at top of stack with equal or higher precedence
			 * than current operator. 
			 * Insert popped operators into postfix queue.
			 */
			else if (c == '+' || c== '-' || c == '*' || c == '/') {
				for (int k = 0; k < stack.size(); k++) {
					prec(stack.top().toString());
					if ((prec(stack.top().toString()) >= prec(infix.valueOf(c)))) {
						queue.enqueue(stack.pop());
					} else {
						stack.push(c);
						break;
					}
				}
			}
			/* If character is a right paranthesis,
			 * Pop operators and put them in queue until a
			 * left paranthesis is at the top of the stack.
			 * Pop the left paranthesis.
			 * 
			 * Throw an error if left paranthesis is never found.
			 * 
			 */
			else if (c == ')') {
				if (stack.size() == 0) {
					throw new InvalidNotationFormatException("The inputted format is invalid.");
				}
				for (int l = 0; l < stack.size(); l++) {
					if ((stack.top().toString()).charAt(0) == '(') {
						stack.pop();
						break;
					} else {
						queue.enqueue(stack.pop());
					}
					if (stack.size() == 1) {
						stack.pop();
					}
				}
			} 
			//Pop anything that remains and put them in queue.
			else {
				for (int j = 0; j < stack.size(); j++) {
				queue.enqueue(stack.pop());
				}
			}
		}
		return queue.toString();
	}
	
	/**Helper method to determine precedence.
	 * 
	 * @param str the string to check for precedence.
	 * @return precedence value of the string.
	 */
	public static int prec(String str) {
		if (str.equals("+") || str.equals("-")) {
		    return ADDITIVE;
		} else if (str.equals("*") || str.equals("/")) {
		    return MULTIPLICATIVE;
		} else if (str.equals("(") || str.equals(")")) {
		    return PARENTHESES;
		} else {
			return 0;
		}
	}
}