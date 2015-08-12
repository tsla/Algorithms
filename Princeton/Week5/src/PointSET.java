import java.util.Iterator;
import java.util.NoSuchElementException;

public class PointSET {
	
	private SET<Point2D> points;
	
	public PointSET()
	{
		points = new SET<Point2D>();
	}
	
	public boolean isEmpty()
	{
		return points.isEmpty();
	}
	
	public int size()
	{
		return points.size();
	}
	
	public void insert(Point2D p)
	{
		if (p == null)
			throw new NoSuchElementException();
		
		points.add(p);
	}
	
	public boolean contains(Point2D p)
	{
		if (p == null)
			throw new NoSuchElementException();
		
		return points.contains(p);
	}
	
	public void draw()
	{
		for (Point2D p : points)
		{
			p.draw();
		}
	}
	
	public Iterable<Point2D> range(RectHV rect)
	{
		final RectHV mRect =  rect;
		if (mRect == null)
			throw new NoSuchElementException();
		
		return new Iterable<Point2D>() {
			
			private SET<Point2D> set;
						
			@Override
			public Iterator<Point2D> iterator() {
				// TODO Auto-generated method stub
				
				set = new SET<Point2D>();
				
				for (Point2D p : points)
				{
					if (mRect.contains(p)){
						set.add(p);
					}
				}
				
				return set.iterator();
			}
		};
	}
	
	
	public Point2D nearest(Point2D p)
	{
		if (p == null)
			throw new NoSuchElementException();
		
		Point2D ret = null;
		double dist = 1000;
		
		for (Point2D mp : points)
		{
			if (mp.distanceTo(p) < dist)
			{
				ret = mp;
				dist = mp.distanceTo(p);
			}
		}
		
		return ret;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	}
}
