package a1;

import javax.swing.*;

public class ControlPanel extends JPanel {

    public JButton origButton, flipXButton, flipYButton, zoomInButton, zoomOutButton,zoomFitButton;
    public JTextField xField, yField, rField, gField, bField, aField;
    public JLabel xLabel,yLabel,rLabel,gLabel,bLabel,aLabel;
    private ImagePanel imagePanel;

    public void setImagePanel(ImagePanel imagePanel) {
        this.imagePanel = imagePanel;
    }

    public ControlPanel(int x, int y, int width, int height) {
        setLayout(null);
        setBounds(x, y, width, height);


        int buttonWidth = 90;
        int buttonHeight = 30;
        int gap = 8;

        int totalWidth = 6 * buttonWidth + 5 * gap;
        int startX = (width - totalWidth) / 2;
        int addButtonSpace = buttonWidth + gap;

        //add "0" button and create action
        origButton = new JButton("Reset");
        origButton.setBounds(startX, 10, buttonWidth, buttonHeight);
        add(origButton);
        origButton.addActionListener(e -> {
            if (imagePanel != null && imagePanel.hasImage()){imagePanel.setOrig();}
        });

        //add "FlipX" button and create action
        flipXButton = new JButton("FlipX");
        flipXButton.setBounds(startX +addButtonSpace, 10, buttonWidth, buttonHeight);
        add(flipXButton);
        flipXButton.addActionListener(e -> {
            if (imagePanel != null && imagePanel.hasImage()){ imagePanel.flipX();}
        });

        //add "FlipY" button and create action
        flipYButton = new JButton("FlipY");
        flipYButton.setBounds(startX + addButtonSpace * 2, 10, buttonWidth,buttonHeight);
        add(flipYButton);
        flipYButton.addActionListener(e -> {
            if (imagePanel != null && imagePanel.hasImage()){imagePanel.flipY();}
        });

        //add "Zoom In" button and create action
        zoomInButton = new JButton("Zoom In");
        zoomInButton.setBounds( startX +addButtonSpace*3, 10, buttonWidth,buttonHeight);
        add(zoomInButton);
        zoomInButton.addActionListener(e -> {
            if (imagePanel != null && imagePanel.hasImage()){System.out.println("Zoom In");}
        });

        //add "Zoom Out" button and create action
        zoomOutButton = new JButton("Zoom Out");
        zoomOutButton.setBounds(startX + addButtonSpace*4, 10, buttonWidth,buttonHeight);
        add(zoomOutButton);
        zoomOutButton.addActionListener(e -> {
            if (imagePanel != null && imagePanel.hasImage()){System.out.println("Zoom Out");}
        });

        //add "Zoom Fit" button and create action
        zoomFitButton = new JButton("Zoom Fit");
        zoomFitButton.setBounds(startX+addButtonSpace*5,10,buttonWidth,buttonHeight);
        add(zoomFitButton);
        zoomFitButton.addActionListener(e -> {
            if (imagePanel != null && imagePanel.hasImage()){System.out.println("Zoom Fit");}
        });

        //add text fields
        int yText = 20 + buttonHeight;
        int labelWidth = 15;
        int fieldWidth = 60;
        int fieldGap = 20;
        int numFields = 6;
        int totalFieldWidth = numFields*(fieldWidth+labelWidth)+(numFields -1)*fieldGap;

        int startFieldX = (width - totalFieldWidth)/2;

        int nextFieldX = startFieldX;

        //X
        xLabel = new JLabel("X:");
        xLabel.setBounds(startFieldX, yText, labelWidth, buttonHeight);
        add(xLabel);
        nextFieldX += labelWidth;

        xField = new JTextField();
        xField.setBounds(nextFieldX,yText,fieldWidth,buttonHeight);
        xField.setEditable(false);
        add(xField);
        nextFieldX += fieldWidth + fieldGap;

        //Y
        yLabel = new JLabel("Y:");
        yLabel.setBounds(nextFieldX,yText,labelWidth,buttonHeight);
        add(yLabel);
        nextFieldX += labelWidth;

        yField = new JTextField();
        yField.setBounds(nextFieldX,yText,fieldWidth,buttonHeight);
        yField.setEditable(false);
        add(yField);
        nextFieldX += fieldWidth+fieldGap;

        //R
        rLabel = new JLabel("R:");
        rLabel.setBounds(nextFieldX, yText, labelWidth, buttonHeight);
        add(rLabel);
        nextFieldX += labelWidth;

        rField = new JTextField();
        rField.setBounds(nextFieldX,yText,fieldWidth,buttonHeight);
        rField.setEditable(false);
        add(rField);
        nextFieldX += fieldWidth + fieldGap;

        //G
        gLabel = new JLabel("G:");
        gLabel.setBounds(nextFieldX, yText, labelWidth, buttonHeight);
        add(gLabel);
        nextFieldX += labelWidth;

        gField = new JTextField();
        gField.setBounds(nextFieldX,yText,fieldWidth,buttonHeight);
        gField.setEditable(false);
        add(gField);
        nextFieldX += fieldWidth + fieldGap;


        //B
        bLabel = new JLabel("B:");
        bLabel.setBounds(nextFieldX, yText, labelWidth, buttonHeight);
        add(bLabel);
        nextFieldX += labelWidth;

        bField = new JTextField();
        bField.setBounds(nextFieldX,yText,fieldWidth,buttonHeight);
        bField.setEditable(false);
        add(bField);
        nextFieldX += fieldWidth + fieldGap;

        //A
        aLabel = new JLabel("A:");
        aLabel.setBounds(nextFieldX, yText, labelWidth, buttonHeight);
        add(aLabel);
        nextFieldX += labelWidth;

        aField = new JTextField();
        aField.setBounds(nextFieldX,yText,fieldWidth,buttonHeight);
        aField.setEditable(false);
        add(aField);

    }

}
