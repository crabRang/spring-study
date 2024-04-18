package hello.hellospring.domain;

public class Member {
    private Long id; //임의의 값, 고객이 정하는게아닌 시스템이 저장
    private String name;

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
