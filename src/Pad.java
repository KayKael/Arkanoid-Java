import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Pad implements Listener {
    private int x,y;
    private  int altura;
    private  int largura;
    private Image paddleimagem;
    private int movimentodopad = 20;

    public Pad() {
        this.x = (GUI.WIDTH / 2) - (largura / 2) ;
        this.y = GUI.HEIGHT - altura * 2 - 30;
        this.altura = 24;
        this.largura = 104;
        paddleimagem = new ImageIcon("C:\\Users\\kaell\\Desktop\\Faculdade\\Disciplinas\\2 ANO\\Programação orientada a Objetos\\Arkanoid-base\\assets\\raquete.png").getImage();
    }
    @Override
    public  int getX() {
        return this.x;
    }
    @Override
    public  int getY() {
        return this.y;
    }
    @Override
    public void setX(int x) {
        this.x=x;

    }
    @Override
    public void setY(int y) {
        this.y=y;
    }

    @Override
    public int getlargura() {
        return this.largura;
    }


    @Override
    public int getaltura() {
        return this.altura;
    }

    @Override
    public void visivel(Graphics g) {
        g.drawImage(paddleimagem, x, y, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if (!((this.getX() + movimentodopad > GUI.WIDTH
                        - largura))) {
                    this.setX((this.getX() + movimentodopad));

                } else {
                    this.setX(GUI.WIDTH - largura);

                }
                break;
            case KeyEvent.VK_LEFT:
                if (!(this.getX() - movimentodopad < 0)) {
                    this.setX((this.getX() - movimentodopad ));


                } else {
                    this.setX(0);


                }
                break;
        }
    }

    public int getMovimentodopad() {
        return movimentodopad;
    }
    public void setMovimentodopad(int movimentodopad) {
        this.movimentodopad = movimentodopad;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
