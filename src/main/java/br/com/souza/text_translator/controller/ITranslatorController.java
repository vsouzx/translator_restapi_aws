package br.com.souza.text_translator.controller;

import br.com.souza.text_translator.dto.TranslatorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

@Tag(name = "Translator Controller")
@Validated
public interface ITranslatorController {

    @Operation(summary = "Translate the text to the target language.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Client side error", content = @Content),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
    })
    ResponseEntity<TranslatorResponse> translateText(@Parameter(description = "The text that will be translated") @NotNull String text,
                                                     @Parameter(description = "The source language of the text. Available languages: 'en', 'pt', 'pt-PT'.") @Nullable String sourceLanguage,
                                                     @Parameter(description = "The target language translation. Available languages: 'en', 'pt', 'pt-PT'.") @NotNull String targetLanguage) throws Exception;
}
