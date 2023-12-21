package raf.dsw.classycraft.app.json.CustomSerializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;

import java.io.IOException;

public class DiagramSerializer extends StdSerializer<Diagram> {
    public DiagramSerializer() {
        this(null);
    }

    protected DiagramSerializer(Class<Diagram> t) {
        super(t);
    }

    @Override
    public void serialize(Diagram diagram, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeStringField("diagram name", diagram.getName());

        jsonGenerator.writeArrayFieldStart("diagram elements");
        for (ClassyNode cn : diagram.getChildren())
            jsonGenerator.writeObject(cn);
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
