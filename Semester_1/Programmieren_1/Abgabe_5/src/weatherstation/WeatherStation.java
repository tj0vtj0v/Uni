package weatherstation;

public class WeatherStation {
    public static void main(String[] args) {
        // @formatter:off
        int[][] dailyMeanTemperatures = {
                { -3, 2, 6, 3, -1, 0, 4, 9, 9, 13, 5, 7, 11, 8, 10, 7, 4, 1, 1, 1, 1, 1, 1, 1, 2, 1, 3, 4, 4, 3, 3 },
                { 2, 1, 0, -1, -1, 0, -2, -2, 0, 2, 0, -1, 1, 6, 1, 1, 1, 2, 2, 7, 5, 3, 4, 5, 5, 5, 5, 4 },
                { 8, 9, 8, 5, 4, 4, 5, 7, 8, 9, 8, 6, 5, 4, 4, 6, 9, 10, 10, 8, 8, 7, 9, 9, 8, 6, 8, 8, 11, 11, 12 },
                { 7, 6, 6, 6, 5, 5, 6, 8, 12, 15, 14, 15, 14, 15, 17, 19, 13, 10, 12, 13, 14, 14, 12, 14, 14, 16, 15, 9, 10, 13 },
                { 10, 12, 16, 19, 18, 16, 15, 16, 17, 17, 19, 22, 20, 18, 10, 14, 16, 17, 15, 11, 12, 13, 15, 16, 16, 13, 12, 15, 18, 16, 17 },
                { 18, 21, 23, 21, 25, 24, 23, 16, 16, 17, 21, 21, 21, 21, 18, 17, 17, 16, 16, 14, 16, 18, 15, 16, 18, 20, 21, 21, 22, 24 },
                { 26, 28, 29, 29, 30, 26, 27, 22, 20, 19, 24, 23, 22, 24, 24, 26, 29, 25, 24, 26, 27, 25, 22, 24, 21, 17, 19, 19, 16, 17, 17 },
                { 18, 21, 24, 21, 22, 27, 29, 27, 23, 22, 24, 25, 25, 21, 19, 16, 17, 16, 17, 18, 19, 20, 18, 17, 18, 19, 24, 25, 23, 26, 26 },
                { 22, 17, 17, 16, 14, 13, 13, 15, 16, 15, 15, 19, 18, 16, 15, 17, 16, 16, 14, 14, 14, 15, 12, 14, 13, 15, 14, 12, 14, 12 },
                { 10, 11, 16, 14, 15, 18, 16, 12, 12, 12, 12, 9, 8, 7, 5, 7, 6, 8, 8, 6, 7, 10, 12, 12, 15, 8, 8, 10, 12, 8, 9 },
                { 7, 7, 6, 10, 12, 16, 18, 16, 17, 13, 9, 8, 11, 11, 14, 15, 14, 15, 15, 12, 4, 3, 2, 0, 4, 4, -1, 3, 8, 10 },
                { 11, 8, 2, 9, 7, 11, 10, 7, 8, 1, 9, 9, 6, 3, 8, 12, 13, 12, 8, 10, 11, 12, 12, 11, 12, 11, 9, 4, 7, 4, 7 } };
        // @formatter:on

        // for the first iteration
        // assign year variables
        int temperatureSumYear = 0;
        int daysInYear = 0;
        int warmestDay = dailyMeanTemperatures[0][0];
        String warmestDate = "";

        // assign month variables
        int temperatureSumMonth;
        int daysInMonth;
        float[] temperatureAvgMonth = new float[12];
        int monthOfYear = 1;

        for (int[] month : dailyMeanTemperatures) {
            temperatureSumMonth = 0;
            daysInMonth = 0;

            for (int day : month) {
                temperatureSumYear += day;
                temperatureSumMonth += day;
                daysInYear++;
                daysInMonth++;

                if (day > warmestDay) {
                    warmestDay = day;
                    warmestDate = daysInMonth + "." + monthOfYear + ".2015";
                }
            }

            temperatureAvgMonth[monthOfYear - 1] = Math.round(((double) temperatureSumMonth / daysInMonth) * 10) / 10f;

            monthOfYear++;
        }

        // calculate years avg temp
        float temperatureAvgYear = Math.round(((double) temperatureSumYear / daysInYear) * 10) / 10f;


        // for the second iteration
        int[] temperatures = new int[daysInYear];
        daysInYear = 0;

        for (int[] month : dailyMeanTemperatures) {
            for (int day : month) {
                temperatures[daysInYear] = day;
                daysInYear++;
            }
        }

        int[] hundredthTemperatures = {temperatures[99], temperatures[199], temperatures[299]};

        // selection sort
        int[] sorted = new int[temperatures.length];
        int maxTemp;
        int maxTempIndex;
        for (int dayInSorted = 0; dayInSorted < sorted.length; dayInSorted++) {
            maxTemp = temperatures[0];
            maxTempIndex = 0;
            for (int dayInTemperatures = 1; dayInTemperatures < temperatures.length; dayInTemperatures++) {
                if (temperatures[dayInTemperatures] > maxTemp) {
                    maxTemp = temperatures[dayInTemperatures];
                    maxTempIndex = dayInTemperatures;
                }
            }
            sorted[dayInSorted] = maxTemp;
            temperatures[maxTempIndex] = -100;
        }


        // issue prompt
        System.out.println("Die Anzahl der Tage im gegebenen Jahr ist: " + daysInYear);
        System.out.println("Jahresdurchschnittstemperatur: " + temperatureAvgYear + " Grad");
        System.out.println("Monatsdurchschnittstemperaturen:");

        int monthCounter = 1;
        for (float monthAvg : temperatureAvgMonth) {
            System.out.println("* Monat " + monthCounter + ": " + monthAvg + " Grad");
            monthCounter++;
        }
        System.out.println("Wärmster Tag am " + warmestDate + " mit " + warmestDay + " Grad. ");
        System.out.print("Temperaturen an jedem hundertsten Tag:\n*");

        for (int hundredth = 1; hundredth <= 3; hundredth++) {
            System.out.print(" Tag " + (hundredth * 100) + ": " + hundredthTemperatures[hundredth - 1] + " Grad");
            if (hundredth != 3) {
                System.out.print(",");
            }
        }
        System.out.print("\nDie fünf wärmsten Tage:\n* ");

        for (int warmDay = 0; warmDay < 5; warmDay++) {
            System.out.print(sorted[warmDay] + " Grad");
            if (warmDay != 4) {
                System.out.print(", ");
            }
        }
    }
}
