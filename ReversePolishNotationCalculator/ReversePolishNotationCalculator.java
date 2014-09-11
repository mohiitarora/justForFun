import java.util.Scanner;
import java.util.Stack;

public class ReversePolishNotationCalculator {

	private final String operators = "+-*/";// can be extended.
	private Stack<Double> operands = new Stack<Double>();

	public static void main(String args[]) {
		ReversePolishNotationCalculator rpnCalculator = new ReversePolishNotationCalculator();
		rpnCalculator.evaluateReversePolishNotation();
	}

	private void evaluateReversePolishNotation() {

		System.out.println("RPNCalculator\n");
		Scanner input = new Scanner(System.in);

		while (true) {
			
			System.out.print("> ");
			String userInput = input.next();
			int index = operators.indexOf(userInput);
			validateInput(userInput,index);
		}
	}
	
	/**
	 * The method is responsible for validation the input provided by the user and
	 * pass on the values for evaluation of answer if the data is validated.
	 * 
	 * @param userInput
	 * @param operands
	 * @param index
	 */
	public void validateInput(String userInput, int index){
		try{
			if(index == -1){
				if (userInput.equals("q") || userInput.equals("\\")) {
					if (operands.size() > 0) {
						System.out.println(operands.pop());
					}
					System.exit(0);
				}
				if(isNumber(userInput)){
					operands.push(Double.valueOf(userInput));
					System.out.println(userInput);
				}
			}else{
				if (operands.size() < 2 || userInput.length() > 1) {
					throw new Exception("Invalid operand/operator.");
				}
				calculateAnswer(index);
			}
			
		}catch(Exception e){
			System.out.println("Warning: "+e.getMessage()+"  Please Re-enter\n");
		}
	}
	
	/**
	 * This method is responsible for calculation of the data with the 
	 * standard set of operands.
	 * 
	 * @param operands
	 * @param index
	 */
	private void calculateAnswer(int index) throws Exception{
		double answer = 0;
		double op2 = operands.pop();
		double op1 = operands.pop();

		switch (index) {
			
			case 0:	answer = op1 + op2;
					if (answer < op1 || answer < op2) {
						throw new Exception("Buffer Overflow");
					}
					break;
					
			case 1:	answer = op1 - op2;
					break;
			
			case 2:	answer = op1 * op2;
					break;
			
			case 3:	if (op2 == 0) {
						operands.push(op1);
						throw new Exception("Divide by Zero not allowed.");
					}
					answer = op1 / op2;
					break;
					
			default:System.out.println("Default.");break;
		}
	
		operands.push(answer);
		System.out.println((answer == Math.floor(answer))?(int)answer:answer);
	}
	
	private boolean isNumber(String n) {
		try {
			Integer.parseInt(n);
			return true;
		} catch (NumberFormatException nfe) {
			System.out.println("Warning: Please enter q ot \\ to quit or a valid number to continue");
			return false;
		}
	}

	public String getOperators() {
		return operators;
	}

	public Stack<Double> getOperands() {
		return operands;
	}

}
