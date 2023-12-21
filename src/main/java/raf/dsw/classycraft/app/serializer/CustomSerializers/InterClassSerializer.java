package raf.dsw.classycraft.app.serializer.CustomSerializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.*;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Enum;

import java.io.IOException;

public class InterClassSerializer extends StdSerializer<InterClass> {
    public InterClassSerializer() {
        this(null);
    }

    protected InterClassSerializer(java.lang.Class<InterClass> t) {
        super(t);
    }

    @Override
    public void serialize(InterClass interClass, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        if (interClass instanceof Class) jsonGenerator.writeStringField("type", "class");
        else if (interClass instanceof Enum) jsonGenerator.writeStringField("type", "enum");
        else if (interClass instanceof Interface) jsonGenerator.writeStringField("type", "interface");

        jsonGenerator.writeStringField("name", interClass.getName());

        jsonGenerator.writeStringField("access", interClass.getAccess().toString());

        jsonGenerator.writeNumberField("positionX", interClass.getPosition().getX());
        jsonGenerator.writeNumberField("positionY", interClass.getPosition().getY());

        jsonGenerator.writeNumberField("dimensionWidth", interClass.getSize().getWidth());
        jsonGenerator.writeNumberField("dimensionHeight", interClass.getSize().getHeight());

        if (interClass instanceof Class) {
            jsonGenerator.writeBooleanField("abstract", ((Class) interClass).isAbstract());

            jsonGenerator.writeArrayFieldStart("diagram elements");
            for (ClassContent cn : ((Class) interClass).getClassContents())
                jsonGenerator.writeObject(cn);
            jsonGenerator.writeEndArray();

        } else if (interClass instanceof Enum) {

            jsonGenerator.writeArrayFieldStart("values");
            for (String value : ((Enum) interClass).getValues())
                jsonGenerator.writeObject(value);
            jsonGenerator.writeEndArray();

        } else if (interClass instanceof Interface) {

            jsonGenerator.writeArrayFieldStart("methods");
            for (Method method : ((Interface) interClass).getMethods())
                jsonGenerator.writeObject(method);
            jsonGenerator.writeEndArray();
        }

        jsonGenerator.writeEndObject();
    }
}
