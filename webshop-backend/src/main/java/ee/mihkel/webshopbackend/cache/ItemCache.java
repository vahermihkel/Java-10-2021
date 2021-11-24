package ee.mihkel.webshopbackend.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import ee.mihkel.webshopbackend.model.Item;
import ee.mihkel.webshopbackend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class ItemCache {

    @Autowired
    private ItemRepository itemRepository;

    private final LoadingCache<Long, Item> itemCache = CacheBuilder
            .newBuilder()
            .expireAfterAccess(10, TimeUnit.SECONDS)
            .build(new CacheLoader<>() {
                @Override
                public Item load(Long id) {
                    System.out.println("UUESTI LOADIMINE");
                    return null;
                }
            });

    private void updateCacheIfEmpty() {
        System.out.println("UPDATE ÜRITUS");
        System.out.println(itemCache.asMap().values().size());
        System.out.println();
        if (itemCache.asMap().values().size() == 0) {
            System.out.println("TÜHJUS");
            itemRepository.findAll().forEach(item -> itemCache.put(item.getId(), item));
        }
    }

    public Item getItem(Long id) throws ExecutionException {
        updateCacheIfEmpty();
        return this.itemCache.get(id);
    }

    public List<Item> getItems() throws ExecutionException {
        updateCacheIfEmpty();
        return new ArrayList<>(this.itemCache.asMap().values());
    }

    public void updateCache(Item item) {
        updateCacheIfEmpty();
        itemCache.put(item.getId(), item);
    }

    public void deleteFromCache(Long id) {
        updateCacheIfEmpty();
        itemCache.invalidate(id);
    }
}
