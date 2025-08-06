package a1;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener {

    private static final double factor = 1.1;
    private BufferedImage originalImage = null;
    private BufferedImage image = null;
    private ControlPanel controlPanel;
    private int imageOffsetX, imageOffsetY;
    private boolean xFlipped = false;
    private boolean yFlipped = false;
    private double scaleFactor = 1.0;

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
            image = originalImage;
            xFlipped = false;
            yFlipped = false;
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

            //centering offset
            double drawX = (panelWidth - scaleFactor * imgWidth) / 2.0;
            double drawY = (panelHeight - scaleFactor * imgHeight) / 2.0;

            //for mousemoved
            imageOffsetX = (int) drawX;
            imageOffsetY = (int) drawY;

            //flip and scale
            double scaleX = xFlipped ? -1 : 1;
            double scaleY = yFlipped ? -1 : 1;

            //AffineTransform to scale image
            AffineTransform transform = new AffineTransform();
            transform.scale(scaleFactor * scaleX, scaleFactor * scaleY);

            //center image
            double tx = (xFlipped ?  imgWidth: 0);
            double ty = (yFlipped ? imgHeight: 0);
            transform.translate(tx + drawX  / scaleFactor, ty + drawY / scaleFactor);

            //System.out.println("ImagePanel width: " + getWidth());
            //System.out.println("Image width: " + image.getWidth());

            //int x2 = (getWidth() - image.getWidth()) / 2;
            //System.out.println("Image X offset: " + x2);

            //draw the immage
            g2.drawImage(image, transform, this);
        }
    }

    public void setOrig() {
        if (originalImage != null) {
            image = originalImage;
            xFlipped = false;
            yFlipped = false;
            scaleFactor = 1.0;
            repaint();
        }
    }

    public void flipX() {
        xFlipped = !xFlipped;
        repaint();
    }

    public void flipY() {
        yFlipped = !yFlipped;
        repaint();
    }

    public void zoomIn() {
        scaleFactor *= factor;
        repaint();
    }

    public void zoomOut() {
        scaleFactor /= factor;
        repaint();

    }

    public void zoomFit() {
        if (image == null){return;}
        double panelWidth = getWidth();
        double panelHeight = getHeight();
        double imageWidth = image.getWidth();
        double imageHeight = image.getHeight();

        scaleFactor = Math.min(panelWidth / imageWidth, panelHeight/ imageHeight);
        repaint();

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
        if (image == null || controlPanel == null) {
            return;
        }
        int mouseX = e.getX();
        int mouseY = e.getY();

        double imgX = (mouseX - imageOffsetX) / scaleFactor;
        double imgY = (mouseY - imageOffsetY) / scaleFactor;

        if (xFlipped) {
            imgX = image.getWidth() - 1 - imgX;
        }
        if (yFlipped) {
            imgY = image.getHeight() - 1 - imgY;
        }

        int ix = (int) imgX;
        int iy = (int) imgY;


        if (ix >= 0 && iy >= 0 &&
                ix < image.getWidth() &&
                iy < image.getHeight()) {

            int pixel = image.getRGB(ix, iy);
            Color color = new Color(pixel, true);

            int r = color.getRed();
            int g = color.getGreen();
            int b = color.getBlue();
            int a = color.getAlpha();

            if (controlPanel != null) {
                controlPanel.xField.setText(String.valueOf(ix));
                controlPanel.yField.setText(String.valueOf(iy));
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
