
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class DFA{
	public int numberOfStates;
	public int numberOfGoalStates;
	public int numberOfVariables;
	private String[] states;
	private String[] inputs;
	private String[] goalStates;
	private Map<String,Transition> Map = new HashMap<>();
	private String startState;
	private String currentState;
	
	
	public DFA(String[] states,String[] inputs,String[] goalStates,Transition[] transitions,String startState) {
		this.states=states;
		this.goalStates = goalStates;
		this.inputs= inputs;
		this.startState = startState;
		this.currentState = startState;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter number of states: ");
		String numberOfStates = s.nextLine();
		System.out.println("Enter number of variables");
		String numberOfVariables = s.nextLine();
		System.out.println("Enter number of Goal States");
		String numberOfGoalStates = s.nextLine();
		System.out.println("Enter the states");
		states = new String[this.numberOfStates];
		inputs = new String[this.numberOfVariables];
		goalStates = new String[this.numberOfGoalStates];
		
		for(String s1 : states)
			s1 = s.nextLine();
		System.out.println("Enter the goal states");
		for(String s1 : goalStates)
			s1 = s.nextLine();
		System.out.println("Enter the variables");
		for(String s1 : inputs)
			s1 = s.nextLine();
		for(Transition t : transitions) {
			t.startState = s.nextLine();
			t.input = s.nextLine();
			t.newState = s.nextLine();
		}

			
			
	
		
		
		
		for (Transition t : transitions) {
			String key = getKeyForTransition(t.input, t.startState);
			this.Map.put(key, t);
		}
	}
	
	public DFA input(String input) {
		String key = getKeyForTransition(input, currentState);

        Transition transition = Map.get(key);
   
        currentState = transition.newState;

        return this;
	}
	
	public void reset() {
		this.currentState = this.startState;
	}
	public String getState() {
		return null;
	}
	public boolean isAccepted() {
		for (String i : goalStates) {
			if(currentState==i) {
				return true;
			}
		}
	return false;
	}
	public void printRoute() {
		
	 }
	 private String getKeyForTransition(String input, String state) {
	        return input + "," + state;
	    }
	}