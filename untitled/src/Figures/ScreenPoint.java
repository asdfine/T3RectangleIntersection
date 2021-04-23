package Figures;

public class ScreenPoint {
    private int x, y;
    public static double scale=1;
    public static double shiftX=0, shiftY=0;

    public ScreenPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ScreenPoint(RealPoint p){
        x = (int)((p.getX()-shiftX)*scale);
        y = (int)((p.getY()-shiftY)*scale);
    }

    public int getX() { return x; }


    public int getY() { return y; }

    public RealPoint toRealPoint(){
        RealPoint result = new RealPoint(getX()/scale+shiftX,getY()/scale+shiftY);
        return result;
    }

    public void set(ScreenPoint p){
        x = p.getX();
        y = p.getY();
    }
}
