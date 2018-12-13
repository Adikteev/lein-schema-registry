package io.confluent.kafka.schemaregistry.maven;

import java.io.File;
import java.util.List;

public class DownloadSchemaRegistry extends DownloadSchemaRegistryMojo {

    public void setSchemaExtension(String extension) { this.schemaExtension = extension; }
    public String getSchemaExtension() { return schemaExtension; }

    public void setSubjectPatterns(List<String> pattern) { this.subjectPatterns = pattern; }
    public List<String> getSubjectPatterns() { return subjectPatterns; }

    public void setOutputDirectory(File output) { this.outputDirectory = output; }
    public File getOutputDirectory() { return outputDirectory; }

    public void setSchemaRegistryUrls(List<String> url) { this.schemaRegistryUrls = url; }
    public List<String> getSchemaRegistryUrls() { return schemaRegistryUrls; }

    public void setPrettyPrintSchemas(Boolean prettify) { this.prettyPrintSchemas = prettify; }
    public Boolean getPrettyPrintSchemas() { return prettyPrintSchemas; }

}
