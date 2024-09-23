package cluster_cli.records

class ExtractVersion {
  static boolean extractVersion(String version, String nature ) {
    return true
  }

  static void main(String[] args) {
    String version = "1.0.0"
    if (!extractVersion(version, null)) println "cli_cluster:Version $version needs to downloaded, please modify the gradle.build file"
    else println "Correct version is available: $version"
  }
}
