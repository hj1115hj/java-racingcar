package car_racing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test_util.NumberGeneratorByInputNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CarRacingTest {

    @BeforeEach
    void tear() {
        clearAllCaches();
    }

    @Test
    @DisplayName("cars를 전달받아 carRacing이 생성되어야 한다.")
    void carRacing_초기화() {
        // when
        NumberGeneratorByInputNumber testNumberGenerator = new NumberGeneratorByInputNumber(0);
        CarRacing carRacing = new CarRacing(CarFactory.generateCars(new ArrayList<>(Arrays.asList("pobi", "crong", "honux")), testNumberGenerator));

        // then
        assertThat(carRacing.getCars()).hasSize(3);
    }

    @Test
    @DisplayName("cars 사이즈에 따라 car를 움직여야한다.")
    void cars_움직임() {
        // given
        List<Car> carMocks = Arrays.asList(mock(Car.class), mock(Car.class), mock(Car.class));
        CarRacing carRacing = new CarRacing(carMocks);
        carRacing.setCars(carMocks);

        // when
        carRacing.moveCars();

        // then
        verify(carMocks.get(0), times(1)).moveOrStop();
        verify(carMocks.get(1), times(1)).moveOrStop();
        verify(carMocks.get(2), times(1)).moveOrStop();
        verifyNoMoreInteractions(carMocks.toArray());
    }


    @Test
    @DisplayName("우승한 차들을 출력한다")
    void carRacing_우승_로직() {
        // Given
        List<Car> cars = new ArrayList<>(
                Arrays.asList(
                        new Car("pobi", new NumberGeneratorByInputNumber(3)),
                        new Car("crong", new NumberGeneratorByInputNumber(5)),
                        new Car("honux", new NumberGeneratorByInputNumber(6))
                )
        );
        CarRacing carRacing = new CarRacing(cars);

        // When
        carRacing.moveCars();
        List<Car> winCars = Winners.getWinner(cars);

        // Then
        assertThat(winCars).hasSize(2);
        assertThat(winCars.get(0).getName()).isEqualTo(new Name("crong"));
        assertThat(winCars.get(1).getName()).isEqualTo(new Name("honux"));
    }

}
