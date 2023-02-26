package cluster_cli.processes

import cluster_cli.records.NodeIndex
import cluster_cli.records.TerminalIndex
import groovy_jcsp.ALT
import groovy_jcsp.ChannelOutputList
import jcsp.lang.CSProcess
import jcsp.lang.ChannelInput

class Manager implements CSProcess{

  ChannelInput readyToSend, readyToRead
  ChannelOutputList sendTo
  ChannelOutputList terminateReader
  int nWriteNodes, nReadNodes, nReadInternals, clusterIndex
//  List <ParseRecord> structure
//  ChannelOutput toHost
//  ChannelInput fromHost

  @Override
  void run() {
    println "Manager $clusterIndex running"
    int queueSize = nReadInternals * nReadNodes
    Integer[] indexBuffer = new Integer[queueSize]
    int entries, writeTo, readFrom, terminated
    boolean running
    (entries, writeTo, readFrom, terminated, running) =
        [0, 0, 0, 0, true]
    ALT managerAlt = new ALT([readyToSend, readyToRead])
    boolean [] managerPreCon = [entries > 0, entries < queueSize]
    while (running) {
//      println "Manager $clusterIndex: $managerPreCon, $entries, $indexBuffer"
      switch (managerAlt.priSelect(managerPreCon)){
        case 0: // a write buffer is ready to send object and queue has entries
          def object = readyToSend.read()
          if (!(object instanceof TerminalIndex)){ // a write node has NOT terminated
            // it needs to be sent a reading node index from indexBuffer
            sendTo[(object as NodeIndex).indexValue].write(new NodeIndex (indexValue: indexBuffer[readFrom]))
            readFrom = (readFrom + 1) % queueSize
            entries = entries - 1
          }
          else{
            terminated = terminated + 1
            if (terminated == nWriteNodes) running = false
//            println "Manager $clusterIndex : termination $terminated, $running"
          }
          break
        case 1: // a read buffer is ready to read an object and indexBuffer is not full
//           or the read buffer has terminated, which will be after the connected WriteBuffer
          def object = readyToRead.read()
//          if ( !(object instanceof TerminalIndex)) {
            // save the reading node's index in the queue
            int readingNodeIndex = (object as NodeIndex).indexValue
            indexBuffer[writeTo] = readingNodeIndex as Integer
            writeTo = (writeTo + 1) % queueSize
            entries = entries + 1
//          } else {
//            println "Manager $clusterIndex has read terminalIndex from ReadBuffer"
//            terminated = terminated + 1
//            if (terminated == nReadNodes) running = false
//            println "Manager $clusterIndex : termination $terminated, $running"
//          }
          break
      } // switch
//      println "Manager $indexBuffer"
      managerPreCon = [entries > 0, entries < queueSize]
    } // running
    // manager can now terminate
    println "Manager $clusterIndex  terminated"
  } // run()

}
