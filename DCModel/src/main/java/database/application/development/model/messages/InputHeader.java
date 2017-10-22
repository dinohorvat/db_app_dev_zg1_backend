package database.application.development.model.messages;

import database.application.development.model.util.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;

@Slf4j
@NoArgsConstructor
@Setter
@Getter
public class InputHeader {
    private LinkedHashMap<String, Order> orderBy;
    private Long pageNumber;
    private Long pageSize;

    public InputHeader(LinkedHashMap<String, Order> orderBy, Long pageNumber, Long pageSize) {
        this.orderBy = orderBy;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[')
                .append("orderBy=")
                .append(orderBy)
                .append(", pageNumber=")
                .append(pageNumber)
                .append(", pageSize=")
                .append(pageSize)
                .append(']');
        return sb.toString();
    }
}
