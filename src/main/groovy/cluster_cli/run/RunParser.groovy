package cluster_cli.run

import cluster_cli.parse.Parser
import cluster_cli.records.ExtractVersion

class RunParser {
  String structureSourceFile

  RunParser(String structureSourceFile){
    this.structureSourceFile = structureSourceFile
  }

  void invoke (){
    String version = "1.0.0"
    if (!ExtractVersion.extractVersion(version)){
      println "clicCluster: Version $version needs to downloaded, please modify the gradle.build file"
      System.exit(-1)
    }
    new Parser(structureSourceFile).parse()

  }// invoke

  static void main(String[] args) {
    String structureSourceFile = args[0]
    new RunParser(structureSourceFile).invoke()
  }
}
