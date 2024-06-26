import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Bloco implements Visível{
    public static String txt="";

    public static byte[] b = txt.getBytes();
    private  int altura ;
    private  int largura ;
    private boolean colisao = true;
    private int x;
    private int y;
    private static String tipo;
    private static int resistencia;
    private static Image blocoimagem;
    public static List<String> tipodobloco = new ArrayList<>();
    public static List<Integer> resistenciadobloco = new ArrayList<>();
    public static List<Image> imagemdobloco = new ArrayList<>();
    public int resistenciaatual;
    public int indice;
    public static void abrirblocos() {
        /** Essa função é feita para abrir o arquivo blocos.txt**/
        String fileName = "C:\\Users\\kaell\\Desktop\\Faculdade\\Disciplinas\\2 ANO\\Programação orientada a Objetos\\Arkanoid-base\\assets\\blocos.txt";
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for (int i = 1; i <= 6; i++) {
                line = br.readLine();
                if (line != null) {
                    String[] parts = line.split(";");
                    String tipo = parts[0].trim();
                    tipodobloco.add(tipo);
                    int resistencia = Integer.parseInt(parts[1].trim());
                    resistenciadobloco.add(resistencia);
                    String imagePath = parts[2].trim();
                    BufferedImage img = ImageIO.read(new File(imagePath));
                    imagemdobloco.add(img);
                }
            }
        } catch (IOException e) {
            /** cria um painel caso o arquivo não abra OBJETIVO 7 **/
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Error reading file: " + e.getMessage());
        }
    }
    public Bloco(int x, int y, int indice,int resistenciaatual) {
        /** Construtor do bloco que define a sua resistencia o seu tipo e sua imagem de acordo com o indice obtido ao abrir o ficheiro **/
        this.x = x;
        this.y = y;
        this.indice=indice;
        resistencia=resistenciadobloco.get(indice);
        if (resistencia >= 0 && resistencia <= 5) {
            this.resistencia = resistencia;
        } else {
            this.resistencia = 5;
        }
        this.resistenciaatual=resistencia;
        this.altura=32;
        this.largura=64;
        tipo= tipodobloco.get(indice);
        blocoimagem = imagemdobloco.get(indice);


    }
    public boolean colisao() {
        return colisao;
    }

    public void setColisao(boolean colisao) {
        this.colisao = colisao;
    }

    public int getResistencia() {
        return resistencia;
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
        return largura;
    }

    @Override
    public int getaltura() {
        return altura;
    }

    @Override
    public void visivel(Graphics g) {
        g.drawImage(blocoimagem, x, y, null);

    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
}
