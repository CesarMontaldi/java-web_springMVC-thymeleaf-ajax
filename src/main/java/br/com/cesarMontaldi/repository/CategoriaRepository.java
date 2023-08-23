package br.com.cesarMontaldi.repository;

import br.com.cesarMontaldi.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
