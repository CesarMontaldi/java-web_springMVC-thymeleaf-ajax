package br.com.cesarMontaldi.repository;

import br.com.cesarMontaldi.domain.Promocao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public interface PromocaoRepository extends JpaRepository<Promocao, Long> {

    @Query("SELECT p FROM Promocao p WHERE p.preco = :preco")
    Page<Promocao> findByPreco(@Param("preco")BigDecimal preco, Pageable pageable);

    @Query("SELECT p FROM Promocao p WHERE p.titulo LIKE %:search% " +
            "OR p.site LIKE %:search% " +
            "OR p.categoria.titulo LIKE %:search% ")
    Page<Promocao> findByTituloOrSiteOrCategoria(@Param("search") String search, Pageable pageable);

    @Query("SELECT p FROM Promocao p WHERE p.site LIKE :site")
    Page<Promocao> findBySite(@Param("site") String site, Pageable pageable);

    @Query("SELECT distinct p.site FROM Promocao p WHERE p.site LIKE %:site%")
    List<String> findSitesByTermo(@Param("site") String site);

    @Transactional(readOnly = false)
    @Modifying
    @Query("UPDATE Promocao p SET p.likes = p.likes + 1 WHERE p.id = :id")
    void updateSomarLikes(@Param("id") Long id);

    @Query("SELECT p.likes FROM Promocao p WHERE p.id = :id")
    int findLikesById(@Param("id") Long id);

    @Query("SELECT MAX(p.dtCadastro) FROM Promocao p")
    LocalDateTime findPromocaoComUltimaData();

    @Query("SELECT count(p.id) as count, max(p.dtCadastro) as lastDate " +
            "FROM Promocao p where p.dtCadastro > :ultimaData")
    Map<String, Object> countAndMaxNovasPromocoesByDtCadastro(LocalDateTime ultimaData);
}
