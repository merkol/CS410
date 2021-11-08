
public class Transition {
	public String input;
	public String startState;
	public String newState;
	
	public Transition(String startState,String input,String newState) {
		this.startState = startState;
		this.input = input;
		this.newState = newState;
	}
}
