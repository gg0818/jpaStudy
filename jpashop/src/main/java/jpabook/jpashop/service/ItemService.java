package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional // flush를 날림. 1. 변경감지를 통해 영속상태를 만들어서 update 쿼리를 날림 = 이걸 단순화해서 jpa에서 한줄로 처리해준게 merge
    public Item updateItem(Long itemId, String name, int price, int stockQuantity ){
        Item findItem = itemRepository.findOne(itemId); // find로 다시 영속으로 들고옴
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
        return findItem; // merge에서 반환하는것과 같음!
        //merge 보다 이게 더 장점인건 바꾸고 싶은 특정 몇개의 필드만 바꿀 수 있다.
        //merge를 쓰게 되면 가격은 바꾸기 싫어서 데이터 set을 하지 않으면 null 값이 넘어간다..!
    }
    public List<Item> findItem(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }


}
