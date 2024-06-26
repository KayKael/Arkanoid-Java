
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Arkanoid extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int DELAY = 5;
    private Bola bola;
    private Pad paddle;
    private Bloco bloco;
    private int numerodeblocos = 0;
    private ArrayList<Bloco> blocos = new ArrayList<Bloco>();
    private static Arkanoid instancia = null;
    private int nivel=0;
    private boolean respawn;
    private int v=3;
    private int resistenciaatual ;
    private int s;
    private JLabel vidas;
    private JLabel score;



    public void startGame(int nivel, boolean respawn) {
        /** Inicia o jogo **/
        this.bola = new Bola();
        this.paddle = new Pad();
        addKeyListener(getPad());
        paddle.setX(150);
        bola.setX(150);
        bola.setY(400);
        bloco.abrirblocos();
        criarblocos(nivel,true);
        this.vidas= new JLabel("Vidas Atuais: " + getV());
        this.score = new JLabel("Score: " + getS());
        atualizar();
}
public void perder() {
    /** Faz a parte lógica do jogo quando o jogador perde, encerra caso o jogador zere as vidas **/
    if (this.bola.getY() + this.bola.getTamanho() > 600) {
        if (!(this.v==1)) {
            setV(this.v - 1);
            startGame(nivel, true);
        } else {
            System.exit(0);
        }

    }
}
public void criarblocos(int nivel, boolean respawn){
    /** cria os blocos e dispõem os mesmos **/
    int x = 50;
    int y = 50;
    if (respawn) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                this.bloco = new Bloco(x,y, nivel,this.resistenciaatual);
                this.blocos.add(this.bloco);
                this.numerodeblocos++;
                x +=60;
            }
            x = 50;
            y += 40;
        }
    }
}



    @Override
    public void paintComponent(Graphics g) {
        /** Apresenta a parte gráfica dos blocos  **/
        super.paintComponent(g);
        this.bola.visivel(g);
        this.paddle.visivel(g);
        for (Bloco bloco : this.blocos) {
            if (bloco.colisao()) {
                bloco.visivel(g);
            }
        }
    }

    public void startThread() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                atualizar();
                repaint();
            }
        }).start();
    }

    private void atualizar() {
        /** Mantém as funções que necessitam ser atualizadas a todo momento **/
        this.bola.moverse();
        colidirpad();
        colidirbloco();
        perder();
        GUI.mostrarplacar();

    }
    public boolean colidirbloco() {
        /** Sistema que controla a colisão da bola com os blocos**/
        for ( Bloco b: getBlocos()){

            if (!b.colisao()) {
                continue;
            }


            boolean removerbloco = false;
            if (this.bola.getX() >= b.getX() && this.bola.getX() <= b.getX() + b.getlargura()) {
                if (this.bola.getY() + this.bola.getTamanho() >= b.getY()
                        && this.bola.getY() <= b.getY() + b.getaltura()) {
                    this.bola.setDirY(this.bola.getDirY()*-1);
                    removerbloco = true;
                }
            }
          if (this.bola.getY() >= b.getY() && this.bola.getY() <= b.getY() + b.getaltura()) {
                if (this.bola.getX() + this.bola.getTamanho() >= b.getX()
                        && this.bola.getX() <= b.getX() + b.getlargura()) {
                    removerbloco = true;
                    this.bola.setDirX(this.bola.getDirX()*-1);
                }
            }
            if (removerbloco) {
                b=this.blocos.get(this.blocos.indexOf(b));
                b.resistenciaatual--;
                //b.setIndice(b.getIndice()-1);
                setS(getS()+5);
                if (b.resistenciaatual <= 0) {
                    b.setColisao(false);
                    this.blocos.remove(b);
                    this.numerodeblocos--;
                }
                if (this.numerodeblocos == 0) {
                    proxnivel();
                }
                return true;
            }
        }
        return false;
    }
    public boolean colidirpad(){
        if (bola.getY() + bola.getTamanho() >= paddle.getY() && bola.getX() + bola.getTamanho() >= paddle.getX() && bola.getX() <= paddle.getX() + paddle.getlargura()) {
            bola.setDirY(bola.getDirY()*-1);
            return true;
        }else
            return false;
    }
    public void proxnivel() {
        this.resistenciaatual = bloco.getResistencia();
        this.v++;
        this.vidas.setText("Vidas Atuais: " + this.v);
        this.nivel++;
        bola.setVelocidade(nivel+2);
        paddle.setMovimentodopad(paddle.getMovimentodopad()+5);
        startGame(getNivel(), isRespawn() );
        addKeyListener(getPad());
    }
    public  Listener getPad() {
        return this.paddle;
    }
    public ArrayList<Bloco> getBlocos() {
        return blocos;
    }
    public int getNivel() {
        return nivel;
    }
    public boolean isRespawn() {
        return respawn;
    }
    public void setRespawn(boolean respawn) {
        this.respawn = respawn;
    }
    public int getV() {
        return v;
    }
    public void setV(int v) {
        this.v = v;
    }
    public int getS() {
        return s;
    }
    public void setS(int s) {
        this.s = s;
    }

    public JLabel getVidas() {
        return vidas;
    }
    public JLabel getScore() {
        return score;
    }
}

