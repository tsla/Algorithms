#include <fstream>
#include <iostream>

#define SIZE 100000

void merge(int* a, int* array, int lo, int hi, int mid)
{

  for (int k=lo; k <= hi; k++)
    {
      array[k] = *(a+k);
    }
  int i = lo;
  int j = mid + 1;
  
  for (int k = lo; k <= hi; k++)
    {
      if (i > mid) a[k] = *(array + j++);
      else if (j > hi) a[k] = *(array + i++);
      else if (array[i] < array[j]) a[k] = *(array + i++);
      else a[k] = *(array + j++);
    }
}



void sort(int* a, int* array, int lo, int hi)
{
  if (hi <= lo)
    return;
  
  int mid = lo + (hi-lo)/2;

  sort(a, array, lo, mid);
  sort(a, array, mid+1, hi);

  merge(a, array, lo, hi, mid);
}


void merge_sort(int* a, int size)
{
  int array[SIZE];
  for (int i = 0; i<size; i++)
    array[i] = *(a+i);

 sort(a, array, 0, size-1);

 for (int i = 0; i < SIZE; i++)
    {
      std::cout << a[i] << "  ";
    }

  std::cout << std::endl;
}

void merge_inverse(int* a, int* array, int lo, int hi, int mid)
{

  for (int k=lo; k <= hi; k++)
    {
      array[k] = *(a+k);
    }
  int i = lo;
  int j = mid + 1;
  
  for (int k = lo; k <= hi; k++)
    {
      if (i > mid) a[k] = *(array + j++);
      else if (j > hi) a[k] = *(array + i++);
      else if (array[i] < array[j]) a[k] = *(array + i++);
      else a[k] = *(array + j++);
    }
}


unsigned long count_split(int* a, int* array, int lo, int hi, int mid)
{  
  sort(a, array, lo, mid);
  sort(a, array, mid+1, hi);

  merge(a, array, lo, hi, mid);
}

unsigned long count (int* a, int* array, int lo, int hi)
{
  if (hi <= lo)
    return 0;
  
  int mid = lo + (hi-lo)/2;

  return count(a, array, lo, mid) + count(a, array, mid+1, hi) + count_split(a, array, lo, hi, mid);
}


int main(void)
{
  std::ifstream infile("IntegerArray.txt");
  
  int temp[SIZE];
  int array[SIZE];
  for (int i = 0; i < SIZE; i++)
    {
      infile >> temp[i];
      array[i] = temp[i];
    }

  //  merge_sort(temp, SIZE);
  
  std::cout << count(temp, array, 0, SIZE-1) << std::endl;
}
