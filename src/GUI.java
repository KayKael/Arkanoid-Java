import javax.swing.*;
import java.awt.*;


public class GUI {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;
    public static Arkanoid arkanoid;
    public static JPanel paineldoscore;
    public static void startGUI() {
            // Inicializar a frame principal
            JFrame frame = new JFrame("Arkanoid - Projeto de PCO @ Instituto Piaget, 2022 / 2023");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.setResizable(false);


            // Instaciar a classe Arkanoid, definir propriedades da JFrame, inicializar motor e thread
            arkanoid = new Arkanoid();
            arkanoid.setFocusable(true);
            arkanoid.setBackground(Color.BLACK);
            arkanoid.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            arkanoid.startGame(arkanoid.getNivel(), arkanoid.isRespawn());
            arkanoid.startThread();
            paineldoscore = new JPanel();
            paineldoscore.setBackground(Color.DARK_GRAY);

            arkanoid.getVidas().setForeground(Color.RED);
            arkanoid.getVidas().setAlignmentX(Component.CENTER_ALIGNMENT);
            arkanoid.getVidas().setBorder(BorderFactory.createEmptyBorder(5, 0, 200, 0));
            paineldoscore.add(arkanoid.getVidas());
            arkanoid.getScore().setAlignmentX(Component.CENTER_ALIGNMENT);
            arkanoid.getScore().setForeground(Color.PINK);
            arkanoid.getScore().setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
            paineldoscore.add(arkanoid.getScore());


            // Adicionar JFrame do Arkanoid à JFrame principal
            frame.add(paineldoscore, BorderLayout.EAST);
            frame.add(arkanoid);
            frame.pack();
            frame.setLocationRelativeTo(null);
            paineldoscore.setVisible(true);
            frame.setVisible(true);

    }
public static void mostrarplacar(){
    arkanoid.getVidas().setText("Vidas Atuais: " + arkanoid.getV());
    arkanoid.getScore().setText("Score: " + arkanoid.getS());
}
}

