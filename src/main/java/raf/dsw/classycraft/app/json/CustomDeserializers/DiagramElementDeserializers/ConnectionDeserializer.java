package raf.dsw.classycraft.app.json.CustomDeserializers.DiagramElementDeserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Aggregation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Composition;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Dependency;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Generalization;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Enum;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Interface;

import java.io.IOException;

public class ConnectionDeserializer extends StdDeserializer<Connection> {
    public ConnectionDeserializer() {
        this(null);
    }

    protected ConnectionDeserializer(java.lang.Class<?> vc) {
        super(vc);
    }

    @Override
    public Connection deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String name = node.get("name").asText();
        InterClass from = getConnectionClass(jsonParser, deserializationContext, node.get("from"), "from");

        InterClass to = getConnectionClass(jsonParser, deserializationContext, node.get("to"), "to");
        Connection newConnection;

        if (node.get("type").asText().equals("aggregation")
                || node.get("type").asText().equals("composition")) {
            String varName = node.get("connection name").asText();
            char cardFrom = node.get("cardinality from").asText().charAt(0);
            char cardTo = node.get("cardinality to").asText().charAt(0);

            if (node.get("type").asText().equals("aggregation"))
                newConnection = new Aggregation(name, from, to, varName, cardFrom, cardTo);
            else
                newConnection = new Composition(name, from, to, varName, cardFrom, cardTo);
        } else if (node.get("type").asText().equals("dependency")) {
            String depType = node.get("dependency type").asText();
            newConnection = new Dependency(name, from, to, depType);
        } else
            newConnection = new Generalization(name, from, to);

        return newConnection;
    }

    private InterClass getConnectionClass(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNode node, String type) throws IOException {
        InterClass interClass;
        if (node.get("type").asText().equals("class"))
            interClass = deserializationContext.readValue(node.traverse(jsonParser.getCodec()), InterClass.class);
        else if (node.get("type").asText().equals("interface"))
            interClass = deserializationContext.readValue(node.traverse(jsonParser.getCodec()), InterClass.class);
        else
            interClass = deserializationContext.readValue(node.traverse(jsonParser.getCodec()), InterClass.class);
        return interClass;
    }
}
