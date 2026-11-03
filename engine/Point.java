//package JAVA.personal.otherprojects.engine3d.engine;

public class Point {
    double x;
    double y;
    double z;
    double i1;
    double i2;
    double i3;
    //CONSTrUCTERs
    public Point(){
        this.x=0.0;
        this.y=0.0;
        this.z=0.0;
        this.i1=x;
        this.i2=y;
        this.i3=z;
    }
    public Point(double xcord,double ycord,double zcord){
        this.x=xcord;
        this.y=ycord;
        this.z=zcord;
        this.i1=x;
        this.i2=y;
        this.i3=z;
    }
    public Point(double[] cords){
        if (cords.length!=3)System.exit(1);
        this.x=cords[1];
        this.y=cords[2];
        this.z=cords[3];
        this.i1=x;
        this.i2=y;
        this.i3=z;
    }
    public void set(double[] cords){
        if (cords.length!=3)System.exit(1);
        this.x=cords[1];
        this.y=cords[2];
        this.z=cords[3];
        this.i1=x;
        this.i2=y;
        this.i3=z;
    }
    public void set(double xcord,double ycord,double zcord){
        this.x=xcord;
        this.y=ycord;
        this.z=zcord;
        this.i1=x;
        this.i2=y;
        this.i3=z;
    }
    public void set(Point cords){
        this.x=cords.x;
        this.y=cords.y;
        this.z=cords.z;
        this.i1=x;
        this.i2=y;
        this.i3=z;
    }
    public void move(double[] cords){
        if (cords.length!=3)System.exit(1);
        this.x+=cords[1];
        this.y+=cords[2];
        this.z+=cords[3];
        this.i1=x;
        this.i2=y;
        this.i3=z;
    }
    public void move(double xcord,double ycord,double zcord){
        this.x+=xcord;
        this.y+=ycord;
        this.z+=zcord;
        this.i1=x;
        this.i2=y;
        this.i3=z;
    }
    public void move(Point cords){
        this.x+=cords.x;
        this.y+=cords.y;
        this.z+=cords.z;
        this.i1=x;
        this.i2=y;
        this.i3=z;
    }
    public void scale(double[] cords){
        if (cords.length!=3)System.exit(1);
        this.x*=cords[1];
        this.y*=cords[2];
        this.z*=cords[3];
        this.i1=x;
        this.i2=y;
        this.i3=z;
    }
    public void scale(double xcord,double ycord,double zcord){
        this.x*=xcord;
        this.y*=ycord;
        this.z*=zcord;
        this.i1=x;
        this.i2=y;
        this.i3=z;
    }
    public void scale(Point cords){
        this.x*=cords.x;
        this.y*=cords.y;
        this.z*=cords.z;
        this.i1=x;
        this.i2=y;
        this.i3=z;
    }
    @Override
    public String toString() {
        return "("+this.x+","+this.y+","+this.z+")";
    }
}