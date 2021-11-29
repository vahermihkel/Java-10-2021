package ee.mihkel.webshopbackend.controller;

import ee.mihkel.webshopbackend.model.output.ItemResponse;
import ee.mihkel.webshopbackend.model.Item;
import ee.mihkel.webshopbackend.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

//@Controller
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {

    @Autowired
    private ItemService itemService;
//
//    @Autowired
//    public ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    //    @RequestMapping(method = RequestMethod.GET)+
    @Operation(summary = "Get all items")
    @GetMapping("items")
    public List<Item> getItems() throws ExecutionException {
        // new ResponseEntity -- sisu + HTTP status code
        return itemService.getItems();
    }

    // 200 - OK
    // 400 - Body puudub
    // 404 - URL on vale
    // 405 - vale POST/GET mida ei ole võimalik
    // 415 - Body on vales tüübis

    @Operation(summary = "Add an item")
    @PostMapping("items")
    public ResponseEntity<ItemResponse> addItem(@RequestBody Item item) {
        Item item2 = itemService.addItem(item);
        Long id = item2.getId();
        return new ResponseEntity<>(new ItemResponse("Ese lisatud: " + item.getTitle() + ". Id'ga: " + id), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete an item")
    @DeleteMapping("items/{itemId}")
    public ResponseEntity<ItemResponse> deleteItem(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
//        itemCache.deleteFromCache(itemId);
        return new ResponseEntity<>(new ItemResponse("Ese kustutatud, mille ID: " + itemId), HttpStatus.OK);
    }

    @Operation(summary = "Edit item")
    @PutMapping("items")
    public ResponseEntity<ItemResponse> updateItem(@RequestBody Item item) {
        Item item2 = itemService.editItem(item);
//        itemCache.updateCache(item);
        Long id = item2.getId();
        return new ResponseEntity<>(new ItemResponse("Ese uuendatud: " + item.getTitle() + ". Id'ga: " + id), HttpStatus.OK);
    }

    @Operation(summary = "Get one item by id")
    @GetMapping("items/{itemId}")
    public Item getItem(@PathVariable Long itemId) {
        return itemService.getItem(itemId);
    }
}
