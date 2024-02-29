package cluster_cli.records
/**
 * Defines the interface used by the Emitter processes in the Emit Cluster
 *
 * @param <T>  The class definition of the data objects to be created
 */
interface EmitInterface <T> {
  /**
   * create() is used to generate new instance of data objects in the Emitter process
   *
   * @return A new instance of type T or null once all the data objects have been created
   */
  T create()
}