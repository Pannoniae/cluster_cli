package cluster_cli.records

class ClassDefinitions implements Serializable{
  Class <EmitInterface> emitClass  // the class associated with the emit phase
  Class <CollectInterface> collectClass // the class associated with the collect phase
  String version  // the version must match for Parser and Host and Nodes
}
