package Figures;

import java.util.ArrayList;

public class Rectangle implements Figures.Figure {

    private RealPoint p1;
    private RealPoint p3;
    public boolean isChanged = false;


    public void setP1(RealPoint p1) {
        this.p1 = p1;
    }

    public void setP2(RealPoint p2) {
        this.p3 = p3;
    }

    public Rectangle(RealPoint p1, RealPoint p3){
        this.p1 = p1;
        this.p3 = p3;
    }

    public Rectangle(double x1, double y1, double x2, double y2){
        p1 = new RealPoint(x1,y1);
        p3 = new RealPoint(x2,y2);
    }

    @Override
    public ArrayList<RealPoint> getPoints(){
        ArrayList<RealPoint> result = new ArrayList<>();
        result.add(p1);
        result.add(new RealPoint(p1.getX(),p3.getY()));
        result.add(p3);
        result.add(new RealPoint(p3.getX(),p1.getY()));
        return result;
    }

    @Override
    public RealPoint findPoint(RealPoint p) {
        if (p1.equals(p))
            return p1;
        if (p3.equals(p))
            return p3;
        RealPoint p2 = new RealPoint(p1.getX(),p3.getY());
        if (p2.equals(p))
            return p2;
        RealPoint p4 = new RealPoint(p3.getX(),p1.getY());
        if (p4.equals(p))
            return p4;

        return null;
    }

    private boolean isInside (RealPoint point) {
        return (point.getX() > this.p1.getX() && point.getY() > this.p1.getX() &&
                point.getX() < this.p3.getX() && point.getY() < this.p3.getY());
    }

    private RealPoint iscPoint (RealPoint one, RealPoint two){
        return new RealPoint(one.getX(), two.getY());
    }

    private boolean isInside (RealPoint point, Rectangle other) {
        return (point.getX() > other.p1.getX() && point.getY() > other.p1.getX() &&
                point.getX() < other.p3.getX() && point.getY() < other.p3.getY());
    }

    private boolean isTwoPointsInside (RealPoint one, RealPoint two){
        return isInside(one)&&isInside(two);
    }

    private Rectangle crossedRect (Rectangle other) {
        if (isCrossed2(other.p1.getX(), other.p1.getY(), other.p3.getY(), this.p1.getY(), this.p1.getX(), this.p3.getX()) &&
                isCrossed2(other.p3.getX(), other.p1.getY(), other.p3.getY(), this.p1.getY(), this.p1.getX(), this.p3.getX()) &&
                isCrossed2(other.p1.getX(), other.p1.getY(), other.p3.getY(), this.p3.getY(), this.p1.getX(), this.p3.getX()) &&
                isCrossed2(other.p3.getX(), other.p1.getY(), other.p3.getY(), this.p3.getY(), this.p1.getX(), this.p3.getX())) {
            isChanged = true;
            return new Rectangle(other.p1.getX(), this.p1.getY(), other.p3.getX(), this.p3.getY());
        }
        else {
            if (isCrossed2(this.p1.getX(), this.p1.getY(), this.p3.getY(), other.p1.getY(), other.p1.getX(), other.p3.getX()) &&
                    isCrossed2(this.p3.getX(), this.p1.getY(), this.p3.getY(), other.p1.getY(), other.p1.getX(), other.p3.getX()) &&
                    isCrossed2(this.p1.getX(), this.p1.getY(), this.p3.getY(), other.p3.getY(), other.p1.getX(), other.p3.getX()) &&
                    isCrossed2(this.p3.getX(), this.p1.getY(), this.p3.getY(), other.p3.getY(), other.p1.getX(), other.p3.getX())) {
                isChanged = true;
                return new Rectangle(this.p1.getX(), other.p1.getY(), this.p3.getX(), other.p3.getY());
            }
    }
        return other;
    }

    private Rectangle cornerInRect (Rectangle other) {


        if ((isCrossed2(other.p1.getX(), other.p1.getY(), other.p3.getY(), this.p1.getY(),this.p1.getX(), this.p3.getX()))&&
                isCrossed2(this.p3.getX(),this.p1.getY(),this.p3.getY(),other.p3.getY(),other.p1.getX(),other.p3.getX())) {
            isChanged = true;
            return new Rectangle(new RealPoint(other.p1.getX(),this.p1.getY()),new RealPoint(this.p3.getX(),other.p3.getY()));
        } else {
                if ((isCrossed2(other.p1.getX(), other.p1.getY(), other.p3.getY(), this.p3.getY(),this.p1.getX(), this.p3.getX()))&&
                        isCrossed2(this.p3.getX(),this.p1.getY(),this.p3.getY(),other.p1.getY(),other.p1.getX(),other.p3.getX())) {
                    isChanged = true;
                    return new Rectangle(other.p1,new RealPoint(this.p3.getX(),this.p3.getY()));//here it doesn't work as intended
                } else {
                    if (isCrossed2(this.p1.getX(), this.p1.getY(), this.p3.getY(), other.p1.getY(), other.p1.getX(), other.p3.getX()) &&
                            isCrossed2(other.p3.getX(), other.p1.getY(), other.p3.getY(), this.p3.getY(), this.p1.getX(), this.p3.getX())) {
                        isChanged = true;
                        return new Rectangle(new RealPoint(this.p1.getX(),other.p1.getY()), new RealPoint(other.p3.getX(), this.p3.getY()));//here
                    } else { //code here should work in theory, but it don't
                        if (isCrossed2(this.p1.getX(), this.p1.getY(), this.p3.getY(), other.p3.getY(), other.p1.getX(), other.p3.getX()) &&
                                isCrossed2(this.p3.getX(), this.p1.getY(), this.p3.getY(), other.p3.getY(), other.p1.getX(), other.p3.getX())) {
                            isChanged = true;
                            return new Rectangle(this.p1, new RealPoint(this.p3.getX(), other.p3.getY()));
                        }
                    }
                }
            }



        return other;
    }

    private Rectangle twoCornerInRect (Rectangle other) {
        Rectangle thisrect = other;
        Rectangle otherrect = this;
        if ((isCrossed(otherrect.p1.getX(), otherrect.p1.getY(), otherrect.p3.getY(), thisrect.p1.getY(),thisrect.p1.getX(), thisrect.p3.getX()))&&
                isCrossed(otherrect.p1.getX(), otherrect.p1.getY(),otherrect.p3.getY(),thisrect.p3.getY(),thisrect.p1.getX(),thisrect.p3.getX())) { //isInside(other.p1)&&!isInside(other.p3)&&isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&!isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
            isChanged = true;
            return new Rectangle(new RealPoint(otherrect.p1.getX(),thisrect.p1.getY()),thisrect.p3);
        } else { //1
            if ((isCrossed(otherrect.p3.getX(), otherrect.p1.getY(), otherrect.p3.getY(), thisrect.p1.getY(),thisrect.p1.getX(), thisrect.p3.getX()))&&
                    isCrossed(otherrect.p3.getX(), otherrect.p1.getY(),otherrect.p3.getY(),thisrect.p3.getY(),thisrect.p1.getX(),thisrect.p3.getX())) {//isInside(other.p1)&&!isInside(other.p3)&&!isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
                isChanged = true;
                return new Rectangle(thisrect.p1,new RealPoint(otherrect.p3.getX(),thisrect.p3.getY()));
            } // горизонтально
        }
        if ((isCrossed(thisrect.p1.getX(), thisrect.p1.getY(), thisrect.p3.getY(), otherrect.p1.getY(),otherrect.p1.getX(),otherrect.p3.getX()))&&
                isCrossed(thisrect.p3.getX(), thisrect.p1.getY(),thisrect.p3.getY(),otherrect.p1.getY(),otherrect.p1.getX(),otherrect.p3.getX())) { //isInside(other.p1)&&!isInside(other.p3)&&isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&!isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
            isChanged = true;
            return new Rectangle(new RealPoint(thisrect.p1.getX(),otherrect.p1.getY()),thisrect.p3);
        } else {
            if ((isCrossed(thisrect.p1.getX(), thisrect.p1.getY(), thisrect.p3.getY(), otherrect.p3.getY(),otherrect.p1.getX(),otherrect.p3.getX()))&&
                    isCrossed(thisrect.p3.getX(), thisrect.p1.getY(),thisrect.p3.getY(),otherrect.p3.getY(),otherrect.p1.getX(),otherrect.p3.getX())) {//isInside(other.p1)&&!isInside(other.p3)&&!isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
                isChanged = true;
                return new Rectangle(thisrect.p1,new RealPoint(thisrect.p3.getX(),otherrect.p3.getY()));
            } // вертикально
        }

        return other; //
    }

    private Rectangle twoCornerInRect1 (Rectangle other) {
        /*if ((isCrossed(other.p1.getX(), other.p1.getY(), other.p3.getY(), this.p1.getY(),this.p1.getX(), this.p3.getX()))&&
                isCrossed(other.p1.getX(), other.p1.getY(),other.p3.getY(),this.p3.getY(),this.p1.getX(),this.p3.getX())) { //isInside(other.p1)&&!isInside(other.p3)&&isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&!isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
            return new Rectangle(new RealPoint(other.p1.getX(),this.p1.getY()),this.p3);
        } else { //1
            if ((isCrossed(other.p3.getX(), other.p1.getY(), other.p3.getY(), this.p1.getY(),this.p1.getX(), this.p3.getX()))&&
                    isCrossed(other.p3.getX(), other.p1.getY(),other.p3.getY(),this.p3.getY(),this.p1.getX(),this.p3.getX())) {//isInside(other.p1)&&!isInside(other.p3)&&!isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
                return new Rectangle(this.p1,new RealPoint(other.p3.getX(),this.p3.getY()));
            } // горизонтально
        }
        if ((isCrossed(this.p1.getX(), this.p1.getY(), this.p3.getY(), other.p1.getY(),other.p1.getX(),other.p3.getX()))&&
                isCrossed(this.p3.getX(), this.p1.getY(),this.p3.getY(),other.p1.getY(),other.p1.getX(),other.p3.getX())) { //isInside(other.p1)&&!isInside(other.p3)&&isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&!isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
            return new Rectangle(new RealPoint(this.p1.getX(),other.p1.getY()),this.p3);
        } else {
            if ((isCrossed(this.p1.getX(), this.p1.getY(), this.p3.getY(), other.p3.getY(),other.p1.getX(),other.p3.getX()))&&
                    isCrossed(this.p3.getX(), this.p1.getY(),this.p3.getY(),other.p3.getY(),other.p1.getX(),other.p3.getX())) {//isInside(other.p1)&&!isInside(other.p3)&&!isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
                return new Rectangle(this.p1,new RealPoint(this.p3.getX(),other.p3.getY()));
            } // вертикально
        }*/

        if ((isCrossed(this.p1.getX(), this.p1.getY(), this.p3.getY(), other.p1.getY(),other.p1.getX(), other.p3.getX()))&&
                isCrossed(this.p1.getX(), this.p1.getY(),this.p3.getY(),other.p3.getY(),other.p1.getX(),other.p3.getX())) { //isInside(other.p1)&&!isInside(other.p3)&&isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&!isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
            isChanged = true;
            return new Rectangle(new RealPoint(this.p1.getX(),other.p1.getY()),other.p3);
        } else { //1
            if ((isCrossed(this.p3.getX(), this.p1.getY(), this.p3.getY(), other.p1.getY(),other.p1.getX(), other.p3.getX()))&&
                    isCrossed(this.p3.getX(), this.p1.getY(),this.p3.getY(),other.p3.getY(),other.p1.getX(),other.p3.getX())) {//isInside(other.p1)&&!isInside(other.p3)&&!isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
                isChanged = true;
                return new Rectangle(other.p1,new RealPoint(this.p3.getX(),other.p3.getY()));
            } // горизонтально
        }
        if ((isCrossed(other.p1.getX(), other.p1.getY(), other.p3.getY(), this.p1.getY(),this.p1.getX(),this.p3.getX()))&&
                isCrossed(other.p3.getX(), other.p1.getY(),other.p3.getY(),this.p1.getY(),this.p1.getX(),this.p3.getX())) { //isInside(other.p1)&&!isInside(other.p3)&&isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&!isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
            isChanged = true;
            return new Rectangle(new RealPoint(other.p1.getX(),this.p1.getY()),other.p3);
        } else {
            if ((isCrossed(other.p1.getX(), other.p1.getY(), other.p3.getY(), this.p3.getY(),this.p1.getX(),this.p3.getX()))&&
                    isCrossed(other.p3.getX(), other.p1.getY(),other.p3.getY(),this.p3.getY(),this.p1.getX(),this.p3.getX())) {//isInside(other.p1)&&!isInside(other.p3)&&!isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
                isChanged = true;
                return new Rectangle(other.p1,new RealPoint(other.p3.getX(),this.p3.getY()));
            } // вертикально
        }

        return other; //done, idk how to remove that bugs
    }

    private Rectangle twoCornerInRect2 (Rectangle other) {
        if ((isCrossed(other.p1.getX(), other.p1.getY(), other.p3.getY(), this.p1.getY(),this.p1.getX(), this.p3.getX()))&&
                isCrossed(other.p1.getX(), other.p1.getY(),other.p3.getY(),this.p3.getY(),this.p1.getX(),this.p3.getX())) { //isInside(other.p1)&&!isInside(other.p3)&&isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&!isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
            isChanged = true;
            return new Rectangle(new RealPoint(other.p1.getX(),this.p1.getY()),this.p3);
        } else { //1
            if ((isCrossed(other.p3.getX(), other.p1.getY(), other.p3.getY(), this.p1.getY(),this.p1.getX(), this.p3.getX()))&&
                    isCrossed(other.p3.getX(), other.p1.getY(),other.p3.getY(),this.p3.getY(),this.p1.getX(),this.p3.getX())) {//isInside(other.p1)&&!isInside(other.p3)&&!isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
                isChanged = true;
                return new Rectangle(this.p1,new RealPoint(other.p3.getX(),this.p3.getY()));
            } // горизонтально
        }
        if ((isCrossed(this.p1.getX(), this.p1.getY(), this.p3.getY(), other.p1.getY(),other.p1.getX(),other.p3.getX()))&&
                isCrossed(this.p3.getX(), this.p1.getY(),this.p3.getY(),other.p1.getY(),other.p1.getX(),other.p3.getX())) { //isInside(other.p1)&&!isInside(other.p3)&&isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&!isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
            isChanged = true;
            return new Rectangle(new RealPoint(this.p1.getX(),other.p1.getY()),this.p3);
        } else {
            if ((isCrossed(this.p1.getX(), this.p1.getY(), this.p3.getY(), other.p3.getY(),other.p1.getX(),other.p3.getX()))&&
                    isCrossed(this.p3.getX(), this.p1.getY(),this.p3.getY(),other.p3.getY(),other.p1.getX(),other.p3.getX())) {//isInside(other.p1)&&!isInside(other.p3)&&!isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
                isChanged = true;
                return new Rectangle(this.p1,new RealPoint(this.p3.getX(),other.p3.getY()));
            } // вертикально
        }
        return other;
    }

    private Rectangle twoCornerInRectWBugs (Rectangle other) {
        if ((isCrossed2(other.p1.getX(), other.p1.getY(), other.p3.getY(), this.p1.getY(),this.p1.getX(), this.p3.getX()))&&
                isCrossed2(other.p1.getX(), other.p1.getY(),other.p3.getY(),this.p3.getY(),this.p1.getX(),this.p3.getX())) { //isInside(other.p1)&&!isInside(other.p3)&&isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&!isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
            return new Rectangle(new RealPoint(other.p1.getX(),this.p1.getY()),this.p3);
        } else { //1
            if ((isCrossed2(other.p3.getX(), other.p1.getY(), other.p3.getY(), this.p1.getY(),this.p1.getX(), this.p3.getX()))&&
                    isCrossed2(other.p3.getX(), other.p1.getY(),other.p3.getY(),this.p3.getY(),this.p1.getX(),this.p3.getX())) {//isInside(other.p1)&&!isInside(other.p3)&&!isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
                return new Rectangle(this.p1,new RealPoint(other.p3.getX(),this.p3.getY()));
            } // горизонтально
        }
        if ((isCrossed2(this.p1.getX(), this.p1.getY(), this.p3.getY(), other.p1.getY(),other.p1.getX(),other.p3.getX()))&&
                isCrossed2(this.p3.getX(), this.p1.getY(),this.p3.getY(),other.p1.getY(),other.p1.getX(),other.p3.getX())) { //isInside(other.p1)&&!isInside(other.p3)&&isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&!isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
            return new Rectangle(new RealPoint(this.p1.getX(),other.p1.getY()),this.p3);
        } else {
            if ((isCrossed2(this.p1.getX(), this.p1.getY(), this.p3.getY(), other.p3.getY(),other.p1.getX(),other.p3.getX()))&&
                    isCrossed2(this.p3.getX(), this.p1.getY(),this.p3.getY(),other.p3.getY(),other.p1.getX(),other.p3.getX())) {//isInside(other.p1)&&!isInside(other.p3)&&!isInside(new RealPoint(other.p1.getX(),other.p3.getY()))&&isInside(new RealPoint(other.p3.getX(),other.p1.getY()))
                return new Rectangle(this.p1,new RealPoint(this.p3.getX(),other.p3.getY()));
            } // вертикально
        }
        return other;
    }
/*
    private Rectangle rectInside (Rectangle other) {
        Rectangle outside = this;
        Rectangle inside = other;
        if (isInside(inside.p1,outside) && isInside(inside.p3,outside) && isInside(new RealPoint(inside.p1.getX(),inside.p3.getY()),outside) && isInside(new RealPoint(inside.p3.getX(),inside.p1.getY())))
            return inside;
        return this;
    }
*/
    private boolean isCrossed (double ax, double ay1, double ay2, double by, double bx1, double bx2) {
        return ((bx1 < ax && ax < bx2 && ay1 < by && by < ay2));
    }

    private boolean isCrossed2 (double ax, double ay1, double ay2, double by, double bx1, double bx2) {
        return ((bx1 <= ax && ax <= bx2 && ay1 <= by && by <= ay2));
    }

    public Rectangle intersectWith(Rectangle other) {

        other = cornerInRect(other);

        other = twoCornerInRect1(other);




        //other = twoCornerInRectWBugs(other);
        other = crossedRect(other);
        //other = twoCornerInRect(other);
        //other = rectInside(other);
        other = twoCornerInRect2(other);
        return other;
    }

    @Override
    public void replace(RealPoint pOld, RealPoint pNew) {
        if (p1.equals(pOld))
            p1.set(pNew);
        if (p3.equals(pOld))
            p3.set(pNew);
        RealPoint p2 = new RealPoint(p1.getX(),p3.getY());
        if (p2.equals(pOld)){
            p1.setX(pNew.getX());
            p3.setY(pNew.getY());
        }
        RealPoint p4 = new RealPoint(p3.getX(),p1.getY());
        if (p4.equals(pOld)){
            p3.setX(pNew.getX());
            p1.setY(pNew.getY());
        }

    }

}
