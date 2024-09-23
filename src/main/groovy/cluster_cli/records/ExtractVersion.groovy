package cluster_cli.records

class ExtractVersion {
  static boolean extractVersion(String version, String nature ) {
    if (1 == 1 || nature == "Net")
      return true
    else {
      String userHome = System.getProperty("user.home")
      String jarLocation = "${userHome}\\.m2\\repository\\jonkerridge\\cluster_cli"
      String gradleLocation = "${userHome}\\.gradle\\caches\\modules-2\\files-2.1\\jonkerridge\\cluster_cli"
      String folder = gradleLocation + "\\$version"
      if (new File(folder).isDirectory()) return true else {
        folder = jarLocation + "\\$version"
        return new File(folder).isDirectory()
      }
    }
  }

  static void main(String[] args) {
    String version = "1.0.0"
    if (!extractVersion(version, null)) println "cli_cluster:Version $version needs to downloaded, please modify the gradle.build file"
    else println "Correct version is available: $version"
  }
}
