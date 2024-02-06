package br.com.souza.text_translator.enums;

public enum AvailableLanguages {

    PT("pt"),
    PT_PT("pt-PT"),
    EN("en"),
    AUTO("auto");

    private String value;

    AvailableLanguages(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value.toUpperCase();
    }
}
