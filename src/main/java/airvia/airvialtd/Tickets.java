package airvia.airvialtd;

import javafx.beans.property.*;

import java.sql.*;
import java.util.ArrayList;

public class Tickets {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;

    public String getValidityStatus() {
        return validityStatus;
    }

    public void setValidityStatus(String validityStatus) {
        this.validityStatus = validityStatus;
    }

    private String validityStatus;

    public Tickets(Integer id, String validityStatus) {
        this.id = id;
        this.validityStatus = validityStatus;
    }

}
