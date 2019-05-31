package lk.nsbm.shared;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import lk.nsbm.shared.enums.NonIdType;
import org.bson.Document;

import java.io.IOException;
import java.util.List;

public class BSONConverter {

    private static ObjectMapper mapper;


    private static void setMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            // StdDateFormat is ISO8601 since jackson 2.9
            mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        }
    }

    public synchronized static Document getDocument(Object obj) {
        setMapper();
        String objJson = null;
        try {
            objJson = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return Document.parse(objJson);
    }

    public synchronized static Object getObject(Document doc, Class entityClass) {
        setMapper();
        String jsonString = doc.toJson();
        Object readValue = null;
        try {
            readValue = mapper.readValue(jsonString, entityClass);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return readValue;
    }

    public synchronized static Document getDocForSingleVal(Object object, Class entityClass) {
        setMapper();

        String objJson = null;
        String jsonVal = "{";
        try {
            objJson = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String idValueName = (new AnnotationFinder()).getIdValueName(entityClass);

        if (idValueName == null) {
            return null;
        }

        jsonVal = jsonVal + "\"" + idValueName + "\":" + objJson + "}";
        return Document.parse(jsonVal);
    }

    public synchronized static Document getDocForSingleVal(Object object, Class entityClass , NonIdType nonIdType) {
        setMapper();

        String objJson = null;
        String jsonVal = "{";
        try {
            objJson = mapper.writeValueAsString(object);
            System.out.println(objJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String idValueName = (new AnnotationFinder()).getNonIdValueName(entityClass, nonIdType);

        if (idValueName == null) {
            return null;
        }

        jsonVal = jsonVal + "\"" + idValueName + "\":" + objJson + "}";
        System.out.println(jsonVal);
        return Document.parse(jsonVal);
    }

    public synchronized static String getStringJson(Object object, Class entityClass , NonIdType nonIdType)  {
        setMapper();

        String objJson = null;
        String jsonVal = "{";
        try {
            objJson = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String idValueName = (new AnnotationFinder()).getNonIdValueName(entityClass, nonIdType);

        if (idValueName == null) {
            return null;
        }

        jsonVal = jsonVal + "\"" + idValueName + "\":" + objJson + "}";
        return jsonVal;
    }

    public synchronized static Document getDocForSingleWithAll(String dataColumnName , List dataList) {
        setMapper();

        String objJson = "{ \""+dataColumnName+"\" : { $all :[" ;

        int i = 1;
        int lengthArr = dataList.size();

        for (Object dataObj :
                dataList) {
            try {
                String dataObjJson = mapper.writeValueAsString(dataObj);
                objJson = objJson + dataObjJson;

                if (i < lengthArr) {
                    objJson = objJson + " , ";
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        objJson = objJson + "]}}";

        return Document.parse(objJson);
    }

}
