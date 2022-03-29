package cm;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BennettPierceTestTask3 {

	private static final IllegalArgumentException ILLEGAL_ARGUMENT_EXCEPTION = new IllegalArgumentException();

	static int numberPeriodTests = 21;
	static int[] periodTestStartHours = { -6000, -1, 0, 1, 12, 13, 24, 25, 5, 5, 0, 1, 12, 18, 18, 18, 0, 0, 24, 13,
			0 };
	static int[] periodTestEndHours = { 5, 5, 5, 7, 17, 20, 24, 5, -6000, -1, 0, 3, 17, 23, 24, 25, 1, 0, 24, 12,
			24 };
	static Object[] periodTestExpectedResults = { ILLEGAL_ARGUMENT_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION, 5, 6, 5,
			7, ILLEGAL_ARGUMENT_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION,
			ILLEGAL_ARGUMENT_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION, 2, 5, 5, 6, ILLEGAL_ARGUMENT_EXCEPTION,
			1, ILLEGAL_ARGUMENT_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION, 24 };

	static CarParkKind[] kinds = { CarParkKind.STAFF, CarParkKind.STUDENT, CarParkKind.MANAGEMENT,
			CarParkKind.VISITOR };

	static ArrayList<Period> reducedPeriods;
	static ArrayList<Period> normalPeriods;

	/*
	 * @Test
	 * void periodTest() {
	 * for (int i = 0; i < numberPeriodTests; i++) {
	 * int index = i;
	 * Object result = periodTestExpectedResults[index];
	 * try {
	 * Period p = new Period(periodTestStartHours[index],
	 * periodTestEndHours[index]);
	 * Assertions.assertEquals(result, p.duration());
	 * } catch (IllegalArgumentException error) {
	 * Assertions.assertThrows(IllegalArgumentException.class, () -> {
	 * new Period(periodTestStartHours[index], periodTestEndHours[index]);
	 * }, "IllegalArgumentException was excepted.");
	 * }
	 * }
	 * }
	 */

	// 1 STAFF -5000 1 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)]
	// hourlyNormalRate >= 0 (2, 8) IllegalArgument Exception
	@Test
	@DisplayName("hourlyNormalRate >= 0 | hourlyNormalRate = -5000")
	void test1() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(-5000);
		BigDecimal hourlyReducedRate = new BigDecimal(1);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.STAFF, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("A rate cannot be negative", thrown.getMessage());
	}

	// 2 STUDENT -1 1 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)]
	// hourlyNormalRate >= 0 (0, 12) IllegalArgument Exception
	@Test
	@DisplayName("hourlyNormalRate >= 0 | hourlyNormalRate = -1")
	void test2() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(-1);
		BigDecimal hourlyReducedRate = new BigDecimal(1);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.STUDENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("A rate cannot be negative", thrown.getMessage());
	}

	// MANAGEMENT 0 1 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)]
	// hourlyNormalRate >= 0 (6, 10) IllegalArgument
	// not hitting correct boundary could be moved or removed or modified.
	@Test
	@DisplayName("hourlyNormalRate >= 0 | hourlyNormalRate = 0")
	void test3() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(0);
		BigDecimal hourlyReducedRate = new BigDecimal(1);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.MANAGEMENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("The normal rate cannot be less or equal to the reduced rate", thrown.getMessage());
	}

	// VISITOR 7 1 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)] hourlyNormalRate
	// >= 0 (1, 19) 7
	@Test
	@DisplayName("hourlyNormalRate >= 0 | hourlyNormalRate = 7")
	void test4() {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(7);
		BigDecimal hourlyReducedRate = new BigDecimal(1);

		Rate rate = new Rate(CarParkKind.VISITOR, hourlyNormalRate, hourlyReducedRate, reducedPeriods,
				normalPeriods);

		Period p = new Period(1, 19);

		BigDecimal answer = new BigDecimal(7);
		Assertions.assertEquals(answer, rate.calculate(p));

	}

	// 5 STAFF 5000 1 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)]
	// hourlyNormalRate >= 0 (5, 12) 16
	@Test
	@DisplayName("hourlyNormalRate >= 0 | hourlyNormalRate = 5000")
	void test5() {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(5000);
		BigDecimal hourlyReducedRate = new BigDecimal(1);

		Rate rate = new Rate(CarParkKind.STAFF, hourlyNormalRate, hourlyReducedRate, reducedPeriods,
				normalPeriods);

		Period p = new Period(5, 12);

		BigDecimal answer = new BigDecimal(16);
		Assertions.assertEquals(answer, rate.calculate(p));

	}

	// STUDENT 1 -5000 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)]
	// hourlyReducedRate >= 0 (2, 8) IllegalArgument Exception
	@Test
	@DisplayName("hourlyReducedRate >= 0 | hourlyReducedRate = -5000")
	void test6() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(1);
		BigDecimal hourlyReducedRate = new BigDecimal(-5000);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.STUDENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("A rate cannot be negative", thrown.getMessage());
	}

	// MANAGEMENT 1 -1 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)]
	// hourlyReducedRate >= 0 (0, 12) IllegalArgument Exception
	@Test
	@DisplayName("hourlyReducedRate >= 0 | hourlyReducedRate = -1")
	void test7() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(1);
		BigDecimal hourlyReducedRate = new BigDecimal(-1);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.MANAGEMENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("A rate cannot be negative", thrown.getMessage());
	}

	// VISITOR 1 0 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)]
	// hourlyReducedRate >= 0 (6, 10) 0
	@Test
	@DisplayName("hourlyReducedRate >= 0 | hourlyReducedRate = 0")
	void test8() {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(1);
		BigDecimal hourlyReducedRate = new BigDecimal(0);

		Rate rate = new Rate(CarParkKind.VISITOR, hourlyNormalRate, hourlyReducedRate, reducedPeriods,
				normalPeriods);

		Period p = new Period(6, 10);

		BigDecimal answer = new BigDecimal(0);
		Assertions.assertEquals(answer, rate.calculate(p));

	}

	// STAFF 1 7 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)] hourlyReducedRate
	// >= 0 (1, 19) IllegalArgument Exception
	// not hitting correct boundary could be moved or removed or modified.
	@Test
	@DisplayName("hourlyReducedRate >= 0 | hourlyReducedRate = 7")
	void test9() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(1);
		BigDecimal hourlyReducedRate = new BigDecimal(7);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.STAFF, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("The normal rate cannot be less or equal to the reduced rate", thrown.getMessage());

	}

	// STUDENT 1 5000 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)]
	// hourlyReducedRate >= 0 (5, 12) IllegalArgument Exception
	// not hitting correct boundary could be moved or removed or modified.
	@Test
	@DisplayName("hourlyReducedRate >= 0 | hourlyReducedRate = 5000")
	void test10() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(1);
		BigDecimal hourlyReducedRate = new BigDecimal(5000);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.STUDENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("The normal rate cannot be less or equal to the reduced rate", thrown.getMessage());

	}

	// MANAGEMENT 1 0 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)]
	// hourlyNormalRate > hourlyReducedRate (2, 8) 4
	@Test
	@DisplayName("hourlyNormalRate > hourlyReducedRate | hourlyNormalRate = 1, hourlyReducedRate = 0")
	void test11() {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(1);
		BigDecimal hourlyReducedRate = new BigDecimal(0);

		Rate rate = new Rate(CarParkKind.MANAGEMENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods,
				normalPeriods);

		Period p = new Period(2, 8);

		BigDecimal answer = new BigDecimal(4);
		Assertions.assertEquals(answer, rate.calculate(p));

	}

	// VISITOR 0 -1 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)]
	// hourlyNormalRate > hourlyReducedRate (0, 12) IllegalArgument Exception
	// not hitting correct boundary could be moved or removed or modified.
	@Test
	@DisplayName("hourlyNormalRate > hourlyReducedRate | hourlyNormalRate = 0, hourlyReducedRate = -1")
	void test12() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(0);
		BigDecimal hourlyReducedRate = new BigDecimal(-1);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.VISITOR, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("A rate cannot be negative", thrown.getMessage());

	}

	// STAFF 3000 2000 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)]
	// hourlyNormalRate > hourlyReducedRate (6, 10) 16
	@Test
	@DisplayName("hourlyNormalRate > hourlyReducedRate | hourlyNormalRate = 3000, hourlyReducedRate = 2000")
	void test13() {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(3000);
		BigDecimal hourlyReducedRate = new BigDecimal(2000);

		Rate rate = new Rate(CarParkKind.STAFF, hourlyNormalRate, hourlyReducedRate, reducedPeriods,
				normalPeriods);

		Period p = new Period(6, 10);

		BigDecimal answer = new BigDecimal(16);
		Assertions.assertEquals(answer, rate.calculate(p));

	}

	// STUDENT 2000 3000 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)]
	// hourlyNormalRate > hourlyReducedRate (1, 19) IllegalArgument Exception
	@Test
	@DisplayName("hourlyNormalRate > hourlyReducedRate | hourlyNormalRate = 2000, hourlyReducedRate = 3000")
	void test14() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(2000);
		BigDecimal hourlyReducedRate = new BigDecimal(3000);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.STUDENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("The normal rate cannot be less or equal to the reduced rate", thrown.getMessage());

	}

	// MANAGEMENT 10 8 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)]
	// hourlyNormalRate > hourlyReducedRate (5, 12) 34
	@Test
	@DisplayName("hourlyNormalRate > hourlyReducedRate | hourlyNormalRate = 10, hourlyReducedRate = 8")
	void test15() {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(8);

		Rate rate = new Rate(CarParkKind.MANAGEMENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods,
				normalPeriods);

		Period p = new Period(5, 12);

		BigDecimal answer = new BigDecimal(34);
		Assertions.assertEquals(answer, rate.calculate(p));

	}

	// MANAGEMENT 0 0 [(4, 8)] [(12, 14)] hourlyNormalRate > hourlyReducedRate (1,
	// 19) IllegalArgument Exception
	@Test
	@DisplayName("hourlyNormalRate > hourlyReducedRate")
	void test16() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(4, 8));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(12, 14));

		BigDecimal hourlyNormalRate = new BigDecimal(0);
		BigDecimal hourlyReducedRate = new BigDecimal(0);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.STUDENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("The normal rate cannot be less or equal to the reduced rate", thrown.getMessage());

	}

	// VISITOR 5 5 [(4, 8)] [(12, 14)] hourlyNormalRate > hourlyReducedRate (5, 12)
	// IllegalArgument Exception
	@Test
	@DisplayName("hourlyNormalRate > hourlyReducedRate")
	void test17() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(4, 8));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(12, 14));

		BigDecimal hourlyNormalRate = new BigDecimal(5);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.STUDENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("The normal rate cannot be less or equal to the reduced rate", thrown.getMessage());

	}

	// VISITOR 10 5 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)] normal == !
	// overlapping (2, 8) 7.5
	@Test
	@DisplayName("normal == ! overlapping")
	void test18() {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Rate rate = new Rate(CarParkKind.VISITOR, hourlyNormalRate, hourlyReducedRate, reducedPeriods,
				normalPeriods);

		Period p = new Period(2, 8);

		BigDecimal answer = new BigDecimal(7.5);
		Assertions.assertEquals(answer, rate.calculate(p));

	}

	// STAFF 10 5 [(1, 4),(8, 9),(10, 11)] [(7, 8),(9, 10),(11, 12)] normal == !
	// overlapping (0, 12) 16
	@Test
	@DisplayName("normal == ! overlapping")
	void test19() {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 4));
		normalPeriods.add(new Period(8, 9));
		normalPeriods.add(new Period(10, 11));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Rate rate = new Rate(CarParkKind.STAFF, hourlyNormalRate, hourlyReducedRate, reducedPeriods,
				normalPeriods);

		Period p = new Period(0, 12);

		BigDecimal answer = new BigDecimal(16);
		Assertions.assertEquals(answer, rate.calculate(p));

	}

	// STUDENT 10 5 [] [(7, 8),(9, 10),(11, 12)] normal == ! overlapping (6, 10)
	// 8.875
	@Test
	@DisplayName("normal == ! overlapping")
	void test20() {

		normalPeriods = new ArrayList<Period>();

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Rate rate = new Rate(CarParkKind.STUDENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods,
				normalPeriods);

		Period p = new Period(6, 10);

		BigDecimal answer = new BigDecimal(8.875);
		Assertions.assertEquals(answer, rate.calculate(p));

	}

	// MANAGEMENT 10 5 [(1, 6), (2, 7), (9, 10)] [(7, 8),(9, 10),(11, 12)] normal ==
	// !
	// overlapping (1, 19) IllegalArgument Exception
	@Test
	@DisplayName("normal == !overlapping")
	void test21() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 6));
		normalPeriods.add(new Period(2, 7));
		normalPeriods.add(new Period(9, 10));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.MANAGEMENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("The periods are not valid individually", thrown.getMessage());

	}

	// VISITOR 10 5 [(3, 5), (1, 4)] [(7, 8),(9, 10),(11, 12)] normal == !
	// overlapping (5, 12) IllegalArgument Exception
	@Test
	@DisplayName("normal == !overlapping")
	void test22() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(3, 5));
		normalPeriods.add(new Period(1, 4));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.VISITOR, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("The periods are not valid individually", thrown.getMessage());

	}

	// STAFF 10 5 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)] reduced ==
	// !overlapping (2, 8) 16
	@Test
	@DisplayName("reduced == ! overlapping")
	void test23() {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Rate rate = new Rate(CarParkKind.STAFF, hourlyNormalRate, hourlyReducedRate, reducedPeriods,
				normalPeriods);

		Period p = new Period(2, 8);

		BigDecimal answer = new BigDecimal(16);
		Assertions.assertEquals(answer, rate.calculate(p));

	}

	// STUDENT 10 5 [(1, 2),(3, 4),(5, 6)] [(2, 3),(4, 5),(6, 7)] reduced == !
	// overlapping (0, 12) 35.125
	@Test
	@DisplayName("reduced == ! overlapping")
	void test24() {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Rate rate = new Rate(CarParkKind.STUDENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods,
				normalPeriods);

		Period p = new Period(0, 12);

		BigDecimal answer = new BigDecimal(35.125);
		Assertions.assertEquals(answer, rate.calculate(p));
	}

	// MANAGEMENT 10 5 [(1, 2),(3, 4),(5, 6)] [] reduced == ! overlapping (6, 10) 4
	@Test
	@DisplayName("reduced == ! overlapping")
	void test25() {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Rate rate = new Rate(CarParkKind.MANAGEMENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods,
				normalPeriods);

		Period p = new Period(6, 10);

		BigDecimal answer = new BigDecimal(4);
		Assertions.assertEquals(answer, rate.calculate(p));
	}

	// VISITOR 10 5 [(1, 2),(3, 4),(5, 6)] [(8, 15), (10, 20)] reduced == !
	// overlapping (1, 19) IllegalArgument Exception
	@Test
	@DisplayName("reduced == !overlapping")
	void test26() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(8, 15));
		reducedPeriods.add(new Period(10, 20));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.VISITOR, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("The periods are not valid individually", thrown.getMessage());

	}

	// STAFF 10 5 [(1, 2),(3, 4),(5, 6)] [(10, 20), (8, 15), (21, 23)] reduced == !
	// overlapping (5, 12) IllegalArgument Exception
	@Test
	@DisplayName("reduced == !overlapping")
	void test27() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(10, 20));
		reducedPeriods.add(new Period(8, 15));
		reducedPeriods.add(new Period(21, 23));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.STAFF, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("The periods are not valid individually", thrown.getMessage());

	}

	// STUDENT 10 5 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)]
	// normal.notOverlapping(reduced) (2, 8) 20.125
	@Test
	@DisplayName("normal.notOverlapping(reduced)")
	void test28() {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Rate rate = new Rate(CarParkKind.STUDENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods,
				normalPeriods);

		Period p = new Period(2, 8);

		BigDecimal answer = new BigDecimal(20.125);
		Assertions.assertEquals(answer, rate.calculate(p));
	}

	// MANAGEMENT 10 5 [(1, 2),(3, 4),(5, 6)] [(2, 3),(4, 5),(6, 7)]
	// normal.notOverlapping(reduced) (0, 12) 45
	@Test
	@DisplayName("normal.notOverlapping(reduced)")
	void test29() {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(2, 3));
		reducedPeriods.add(new Period(4, 5));
		reducedPeriods.add(new Period(6, 7));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Rate rate = new Rate(CarParkKind.MANAGEMENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods,
				normalPeriods);

		Period p = new Period(0, 12);

		BigDecimal answer = new BigDecimal(45);
		Assertions.assertEquals(answer, rate.calculate(p));
	}

	// VISITOR 10 5 [] [] normal.notOverlapping(reduced) (6, 10) 0
	@Test
	@DisplayName("normal.notOverlapping(reduced)")
	void test30() {

		normalPeriods = new ArrayList<Period>();

		reducedPeriods = new ArrayList<Period>();

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Rate rate = new Rate(CarParkKind.VISITOR, hourlyNormalRate, hourlyReducedRate, reducedPeriods,
				normalPeriods);

		Period p = new Period(6, 10);

		BigDecimal answer = new BigDecimal(0);
		Assertions.assertEquals(answer, rate.calculate(p));
	}

	// STAFF 10 5 [(1, 10), (12, 18)] [(5, 10), (16, 18)]
	// normal.notOverlapping(reduced) (1, 19)
	// IllegalArgument Exception
	@Test
	@DisplayName("normal.notOverlapping(reduced)")
	void test31() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 10));
		normalPeriods.add(new Period(12, 14));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(5, 10));
		reducedPeriods.add(new Period(16, 18));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.STAFF, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("The periods overlaps", thrown.getMessage());

	}

	// STUDENT 10 5 [(5, 10), (12, 14)] [(1, 10), (16, 18)]
	// normal.notOverlapping(reduced) (5, 12)
	// IllegalArgument Exception
	@Test
	@DisplayName("normal.notOverlapping(reduced)")
	void test32() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(5, 10));
		normalPeriods.add(new Period(12, 14));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(1, 10));
		reducedPeriods.add(new Period(16, 18));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.STUDENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("The periods overlaps", thrown.getMessage());

	}

	// MANAGEMENT 10 5 [(1, 2),(3, 4),(5, 6)] [(8, 16), (10, 12)] reduced == !
	// overlapping (1, 19) IllegalArgument Exception
	@Test
	@DisplayName("reduced == ! overlapping")
	void test33() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(8, 16));
		reducedPeriods.add(new Period(10, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.MANAGEMENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("The periods are not valid individually", thrown.getMessage());

	}

	// VISITOR 10 5 [(1, 2),(3, 4),(5, 6)] [(10, 12), (8, 16)] reduced == !
	// overlapping (1, 19) IllegalArgument Exception
	// test failing - bug in code
	@Test
	@DisplayName("reduced == ! overlapping")
	void test34() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(10, 12));
		reducedPeriods.add(new Period(8, 16));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.VISITOR, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("The periods are not valid individually", thrown.getMessage());

	}

	// STAFF 10 5 [(1, 16), (2, 7] [(7, 8),(9, 10),(11, 12)] normal == ! overlapping
	// (1, 19) IllegalArgument Exception
	@Test
	@DisplayName("normal == ! overlapping")
	void test35() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 16));
		normalPeriods.add(new Period(2, 7));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.STAFF, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("The periods are not valid individually", thrown.getMessage());

	}

	// STUDENT 10 5 [(4, 12), (2, 18)] [(7, 8),(9, 10),(11, 12)] normal == !
	// overlapping (1, 19) IllegalArgument Exception
	@Test
	@DisplayName("normal == ! overlapping")
	void test36() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(4, 12));
		normalPeriods.add(new Period(2, 18));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.STUDENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("The periods are not valid individually", thrown.getMessage());

	}

	// MANAGEMENT 10 NULL [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)]
	// hourlyReducedRate != NULL (5, 12) IllegalArgument Exception
	@Test
	@DisplayName("hourlyReducedRate != NULL")
	void test37() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = null;

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.MANAGEMENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("The rates cannot be null", thrown.getMessage());

	}

	// VISITOR NULL 10 [(1, 2),(3, 4),(5, 6)] [(7, 8),(9, 10),(11, 12)]
	// hourlyNormalRate != NULL (2, 8) IllegalArgument Exception
	@Test
	@DisplayName("hourlyNormalRate != NULL")
	void test38() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(7, 8));
		reducedPeriods.add(new Period(9, 10));
		reducedPeriods.add(new Period(11, 12));

		BigDecimal hourlyNormalRate = null;
		BigDecimal hourlyReducedRate = new BigDecimal(10);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.VISITOR, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("The rates cannot be null", thrown.getMessage());

	}

	// STAFF 10 5 NULL [(2, 3),(4, 5),(6, 7)] normal != NULL (2, 12) IllegalArgument
	// Exception
	@Test
	@DisplayName("normal != NULL")
	void test39() throws IllegalArgumentException {

		normalPeriods = null;

		reducedPeriods = new ArrayList<Period>();
		reducedPeriods.add(new Period(2, 3));
		reducedPeriods.add(new Period(4, 5));
		reducedPeriods.add(new Period(6, 7));

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.STAFF, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("periods cannot be null", thrown.getMessage());

	}

	// STUDENT 10 5 [(1, 2),(3, 4),(5, 6)] NULL reduced != NULL (6, 10)
	// IllegalArgument Exception
	@Test
	@DisplayName("reduced != NULL")
	void test40() throws IllegalArgumentException {

		normalPeriods = new ArrayList<Period>();
		normalPeriods.add(new Period(1, 2));
		normalPeriods.add(new Period(3, 4));
		normalPeriods.add(new Period(5, 6));

		reducedPeriods = null;

		BigDecimal hourlyNormalRate = new BigDecimal(10);
		BigDecimal hourlyReducedRate = new BigDecimal(5);

		Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rate(CarParkKind.STUDENT, hourlyNormalRate, hourlyReducedRate, reducedPeriods, normalPeriods);
		}, "Creating Rate with invalid inputs throws an IllegalArgumentException");

		Assertions.assertEquals("periods cannot be null", thrown.getMessage());

	}
}
