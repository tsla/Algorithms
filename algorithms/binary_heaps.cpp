#include <iostream>

#define SIZE 10
int last = 0;

void swap(int* a, int n1, int n2)
{
  int tmp = a[n2];
  a[n2] = a[n1];
  a[n1] = tmp;
}

void sink(int* a, int mKey)
{
  while (2*mKey <= last)
    {
      int larger = 2*mKey;
      if (larger < last && a[larger] < a[larger+1]) larger++;
      if (a[mKey] >= a[larger]) break;
      
      //Swap
      swap(a, mKey, larger);
      mKey = larger;
    }
}

void swim(int* a, int k)
{
  while (k>1 && a[k/2] < a[k])
    {
      swap(a, k/2, k);
      k = k/2;
    }
}

void delMax(int* a)
{
  swap(a, 1, last);
  last = last-1;
  sink(a, 1);
}

void insert(int* a, int nKey)
{
  a[++last] = nKey;

  swim(a, last);
}

int main()
{
  int a[SIZE];
  insert(a,3);
  insert(a,2);
  insert(a,1);
  insert(a,26);
  
  for (int i = 1; i <= last; i++)
    {
      std::cout <<  a[i] << "  ";
    }

  std::cout << std::endl;

  std::cout << "Before sink: " << std::endl;
  a[++last] = 4;

  for (int i = 1; i <= last; i++)
    {
      std::cout <<  a[i] << "  ";
    }

  std::cout << std::endl;
  
  sink(a, 2);

  std::cout << "After sink: " << std::endl;
  for (int i = 1; i <= last; i++)
    {
      std::cout <<  a[i] << "  ";
    }

  std::cout << std::endl;

  delMax(a);

  std::cout << "Delete max: " << std::endl;
  for (int i = 1; i <= last; i++)
    {
      std::cout <<  a[i] << "  ";
    }

  std::cout << std::endl;
}
