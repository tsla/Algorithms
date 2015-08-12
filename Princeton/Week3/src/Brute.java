import java.util.Arrays;

public class Brute {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Get filename
		if (args.length == 0)
		{
			return;
		}
		

		StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
		In reader = new In(args[0]);
		
		// Get size
		int size = reader.readInt();
		reader.readLine();
		Point[] rArray = new Point[size]; // Reference array		
		
		int i = 0;
		while (reader.hasNextLine())
		{
			rArray[i++] = new Point(reader.readInt(), reader.readInt());
			rArray[i-1].draw();
			reader.readLine();
		}

		reader.close();
        
		for (i = 0; i < size; i++)
		{
			for (int j = i + 1; j < size; j++)
			{
				for (int k = j + 1; k < size; k++)
				{
					for (int l = k + 1; l < size; l++)
					{
						if ((rArray[j].slopeTo(rArray[i]) == (rArray[k].slopeTo(rArray[i]))) && (rArray[k].slopeTo(rArray[i]) == (rArray[l].slopeTo(rArray[i]))))
						{
							Point[] temp = new Point[4];
							temp[0] = rArray[i];
							temp[1] = rArray[j];
							temp[2] = rArray[k];
							temp[3] = rArray[l];
							Arrays.sort(temp,new Point(0,0).SLOPE_ORDER);
							
							temp[3].drawTo(temp[2]);
							temp[2].drawTo(temp[1]);
							temp[1].drawTo(temp[0]);
							
							
							//System.out.println(rArray[i] + " -> " + rArray[j] + " -> " + rArray[k] + " -> " + rArray[l]);
							System.out.println(temp[3] + " -> " + temp[2] + " -> " + temp[1] + " -> " + temp[0]);
						}
					}
				}
			}
		}
	}

}
