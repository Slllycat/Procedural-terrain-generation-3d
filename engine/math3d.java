import java.util.ArrayList;

public class math3d {
    public static Point make3d2d(Point pos,Point cord,double yaw,double pitch,double fov){
        //move it to origin
        double cx=pos.x;
        double cy=pos.y;
        double cz=pos.z;
        double x0=cord.x;
        double y0=cord.y;
        double z0=cord.z;
        x0-=cx;
        y0-=cy;
        z0-=cz;

        double cyaw=Math.cos(yaw);
        double syaw=Math.sin(yaw);
        double x1=x0*cyaw-z0*syaw;
        double y1=y0;
        double z1=x0*syaw+z0*cyaw;

        double cp=Math.cos(pitch);
        double sp=Math.sin(pitch);
        double y2=y1*cp-z1*sp;
        double z2=y1*sp+z1*cp;
        if (z2>1) {
            return new Point(x1 * fov / z2, y2 * fov / z2, z2);
        }
        return new Point(-10000,-10000,-10000);
    }
    public static Point make3d2d(Camera cam,Point cord){
        //move it to origin
        double cx=cam.pos.x;
        double cy=cam.pos.y;
        double cz=cam.pos.z;
        double x0=cord.x;
        double y0=cord.y;
        double z0=cord.z;
        x0-=cx;
        y0-=cy;
        z0-=cz;

        double cyaw=Math.cos(cam.yaw);
        double syaw=Math.sin(cam.yaw);
        double x1=x0*cyaw-z0*syaw;
        double y1=y0;
        double z1=x0*syaw+z0*cyaw;

        double cp=Math.cos(cam.pitch);
        double sp=Math.sin(cam.pitch);
        double y2=y1*cp-z1*sp;
        double z2=y1*sp+z1*cp;

        if (z2>0){
            return new Point(x1*cam.fov/z2,y2*cam.fov/z2,z2);
        }
        return new Point(-10000,-10000,-10000);
    }
    public static Point rotatePoint(double x,double y,double z,Point rot){
        double rx=rot.x;
        double ry=rot.y;
        double rz=rot.z;
        //X ROT
        double cx=Math.cos(rx);
        double sx=Math.sin(rx);
        double y1=(y*cx)-(z*sx);
        double z1=(y*sx)+(z*cx);
        //Y ROT
        double cy=Math.cos(ry);
        double sy=Math.sin(ry);
        double x1=(x*cy)+(z1*sy);
        double z2=-(x*sy)+(z1*cy);
        //Z rot;
        double cz=Math.cos(rz);
        double sz=Math.sin(rz);
        double x2=(x1*cz)-(y1*sz);
        double y2=(x1*sz)+(y1*cz);
        return new Point(x2,y2,z2);
    }
    public static Point rotatePoint(Point pos,Point rot){
        double x=pos.x;
        double y=pos.y;
        double z=pos.z;
        double rx=rot.x;
        double ry=rot.y;
        double rz=rot.z;
        //X ROT
        double cx=Math.cos(rx);
        double sx=Math.sin(rx);
        double y1=(y*cx)-(z*sx);
        double z1=(y*sx)+(z*cx);
        //Y ROT
        double cy=Math.cos(ry);
        double sy=Math.sin(ry);
        double x1=(x*cy)+(z1*sy);
        double z2=-(x*sy)+(z1*cy);
        //Z rot;
        double cz=Math.cos(rz);
        double sz=Math.sin(rz);
        double x2=(x1*cz)-(y1*sz);
        double y2=(x1*sz)+(y1*cz);
        return new Point(x2,y2,z2);
    }
    public static Screenpoint bindto(int size,double sx,double sy){
        sx+=size/2.0;
        sy=size/2.0-sy;
        return new Screenpoint(sx,sy);
    }
    private static long old=System.nanoTime();
    public static double gettimedif(){
        long now=System.nanoTime();
        double tmp=(now-old)/1_000_000_000.0;//from ns to s
        old=System.nanoTime();
        return tmp;
    }
    public static int getfps(){
        return (int)(1/gettimedif());
    }
    public static Mesh mergemesh(Mesh mesh1,Mesh mesh2){
        Mesh tmp=new Mesh();
        if (mesh1.color.size()==1){
            tmp.color.add(mesh1.color.get(0));
        }else{
            for (String i:mesh1.color){
                tmp.color.add(i);
            }
            for (String i:mesh2.color){
                tmp.color.add(i);
            }
        }
        tmp.wire=mesh1.wire;
        for (Point i:mesh1.vert){
            tmp.vert.add(i);
        }
        for (Point i:mesh2.vert){
            tmp.vert.add(i);
        }
        for (Indextri i:mesh1.tri){
            tmp.tri.add(i);
        }
        for (Indextri i:mesh2.tri){
            tmp.tri.add(i.addreturn(mesh1.vert.size()));
        }
        return tmp;
    }
    public static Object3d mergeobject(Object3d obj1,Object3d obj2){
        Object3d tmp=new Object3d();
        tmp.mesh=mergemesh(obj1.mesh,obj2.mesh);
        tmp.pos=obj1.pos;
        tmp.rot=obj1.rot;
        tmp.scale=obj1.scale;
        return tmp;
    }
    public static ArrayList<Point> transformobjvert(Object3d obj){
        ArrayList<Point> vert=new ArrayList<>();
        for (Point v:obj.mesh.vert){
            double x=v.x;double y=v.y;double z=v.z;
            x*=obj.scale.i1;
            y*=obj.scale.i2;
            z*=obj.scale.i3;
            Point tmp=rotatePoint(x,y,z,obj.rot);
            x=tmp.i1;y=tmp.i2;z=tmp.i3;
            x+=obj.pos.i1;
            y+=obj.pos.i2;
            z+=obj.pos.i3;
            vert.add(new Point(x,y,z));
        }
        return vert;
    }
    public static Object3d transformobj(Object3d obj){
        ArrayList<Point> vert=new ArrayList<>();
        for (Point v:obj.mesh.vert){
            double x=v.x;double y=v.y;double z=v.z;
            x*=obj.scale.i1;
            y*=obj.scale.i2;
            z*=obj.scale.i3;
            Point tmp=rotatePoint(x,y,z,obj.rot);
            x=tmp.i1;y=tmp.i2;z=tmp.i3;
            x+=obj.pos.i1;
            y+=obj.pos.i2;
            z+=obj.pos.i3;
            vert.add(new Point(x,y,z));
        }
        Object3d tmp=new Object3d(obj.pos,obj.rot,obj.scale);
        tmp.mesh=obj.mesh;
        tmp.mesh.vert=vert;
        return tmp;
    }
}