package com.foodapp.springfoodapp.serviceImpl;

import com.foodapp.springfoodapp.entiry.Item;
import com.foodapp.springfoodapp.repository.ItemRepo;
import com.foodapp.springfoodapp.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepo itemRepo;


    @Override
    public List<Item> saveitems(List<Item> items) {
        List<Item> itemList = itemRepo.saveAll(items);
        return itemList;
    }

    @Override
    public List<Item> getItems() {
        List<Item> getItemList = itemRepo.findAll();
        return getItemList;
    }

    @Override
    public Item saveitem(Item item) {

        Item saveItem = itemRepo.save(item);
        return saveItem;
    }

    @Override
    public String deleteitemById(int itemId) {
        itemRepo.deleteById(itemId);
        return ":your Item had been deleted safely";
    }

    @Override
    public Item updateItems(Item updateitem, int id) {
        Item existByitem = itemRepo.findById(updateitem.getItemId())
                .orElseThrow(() -> new RuntimeException("can't get your ItemId please try to create items"));

        if (existByitem.getItemName() != null) {
            existByitem.setItemName(updateitem.getItemName());
        }

//        existByitem.setItemName(updateitem.getItemName());
//        existByitem.setCost(updateitem.getCost());
//        existByitem.setCategory(updateitem.getCategory());
//        existByitem.setQuantity(updateitem.getQuantity());

        itemRepo.save(existByitem);
        return existByitem;
    }

    @Override
    public Item getId(int id) {
        Item item = itemRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return item;
    }


}
