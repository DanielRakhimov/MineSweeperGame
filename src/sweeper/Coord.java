package sweeper;

public class Coord {

    public int x;
    public int y;

    public Coord(int x, int y){

        this.x = x;
        this.y = y;

    }

    @Override
    public boolean equals(Object o) {

        if(!(o instanceof Coord)) return false;
        Coord coord = (Coord) o;
        return (coord.y == this.y && coord.x == this.x);

    }
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
