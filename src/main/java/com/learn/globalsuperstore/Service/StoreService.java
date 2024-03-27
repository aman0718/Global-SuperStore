package com.learn.globalsuperstore.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.globalsuperstore.Constants;
import com.learn.globalsuperstore.Item;
import com.learn.globalsuperstore.Repository.StoreRepository;


@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    public Item getItem(int index){
        return storeRepository.getItem(index);
    }

    public void addItem(Item item){
        storeRepository.addItem(item);
    }


    public void updateItem(Item item, int index){
        storeRepository.updateItem(item, index);
    }

    public List<Item> getAllItems(){
        return storeRepository.getAllItems();
    }

    public Item getItemFromId(String id){

        int index = getIndexFromId(id);
        return index == Constants.NOT_FOUND ? new Item() : getItem(index);
    }


    public int getIndexFromId(String id){
        for (int i = 0; i < getAllItems().size(); i++) {
            if(getItem(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }

    //forbid update if order date is passed more than 5 days
    public boolean within5Days(Date newDate, Date oldDate){
        long diff = Math.abs(newDate.getTime() - oldDate.getTime());
        return (int) (TimeUnit.MILLISECONDS.toDays(diff)) <=5;
    }


    public String handleSubmit(Item item){

        int index = getIndexFromId(item.getId());
        String status = Constants.SUCCESS_STATUS;

        if(index == Constants.NOT_FOUND){
            addItem(item);
        }
        else if(within5Days(item.getDate(), getItem(index).getDate())){
            updateItem(item, index);
        }
        else{
            status = Constants.FAILED_STATUS;
        }
        return status;
    }
    
}
