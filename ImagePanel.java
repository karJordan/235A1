package a1;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener {

    private static final double factor = 1.1;
    private BufferedImage originalImage = null;
    private BufferedImage image = null;
    private int imageOffsetX, imageOffsetY;
    private ControlPanel controlPanel;


    private BufferedImage copyImage(BufferedImage image){
        BufferedImage copy = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                image.getType()
        );
        Graphics g = copy.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return copy;
    }

    public ImagePanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        setOpaque(true);
        setBackground(Color.LIGHT_GRAY);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setControlPanel(ControlPanel cp) {
        this.controlPanel = cp;
    }

    public boolean hasImage() {
        return image != null;
    }

    public void readImage(File file) {
        try {
            originalImage = ImageIO.read(file);
            image = copyImage(originalImage);
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearImage() {
        image = null;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (image != null) {

            int imgWidth = image.getWidth();
            int imgHeight = image.getHeight();

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int x = (panelWidth - imgWidth) / 2;
            int y = (panelHeight - imgHeight) / 2;

            imageOffsetX = x;
            imageOffsetY = y;

            //System.out.println("ImagePanel width: " + getWidth());
            //System.out.println("Image width: " + image.getWidth());

            //int x2 = (getWidth() - image.getWidth()) / 2;
            //System.out.println("Image X offset: " + x2);
            g2.drawImage(image, x, y, this);
        }
    }

    public void setOrig() {
        if (originalImage != null) {
            image = copyImage(originalImage);
            repaint();
        }
    }

    public void flipX() {
    if (image == null){ return;}

    int width = image.getWidth();
    int height = image.getHeight();

    for (int y = 0; y < height; y++){
        for (int x = 0; x < width / 2; x++){
            int xOpposite = width - 1 - x;

            //get the pixels
            int leftPixel = image.getRGB(x,y);
            int rightPixel = image.getRGB(xOpposite,y);

            //swap the pixels
            image.setRGB(x,y,rightPixel);
            image.setRGB(xOpposite,y,leftPixel);
        }
    }
    repaint();
    }

    public void flipY() {
    if (image == null){return;}
    int width = image.getWidth();
    int height = image.getHeight();

    for (int x = 0; x<width;x++){
        for (int y = 0; y<height/2; y++){
            int yOpposite = height - 1 - y;

            //get the pixels
            int topPixel = image.getRGB(x,y);
            int bottomPixel = image.getRGB(x,yOpposite);

            //swap pixels
            image.setRGB(x,y,bottomPixel);
            image.setRGB(x,yOpposite,topPixel);
        }
    }
    repaint();
    }

    public void zoomIn() {

    }

    public void zoomOut() {

    }

    public void zoomFit() {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        int imgX = mouseX - imageOffsetX;
        int imgY = mouseY - imageOffsetY;

        if (image != null &&
                imgX >= 0 && imgY >= 0 &&
                imgX < image.getWidth() &&
                imgY < image.getHeight()) {

            int pixel = image.getRGB(imgX, imgY);
            Color color = new Color(pixel, true);

            int r = color.getRed();
            int g = color.getGreen();
            int b = color.getBlue();
            int a = color.getAlpha();

            if (controlPanel != null) {
                controlPanel.xField.setText(String.valueOf(imgX));
                controlPanel.yField.setText(String.valueOf(imgY));
                controlPanel.rField.setText(String.valueOf(r));
                controlPanel.gField.setText(String.valueOf(g));
                controlPanel.bField.setText(String.valueOf(b));
                controlPanel.aField.setText(String.valueOf(a));
            }
        } else {
            if (controlPanel != null) {
                controlPanel.xField.setText("0");
                controlPanel.yField.setText("0");
                controlPanel.rField.setText("0");
                controlPanel.gField.setText("0");
                controlPanel.bField.setText("0");
                controlPanel.aField.setText("0");
            }


        }
    }
}
