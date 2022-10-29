package com.comop;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        new Assignment();
    }
}


class Assignment extends JFrame {
    JLabel lb_x, lb_y, lb_w, lb_h;
    JTextField txt_x, txt_y, txt_w, txt_h;
    JButton bt_draw;
    JComboBox comboBox;
    String shape = "Oval";
    String shap[] = {"Oval", "Rectangle"};
    MyCanvas m;
    int x,y,w,h;
    Assignment() {
        comboBox = new JComboBox(shap);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String item = (String) e.getItem();
                    if (item.equals("Oval")) {
                        shape = "Oval";
                    } else {
                        shape = "Rectangle";
                    }

                }
            }
        });
        bt_draw = new JButton("Draw");

        lb_x = new JLabel("X-Coordinate");
        lb_y = new JLabel("Y-Coordinate");
        lb_w = new JLabel("Width");
        lb_h = new JLabel("Height");

        txt_x = new JTextField();
        txt_y = new JTextField();
        txt_w = new JTextField();
        txt_h = new JTextField();

        JPanel inputs = new JPanel();
        inputs.setLayout(new GridLayout(1, 2));
        inputs.setBackground(Color.red);

        JPanel textInputs = new JPanel();
        textInputs.setLayout(new GridLayout(4, 2));
        textInputs.add(lb_x);
        textInputs.add(txt_x);
        textInputs.add(lb_y);
        textInputs.add(txt_y);
        textInputs.add(lb_w);
        textInputs.add(txt_w);
        textInputs.add(lb_h);
        textInputs.add(txt_h);

        //make sure just to input numbers toke it from website https://stackoverflow.com/questions/20541230/allow-only-numbers-in-jtextfield
        ((AbstractDocument)txt_x.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });
        ((AbstractDocument)txt_y.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });
        ((AbstractDocument)txt_w.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });
        ((AbstractDocument)txt_h.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        JPanel otherInputs = new JPanel();
        otherInputs.setLayout(new GridLayout(2, 1));
        otherInputs.add(comboBox);
        otherInputs.add(bt_draw);

        inputs.add(textInputs);
        inputs.add(otherInputs);

        JPanel paintBoard = new JPanel();
        paintBoard.setBackground(Color.lightGray);
        paintBoard.setSize(300,300);
        bt_draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txt_x.getText() == null || txt_x.getText().trim().isEmpty()) {// copied this sentence  from https://stackoverflow.com/questions/32866937/how-to-check-if-textfield-is-empty
                    JOptionPane.showMessageDialog(null, "Please fill the X","",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    if (txt_y.getText() == null || txt_y.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill the Y","",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        if (txt_w.getText() == null || txt_w.getText().trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please fill the width","",JOptionPane.INFORMATION_MESSAGE);
                          }else{
                                if (txt_h.getText() == null || txt_h.getText().trim().isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Please fill the height","",JOptionPane.INFORMATION_MESSAGE);
                                }else{
                                    x=Integer.parseInt(txt_x.getText().toString());
                                    y=Integer.parseInt(txt_y.getText().toString());
                                    w=Integer.parseInt(txt_w.getText().toString());
                                    h=Integer.parseInt(txt_h.getText().toString());
                                    if (shape=="Oval"){
                                        m = new MyCanvas(x,y,w,h,shape);
                                    }
                                    else if(shape=="Rectangle"){
                                        m = new MyCanvas(x,y,w,h,shape);
                                    }
                                    paintBoard.add(m);
                                }
                            }
                        }
                }

            }
        });

        add(inputs,"North");
        add(paintBoard,"Center");
        setVisible(true);
        this.setSize(600, 600);

    }


}


class MyCanvas extends Canvas {
    int x, y, w, h;
    String shape;
    Color color;



    public MyCanvas(int x,int y,int w,int h,String shape) {
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.shape=shape;
        setBackground(Color.lightGray);
        setSize(500, 500);

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d= ( Graphics2D)g.create();
        if(shape=="Oval") {
            g.setColor(Color.red);
            g.fillOval(x, y,w,h);
        } if(shape=="Rectangle") {
            g.setColor(Color.red);
            g.fillRect(x, y,w,h);
        }

    }

}