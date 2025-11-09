package com.foodapp.springfoodapp.controller;


import com.foodapp.springfoodapp.entiry.Item;
import com.foodapp.springfoodapp.exception.UserException;

import com.foodapp.springfoodapp.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.v1.prefix}/item")
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/addItem")
    public ResponseEntity<Item> item(@RequestBody @Valid Item item) {
        Item saveItem = itemService.saveitem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveItem);
    }

    @PostMapping("/addMore")
    public ResponseEntity<List<Item>> addItems(@RequestBody @Valid List<Item> items){
        List<Item> itemList = itemService.saveitems(items);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemList);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Item>> fetchAllItems() {
        List<Item> getItemList = itemService.getItems();
        return ResponseEntity.status(HttpStatus.CREATED).body(getItemList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Item> fetchAllItemIds(@PathVariable int id){
        Item getItemIds = itemService.getId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(getItemIds);
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable int itemId){
        String deleteItems = itemService.deleteitemById(itemId);
        return ResponseEntity.status(HttpStatus.CREATED).body(deleteItems);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Item> updateItem(@RequestBody Item updateitem,
                                           @PathVariable int id){

        Item updateItems = itemService.updateItems(updateitem, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(updateitem);
    }

}
