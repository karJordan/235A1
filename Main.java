package a1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame implements ActionListener {
    private static final int FRAME_WIDTH = 600;
    private static final int PANEL_HEIGHT = 600;
    private static final int CONTROL_HEIGHT = 100;
    private static final int FRAME_HEIGHT = PANEL_HEIGHT + CONTROL_HEIGHT;
    //Menu items for JFrame
    JMenuItem Open, Close, Print, Quit;

    //JPanels for JFrame
    private final ImagePanel imagePanel;
    private final ControlPanel controlPanel;

    public void actionPerformed(ActionEvent e) {
        JComponent source = (JComponent) e.getSource();
        if (source == Open) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if(result == JFileChooser.APPROVE_OPTION){
                File selectedFile = fileChooser.getSelectedFile();
                imagePanel.readImage(selectedFile);
                //imagePanel.repaint();
            }
            System.out.println("Open");
        } else if (source == Close) {
            imagePanel.clearImage();
            System.out.println("Close");
        } else if (source == Print) {
            System.out.println("Print");
        } else if (source == Quit) {
            System.out.println("Quit");
            System.exit(0);
        }
    }

        //constructor
    public Main() {
            super("Assignment1");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //set the size of the Jframe
            this.setSize(FRAME_WIDTH, FRAME_HEIGHT);

            //add imagePanel to JFrame
        controlPanel = new ControlPanel(0,0,FRAME_WIDTH,CONTROL_HEIGHT);
        imagePanel = new ImagePanel(0,CONTROL_HEIGHT,FRAME_WIDTH,PANEL_HEIGHT);
        imagePanel.setControlPanel(controlPanel);
        controlPanel.setImagePanel(imagePanel);

        //create a contianer for the jpanel
        Container content = this.getContentPane();
        content.setLayout(null);
        content.add(imagePanel);
        content.add(controlPanel);
        controlPanel.xField.setText("0");
        controlPanel.yField.setText("0");
        controlPanel.rField.setText("0");
        controlPanel.gField.setText("0");
        controlPanel.bField.setText("0");
        controlPanel.aField.setText("0");

            JMenuBar menuBar = new JMenuBar();
            this.setJMenuBar(menuBar);

            JMenu fileMenu = new JMenu("File");
            menuBar.add(fileMenu);

            Open = new JMenuItem("Open File");
            Open.addActionListener(this);
            fileMenu.add(Open);

            Close = new JMenuItem("Close");
            Close.addActionListener(this);
            fileMenu.add(Close);

            Print = new JMenuItem("Print");
            Print.addActionListener(this);
            fileMenu.add(Print);

            Quit = new JMenuItem("Quit");
            Quit.addActionListener(this);
            fileMenu.add(Quit);

            JMenu helpMenu = new JMenu("Help");
            menuBar.add(helpMenu);


            //display the Jframe
            this.setVisible(true);
        }
        public static void main (String[]args){
            new Main();
        }
    }
