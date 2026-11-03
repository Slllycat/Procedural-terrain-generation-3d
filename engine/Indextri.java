//package JAVA.personal.otherprojects.engine3d.engine;

import java.util.ArrayList;

public class Indextri {
    int i1;
    int i2;
    int i3;
    public Indextri(){
        i1=0;
        i2=0;
        i3=0;
    }
    public Indextri(int index1,int index2,int index3){
        this.i1=index1;
        this.i2=index2;
        this.i3=index3;
    }
    public Indextri(int[] indexe){
        this.i1=indexe[0];
        this.i2=indexe[1];
        this.i3=indexe[2];
    }
    public Indextri(ArrayList<Integer> indexe){
        this.i1=indexe.get(0);
        this.i2=indexe.get(1);
        this.i3=indexe.get(2);
    }
    public void add(int num){
        this.i1+=num;
        this.i2+=num;
        this.i3+=num;
    }
    public Indextri addreturn(int num){
        return new Indextri(this.i1+num,this.i2+num,this.i3+num);
    }
    @Override
    public String toString() {
        return "("+this.i1+","+this.i2+","+this.i3+")";
    }
}
