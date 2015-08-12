#include "Member.h"

Member::Member(std::string id, std::string name)
  :id(id), name(name)
{
}

std::string Member::getId()
{
  return id;
}

std::string Member::getName()
{
  return name;
}
