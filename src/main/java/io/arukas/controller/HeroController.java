package io.arukas.controller;

import io.arukas.entity.Hero;
import io.arukas.serevice.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 牛玉贤
 * Date: 2018/7/17
 * Time: 23:42
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class HeroController {

    @Autowired
    private HeroService heroService;

    @GetMapping("/heroes/{id}")
    public Hero getHeroById(@PathVariable("id") Long id) {
        return heroService.getHeroById(id);
    }

    @GetMapping("/heroes")
    public List<Hero> getHeroes() {
        return heroService.getAllHeroes();
    }

    @GetMapping("/heroes/")
    public List<Hero> searchHeroes(@RequestParam("name") String name) {
        return heroService.findHeroesByName(name);
    }

    @PostMapping("/heroes")
    public Hero addHero(@RequestBody Hero hero) {
        return heroService.saveHero(hero);
    }

    @PutMapping("/heroes")
    public Hero updateHero(@RequestBody Hero hero) {
        return heroService.saveHero(hero);
    }

    @DeleteMapping("/heroes/{id}")
    public void deleteHero(@PathVariable("id") Long id) {
        heroService.deleteHero(id);
    }

}
