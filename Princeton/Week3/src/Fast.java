import java.util.Arrays;

public class Fast {
    
    private static Line head;
    private static int length = 0;
    private static Point ref;
    private static class Line
    {
        private Point a;
        private Point b;
        private Line next;
        
        private Line(Point a, Point b)
        {
            this.a = a;
            this.b = b;
        }
    }
    
    private static boolean isDuplicate(Point[] array, int start, int size)
    {
        Line cur = head;
        int i = 0;
        
        for (i = 0; i < length; i++)
        {
            // Scan from head to tail, checking if two points exist in array from start to start + size
            boolean dup1 = false;
            boolean dup2 = false;
            //Check cur first point
            for (int j = start; j < start + size; j++)
            {
                if (cur.a == array[j])
                {
                    dup1 = true;
                    break;
                }
            }
            
            if (cur.a == ref)
            	dup1 = true;
            
            //Check cur 2nd point
            for (int j = start; j < start + size; j++)
            {
                if (cur.b == array[j])
                {
                    dup2 = true;
                    break;
                }
            }
            
            if (cur.b == ref)
            	dup2 = true;
            
            if (dup1 && dup2)
            {
                return true;
            }
            cur = cur.next;
        }
        
        if (i == length)
        {
            Line nHead = new Line(array[start], array[start+1]);
            nHead.next = head;
            head = nHead;
            length++;
        }
        
        return false;
    }
    
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
        Point[] cArray = new Point[size-1]; // Scan array
        
        
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
            // Sort array
            System.arraycopy(rArray, 0, cArray, 0, i);
            System.arraycopy(rArray, i+1, cArray, i, size-i-1);
            
            Arrays.sort(cArray, rArray[i].SLOPE_ORDER); // refArray[i] as Origin
            ref = rArray[i];
            
            // Start to scan
            for (int j = 0; j < size-1;)
            {
                int k = 1;
                // Find consecutive equality
                while ((j + k < size-1) && (cArray[j].slopeTo(rArray[i]) == cArray[j+k].slopeTo(rArray[i])))
                {
                    ++k;
                }
                
                // 3 found plus origin = 4 collinear
                if (k >= 3)
                {
                    // Check duplication
                    if (!isDuplicate(cArray, j, k))
                    {
                    	Point[] temp = new Point[k+1];
                    	temp[0] = rArray[i]; 
                    	System.arraycopy(cArray, j, temp, 1, k);
                    	Arrays.sort(temp, new Point(0,0).SLOPE_ORDER);
                    	
                    	/*
                        System.out.print(rArray[i]);
                        rArray[i].draw();
                        for (int l = j; l < j+k; l++)
                        {
                            System.out.print(" -> " + cArray[l]);
                            if ((l+1) < (j+k))
                            	cArray[l].drawTo(cArray[l+1]);
                        }
                        System.out.println();
                        */
                    	for (int l = k; l >= 0; l--)
                        {
                            System.out.print(temp[l]);
                            if ((l-1) >= 0)
                            {
                            	temp[l].drawTo(temp[l-1]);
                            	System.out.print(" -> ");
                            }
                        }
                    	System.out.println();
                    	
                    }
                }
                j = j + k;
            }
            //System.out.println("************************************");
        }    
    }
}
