package raf.dsw.classycraft.app.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;
import raf.dsw.classycraft.app.serializer.CustomSerializers.DiagramElements.ConnectionSerializer;
import raf.dsw.classycraft.app.serializer.CustomSerializers.DiagramSerializer;
import raf.dsw.classycraft.app.serializer.CustomSerializers.DiagramElements.ClassContentSerializer;
import raf.dsw.classycraft.app.serializer.CustomSerializers.DiagramElements.InterClassSerializer;
import raf.dsw.classycraft.app.serializer.CustomSerializers.PackageSerializer;
import raf.dsw.classycraft.app.serializer.CustomSerializers.ProjectSerializer;

import java.io.File;
import java.io.IOException;

public class Json {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Json() {
        SimpleModule module = new SimpleModule();

        module.addSerializer(Project.class, new ProjectSerializer());
        module.addSerializer(Package.class, new PackageSerializer());
        module.addSerializer(Diagram.class, new DiagramSerializer());
        module.addSerializer(InterClass.class, new InterClassSerializer());
        module.addSerializer(ClassContent.class, new ClassContentSerializer());
        module.addSerializer(Connection.class, new ConnectionSerializer());

        objectMapper.registerModule(module);
    }

    private static ObjectMapper getDefaultObjectMapper() {
        return new ObjectMapper();
    }

    // PROJECT TO JSON
    public void parseToJson(File file, Project project) throws IOException {
        this.objectMapper.writeValue(file, project);
    }


}
