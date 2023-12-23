package raf.dsw.classycraft.app.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;
import raf.dsw.classycraft.app.json.CustomDeserializers.DiagramDeserializer;
import raf.dsw.classycraft.app.json.CustomDeserializers.DiagramElementDeserializers.ClassContentDeserializer;
import raf.dsw.classycraft.app.json.CustomDeserializers.DiagramElementDeserializers.ConnectionDeserializer;
import raf.dsw.classycraft.app.json.CustomDeserializers.DiagramElementDeserializers.InterClassDeserializer;
import raf.dsw.classycraft.app.json.CustomDeserializers.PackageDeserializer;
import raf.dsw.classycraft.app.json.CustomDeserializers.ProjectDeserializer;
import raf.dsw.classycraft.app.json.CustomSerializers.DiagramElementSerializers.ClassContentSerializer;
import raf.dsw.classycraft.app.json.CustomSerializers.DiagramElementSerializers.ConnectionSerializer;
import raf.dsw.classycraft.app.json.CustomSerializers.DiagramElementSerializers.InterClassSerializer;
import raf.dsw.classycraft.app.json.CustomSerializers.DiagramSerializer;
import raf.dsw.classycraft.app.json.CustomSerializers.PackageSerializer;
import raf.dsw.classycraft.app.json.CustomSerializers.ProjectSerializer;

import java.io.File;
import java.io.IOException;

public class Json {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public Json() {
        SimpleModule module = new SimpleModule();

        module.addSerializer(Project.class, new ProjectSerializer());
        module.addSerializer(Package.class, new PackageSerializer());
        module.addSerializer(Diagram.class, new DiagramSerializer());
        module.addSerializer(InterClass.class, new InterClassSerializer());
        module.addSerializer(ClassContent.class, new ClassContentSerializer());
        module.addSerializer(Connection.class, new ConnectionSerializer());

        module.addDeserializer(Project.class, new ProjectDeserializer());
        module.addDeserializer(Package.class, new PackageDeserializer());
        module.addDeserializer(Diagram.class, new DiagramDeserializer());
        module.addDeserializer(InterClass.class, new InterClassDeserializer());
        module.addDeserializer(ClassContent.class, new ClassContentDeserializer());
        module.addDeserializer(Connection.class, new ConnectionDeserializer());

        objectMapper.registerModule(module);
    }

    private static ObjectMapper getDefaultObjectMapper() {
        return new ObjectMapper();
    }

    private static ObjectMapper getMapper() {
        return objectMapper;
    }

    // PROJECT TO JSON
    public void parseProjectToJson(File file, Project project) throws IOException {
        objectMapper.writeValue(file, project);
    }

    // JSON TO PROJECT
    public Project parseToProject(File file) throws IOException {
        return objectMapper.readValue(file, Project.class);
    }

    public void parseDiagramToTemplate(File file, Diagram diagram) throws IOException {
        objectMapper.writeValue(file, diagram);
    }

    public Diagram parseToDiagram(File file) throws IOException {
        return objectMapper.readValue(file, Diagram.class);
    }
}
