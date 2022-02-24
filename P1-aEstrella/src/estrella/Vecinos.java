package estrella;

public enum Vecinos {
	TL(-1,-1),T(-1,0),TR(-1,1),R(0,1),BR(1,1),B(1,0),BL(1,-1),L(0,-1);

    private final Integer val1;
    private final Integer val2;

    Vecinos(Integer val1, Integer val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    public Integer getVal1() {
        return val1;
    }
    public Integer getVal2() {
        return val2;
    }
}
