package parserTests

import groovy.test.AllTestSuite
import groovy.test.GroovyTestSuite
import junit.framework.*
import junit.textui.TestRunner

static Test suite(){
//  def gts = new GroovyTestSuite()
  def suite = AllTestSuite.suite(".", "Test*Pass.groovy")
  return suite
}

TestRunner.run(suite())
