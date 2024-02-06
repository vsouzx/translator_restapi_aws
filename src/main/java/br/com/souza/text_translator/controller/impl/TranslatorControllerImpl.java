package br.com.souza.text_translator.controller.impl;

import br.com.souza.text_translator.controller.ITranslatorController;
import br.com.souza.text_translator.dto.TranslatorResponse;
import br.com.souza.text_translator.service.TranslatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/translate")
public class TranslatorControllerImpl implements ITranslatorController {

    private final TranslatorService translatorService;

    public TranslatorControllerImpl(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TranslatorResponse> translateText(@RequestParam("text") String text,
                                                            @RequestParam(value = "sourceLanguage", required = false) String sourceLanguage,
                                                            @RequestParam("targetLanguage") String targetLanguage) throws Exception {
        return new ResponseEntity<>(translatorService.translateText(text, sourceLanguage, targetLanguage), HttpStatus.OK);
    }
}
