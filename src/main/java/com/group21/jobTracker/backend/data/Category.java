package com.group21.jobTracker.backend.data;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Category implements Serializable {

    @NotNull
    private int id = -1;
    
    private String jobType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getjobType() {
        return jobType;
    }

    public void setjobType(String jobType) {
        this.jobType = jobType;
    }

    @Override
    public String toString() {
        return getjobType();
    }

    /*
     * Vaadin DataProviders rely on properly implemented equals and hashcode
     * methods.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || id == -1) {
            return false;
        }
        if (obj instanceof Category) {
            return id == ((Category) obj).id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (id == -1) {
            return super.hashCode();
        }

        return Objects.hash(id);
    }
}
