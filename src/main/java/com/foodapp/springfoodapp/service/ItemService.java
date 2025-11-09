package com.foodapp.springfoodapp.service;

import com.foodapp.springfoodapp.entiry.Item;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ItemService {

	List<Item> saveitems(List<Item> items);
	
	List<Item> getItems();
	
	Item saveitem(Item item);

    String deleteitemById(int itemId);

	Item updateItems(Item updateitem, int id);


	Item getId(int id);
}
