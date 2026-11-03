//package JAVA.personal.otherprojects.engine3d.engine;

public class Camera {
    Point pos;
    Double yaw;
    Double pitch;
    Double fov;
    public Camera(){
        this.pos=new Point();
        this.yaw=0.0;
        this.pitch=0.0;
        this.fov=500.0;
    }
    public Camera(double x,double y,double z){
        this.pos=new Point(x,y,z);
        this.yaw=0.0;
        this.pitch=0.0;
        this.fov=500.0;
    }
    public Camera(Point position){
        this.pos=position;
        this.yaw=0.0;
        this.pitch=0.0;
        this.fov=500.0;
    }
    public void setpos(double[] cords){
        this.pos.set(cords);
    }
    public void setpos(double xcord,double ycord,double zcord){
        this.pos.set(xcord,ycord,zcord);
    }
    public void movepos(double[] cords){
        this.pos.move(cords);
    }
    public void movepos(double xcord,double ycord,double zcord){
        this.pos.move(xcord,ycord,zcord);
    }
    public void setrot(double yawrot,double pitchrot){
        this.pitch=pitchrot;
        this.yaw=yawrot;
    }
    public void moverot(double yawrot,double pitchrot){
        this.pitch+=pitchrot;
        this.yaw+=yawrot;
    }
    @Override
    public String toString() {
        return "Camera(\n   Position:"+this.pos+"\n   Pitch:"+this.pitch+"\n   Yaw:"+this.yaw+"\n   Fov:"+this.fov+"\n)";
    }
}
