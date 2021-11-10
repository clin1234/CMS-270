public class Avatar implements CommonBehavior {
    private String alias;

    public Avatar(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public void touch(String t) {
        System.out.println("Nearly the universe is within touchability, yet you let "+alias+" feel a " + t);
    }

    @Override
    public void help() {
        System.out.println("As a near omnipotent being, " + alias + " can help you.");
    }

    @Override
    public void summon(int c) {
        System.out.println("You have summoned "+c+" copies of "+alias);
    }

}
