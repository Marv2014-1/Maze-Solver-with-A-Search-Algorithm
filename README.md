# Maze Solver with A* Search Algorithm

## Project Overview

This project implements an **A* pathfinding algorithm** for navigating through a 2D maze. The AI uses heuristic-based search with custom cost functions to find the optimal path from a starting position to a goal location.

## Authors
- Hufsah Sandila
- Marfn Mlakha

## Features

- **A* Search Implementation**: Uses f(n) = g(n) + h(n) to determine the optimal path
- **Custom Heuristics**: Modified Manhattan distance with weighted directional costs
- **Priority Queue**: Efficiently selects the lowest-cost nodes for expansion
- **Visual Feedback**: Displays the maze state after each node expansion
- **Path Tracing**: Backtracks from the goal to show the complete optimal path

## Algorithm Details

### Cost Functions

The algorithm uses three cost components:

1. **f(n)**: Total cost (g(n) + h(n))
2. **g(n)**: Cumulative step cost from start to current node
   - Left movement: cost = 2
   - Up movement: cost = 3
   - Right movement: cost = 2
   - Down movement: cost = 1
3. **h(n)**: Heuristic (Manhattan distance) from current node to goal
   - Horizontal distance weighted by 2
   - Vertical distance weighted by 1 (if y ≤ goal.y) or 3 (if y > goal.y)

### Search Strategy

- Nodes are expanded in order of lowest f(n) value
- The frontier is maintained using a Priority Queue
- Exploration order: Left → Up → Right → Down
- Already visited positions are marked in the maze array

## Project Structure

```
P1/
├── bin/              # Compiled Java classes
├── src/              # Source code
│   ├── Main.java     # Entry point and maze initialization
│   ├── Maze.java     # Maze solving logic and A* implementation
│   └── Node.java     # Node class with cost calculations
└── README.md         # This file
```

## Maze Representation

The maze is represented as a 2D string array with the following symbols:
- `##` - Wall (impassable)
- `[]` - Empty space (passable)
- `00` - Starting position
- `GG` - Goal location
- `XX/Y` - Explored node (XX = node number, Y = f(n) cost)

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line or IDE (Eclipse, IntelliJ IDEA, VS Code, etc.)

### Compilation

Navigate to the project directory and compile the source files:

```bash
javac -d bin src/*.java
```

### Execution

Run the program from the `bin` directory:

```bash
cd bin
java Main
```

Or from the project root:

```bash
java -cp bin Main
```

## Output

The program will:
1. Display the maze state after each node expansion
2. Show node numbers and their f(n) costs
3. Print the final optimal path as a sequence of node numbers

Example output:
```
The chosen path for the AI to take is the following nodes:
1, 5, 10, 15, 21, 27, ...
```

## Classes

### Main.java
- Initializes the maze array
- Creates a `Maze` object and starts the solving process
- Provides static methods to access goal coordinates

### Maze.java
- Implements the A* search algorithm
- Manages the frontier (priority queue) and explored set
- Creates child nodes and updates maze visualization
- Traces back the optimal path from goal to start

### Node.java
- Represents a position in the maze
- Calculates g(n), h(n), and f(n) costs
- Implements `Comparable` interface for priority queue ordering
- Maintains parent reference for path reconstruction

## Algorithm Complexity

- **Time Complexity**: O(b^d) where b is the branching factor (up to 4 directions) and d is the depth of the optimal solution
- **Space Complexity**: O(b^d) for storing nodes in the frontier and explored set

## Educational Value

This project demonstrates key AI concepts:
- Informed search algorithms
- Heuristic design and admissibility
- Priority queue-based node selection
- Graph traversal and pathfinding
- Cost optimization in search problems

## License

This is an academic project created for educational purposes.
