package cluster_cli

import cluster_cli.run.RunParser

class ParserRun {
  static void main(String[] args) {
    String structureSourceFile = "D:\\IJGradle\\cluster_cli\\src\\test\\groovy\\parserTests/test1"
    new RunParser(structureSourceFile).invoke()

  }
}
