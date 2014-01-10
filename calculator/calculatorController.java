import javax.swing.*;
import java.awt.event.*;

public class calculatorController implements ActionListener {
	/* 12/14/2012
	 * CS111B
	 * Calculator Gui
	 * Dana Bienenfeld
	 * Objective: create a functional calculator
	 * 
	 * "Deviations:" I omitted the boolean value shouldReplace as I simply rewrite the text field when needed
	 *               Other than that, aside from esthetic preferences, there are no fundamental differences.
	 */

  	private int leftOperand;
	private int rightOperand;
	private double result;
	
	private int opCounter = 0; //counts how many times an operand is pushed
	private String performAction; // + - * /
	private String secondAction;            // used to store an operator temporarily when multiple exist
	
	private JTextField label;
	private boolean operatorIsSet = false;  //boolean expressions test the existence of part of a potential equation
	private boolean rightOpIsSet = false;
	private boolean leftOpIsSet = false;
																					//all calculator buttons
	private JButton b7; private JButton b8; private JButton b9; private JButton bDiv;
	private JButton b4; private JButton b5; private JButton b6; private JButton bMult;
	private JButton b1; private JButton b2; private JButton b3; private JButton bSub;
	private JButton b0; private JButton bC; private JButton bEq; private JButton bAdd;	
	
	public calculatorController(JTextField l, 										//insubtantiate the calculator
								JButton b07, JButton b08, JButton b09, JButton bDi,
								JButton b04, JButton b05, JButton b06, JButton bMul,
								JButton b01, JButton b02, JButton b03, JButton bSu,
								JButton b00, JButton b0C,JButton b0Eq, JButton b0A) {
		label = l;
		b7 = b07; b8 = b08; b9 = b09; bDiv = bDi;
		b4 = b04; b5 = b05; b6 = b06; bMult = bMul;
		b1 = b01; b2 = b02; b3 = b03; bSub = bSu;
		b0 = b00; bC = b0C; bEq = b0Eq; bAdd = b0A;
		
	}
	
	public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			int setNum;
			
			if ((source == b0) || (source == b1) || (source == b2) || (source == b3) ||(source == b4) ||
			    (source == b5) || (source == b6) || (source == b7) || (source == b8) ||(source == b9) ) {
							
					DigitButtonPressed(source); // digit was pressed
			
			} else if ( (source == bDiv) || (source == bMult) || (source == bSub) || (source == bAdd) ) {
				
					operatorIsSet = true;
					opCounter++;           //increment the operator counter every time an arithmetic button is pressed
					ArithButtonPressed(source);  // arithmetic button was pressed
					                 
			
			} else if (source == bC) { // clear the screen and reset operands, operation(s) and boolean values, etc
				
				label.setText(""+0);
				result = 0;
				reset();
		   						
			} else if (source == bEq) {
				
					EqualButtonPressed(source); //snap together equation components and return result to the screen
			}
	}
	
	private void reset() { //reset all values for next equation
			
		leftOperand = 0;  rightOperand = 0;  opCounter = 0;
		leftOpIsSet = false; rightOpIsSet = false; operatorIsSet = false; 	
		secondAction = "";
		
	}
	
	private void EqualButtonPressed(JButton source) {
		
		if (operatorIsSet) {
			   
			   if (leftOpIsSet) {
				   	
				   	 if (!rightOpIsSet) {        // EX: 5 + =  is the equivalent of 5 +5
				   		 		rightOperand = leftOperand;
				   		 		rightOpIsSet = true;
				   	 } 
				   	
				   	 result = PerformAction(leftOperand,rightOperand,performAction);   				  				   	
				   	 
				   	 label.setText(""+result);
				   	 reset();
			   }
		}
	}
	
	   private void ArithButtonPressed(JButton source) {
	
		   if (opCounter == 1) {
			   performAction = getFunction(source);   //retrieve operator
		   } else {
			   secondAction = getFunction(source);	  //store secondary secondary operator when multiple exist 		  			  
		   }
		   		  
			   
			   if (leftOpIsSet) { //if left value is set
				   if (rightOpIsSet) { // if right value is set
					   
					   if (opCounter > 1) { // multiple operators												   
						   
						    result = PerformAction(leftOperand, rightOperand, performAction); // perform first operator on operands
						    performAction = secondAction;                       //store second operator in primary variable
						    leftOperand = (int)result;                          //store result in left operand, set right operand to null
						    rightOperand = 0;
						    rightOpIsSet = false;						    
						    
						    label.setText(""+result);  
					   } 
				   }    
				   
			   } else {          //if left value is empty
				   		leftOperand = 0;
				   		leftOpIsSet = true;
			   }	   			
	   }
	   
	   private int PerformAction(int leftOp, int rightOp, String action) { //construct equations and return results
		   int result = 0;		   
		   
		        if (action == "Add")      { result = leftOp + rightOp;}
		   else if (action == "Subtract") { result = leftOp - rightOp;}
		   else if (action == "Multiply") { result = leftOp * rightOp;}
		   else if (action == "Divide")   { result = leftOp / rightOp;}		   
		   return result;
	   }
	
	
	
		private void DigitButtonPressed(JButton source) {
			int setNum = 0;
			String tempStr;			
			
			setNum = getValue(source); //get corresponding number			
			
					if (operatorIsSet) { // if + - * /  already in operation                         EX:  + 						
						
							if (leftOpIsSet) {   //if left Operand is set                            EX: 12 +
								 
									if (rightOpIsSet) { // if right operand value exists then append EX: 12 + 3
										                                                      // RESULT: 12 + 35
										 tempStr = rightOperand+""+setNum;
										 rightOperand = Integer.parseInt(tempStr);
										 result = rightOperand;
									
									} else { //if right operand value is empty then add              EX: 12 + __										                                                      																				
											 rightOperand = setNum;                               // RESULT: 12 + 5
										     result = rightOperand;
										     rightOpIsSet = true;
									}
									
							}	else {  //if left operand is not set                                EX: __ +
								
								
										leftOperand = 0;                                     // RESULT: 0 + 5 
										rightOperand = setNum;
										leftOpIsSet = true;
										rightOpIsSet = true;
							}
						
					} else { //if operator is not set
							 if (leftOpIsSet) {                          //if left operand value exists then append								
								 
								                tempStr = leftOperand+""+setNum;
								                leftOperand = Integer.parseInt(tempStr);
								 				result = leftOperand;
							 } else {                                   // if left operand value doesn't exist then add								 
								 
							 			leftOperand = setNum;
							 			result = leftOperand;
							 			leftOpIsSet = true;
							 }
						
						   }	
				
			label.setText(""+result);
		
	      }
		
		private int getValue(JButton numPressed) {  //find value corresponding with button pressed
			int setNum = 0;
			
			if (numPressed == b9) { setNum = 9;}
			else if (numPressed == b8) { setNum = 8;}
			else if (numPressed == b7) { setNum = 7;}
			else if (numPressed == b6) { setNum = 6;}
			else if (numPressed == b5) { setNum = 5;}
			else if (numPressed == b4) { setNum = 4;}
			else if (numPressed == b3) { setNum = 3;}
			else if (numPressed == b2) { setNum = 2;}
			else if (numPressed == b1) { setNum = 1;}
			else if (numPressed == b0) { setNum = 0;}
			
			return setNum;
		}
		
		private String getFunction(JButton arithPressed) {  //fetch command corresponding to button-press received
			String doThis = "";
			
			if      (arithPressed == bDiv)  { doThis = "Divide";}
			else if (arithPressed == bMult) { doThis = "Multiply";}
			else if (arithPressed == bSub)  { doThis = "Subtract";}
			else if (arithPressed == bAdd)  { doThis = "Add";}
			
			return doThis;
		}

	}
