//777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777
//                               				V.W.F Mattana 21128707
//777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777
import java.awt.*;
import javax.swing.*;
import java.util.*;
//======================================================================================================
//                             							MAZE
//======================================================================================================

public class Maze extends JFrame
{
	public static int ROWS = 70;
	public static int COLUMNS = 70;
	public static int TOTAL_BLOCKS = ROWS*COLUMNS;
	public Block currentBlock, start, end, head, rowFront;
	public int visitedBlocks;
	JPanel mazePanel = new JPanel(new GridLayout(ROWS + (ROWS+1),COLUMNS + (COLUMNS+1)));
	JLabel[][] label = new JLabel[ROWS + (ROWS+1)][COLUMNS + (COLUMNS+1)];
	Stack solutionStack = new Stack();
	Coordinate loc = new Coordinate(-1,-1);

	public Maze()
	{
		
		for(int j = 0; j < ROWS + (ROWS+1); j++) 
		{
			int k = j;
			for(int i = 0; i < COLUMNS + (COLUMNS+1); i++) 
			{
				label[i][j] = new JLabel();
				label[i][j].setOpaque(true);
				k++;
				label[i][j].setBackground(Color.BLACK);
				mazePanel.add(label[i][j]);
			}
		}
	
		JFrame mazeFrame = new JFrame("Maze");
		mazeFrame.add(mazePanel, BorderLayout.CENTER);
		mazeFrame.setSize(723, 745);
		mazeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mazeFrame.setVisible(true);
	}

	public void setupGrid()
	{
		Block newBlock = new Block(0,0);
		currentBlock = head = start = rowFront = newBlock; // Set Markers
		for(int i=0;i<ROWS;i++) // ROWS
		{
			rowFront = head;	
			for(int j=0;j<COLUMNS;j++) // COLUMNS
			{
				if(i==0)// First Row
				{
					if(j<COLUMNS-1) // First Nine Blocks
					{
						currentBlock.e = new Block(i,j+1);
						currentBlock.e.w = currentBlock;
						currentBlock.s = new Block(i+1,j);
						currentBlock.s.n = currentBlock;
						currentBlock = currentBlock.e;
					}
					else //if(j==9) // Last Block
					{
						currentBlock.s = new Block(i+1,j);
						currentBlock.s.n = currentBlock;
					}
				}
				else if(i==ROWS-1) // Last Row
				{
					if(j<COLUMNS-1) // First Nine Blocks
					{
						currentBlock.e = currentBlock.n.e.s;
						currentBlock.e.w = currentBlock;
						currentBlock = currentBlock.e;
					}
				}
				else// if(i>0 && i<9) // Center Rows
				{
					if(j<COLUMNS-1) // First Nine Blocks
					{
						currentBlock.e = currentBlock.n.e.s;
						currentBlock.e.w = currentBlock;
						currentBlock.s = new Block(i+1,j);
						currentBlock.s.n = currentBlock;
						currentBlock = currentBlock.e;
					}
					else //if(j==9) // Last Block
					{
						currentBlock.s = new Block(i+1,j);
						currentBlock.s.n = currentBlock;
					}
				}
			}
			for(int k=0;k<=i;k++) // Shift Row-Start Pointer
			{
				if(rowFront.s != null)
					rowFront = rowFront.s;
			}
			currentBlock = rowFront;
		}
	}
	
	public Block findBlock(int x, int y)
	{
		currentBlock = head;
		for(int i=0;i<x;i++)
		{
			currentBlock = currentBlock.s;
		}
		for(int j=0;j<y;j++)
		{
			currentBlock = currentBlock.e;
		}
		return currentBlock;
	}
	
	public void createMaze()
	{
		visitedBlocks = 1;
		Random rnd1 = new Random();
		int x = rnd1.nextInt(ROWS);
		int y = rnd1.nextInt(COLUMNS);
		currentBlock = start = findBlock(x, y);
		
		loc.x = (start.blockNumber.x*2);
		loc.y = (start.blockNumber.y*2);
		label[loc.x][loc.y].setBackground(Color.GREEN);

		SLL available = new SLL();
		Stack blockStack = new Stack();

		int length = 0;

		while(visitedBlocks<TOTAL_BLOCKS)
		{
			if(currentBlock!= start)
			{
				loc.x = (currentBlock.blockNumber.x*2);
				loc.y = (currentBlock.blockNumber.y*2);
				label[loc.x][loc.y].setBackground(Color.RED);
			}
				
			currentBlock.visited = true;
			
			if(currentBlock.hasUnvisitedNeighbours())
			{
				if(currentBlock.n != null && currentBlock.n.visited != true) 
				{
					available.addToTail(currentBlock.n);
				}
				if(currentBlock.w != null && currentBlock.w.visited != true) 
				{
					available.addToTail(currentBlock.w);
				}
				if(currentBlock.s != null && currentBlock.s.visited != true) 
				{
					available.addToTail(currentBlock.s);
				}
				if(currentBlock.e != null && currentBlock.e.visited != true) 
				{
					available.addToTail(currentBlock.e);
				}
				
				length = available.length();
				Block selected;
				Random rnd2 = new Random();
				int value = rnd2.nextInt(length);
				selected = available.element(value);
				available.clear();
				
				
				//WAIT FOR USER INPUT
				//Scanner sc = new Scanner(System.in);
				//String choice = sc.nextLine();
				
				try {
				    Thread.sleep(400);
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}

				if(selected == currentBlock.n)
				{
					currentBlock.nWall = selected.sWall = false;
					label[loc.x-1][loc.y].setBackground(Color.WHITE);
					if(currentBlock!= start)
					{
						label[loc.x][loc.y].setBackground(Color.WHITE);
					}
					blockStack.push(currentBlock);
					currentBlock = selected;
					visitedBlocks++;
				}
				else if(selected == currentBlock.w)
				{
					currentBlock.wWall = selected.eWall = false;
					label[loc.x][loc.y-1].setBackground(Color.WHITE);
					if(currentBlock!= start)
					{
						label[loc.x][loc.y].setBackground(Color.WHITE);
					}
					blockStack.push(currentBlock);
					currentBlock = selected;
					visitedBlocks++;
				}
				else if(selected == currentBlock.s)
				{
					currentBlock.sWall = selected.nWall = false;
					label[loc.x+1][loc.y].setBackground(Color.WHITE);
					if(currentBlock!= start)
					{
						label[loc.x][loc.y].setBackground(Color.WHITE);
					}
					blockStack.push(currentBlock);
					currentBlock = selected;
					visitedBlocks++;
				}
				else if(selected == currentBlock.e)
				{
					currentBlock.eWall = selected.wWall = false;
					label[loc.x][loc.y+1].setBackground(Color.WHITE);
					if(currentBlock!= start)
					{
						label[loc.x][loc.y].setBackground(Color.WHITE);
					}
					blockStack.push(currentBlock);
					currentBlock = selected;
					visitedBlocks++;
				}
				mazePanel.validate();
			}
			else 
			{
				label[loc.x][loc.y].setBackground(Color.WHITE);
				currentBlock = blockStack.pop();
			}
		}
		Random rnd3 = new Random();
		x = rnd3.nextInt(ROWS);
		y = rnd3.nextInt(COLUMNS);
		end = findBlock(x, y);
		
		loc.x = (end.blockNumber.x*2);
		loc.y = (end.blockNumber.y*2);
		label[loc.x][loc.y].setBackground(Color.RED);
	}
	
	public boolean solveMaze(Block currentBlock)
	{
		if(currentBlock == null)
		{
			return false;
		}
		if(currentBlock.empty == false)
		{
			return false;
		}
		if(currentBlock != start)
		{
			if((solutionStack.peek() == currentBlock.s) && (currentBlock.sWall == true))
			{
				return false;
			}
			if((solutionStack.peek() == currentBlock.e) && (currentBlock.eWall == true))
			{
				return false;
			}
			if((solutionStack.peek() == currentBlock.n) && (currentBlock.nWall == true))
			{
				return false;
			}
			if((solutionStack.peek() == currentBlock.w) && (currentBlock.wWall == true))
			{
				return false;
			}
		}
		if(currentBlock == end)
		{
			return true;
		}
		solutionStack.push(currentBlock);
		currentBlock.solution = true;
		currentBlock.empty = false;
		colour();


		//Wait for user input
		//Scanner sc = new Scanner(System.in);
		//String choice = sc.nextLine();

		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}


		if(solveMaze(currentBlock.n) == true)
		{
			return true;
		}
		if(solveMaze(currentBlock.w) == true)
		{
			return true;
		}
		if(solveMaze(currentBlock.s) == true)
		{
			return true;
		}
		if(solveMaze(currentBlock.e) == true)
		{
			return true;
		}
		solutionStack.pop();
		currentBlock.solution = false;
		return false;
	}
	
	public Block getStart()
	{
		return start;
	}
	
	public void colour()
	{
		Block tmpBlock;
		for(int i=0;i<ROWS;i++) // ROWS
		{
			for(int j=0;j<COLUMNS;j++) // COLUMNS
			{
				tmpBlock = findBlock(i,j);
				loc.x = (tmpBlock.blockNumber.x*2);
				loc.y = (tmpBlock.blockNumber.y*2);
				if(tmpBlock.solution == true)
				{
					label[loc.x][loc.y].setBackground(Color.BLUE);
				}
				if((tmpBlock.solution == false) && (tmpBlock.empty == false))
				{
					label[loc.x][loc.y].setBackground(Color.GRAY);
				}
				
				if((tmpBlock.n != null) && (tmpBlock.solution == true) && (tmpBlock.n.solution == true) && (tmpBlock.nWall == false) )
				{
					label[loc.x-1][loc.y].setBackground(Color.BLUE);
				}
				if((tmpBlock.n != null) && (tmpBlock.solution == false) && (tmpBlock.empty == false) && (tmpBlock.n.solution == false) && (tmpBlock.nWall == false) )
				{
					label[loc.x-1][loc.y].setBackground(Color.GRAY);
				}
				if((tmpBlock.n != null) && (tmpBlock.solution == true) && (tmpBlock.empty == false) && (tmpBlock.n.solution == false) && (tmpBlock.n.empty == false) && (tmpBlock.nWall == false) )
				{
					label[loc.x-1][loc.y].setBackground(Color.GRAY);
				}
				
				if((tmpBlock.w != null) && (tmpBlock.solution == true) && (tmpBlock.w.solution == true) && (tmpBlock.wWall == false) )
				{
					label[loc.x][loc.y-1].setBackground(Color.BLUE);
				}
				if((tmpBlock.w != null) && (tmpBlock.solution == false) && (tmpBlock.empty == false) && (tmpBlock.w.solution == false) && (tmpBlock.wWall == false) )
				{
					label[loc.x][loc.y-1].setBackground(Color.GRAY);
				}
				if((tmpBlock.w != null) && (tmpBlock.solution == true) && (tmpBlock.empty == false) && (tmpBlock.w.solution == false) && (tmpBlock.w.empty == false) && (tmpBlock.wWall == false) )
				{
					label[loc.x][loc.y-1].setBackground(Color.GRAY);
				}
				
				if((tmpBlock.s != null) && (tmpBlock.solution == true) && (tmpBlock.s.solution == true) && (tmpBlock.sWall == false) )
				{
					label[loc.x+1][loc.y].setBackground(Color.BLUE);
				}
				if((tmpBlock.s != null) && (tmpBlock.solution == false) && (tmpBlock.empty == false) && (tmpBlock.s.solution == false) && (tmpBlock.sWall == false) )
				{
					label[loc.x+1][loc.y].setBackground(Color.GRAY);
				}
				if((tmpBlock.s != null) && (tmpBlock.solution == true) && (tmpBlock.empty == false) && (tmpBlock.s.solution == false) && (tmpBlock.s.empty == false) && (tmpBlock.sWall == false) )
				{
					label[loc.x+1][loc.y].setBackground(Color.GRAY);
				}
				
				if((tmpBlock.e != null) && (tmpBlock.solution == true) && (tmpBlock.e.solution == true) && (tmpBlock.eWall == false) )
				{
					label[loc.x][loc.y+1].setBackground(Color.BLUE);
				}
				if((tmpBlock.e != null) && (tmpBlock.solution == false) && (tmpBlock.empty == false) && (tmpBlock.e.solution == false) && (tmpBlock.eWall == false) )
				{
					label[loc.x][loc.y+1].setBackground(Color.GRAY);
				}
				if((tmpBlock.e != null) && (tmpBlock.solution == true) && (tmpBlock.empty == false) && (tmpBlock.e.solution == false) && (tmpBlock.e.empty == false) && (tmpBlock.eWall == false) )
				{
					label[loc.x][loc.y+1].setBackground(Color.GRAY);
				}
			}
		}
	}
	
	public static void main(String arg[])
	{
		Maze maze1 = new Maze();
		maze1.validate();
		maze1.setupGrid();
		maze1.createMaze();
		maze1.solveMaze(maze1.getStart());
	}
}
