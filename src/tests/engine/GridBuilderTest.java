package tests.engine;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import engine.GridBuilder;
import engine.GridParameters;
import grid.BoxFactory;
import grid.Grid;

/**
 * @author raphael
 *
 */
class GridBuilderTest {

	private static GridBuilder gbT;
	private static Grid gT;
	private static GridParameters paramT;
	private static int i,j;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		paramT = GridParameters.getInstance();
		gbT = new GridBuilder(paramT);
		gT = new Grid();
		
	}

	@Test
	void testBuildeGrid() {
		gbT.buildeGrid("Grass");
		
		i=1; j=5;
		gT.setBoxAt(i, j,  BoxFactory.creatBox(BoxFactory.creatWall(i, j)));
		gT.setBoxAtFree(i, j, false);
		assertEquals("Il devrait Y avoir un mur pour i=1", gT.getBoxAt(i, j).getClass(), gbT.getGrid().getBoxAt(i, j).getClass());
		
		 i=10; j=1;
		gT.setBoxAt(i, j,  BoxFactory.creatBox(BoxFactory.creatWall(i, j)));
		gT.setBoxAtFree(i, j, false);
		assertEquals("Il devrait Y avoir un mur pour j=1", gT.getBoxAt(i, j).getClass(), gbT.getGrid().getBoxAt(i, j).getClass());
		
		i=(GridParameters.HEIGHT)-1;j=5;
		gT.setBoxAt(i, j,  BoxFactory.creatBox(BoxFactory.creatWall(i, j)));
		gT.setBoxAtFree(i, j, false);
		assertEquals("Il devrait Y avoir un mur pour i=HEIGHT-1", gT.getBoxAt(i, j).getClass(), gbT.getGrid().getBoxAt(i, j).getClass());
		
		i=8; j=(GridParameters.WIDTH)-1;
		gT.setBoxAt(i, j,  BoxFactory.creatBox(BoxFactory.creatWall(i, j)));
		gT.setBoxAtFree(i, j, false);
		assertEquals("Il devrait Y avoir un mur pour j=WIDTH-1", gT.getBoxAt(i, j).getClass(), gbT.getGrid().getBoxAt(i, j).getClass());
		
		i=7; j=5;
		gT.setBoxAt(i, j,  BoxFactory.creatBox(BoxFactory.creatGrass(i, j, "Grass")));
		assertEquals("Il devrait Y avoir de l'herbe ici", gT.getBoxAt(i, j).getClass(), gbT.getGrid().getBoxAt(i, j).getClass());
	}



}
