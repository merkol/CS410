public class Transition {
	public String input;
	public String startState;
	public String newState;
    public String pop;
	public String push;
	
	
	public Transition() {
		
	}
	public Transition(String startState,String input , String pop ,String push , String newState ) {
		this.startState = startState;
		this.input = input;
		this.pop = pop;
		this.push = push;
		this.newState = newState;

	}
}
