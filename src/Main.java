/**
 * @author Hufsah Sandila and Marfn Mlakha 
 *
 * This Main class will create a 2D string array that uses ## as a wall,
 * [] as an empty space, and GG as the goal location. This array will be
 * passed into the Maze class and will start the solving process.
 */

public class Main {
	
	private static String[][] mazeArr = {  { "##", "##", "##", "00", "##", "##", "##", "##", "##", "##", "##", "##" },
										   { "##", "[]", "[]", "[]", "[]", "##", "[]", "[]", "[]", "[]", "[]", "##" },
										   { "##", "[]", "##", "##", "[]", "##", "[]", "##", "##", "##", "[]", "##" },
										   { "##", "[]", "##", "[]", "[]", "##", "[]", "[]", "[]", "##", "[]", "GG" },
										   { "##", "[]", "##", "##", "##", "##", "##", "##", "[]", "##", "##", "##" },
										   { "##", "[]", "[]", "##", "[]", "[]", "[]", "##", "[]", "[]", "[]", "##" },
										   { "##", "##", "[]", "##", "[]", "##", "##", "##", "[]", "##", "[]", "##" },
										   { "##", "[]", "[]", "[]", "[]", "##", "[]", "[]", "[]", "##", "[]", "##" },
										   { "##", "[]", "##", "##", "##", "##", "[]", "##", "[]", "##", "##", "##" },
										   { "##", "[]", "[]", "[]", "[]", "[]", "[]", "##", "[]", "[]", "[]", "##" },
										   { "##", "##", "##", "##", "##", "##", "##", "##", "##", "##", "##", "##" } };

	private static Maze m = new Maze(mazeArr);

	public static void main(String[] args) {

		m.SolveMaze();

	}
	
	//return the x value of the finish line
	public static int goalX() {
		return m.getGoalX();
	}
	
	//return the y value of the finish line
	public static int goalY() {
		return m.getGoalY();
	}

}
