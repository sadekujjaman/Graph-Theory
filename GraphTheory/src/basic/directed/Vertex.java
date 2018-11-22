package basic.directed;

public class Vertex {
	private char label;
	private boolean wasVisited;

	public Vertex(char label) {
		this.label = label;
		wasVisited = false;
	}
	public char getLabel(){
		return label;
	}
	
	public void setLabel(char label){
		this.label = label;
	}

}
