package examplescatalog.catalog;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Каталог примеров.
 */
@Component
public class Catalog {
    private Map<String, Pr> prs = new HashMap<>();

    /**
     * Каталог обновлен и готов к использованию.
     */
    private boolean isReady = false;

    public Pr getPrById(String prId) {
        return prs.get(prId);
    }

    public List<Pr> getAllPrs() {
        return new ArrayList<>(prs.values());
    }

    public void addPr(Pr pr) {
        prs.put(pr.getId(), pr);
    }

    /**
     * Очищает каталог примеров.
     */
    public void clear() {
        prs.clear();
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean isReady) {
        this.isReady = isReady;
    }

}