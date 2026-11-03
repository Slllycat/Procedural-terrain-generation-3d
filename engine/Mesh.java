import java.util.ArrayList;
public class Mesh {
    ArrayList<Point>vert;   //vertecies
    ArrayList<Indextri>tri;  //triangles
    ArrayList<String>color; //colors
    Boolean wire;

    //CONSTrUCTER
    public Mesh(){
        this.vert=new ArrayList<>();
        this.tri=new ArrayList<>();
        this.color=new ArrayList<>();
        this.color.add("#000000");
        this.wire=false;
    }
    public Mesh(ArrayList<String> meshcolor){
        this.vert=new ArrayList<>();
        this.tri=new ArrayList<>();
        this.color=new ArrayList<>();
        for (int i=0;i<meshcolor.size();i++){
            this.color.add(meshcolor.get(i));
        }
        this.wire=false;
    }
    public void addvert(Point point){
        this.vert.add(point);
    }
    public void addtri(Indextri indexe){
        this.tri.add(indexe);
    }
    public void addtri(int i1,int i2,int i3){
        Indextri tmp=new Indextri(i1,i2,i3);
        this.tri.add(tmp);
    }
    public void addcolor(String col){
        this.color.add(col);
    }
    public Mesh tocenter(Point pos){
        Mesh tmp=new Mesh();
        tmp.vert=new ArrayList<>(this.vert);
        tmp.tri=new ArrayList<>(this.tri);
        tmp.color=new ArrayList<>(this.color);
        for (int i=0;i<tmp.vert.size();i++){
            tmp.vert.get(i).move(pos);
        }
        return tmp;
    }
    public Mesh toscale(Point pos){
        Mesh tmp=new Mesh();
        tmp.vert=new ArrayList<>(this.vert);
        tmp.tri=new ArrayList<>(this.tri);
        tmp.color=new ArrayList<>(this.color);
        for (int i=0;i<tmp.vert.size();i++){
            tmp.vert.get(i).scale(pos);
        }
        return tmp;
    }
    public Mesh torot(Point rot){
        Mesh tmp=new Mesh();
        tmp.vert=new ArrayList<>(this.vert);
        tmp.tri=new ArrayList<>(this.tri);
        tmp.color=new ArrayList<>(this.color);
        for (int i=0;i<tmp.vert.size();i++){
            tmp.vert.set(i,math3d.rotatePoint(tmp.vert.get(i),rot));
        }
        return tmp;
    }
    public String toString() {
        String tmp="Mesh(\n   Vert:";
        for (int i=0;i<this.vert.size();i++){
            tmp+=this.vert.get(i).toString();
            if (i!=this.vert.size()-1)tmp+=",";
        }
        tmp+="\n   Tri:";
        for (int i=0;i<this.tri.size();i++){
            tmp+=this.tri.get(i).toString();
            if (i!=this.tri.size()-1)tmp+=",";
        }
        tmp+="\n   Colors:";
        for (int i=0;i<this.color.size();i++){
            tmp+=this.color.get(i);
            if (i!=this.color.size()-1)tmp+=",";
        }
        tmp+="\n   wire:"+this.wire;
        tmp+="\n)";
        return tmp;
    }
    public String toStringinObject() {
        String tmp="Mesh(\n      Vert:";
        for (int i=0;i<this.vert.size();i++){
            tmp+=this.vert.get(i).toString();
            if (i!=this.vert.size()-1)tmp+=",";
        }
        tmp+="\n      Tri:";
        for (int i=0;i<this.tri.size();i++){
            tmp+=this.tri.get(i).toString();
            if (i!=this.tri.size()-1)tmp+=",";
        }
        tmp+="\n      Colors:";
        for (int i=0;i<this.color.size();i++){
            tmp+=this.color.get(i);
            if (i!=this.color.size()-1)tmp+=",";
        }
        tmp+="\n      wire:"+this.wire;
        tmp+="\n   )";
        return tmp;
    }
}