package raf.dsw.classycraft.app.json.CustomDeserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;

import java.io.IOException;

public class PackageDeserializer extends StdDeserializer<Package> {
    public PackageDeserializer() {
        this(null);
    }

    protected PackageDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Package deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String name = node.get("package name").asText();

        Package newPackage = new Package(name);

        if (node.get("children").isArray()) {
            for (JsonNode element : node.get("children")) {
                if (element.get("type").asText().equals("diagram"))
                    newPackage.addChild(deserializationContext.readValue(element.traverse(jsonParser.getCodec()), Diagram.class));
                else if (element.get("type").asText().equals("package"))
                    newPackage.addChild(deserializationContext.readValue(element.traverse(jsonParser.getCodec()), Package.class));
            }
        }

        return newPackage;
    }
}
