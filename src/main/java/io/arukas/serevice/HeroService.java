package io.arukas.serevice;

import io.arukas.entity.Hero;
import io.arukas.repo.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 牛玉贤
 * Date: 2018/7/17
 * Time: 23:37
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    @Transactional(readOnly = true)
    public Hero getHeroById(Long id) {
        return heroRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Hero> getAllHeroes() {
        return heroRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Hero> findHeroesByName(String name) {
        return heroRepository.findByUsername(name);
    }

    @Transactional
    public Hero saveHero(Hero hero) {
        return heroRepository.save(hero);
    }

    @Transactional
    public void deleteHero(Long id) {
        heroRepository.deleteById(id);
    }

}
