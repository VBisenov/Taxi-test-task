package com.company;

public class IDGenerator {
    private long stat;

    public IDGenerator(){
        this.stat = 100000;
    }

    public long next(){
        return this.stat + 1;
    }

    public long current(){
        return this.stat;
    }

    public void reset(){
        this.stat = 100000;
    }

    public long getId(){
        return this.stat = next();
    }
}
