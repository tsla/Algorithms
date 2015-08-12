import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Board {
    
    private int[][] blocks;    
    
    public Board(int[][] blocks)
    {
        this.blocks = Board.clone(blocks);
    }
    
    public int dimension()
    {
        if (blocks == null)
            return 0;
        else
            return blocks.length;
    }
    
    public int hamming()
    {
        int ret = 0;
        for (int i = 0; i < blocks.length; i++)
        {
            for (int j = 0; j < blocks[0].length; j++)
            {
                if ((i * blocks[0].length + j +1) == dimension()*dimension())
                    continue;
                                
                ret += ((blocks[i][j] == (i * blocks[0].length + j +1)) == true ? 0:1);
            }
        }
        return ret;
    }
    
    
    public int manhattan()
    {
        int ret = 0;
        for (int i = 0; i < blocks.length; i++)
        {
            for (int j = 0; j < blocks[0].length; j++)
            {
                if (blocks[i][j] == 0)
                    continue;
                                
                int change = Math.abs((blocks[i][j] - 1)%blocks.length - j) + Math.abs((blocks[i][j] - 1)/blocks.length - i);
                
                ret += change;
                //System.out.print(blocks[i][j] + "  " + change + "; ");
            }
            //System.out.println();
        }
        
        return ret;
    }
    
    public boolean isGoal()
    {
        boolean ret = true; 
        
        if (blocks[dimension()-1][dimension()-1] != 0)
            return false;
        
        for (int i = 0; i < blocks.length; i++)
        {
            for (int j = 0; j < blocks.length; j++)
            {
                if (blocks[i][j] == 0)
                    continue;
                if (blocks[i][j] != (i * blocks[0].length + j +1))
                {
                    ret = false;
                    break;
                }
            }
            if (ret == false)
                break;
        }
        
        return ret;
    }
    
    public Board twin()
    {
        int tmp[][] = Board.clone(blocks);
        
        
        if (tmp[0][0] == 0 || tmp[0][1] == 0)
        {
            int h = tmp[1][0];
            tmp[1][0] = tmp[1][1];
            tmp[1][1] = h;
        }
        else
        {
            int h = tmp[0][0];
            tmp[0][0] = tmp[0][1];
            tmp[0][1] = h;
        }
        
        
        return new Board(tmp);
    }
    
    public boolean equals(Object that)
    {
        if (that == null)
            return false;
        
        for (int i = 0; i < blocks.length; i++)
            if (!Arrays.equals(this.blocks[i], Board.class.cast(that).blocks[i]))
                return false;
        
        return true;
    }

    public Iterable<Board> neighbors()
    {
        return new BoardIteratble();
    }
            
    private static int[][] clone(int[][] blk)
    {
        int tmp[][] = new int[blk.length][blk[0].length];
        for (int i = 0; i < blk.length; i++)
        {
            System.arraycopy(blk[i], 0, tmp[i], 0, blk[i].length);
        }
        
        return tmp;
    }
    
    @Override
    public String toString()
    {
        String ret = "";
        
        ret += dimension();
        ret += "\n";
        
        for (int i = 0; i < dimension(); i++)
        {
            ret += " ";
            for (int j = 0; j < dimension(); j++)
                ret += blocks[i][j] + " ";
            ret += "\n";
        }        
                
        
        return ret;
    }
    

    private class BoardIteratble implements Iterable<Board>
    {
        @Override
        public Iterator<Board> iterator() {
            // TODO Auto-generated method stub
            return new BoardIterator();
        }
    }
    
    private class BoardIterator implements Iterator<Board>
    {
        private Board[] boards = new Board[4];
        private int count = 0;
        
        public BoardIterator()
        {
            if (dimension() != 0)
            {
                int i = 0, j = 0;
                // Find location of 0 
                for (i = 0; i < dimension(); i++)
                {
                    for (j = 0; j < dimension(); j++)
                    {
                        if (blocks[i][j] == 0)
                        {
                            break;
                        }
                    }
                    if (j != dimension())
                        break;
                }
                
                if (i == dimension()) i--;
                if (j == dimension()) j--;
            
                int[][] tmp = Board.clone(blocks);
                if (i > 0)
                {
                    // Move 0 up
                    
                    tmp[i][j] = tmp[i-1][j];
                    tmp[i-1][j] = 0;
                    
                    boards[count] = new Board(tmp);
                    
                    tmp[i-1][j] = tmp[i][j];
                    tmp[i][j] = 0;
                    
                    count++;
                }
                if (i < dimension()-1)
                {
                    // Move 0 down
                    
                    tmp[i][j] = tmp[i+1][j];
                    tmp[i+1][j] = 0;
                    
                    boards[count] = new Board(tmp);
                    
                    tmp[i+1][j] = tmp[i][j];
                    tmp[i][j] = 0;
                    
                    count++;
                }
                
                if (j > 0)
                {
                    // Move 0 left
                    
                    tmp[i][j] = tmp[i][j-1];
                    tmp[i][j-1] = 0;
                    
                    boards[count] = new Board(tmp);
                    
                    tmp[i][j-1] = tmp[i][j];
                    tmp[i][j] = 0;
                    
                    count++;
                }
                if (j < dimension()-1)
                {
                    // Move 0 right
                    
                    tmp[i][j] = tmp[i][j+1];
                    tmp[i][j+1] = 0;
                    
                    boards[count] = new Board(tmp);
                    
                    tmp[i][j+1] = tmp[i][j];
                    tmp[i][j] = 0;
                    
                    count++;
                }
                
                tmp = null;
            }
        }
        
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return count > 0;
        }

        @Override
        public Board next() {
            // TODO Auto-generated method stub\
            if (!hasNext())
                throw new NoSuchElementException();
            return boards[--count];
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub
            
        }
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] blocks = new int[][]{
            {8, 1, 3},
            {4, 0, 2},
            {7, 6, 5}
        };

        Board b = new Board(blocks);
        
        System.out.println("Origin: ");
        System.out.println(b);
        System.out.println("Neibors: ");
        
        for (Board bo : b.neighbors())
        {
            
            System.out.println(bo.manhattan() + "  " + bo.hamming());
            System.out.println(bo);
        }
        
    }
    
    
}
