package ru.asarychev.family_food_note.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.asarychev.api.StepsApi;
import ru.asarychev.model.Step;

import java.util.List;

@RestController
public class StepsController implements StepsApi {

    @Override
    public ResponseEntity<Step> createStep(Step step) {
        return StepsApi.super.createStep(step);
    }

    @Override
    public ResponseEntity<List<Step>> getAllSteps() {
        return StepsApi.super.getAllSteps();
    }
}
