package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);


    private static class Position {
        private int x;
        private int y;

        Position() {

        }
        Position(int posX, int posY) {
            x = posX;
            y = posY;
        }
    }

    public static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if(i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return s + 2 * effectiveI;
    }

    /*
    *computes relative x coordinate of the leftmost tile in the ith
    * row of a hexgon, assuming the bottom row has an x-coordinate
    * of zero.
     */
    public static int hexRowOffset(int s, int i) {
        int effectiveI = i;
        if(i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return -effectiveI;
    }

    /**
     * Adds a row to the same tile
     * @param world the world to draw on
     * @param p the leftmmost position of the row
     * @param width the num of tiles wide to draw
     * @param t the tile to draw
     */
    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for(int i = 0; i < width; i++) {
            int xCoord = p.x + i;
            int yCoord = p.y;
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }

    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        if(s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2");
        }
        //iterate from bottom to top
        for (int y = 0; y < 2 * s; y++) {
            int yCoord = p.y + y;
            int xCoord = p.x + hexRowOffset(s, y);
            Position rowStartP = new Position(xCoord, yCoord);
            addRow(world, rowStartP, hexRowWidth(s, y), t);
        }
    }

    /*
    @Test
    public void testHexRowWidth() {
        assertEquals(3, hexRowWidth(3, 5));
        assertEquals(3, hexRowWidth(3, 0));
        assertEquals(7, hexRowWidth(3, 2));
        assertEquals(5, hexRowWidth(5, 9));
    }


    @Test
    public void testHexRowOffset() {
        assertEquals(-1, hexRowOffset(2, 1));
        assertEquals(0, hexRowOffset(2, 3));
        assertEquals(-2, hexRowOffset(3, 2));
        assertEquals(-3, hexRowOffset(4, 4));
    }
    */

    /**
     * Draws a column f num hexes, each one of a random biome.
     * @param world
     * @param p
     * @param s
     * @param num
     */
    public static void drawRandomVerticalHexes(TETile[][] world, Position p, int s, int num) {
        Position pp =  new Position(p.x, p.y);
        TETile t = randomTile();
        for(int i = 0; i < num; i++) {
            pp.y += 2 * s;
            addHexagon(world, pp, s, t);
            t = randomTile();
        }
    }

    /** Picks a RANDOM tile with a 20% change of being
     *  a wall, 20% chance of being a flower, and 20%
     *  chance of being a SAND, or a TREE or a MOUNTAIN.
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.MOUNTAIN;
            case 3: return Tileset.SAND;
            case 4: return Tileset.TREE;
            default: return Tileset.NOTHING;
        }
    }

    public static Position topRightNeighbor(Position p, int s) {
        Position res = new Position(p.x, p.y);
        res.x += 2 * s - 1;
        res.y += s;
        return res;
    }


    public static Position bottomRightNeightbor(Position p, int s) {
        Position res = new Position(p.x, p.y);
        res.x += 2 * s - 1;
        res.y -= s;
        return res;
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        int s = 2;
        Position p = new Position(s, 2 * s);

        drawRandomVerticalHexes(world, p, s, 3);
        p = bottomRightNeightbor(p, s);
        drawRandomVerticalHexes(world, p, s, 4);
        p = bottomRightNeightbor(p, s);
        drawRandomVerticalHexes(world, p, s, 5);
        p = topRightNeighbor(p, s);
        drawRandomVerticalHexes(world, p, s, 4);
        p = topRightNeighbor(p, s);
        drawRandomVerticalHexes(world, p, s, 3);

        ter.renderFrame(world);
    }
 }

