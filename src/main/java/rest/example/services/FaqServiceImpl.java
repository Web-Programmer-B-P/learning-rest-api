package rest.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.example.models.Faq;
import rest.example.repositories.FaqRepository;
import rest.example.services.interfaces.FaqService;
import java.util.List;
import java.util.Optional;

@Service
public class FaqServiceImpl implements FaqService {

    @Autowired
    private FaqRepository faqRepository;

    @Override
    public List<Faq> findAll() {
        return (List<Faq>) faqRepository.findAll();
    }

    @Override
    public Optional<Faq> findById(long id) {
        return faqRepository.findById(id);
    }

    @Override
    public Faq save(Faq newFaq) {
        return faqRepository.save(newFaq);
    }

    @Override
    public void deleteById(Long id) {
        faqRepository.deleteById(id);
    }
}
