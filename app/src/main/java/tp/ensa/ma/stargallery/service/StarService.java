package tp.ensa.ma.stargallery.service;

import tp.ensa.ma.stargallery.beans.Star;
import tp.ensa.ma.stargallery.dao.IDao;

import java.util.ArrayList;
import java.util.List;

public class StarService implements IDao<Star> {
    private List<Star> stars;
    private static StarService instance;

    // Constructeur privé pour le Singleton
    private StarService() {
        this.stars = new ArrayList<>();
    }

    // Méthode pour obtenir l'instance unique
    public static StarService getInstance() {
        if (instance == null) {
            instance = new StarService();
        }
        return instance;
    }

    @Override
    public boolean create(Star star) {
        return stars.add(star);
    }

    @Override
    public boolean update(Star star) {
        for (Star s : stars) {
            if (s.getId() == star.getId()) {
                s.setName(star.getName());
                s.setImgResId(star.getImgResId());
                s.setStar(star.getStar());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Star star) {
        return stars.remove(star);
    }

    @Override
    public Star findById(int id) {
        for (Star s : stars) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    @Override
    public List<Star> findAll() {
        return stars;
    }
}

