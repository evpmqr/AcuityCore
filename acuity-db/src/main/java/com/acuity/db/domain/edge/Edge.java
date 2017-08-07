package com.acuity.db.domain.edge;

import com.acuity.db.domain.vertex.Vertex;

/**
 * Created by Zach on 8/6/2017.
 */
public class Edge extends Vertex{

    protected String _from;
    protected String _to;

    public Edge(String _from, String _to) {
        this._from = _from;
        this._to = _to;
    }

    public Edge() {
    }

    public String getFrom() {
        return _from;
    }

    public String getTo(){
        return _to;
    }
}
