package com.acuity.db.domain.vertex;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class Vertex {

    protected String _key;
    protected String _id;
    protected String _rev;

    public String getKey() {
        return _key;
    }

    public String getID() {
        return _id;
    }

    public String getRev() {
        return _rev;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Vertex)) return false;

        Vertex vertex = (Vertex) object;

        return _key != null ? _key.equals(vertex._key) : vertex._key == null;
    }


    @Override
    public String toString() {
        return "Vertex{" +
                "_key='" + _key + '\'' +
                ", _id='" + _id + '\'' +
                ", _rev='" + _rev + '\'' +
                '}';
    }
}
