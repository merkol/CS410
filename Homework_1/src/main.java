import java.util.Scanner;
public class main {
public static void main(String[] args) {
	Scanner s = new Scanner(System.in);
	
	
	DFA dfa = new DFA(new String[dfa.numberOfStates], 
			new String[dfa.numberOfVariables],
			new String[dfa.numberOfGoalStates],
			
			);
	
	System.out.println("Please enter the inputs");
	String input_1 = s.nextLine();
	System.out.println("Please enter the second inputs");
	String input_2 = s.nextLine();	
	
	String[] arr = input_1.split("");
	for(String ch: arr) {
		dfa.input(ch);
	}
	dfa.reset();
	String[] arr1 = input_2.split("");
	for(String ch: arr1) {
		dfa.input(ch);
	}
	
}
}
