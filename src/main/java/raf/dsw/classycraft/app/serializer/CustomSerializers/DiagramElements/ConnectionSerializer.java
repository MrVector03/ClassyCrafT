package raf.dsw.classycraft.app.serializer.CustomSerializers.DiagramElements;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Aggregation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Composition;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Dependency;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Generalization;

import java.io.IOException;

public class ConnectionSerializer extends StdSerializer<Connection> {
    public ConnectionSerializer() {
        this(null);
    }

    protected ConnectionSerializer(Class<Connection> t) {
        super(t);
    }

    @Override
    public void serialize(Connection connection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeStringField("name", connection.getName());

        if (connection instanceof Aggregation) jsonGenerator.writeStringField("type", "aggregation");
        else if (connection instanceof Composition) jsonGenerator.writeStringField("type", "composition");
        else if (connection instanceof Dependency) jsonGenerator.writeStringField("type", "dependency");
        else if (connection instanceof Generalization) jsonGenerator.writeStringField("type", "generalization");

        jsonGenerator.writeArrayFieldStart("from-to");

        jsonGenerator.writeObject(connection.getFrom());
        jsonGenerator.writeObject(connection.getTo());

        jsonGenerator.writeEndArray();

        if (connection instanceof Aggregation) {
            jsonGenerator.writeStringField("varName", ((Aggregation) connection).getVarName());
            jsonGenerator.writeStringField("cardFrom", String.valueOf(((Aggregation) connection).getCardFrom()));
            jsonGenerator.writeStringField("cardTo", String.valueOf(((Aggregation) connection).getCardTo()));
        } else if (connection instanceof Composition) {
            jsonGenerator.writeStringField("varName", ((Composition) connection).getVarName());
            jsonGenerator.writeStringField("cardFrom", String.valueOf(((Composition) connection).getCardFrom()));
            jsonGenerator.writeStringField("cardTo", String.valueOf(((Composition) connection).getCardTo()));
        } else if (connection instanceof Dependency) {
            jsonGenerator.writeStringField("dependencyType", ((Dependency) connection).getType());
        }

        jsonGenerator.writeEndObject();
    }
}
