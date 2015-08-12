#include <fstream>
#include <iostream>

#define SIZE 10000

void swap(int* a, int n1, int n2)
{

  int tmp = a[n1];
  a[n1] = a[n2];
  a[n2] = tmp;

}

int compare = 0;

void partition(int* a, int lo, int hi)
{
  if (lo >= hi)
    return;

  compare += hi - lo;

  int i = lo + 1;

  for (int j = lo+1; j <= hi; j++)
    {
      if (a[j] < a[lo])
	{
	  swap(a, j, i);
	  i++;
	}
    }

  swap(a, lo, i-1);

  partition(a, lo, i-2);
  partition(a, i, hi);
}

int main(void)
{
  std::ifstream infile("QuickSort.txt");
  
  int a[SIZE];
  
  for (int i = 0; i < SIZE; i++)
    {
      infile >> a[i];
      //   std::cout << a[i] << "  ";
    }
  std::cout << std::endl;

  partition(a, 0, SIZE-1);

  for (int i = 0; i < SIZE; i++)
    {
      std::cout << a[i] << "  ";
    }
  std::cout << std::endl;

  std::cout << compare << std::endl;
}
