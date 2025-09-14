package com.ifgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ifgram.model.Postagem;
import com.ifgram.repository.PostagemRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private PostagemRepository postagemRepository;

    @GetMapping
    public List<Postagem> listarTodas() {
        return postagemRepository.findAll();
    }

    @PostMapping
    public Postagem criar(@RequestBody Postagem postagem) {
        return postagemRepository.save(postagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> buscarPorId(@PathVariable Long id) {
        Optional<Postagem> postagem = postagemRepository.findById(id);
        return postagem.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Postagem> atualizar(@PathVariable Long id, @RequestBody Postagem dadosAtualizados) {
        return postagemRepository.findById(id).map(postagem -> {
            postagem.setTitulo(dadosAtualizados.getTitulo());
            postagem.setConteudo(dadosAtualizados.getConteudo());
            return ResponseEntity.ok(postagemRepository.save(postagem));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
public ResponseEntity<Void> deletar(@PathVariable Long id) {
    Optional<Postagem> optionalPostagem = postagemRepository.findById(id);

    if (optionalPostagem.isPresent()) {
        postagemRepository.delete(optionalPostagem.get());
        return ResponseEntity.ok().build();
    } else {
        return ResponseEntity.notFound().build();
    }

}
}
