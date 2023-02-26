package cluster_cli.run

import cluster_cli.run.NodeRun
import jcsp.userIO.Ask

class LocalNode {
  static void main(String[] args) {
    String localIP = "127.0.0." + Ask.string("Last part of local IP address? ")
    new NodeRun("127.0.0.1", localIP).invoke()
  }
}
