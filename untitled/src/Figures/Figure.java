package Figures;

import java.util.ArrayList;

public interface Figure {

    ArrayList<RealPoint> getPoints();
    RealPoint findPoint(RealPoint p);
    void replace(RealPoint pOld, RealPoint pNew);
    Rectangle intersectWith (Rectangle rectangle);
}
