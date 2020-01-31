package br.com.mastertech.masterflix.masterflix.controller;

import br.com.mastertech.masterflix.masterflix.model.Filme;
import br.com.mastertech.masterflix.masterflix.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping("/buscar")
    public String retornarFilmes(){
        return "buscarFilme";
    }

    @GetMapping("filme")
    public String listaFilmes(@RequestParam("nome") String nome, Model model){
        Filme filme = service.listarFilme(nome);
        if (filme != null){
            model.addAttribute("filme", filme);
            return "listarFilme";
        }
        else {
            model.addAttribute("msg", "O filme" + nome + "não foi encontrado");
            return "buscarFilme";
        }
    }

}
