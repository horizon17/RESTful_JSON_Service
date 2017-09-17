package jrest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Tech {

    private String tech;
    private Integer count;
    private Date date;
    //private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    /*public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }*/

}