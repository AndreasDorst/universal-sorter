package com.universalsorter.repository;
import com.universalsorter.model.RootVegetable;

import java.util.ArrayList;
import java.util.List;

public class RootVegetableRepository {
    private static final List<RootVegetable> ROOTS_VEG = new ArrayList<>();
    static {
        ROOTS_VEG.add(new RootVegetable.Builder().type("Свёкла").weight(225.0).color("Фиолетовый").build());
        ROOTS_VEG.add(new RootVegetable.Builder().type("Морковь").weight(75.0).color("Оранжевый").build());
        ROOTS_VEG.add(new RootVegetable.Builder().type("Репа").weight(175.0).color("Белый").build());
        ROOTS_VEG.add(new RootVegetable.Builder().type("Редис").weight(22.0).color("Фиолетовый").build());
        ROOTS_VEG.add(new RootVegetable.Builder().type("Картофель").weight(150.0).color("Коричневый").build());
        ROOTS_VEG.add(new RootVegetable.Builder().type("Имбирь").weight(100.0).color("Коричневый").build());
        ROOTS_VEG.add(new RootVegetable.Builder().type("Батат").weight(200.0).color("Розовый").build());
        ROOTS_VEG.add(new RootVegetable.Builder().type("Пастернак").weight(120.0).color("Бежевый").build());
        ROOTS_VEG.add(new RootVegetable.Builder().type("Хрен").weight(50.0).color("Белый").build());
        ROOTS_VEG.add(new RootVegetable.Builder().type("Топинамбур").weight(130.0).color("Коричневый").build());
        ROOTS_VEG.add(new RootVegetable.Builder().type("Козелец").weight(90.0).color("Белый").build());
        ROOTS_VEG.add(new RootVegetable.Builder().type("Сельдерей").weight(110.0).color("Белый").build());
        ROOTS_VEG.add(new RootVegetable.Builder().type("Лук репчатый").weight(70.0).color("Коричневый").build());
        ROOTS_VEG.add(new RootVegetable.Builder().type("Чеснок").weight(30.0).color("Белый").build());

    }

    public RootVegetable getRootVegetable(int i){
        return  ROOTS_VEG.get(i);
    }
    public int getSizeRootList(){
        return ROOTS_VEG.size();
    }

}
