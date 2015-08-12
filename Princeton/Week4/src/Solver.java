import java.util.Iterator;
import java.util.NoSuchElementException;

public class Solver {
    private int move = -1;
    private boolean isSolvable = true;
    
    private MaxPQ<SearchNode> pq;
    private MaxPQ<SearchNode> pq2;
    
    public Solver(Board initial)
    {
        pq = new MaxPQ<SearchNode>();
        pq2 = new MaxPQ<SearchNode>();
        
        Board initial2 = initial.twin();
        
        if (!initial.isGoal())
        {
            SearchNode ini = new SearchNode(null, initial.manhattan(), 0, initial);
            SearchNode ini2 = new SearchNode(null, initial2.manhattan(), 0, initial2);
            
            for (Board s: initial.neighbors())
            {
                SearchNode sn = new SearchNode(ini, s.manhattan()+ini.moves+1, ini.moves+1, s);
                pq.insert(sn);
            }
            
            for (Board s: initial2.neighbors())
            {
                SearchNode sn = new SearchNode(ini2, s.manhattan()+ini2.moves+1, ini2.moves+1, s);
                pq2.insert(sn);
            }
            
            while (!pq.max().board.isGoal())
            {
                SearchNode nSN = pq.max();
                pq.delMax();
                for (Board s : nSN.board.neighbors())
                {
                    if (!s.equals(nSN.prevNode.board))
                    {
                        SearchNode sn = new SearchNode(nSN, s.manhattan()+nSN.moves+1, nSN.moves+1, s);
                        pq.insert(sn);
                    }
                }
                
                SearchNode nSN2 = pq2.max();
                pq2.delMax();
                for (Board s : nSN2.board.neighbors())
                {
                    if (!s.equals(nSN2.prevNode.board))
                    {
                        SearchNode sn = new SearchNode(nSN2, s.manhattan()+nSN2.moves+1, nSN2.moves+1, s);
                        pq2.insert(sn);
                    }
                }
                if (pq2.max().board.isGoal())
                {
                    isSolvable = false;
                    break;
                }
            }
            
            move=pq.max().moves;
        }
        
    }
    
    public boolean isSolvable()
    {
        return isSolvable;
    }
    
    public int moves()
    {
        return move;
    }
    public Iterable<Board> solution()
    {
        return new BoardIteratble();
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
        private Board[] b = new Board[pq.max().moves+1];
        private int count = pq.max().moves+1; 
        
        public BoardIterator() {
            // TODO Auto-generated constructor stub
            SearchNode sn = pq.max();
            
            for (int i = 0; i <= pq.max().moves; i++)
            {
                b[i] = sn.board;
                sn = sn.prevNode;
            }
        }
        
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return count > 0;
        }

        @Override
        public Board next() {
            // TODO Auto-generated method stub
            if (!hasNext())
                throw new NoSuchElementException();
            return b[--count];
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub
            
        }    
    }
    
    
    private class SearchNode implements Comparable<SearchNode>
    {
        private SearchNode prevNode;
        private int priority;
        private int moves;
        private Board board;
        
        public SearchNode(SearchNode prev, int priority, int moves, Board board) {
            // TODO Auto-generated constructor stub
            this.prevNode = prev;
            this.priority = priority;
            this.moves = moves;
            this.board = board;
        }
        
        @Override
        public int compareTo(SearchNode that) {
            // TODO Auto-generated method stub
            if (this.priority < that.priority)
                return 1;
            else if (this.priority == that.priority)
                return 0;
            else
                return -1;
        }
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);
        
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            System.out.println("Minimum number of moves = " + solver.moves());
            System.out.println();
            
            for (Board b : solver.solution())
            {
                System.out.println(b);
            }
        }
    }
    
}
