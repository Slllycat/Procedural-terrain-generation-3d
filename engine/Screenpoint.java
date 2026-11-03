public class Screenpoint {
    double x;
    double y;
    //CONSTrUCTERs
    public Screenpoint(){
        this.x=0.0;
        this.y=0.0;
    }
    public Screenpoint(double xcord,double ycord){
        this.x=xcord;
        this.y=ycord;
    }
    @Override
    public String toString() {
        return "("+this.x+","+this.y+")";
    }
}
