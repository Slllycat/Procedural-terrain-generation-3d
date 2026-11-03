import java.awt.*;
import java.util.Arrays;

public class map {
    //settings :P
    private static boolean creative=true;
    private static double ofset=2;//how much over the terain you are
    private static int size=100;//terain-size arround you :P
    private static int scale=40;//the scale of the terain           (20 for creative else 40)
    private static int tscale=20;//the scale of the terain hight    (10 for creative else 20)
    private static int sscreen=1000;//the size of the screen

    //global vars
    private static Camera cam=new Camera();
    private static Object3d obj;
    private static Inputs inp;
    private static Point oldp=new Point();

    public static void main(String[] args) {
        cam.setpos(0,10,2147483647);
        cam.setrot(0,-Math.PI/2);
        makemap();
        Panel panel=draw3d.setup("MAP :P", sscreen, sscreen, 0,map::draw);
        inp=draw3d.setupinput(panel);
    }
    public static void draw(Graphics g) {
        //drawing and more
        if (oldp.x!=cam.pos.x||oldp.y!=cam.pos.y||oldp.z!=cam.pos.z){
            makemap();
        }
        oldp=new Point(cam.pos.x,cam.pos.y,cam.pos.z);
        draw3d.renderobj(sscreen,obj,cam,g);
        //System.out.println(math3d.getfps());
        draw3d.controls(inp,cam);
    }
    public static void makemap(){
        //local vars and math
        obj=new Object3d();
        int ox=(int)cam.pos.x;
        int oz=(int)cam.pos.z;
        obj.pos.set(new Point(ox-(size/2),0,oz-(size/2)));
        double[][]p=perlinnoise.multimap(ox,oz,scale,size,5,123L);
        if(!creative)cam.pos.y=p[size/2][size/2]*tscale+ofset;
        for (int y=0;y<size;y++){
            for (int x=0;x<size;x++){
                obj.mesh.vert.add(new Point(x,p[x][y]*tscale,y));
            }
        }
        obj.mesh.color.clear();
        for (int y=0;y<size-1;y++){
            for (int x=0;x<size-1;x++){
                int p1=x+(size*y);
                int p2=x+1+(size*y);
                int p3=x+(size*(y+1));
                int p4=x+1+(size*(y+1));
                obj.mesh.tri.add(new Indextri(p1,p2,p3));
                obj.mesh.color.add(perlinnoise.colorhex((p[x][y]+p[x+1][y]+p[x][y+1])/3));
                obj.mesh.tri.add(new Indextri(p4,p2,p3));
                obj.mesh.color.add(perlinnoise.colorhex((p[x+1][y+1]+p[x+1][y]+p[x][y+1])/3));
            }
        }
    }
}