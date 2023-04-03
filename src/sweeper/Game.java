package sweeper;

public class Game {

    private Bomb bomb;
    private Flag flag;
    private GameState gameState;

    public Game(int col, int row, int bombs) {

        Ranges.setSize(new Coord(col, row));
        bomb = new Bomb(bombs);
        flag = new Flag();

    }

    public GameState getGameState() {
        return gameState;
    }

    public void start() {

        bomb.start();
        flag.start();
        gameState = GameState.PLAYED;

    }

    public Box getBox(Coord coord) {

        if (flag.get(coord) == Box.OPENED)
            return bomb.get(coord);
        return flag.get(coord);

    }

    private void checkWinner(){

        if(gameState == GameState.PLAYED)
        if(flag.getCountClosedBox() == bomb.getTotalBombs()) gameState = GameState.WINNER;

    }

    public void pressLeftButton(Coord coord){
        if(checkGameOver()) return;
        openBox(coord);
        checkWinner();

    }

    private boolean checkGameOver() {
        if(gameState == GameState.PLAYED) return false;
        start();
        return true;
    }

    private void openBox(Coord coord){

        switch (flag.get(coord)){

            case OPENED : return;
            case FLAGED : return;
            case CLOSED :
                switch (bomb.get(coord)){

                    case ZERO : openBoxAround(coord); return;
                    case BOMB : openBombs(coord); return;
                    default   : flag.setOpenedBox(coord); return;

                }


        }

    }

    private void openBombs(Coord bombedCoord){

        gameState = GameState.BOMBED;
        flag.setBombedBox(bombedCoord);
        for(var coord : Ranges.getAllCoords()){

            if(bomb.get(coord) == Box.BOMB)
                flag.setOpenedBombedToBomb(coord);
            else if(flag.get(coord) == Box.FLAGED && bomb.get(coord) != Box.BOMB)
                flag.setNoBombOnFlaged(coord);
            else flag.setOpenedBox(coord);

        }

    }

    private void openBoxAround(Coord coord){

        flag.setOpenedBox(coord);
        for(var around : Ranges.getCoordsAround(coord)){

            openBox(around);

        }

    }

    public void pressRightButton(Coord coord){
        if(checkGameOver()) return;
        flag.toggleFlagBox(coord);

    }

}
