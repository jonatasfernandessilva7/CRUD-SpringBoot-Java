package com.example.crud.controllers;

import com.example.crud.models.ProdutoEntity;
import com.example.crud.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProdutosControllers {
    @Autowired
    private ProdutoRepository __produtoRepository;
    @RequestMapping(value = "/produtos", method = RequestMethod.GET)
    public List<ProdutoEntity> Get(){
        return __produtoRepository.findAll();
    }

    @RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProdutoEntity> GetById(@PathVariable(value = "id") long id){
        Optional<ProdutoEntity> produto = __produtoRepository.findById(id);

        if(produto.isPresent())
            return new ResponseEntity<ProdutoEntity>(produto.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/produto/cadastro", method = RequestMethod.POST)
    public ProdutoEntity createProduto(@RequestBody ProdutoEntity produto){
        return __produtoRepository.save(produto);
    }

    @RequestMapping(value = "/produto/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ProdutoEntity> putPassword(@PathVariable(value = "id") long id ,@RequestBody ProdutoEntity novoNome) {
        Optional<ProdutoEntity> produto = __produtoRepository.findById(id);
        if (produto.isPresent()) {
            ProdutoEntity produtoNovo = produto.get();
            produtoNovo.setNome(novoNome.getNome());
            __produtoRepository.save(produtoNovo);
            return new ResponseEntity<ProdutoEntity>(produto.get(), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/produto/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ProdutoEntity> deleteProduto(@PathVariable(value = "id") long id){
        Optional<ProdutoEntity> produto = __produtoRepository.findById(id);
        if (produto.isPresent()){
            __produtoRepository.delete(produto.get());
            return new ResponseEntity<>(produto.get(), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
