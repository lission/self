package com.prictice;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/23 11:17
 * 五子棋应用
 * 享元模式
 */
public class FivePieces {

    @Test
    public void gameStart(){
        new ChessBoard();
    }

    public static void main(String[] args) {
        new ChessBoard();

    }
}

/**
 * 非享元角色
 * 落子位置
 * */
class Point{
    private int x;
    private int y;

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

/**
 * 抽象享元角色
 * 五子棋
 * */

interface ChessPieces{
    //

    void downPieces(Graphics g, Point p);
}

/**
 * 具体享元角色
 * 白棋
 * */

class WhitePieces implements ChessPieces{
    @Override
    public void downPieces(Graphics g, Point p) {
        g.setColor(Color.WHITE);
        g.fillOval(p.getX(),p.getY(),30,30);
    }
}

/**
 * 具体享元角色
 * 黑棋
 * */

class BlackPieces implements ChessPieces{
    @Override
    public void downPieces(Graphics g, Point p) {
        g.setColor(Color.BLACK);
        g.fillOval(p.getX(),p.getY(),30,30);
    }
}

/**
 * 享元工厂角色
 * */
class PiecesFactory{
    private ArrayList<ChessPieces> list;

    public PiecesFactory() {
        list = new ArrayList<>();
        ChessPieces w = new WhitePieces();
        list.add(w);
        ChessPieces b = new BlackPieces();
        list.add(b);
    }

    public ChessPieces getChesePieces(String type){
        if (type.equalsIgnoreCase("w")){
            return list.get(0);
        }else if (type.equalsIgnoreCase("b")){
            return list.get(1);
        }else{
            return null;
        }
    }
}

/**
 * 客户角色
 * 棋盘
 * */
class ChessBoard extends MouseAdapter {
    protected PiecesFactory pf;
    protected JFrame f;
    protected Graphics g;
    protected JRadioButton wp;
    protected JRadioButton bp;
    private final int x = 50;
    private final int y = 50;
    private final int w = 40;//小方格宽高
    private final int rw = 400;//棋盘高宽
    public ChessBoard() {
        pf = new PiecesFactory();
        f = new JFrame("享元模式在五子棋中的应用");
        f.setBounds(100,100,500,500);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel SouthJP = new JPanel();
        f.add("South",SouthJP);
        wp = new JRadioButton("白子");
        bp = new JRadioButton("黑子",true);
        ButtonGroup group = new ButtonGroup();
        group.add(wp);
        group.add(bp);
        SouthJP.add(wp);
        SouthJP.add(bp);
        JPanel CenterJP = new JPanel();
        CenterJP.setLayout(null);
        CenterJP.setSize(500,500);
        CenterJP.addMouseListener(this);
        f.add("Center",CenterJP);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g = CenterJP.getGraphics();
        g.setColor(Color.BLUE);
        g.drawRect(x,y,rw,rw);

        for (int i = 1;i<10;i++){
            //绘制第i条竖线
            g.drawLine(x+(i*w),y,x+(i*w),y+rw);
            //绘制第i条水平线
            g.drawLine(x,y+(i*w),x+rw,y+(i*w));
        }

    }
    @Override
    public void mouseClicked(MouseEvent e){
        Point p = new Point(e.getX()-15,e.getY()-15);
        if (wp.isSelected()){
            ChessPieces c1 = pf.getChesePieces("w");
            c1.downPieces(g,p);
        }else if (bp.isSelected()){
            ChessPieces c2 = pf.getChesePieces("b");
            c2.downPieces(g,p);
        }
    }
}
