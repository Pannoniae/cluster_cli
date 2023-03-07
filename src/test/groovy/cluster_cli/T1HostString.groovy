package cluster_cli

import cluster_cli.run.HostRun

class T1HostString {
  static void main(String[] args) {
    String structure = "D:\\IJGradle\\cluster_cli\\src\\test\\groovy\\parserTests_101/test1"
    String emitClassName = "cluster_cli.EmitObject"
    String collectClassName = "cluster_cli.CollectObject"
    new HostRun(structure, emitClassName, collectClassName, "Local").invoke()
  }
}
