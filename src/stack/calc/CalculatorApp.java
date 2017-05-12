package stack.calc;

public class CalculatorApp {

	public static void main(String[] args) {
		Calculator calculator = new Calculator();

//		calculator.calculate( "2 * 1 + 3 / 2  " );
//		calculator.calculate( "(1 + 2 * 3 ) /   7" );
		calculator.calculate( "( 1 + 2 ) * ( 3 / 4 ) + ( 5 + ( 6 - 7 )   )  " );
	
	}

}
