(defproject lein-schema-registry "0.1.0"
  :description ""
  :url "http://github.com/Adikteev/lein-schema-registry"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true
  :dependencies [[io.confluent/kafka-schema-registry-maven-plugin "5.0.1" :exclusions [org.apache.maven.plugin-tools/maven-plugin-annotations]]]
  :repositories [["confluent" "https://packages.confluent.io/maven/"]]
  :java-source-paths ["src/java/main"]
  :source-paths ["target"])
