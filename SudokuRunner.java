/*
@author Madeline Temares
@version December 6, 2015

This class solves the sudoku board
*/

public class SudokuRunner
{
	public static void main(String[] args)
	{	
		Stack<GameBoard> solve = new LinkedList<GameBoard>(); //create stack
		GameBoard example = new GameBoard();
		solve.push(example); //push starting board
		while (true)
		{
			GameBoard x = solve.pop(); //pop stack
			System.out.println(x);
			if (x.solved() == true) //is it solved?
			{
				break;
			}
			for (int i: x.options(x.mostConstrained()[0], x.mostConstrained()[1])) //find most constrained
			{
				solve.push(new GameBoard(x, x.mostConstrained()[0], x.mostConstrained()[1], i)); //push all those options on stack
			}
		}
		/*System.out.println(example);
		System.out.println(example.options(1,6));
		System.out.println(example.mostConstrained()[0]);
		System.out.println(example.mostConstrained()[1]);*/
	}
}
