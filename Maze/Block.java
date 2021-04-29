public class Block
{
	public Block n, w, s, e;
	public Coordinate blockNumber;

	public boolean visited;
	public boolean  nWall, wWall, sWall, eWall;
	
	public boolean empty;
	public boolean solution;
	
	public Block()
	{
		blockNumber= new Coordinate(-1,-1);
		n = null;
		w = null;
		s = null;
		e = null;
		
		visited = false;
		nWall = wWall = sWall = eWall = true;
		
		empty = true;
		solution = false;
	}
	
	public Block(int xCoord, int yCoord)
	{
		blockNumber= new Coordinate(xCoord, yCoord);
		n = null;
		w = null;
		s = null;
		e = null;
		
		visited = false;
		nWall = wWall = sWall = eWall = true;
		
		empty = true;
		solution = false;
	}
	
	public boolean hasUnvisitedNeighbours()
	{
		if((n!=null && n.visited == false) || (w!=null && w.visited == false) || (s!=null && s.visited == false) || (e!=null && e.visited == false))
			return true;
		else
			return false;
	}
	
}