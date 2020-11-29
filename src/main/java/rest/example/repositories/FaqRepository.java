package rest.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rest.example.models.Faq;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {
}
