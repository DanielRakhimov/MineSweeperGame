import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JavaSweeper extends JFrame{

    private final int Col = 9;
    private final int Row = 9;
    private final int Bomb = 10;
    private final int ImageSize = 50;
    private JPanel panel;
    private JLabel label;
    private Game game;

    public static void main(String[] args) {
        new JavaSweeper();
    }
    private JavaSweeper(){
        game = new Game(Col, Row, Bomb);
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();

    }

    private void initLabel(){ // баннер о статусе игры

        label = new JLabel("Добро пожаловать!");
        add(label, BorderLayout.SOUTH);

    }

    private void initFrame(){

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // остановить программу на крестик
        setTitle("Mine Sweeper");
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("icon"));
        pack(); // задать минимальные нужные размеры  окна
        setLocationRelativeTo(null); // установка окна по центру

    }

    private void  initPanel(){

        panel = new JPanel(){ // ананомный класс с перегрузкой функции вывода картинок

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                for(var coord : Ranges.getAllCoords()){
                    g.drawImage((Image) game.getBox(coord).image, coord.x * ImageSize, coord.y * ImageSize, this);

                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / ImageSize;
                int y = e.getY() / ImageSize;
                Coord coord = new Coord(x, y);
                if(e.getButton() == MouseEvent.BUTTON1) game.pressLeftButton(coord);
                if(e.getButton() == MouseEvent.BUTTON3) game.pressRightButton(coord);
                label.setText(getLabelText());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * ImageSize, Ranges.getSize().y * ImageSize)); // задать размеры панели
        add(panel); // добавить панель в форму

    }

    private String getLabelText(){

        switch (game.getGameState()){

            case PLAYED : return "Пока везет!";
            case BOMBED : return "А нет, не везет";
            case WINNER : return "Победа! Дальше только лотерея!";

        }

        return null;
    }

    private Image getImage (String name){

        String filename = "img/" +  name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename)); // обращение к папке ресурса
        return icon.getImage();

    }

    private void setImages(){ // установка иконок полям Box

        for(var box : Box.values()){

            box.image = getImage(box.name().toLowerCase());

        }

    }

}
