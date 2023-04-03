package sweeper;

class Bomb {

    private Matrix bombMap;
    private int totalBombs;

    public Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    void start(){

        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBombs; i++) {
            placeBomb();
        }

    }

    private void fixBombsCount(){
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if(totalBombs > maxBombs) totalBombs = maxBombs;

    }

    private void placeBomb(){
    Coord coord;

        do {
            coord = Ranges.getRandomCoord();
            if(bombMap.get(coord).equals(Box.BOMB)) continue;
            bombMap.set(coord, Box.BOMB);
            break;
        } while (true);

        incNumbersAroundBomb(coord);

    }

    private void incNumbersAroundBomb(Coord coord){

        for(var aroundCoord : Ranges.getCoordsAround(coord)){
            if(!(bombMap.get(aroundCoord).equals(Box.BOMB)))
                bombMap.set(aroundCoord, Box.values()[bombMap.get(aroundCoord).ordinal() + 1]);
        }

    }

    Box get(Coord coord){

        return bombMap.get(coord);

    }

    public int getTotalBombs() {
        return totalBombs;
    }
}
