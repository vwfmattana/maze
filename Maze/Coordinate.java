//777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777
//                               V.W.F Mattana 21128707
//777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777
//======================================================================================================
//                             				COORDINATES
//======================================================================================================
public class Coordinate
{
	//******************************************************************************************************
	//												DECLARATIONS
	//******************************************************************************************************
	int x;
	int y;
	
	public Coordinate(int newX, int newY)
	{
		x = newX;
		y = newY;
	}
	
	public String toSting()
	{
		return x+" "+y;
	}
}// END OF Coordinates CLASS 