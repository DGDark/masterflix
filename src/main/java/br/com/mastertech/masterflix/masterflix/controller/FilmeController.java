package br.com.mastertech.masterflix.masterflix.controller;

import br.com.mastertech.masterflix.masterflix.model.Filme;
import br.com.mastertech.masterflix.masterflix.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FilmeController {

    @Autowired
    private FilmeService service;

    @GetMapping
    public String index(){
        return "index";
    }


    @GetMapping("/cadastro")
    public String mostraCadastro(){
        return "cadastro";
    }


   @PostMapping("/cadastrar")
    public String cadastrarFilme (Filme filme){
        service.cadastrarFilme(filme);
        return "cadastro";
   }
    @GetMapping("/filmes")
    public ModelAndView listarFilmes(){
        ModelAndView mv = new ModelAndView("listarFilmes");
        Iterable<Filme> filmes = service.listarFilmes();
        mv.addObject("filmes",filmes);
        return mv;
    }

}
