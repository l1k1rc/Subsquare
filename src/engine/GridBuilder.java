package engine;

import grid.BoxFactory;
import grid.Grid;
import used.Random;

public class GridBuilder {
	/**
	 * This class initializes the grid's settings for the first time.
	 * 
	 * @author MOEs
	 *
	 */
	private Grid grid;
	private int height, width;

	public GridBuilder(GridParameters parameters) {
		grid = new Grid();
		height = GridParameters.HEIGHT;
		width = GridParameters.WIDTH;
		buildeGrid(parameters.getGround());
		grid.setGridParameters(parameters);
	}
	/********** methodes **********/
	// getters

	/**
	 * Returns the grid.
	 * 
	 * @return Grid
	 */

	public Grid getGrid() {
		return grid;
	}

	// others

	/**
	 * This method places obstacles and the ground,
	 * It builds 3 forest at a random position.
	 * @param ground
	 */

	public void buildeGrid(String ground) {
		int i1, i2, i3, j1, j2, j3;
		/* To know in which column the forest will be */
		j1 = Random.randomInt(4, 15);
		j2 = Random.randomInt(16, 30);
		j3 = Random.randomInt(31, 38);

		i1 = Random.randomInt(4, 16);
		i2 = Random.randomInt(4, 16);
		i3 = Random.randomInt(4, 16);

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
					grid.setBoxAt(i, j, BoxFactory.creatBox(BoxFactory.creatWall(i, j)));
					grid.setBoxAtFree(i, j, false);
				} else {
					if ((i >= i1 && i <= i1 + 4) && (j >= j1 && j <= j1 + 4)
							|| ((i >= i2 && i <= i2 + 4) && (j >= j2 && j <= j2 + 4))
							|| ((i >= i3 && i <= i3 + 4) && (j >= j3 && j <= j3 + 4)))
						grid.setBoxAt(i, j, BoxFactory.creatBox(BoxFactory.creatGrass(i, j, ground, true)));
					else
						grid.setBoxAt(i, j, BoxFactory.creatBox(BoxFactory.creatGrass(i, j, ground, false)));
				}
			}
		}
	}

}
