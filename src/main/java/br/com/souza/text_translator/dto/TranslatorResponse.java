package br.com.souza.text_translator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TranslatorResponse {

    private String sourceLanguage;
    private String targetLanguage;
    private String originalText;
    private String translatedText;

}
