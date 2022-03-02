package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue //ㅅㅣ퀀스값?
    @Column(name="member_id")
    private Long id;

    private String name;

    @Embedded //내장 타입이다..
    private Address address;

    @OneToMany(mappedBy = "member") //멤버의 기준으로 오더는 여러개 있을 수 있으므로 1:N => 양방향 의존, 최대한 바꾸지 않을것!!!
    private List<Order> orders = new ArrayList<>();

}

