#include <fstream>
#include <iostream>
#include <sstream>
#include <stack>

//#define SIZE 875715
#define SIZE 9

using namespace std;

struct node {
  unsigned long val;
  unsigned long leader;
  stack<unsigned long> edge;
  bool discovered = false;
  unsigned long finish_time;
};


unsigned long leader[SIZE] = {0};
node vertices[SIZE];
node vertices_rev[SIZE];

unsigned long processed_nodes = 0;
unsigned long current_source;




void exch(unsigned long n1, unsigned long n2)
{
  // Swap vertices[i] with place finish_time
  node tmp = vertices_rev[n1];
  unsigned long time = vertices_rev[n1].finish_time;
  vertices_rev[n1] = vertices_rev[n2];
  vertices_rev[n2] = tmp;
}

void sort(unsigned long lo, unsigned long hi)
{
 
  if ( hi <= lo) return;

  unsigned long lt = lo, gt = hi;
  node pv = vertices_rev[lo];

  unsigned long i = lo;
  while (i <= gt)
    {
      if (vertices_rev[i].finish_time < pv.finish_time)
	exch(lt++, i++);
      else if (vertices_rev[i].finish_time > pv.finish_time)
	exch(i, gt--);
      else i++;
    }

  sort(lo, lt-1);
  sort(gt+1, hi);
}

bool FIRST = true;

/* DFS */
void DFS(int cur)
{
  if (FIRST)
    {
      vertices_rev[cur].discovered = true;

      while (vertices_rev[cur].edge.size() > 0)
	{
	  unsigned long tmp = vertices_rev[cur].edge.top();
	  vertices_rev[cur].edge.pop();
	  if (!vertices_rev[tmp].discovered)
	    DFS(tmp);

	}
      processed_nodes++;
      vertices_rev[cur].finish_time = processed_nodes;
      vertices[cur].finish_time = processed_nodes;
    }
  else
    {
      vertices[cur].discovered = true;
      leader[current_source]++;

      while (vertices[cur].edge.size() > 0)
	{
	  unsigned long tmp = vertices[cur].edge.top();

	  vertices[cur].edge.pop();
	  if (!vertices[tmp].discovered)
	    DFS(tmp);

	}
    }

}


/* Leader sort */
void exch_l(unsigned long n1, unsigned long n2)
{
  // Swap vertices[i] with place finish_time
  unsigned long tmp = leader[n1];
  leader[n1] = leader[n2];
  leader[n2] = tmp;
}

void sort_l(unsigned long lo, unsigned long hi)
{
 
  if ( hi <= lo) return;

  unsigned long lt = lo, gt = hi;
  unsigned long pv = leader[lo];

  unsigned long i = lo;
  while (i <= gt)
    {
      if (leader[i] > pv)
	exch_l(lt++, i++);
      else if (leader[i] < pv)
	exch_l(i, gt--);
      else i++;
    }

  sort_l(lo, lt-1);
  sort_l(gt+1, hi);
}


/* MAIN */
int main()
{
  for (unsigned long i = 1; i < SIZE; i++)
    vertices_rev[i].val = i;


  /* Read data  */
  //ifstream infile("SCC.txt");
  ifstream infile("small_test.txt");

  string line;
  while(getline(infile, line))
    {
      stringstream ss;
      ss << line;

      unsigned long end;
      unsigned long start;
      ss >> start;
      ss >> end;

      vertices[start].edge.push(end);
      vertices_rev[end].edge.push(start);
    }

  /* Implement algorithm
   * DFS-LOOP
   */

  for (unsigned long i = SIZE-1; i >= 1; i--)
    {
      if (!vertices_rev[i].discovered)
	{
	  current_source = i;
	  DFS(i);
	}
    }


  // Quick sort finish time
  sort(1,SIZE-1);

  /*
   * DFS 2
   */

  FIRST = false;
  

  for (unsigned long i = SIZE -1; i >= 1; i--)
    {
      if (!vertices[vertices_rev[i].val].discovered)
	{
	  current_source = vertices_rev[i].val;
	  DFS(vertices_rev[i].val);
	}
    }
  




sort_l(1, SIZE-1);
  
  for (int i = 1; i < 6; i++)
    cout << leader[i] << "  ";
  cout << endl;

}
