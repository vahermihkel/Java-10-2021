package ee.mihkel.webshopbackend.service;

import ee.mihkel.webshopbackend.cache.ItemCache;
import ee.mihkel.webshopbackend.model.Item;
import ee.mihkel.webshopbackend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemCache itemCache;

    @Autowired
    ItemRepository itemRepository;

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item addItem(Item item) {
        itemCache.updateCache(item);
        return itemRepository.save(item);
    }

    public Item editItem(Item item) {
//        itemCache.updateCache(item);
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
//        itemCache.deleteFromCache(itemId);
        itemRepository.deleteById(id);
    }

    public Item getItem(Long id) {
        if (itemRepository.findById(id).isPresent()) {
            return itemRepository.findById(id).get();
        } else {
            throw new NoSuchElementException();
        }
    }
}
