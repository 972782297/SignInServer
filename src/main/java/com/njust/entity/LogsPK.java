package com.njust.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class LogsPK implements Serializable {
    private String clazz;
    private String time;

    @Column(name = "class", nullable = false, length = 4)
    @Id
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Column(name = "time", nullable = false, length = 3)
    @Id
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogsPK logsPK = (LogsPK) o;

        if (clazz != null ? !clazz.equals(logsPK.clazz) : logsPK.clazz != null) return false;
        if (time != null ? !time.equals(logsPK.time) : logsPK.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clazz != null ? clazz.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
