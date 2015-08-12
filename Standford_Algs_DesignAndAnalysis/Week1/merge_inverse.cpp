#include <fstream>
#include <iostream>

//#define SIZE 100000
#define SIZE 10000
#define DEBUG true


unsigned int merge(int* a, int* array, int lo, int hi, int mid)
{
  unsigned int ret = 0;
  
  for (int k=lo; k <= hi; k++)
    {
      array[k] = *(a+k);
    }
  

  int i = lo;
  int j = mid + 1;

  for (int k = lo; k <= hi; k++)
    {
      if (i > mid) a[k] = *(array + j++);
      else if (j > hi) 
	{
	  a[k] = *(array + i++);
	  ret++;
	}
      else if (array[i] < array[j]) a[k] = *(array + i++);
      else 
	{
	  a[k] = *(array + j++);
	  ret = ret + mid - i;
	}
    }
  
  return ret;
}



unsigned int sort(int* a, int* array, int lo, int hi)
{
  if (hi <= lo)
    return 0;
  
  int mid = lo + (hi-lo)/2;

  sort(a, array, lo, mid);
  sort(a, array, mid+1, hi);

  return merge(a, array, lo, hi, mid);
}


unsigned long count (int* a, int* array, int lo, int hi)
{
  if (hi <= lo)
    return 0;
  
  int mid = lo + (hi-lo)/2;

  return count(a, array, lo, mid) + count(a, array, mid+1, hi) + sort(a, array, lo, hi);
}


int main(void)
{
   std::ifstream infile("IntegerArray.txt");
  //  std::ifstream infile("temp.txt");
  
  int a[SIZE];
  int array[SIZE];
  std::cout << "{";
  for (int i = 0; i < SIZE; i++)
    {
      infile >> a[i];
      array[i] = a[i];
      std::cout << a[i] << ", ";
    }
  std::cout << "}";
  //std::cout << count(a, array, 0, SIZE-1) << std::endl;

  std::cout << std::endl;
}
