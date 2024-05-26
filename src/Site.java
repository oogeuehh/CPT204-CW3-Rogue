public class Site {
    private int i; //row
    private int j; //column

    // initialize board from file
    public Site(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int i() { return i; }
    public int j() { return j; }

    // Manhattan distance between invoking Site and w
    public int manhattanTo(Site w) {
        Site v = this;
        int i1 = v.i();
        int j1 = v.j();
        int i2 = w.i();
        int j2 = w.j();
        return Math.abs(i1 - i2) + Math.abs(j1 - j2);
    }

    // does invoking site equal site w?
    public boolean equals(Site w) {
<<<<<<< HEAD
        return (manhattanTo(w) == 0);
=======
        Site obj = (Site)w;
        return (manhattanTo(obj) == 0);
    }
    @Override
    public String toString() {
        return "(" + i + ", " + j + ")";
>>>>>>> bf73991 (Initial commit)
    }

}
