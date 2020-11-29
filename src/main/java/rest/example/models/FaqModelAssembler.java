package rest.example.models;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import rest.example.controllers.FaqController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FaqModelAssembler implements RepresentationModelAssembler<Faq, EntityModel<Faq>> {
    public static final String ROOT_NAME_IN_JSON = "faqs";

    @Override
    public EntityModel<Faq> toModel(Faq faq) {
        return new EntityModel<Faq>(faq,
                linkTo(methodOn(FaqController.class).getFaqById(faq.getId())).withSelfRel(),
                linkTo(methodOn(FaqController.class).getAllFaqs()).withRel(ROOT_NAME_IN_JSON));
    }
}
