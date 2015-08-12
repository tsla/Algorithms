

public class test {

	private static class Line
	{
		private Point a;
		private Point b;
		private Line next;
		
		private Line (Point a, Point b)
		{
			this.a = a;
			this.b = b;
		}
	}
	
	private static Line head;
	private static Line tail;
	private static int length = 0;
	
	
	private static boolean isDuplicate(Point[] array, int start, int size)
	{
		Line cur = head;
		int i = 0;
		
		for (i = 0; i < length; i++)
		{
			
		}
		
		if (i == length)
		{
			Line nHead = new Line(array[start], array[start+1]);
			nHead.next = head.next;
			head = nHead;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
		Line head = new Line(new Point(1,2), new Point(3,4));
		head.next = new Line(new Point(-1,-2), new Point(-3,-4));
		Line nHead = new Line(new Point(5,6), new Point(7,8));
		
		nHead.next = head;
		head = nHead;
	}

}
