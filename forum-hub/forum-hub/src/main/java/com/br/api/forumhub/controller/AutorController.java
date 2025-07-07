package com.br.api.forumhub.controller;

import com.br.api.forumhub.domain.autor.Autor;
import com.br.api.forumhub.domain.autor.DadosAutor;
import com.br.api.forumhub.repository.AutorRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/autores")
@SecurityRequirement(name = "bearer-key")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping("/{id}")
    public ResponseEntity buscarAutor(@PathVariable Long id){
        var autor = autorRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosAutor(autor));
    }

    @GetMapping
    public ResponseEntity<Page<DadosAutor>> listarAutores(@PageableDefault(sort = {"nome"}) Pageable pageable){
        var pagina = autorRepository.findAll(pageable).map(DadosAutor::new);
        return ResponseEntity.ok(pagina);
    }

    @PostMapping
    public Autor cadastrar(@RequestBody @Valid Autor a){
        return autorRepository.save(a);
    }

    @PutMapping()
    public Autor editar(@RequestBody @Valid Autor a){
        return autorRepository.save(a);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable Long id){
        autorRepository.deleteById(id);
    }

}
