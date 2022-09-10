import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType;
    public static int[] heroesHealth = {250, 270, 280, 400 , 900, 300, 230, 200};
    public static int[] heroesDamage = {25, 20, 15, 0 , 10, 30 , 35, 10};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic" , "Medic", "Golem",
            "Lucky", "Berserk" ,"Thor" };
    public static int roundNumber = 0;

    static Random random = new Random();



    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        medicTreat();
        lucki();
        Berserk();
        Thor();
        heroesHit();
        printStatistics();
        bossDamage =50;
    }
    public static void Thor(){
        boolean bossSleep = random.nextBoolean();
        if (heroesHealth[7] > 0){
            if (bossSleep){
                bossDamage = 0;

            }
        }


    }
    public static void Berserk(){
        Random random = new Random();
        int randomBlock = random.nextInt(25);
        if (heroesHealth[6] > 0) {
            heroesHealth[6] += randomBlock;
            heroesDamage[6] += randomBlock;
        }

    }
    public static void lucki() {
        Random random = new Random();
        boolean a = random.nextBoolean();
        if (a) {
            heroesHealth[5] += bossDamage;
        }
    }






    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ---------------");
        /*String defence;
        if (bossDefenceType == null) {
            defence = "No defence";
        } else {
            defence = bossDefenceType;
        }*/
        System.out.println("Boss health: " + bossHealth + "; damage: "
                + bossDamage + "; defence: " + (bossDefenceType == null ? "No defence" : bossDefenceType));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + "; damage: "
                    + heroesDamage[i]);
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefenceType = heroesAttackType[randomIndex];
        //random.nextBoolean();

    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void bossHits() {

        for (int i = 0; i < heroesHealth.length; i++) {
            int bossDamagePower;

            if (heroesHealth[4] > 0){
                bossDamagePower = bossDamage - bossDamage/5;
            }else {
                bossDamagePower = bossDamage;
            }

            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamagePower < 0) {
                    heroesHealth[4] = heroesHealth[4] - bossDamage/5;
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamagePower;
                    heroesHealth[4] = heroesHealth[4] - bossDamage/5;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int hit = heroesDamage[i];
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int coeff = random.nextInt(6) + 2; // 2,3,4,5,6,7
                    hit = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + hit);
                }
                if (bossHealth - hit < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - hit;
                }

            }
        }
    }


    public static void medicTreat(){
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesAttackType[i] != "Medic"){
                heroesHealth[i] = heroesHealth[i] + 25;
            }
        }
    }
}

