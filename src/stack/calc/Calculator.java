package stack.calc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import stack.ArrayList;
import stack.List;
import stack.Stack;
import stack.StackException;

public class Calculator {

	public void calculate( String exp ) {
		try {
			
			List<String> tokens = parseExp(exp.replaceAll("\\s+", ""));
			System.out.println( Arrays.toString( tokens.toArray() ) );
			
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}

	private List<String> parseExp( String infix ) throws StackException {
		List<String> tokens = new ArrayList<String>();
		Stack<Character> operatorStack = new Stack<Character>();
		
		for(int i = 0; i < infix.length(); i++) {
			
			char c = infix.charAt( i );
			switch (c) {
				case '*':
				case '/':
				case '+':
				case '-':
				case '(':
				case ')':
					tokenizeOperator( c, tokens, operatorStack );
					System.out.println( "operatorStack:" + Arrays.toString( operatorStack.toArray() ) );
					break;
				default:
					tokens.add( String.valueOf( c ) );
					break;
			}
		}
		
		// 마지막 처리, operator stack 비우기
		tokenizeOperator( tokens, operatorStack );
		
		return tokens;
	}
	
	/**
	 * operatorStack 을 비우면서 tokens에 차례대로 담는다.
	 * 
	 * @param tokens
	 * @param operatorStack
	 * @throws StackException
	 */
	private void tokenizeOperator( 
		List<String> tokens, 
		Stack<Character> operatorStack ) 
		throws StackException {
		
		while( operatorStack.empty() == false ) {
			char operator = operatorStack.pop();
			if( operator == '(' ) {
				//발견 즉시 바닥이 아니더라도 중지
				break;
			}
			
			tokens.add( String.valueOf( operator ) );
		}		
	
	}
	
	/**
	 * 
	 * Postfix Notation Algorithm 에 따라
	 * operator를 기준으로 tokens 또는 operatorStack를 채우고 비운다.
	 * 
	 * @param operator
	 * @param tokens
	 * @param operatorStack
	 * @throws StackException
	 */
	private void tokenizeOperator( 
		char operator, 
		List<String> tokens, 
		Stack<Character> operatorStack ) 
		throws StackException {
		
		if( operatorStack.empty() || operator == '(' ) {
			operatorStack.push( operator );
			return;
		}
		
		if( operator == ')' ) { // top 내용이 '(' 또는 빌 때 까지 모두 pop
			tokenizeOperator( tokens, operatorStack );
			return;
		}
		
		char operatorTop = operatorStack.peek();
		int result = OperatorPriority.comp( operator, operatorTop );
		
		if( result > 0 ) { // top 연산자보다 우선순위가 크다 ( just push )
			operatorStack.push( operator );
			return;
		}
		
		if( result == 0 ) { // top 연산자와 우선순위가 같다( exchange top operator )
			tokens.add( String.valueOf( operatorStack.pop() ) );
			operatorStack.push( operator );
			return;
		} 
		
		// top 연산자와 우선순위가 작다
		// top 내용이 '(' 또는 빌 때 까지 모두 pop 그리고 push
		tokenizeOperator( tokens, operatorStack );
		operatorStack.push( operator );
	}
	
	
	/**
	 * 연산자 사이의 우선순위 차이를 비교해서 -1, 0, 1로 반환하기 위한 클래스 
	 * class OperatorPriority
	 *
	 */
	private static class OperatorPriority {
		private static final Map<Character, Integer> MAP  = new HashMap<Character, Integer>();
		static {
			MAP.put( '*', 2 );
			MAP.put( '/', 2 );
			MAP.put( '+', 1 );
			MAP.put( '-', 1 );
			MAP.put( '(', 0 );
		}
		
		private static int comp( char op1, char op2 ) {
			return MAP.get( op1 ) - MAP.get( op2 );
		}
	}
}
