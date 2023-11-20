package emisar.soft.ioc.container.entity;

import emisar.soft.ioc.container.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Zoo {

    private final Animal cat;
    private final Animal dog;

    @PostConstruct
    public void initialize() {
        System.out.println("Zoo is initialized!");
    }
}
