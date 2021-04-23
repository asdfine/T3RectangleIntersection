import Figures.RealPoint;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.geom.Area;

public class IntersectionFiller {

    public static Area inFill(ArrayList<ArrayList<RealPoint>> allPoints){
        Area result = new Area(new Rectangle((int)allPoints.get(0).get(0).getX(),(int)allPoints.get(0).get(0).getY(),
                (int)(allPoints.get(0).get(2).getX()-allPoints.get(0).get(0).getX()),
                (int)(allPoints.get(0).get(2).getY()-allPoints.get(0).get(0).getY())));
        for (int i=1; i<allPoints.size(); i++){
            result.intersect(new Area(new Rectangle((int)allPoints.get(i).get(0).getX(),(int)allPoints.get(i).get(0).getY(),
                    (int)(allPoints.get(i).get(2).getX()-allPoints.get(i).get(0).getX()),
                    (int)(allPoints.get(i).get(2).getY()-allPoints.get(i).get(0).getY()))));
            }
            //result.intersect(new Area(new Rectangle(0.0, 0x0.0p0)));
            return result;
        }
    }
