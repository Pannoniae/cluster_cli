The software is used to build clustered computing networks. 
The network is defined by a DSL (Domain Specific Language) 
that uses a command line 
interface based on picocli.  The user has to supply two classes; 
one that defines the data to be processed and the other that 
defines what happens to the final results of processing.

This version of software has only been tested using a 
local 127.0.0.0 type network.  

The LocalNode is used to run a single node of the cluster, 
thus multiple instances of the nodes have to be initialised, 
each with a different IP address to form the nodes of the cluster.  
A local host is also required which can access the application classes.  
The local host must be started BEFORE any of the other nodes.  
The nodes assume the host is placed on node 127.0.0.1. 
See clic_run and the test codes, where there are separate hosts 
for each test and scripts to run the required nodes.

The jar LocalNode contained in the corresponding release can 
also be used to run separate instances of the node process.

The code RunParser is used to parse a definition file and create 
a version that can be used to build the system using RunHostLocal. This creates a file
with suffix .clicstruct which is used by the rest of the system.  The parser also
produces a readable test file of the parsed output in a file with suffix .clictxt

The parser will copy the software version number in to the parsed output
and all the codes have the version number stored in them.  A check is
made to ensure that the version number is consistent across all software,
at the time of use.

The following is a DSL definition for test5:
<pre>
host -ip 127.0.0.1
emit -nodes 2 -workers 2 -p int,int!0,23!100,123!200,223!300,323
work -n 2 -w 4 -m update1 -p int!500
collect -n 2 -w 2 -f Test5Results
</pre>

This defines a network of three clusters (emit, work and collect), each with 2 nodes
and varying numbers of workers running in parallel within the node.

An emit node assumes the data object has a create method as defined in 
cli_records.EmitInterface. Parameters for the constructor are passed to each node and
worker in order.  The first entry in the parameter list specifies the data types.
This is followed by the number of nodes multiplied by the number of workers instances of parameter
values, separated by !.  These parameters will govern the number of data objects 
the emit node(s) will create.  In this case there are 2 nodes each having 2 internal emitter processes, 
thus 4 sets of parameters are required.

The work cluster comprises 2 nodes each of 4 internal worker processes.  The name of the method used to
undertake the operation is specified after the  -(m)ethod option.  The parameter string comprises the type specification
followed by required number of parameter values.

The collect cluster comprises 2 nodes of 2 internal collect processes.  It requires the definition of a Collect 
class that implements the cli_records.CollectInterface.  This defines a collate and finalise method that will be
used to process the incoming data objects.  The output can also be stored in a file the base name of which appears
after the -(f)ile option.  The Filenames will be differentiated by the node and internal 
Collect process used to write the file.

The local host for the above specification is invoked as follows (note the .clicstruct suffix is added automatically):

<pre>
class HostRunT5 {
  static void main(String[] args) {
    String structureFileName = "D:\\IJGradle\\cli_cluster\\src\\test\\groovy\\cli_cluster/test5"
    String emitObjectName = "cli_cluster.EmitObject"
    String collectObjectName = "cli_cluster.CollectObject"
    new RunHostLocal(structureFileName, emitObjectName, collectObjectName ).invoke()
  }
}
</pre>

and the node at 127.0.0.2 is invoked as follows:

<pre>
class NodeRun2 {
  static void main(String[] args) {
    new LocalNode("127.0.0.1", "127.0.0.2").invoke()
  }
}
</pre>

If using the LocalNode-?.?.?.jar then you will be asked for the last part of the IP address.

If using NetNode-?.?.?.jar you can either supply the Host IP address as an argument or 
you will be asked to input the Host IP address as a string.

In both cases the same jar can be used to load ANY application, 
provided the version numbers used to parse and run the host match the version
number of the node.jar





