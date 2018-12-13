# lein-schema-registry

A Leiningen plugin to help managing Avro schemas with the Confluent's Schema Registry.

## Usage

Put `[lein-schema-registry "0.1.0-SNAPSHOT"]` into the `:plugins` vector of your `:user`
profile.

Put `[lein-schema-registry "0.1.0-SNAPSHOT"]` into the `:plugins` vector of your project.clj.

FIXME: and add an example usage that actually makes sense:

    $ lein schema-registry

## Configuration

lein-schema-registry follows the features in the [Confluent's schema-registry Maven plugin](https://docs.confluent.io/current/schema-registry/docs/maven-plugin.html "Confluent schema registry documentation").  
Configuration is defined in `resources/schemas.edn`.   

Typical configuration look like this:

```edn
{:schema-registry-urls ["http://localhost:8081"]
 :output-directory "resources/schemas/avro"
 :subject-patterns ["^[tld.organization.].*(key|value)$"]
 :subjects [{:topic "input-topic"
             :key "resources/schemas/avro/input-topic-key.avsc"
             :value "resources/schemas/avro/input-topic-value.avsc"}
            {:topic "output-topic"
             :value "resources/schemas/avro/output-value.avsc"}]}
```

## Tasks

### `$ lein schema-registry:download`

The `download` task is used to pull down schemas from a schema-registry server.  
This plugin is used to download Avro schemas for the requested subjects and write them to a folder on the local file system.

``schema-registry-urls``
  Schema Registry URLs to connect to.

  * Type: String[]
  * Required: true

``output-directory``
  Output directory to write the schemas to.

  * Type: File
  * Required: true

``schema-extension``
  The file extension to use for the output file name. This must begin with a '.' character.

  * Type: File
  * Required: false
  * Default: .avsc

``subject-patterns``
  The subject patterns to download. This is a list of regular expressions. Patterns must match the entire subject name.

  * Type: String[]
  * Required: true

``pretty-print-schemas``
  Flag to determine if the schemas should be pretty printed when written to disk.

  * Type: Boolean
  * Required: false
  * Default: true

### `$ lein schema-registry:test-compatibility`

This task is used to read schemas from the local file system and test them for compatibility against the
schema-registry server(s). This goal can be used in a continuous integration pipeline to ensure that schemas in the
project are compatible with the schemas in another environment.

``schema-registry-urls``
  Schema Registry URLs to connect to.

  * Type: String[]
  * Required: true

``subjects``
  Map containing subject to schema path of the subjects to be registered.

  * Type: Map<String, File>
  * Required: true

### `$ lein schema-registry:register`

This task is used to read schemas from the local file system and register them on the target schema-registry server(s).
This task can be used in a continuous deployment pipeline to push schemas to a new environment.

``schema-registry-urls``
  Schema Registry URLs to connect to.

  * Type: String[]
  * Required: true

``subjects``
  Map containing subject to schema path of the subjects to be registered.

  * Type: Map<String, File>
  * Required: true

## License

Copyright © 2018 Adikteev

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
