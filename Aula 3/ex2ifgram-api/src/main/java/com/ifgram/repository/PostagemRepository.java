package com.ifgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ifgram.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
}
