package raf.dsw.classycraft.app.serializer.CustomSerializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;

import java.io.IOException;

public class PackageSerializer extends StdSerializer<Package> {
    public PackageSerializer() {
        this(null);
    }

    protected PackageSerializer(Class<Package> t) {
        super(t);
    }

    @Override
    public void serialize(Package classyPackage, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeStringField("package custom name", classyPackage.getName());
        jsonGenerator.writeArrayFieldStart("children");

        for (ClassyNode cn : classyPackage.getChildren())

            jsonGenerator.writeObject(cn);

        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
