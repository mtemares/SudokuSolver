import java.io.*;
import java.util.Scanner;

/*
@author Madeline Temares
@version December 6, 2015

This program makes the GameBoard
*/

public class GameBoard
{	
	private int[][] values; //the values in the board
	
	/*
	Default constructor.
	
	*/
	public GameBoard()
	{
	
		values = new int[9][9];
		ReadCSV(); //reads the CSV which fills the board
	}
		
	/*
	Constructor that takes in the board it is copying, the row and column and the value it is placing there. It adds this value to the new board.
	I commented it out because it was giving me an error.
	@param gb GameBoard to be copied and added to
	@param r Row to be added to
	@param c Column to be added to
	@param v Value to be added
	*/
	public GameBoard(GameBoard gb, int r, int c, int v)
	{
		values = new int[9][9];
		for (int i = 0; i<9; i++)
		{
			for (int j = 0; j<9; j++)
			
			{
				values[i][j] = gb.values[i][j];
			}	
		}		
		values[r][c] = v;
	}
	
	/*
	Reads the CSV file. It populates the values of the gameboard.
	
	@return void
	*/
	private void ReadCSV()
	{
		String pathname = "ExampleGB.CSV";
		File file = new File(pathname);	
		Scanner input = null;
		try
		{
			input = new Scanner(file);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(" Cannot open " + pathname );
			System.exit(1);
		}
		
		String[][] svalues = new String[9][9];
		int count = 0;
		while( input.hasNextLine() )
		{
			svalues[count] = input.nextLine().split(",");
			count++;
		}
		for (int i = 0; i< 9; i++)
		{
			for (int j = 0; j< 9; j++)
			{
				if (svalues[i][j].equals("-"))
				{
					values[i][j] = 0;
				}
				else if (Integer.parseInt(svalues[i][j]) > 0 && Integer.parseInt(svalues[i][j])<10)
				{
					values[i][j] = Integer.parseInt(svalues[i][j]);
				}
				else
				{
					values[i][j] = 0;
				}
			}
		}
	}
	
	
	/*
	toString method
	
	@return String The string representation of the sudoku board
	
	*/
	public String toString()
	{
		String output = "";
		output += "- - - - - - - - - - - - - - - - - - -" + "\n";
		for (int i = 0; i<9; i++)
		{
			for (int j = 0; j<9; j++)
			{
				if (values[i][j] != 0)
				{
				
					output += "| " + values[i][j] + " " ;
				}
				else
				{
					output += "| " + " " + " " ;
				}
			}
			output += "| \n" + ("- - - - - - - - - - - - - - - - - - -")+ "\n";
		}
		return output;
	}
	
	
	/*
	Finds the mostConstrained spot - the spot with the least number of options
	
	@return int[] Spot (row, col) of most constrained spot
	*/
	public int[] mostConstrained()
	{
		int[] spot = new int[2];
		int numOfOptions = 9;
		for (int row = 0; row<9; row++)
		{
			for (int col = 0; col<9; col++)
			{
				if (values[row][col] == 0)
				{
					if (options(row,col).size() < numOfOptions)
					{
						numOfOptions = 	options(row,col).size(); //size of options vector gives you number of options for that spot
						spot[0] = row;
						spot[1] = col;
					}
				}
			}
		}
		return spot;	
	}
	
	/*
	Gives amount of options for specific spot. Helper method for mostConstrained

	@param row Row of spot
	@param col Column of spot
	@return Vector<Integer> Options that spot has
	*/
	//length of vectors gives you number of options
	public Vector<Integer> options(int row, int col)
	{	
		Vector<Integer> o = new Vector<Integer>();
		o.add(1);
		o.add(2);
		o.add(3);
		o.add(4);
		o.add(5);
		o.add(6);
		o.add(7);
		o.add(8);
		o.add(9);
		for (int i: colValues(col))
		{
			o.remove(new Integer(i)); //if that number is in the column, it removes it from the options
		}
		for (int j: rowValues(row))
		{
			o.remove(new Integer(j));//if that number is in the row, it removes it from the options
		}
		for (int k: boxValues(row, col))
		{
			o.remove(new Integer(k));//if that number is in the box, it removes it from the options
		}
		return o;
	}
	/*
	Gives values in the column. Helper method for options

	@param col Column of spot
	@return Vector<Integer> Integers already in that column
	*/
	private Vector<Integer> colValues(int col)
	{
		Vector<Integer> c = new Vector<Integer>();
		for (int i = 0; i<9; i++)
		{
			if (values[i][col] != 0)
			{
				c.add(values[i][col]);
			}
		}
		return c;
	}
	
	/*
	Gives values in the row. Helper method for options

	@param row Row of spot
	@return Vector<Integer> Integers already in that row
	*/
	public Vector<Integer> rowValues(int row)
	{
		Vector<Integer> r = new Vector<Integer>();
		for (int i = 0; i<9; i++)
		{
			if (values[row][i] != 0)
			{
				r.add(values[row][i]);
			}
		}
		return r;
	}
	
	/*
	Gives values in the box. Helper method for options

	@param row Row of spot
	@param col Column of spot
	@return Vector<Integer> Integers already in that box
	*/
	private Vector<Integer> boxValues(int row, int col)
	{
		Vector<Integer> b = new Vector<Integer>();
		int startrow = row - (row%3);
		int startcol = col - (col%3);
		int endrow = startrow + 3;
		int endcol = startcol +3;
		while (startrow<endrow)
		{
			startcol = endcol -3;
			while (startcol<endcol)
			{
				b.add(values[startrow][startcol]);
				startcol++;
			}
			startrow++;
		}
		return b;
	}
	
	/*
	Places numeral at given position

	@param r Row of position
	@param c Column of position
	@param n Numeral to be placed at position
	@return void
	*/
	public void place (int r,int c, int n)
	{
		values[r][c] = n;
	}
	
	
	/*
	Gets integer at given position

	@param r Row of spot
	@param c Column of spot
	@return int Integer at given position
	*/
	public int get(int r,int c)
	{
		return values[r][c];
	}
	
	
	/*
	Removes integer at given position

	@param r Row of spot
	@param c Column of spot
	@return void
	*/
	public void remove(int r,int c)
	{
		values[r][c] = 0;
	}
	
	
	/*
	Whether or not integer can be placed in specific spot

	@param r Row of possible placement
	@param c Column of possible placement
	@param n Integer of possible placement
	@return boolean Whether or not integer can be placed in specified spot
	*/
	public boolean canPlace(int r,int c,int n)
	{
		for (int i: colValues(c)) //checks column
		{
			if (i ==n)
			{
				return false;
			}
		}
		for (int j: rowValues( r))//checks row
		{
			if (j ==n)
			{
				return false;
			}
		}
		for (int k: boxValues(r, c))//checks box
		{
			if (k ==n)
			{
				return false;
			}
		}
		return true;
	}
	
	
	/*
	Whether or not board is solved

	@return boolean Whether or not board is solved
	*/
	public boolean solved()
	{
		for (int i = 0; i <9; i++)
		{
			for (int j = 0; j<9; j++)
			{
				if (values[i][j] == 0) //just checks if board is filled (with numbers besides 0)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	/*
	Accessor for values

	@return int[][] Values
	*/
	public int[][] getValues()
	{
		return values;
	}
}
