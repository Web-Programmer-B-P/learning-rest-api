package rest.example.services.interfaces;

import rest.example.models.Faq;
import java.util.List;
import java.util.Optional;

public interface FaqService {
    List<Faq> findAll();

    Optional<Faq> findById(long id);

    Faq save(Faq newFaq);

    void deleteById(Long id);
}
