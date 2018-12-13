package io.confluent.kafka.schemaregistry.maven;

import java.io.File;
import java.util.List;
import java.util.Map;

public class RegisterSchemaRegistry extends TestCompatibilitySchemaRegistryMojo {

    public void setSchemaRegistryUrls(List<String> url) { this.schemaRegistryUrls = url; }
    public List<String> getSchemaRegistryUrls() { return schemaRegistryUrls; }

    public void setSubjects(Map<String, File> subjectsMap) { this.subjects = subjectsMap; }
    public Map<String, File> getSubjects() { return this.subjects; }

}
