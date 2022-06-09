package tiles;

import java.awt.Graphics2D;

import math.AABB;
import tiles.blocks.Block;

public abstract class TileMap {
    public abstract Block[] getBlocks();
    public abstract void render(Graphics2D g, AABB cam);
}
