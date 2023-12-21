package raf.dsw.classycraft.app.serializer.CustomSerializers.InterClass;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;

import java.io.IOException;

public class ClassSerializer extends StdSerializer<Class> {
    public ClassSerializer() {
        this(null);
    }

    protected ClassSerializer(java.lang.Class<Class> t) {
        super(t);
    }

    @Override
    public void serialize(Class aClass, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        // jsonGenerator.writeStringField("name", aClass.getName());
//
        // jsonGenerator.writeStringField("access", aClass.getAccess().toString());
//
        // jsonGenerator.writeNumberField("positionX", aClass.getPosition().getX());
        // jsonGenerator.writeNumberField("positionY", aClass.getPosition().getY());
//
        // jsonGenerator.writeNumberField("dimensionWidth", aClass.getSize().getWidth());
        // jsonGenerator.writeNumberField("dimensionHeight", aClass.getSize().getHeight());
//
        // jsonGenerator.writeBooleanField("abstract", aClass.isAbstract());
//
        // jsonGenerator.writeArrayFieldStart("diagram elements");
        // for (ClassContent cn : aClass.getClassContents())
        //     jsonGenerator.writeObject(cn);
        // jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
