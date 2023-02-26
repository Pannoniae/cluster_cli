package cluster_cli.run


import jcsp.userIO.Ask

class NetNode {
  static void main(String[] args) {
    String hostIP
    if (args.size() == 1)
      hostIP = args[0]
    else
      hostIP = Ask.string("Host IP address? ")
    new NodeRun(hostIP).invoke()
  }
}
