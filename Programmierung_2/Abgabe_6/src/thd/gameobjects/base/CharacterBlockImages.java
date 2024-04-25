package thd.gameobjects.base;

/**
 * Class with all Blockimages stored inside.
 */
public class CharacterBlockImages {
    /**
     * Static Subclass for the main character.
     */
    public static class Main {
        /**
         * String with the 1st frame of the main character running down.
         */
        public static final String DOWN_1 = """
                   555555
                  55555555
                  56611665
                  51111115
                   116611
                 5555555588
                 5555555588
                555555588555
                555665588555
                555668855555
                588668855555
                 8866555555
                 5566555555
                 5566555555
                 5555  5555
                 5555  5555
                 5555  5555
                 5555  5555
                 5555  5555
                 6666  6666
                """;
    }

    /**
     * Static Subclass for the enemy character.
     */
    public static class Enemy {
        /**
         * Static Subclass for the enemy with a mortar.
         */
        public static class Mortar {
            /**
             * String with the normal frame of the enemy with a mortar.
             */
            public static final String NORMAL = """
                             2222
                           22222222
                           26611662
                           21111112
                            116611
                          2222222222
                         2222222222222
                         2222222222222
                         2222222222222
                         2222222211222
                          2222222112
                           222222222
                           222222222
                           222222222
                           222   222
                           222   222
                           222   222
                           222   222
                           666   666
                    """;

            /**
             * String with the loading frame of the enemy with a mortar.
             */
            public static final String LOADING = """
                             2222
                           22222222
                           26611662
                    112    21111112
                    66222   116611
                    6622222222222222
                        22222222222222
                          222222222222
                          222222222222
                          222222211222
                           222222112
                           222222222
                           222222222
                           222222222
                           222   222
                           222   222
                           222   222
                           222   222
                           666   666
                    """;
        }
    }
}
