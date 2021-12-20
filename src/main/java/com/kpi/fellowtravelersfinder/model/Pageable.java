package com.kpi.fellowtravelersfinder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pageable {
    private int pageNumber = 0;
    private int itemsCount = 10;

    public int getOffset() {
        if (pageNumber < 0 || itemsCount < 0) {
            return 0;
        }
        return pageNumber * itemsCount;
    }

    public int getItemsCount() {
        if (itemsCount < 0) {
            return 0;
        }
        return itemsCount;
    }
}
