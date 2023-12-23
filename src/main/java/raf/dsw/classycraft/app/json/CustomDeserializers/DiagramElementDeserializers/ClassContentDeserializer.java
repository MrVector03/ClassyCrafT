package raf.dsw.classycraft.app.json.CustomDeserializers.DiagramElementDeserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Method;

import java.io.IOException;

public class ClassContentDeserializer extends StdDeserializer<ClassContent> {
    public ClassContentDeserializer() {
        this(null);
    }

    protected ClassContentDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ClassContent deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        ClassContent newClassContent;

        Access access = Access.fromSymbol(node.get("access").asText());
        String returnType = node.get("return type").asText();
        String name = node.get("name").asText();

        if (node.get("type").asText().equals("method"))
            newClassContent = new Method(access, returnType, name);
        else
            newClassContent = new ClassContent(access, returnType, name);

        return newClassContent;
    }
}
