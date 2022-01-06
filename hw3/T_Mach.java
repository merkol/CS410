import java.util.HashMap;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class T_Mach {
    public int numberOfInputAlph;
    public int numberOfTapeAlph;
    public int numberOfStates;
    public String[] states;
    public String currentState;
    public String startState;
    public String acceptState;
    public String rejectState;
    public String blankSymbol;
    public String[] tapeAlphabet;
    public String[] inputAlphabet;
    private ArrayList<Transition> transitions = new ArrayList<Transition>();
    private HashMap<String, Transition> Map = new HashMap<String, Transition>();
    StringBuffer tape = new StringBuffer();
    File file = new File("input.txt");
    FileWriter file1 = new FileWriter("output.txt");
    BufferedWriter buffer1 = new BufferedWriter(file1);
    Scanner s = new Scanner(file);
    Scanner s12 = new Scanner(file);

    public T_Mach() throws Exception {
        System.out.println("Enter number of variables in input alphabet: ");
        numberOfInputAlph = s.nextInt();
        s12.nextInt();

        System.out.println("Enter number of variables in tape alphabet: ");
        numberOfTapeAlph = s.nextInt();
        s12.nextInt();

        System.out.println("Enter number of states: ");
        numberOfStates = s.nextInt();
        s12.nextInt();

        states = new String[numberOfStates];
        inputAlphabet = new String[numberOfInputAlph];
        tapeAlphabet = new String[numberOfTapeAlph + 1];

        System.out.println("Enter the states: ");

        for (int i = 0; i < numberOfStates; i++) {

            String s1 = s.next();
            s12.next();
            states[i] = s1;

        }

        System.out.println("Enter the start state : ");
        startState = s.next();
        s12.next();

        currentState = startState;

        System.out.println("Enter the accept state: ");
        acceptState = s.next();
        s12.next();

        System.out.println("Enter the reject state: ");
        rejectState = s.next();
        s12.next();

        System.out.println("Enter the blank symbol : ");
        blankSymbol = s.next();
        s12.next();

        System.out.println("Enter the tape alphabet: ");
        for (int i = 0; i < numberOfTapeAlph + 1; i++) {

            String s1 = s.next();
            s12.next();
            tapeAlphabet[i] = s1;

        }
        System.out.println("Enter the input alphabet: ");
        for (int i = 0; i < numberOfInputAlph; i++) {

            String s1 = s.next();
            s12.next();
            inputAlphabet[i] = s1;
        }

        System.out.println("Enter the transition");

        s12.nextLine();
        outerloop:
        while (s12.hasNextLine() == true) {
            String line = s12.nextLine();
            for (String check : inputAlphabet) {
                if (line.isEmpty() || line.startsWith(check) ) {
                    break outerloop;
                }
            }
           
            transitions.add(new Transition());

        }

        for (int i = 0; i < transitions.size(); i++) {
            transitions.get(i).startState = s.next();
            transitions.get(i).input = s.next();
            transitions.get(i).tape_w = s.next();
            transitions.get(i).tape_d = s.next();
            transitions.get(i).newState = s.next();
        }

        for (Transition t : transitions) {
            String key = t.input + "," + t.startState;

            Map.put(key, t);

        }

    }

    public void input(String s)throws Exception {
        int index = 0;
        if(!s.equals(" ")){
        for (int i = 0; i < s.length(); i++) {
            String parsed = String.valueOf(s.charAt(i));
            tape.replace(index, index+1, parsed);
            index += 1;
        }
    }
        index = 0;

        for (int i = 0; i < tape.length(); i++) {
            char c = tape.charAt(index);
            String converted = String.valueOf(c);
            String key = converted + "," + currentState;
            Transition transition = Map.get(key);
          
            if (currentState.equals(acceptState) || currentState.equals(rejectState)) {
               
                break;
            }
           
            tape.replace(index, index+1, transition.tape_w);
            if (transition.tape_d.equals("R")) {
                index++;
            } else if (transition.tape_d.equals("L")) {
                index--;
            }
            
             currentState = transition.newState;
             System.out.print(currentState+ " ");
             buffer1.write(currentState + " ");
           

        }
    }

    public void reset() {
        currentState = startState;
    }

    public boolean isAccepted() {

        if (currentState.equals(acceptState)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        
        

        String input_1 = new String();
        String input_2 = new String();

        T_Mach machine = new T_Mach();
       
        System.out.println("Please enter the inputs");

        if (machine.s.hasNext()) {
            input_1 = machine.s.next();
        } else {
            input_1 = " ";
        }

        System.out.println(input_1);
        System.out.println("Please enter the second inputs");
        if (machine.s.hasNext()) {
            input_2 = machine.s.next();
        } else {

            input_2 = " ";
        }
        System.out.println(input_2);



    ////
    for (int i = 0; i < 16; i++) {
        machine.tape.append(machine.blankSymbol);
    }
        machine.buffer1.write("Route Taken :");
        machine.buffer1.newLine();
        System.out.println("Route Taken :");
        System.out.print(machine.startState + " ");
        machine.buffer1.write(machine.startState + " ");
       
        machine.input(input_1);

        if (machine.isAccepted() == true) {

            machine.buffer1.newLine();
            System.out.println();

            System.out.println("Accepted");
            machine.buffer1.write("Accepted");
            machine.buffer1.newLine();
        } 
         else {
            System.out.println("Rejected");
            machine.buffer1.write("Rejected");
            machine.buffer1.newLine();
        }

        machine.reset();

        machine.tape.delete(0, machine.tape.length());

        for (int i = 0; i < 16; i++) {
            machine.tape.append(machine.blankSymbol);
        }

        System.out.println();
        machine.buffer1.write("Route Taken :");
        machine.buffer1.newLine();
        System.out.println("Route Taken :");
        System.out.print(machine.currentState + " ");
        machine.buffer1.write(machine.currentState + " ");

        machine.input(input_2);

        machine.buffer1.newLine();
        System.out.println();
        if (machine.isAccepted() == true) {
            System.out.println(machine.currentState + " ");
            machine.buffer1.write(machine.currentState + " ");
            machine.buffer1.newLine();
            System.out.println();

            System.out.println("Accepted");
            machine.buffer1.write("Accepted");
            machine.buffer1.newLine();
        } 
         else {
            System.out.println("Rejected");
            machine.buffer1.write("Rejected");
            machine.buffer1.newLine();
        }
        machine.buffer1.close();
    }
}