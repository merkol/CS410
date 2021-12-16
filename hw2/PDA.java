import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



import java.util.ArrayList;
import java.io.*;
public class PDA{

    public int numberOfInputAlph;
    public int numberofStackAlph;
    public int numberOfGoalStates;
    public int numberOfStates;
    public String startState; // Tekrar kontrol et array mi tek mi 
    public String[] states;
    public String[] goalStates;
    public String[] stackAlphabet;
    public String[] inputAlphabet;
    public String currentState;
    private ArrayList<Transition> transitions = new ArrayList<Transition>();
    private Map<String, Transition> Map = new HashMap<>();

    File file = new File("input.txt");
	Scanner s = new Scanner(file);
	
    public PDA() throws Exception{
        System.out.println("Enter number of variables in input alphabet: ");
		numberOfInputAlph = s.nextInt();

		System.out.println("Enter number of variables in stack alphabet: ");
		numberofStackAlph = s.nextInt();

		System.out.println("Enter number of Goal States");
		numberOfGoalStates = s.nextInt();

        System.out.println("Enter number of states: ");
        numberOfStates = s.nextInt();

		states = new String[numberOfStates];
		inputAlphabet = new String[numberOfInputAlph];
        stackAlphabet = new String[numberofStackAlph];
		goalStates = new String[numberOfGoalStates];

        for(int i = 0 ; i < 5 ; i ++ ){       // Büyük ihtimalle yanlış mutlaka tekrar bak 
            transitions.add(new Transition());        
        }
		

        System.out.println("Enter the states: ");

        for (int i = 0; i < numberOfStates; i++) {

			String s1 = s.next();
			states[i] = s1;

		}

		

		

        System.out.println("Enter the start state : ");
        startState = s.next();

		currentState = startState;

        System.out.println("Enter the goal states: ");
        for (int i = 0; i < numberOfGoalStates; i++) {

			String s1 = s.next();
			goalStates[i] = s1;

		}

        System.out.println("Enter the stack alphabet: ");
        for (int i = 0; i < numberofStackAlph; i++) {

			String s1 = s.next();
			stackAlphabet[i] = s1;
        }

        System.out.println("Enter the input alphabet: ");
        for (int i = 0; i < numberOfInputAlph; i++) {

			String s1 = s.next();
			inputAlphabet[i] = s1;

		}
        
        System.out.println("Enter the transition");
		
		for (int i = 0; i < transitions.size(); i++) {
				
				transitions.get(i).startState = s.next();
				transitions.get(i).input = s.next();
            	transitions.get(i).pop = s.next();
            	transitions.get(i).push = s.next();
				transitions.get(i).newState = s.next();
				
				}
				

			
				
		
		
		








		
		
        
              //  WRITE THE PDA ALGORITHM HERE


        
		for (Transition t : transitions) {
			String key = t.input + "," + t.startState;

			Map.put(key, t);
			
		}
        



        
        
        
        
    }

    

	public boolean input(String input) {
		String key = input + "," + currentState;
		if(Map.containsKey(key) == true && Map.containsKey("e,"+currentState ) == true)
		{
			
			Transition transition = Map.get(key);
			currentState = transition.newState;
			return true;
			
			
		}
		
		
		
		else if(Map.containsKey(key) == true && Map.containsKey("e,"+currentState ) == false){
			Transition transition = Map.get(key);
			currentState = transition.newState;
			
		}
		else if(Map.containsKey(key) == false){
			if(Map.containsKey("e,"+currentState))
			{
				Transition transition = Map.get("e,"+currentState);
				currentState = transition.newState;
				return true;
				
			}
		}
		return false;
		
	}
	
	
	public boolean input_e(){
		if(Map.containsKey("e,"+currentState) == true){
			
		while(Map.containsKey("e,"+currentState) == true )
		{
			Transition transition = Map.get("e,"+currentState);
			
			currentState = transition.newState;	
			return true;		
		}
	}
	return false;
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
    public static void main(String[] args) throws Exception{
        FileWriter writer = new FileWriter("output.txt");
		BufferedWriter buffer1 = new BufferedWriter(writer);

		String input_1 = new String();
		String input_2 = new String();

		PDA pda = new PDA();
		
		for (Transition t : pda.transitions) {
			System.out.print(t.startState+ " ");
			System.out.print(t.input+" ");
			System.out.print(t.pop+" ");
			System.out.print(t.push+" ");
			System.out.print(t.newState);
			System.out.println(" ");
		}
		
		System.out.println(pda.transitions.size());

		System.out.println("Please enter the inputs");
		
		
		
		input_1 = pda.s.next();
		System.out.println(input_1);
		System.out.println("Please enter the second inputs");
		input_2 = pda.s.next();
		System.out.println(input_2);

		buffer1.write("Route Taken :");
		buffer1.newLine();
		System.out.println("Route Taken :");
		System.out.print(pda.startState + " ");
		for (int i = 0; i < input_1.length(); i++) {
			String s = String.valueOf(input_1.charAt(i));
			
			
			pda.input(s);
			System.out.print(pda.currentState + " ");
			buffer1.write(pda.currentState + " ");
			
		}
		for (int i = 0; i < pda.states.length; i++) {
			
			
			
			if(pda.input_e()){
				System.out.print(pda.currentState + " ");
				buffer1.write(pda.currentState + " ");
			}
			
			
		}

		
		
		buffer1.newLine();
		System.out.println();

		if (pda.isAccepted() == true) {
			System.out.println("Accepted");
			buffer1.write("Accepted");
			buffer1.newLine();
		} else {
			System.out.println("Rejected");
			buffer1.write("Rejected");
			buffer1.newLine();
		}

		pda.reset();

		System.out.println();
		buffer1.write("Route Taken :");
		buffer1.newLine();
		System.out.println("Route Taken :");
		for (int i = 0; i < input_2.length(); i++) {
			String s = String.valueOf(input_2.charAt(i));
			pda.input(s);
			
			System.out.print(pda.currentState + " ");
			buffer1.write(pda.currentState + " ");
			}

			for (int i = 0; i < pda.states.length; i++) {
				
				
				
				if(pda.input_e()){
					System.out.print(pda.currentState + " ");
					buffer1.write(pda.currentState + " ");
				}
				
				
			}
		


		buffer1.newLine();
		System.out.println();
		if (pda.isAccepted() == true) {
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