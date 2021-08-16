package org.d11.boot.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Formats LocalDateTime objects in objects that will be serialized by Gson as yyyy-MM-dd HH:mm.
 * Use like this:
 * Gson gson = new GsonBuilder()
 *         .setPrettyPrinting()
 *         .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
 *         .create();
 */
public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime> {

    /**
     * Date time formatter used to format LocalDateTimes.
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public JsonElement serialize(final LocalDateTime localDateTime, final Type type, final JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(localDateTime.format(DATE_TIME_FORMATTER));
    }

}
