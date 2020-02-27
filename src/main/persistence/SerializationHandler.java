package persistence;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Represents a custom json serializer that preserves type information
 * From: https://stackoverflow.com/questions/3629596/deserializing-an-abstract-class-in-gson
 */
public class SerializationHandler implements JsonDeserializer<Object>, JsonSerializer<Object> {

    private static final String CLASS_TYPE_META_KEY = "CLASS_META_KEY";

    /**
     * EFFECTS: Overrides default deserializer to take into account object type tag
     */
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        JsonObject jsonObj = jsonElement.getAsJsonObject();
        String className = jsonObj.get(CLASS_TYPE_META_KEY).getAsString();
        try {
            Class<?> clz = Class.forName(className);
            return jsonDeserializationContext.deserialize(jsonElement, clz);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }

    /**
     * EFFECTS: Overrides default serializer to take into account object type tag
     */
    @Override
    public JsonElement serialize(Object o, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonEle = jsonSerializationContext.serialize(o, o.getClass());
        jsonEle.getAsJsonObject().addProperty(CLASS_TYPE_META_KEY,
                o.getClass().getCanonicalName());
        return jsonEle;
    }
}
