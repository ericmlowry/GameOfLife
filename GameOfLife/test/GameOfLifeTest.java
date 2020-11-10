import static org.junit.Assert.*;

import org.junit.Test;

public class GameOfLifeTest {
	
	@Test
	public void testSetNewStateGrid() {
		//Test minimum grid and no neighbor doesn't sustain (rule 1)
		boolean[][] one = {{true}};
		GameOfLife One = new GameOfLife(one);
		assertEquals(". ",One.setNewStateGrid());

		//Test one neighbor doesn't sustain (rule 1)
		boolean[][] two = {{true,true}};
		GameOfLife Two = new GameOfLife(two);
		assertEquals(". . ", Two.setNewStateGrid());

		//Test one alive doesn't revive (!rule 4)
		boolean[][] oneone = {{true,false}};
		GameOfLife OneOne = new GameOfLife(oneone);
		assertEquals(". . ", OneOne.setNewStateGrid());

		//Test two alive doesn't revive (!rule 4)
		boolean[][] twotwo = {{true,false},{true,false}};
		GameOfLife TwoTwo = new GameOfLife(twotwo);
		assertEquals(". . \n. . ", TwoTwo.setNewStateGrid());

		//Test two neighbors does sustain and three neighbor revival
		//(!rule 1, !rule 2, rule 3, rule 4)
		boolean[][] twoN = {{true,true},{true,false}};
		GameOfLife TwoN = new GameOfLife(twoN);
		assertEquals("o o \no o ", TwoN.setNewStateGrid());

		//Test three neighbors and repeating the function on the same game
		//(rule 3, !rule 2)
		assertEquals("o o \no o ", TwoN.setNewStateGrid());

		//Test more than three neighbors (rule 2, rule 3)
		boolean[][] nine = {{true,true,true},{true,true,true},{true,true,true}};
		GameOfLife Nine = new GameOfLife(nine);
		assertEquals("o . o \n. . . \no . o ", Nine.setNewStateGrid());

		//Test more than three alive doesn't revive (rule 1, !rule 4)
		assertEquals(". . . \n. . . \n. . . ", Nine.setNewStateGrid());

		//Test much more than three alive doesn't revive (rule 2, rule 3, !rule 4)
		boolean[][] eight = {{true,true,true},{true,false,true},{true,true,true}};
		GameOfLife Eight = new GameOfLife(eight);
		assertEquals("o . o \n. . . \no . o ", Eight.setNewStateGrid());
		
		//All tests test rule 5 and !rule 5
	}

	@Test
	public void testArrayToString() {
		assertEquals("",GameOfLife.arrayToString(null));
		
		boolean[][] nothing = {{}};
		assertEquals("",GameOfLife.arrayToString(nothing));
		
		boolean[][] oneLive = {{true}};
		assertEquals("o ",GameOfLife.arrayToString(oneLive));
		
		boolean[][] oneDead = {{false}};
		assertEquals(". ",GameOfLife.arrayToString(oneDead));
		
		boolean[][] oneRow = {{true,false,false}};
		assertEquals("o . . ",GameOfLife.arrayToString(oneRow));

		boolean[][] oneCol = {{false},{true},{true}};
		assertEquals(". \no \no ",GameOfLife.arrayToString(oneCol));

		boolean[][] general = {{false,true,false},{true,true,false},{false,false,false}};
		assertEquals(". o . \no o . \n. . . ",GameOfLife.arrayToString(general));
	}

}
