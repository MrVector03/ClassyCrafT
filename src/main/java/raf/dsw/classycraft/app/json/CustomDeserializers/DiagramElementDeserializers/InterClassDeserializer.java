package raf.dsw.classycraft.app.json.CustomDeserializers.DiagramElementDeserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.*;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Enum;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

public class InterClassDeserializer extends StdDeserializer<InterClass> {
    public InterClassDeserializer() {
        this(null);
    }

    protected InterClassDeserializer(java.lang.Class<?> vc) {
        super(vc);
    }

    @Override
    public InterClass deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        System.out.println("reading name");
        String name = node.get("name").asText();
        System.out.println("reading access");
        Access access = Access.fromSymbol(node.get("access").asText());

        System.out.println("reading position");
        double positionX = node.get("positionX").asDouble();
        double positionY = node.get("positionY").asDouble();


        System.out.println("reading size");
        int width = node.get("dimensionWidth").asInt();
        int height = node.get("dimensionHeight").asInt();

        Point2D position = new Point2D.Double(positionX, positionY);
        Dimension dimension = new Dimension(width, height);


        InterClass newInterClass = null;

        System.out.println("reading specifics");

        if (node.get("type").asText().equals("class")) {
            ArrayList<ClassContent> classContents = new ArrayList<>();
            if (node.get("content").isArray()) {
                for (JsonNode element : node.get("content"))
                    classContents.add(deserializationContext.readValue(element.traverse(jsonParser.getCodec()), ClassContent.class));
            }
            boolean isAbstract = node.get("abstract").asBoolean();

            newInterClass = new Class(name, access, position, dimension, classContents, isAbstract);
        } else if (node.get("type").asText().equals("enum")) {
            ArrayList<String> values = new ArrayList<>();

            if (node.get("values").isArray()) {
                for (JsonNode element : node.get("values"))
                    values.add(element.asText());
            }

            newInterClass = new Enum(name, access, position, dimension, values);
        } else if (node.get("type").asText().equals("interface")) {
            ArrayList<Method> methods = new ArrayList<>();

            if (node.get("methods").isArray()) {
                for (JsonNode element : node.get("methods"))
                    methods.add((Method) deserializationContext.readValue(element.traverse(jsonParser.getCodec()), ClassContent.class));
            }

            newInterClass = new Interface(name, access, position, dimension, methods);
        }
        if (newInterClass == null) return null;
        System.out.println("DESERIALIZED AN INTER CLASS");
        System.out.println(newInterClass.getName());
        return newInterClass;
    }
}
