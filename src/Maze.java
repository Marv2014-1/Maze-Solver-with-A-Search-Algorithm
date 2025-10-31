import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Hufsah Sandila and Marfn Mlakha 
 *
 * The Maze class will solve a 2D string array that uses ## as a wall,
 * [] as an empty space, and GG as the goal location. It will do this
 * by utilizing a node and a 2D String array in it's constructor.
 */

public class Maze {

	// A priority queue will determine the lowest cost node for expansion
	private PriorityQueue<Node> frontier = new PriorityQueue<Node>();
	// A Linked list will be used to represent the chosen path
	private LinkedList<Node> chosenPath = new LinkedList<Node>();
	// The finish line coordinates
	private int goalX;
	private int goalY;
	// The start location coordinates
	private int currentX;
	private int currentY;
	// A counter for total created nodes
	private int nodeCount;
	// A trigger for when the AI "wins"
	private boolean solved;
	// A 2D String Array that will store maze data and be used for the explored set
	private String[][] stringArr;

	// Initialize the starting position of the maze
	public Maze(String[][] arr) {

		stringArr = arr;

		for (int i = 0; i < stringArr.length; i++) {
			for (int j = 0; j < stringArr[i].length; j++) {
				if (stringArr[i][j].equals("00")) {
					this.currentX = j;
					this.currentY = i;
				}
				if (stringArr[i][j].equals("GG")) {
					this.goalX = j;
					this.goalY = i;
				}
			}
		}
		nodeCount = 0;
		solved = false;
	}

	/**
	 * Solving the maze requires knowing where the start node is.
	 * Based on the location of the starting node, we start peeking 
	 * our surroundings through a priority queue and continuously add
	 * new nodes to the maze until it is solved. Once we reach the end,
	 * we trace our way back and add items to a linked list that
	 * traces the right path.
	 */
	public void SolveMaze() {

		// we must first create the first node that has no direction and a null parent.
		if (frontier.isEmpty()) {
			Node start = new Node(currentX, currentY, nodeCount, null, 0);
			nodeCount++;
			frontier.add(start);
		}

		while (!solved) {
			createNode(frontier.poll());
		}
		
		Node endNode = frontier.poll();
		
		solveChosenPath(endNode);
		printChosenPath();
	}

	/**
	 * Create a new node based on the current parent's location.
	 * We peek in this order: left, top, right, and then down.
	 * This order is used to number the nodes and the order they are
	 * added to the priority queue. 
	 * @param parent
	 */
	public void createNode(Node parent) {
		if (nodeCount != 0) {
			// search left position of currentX, currentY
			if (parent.getX() != 0 && (stringArr[parent.getY()][parent.getX() - 1].equals("[]")
					|| stringArr[parent.getY()][parent.getX() - 1].equals("GG"))) {
				Node west = new Node(parent.getX() - 1, parent.getY(), nodeCount, parent, 1);

				updateMaze(parent.getX() - 1, parent.getY(), FormatNumber(), west.getF());
				
				if (west.getX() == this.goalX && west.getY() == this.goalY) {
					solved = true;
				} else {
					nodeCount++;
				}

				frontier.add(west);
			}
			// search top position of currentX, currentY
			if (parent.getY() != 0 && (stringArr[parent.getY() - 1][parent.getX()].equals("[]")
					|| stringArr[parent.getY() - 1][parent.getX()].equals("GG"))) {
				Node north = new Node(parent.getX(), parent.getY() - 1, nodeCount, parent, 2);

				updateMaze(parent.getX(), parent.getY() - 1, FormatNumber(), north.getF());
				
				if (north.getX() == this.goalX && north.getY() == this.goalY) {
					solved = true;
				} else {
					nodeCount++;
				}

				frontier.add(north);
			}
			// search right position of currentX, currentY
			if (parent.getX() != stringArr[parent.getY()].length
					&& (stringArr[parent.getY()][parent.getX() + 1].equals("[]")
							|| stringArr[parent.getY()][parent.getX() + 1].equals("GG"))) {
				Node east = new Node(parent.getX() + 1, parent.getY(), nodeCount, parent, 3);

				updateMaze(parent.getX() + 1, parent.getY(), FormatNumber(), east.getF());
				
				if (east.getX() == this.goalX && east.getY() == this.goalY) {
					solved = true;
				} else {
					nodeCount++;
				}

				frontier.add(east);
			}
			// search Bottom position of currentX, currentY
			if (currentY != stringArr.length && (stringArr[parent.getY() + 1][parent.getX()].equals("[]")
					|| stringArr[parent.getY() + 1][parent.getX()].equals("GG"))) {
				Node south = new Node(parent.getX(), parent.getY() + 1, nodeCount, parent, 4);

				updateMaze(parent.getX(), parent.getY() + 1, FormatNumber(), south.getF());

				if (south.getX() == this.goalX && south.getY() == this.goalY) {
					solved = true;
				} else {
					nodeCount++;
				}
				frontier.add(south);
			}
		}
	}
	
	// Format the String to represent the node number
	public String FormatNumber() {
		String temp;

		if (nodeCount < 10) {
			temp = "0" + nodeCount;
		} else {
			temp = "" + nodeCount;
		}

		return temp;
	}

	// Update the maze to see the AI's current state/vision, then display
	// the maze on console for each step
	public void updateMaze(int x, int y, String content, int f) {
		stringArr[y][x] = content + "/" + f;
		for (int i = 0; i < stringArr.length; i++) {
			for (int j = 0; j < stringArr[i].length; j++) {
				System.out.print(stringArr[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("______________________________________________________________________________________________");
	}
	
	// We work our way backwards from the final node to the start location and save the path
	public void solveChosenPath(Node endNode) {
		Node temp = endNode;
		while(temp.getParent() != null) {
			chosenPath.addFirst(temp);
			temp = temp.getParent();
		}
	}
	
	// Display the chosen path to the console
	public void printChosenPath() {
		System.out.println("\n" + "The chosen path for the AI to take is the following nodes:");
		
		String output = "";
		int counter = 0;
		while(!chosenPath.isEmpty()) {
			output += chosenPath.removeFirst().getN() + ", ";
			
			if(counter > 25) {
				output += "\n";
				counter = 0;
			}
			
			counter++;
		}
		
		output = output.substring(0, output.length()-1);
		
		System.out.println("\n" + output);
		
		System.out.println("______________________________________________________________________________________________");
	}

	public int getGoalX() {
		return goalX;
	}

	public void setGoalX(int goalX) {
		this.goalX = goalX;
	}

	public int getGoalY() {
		return goalY;
	}

	public void setGoalY(int goalY) {
		this.goalY = goalY;
	}

	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}

	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}

	public void Type(int x, int y) {
		System.out.println(stringArr[x][y]);
	}

}
