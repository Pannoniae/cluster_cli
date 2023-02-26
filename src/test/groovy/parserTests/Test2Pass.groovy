package parserTests

import cluster_cli.parse.Parser
import groovy.test.GroovyTestCase
import org.junit.jupiter.api.Test

import static org.junit.Assert.assertTrue

class Test2Pass extends  GroovyTestCase{
  @Test
  void test(){
    String inFileName = "D:/IJGradle/cluster_cli/src/test/groovy/parserTests/test2"
    Parser parser = new Parser(inFileName)
    assertTrue (parser.parse())
  }
}
