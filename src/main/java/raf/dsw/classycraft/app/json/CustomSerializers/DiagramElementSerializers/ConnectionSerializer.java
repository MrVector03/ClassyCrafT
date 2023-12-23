package raf.dsw.classycraft.app.json.CustomSerializers.DiagramElementSerializers;

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

        jsonGenerator.writeStringField("general type", "connection");

        if (connection instanceof Aggregation) jsonGenerator.writeStringField("type", "aggregation");
        else if (connection instanceof Composition) jsonGenerator.writeStringField("type", "composition");
        else if (connection instanceof Dependency) jsonGenerator.writeStringField("type", "dependency");
        else if (connection instanceof Generalization) jsonGenerator.writeStringField("type", "generalization");

        jsonGenerator.writeObjectField("from", connection.getFrom());
        jsonGenerator.writeObjectField("to", connection.getTo());

        if (connection instanceof Aggregation) {
            jsonGenerator.writeStringField("connection name", ((Aggregation) connection).getVarName());
            jsonGenerator.writeStringField("cardinality from", String.valueOf(((Aggregation) connection).getCardFrom()));
            jsonGenerator.writeStringField("cardinality to", String.valueOf(((Aggregation) connection).getCardTo()));
        } else if (connection instanceof Composition) {
            jsonGenerator.writeStringField("connection name", ((Composition) connection).getVarName());
            jsonGenerator.writeStringField("cardinality from", String.valueOf(((Composition) connection).getCardFrom()));
            jsonGenerator.writeStringField("cardinality to", String.valueOf(((Composition) connection).getCardTo()));
        } else if (connection instanceof Dependency) {
            jsonGenerator.writeStringField("dependency type", ((Dependency) connection).getType());
        }

        jsonGenerator.writeEndObject();
    }
}
