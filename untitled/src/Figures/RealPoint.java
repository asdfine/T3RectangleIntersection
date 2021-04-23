package Figures;

public class RealPoint {
    private double x;
    private double y;
    public double delta = 5;

    public RealPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean equals(RealPoint p){
        if (Math.abs(x-p.getX())<delta)
            if (Math.abs(y-p.getY())<delta)
                return true;
        return false;
    }
    public void set(RealPoint p){
        x = p.getX();
        y=p.getY();
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}