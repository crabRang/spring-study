package hello.hellospring.domain;


import jakarta.persistence.*;

// JPA : 인터페이스만 제공, Hibernate : 구현
// JPA는 객체, ORM(Object Relation Mapping)
@Entity // JPA가 관리하는 엔티티
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //insert 시 db가 알아서 생성해주는 값(oracle의 시퀀스개념)
    private Long id; //임의의 값, 고객이 정하는게아닌 시스템이 저장

    @Column
    private String name;

    //@Id, @Column 어노테이션을 통해 DB와 매핑

    //getter, setter 단축어 : alt + insert
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
