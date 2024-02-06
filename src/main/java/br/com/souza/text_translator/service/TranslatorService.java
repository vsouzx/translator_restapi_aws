package br.com.souza.text_translator.service;

import br.com.souza.text_translator.dto.TranslatorResponse;
import br.com.souza.text_translator.enums.AvailableLanguages;
import br.com.souza.text_translator.exceptions.InvalidLanguageException;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TranslatorService {

    private final AmazonTranslate amazonTranslate;

    public TranslatorService(AmazonTranslate amazonTranslate) {
        this.amazonTranslate = amazonTranslate;
    }

    public TranslatorResponse translateText(String text, String sourceLanguage, String targetLanguage) throws Exception {

        validateLanguage(sourceLanguage);
        validateLanguage(targetLanguage);

        TranslateTextRequest request = new TranslateTextRequest();
        request.withText(text);
        request.withSourceLanguageCode(sourceLanguage != null ? sourceLanguage : AvailableLanguages.AUTO.getValue());
        request.withTargetLanguageCode(targetLanguage);

        try{
            TranslateTextResult result = amazonTranslate.translateText(request);
            return TranslatorResponse.builder()
                    .sourceLanguage(result.getSourceLanguageCode())
                    .targetLanguage(result.getTargetLanguageCode())
                    .originalText(text)
                    .translatedText(result.getTranslatedText())
                    .build();
        }catch (Exception e){
            log.error("Erro ao traduzir mensagem: {}", e.getMessage());
            throw e;
        }
    }

    private void validateLanguage(String language) throws Exception {
        List<String> availableLanguages = Arrays.stream(AvailableLanguages.values())
                .map(AvailableLanguages::getValue)
                .toList();

        if(language != null && !availableLanguages.contains(language.toUpperCase())){
            throw new InvalidLanguageException(language);
        }
    }
}
