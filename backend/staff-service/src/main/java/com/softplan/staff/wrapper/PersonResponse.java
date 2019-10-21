package com.softplan.staff.wrapper;

import com.softplan.staff.model.Person;

import java.io.Serializable;
import java.util.List;

public class PersonResponse implements Serializable {

    private int status;
    private String message;
    private List<Person> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Person> getResult() {
        return result;
    }

    public void setResult(List<Person> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "PersonResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
