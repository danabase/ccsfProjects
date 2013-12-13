import javax.swing.*;
import java.awt.*;
public class CalculatorProgram {
	/* 12/13/2012
	 * CS111B
	 * Calculator Gui
	 * Dana Bienenfeld
	 * Objective: create a functional calculator
	 */
	
		public static void main(String args[]) {
			//Create the components
			JFrame frame = new JFrame("Calculator");  //frame
			JTextField label = new JTextField("0");   //text field
													//buttons
			JButton b7   = new JButton("7"); 
			JButton b8   = new JButton("8");
			JButton b9 	 = new JButton("9");
			JButton bDiv = new JButton("/");
			
			JButton b4    = new JButton("4");
			JButton b5    = new JButton("5");
			JButton b6    = new JButton("6");
			JButton bMult = new JButton("*");
			
			JButton b1    = new JButton("1");
			JButton b2    = new JButton("2");
			JButton b3    = new JButton("3");
			JButton bSub  = new JButton("-");
			
			JButton b0    = new JButton("0");
			JButton bC    = new JButton("C");
			JButton bAdd  = new JButton("+");
			JButton bEq   = new JButton("=");
			
			//Basic Layout
			frame.getContentPane().setPreferredSize(new Dimension(200,185));
			frame.setLocation(300,300);
			frame.setLayout(new FlowLayout());
			label.setPreferredSize(new Dimension(165, 25));
			label.setHorizontalAlignment(JTextField.CENTER);
			
			//snap components together
			frame.getContentPane().add(label);
			frame.getContentPane().add(b7);
			frame.getContentPane().add(b8);
			frame.getContentPane().add(b9);
			frame.getContentPane().add(bDiv);
			frame.getContentPane().add(b4);
			frame.getContentPane().add(b5);
			frame.getContentPane().add(b6);
			frame.getContentPane().add(bMult);
			frame.getContentPane().add(b1);
			frame.getContentPane().add(b2);
			frame.getContentPane().add(b3);
			frame.getContentPane().add(bSub);
			frame.getContentPane().add(b0);
			frame.getContentPane().add(bC);
			frame.getContentPane().add(bAdd);		
			frame.getContentPane().add(bEq);	
			
			//create the controller
			calculatorController controller = new calculatorController(label,
																	   b7,b8,b9,bDiv,
																	   b4,b5,b6,bMult,
																	   b1,b2,b3,bSub,
																	   b0,bC,bEq,bAdd);
			
			//register the controller with the buttons
			b7.addActionListener(controller);
			b8.addActionListener(controller);
			b9.addActionListener(controller);
			bDiv.addActionListener(controller);
			
			b4.addActionListener(controller);
			b5.addActionListener(controller);
			b6.addActionListener(controller);
			bMult.addActionListener(controller);
			
			b1.addActionListener(controller);
			b2.addActionListener(controller);
			b3.addActionListener(controller);
			bSub.addActionListener(controller);
			
			b0.addActionListener(controller);
			bC.addActionListener(controller);
			bAdd.addActionListener(controller);
			bEq.addActionListener(controller);
			
			//pack it all in and set visible
			frame.pack();
			frame.setVisible(true);
		}

}
