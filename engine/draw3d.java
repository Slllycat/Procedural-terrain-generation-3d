import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;
import java.util.ArrayList;

public class draw3d{
    public static Panel setup(String title,int sizex,int sizey,int delay,Consumer<Graphics> action){
        JFrame frame=new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(sizex, sizey);
        Panel panel=new Panel();
        panel.setFocusable(true);
        panel.setDrawAction(action);
        frame.add(panel);
        frame.setVisible(true);
        panel.startTimer(()->{}, delay);
        return panel;
    }
    public static Inputs setupinput(Panel panel){
        Inputs inp=new Inputs();
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON1) {
                    if(!inp.isbuttonpressed("Right"))inp.addbuttom("Right");
                }else if (e.getButton()==MouseEvent.BUTTON2) {
                    if(!inp.isbuttonpressed("Middle"))inp.addbuttom("Middle");
                }else if (e.getButton()==MouseEvent.BUTTON3) {
                    if(!inp.isbuttonpressed("Left"))inp.addbuttom("Left");
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON1) {
                    inp.removebuttom("Right");
                }else if (e.getButton()==MouseEvent.BUTTON2) {
                    inp.removebuttom("Middle");
                }else if (e.getButton()==MouseEvent.BUTTON3) {
                    inp.removebuttom("Left");
                }
            }
        });
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!inp.iskeypressed(KeyEvent.getKeyText(e.getKeyCode()))){
                inp.addkey(KeyEvent.getKeyText(e.getKeyCode()));
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                inp.removekey(KeyEvent.getKeyText(e.getKeyCode()));
            }
        });
        return inp;
    }
    public static void controlsimple(Inputs inp,Camera cam){
        if (inp.iskeypressed("W")){cam.movepos(0,0,1);}
        if (inp.iskeypressed("A")){cam.movepos(-1,0,0);}
        if (inp.iskeypressed("S")){cam.movepos(0,0,-1);}
        if (inp.iskeypressed("D")){cam.movepos(1,0,0);}
        if (inp.iskeypressed("Rechts")){cam.moverot(1, 0);}
        if (inp.iskeypressed("Links")){cam.moverot(-1, 0);}
        if (inp.iskeypressed("Oben")){cam.moverot(0, 1);}
        if (inp.iskeypressed("Unten")){cam.moverot(0, -1);}
    }
    public static void controls(Inputs inp, Camera cam,double speed) {
        double sin=Math.sin(cam.yaw);
        double cos=Math.cos(cam.yaw);
        if (inp.iskeypressed("W"))cam.movepos(sin*speed,0,cos*speed);
        if (inp.iskeypressed("S"))cam.movepos(-sin*speed,0,-cos*speed);
        if (inp.iskeypressed("D"))cam.movepos(cos*speed,0,-sin*speed);
        if (inp.iskeypressed("A"))cam.movepos(-cos*speed,0,sin*speed);
        if (inp.iskeypressed("Q"))cam.movepos(0,-speed,0);
        if (inp.iskeypressed("E"))cam.movepos(0,speed,0);
        if (inp.iskeypressed("Rechts")) cam.moverot(0.1,0);
        if (inp.iskeypressed("Links")) cam.moverot(-0.1,0);
        if (inp.iskeypressed("Oben")) cam.moverot(0,0.1);
        if (inp.iskeypressed("Unten")) cam.moverot(0,-0.1);
    }
    public static void controls(Inputs inp, Camera cam){
        double speed=1;
        double sin=Math.sin(cam.yaw);
        double cos=Math.cos(cam.yaw);
        if (inp.iskeypressed("W"))cam.movepos(sin*speed,0,cos*speed);
        if (inp.iskeypressed("S"))cam.movepos(-sin*speed,0,-cos*speed);
        if (inp.iskeypressed("D"))cam.movepos(cos*speed,0,-sin*speed);
        if (inp.iskeypressed("A"))cam.movepos(-cos*speed,0,sin*speed);
        if (inp.iskeypressed("Q"))cam.movepos(0,-speed,0);
        if (inp.iskeypressed("E"))cam.movepos(0,speed,0);
        if (inp.iskeypressed("Rechts")) cam.moverot(0.1,0);
        if (inp.iskeypressed("Links")) cam.moverot(-0.1,0);
        if (inp.iskeypressed("Oben")) cam.moverot(0,0.1);
        if (inp.iskeypressed("Unten")) cam.moverot(0,-0.1);
    }
    public static void draw2dpoint(int size,int x,int y,Color color,Graphics g) {
        Screenpoint p=math3d.bindto(size,x,y);
        g.setColor(color);
        g.fillRect((int)p.x-2,(int)p.y-2,4,4);
    }
    public static void draw3dpoint(int size,Point point,Camera cam,Color color,Graphics g) {
        Point a=math3d.make3d2d(cam, point);
        draw2dpoint(size,(int)a.x,(int)a.y,color,g);
    }
    public static void rendermesh(int size,Mesh mesh,Camera cam,Graphics g){
        ArrayList<Point> p=new ArrayList<>();
        ArrayList<Screenpoint> p2=new ArrayList<>();
        Boolean err=true;
        for (Point i:mesh.vert){
            if (i.x!=-10000){
                p.add(math3d.make3d2d(cam,i));
            }else{
                err=false;
            }
        }
        if (err){
            for (int i=0;i<p.size();i++){
                p2.add(i,math3d.bindto(size,p.get(i).x,p.get(i).y));
            }
            if (mesh.wire){
                for (Indextri i:mesh.tri){
                    g.drawPolygon(new int[]{(int)p2.get(i.i1).x,(int)p2.get(i.i2).x,(int)p2.get(i.i3).x},new int[]{(int)p2.get(i.i1).y,(int)p2.get(i.i2).y,(int)p2.get(i.i3).y},3);
                }
            }else{
                for (Indextri i:mesh.tri){
                    g.fillPolygon(new int[]{(int)p2.get(i.i1).x,(int)p2.get(i.i2).x,(int)p2.get(i.i3).x},new int[]{(int)p2.get(i.i1).y,(int)p2.get(i.i2).y,(int)p2.get(i.i3).y},3);
                }
            }
        }
    }
    public static void renderobj(int size,Object3d obj,Camera cam,Graphics g){
        if (obj.show){
            ArrayList<Point>vert=math3d.transformobjvert(obj);
            ArrayList<Triangle3d>tri=new ArrayList<>();
            for (int i=0;i<obj.mesh.tri.size();i++){
                Indextri e=obj.mesh.tri.get(i);
                ArrayList<Point> points=new ArrayList<>();
                points.add(math3d.make3d2d(cam,vert.get(e.i1)));
                points.add(math3d.make3d2d(cam,vert.get(e.i2)));
                points.add(math3d.make3d2d(cam,vert.get(e.i3)));
                if ((points.get(0).x==-10000)||(points.get(1).x==-10000)||(points.get(2).x==-10000)){
                    continue;
                }
                double z=(points.get(0).i3+points.get(1).i3+points.get(2).i3)/3;
                Triangle3d tmp=new Triangle3d();
                tmp.depth=z;
                tmp.index=i;
                tmp.points=points;
                tri.add(tmp);
            }
            tri.sort((a,b)->Double.compare(b.depth,a.depth));
            for (int ind=0;ind<tri.size();ind++){
                int i=tri.get(ind).index;
                ArrayList<Point> p=tri.get(ind).points;
                Screenpoint p1=math3d.bindto(size,p.get(0).x,p.get(0).y);
                Screenpoint p2=math3d.bindto(size,p.get(1).x,p.get(1).y);
                Screenpoint p3=math3d.bindto(size,p.get(2).x,p.get(2).y);
                if (obj.mesh.color.size()==1){
                    String tmp=obj.mesh.color.get(0);
                    if (tmp.length()==9){//#00FF00FF
                        int r=Integer.parseInt(obj.mesh.color.get(i).substring(1,3),16);
                        int gg=Integer.parseInt(obj.mesh.color.get(i).substring(3,5),16);
                        int b=Integer.parseInt(obj.mesh.color.get(i).substring(5,7),16);
                        int a=Integer.parseInt(obj.mesh.color.get(i).substring(7,9),16);
                        g.setColor(new Color(r,gg,b,a));
                    }else{
                        g.setColor(Color.decode(obj.mesh.color.get(i)));
                    }
                }else{
                    String tmp=obj.mesh.color.get(i);
                    if (tmp.length()==9){//#00FF00FF
                        int r=Integer.parseInt(obj.mesh.color.get(i).substring(1,3),16);
                        int gg=Integer.parseInt(obj.mesh.color.get(i).substring(3,5),16);
                        int b=Integer.parseInt(obj.mesh.color.get(i).substring(5,7),16);
                        int a=Integer.parseInt(obj.mesh.color.get(i).substring(7,9),16);
                        g.setColor(new Color(r,gg,b,a));
                    }else{
                        g.setColor(Color.decode(obj.mesh.color.get(i)));
                    }
                }
                if (obj.mesh.wire){
                    g.drawPolygon(new int[]{(int)p1.x,(int)p2.x,(int)p3.x},new int[]{(int)p1.y,(int)p2.y,(int)p3.y},3);
                }else{
                    g.fillPolygon(new int[]{(int)p1.x,(int)p2.x,(int)p3.x},new int[]{(int)p1.y,(int)p2.y,(int)p3.y},3);
                }
            }
        }
    }
}