package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        //1단계 - db 연결
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 시작
        try{
            Order order = new Order();
            order.addOrderItem(new OrderItem());
            tx.commit(); //커밋 --이 순간 insert sql을 db에 보낸다.
        }catch (Exception e){
            tx.rollback() ;
        }finally {
            em.close();
        }

        emf.close();
    }
}
