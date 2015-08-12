#include <fstream>
#include <iostream>
#include <string>
#include <sstream>
#include <time.h>
#include <vector>
#include <random>
#include <cstdlib>
#include <algorithm> 

using namespace std;

struct node {
  int val;
  vector<int> edge;
  int num;
};
 
int cnt = 0;

int main()
{
  vector<node> master;


  /* Get data */
  std::ifstream infile("kargerMinCut.txt");
  //std::ifstream infile("2.txt");
  bool nline;
  
  string line;
  while(getline(infile, line))
    {
      //Get line
      stringstream ss;
      ss << line;			   

      //Get vertex
      int tmp;   
      ss >> tmp;
      
      //Initlize first node for vertex
      node*  n = new node;
      n->val = tmp;
      
      while (ss >> tmp)
	{
	  n->edge.push_back(tmp);
	}
      
      sort(n->edge.begin(), n->edge.end());
      n->num = 0;

      master.push_back(*n);
    }

  int min = -1;
 
  int k = 1;


  srand(time(NULL));
  while (k-- > 0)
    {
      vector<node> nodes = master;

      // random choose edge  
      while (nodes.size() > 2)
	{

	  // get head
	  int n_head = rand() % nodes.size();
      
	  // get tail
	  int tail = rand() % nodes[n_head].edge.size(); 
	  int n_tail = 0;
	  for (n_tail = 0; n_tail < nodes.size(); n_tail++)
	    if (nodes[n_head].edge[tail] == nodes[n_tail].val) break;

	  
	  // Remove self-loop
    
	  // Remove tail in head
	  nodes[n_head].edge.erase(nodes[n_head].edge.begin() + tail);
	  // nodes[n_head].num++;

	  // Remove head in tail
	  for (int i = 0; i < nodes[n_tail].edge.size(); i++)
	    {
	      if (nodes[n_tail].edge[i] == nodes[n_head].val)
		{
		  nodes[n_tail].edge.erase(nodes[n_tail].edge.begin() + i);
		  break;
		}
	    }
    

	  // Merge edge of two vertices 
	  vector<int> tmp(nodes[n_head].edge.size() + nodes[n_tail].edge.size());
	  vector<int>::iterator it = set_union(nodes[n_head].edge.begin(), nodes[n_head].edge.end(), nodes[n_tail].edge.begin(), nodes[n_tail].edge.end(), tmp.begin());
	  tmp.resize(it - tmp.begin());
      
    
	  // Assign merged vector to first vertex
	  nodes[n_head].edge = tmp;

    
	  // Change connection in all nodes
	  for (int i = 0; i < nodes.size(); i++)
	    {
	      int found_head = -1;
	      int found_tail = -1;
	      for (int j = 0; j < nodes[i].edge.size(); j++)
		{
		  if (nodes[i].edge[j] == nodes[n_head].val)
		    found_head = j;
		  if (nodes[i].edge[j] == nodes[n_tail].val)
		    found_tail = j;
		}
	
	      if (found_head != -1 && found_tail != -1)
		{
		  nodes[i].edge.erase(nodes[i].edge.begin() + found_tail);
		  nodes[i].num++;
		}

	      else if (found_tail != -1)
		{
		  nodes[i].edge[found_tail] = nodes[n_head].val;
		  sort(nodes[i].edge.begin(), nodes[i].edge.end());
		}
	    }

    
	  //	  cout << "_" <<  nodes[n_tail].val << "_  ";
	  // remove one vertex
	  nodes.erase(nodes.begin() + n_tail);

	}
 

      if (min == -1)
	min = nodes[1].num;

      if (nodes[1].num < min)
	min = nodes[1].num;
      if (nodes[0].num < min)
	min = nodes[0].num;
  
      cout << min << endl;  
      for (int i = 0; i < nodes.size(); i++)	
	{
	  cout << nodes[i].val << "_  :";
	  for (int j = 0; j < nodes[i].edge.size(); j++)
	    cout << nodes[i].edge[j] << "  ";
	  cout << endl;
	}
      cout << endl;
    }

  min = 1000;
  for (int i = 0; i < master.size(); i++)	
       if (master[i].edge.size() < min) min = master[i].edge.size();
	  
     cout << min << endl;

 
}
