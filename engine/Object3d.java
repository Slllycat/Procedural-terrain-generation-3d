//package JAVA.personal.otherprojects.engine3d.engine;

public class Object3d {
    Mesh mesh;
    Point pos;
    Point rot;
    Point scale;
    boolean show;
    public Object3d(){
        this.mesh=new Mesh();
        this.pos=new Point();
        this.rot=new Point();
        this.scale=new Point(1,1,1);
        this.show=true;
    }
    public Object3d(Point pos){
        this.mesh=new Mesh();
        this.pos=pos;
        this.rot=new Point();
        this.scale=new Point(1,1,1);
        this.show=true;
    }
    public Object3d(Point pos,Point rot){
        this.mesh=new Mesh();
        this.pos=pos;
        this.rot=rot;
        this.scale=new Point(1,1,1);
        this.show=true;
    }
    public Object3d(Point pos,Point rot,Point scale){
        this.mesh=new Mesh();
        this.pos=pos;
        this.rot=rot;
        this.scale=scale;
        this.show=true;
    }
    public void setvisability(Boolean bleh){
        this.show=bleh;
    }
    public void setscale(Point scaling){
        this.scale.set(scaling);
    }
    public void addscale(Point scaling){
        this.scale.move(scaling);
    }
    public void setscale(double x,double y,double z){
        this.scale.set(x,y,z);
    }
    public void addscale(double x,double y,double z){
        this.scale.move(x,y,z);
    }
    public void setrot(Point scaling){
        this.rot.set(scaling);
    }
    public void addrot(Point scaling){
        this.rot.move(scaling);
    }
    public void setrot(double x,double y,double z){
        this.rot.set(x,y,z);
    }
    public void addrot(double x,double y,double z){
        this.rot.move(x,y,z);
    }
    public void setpos(Point scaling){
        this.pos.set(scaling);
    }
    public void addpos(Point scaling){
        this.pos.move(scaling);
    }
    public void setpos(double x,double y,double z){
        this.pos.set(x,y,z);
    }
    public void addpos(double x,double y,double z){
        this.pos.move(x,y,z);
    }
    @Override
    public String toString() {
        String tmp="";
        tmp+="Object(\n   Mesh:"+this.mesh.toStringinObject()+"\n   Pos:"+this.pos.toString()+"\n   Rot:"+this.rot.toString()+"\n   Scale:"+this.scale.toString()+"\n)";
        return tmp;
    }
}