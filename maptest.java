import java.awt.*;

public class maptest {
    //settings :P
    private static boolean creative=true;
    private static double ofset=2;//how much over the terain you are
    private static long seed=51243L;//the seed :P
    private static int size=150;//terain-size arround you :P
    private static int scale=40;//the scale of the terain           (20 for creative else 40)
    private static int tscale=20;//the scale of the terain hight    (10 for creative else 20)
    private static int sscreen=1000;//the size of the screen

    //global vars
    private static Camera cam=new Camera(0,10,0);
    private static Object3d obj;
    private static Inputs inp;
    private static Point oldp=new Point();

    public static void main(String[] args) {
        cam.setrot(0,-Math.PI/2);
        makemap(32);
        Panel panel=draw3d.setup("MAP :P", sscreen, sscreen, 15,maptest::draw);
        inp=draw3d.setupinput(panel);
    }
    public static void draw(Graphics g) {
        //drawing and more
        if ((oldp.x!=cam.pos.x||oldp.y!=cam.pos.y||oldp.z!=cam.pos.z)||inp.iskeypressed("1")||inp.iskeypressed("2")||inp.iskeypressed("3")||inp.iskeypressed("4")||inp.iskeypressed("5")||inp.iskeypressed("6")||inp.iskeypressed("7")||inp.iskeypressed("8")){
            if (inp.iskeypressed("2")){
                makemap(2);
            }else if (inp.iskeypressed("3")){
                makemap(3);
            }else if (inp.iskeypressed("4")){
                makemap(4);
            }else if (inp.iskeypressed("5")){
                makemap(5);
            }else if (inp.iskeypressed("6")){
                makemap(6);
            }else if (inp.iskeypressed("7")){
                makemap(7);
            }else if (inp.iskeypressed("8")){
                makemap(8);
            }else if (inp.iskeypressed("9")){
                makemap(32);
            }else {
                makemap(1);
            }
        }
        oldp=new Point(cam.pos.x,cam.pos.y,cam.pos.z);
        draw3d.renderobj(sscreen,obj,cam,g);
        System.out.println(cam.pos);
        draw3d.controls(inp,cam,1);
    }
    public static void makemap(int lod){
        //local vars and math
        obj=new Object3d();
        int ox=(int)cam.pos.x;
        int oz=(int)cam.pos.z;
        obj.pos.set(new Point(ox-(size/2),0,oz-(size/2)));
        double[][]p=perlinnoise.multimap(ox,oz,scale,size,10,seed);
        if(!creative)cam.pos.y=p[size/2][size/2]*tscale+ofset;
        int ofx=Math.floorMod(-(ox-size/2),lod);
        int ofy=Math.floorMod(-(oz-size/2),lod);
        for (int y=ofy;y<size;y+=lod){
            for (int x=ofx;x<size;x+=lod){
                obj.mesh.vert.add(new Point(x,p[x][y]*tscale,y));
            }
        }
        int nx=(size-1-ofx)/lod+1;
        int ny=(size-1-ofy)/lod+1;
        obj.mesh.color.clear();
        for (int y=0;y<ny-1;y++){
            for (int x=0;x<nx-1;x++){
                int p1=x+(nx*y);
                int p2=x+1+(nx*y);
                int p3=x+(nx*(y+1));
                int p4=x+1+(nx*(y+1));
                obj.mesh.tri.add(new Indextri(p1,p2,p3));
                obj.mesh.color.add(perlinnoise.colorhex((p[ofx+x*lod][ofy+y*lod]+p[ofx+x*lod+lod][ofy+y*lod]+p[ofx+x*lod][ofy+y*lod+lod])/3));
                obj.mesh.tri.add(new Indextri(p4,p2,p3));
                obj.mesh.color.add(perlinnoise.colorhex((p[ofx+x*lod+lod][ofy+y*lod+lod]+p[ofx+x*lod+lod][ofy+y*lod]+p[ofx+x*lod][ofy+y*lod+lod])/3));
            }
        }
    }
}