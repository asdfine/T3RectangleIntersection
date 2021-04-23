package Drawer;

import Figures.ScreenPoint;

import java.awt.*;

public class BresenhamLineDrawer implements LineDrawer {
    private PixelDrawer pd;

    public BresenhamLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(ScreenPoint p1, ScreenPoint p2) {
        int x2 = p2.getX();
        int x1 = p1.getX();
        int y2 = p2.getY();
        int y1 = p1.getY();

        int d = 0;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int dx2 = 2 * dx;
        int dy2 = 2 * dy;

        int ix = x1 < x2 ? 1 : -1;
        int iy = y1 < y2 ? 1 : -1;

        //задается направление

        int x = x1;
        int y = y1;

        if (dx >= dy) {
            while (true) {
                pd.setPixel(x,y,Color.WHITE);
                if (x == x2)
                    break;
                x += ix;
                d += dy2;
                if (d > dx) {
                    y += iy;
                    d -= dx2;
                }
            }
        } else {
            while (true) {
                pd.setPixel(x,y,Color.WHITE);
                if (y == y2)
                    break;
                y += iy;
                d += dx2;
                if (d > dy) {
                    x += ix;
                    d -= dy2;
                }
            }
        }
/*
        if (x2>=x1) {
            a = x1;
            dx = x2 - x1;
        }

        if (y2>=y1) {
            b = y1;
            dy = y2 - y1;
        }

        if (x1>x2) {
            a = x2;
            dx = x2-x1;
        }

        if (y1>y2) {
            b = y2;
            dy = y2 - y1;
        }*/
/*
if (x2>=x1 && y2>=y1) {
    a = x1;
    dx = x2-x1;
    b = y1;
    dy = y2-y1;
}

        if (x2>=x1 && y2>=y1) {
            a = x1;
            dx = x2-x1;
            b = y1;
            dy = y2-y1;
        }


        int p = 2*dy-dx;

        while (a<x2){
            if(p>=0){
                pd.setPixel(a,b,Color.WHITE);
                b++;
                p=p+2*dy-2*dx;
            } else
            {
                pd.setPixel(a,b,Color.WHITE);
                p=p+2*dy;
            }
            a++;
        }
*/
        /*
        int dx, dy, p, x, y;
 
	dx=x1-x0;
	dy=y1-y0;
 
	x=x0;
	y=y0;
 
	p=2*dy-dx;
 
	while(x<x1)
	{
		if(p>=0)
		{
			putpixel(x,y,7);
			y=y+1;
			p=p+2*dy-2*dx;
		}
		else
		{
			putpixel(x,y,7);
			p=p+2*dy;
		}
		x=x+1;
         */


        /*

         */
    }
}