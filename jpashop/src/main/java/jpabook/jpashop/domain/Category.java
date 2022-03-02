package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Category { //하나의 부모가 여러가지 카테고리를 가지는 구조
    @Id @GeneratedValue
    @Column(name="category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name="category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name="item_id")) //item에 들어갈 컬럼 값 맵핑
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = LAZY) //여기서 이해하면.. 객체 기준으로 현재 객체(객체가 자식 입장)는 하나의 부모를 가진다.
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")// 현재 객체가 여러 자식을 가진다.
    private List<Category> child = new ArrayList<>();

    //== 연관관계 메서드 =====
    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }

}
