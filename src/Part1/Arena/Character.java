package Part1.Arena;

/**
 * Lớp trừu tượng đại diện cho một nhân vật trong game.
 * Chứa các thuộc tính và phương thức cơ bản mà mọi nhân vật đều có.
 */
public abstract class Character implements Combatant {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int attackPower;

    /**
     * Constructor cho một nhân vật.
     * @param name Tên nhân vật.
     * @param maxHealth Lượng máu tối đa.
     * @param attackPower Sức tấn công cơ bản.
     */
    public Character(String name, int maxHealth, int attackPower) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth; // Bắt đầu với đầy máu
        this.attackPower = attackPower;
    }



    /**
     * {@inheritDoc}
     * Giảm máu của nhân vật. Máu không thể nhỏ hơn 0.
     */
    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
        System.out.println(this.name + " takes " + damage + " damage, remaining health: " + this.health);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        return this.health > 0;

    }

    /**
     * Lấy thông tin trạng thái hiện tại của nhân vật.
     * @return Một chuỗi mô tả trạng thái.
     */
    public String getStatus() {
        return name + " [Health: " + health + "/" + maxHealth + "]";
    }
}