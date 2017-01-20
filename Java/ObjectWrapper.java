package apiProcessing;

import java.io.*;
import java.io.Serializable;

/**
 * Created by Ali on 02/12/2016.
 */
public class ObjectWrapper implements Serializable{

    private static final long serialVersionUID = 42L;

    private String date;
    private Object value;
    private String country;
    private String description;


    public ObjectWrapper(String c, String d, Object val, String desc) {

        country = c;
        date = d;
        value = val;
        description = desc;


    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ObjectWrapper) {
            ObjectWrapper ow = (ObjectWrapper) obj;
            return value == ow.getValue();
        }
        return false;
    }

    @Override
    public String toString() {
        return "ObjectWrapper{" +
                "date='" + date + '\'' +
                ", value=" + value +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                '}';
    }



    public String getDescription() {

        return description;
    }

    public String getDate() {

        return date;
    }

    public Object getValue() {
        return value;
    }

    public String getCountry() {
        return country;
    }
}
