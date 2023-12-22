package raf.dsw.classycraft.app.json.CustomDeserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.json.CustomSerializers.DiagramSerializer;

import java.io.IOException;

public class DiagramDeserializer extends StdDeserializer<Diagram> {
    public DiagramDeserializer() {
        this(null);
    }

    protected DiagramDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Diagram deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String name = node.get("diagram name").asText();

        Diagram newDiagram = new Diagram(name);

        if (node.get("diagram elements").isArray()) {
            for (JsonNode element : node.get("diagram elements")) {
                if (element.get("general type").asText().equals("interclass"))
                    newDiagram.loadChild(deserializationContext.readValue(element.traverse(jsonParser.getCodec()), InterClass.class));
                else
                    newDiagram.loadChild(deserializationContext.readValue(element.traverse(jsonParser.getCodec()), Connection.class));
            }
        }

        return newDiagram;
    }
}
