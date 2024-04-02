/* @author G. Hemingway, copyright 2020 - All rights reserved */
#ifndef PARSER_H
#define PARSER_H

/**
 * Class responsible for loading in custom setup scripts and
 * configuring the Universe appropriately.
 */
class Parser {
public:
  /**
   *  Loads the script file and configures the Universe. Consult the
   *  assignment README.md for the syntax of the scripts.
   */
  void loadFile(const char* filename);

private:
  const char* delims()
  {
    return "{}[], ";
  }

  double getDouble();
};

#endif // PARSER_H
