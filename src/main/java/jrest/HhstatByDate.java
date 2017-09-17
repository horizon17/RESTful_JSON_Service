package jrest;

import java.util.*;

public class HhstatByDate {

    private String lang;
    private Map<Date,Tech> techs = null;
    //private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private List<String> nextLangs = new ArrayList<String>();

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Map<Date, Tech> getTechs() {
        return techs;
    }

    public void setTechs(Map<Date, Tech> techs) {
        this.techs = techs;
    }

    /*public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }*/

}