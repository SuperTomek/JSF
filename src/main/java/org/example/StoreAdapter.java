package org.example;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StoreAdapter extends TypeAdapter<Store> {
    @Override
    public void write(JsonWriter jsonWriter, Store store) throws IOException {

    }

    @Override
    public Store read(JsonReader jsonReader) throws IOException {
        Store store = new Store();
        jsonReader.beginObject();
        String fieldname = null;
        while (jsonReader.hasNext()){
            JsonToken token = jsonReader.peek();

            if(token.equals(JsonToken.NAME)){
                fieldname = jsonReader.nextName();
            }

            if("pickers".equals(fieldname)){
                jsonReader.beginArray();
                List<String> pickers = new ArrayList<>();
                token = jsonReader.peek();
                while (jsonReader.hasNext()){
                    pickers.add(jsonReader.nextString());
                }
                store.setPickers(pickers);
                jsonReader.endArray();
            }

            if("pickingStartTime".equals(fieldname)){
                token = jsonReader.peek();
                store.setPickingStartTime(LocalTime.parse(jsonReader.nextString()));
            }

            if("pickingEndTime".equals(fieldname)){
                token = jsonReader.peek();
                store.setPickingEndTime(LocalTime.parse(jsonReader.nextString()));
            }
        }
        jsonReader.endObject();
        return store;
    }
}
