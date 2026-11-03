import java.util.ArrayList;

public class Triangle3d {
    double depth;
    int index;
    ArrayList<Point> points;
    public Triangle3d(){
        this.depth=0;
        this.index=-1;
        this.points=new ArrayList<>();
    }
    public Triangle3d(ArrayList<Point>p,int i,double z){
        this.depth=z;
        this.index=i;
        this.points=p;
    }
}
