package raf.dsw.classycraft.app.json.CustomDeserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDeserializer extends StdDeserializer<Project> {
    public ProjectDeserializer() {
        this(null);
    }

    public ProjectDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Project deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        System.out.println(node);

        String name = node.get("project name").asText();
        String author = node.get("author").asText();
        String path = node.get("path").asText();

        Project newProject = new Project(name, author, path);

        if (node.get("packages").isArray()) {
            for (JsonNode element : node.get("packages")) {
                // System.out.println(element);
                newProject.addChild(deserializationContext.readValue(element.traverse(jsonParser.getCodec()), Package.class));
            }
        }

        //System.out.println(newProject.getChildren().get(0).getName());

        return newProject;
    }
}
