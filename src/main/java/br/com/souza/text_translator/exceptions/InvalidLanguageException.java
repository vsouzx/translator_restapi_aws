package br.com.souza.text_translator.exceptions;

public class InvalidLanguageException extends Exception{

    public InvalidLanguageException(String language) {
        super(language);
    }
}
