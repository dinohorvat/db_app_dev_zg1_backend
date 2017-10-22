package database.application.development.model.messages;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
public class OutputHeader {
    @JsonView(Views.PrimitiveField.class)
    private Long page;
    @JsonView(Views.PrimitiveField.class)
    private Long pages;
    @JsonView(Views.PrimitiveField.class)
    private Long pageSize;
    @JsonView(Views.PrimitiveField.class)
    private Long results;

    public OutputHeader() {
        this.page = 1L;
        this.pages = 1L;
        this.results = 1L;
        this.pageSize = 50L;
    }

    public OutputHeader(Long page, Long pages, Long pageSize, Long results) {
        this.page = page;
        this.pages = pages;
        this.pageSize = pageSize;
        this.results = results;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[')
                .append("page=")
                .append(page)
                .append(", pages=")
                .append(pages)
                .append(", results=")
                .append(results)
                .append(", pageSize=")
                .append(pageSize)
                .append(']');
        return sb.toString();
    }
}

