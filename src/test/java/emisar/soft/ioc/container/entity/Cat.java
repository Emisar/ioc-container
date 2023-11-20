package emisar.soft.ioc.container.entity;

import emisar.soft.ioc.container.annotation.PostConstruct;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Cat extends Animal {

    private Integer lives = 9;
    private String sayLine;

    @PostConstruct
    public void initialize() {
        sayLine = "Meow-meow";
        System.out.println("sayLine = " + sayLine);
    }
}
