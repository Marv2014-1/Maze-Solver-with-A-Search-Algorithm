/**
 * @author Hufsah Sandila and Marfn Mlakha 
 *
 * The Node Class implements the A* search algorithm to determine the current
 * position's cost from the end goal location and from the starting location.
 * This node also links to it's parent in order to traverse back once the
 * optimal path is found.
 * 
 * The formula used is f(n) = g(n) + h(n)
 * where f(n) is the total cost from the node's location to the finish line,
 * h(n) is the Manhattan distance, and g(n) is the cumulative step cost.
 * 
 * h(n) = |x1 - x2| + |y1 - y2|
 * g(n) is determined by the direction moved:
 *   left  = 2
 *   top = 3
 *   right = 2
 *   down = 1
 * 
 * The Manhattan distance is calculated by taking the absolute value of the difference
 * between the x and y coordinates of two points.
 */

public class Node implements Comparable<Node>{

	// n will be the Node's number
	private int n;
	// f(n) = h(n) + g(n) is the total cost from the node's location to the finish line
	private int f;
	// h(n) is the Manhattan distance
	private int h;
	// g(n) is the cumulative step cost
	private int g;
	// The x coordinate
	private int x;
	// The y coordinate
	private int y;

	// Number value for direction: 1 = left, 2 = top, 3 = right, 4 = down.
	private int direction;

	// The node has pointers to parent and child in 
	// order to traverse back when it reaches the end
	private Node parent;

	/**
	 * Constructor that will take in values and calculate costs (x and y coordinates,)
	 * @param x
	 * @param y
	 * @param n
	 * @param parent
	 * @param direction
	 */
	public Node(int x, int y, int n, Node parent, int direction) {
		this.x = x;
		this.y = y;
		this.n = n;
		this.parent = parent;
		this.direction = direction;

		calcH();
		calcG();
		calcF();
	}

	/**
	 * calculate the Manhattan distance based on the current node location
	 * and it's Manhattan cost from the finish line
	 */
	private void calcH() {
		// If Y value is less than or equal to 3, multiply the Y axis by 1
		if (this.y <= Main.goalY())
			this.h = Math.abs(2 * (this.x - Main.goalX())) + Math.abs(1 * (this.y - Main.goalY()));
		// If Y value is greater than 3, multiply the Y axis by 3
		else if (this.y > Main.goalY()) {
			this.h = Math.abs(2 * (this.x - Main.goalX())) + Math.abs(3 * (this.y - Main.goalY()));
		}
	}

	/**
	 * Calculate the cumulative step costs by adding the current
	 * step direction cost to the previous parent
	 */
	private void calcG() {
		if (direction == 1) {
			g = parent.getG() + 2;
		} else if (direction == 2) {
			g = parent.getG() + 3;
		} else if (direction == 3) {
			g = parent.getG() + 2;
		} else if (direction == 4) {
			g = parent.getG() + 1;
		} else {
			g = 0;
		}
	}

	/**
	 * Once g(n) and h(n) are known, we can add them to find f(n)
	 */
	private void calcF() {
		f = g + h;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	@Override
	public int compareTo(Node node) {
		if(this.f - node.getF() > 0) {
			return 1;
		}else if(this.f - node.getF() < 0) {
			return -1;
		}
		return 0;
	}



}
