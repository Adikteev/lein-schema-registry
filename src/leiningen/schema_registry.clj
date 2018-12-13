(ns leiningen.schema-registry
  (:require [clojure.java.io :as io])
  (:import (io.confluent.kafka.schemaregistry.maven DownloadSchemaRegistry TestCompatibilitySchemaRegistry RegisterSchemaRegistry)))

(defn- fetch-resources []
  (merge (clojure.edn/read-string (slurp "resources/defaults.edn"))
         (clojure.edn/read-string (slurp "resources/schemas.edn"))))

(defn ->mvn-subjects [subjects]
  (reduce
    (fn [res {:keys [topic key value]}]
      (cond-> res
              (not (nil? key)) (assoc (format "%s-key" topic) (io/file key))
              (not (nil? value)) (assoc (format "%s-value" topic) (io/file value))))
    {}
    subjects))

(defn- conform-to-java-context [config]
  (-> config
      (update :subjects ->mvn-subjects)
      (update :output-directory io/file)))

(defn get-config []
  (-> (fetch-resources)
      (conform-to-java-context)))

(defn download [_]
  (let [d (DownloadSchemaRegistry.)
        context (get-config)]
    (.setSchemaRegistryUrls d (get context :schema-registry-urls))
    (.setSchemaExtension d (get context :schema-extension))
    (.setSubjectPatterns d (get context :subject-patterns))
    (.setOutputDirectory d (get context :output-directory))
    (.setPrettyPrintSchemas d (get context :pretty-print-schemas))
    (.execute d)))

(defn test-compatibility [_]
  (let [d (TestCompatibilitySchemaRegistry.)
        context (get-config)]
    (.setSchemaRegistryUrls d (get context :schema-registry-urls))
    (.setSubjects d (get context :subjects))
    (.execute d)))

(defn register [_]
  (let [d (RegisterSchemaRegistry.)
        context (get-config)]
    (.setSchemaRegistryUrls d (get context :schema-registry-urls))
    (.setSubjects d (get context :subjects))
    (.execute d)))

(defn schema-registry
  ""
  {:subtasks [#'download #'test-compatibility #'register]}
  [project & [sub-name]]
  (case sub-name
    "download" (download project)
    "test-compatibility" (test-compatibility project)
    "register" (register project)
    nil (leiningen.core.main/warn "No task specified.")
    (leiningen.core.main/warn "Unknown task.")))
