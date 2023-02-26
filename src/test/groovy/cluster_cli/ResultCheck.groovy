package cluster_cli

import org.junit.Test

class ResultCheck {
  @Test
  public void test(){
    String testName = "7"
    int nodes = 2
    int collectors = 2
    int minValue, maxValue
    minValue = 501
    maxValue = 900
    List <String> fileNames = []
    for ( n in 0 ..< nodes)
      for ( c in 0 ..< collectors)
        fileNames << "C:/RunJars/clic/Test${testName}Results-${n}-${c}.cliCout"
    println "Files are $fileNames"
    fileNames.each { String name ->
      println "$name"
      List retrieved = []
      File objFile = new File(name)
      objFile.withObjectInputStream { inStream ->
        inStream.eachObject {
          assert ((it.value >= minValue) && (it.value <= maxValue)): "Value ${it.value} out of range"
//          println "${it.value}"
          retrieved << it.value
        }
        inStream.close()
      } // inStream
      println "$retrieved"
    } // filenames
  } // test
}
