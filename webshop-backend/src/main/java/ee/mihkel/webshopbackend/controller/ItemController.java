package ee.mihkel.webshopbackend.controller;

import ee.mihkel.webshopbackend.model.Item;
import ee.mihkel.webshopbackend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

//@Controller
@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;
//
//    @Autowired
//    public ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    //    @RequestMapping(method = RequestMethod.GET)
    @GetMapping("items")
    public List<Item> getItems() {
        // new ResponseEntity -- sisu + HTTP status code
        return itemRepository.findAll();
    }

    // 200 - OK
    // 400 - Body puudub
    // 404 - URL on vale
    // 405 - vale POST/GET mida ei ole võimalik
    // 415 - Body on vales tüübis

    @PostMapping("items")
    public String addItem(@RequestBody Item item) {
        Item item2 = itemRepository.save(item);
        Long id = item2.getId();
        return "Ese lisatud: " + item.getTitle() + ". Id'ga: " + id;
    }

    @DeleteMapping("items/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemRepository.deleteById(id);
        return "Ese kustutatud, mille ID: " + id;
    }

    @PutMapping("items")
    public String updateItem(@RequestBody Item item) {
        Item item2 = itemRepository.save(item);
        Long id = item2.getId();
        return "Ese uuendatud: " + item.getTitle() + ". Id'ga: " + id;
    }

    @GetMapping("items/{id}")
    public Item getItem(@PathVariable Long id) {
        if (itemRepository.findById(id).isPresent()) {
            return itemRepository.findById(id).get();
        } else {
            throw new NoSuchElementException();
        }
    }
}
