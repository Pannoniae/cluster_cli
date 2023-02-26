package parserTests

import cluster_cli.parse.Parser
import groovy.test.GroovyTestCase
import org.junit.jupiter.api.Test

import static org.junit.Assert.assertFalse

class Test5b extends GroovyTestCase {
  @Test
  void test(){
    String inFileName = "D:/IJGradle/cluster_cli/src/test/groovy/parserTests/test5b"
    Parser parser = new Parser(inFileName)
    assertFalse (parser.parse())
  }
}
