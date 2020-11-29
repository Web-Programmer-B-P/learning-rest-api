package rest.example.controllers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import rest.example.exceptions.MainNotFoundException;
import rest.example.models.Faq;
import rest.example.models.FaqModelAssembler;
import rest.example.services.FaqServiceImpl;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class FaqController {
    private final static String NOT_FOUND_EXCEPTION = "Faq with id: %s doesn't exist!";
    public static final String UPDATE_EXCEPTION_EXPRESSION = "Faq with id: %s which you want to update doesn't exist!\nTap correct id and try again!";
    public static final String URI_WITH_ID = "/faqs/{id}";
    public static final String ROOT_URI = "/faqs";

    private final FaqServiceImpl service;
    private final FaqModelAssembler faqModelAssembler;

    public FaqController(FaqServiceImpl service, FaqModelAssembler faqModelAssembler) {
        this.service = service;
        this.faqModelAssembler = faqModelAssembler;
    }

    @GetMapping(path = ROOT_URI)
    public CollectionModel<EntityModel<Faq>> getAllFaqs() {
        List<EntityModel<Faq>> allFaqs = service.findAll()
                .stream()
                .map(faqModelAssembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(allFaqs, linkTo(methodOn(FaqController.class).getAllFaqs()).withSelfRel());
    }

    @GetMapping(path = URI_WITH_ID)
    public EntityModel<Faq> getFaqById(@PathVariable long id) {
        Faq faqFromDb = service.findById(id)
                .orElseThrow(() -> new MainNotFoundException(String.format(NOT_FOUND_EXCEPTION, id)));
        return faqModelAssembler.toModel(faqFromDb);
    }

    @PostMapping(path = ROOT_URI)
    EntityModel<Faq> createNewFaq(@RequestBody Faq newFaq) {
        Faq createdFaq = service.save(newFaq);
        return faqModelAssembler.toModel(createdFaq);
    }

    @PutMapping(path = URI_WITH_ID)
    EntityModel<Faq> updateFaq(@RequestBody Faq newFaq, @PathVariable Long id) {
        return faqModelAssembler.toModel(service.findById(id)
                .map(faqFromDb -> getNewFaq(newFaq, faqFromDb)
                ).orElseThrow(() -> new MainNotFoundException(String.format(UPDATE_EXCEPTION_EXPRESSION, id))));
    }

    @DeleteMapping(path = URI_WITH_ID)
    void deleteFaq(@PathVariable Long id) {
        service.deleteById(id);
    }

    /**
     *
     * @param newFaq Пришедшая новая карточка
     * @param faqFromDb Карточка полученная из бд по id
     * @return Возвращаем faqFromDb и меняем ее данные на newFaq
     */
    private Faq getNewFaq(Faq newFaq, Faq faqFromDb) {
        faqFromDb.setTitle(newFaq.getTitle());
        faqFromDb.setContent(newFaq.getContent());
        return service.save(faqFromDb);
    }
}
