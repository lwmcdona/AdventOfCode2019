package day12;

public class Moon {
    private int pX;
    private int pY;
    private int pZ;

    private int startX;
    private int startY;
    private int startZ;

    private int vX;
    private int vY;
    private int vZ;

    public Moon(int x, int y, int z) {
        this.pX = x;
        this.pY = y;
        this.pZ = z;

        this.startX = x;
        this.startY = y;
        this.startZ = z;

        resetVelocity();
    }

    public int getX() {
        return pX;
    }

    public int getY() {
        return pY;
    }

    public int getZ() {
        return pZ;
    }

    public int getVX() {
        return vX;
    }

    public int getVY() {
        return vY;
    }

    public int getVZ() {
        return vZ;
    }

    public void applyVelocity() {
        applyVelocityX();
        applyVelocityY();
        applyVelocityZ();
    }

    public void applyVelocityX() {
        pX += vX;
    }

    public void applyVelocityY() {
        pY += vY;
    }

    public void applyVelocityZ() {
        pZ += vZ;
    }

    public void resetVelocity() {
        vX = 0;
        vY = 0;
        vZ = 0;
    }

    public boolean isCompleteOrbitX() {
        return pX == startX && vX == 0;
    }

    public boolean isCompleteOrbitY() {
        return pY == startY && vY == 0;
    }

    public boolean isCompleteOrbitZ() {
        return pZ == startZ && vZ == 0;
    }

    public void incrementVX() {
        vX += 1;
    }

    public void decrementVX() {
        vX -= 1;
    }

    public void incrementVY() {
        vY += 1;
    }

    public void decrementVY() {
        vY -= 1;
    }

    public void incrementVZ() {
        vZ += 1;
    }

    public void decrementVZ() {
        vZ -= 1;
    }

    public int kineticEnergy() {
        return Math.abs(vX) + Math.abs(vY) + Math.abs(vZ);
    }

    public int potentialEnergy() {
        return Math.abs(pX) + Math.abs(pY) + Math.abs(pZ);
    }

    public int totalEnergy() {
        return potentialEnergy() * kineticEnergy();
    }

    static void applyGravity(Moon moon1, Moon moon2) {
        applyGravityX(moon1, moon2);
        applyGravityY(moon1, moon2);
        applyGravityZ(moon1, moon2);
    }

    static void applyGravityX(Moon moon1, Moon moon2) {
        if (moon1.getX() > moon2.getX()) {
            moon1.decrementVX();
            moon2.incrementVX();
        } else if (moon1.getX() < moon2.getX()) {
            moon1.incrementVX();
            moon2.decrementVX();
        }
    }

    static void applyGravityY(Moon moon1, Moon moon2) {
        if (moon1.getY() > moon2.getY()) {
            moon1.decrementVY();
            moon2.incrementVY();
        } else if (moon1.getY() < moon2.getY()) {
            moon1.incrementVY();
            moon2.decrementVY();
        }
    }

    static void applyGravityZ(Moon moon1, Moon moon2) {
        if (moon1.getZ() > moon2.getZ()) {
            moon1.decrementVZ();
            moon2.incrementVZ();
        } else if (moon1.getZ() < moon2.getZ()) {
            moon1.incrementVZ();
            moon2.decrementVZ();
        }
    }
}
