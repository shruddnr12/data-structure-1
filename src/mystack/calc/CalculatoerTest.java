package mystack.calc;

import java.util.HashMap;

public class CalculatoerTest {

	public static Double result;
	public static void main(String[] args) {
		Stack<Double> doubleStack = new Stack<Double>();
		Stack<Character> operationStack = new Stack<Character>();

	//	String sample = "2 * 1 + 3 / 2";
		String sample = "2 - 3 * 5";
	
		sample =  sample.replaceAll(" ", "");

		double right;
		double left;
		
		HashMap<Character,Integer> operationcompareset = new HashMap<Character, Integer>(); 
		operationcompareset .put('(', 0);
		operationcompareset .put('+', 1);
		operationcompareset .put('-', 1);
		operationcompareset .put('*', 2);
		operationcompareset .put('/', 2);
		

		try{
			for(int i = 0 ; i < sample.length(); i++){
				if( 1 <=(double) Character.digit(sample.charAt(i), 10) &&(double) Character.digit(sample.charAt(i), 10) <= 9)
				{
					doubleStack.push((double) Character.digit(sample.charAt(i), 10));
				}
				else{
					Character currentgetoperation = sample.charAt(i);
					if(operationStack.empty()){
						operationStack.push(currentgetoperation);
					}

					//새로들어온 문자가 stack의 top 부호와 같을 때
					else if(operationcompareset.get(currentgetoperation) == operationcompareset.get(operationStack.peek())) {
						Character operation = operationStack.pop();
						right = doubleStack.pop();
						left = doubleStack.pop();
					
						result = calculate(operation,left,right);
				
						doubleStack.push(result);
						operationStack.push(currentgetoperation);
					}
					
					else if(operationcompareset.get(currentgetoperation) > operationcompareset.get(operationStack.peek())) {
						operationStack.push(currentgetoperation);
					}
//					else if(currentgetoperation < operationput.peek()) {
//				
//						Character operation = operationput.pop();
//						right = intput.pop();
//						left = intput.pop();
//					
//						result = calculate(operation,left,right);
//				
//						intput.push(result);
//						operationput.push(currentgetoperation);
//					}
					
					
				}
			}
			
			if( !operationStack.empty()){
			Character operation = operationStack.pop();
			right = doubleStack.pop();
			left = doubleStack.pop();
		
			result = calculate(operation,left,right);
			}
			
		}catch(StackException e){
			System.out.println(e);
		}
				
		System.out.println(result);
	}

	public static double calculate(char operation,double left, double right){
		double result = 0;
		
		if(operation == '+'){
			return add(left,right);
		}
		else if(operation == '-'){
			return minus(left,right);
		}
		else if(operation == '*'){
			return multiple(left,right);
		}
		else if(operation == '/'){
			return divide(left,right);
		}
		
		return result;
	}
	
	public static double add(double left,double right){
		return left + right;
	}
	public static double minus(double left,double right){
		return left - right;
	}
	public static double multiple(double left,double right){
		return left * right;
	}
	public static double divide(double left,double right){
		return left / right;		
	}


}

