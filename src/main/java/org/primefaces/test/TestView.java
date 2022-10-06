package org.primefaces.test;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.AllArgsConstructor;

import lombok.Data;
import org.primefaces.event.CellEditEvent;

@Data
@Named
@ViewScoped
public class TestView implements Serializable {

    private String string;
    private Integer integer;
    private BigDecimal decimal;
    private LocalDateTime localDateTime;
    private List<Row> birthdays = new ArrayList<>();

    @Data @AllArgsConstructor
    public static class Row {
        private LocalDate dob;
    }

    @PostConstruct
    public void init() {
        string = "Welcome to PrimeFaces!!!";
        birthdays.add(new Row(LocalDate.now()));
        birthdays.add(new Row(LocalDate.of(2020, Month.MARCH, 5)));
    }

    public void onCellEdited(CellEditEvent<?> event) {
        if (event.getNewValue() instanceof LocalDate) {
            birthdays.get(event.getRowIndex()).setDob((LocalDate)event.getNewValue());
        }
    }
}
