class shapes {
    //cube
    public static Mesh meshcube(){
        Mesh tmp=new Mesh();
        for (Point i:new Point[]{
            new Point(-1,-1,-1),
            new Point(1,-1,-1),
            new Point(1,1,-1),
            new Point(-1,1,-1),
            new Point(-1,-1,1),
            new Point(1,-1,1),
            new Point(1,1,1),
            new Point(-1,1,1)
            }){
                tmp.vert.add(i);
            }
        for (Indextri i:new Indextri[]{
            new Indextri(0,1,2),
            new Indextri(0,2,3),
            new Indextri(4,6,5),
            new Indextri(4,7,6),
            new Indextri(0,4,5),
            new Indextri(0,5,1),
            new Indextri(2,6,7),
            new Indextri(2,7,3),
            new Indextri(0,3,7),
            new Indextri(0,7,4),
            new Indextri(1,5,6),
            new Indextri(1,6,2),
            }){
                tmp.tri.add(i);
            }
        return tmp;
    }
    public static Object3d objcube(){
        return objcube(0,0,0);
    }
    public static Object3d objcube(double x, double y,double z){
        Object3d tmp=new Object3d(new Point(x,y,z));
        for (Point i:new Point[]{
            new Point(-1,-1,-1),
            new Point(1,-1,-1),
            new Point(1,1,-1),
            new Point(-1,1,-1),
            new Point(-1,-1,1),
            new Point(1,-1,1),
            new Point(1,1,1),
            new Point(-1,1,1)
            }){
                tmp.mesh.vert.add(i);
            }
        for (Indextri i:new Indextri[]{
            new Indextri(0,1,2),//FLOOR
            new Indextri(0,2,3),//FLOOR
            new Indextri(4,6,5),//TOP
            new Indextri(4,7,6),//TOP
            new Indextri(0,4,5),//SIDE1
            new Indextri(0,5,1),//SIDE1
            new Indextri(2,6,7),//SIDE2
            new Indextri(2,7,3),//SIDE2
            new Indextri(0,3,7),//SIDE3
            new Indextri(0,7,4),//SIDE3
            new Indextri(1,5,6),//SIDE4
            new Indextri(1,6,2),//SIDE4
            }){
                tmp.mesh.tri.add(i);
            }
        return tmp;
    }
}