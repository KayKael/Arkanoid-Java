import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bola implements Movimento,Visível {
    private int x, y;
    private int tamanho = 14;
    private int velocidade;
    private int dirX, dirY;
    private Image bolaimagem;

    public Bola() {
        this.x = GUI.WIDTH/2;
        this.y = GUI.HEIGHT/2;
        this.velocidade = 1;
        this.dirX = 1;
        this.dirY = 1;
        if(GUI.arkanoid.getNivel()>=3){
            bolaimagem = new ImageIcon("C:\\Users\\kaell\\Desktop\\Faculdade\\Disciplinas\\2 ANO\\Programação orientada a Objetos\\Arkanoid-base\\assets\\bola_azul.png").getImage();
        } else{
            bolaimagem = new ImageIcon("C:\\Users\\kaell\\Desktop\\Faculdade\\Disciplinas\\2 ANO\\Programação orientada a Objetos\\Arkanoid-base\\assets\\bola_cinzenta.png").getImage();
        }
    }

    public Image getImage() {
        return bolaimagem;
    }

    public int getDirX() {
        return dirX;
    }

    public int getDirY() {
        return this.dirY;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setDirY(int dirY) {
        this.dirY = dirY;
    }

    public void setDirX(int dirX) {
        this.dirX = dirX;
    }
    @Override
    public void moverse() {

        this.x = this.x + this.velocidade * this.dirX ;
        this.y = this.y + this.velocidade * this.dirY ;

        if (x < 0 || x + tamanho > 400) {
            dirX = -dirX;
        }
        if (y < 0) {
            dirY = -dirY;
        }
    }
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {

    }

    @Override
    public void setY(int y) {

    }

    @Override
    public int getlargura() {
        return tamanho;
    }

    @Override
    public int getaltura() {
        return tamanho;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    @Override
    public void visivel(Graphics g) {
        g.drawImage(bolaimagem, x, y, null);
    }

    public Image getBolaimagem() {
        return bolaimagem;
    }

    public void setBolaimagem(Image bolaimagem) {
        this.bolaimagem = bolaimagem;
    }
}
