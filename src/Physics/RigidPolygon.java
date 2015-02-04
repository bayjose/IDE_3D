///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package Physics;
//
//import Base.DistanceCalculator;
//import Entity.EnumMaterial;
//import Base.Handler;
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Polygon;
//import java.awt.geom.Line2D;
//
///**
// *
// * @author Bayjose
// */
//public class RigidPolygon {
//    private EnumMaterial enumMaterial;
//    
//    private Line2D[] lines;
//    private Point[] points = new Point[3];
//    private Point centerOfMass;
//    private Point Offset;
//    private Vector3D forward;
//    private double angleX=0;
//    private double angleY=0;
//    private double angleZ=0;
//    private double AbsoluteAnlgeX=0;
//    private double AbsoluteAnlgeY=0;
//    private double AbsoluteAnlgeZ=0;
//    private float Scale = 0;
//
//    
//    public RigidPolygon (Point position, Point pt1, Point pt2, Point pt3){
//        this.points[0]=pt1;
//        this.points[1]=pt2;
//        this.points[2]=pt3;
//        this.lines = new Line2D[3];
//        for(int i=0; i<3-1; i++){
//          this.lines[i]= new Line2D.Float(this.points[i].x+position.getX(), this.points[i].y+position.getY(), this.points[i+1].x+position.getX(), this.points[i+1].y+position.getY());
//        }
//        this.lines[this.lines.length-1]=  new Line2D.Float(this.points[0].x+position.getX(), this.points[0].y+position.getY(), this.points[3-1].x+position.getX(), this.points[3-1].y+position.getY());
//        this.forward=new Vector3D(1.0F, 1.0F, 0.1F);
//        this.getArea();
//        //maths to calc the exact center of the object based on its points 
//        //will offset all points by the center point
//        float tempBigX=0;
//        float tempBigY=0;
//        float tempBigZ=0;
//        for(int i=0; i<3; i++){
//            //using CalculateXDifferenceF for each is ok, because its the distance between two points. 
//            if(DistanceCalculator.CalculateXDifferenceF(position.getX(), this.points[i].getX())>tempBigX){
//                tempBigX = this.points[i].getX();
//            }
//            if(DistanceCalculator.CalculateXDifferenceF(position.getY(), this.points[i].getY())>tempBigY){
//                tempBigY = this.points[i].getY();
//            }
//            if(DistanceCalculator.CalculateXDifferenceF(position.getZ(), this.points[i].getZ())>tempBigZ){
//                tempBigZ = this.points[i].getZ();
//            }
//        }
//
//        //offset by - half of the biggest values
//        Point tempPt = new Point3D(-tempBigX/2, -tempBigY/2, -tempBigZ/2);
////        this.Offset = new BasicPoint(-256, -256, -0);
//        this.Offset = new Point3D(-0, -0, -0);
//        
//        this.centerOfMass = position;
////        this.setXTo(180);
////         this.setYTo(45);
////          this.setZTo(45);
//          
//          this.updateCollision();
//    }
//    
//    //code from:http://stackoverflow.com/questions/20936429/rotating-a-rectangle-shaped-polygon-around-its-center-java
//    
//    public void rotatePointXAxisAndUpdateVector(double angle) {
//        this.angleX=Math.toRadians(angle);
//        this.AbsoluteAnlgeX+=Math.toRadians(angle);
//        for (int i=0; i<3; i++) {
//            double x1 = this.points[i].getX() + this.Offset.getX();
//            double y1 = this.points[i].getY() + this.Offset.getY();
//            double z1 = this.points[i].getZ() + this.Offset.getZ();
//            double temp_x1 = x1 * Math.cos(this.angleX) - z1 * Math.sin(this.angleX);
//            double temp_y1 = y1;
//            double temp_z1 = x1 * Math.sin(this.angleX) + z1 * Math.cos(this.angleX);
//            this.points[i].setPoint(((float)(temp_x1 - this.Offset.getX())), ((float)(temp_y1 - this.Offset.getY())), ((float)(temp_z1 - this.Offset.getZ())));
////            this.forward=new Vector((float)temp_x1,(float)temp_y1, this.forward.pull);
//            this.forward = new Vector3D(1, 1, 0.1F);
//        }
//        this.updateCollision();
//    }
//    
//    public void rotatePointYAxisAndUpdateVector(double angle) {
//        this.angleY=Math.toRadians(angle);
//        this.AbsoluteAnlgeY+=Math.toRadians(angle);
//        for (int i=0; i<3; i++) {
//            double x1 = this.points[i].getX() + this.Offset.getX();
//            double y1 = this.points[i].getY() + this.Offset.getY();
//            double z1 = this.points[i].getZ() + this.Offset.getZ();
//            double temp_x1 = x1 * Math.cos(this.angleY) - y1 * Math.sin(this.angleY);
//            double temp_y1 = x1 * Math.sin(this.angleY) + y1 * Math.cos(this.angleY);
//            double temp_z1 = z1;
//            this.points[i].setPoint(((float)(temp_x1 - this.Offset.getX())), ((float)(temp_y1- this.Offset.getY())), ((float)(temp_z1- this.Offset.getZ())));
////            this.forward=new Vector((float)temp_x1,(float)temp_y1, this.forward.pull);
//            this.forward = new Vector3D(1, 1, 0.1F);
//        }
//        this.updateCollision();
//    }
//    
//    public void rotatePointZAxisAndUpdateVector(double angle) {
//        this.angleZ=Math.toRadians(angle);
//        this.AbsoluteAnlgeZ+=Math.toRadians(angle);
//        for (int i=0; i<3; i++) {
//            double x1 = this.points[i].getX()+ this.Offset.getX();
//            double y1 = this.points[i].getY()+ this.Offset.getY();
//            double z1 = this.points[i].getZ()+ this.Offset.getZ();
//            double temp_x1 = x1;
//            double temp_y1 = y1 * Math.cos(this.angleZ) - z1 * Math.sin(this.angleZ);
//            double temp_z1 = y1 * Math.sin(this.angleZ) + z1 * Math.cos(this.angleZ);
//            this.points[i].setPoint(((float)(temp_x1 - this.Offset.getX())), ((float)(temp_y1 - this.Offset.getY())), ((float)(temp_z1  - this.Offset.getZ())));
////            this.forward=new Vector((float)temp_x1,(float)temp_y1, this.forward.pull);
//            this.forward = new Vector3D(1, 1, 0.1F);
//        }
//        this.updateCollision();
//    }
//   
//    
//    
//    //todo
//    public float getArea() {
//        return 100.0F;
//    }
//
//    public Vector3D getVectorForward(){
//        return this.forward;
//    }
//    
//    public Vector3D getInverseForward(){
//        Vector3D vect = this.forward;
////        vect.xDir= -vect.xDir;
////        vect.yDir= -vect.yDir;
//        vect.zDir= vect.zDir*-1;
//        return vect;
//    }
//    
//    public void render(Graphics g){
//        g.setColor(Color.BLACK);
//        
//        for(int i=0; i<this.lines.length; i++){
//            g.drawLine((int)this.lines[i].getX1(), (int)this.lines[i].getY1(), (int)this.lines[i].getX2(), (int)this.lines[i].getY2());
//        }
//        
//        if(Handler.showSolidTiles){
//            //comment this in to show zed axis in debug mode
////            for(int i=0; i<3; i++){
////                g.setColor(new Color(0, (int)Math.abs(this.points[i].getZ()%255), 0));
////                g.drawRect((int)this.points[i].x, (int)this.points[i].y, (g.getFont().getSize()*("Z:"+this.points[i].getZ()).length()), 10);
////            }
//            g.setColor(Color.YELLOW);
//            for(int i=0; i<this.lines.length; i++){
//                g.drawLine((int)this.lines[i].getX1(), (int)this.lines[i].getY1(), (int)this.lines[i].getX2(), (int)this.lines[i].getY2());
//            }
////            g.setColor(Color.red);
////            g.drawPolygon(returnJavaPolygon());
//            g.setColor(Color.CYAN);
//            if (this.centerOfMass != null) {
//                g.drawRect((int) this.centerOfMass.x, (int) this.centerOfMass.y, 1, 1);
//                g.drawString("Center:"+this.centerOfMass.getX()+" "+this.centerOfMass.getY()+" "+this.centerOfMass.getZ(), (int)this.centerOfMass.x, (int) this.centerOfMass.y+16);
//            } else {
//                this.getCenter();
//            }
//            
//            g.setColor(Color.red);
//            g.drawPolygon(this.returnJavaPolygon());
//            
//            g.setColor(Color.MAGENTA);
//            g.drawRect(this.returnJavaPolygon().getBounds().x, this.returnJavaPolygon().getBounds().y, this.returnJavaPolygon().getBounds().width, this.returnJavaPolygon().getBounds().height);
//            
//            g.setColor(Color.blue);
//            for(int i=0; i<this.points.length; i++){
//                g.fillRect((int)this.points[i].getX(), (int)this.points[i].getY(), 1, 1);
//            }
//        }
//        
//       
//    }
//    
//    public void setVecForward(Vector3D vect){
//        this.forward=vect;
//    }
//    
//    public Point getPointInArray(int index){
//        if(index<3){
//            return this.points[index];
//        }
//        return new Point3D(0,0,0);
//    }
//    
//    public Point[] getPoints(){
//        return this.points;
//    }
//    
//    public Point getCenter(){
//        return this.centerOfMass;
//    }
//    
//    public void changeCenterOfMass(Point p){
//        this.centerOfMass=p;
//    }
//    
//    public RigidPolygon returnPolygon(){
//        RigidPolygon rp = this;
//        Point[] pts = new Point[rp.points.length];
//        for(int i=0; i<rp.points.length; i++){
//            pts[i].setPoint((rp.points[i].x),(rp.points[i].y),(rp.points[i].z));
//        }
//        rp.points=pts;
//        return rp;
//    }
//    
//    public Polygon returnJavaPolygon(){
//        Point[] tempPts =this.points;
//        int[] xpts = new int[tempPts.length];
//        int[] ypts = new int[tempPts.length];
//        for(int i=0; i<3; i++){
//            xpts[i]=(int)(tempPts[i].x+this.centerOfMass.getX()+this.Offset.getX());
//            ypts[i]=(int)(tempPts[i].y+this.centerOfMass.getY()+this.Offset.getY());
//        }
//        return new Polygon(xpts, ypts, tempPts.length);
//    }
//    
//    
//    public void updateCollision(){
//
//        for(int i=0; i<3-1; i++){
//          this.lines[i]= new Line2D.Float(this.points[i].x+this.centerOfMass.getX()+this.Offset.getX(), this.points[i].y+this.centerOfMass.getY()+this.Offset.getY(), this.points[i+1].x+this.centerOfMass.getX()+this.Offset.getX(), this.points[i+1].y+this.centerOfMass.getY()+this.Offset.getY());
//        }
//        this.lines[this.lines.length-1]=  new Line2D.Float(this.points[0].x+this.centerOfMass.getX()+this.Offset.getX(), this.points[0].y+this.centerOfMass.getY()+this.Offset.getY(), this.points[3-1].x+this.centerOfMass.getX()+this.Offset.getX(), this.points[3-1].y+this.centerOfMass.getY()+this.Offset.getY());
//        
//        
//        
//    }
//    
//    public boolean intersects(RigidPolygon rp){
//        for(int i=0; i<this.lines.length; i++){
//            for(int j=0; j<rp.lines.length; j++){
//                if(this.lines[i].intersectsLine(rp.lines[j])){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//    
//    public double getAngleX(){
//        return Math.toDegrees(this.AbsoluteAnlgeX%(2*Math.PI));
//    }
//    
//    public double getAngleY(){
//        return Math.toDegrees(this.AbsoluteAnlgeY%(2*Math.PI));
//    }
//    
//    public double getAngleZ(){
//        return Math.toDegrees(this.AbsoluteAnlgeZ%(2*Math.PI));
//    }
//    
//    
//    public void move(){
//        this.centerOfMass.x+=(this.forward.xDir*this.forward.zDir);
//        this.centerOfMass.y+=(this.forward.yDir*this.forward.zDir);
//        this.updateCollision();
//    }
//    
//    public void moveBy(Vector3D vect){
//        this.centerOfMass.x+=(vect.xDir*vect.zDir);
//        this.centerOfMass.y+=(vect.yDir*vect.zDir);
//        this.updateCollision();
//    }
//    
//    //only used in collisions, rarely
//    public void offsetAllPoints(float x, float y, float z){
//        for(int i=0; i<3; i++){
//            this.points[i].setPoint(this.points[i].getX()+x, this.points[i].getY()+y, this.points[i].getZ()+z);
//        }
//    }
//    public void offsetAllPoints(Point p){
//        for(int i=0; i<3; i++){
//            this.points[i].setPoint(this.points[i].getX()+p.getX(), this.points[i].getY()+p.getY(), this.points[i].getZ()+p.getZ());
//        }
//    }
//    
//    public void setAngleX(float angle){
//        this.angleX = angle;
//        this.AbsoluteAnlgeX = angle;
//    }
//    
//    public void setAngleY(float angle){
//        this.angleY = angle;
//        this.AbsoluteAnlgeY = angle;
//    }
//    
//    public void setAngleZ(float angle){
//        this.angleZ = angle;
//        this.AbsoluteAnlgeZ = angle;
//    }
//    
//    public void setCenter(Point p){
//        this.centerOfMass = p;
//    }
//    
//    public void setXTo(float angle){
//        this.AbsoluteAnlgeX = 0;
//        this.angleX = 0;
//        this.rotatePointXAxisAndUpdateVector(angle);
//    }
//    
//    public void setYTo(float angle){
//        this.AbsoluteAnlgeY = 0;
//        this.angleY = 0;
//        this.rotatePointYAxisAndUpdateVector(angle);
//    }
//    
//    public void setZTo(float angle){
//        this.AbsoluteAnlgeZ = 0;
//        this.angleZ = 0;
//        this.rotatePointZAxisAndUpdateVector(angle);
//    }
//    
//    public void setOffset(Point p){
//        this.Offset = p;
//    }
//    
//    public float getScale(){
//        return this.Scale;
//    }
//    
//    public void setScale(float scale){
//        this.Scale = scale;
//    }
//}
