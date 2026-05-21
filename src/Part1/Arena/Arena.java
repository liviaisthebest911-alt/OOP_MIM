package Part1.Arena;

public class Arena {
    public void startBattle(Character c1, Character c2) {

        System.out.println("========= BATTLE START =========");
        System.out.println(c1.getStatus() + " VS " + c2.getStatus());
        System.out.println("================================");

        Character attacker = c1;
        Character defender = c2;

        int turn = 1;

        while (c1.health > 0 && c2.health > 0) {

            System.out.println("Turn " + turn);

            System.out.println(attacker.name + "'s turn to attack!");

            try {

                attacker.attack(defender);

            } catch (InsufficientManaException e) {

                System.out.println("Attack failed! " + e.getMessage());
                System.out.println(attacker.name + " skips a turn to recover.");

                if (attacker instanceof Mage) {
                    ((Mage) attacker).recoverMana();
                }
            }

            System.out.println("Status after turn:");
            System.out.println(c1.getStatus());
            System.out.println(c2.getStatus());

            // đổi lượt nếu chưa ai chết
            if (defender.health > 0) {
                Character temp = attacker;
                attacker = defender;
                defender = temp;
            }

            turn++;
        }

        System.out.println("\n========= BATTLE END =========");

        if (c1.health > 0) {
            System.out.println(c1.name + " is the winner!");
        } else {
            System.out.println(c2.name + " is the winner!");
        }

        System.out.println("===============");
    }
}
