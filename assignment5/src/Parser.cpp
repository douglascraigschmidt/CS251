/* @author G. Hemingway, copyright 2020 - All rights reserved */
#include "Parser.h"
#include "ObjectFactory.h"
#include <cstring>
#include <fstream>
#include <iostream>
#include <memory>
#include <sstream>
#include <string>

double Parser::getDouble()
{
  std::stringstream s(std::strtok(nullptr, delims()));
  double d;
  s >> d;
  return d;
}

void Parser::loadFile(const char* filename)
{
  std::ifstream file(filename);
  if (file.fail()) {
    std::cout << "Parser not able to open file: " << filename << std::endl;
    exit(-1);
  }

  while (!file.eof()) {
    // read an entire line into memory
    std::string line;
    if (std::getline(file, line)) {
      // Copy string to char*
      std::unique_ptr<char[]> data (new char[line.length()]);
      std::copy(line.begin(), line.end(), data.get());
      // Now tokenize
      char* token = std::strtok(data.get(), delims());
      std::string name(token);
      double mass = getDouble();
      vector2 pos;
      vector2 vel;
      pos[0] = getDouble();
      pos[1] = getDouble();
      vel[0] = getDouble();
      vel[1] = getDouble();
      // Make a happy object
      ObjectFactory::makeObject(name, mass, pos, vel);
    }
  }
  file.close();
}
