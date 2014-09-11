import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ReversePolishNotationCalculatorTest {

	private ReversePolishNotationCalculator calculator;

	@Before
	public void setUp() throws Exception {
		calculator = new ReversePolishNotationCalculator();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidAddition() {
		String userInput = "5";
		int index = calculator.getOperators().indexOf(userInput);
		calculator.validateInput(userInput,index);
		
		userInput = "6";
		index = calculator.getOperators().indexOf(userInput);
		calculator.validateInput(userInput,index);
		
		userInput = "+";
		index = calculator.getOperators().indexOf(userInput);
		calculator.validateInput(userInput,index);
		
		System.out.println(calculator.getOperands());
		assertEquals(String.valueOf(calculator.getOperands().peek()),"11.0");
	}
	
	@Test
	public void testValidSubstraction() {
		String userInput = "12";
		int index = calculator.getOperators().indexOf(userInput);
		calculator.validateInput(userInput,index);
		
		userInput = "6";
		index = calculator.getOperators().indexOf(userInput);
		calculator.validateInput(userInput,index);
		
		userInput = "-";
		index = calculator.getOperators().indexOf(userInput);
		calculator.validateInput(userInput,index);
		
		System.out.println(calculator.getOperands());
		assertEquals(String.valueOf(calculator.getOperands().peek()),"6.0");
	}
	
	@Test
	public void testValidMultiply() {
		String userInput = "5";
		int index = calculator.getOperators().indexOf(userInput);
		calculator.validateInput(userInput,index);
		
		userInput = "6";
		index = calculator.getOperators().indexOf(userInput);
		calculator.validateInput(userInput,index);
		
		userInput = "*";
		index = calculator.getOperators().indexOf(userInput);
		calculator.validateInput(userInput,index);
		
		System.out.println(calculator.getOperands());
		assertEquals(String.valueOf(calculator.getOperands().peek()),"30.0");
	}
	
	@Test
	public void testValidDivision() {
		String userInput = "5";
		int index = calculator.getOperators().indexOf(userInput);
		calculator.validateInput(userInput,index);
		
		userInput = "5";
		index = calculator.getOperators().indexOf(userInput);
		calculator.validateInput(userInput,index);
		
		userInput = "/";
		index = calculator.getOperators().indexOf(userInput);
		calculator.validateInput(userInput,index);
		
		System.out.println(calculator.getOperands());
		assertEquals(String.valueOf(calculator.getOperands().peek()),"1.0");
	}

}
