#ifndef MEMBER_H
#define MEMBER_H

#include <string>

class Member
{
  Member(std::string id, std::string name);
  
  std::string getId();
  std::string getName();

 private:
  std::string id;
  std::string name;
};

#endif
