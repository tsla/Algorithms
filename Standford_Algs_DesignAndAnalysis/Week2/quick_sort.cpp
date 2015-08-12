
#include <iostream>

#define SIZE 10

void exch(int* a, int n1, int n2)
{
  int tmp = a[n1];
  a[n1] = a[n2];
  a[n2] = tmp;
}

void sort(int* a, int lo, int hi)
{
 
  if ( hi <= lo) return;

  int lt = lo, gt = hi;
  int pv = a[lo];

  int i = lo;
  while (i <= gt)
    {
      if (a[i] < pv)
	exch(a, lt++, i++);
      else if (a[i] > pv)
	exch(a, i, gt--);
      else i++;
    }

  sort(a, lo, lt-1);
  sort(a, gt+1, hi);
}
					      

int main()
{
  int array[] = {1,3,4,10,2,5,15,9,6,7};
  std::cout << "Before sort: " << std::endl;
  
  for (int i=0; i < SIZE; i++)
    {
      std::cout << array[i] << "  ";
    }
  std::cout << std::endl;

  sort(array, 0, SIZE-1);

  std::cout << "After sort: " << std::endl;

  for (int i=0; i < SIZE; i++)
    {
      std::cout << array[i] << "  ";
    }
  std::cout << std::endl;
}
