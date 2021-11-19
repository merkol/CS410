package hw1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class DFA {

	public int numberOfStates;
	public int numberOfGoalStates;
	public int numberOfVariables;
	public String[] states;
	public String[] inputs;
	public String[] goalStates;
	private ArrayList<Transition> transitions = new ArrayList<Transition>();
	private Map<String, Transition> Map = new HashMap<>();
	private String startState;
	private String currentState;

	File file = new File("input.txt");
	Scanner s = new Scanner(file);
	

	public DFA() throws Exception {

		System.out.println("Enter number of states: ");
		numberOfStates = s.nextInt();

		System.out.println("Enter number of variables");
		numberOfVariables = s.nextInt();

		System.out.println("Enter number of Goal States");
		numberOfGoalStates = s.nextInt();

		states = new String[numberOfStates];
		inputs = new String[numberOfVariables];
		goalStates = new String[numberOfGoalStates];

		for (int i = 0; i < numberOfStates * 2; i++) {
			transitions.add(new Transition());
		}

		System.out.println("Enter the states");

		for (int i = 0; i < numberOfStates; i++) {

			String s1 = s.next();
			states[i] = s1;

		}

		startState = states[0];

		currentState = startState;

		System.out.println("Enter the goal states");

		for (int i = 0; i < goalStates.length; i++) {
			String s1 = s.next();
			goalStates[i] = s1;
		}

		System.out.println("Enter the variables");

		for (int i = 0; i < inputs.length; i++) {
			String s1 = s.next();
			inputs[i] = s1;
		}

		System.out.println("Enter the transition");

		for (int i = 0; i < transitions.size(); i++) {

			transitions.get(i).startState = s.next();
			transitions.get(i).input = s.next();
			transitions.get(i).newState = s.next();

		}
		

		for (Transition t : transitions) {
			String key = t.input + "," + t.startState;

			Map.put(key, t);
		}
	}

	public void input(String input) {
		String key = input + "," + currentState;

		Transition transition = Map.get(key);

		currentState = transition.newState;

	}

	public boolean isAccepted() {
		for (String goalState : goalStates) {
			if (goalState.contains(currentState)) {
				return true;
			}
		}
		return false;
	}

	public void reset() {
		currentState = startState;
	}

	public static void main(String[] args) throws Exception {
		FileWriter writer = new FileWriter("output.txt");
		BufferedWriter buffer1 = new BufferedWriter(writer);

		String input_1 = new String();
		String input_2 = new String();

		DFA dfa = new DFA();

		System.out.println("Please enter the inputs");
		input_1 = dfa.s.next();
		System.out.println(input_1);
		System.out.println("Please enter the second inputs");
		input_2 = dfa.s.next();
		System.out.println(input_2);

		buffer1.write("Route Taken :");
		buffer1.newLine();
		System.out.println("Route Taken :");
		for (int i = 0; i < input_1.length(); i++) {
			String s = String.valueOf(input_1.charAt(i));
			dfa.input(s);
			System.out.print(dfa.currentState + " ");
			buffer1.write(dfa.currentState + " ");

		}
		buffer1.newLine();
		System.out.println();

		if (dfa.isAccepted() == true) {
			System.out.println("Accepted");
			buffer1.write("Accepted");
			buffer1.newLine();
		} else {
			System.out.println("Rejected");
			buffer1.write("Rejected");
			buffer1.newLine();
		}

		dfa.reset();

		System.out.println();
		buffer1.write("Route Taken :");
		buffer1.newLine();
		System.out.println("Route Taken :");
		for (int i = 0; i < input_2.length(); i++) {
			String s = String.valueOf(input_2.charAt(i));
			dfa.input(s);
			System.out.print(dfa.currentState + " ");
			
			buffer1.write(dfa.currentState + " ");

		}
		buffer1.newLine();
		System.out.println();
		if (dfa.isAccepted() == true) {
			System.out.println("Accepted");
			buffer1.write("Accepted");
			buffer1.newLine();
		} else {
			System.out.println("Rejected");
			buffer1.write("Rejected");
			buffer1.newLine();
		}
		buffer1.close();

	}
}