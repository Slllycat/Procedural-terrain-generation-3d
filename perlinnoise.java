import java.util.Random;

public class perlinnoise {
    static double fade(double t) {
        return t*t*t*(t*(t*6-15)+10);
    }
    static double dot(double[]dir,double ex,double ey){
        double p=dir[0]*ex+dir[1]*ey;
        return p;
    }
    static double rank(double a,double b,double t){//t is for importance t=1 b t=0 a :P
        return a+((b-a)*t);
    }
    static double[] gradient(int x, int y,long seed) {
        Random random=new Random(seed+x*374761393L+y*668265263L);
        double a=random.nextDouble() * 2 * Math.PI;
        return new double[]{Math.cos(a),Math.sin(a)};
    }
    static double noise(double x,double y,long seed){
        int[][] walls=new int[2][2];
        for (int i=0;i<2;i++){
            walls[i][0]=(int)Math.floor(x)+i;
            walls[i][1]=(int)Math.floor(y)+i;
        }
        double lx=x-walls[0][0];
        double ly=y-walls[0][1];
        double g00=dot(gradient(walls[0][0],walls[0][1], seed),lx,ly);
        double g01=dot(gradient(walls[1][0],walls[0][1], seed),lx-1,ly);
        double g10=dot(gradient(walls[0][0],walls[1][1], seed),lx,ly-1);
        double g11=dot(gradient(walls[1][0],walls[1][1], seed),lx-1,ly-1);
        double u=fade(lx);
        double v=fade(ly);
        double bot=rank(g00,g01,u);
        double top=rank(g10,g11,u);
        double p=rank(bot,top,v);
        return p;
    }
    static double noisescale(double x,double y,double scale,long seed){
        return noise(x/scale,y/scale,seed);
    }
    static double multinoise(double x,double y,double scale,int octaves, double pers,double lacu,long seed){
        double sum=0;
        double maxv=0;//so the last octive is the most influential
        double amp=1;
        double freq=1;
        for (int i=0;i<octaves;i++){
            sum+=noise(x/scale*freq,y/scale*freq,seed+i*121393L)*amp;
            maxv+=amp;
            amp*=pers;//persistene how the the influention changes
            freq*=lacu;//lacunarity is how the scale changes :P
        }
        return sum/maxv;
    }
    public static double[][] map(double scale,int size,long seed){
        double[][]tmp=new double[size][size];
        for(int x=0;x<size;x++){
            for(int y=0;y<size;y++){
                tmp[x][y]=noisescale(x,y,scale,seed);
            }
        }
        return tmp;
    }
    public static double[][] map(int ox,int oy,double scale,int size,long seed){
        double[][]tmp=new double[size][size];
        for(int x=0;x<size;x++){
            for(int y=0;y<size;y++){
                tmp[x][y]=noisescale(x+ox,y+oy,scale,seed);
            }
        }
        return tmp;
    }
    public static double[][] multimap(int ox,int oy,double scale,int size,int oct,long seed){
        double[][]tmp=new double[size][size];
        for(int x=0;x<size;x++){
            for(int y=0;y<size;y++){
                tmp[x][y]=multinoise(x+ox,y+oy,scale,oct,0.5,2,seed);
            }
        }
        return tmp;
    }
    public static int greytone(double n){
        int grey=(int)(128+n*127);//to brighness
        grey=Math.max(0, Math.min(255, grey));//clamp
        int rgb=(grey<<16)|(grey<<8)|grey;//i dunno why but the colors are stores as one 24 bit number
        return rgb;
    } 
    public static int colortone(double n){
        if (n<0){
            int tmp=(int)(175+n*127);//to brighness
            tmp=Math.max(0, Math.min(255,tmp));//clamp
            return (0<<16)|(0<<8)|tmp;//only blue
        }else if(n>0.05){
            int tmp=(int)(25+n*200);//to brighness
            tmp=Math.max(0, Math.min(255,tmp));//clamp
            tmp=255-tmp;
            return (0<<16)|(tmp<<8)|0;//only blue
        }else{
            int tmp=(int)(128+n*800);
            return(tmp<<16)|(tmp<<8)|0;//only blue
        }
    }
    public static String colorhex(double n){
        if (n<0){
            int tmp=(int)(175+n*127);//to brighness
            tmp=Math.max(0, Math.min(255,tmp));//clamp
            return String.format("#%02X%02X%02X",0,0,tmp);
        }else if(n>0.05){
            int tmp=(int)(50+n*200);//to brighness
            tmp=Math.max(0, Math.min(255,tmp));//clamp
            tmp=255-tmp;
            return String.format("#%02X%02X%02X",0,tmp,0);
        }else{
            int tmp=(int)(128+n*800);
            return String.format("#%02X%02X%02X",tmp,tmp,0);
        }
    }
    public static String colorhex2(double n){
        if (n<0){
            return String.format("#%02X%02X%02X",128,128,0);
        }else if(n>0.05){
            int tmp=(int)(25+n*200);//to brighness
            tmp=Math.max(0, Math.min(255,tmp));//clamp
            tmp=255-tmp;
            return String.format("#%02X%02X%02X",0,tmp,0);
        }else{
            int tmp=(int)(128+n*800);
            return String.format("#%02X%02X%02X",tmp,tmp,0);
        }
    }
}
