package sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {
    private static Coord size;
    private static ArrayList<Coord> allCoords;
    private static Random random = new Random();

    public static ArrayList<Coord> getAllCoords() {
        return allCoords;
    }

    public static void setSize(Coord _size){

        size = _size;
        allCoords = new ArrayList<>();
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                allCoords.add(new Coord(x, y));
            }
        }

    }

    public static Coord getSize() {
        return size;
    }

    static boolean inRange(Coord coord){

        return  0 <= coord.x && coord.x < size.x &&
                coord.y >= 0 && coord.y < size.y;

    }

    static Coord getRandomCoord(){

        return new Coord(random.nextInt(size.x), random.nextInt(size.y));

    }

    static ArrayList<Coord> getCoordsAround(Coord coord){

        ArrayList<Coord> around = new ArrayList<>();
        for (int x = coord.x - 1; x <= coord.x + 1; x++) {
            for (int y = coord.y - 1; y <= coord.y + 1; y++) {
                if(Ranges.inRange(new Coord(x, y)) && !(new Coord(x, y).equals(coord))) around.add(new Coord(x, y));
            }
        }
        return around;
    }


}
