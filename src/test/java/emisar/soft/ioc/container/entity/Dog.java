package emisar.soft.ioc.container.entity;

import emisar.soft.ioc.container.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class Dog extends Animal {

    private Integer happyLevel;

    @PostConstruct
    public void initialize() {
        System.out.println("Bark-bark");
    }
}
