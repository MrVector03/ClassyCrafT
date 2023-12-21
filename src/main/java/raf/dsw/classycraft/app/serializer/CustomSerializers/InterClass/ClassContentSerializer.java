package raf.dsw.classycraft.app.serializer.CustomSerializers.InterClass;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;

import java.io.IOException;

public class ClassContentSerializer extends StdSerializer<ClassContent> {
    public ClassContentSerializer() {
        this(null);
    }

    protected ClassContentSerializer(Class<ClassContent> t) {
        super(t);
    }

    @Override
    public void serialize(ClassContent classContent, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeStringField("access", classContent.getAccess().toString());
        jsonGenerator.writeStringField("returnType", classContent.getReturnType());
        jsonGenerator.writeStringField("name", classContent.getName());

        jsonGenerator.writeEndObject();
    }
}
