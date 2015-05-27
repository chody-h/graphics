package CS355.LWJGL;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Brennan Smith
 */
public class HouseModel extends WireFrame
{
    List<Line3D> lines = new ArrayList<>();
    
    public HouseModel()
    {
            //Make the object:
        //Floor
        lines.add(new Line3D(new Point3D(-5,0,-5), new Point3D(5,0,-5)));
        lines.add(new Line3D(new Point3D(5,0,-5), new Point3D(5,0,5)));
        lines.add(new Line3D(new Point3D(5,0,5), new Point3D(-5,0,5)));
        lines.add(new Line3D(new Point3D(-5,0,5), new Point3D(-5,0,-5)));
        //Ceiling
        lines.add(new Line3D(new Point3D(-5,5,-5), new Point3D(5,5,-5)));
        lines.add(new Line3D(new Point3D(5,5,-5), new Point3D(5,5,5)));
        lines.add(new Line3D(new Point3D(5,5,5), new Point3D(-5,5,5)));
        lines.add(new Line3D(new Point3D(-5,5,5), new Point3D(-5,5,-5)));
        //Walls
        lines.add(new Line3D(new Point3D(-5,5,-5), new Point3D(-5,0,-5)));
        lines.add(new Line3D(new Point3D(5,5,-5), new Point3D(5,0,-5)));
        lines.add(new Line3D(new Point3D(5,5,5), new Point3D(5,0,5)));
        lines.add(new Line3D(new Point3D(-5,5,5), new Point3D(-5,0,5)));        
        //Roof
        lines.add(new Line3D(new Point3D(-5,5,-5), new Point3D(0,8,-5)));
        lines.add(new Line3D(new Point3D(0,8,-5), new Point3D(5,5,-5)));
        lines.add(new Line3D(new Point3D(-5,5,5), new Point3D(0,8,5)));
        lines.add(new Line3D(new Point3D(0,8,5), new Point3D(5,5,5)));
        lines.add(new Line3D(new Point3D(0,8,-5), new Point3D(0,8,5)));
        //Door
        lines.add(new Line3D(new Point3D(1,0,5), new Point3D(1,3,5)));
        lines.add(new Line3D(new Point3D(-1,0,5), new Point3D(-1,3,5)));
        lines.add(new Line3D(new Point3D(1,3,5), new Point3D(-1,3,5)));
    }
    
    
    public Iterator<Line3D> getLines()
    {
        return lines.iterator();
    }
}
