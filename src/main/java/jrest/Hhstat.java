package jrest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hhstat {

    private String lang;
    private List<Tech> techs = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private List<String> nextLangs = new ArrayList<String>();

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<Tech> getTechs() {
        return techs;
    }

    public void setTechs(List<Tech> techs) {
        this.techs = techs;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}