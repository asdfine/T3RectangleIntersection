

import Drawer.*;
import Figures.*;
import Figures.Figure;
import Figures.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener {

    private boolean isFirst=true;
    private boolean isShiftFirst=true;
    private ScreenPoint startPoint;
    private ScreenPoint endPoint;
    private ScreenPoint startShiftPoint;
    private ScreenPoint endShiftPoint;
    private ArrayList<Figure> figures = new ArrayList<>();
    private Figure filledArea;
    private final ScreenConvertor sc = new ScreenConvertor(-2, 2, 4, 4, 800, 600 );

    public DrawPanel(){
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addMouseWheelListener(this);
        filledArea = new Rectangle(200.0,200.0,600.0,600.0);
        //figures.add(new Rectangle(150.0,150.0,700.0,700.0));
        figures.add(new Rectangle(200.0,200.0, 600.0,600.0));
        figures.add(new Rectangle(100.0,100.0,500.0,500.0));
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bi_g = bi.createGraphics();

        bi_g.setColor(Color.WHITE);
        PixelDrawer pd = new BufferedImagePixelDrawer(bi);
        LineDrawer DDAld = new DDALineDrawer(pd);
        LineDrawer Woold = new WooLineDrawer(pd);
        LineDrawer Bresld = new BresenhamLineDrawer(pd);

        sc.setScreenH(getHeight());
        sc.setScreenW(getWidth());

        bi_g.setColor(Color.black);

        bi_g.fillRect(0, 0, getWidth(), getHeight());

        bi_g.setColor(Color.GREEN);

        for (Figure figure:figures){
            ScreenPoint pp1 = new ScreenPoint(figure.getPoints().get(0));
            ScreenPoint pp2 = new ScreenPoint(figure.getPoints().get(1));
            ScreenPoint pp3 = new ScreenPoint(figure.getPoints().get(2));
            ScreenPoint pp4 = new ScreenPoint(figure.getPoints().get(3));

            bi_g.drawLine(pp1.getX(),pp1.getY(),pp2.getX(),pp2.getY());
            bi_g.drawLine(pp2.getX(),pp2.getY(),pp3.getX(),pp3.getY());
            bi_g.drawLine(pp3.getX(),pp3.getY(),pp4.getX(),pp4.getY());
            bi_g.drawLine(pp4.getX(),pp4.getY(),pp1.getX(),pp1.getY());

            Rectangle temp = new Rectangle(figure.getPoints().get(0), figure.getPoints().get(2));
            temp.isChanged = false;
            filledArea = filledArea.intersectWith(temp);

        }
        ScreenPoint ppp1 = new ScreenPoint(filledArea.getPoints().get(0));
        ScreenPoint ppp2 = new ScreenPoint(filledArea.getPoints().get(1));
        ScreenPoint ppp3 = new ScreenPoint(filledArea.getPoints().get(2));
        ScreenPoint ppp4 = new ScreenPoint(filledArea.getPoints().get(3));

        bi_g.fill(new java.awt.Rectangle(ppp1.getX(),ppp1.getY(),ppp3.getX()-ppp1.getX(),ppp3.getY()-ppp1.getY()));//height


        bi_g.setColor(Color.WHITE);

        drawSnowflake1(DDAld,1500,200,200,64);
        bi_g.setColor(Color.WHITE);
        bi_g.fill(new java.awt.Rectangle(1300,400,400,400));
        drawSnowflake1(Woold,1500,600,200,64);
        drawSnowflake1(Bresld,1100,200,200,64);

        bi_g.setColor(Color.CYAN);

        bi_g.dispose();
        g.drawImage(bi, 0, 0, null);
    }

    private void drawLine(LineDrawer ld, Line l) {
        ld.drawLine(sc.r2s(l.getP1()), sc.r2s(l.getP2()));
    }

    public static void drawSnowflake1 (LineDrawer ld, int x, int y, int r, int n){
        double itrRad = 0;
        for (int i=0; i<n; i++) {
            int xCord = (int) (Math.cos(itrRad) * r)+x;
            int yCord = (int) (Math.sin(itrRad) * r)+y;
            itrRad += Math.PI / (n/2);
            ld.drawLine(new ScreenPoint(x, y), new ScreenPoint(xCord, yCord));
        }
    }

   @Override
    public void mouseClicked(MouseEvent e) {

    }


    @Override
    public void mousePressed(MouseEvent e) {
//here
        if (e.getButton() == MouseEvent.BUTTON1){
            startPoint = new ScreenPoint(e.getX(),e.getY());
            for(Figure figure:figures) {
                RealPoint p = figure.findPoint(startPoint.toRealPoint());
                if(p!=null){
                    startPoint=new ScreenPoint(p);
                    isFirst = false;
                    break;
                }
            }
            if(isFirst)
                startPoint=null;
        }
        else if(e.getButton()==MouseEvent.BUTTON3){
            isShiftFirst = false;
            startShiftPoint = new ScreenPoint(e.getX(),e.getY());
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
        if(!isFirst){
            endPoint = new ScreenPoint(e.getX(),e.getY());
            for(Figure figure:figures) {
                RealPoint p = figure.findPoint(startPoint.toRealPoint());
                if(p!=null){
                    figure.replace(startPoint.toRealPoint(),endPoint.toRealPoint());
                    break;
                }
            }
            isFirst = true;
            startPoint=null;
            endPoint=null;
            repaint();
        }
        }
        else if(e.getButton()==MouseEvent.BUTTON3){
            if (!isShiftFirst) {
                endShiftPoint = new ScreenPoint(e.getX(), e.getY());
                ScreenPoint.shiftX = ScreenPoint.shiftX +startShiftPoint.toRealPoint().getX() - endShiftPoint.toRealPoint().getX();
                ScreenPoint.shiftY = ScreenPoint.shiftY + startShiftPoint.toRealPoint().getY() - endShiftPoint.toRealPoint().getY();
                isShiftFirst = true;
                startShiftPoint = null;
                endShiftPoint = null;
                repaint();
            }
        }
    }


    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void mouseDragged(MouseEvent e) {
        ScreenPoint current = new ScreenPoint(e.getX(), e.getY());
        if (SwingUtilities.isLeftMouseButton(e)) {
            if(!isFirst){
                endPoint = new ScreenPoint(e.getX(),e.getY());
                for(Figure figure:figures) {
                    RealPoint p = figure.findPoint(startPoint.toRealPoint());
                    if(p!=null){
                        figure.replace(startPoint.toRealPoint(),endPoint.toRealPoint());

                        startPoint.set(endPoint);
                        break;
                    }
                }
            }
            repaint();
        }
        else if (SwingUtilities.isRightMouseButton(e)) {
            if (!isShiftFirst) {
                endShiftPoint = new ScreenPoint(e.getX(), e.getY());
                ScreenPoint.shiftX = ScreenPoint.shiftX + startShiftPoint.toRealPoint().getX() - endShiftPoint.toRealPoint().getX();
                ScreenPoint.shiftY =  ScreenPoint.shiftY + startShiftPoint.toRealPoint().getY() - endShiftPoint.toRealPoint().getY();
                startShiftPoint.set(endShiftPoint);
                repaint();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int clicks = e.getWheelRotation();
        double coef = clicks > 0 ?  1.05 : 0.95;
        for (int i = 0; i < Math.abs(clicks); i++) {
            ScreenPoint.scale *= coef;
        }
        repaint();
    }


}
