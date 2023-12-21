package raf.dsw.classycraft.app.serializer.CustomSerializers.InterClass;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Enum;

import java.io.IOException;

public class EnumSerializer extends StdSerializer<Enum> {
    public EnumSerializer() {
        this(null);
    }

    protected EnumSerializer(Class<Enum> t) {
        super(t);
    }

    @Override
    public void serialize(Enum anEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

    }
}
