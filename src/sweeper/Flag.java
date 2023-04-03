package sweeper;

class Flag {

    private Matrix flagMap;
    private int totalFlags;
    private int countClosedBox;

    public int getCountClosedBox() {
        return countClosedBox;
    }

    void start(){

        flagMap = new Matrix(Box.CLOSED);
        countClosedBox = Ranges.getSize().x * Ranges.getSize().y;

    }

    Box get(Coord coord){

        return flagMap.get(coord);

    }

    void setOpenedBox(Coord coord){

            flagMap.set(coord, Box.OPENED);
            countClosedBox--;

    }

    void toggleFlagBox(Coord coord){

        switch (flagMap.get(coord)){

            case FLAGED : setClosedBox(coord); break;
            case CLOSED : setFlagedBox(coord); break;

        }

    }

    void setFlagedBox(Coord coord){

        flagMap.set(coord, Box.FLAGED);

    }

    void setClosedBox(Coord coord){

        flagMap.set(coord, Box.CLOSED);

    }


    public void setBombedBox(Coord coord) {

        flagMap.set(coord, Box.BOMBED);

    }

    public void setOpenedBombedToBomb(Coord coord) {
        if(flagMap.get(coord) == Box.CLOSED) flagMap.set(coord, Box.OPENED);
    }

    public void setNoBombOnFlaged(Coord coord) {
        if(flagMap.get(coord) == Box.FLAGED) flagMap.set(coord, Box.NOBOMB);
    }
}
