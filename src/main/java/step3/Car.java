package step3;

import java.util.Objects;

public class Car {
    private static final int MOVE_CRITERION = 4;

    private int moveDistance;
    private String carName;

    public Car(String carName) {
        this.moveDistance = 0;
        validateCarName(carName);
        this.carName = carName;
    }

    public Car(String carName, int moveDistance) {
        this.moveDistance = moveDistance;
        validateCarName(carName);
        this.carName = carName;
    }

    private void validateCarName(String carName) {
        if (!CarUtils.validateNameLength(carName)) {
            throw new IllegalArgumentException("자동차 이름은 다섯 글자를 초과할 수 없습니다.");
        }
    }

    public int getMoveDistance() {
        return moveDistance;
    }

    public String getCarName() {
        return carName;
    }

    public void moveForward(int number) {
        if (checkProgressPossible(number)) {
            addMoveDistance();
        }
    }

    private boolean checkProgressPossible(int number) {
        return number >= MOVE_CRITERION;
    }

    private void addMoveDistance() {
        this.moveDistance += 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return moveDistance == car.moveDistance && carName.equals(car.carName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moveDistance, carName);
    }

    @Override
    public String toString() {
        return "Car{" +
                "moveDistance=" + moveDistance +
                ", carName='" + carName + '\'' +
                '}';
    }
}