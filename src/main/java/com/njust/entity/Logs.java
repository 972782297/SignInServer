package com.njust.entity;

import javax.persistence.*;

@Entity
@IdClass(LogsPK.class)
public class Logs {
    private String clazz;
    private String time;
    private String list;

    @Id
    @Column(name = "class", nullable = false, length = 4)
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Id
    @Column(name = "time", nullable = false, length = 3)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "list", nullable = false, length = 15000)
    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Logs logs = (Logs) o;

        if (clazz != null ? !clazz.equals(logs.clazz) : logs.clazz != null) return false;
        if (time != null ? !time.equals(logs.time) : logs.time != null) return false;
        if (list != null ? !list.equals(logs.list) : logs.list != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clazz != null ? clazz.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (list != null ? list.hashCode() : 0);
        return result;
    }
}
