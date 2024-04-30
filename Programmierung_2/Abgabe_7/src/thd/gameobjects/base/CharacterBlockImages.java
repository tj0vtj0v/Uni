package thd.gameobjects.base;

/**
 * Class with all Blockimages stored inside.
 */
public final class CharacterBlockImages {
    /**
     * Static Subclass for the main character.
     */
    public static final class Main {
        /**
         * String with the 1st frame of the main character running down.
         */
        public static final String DOWN_1 = """
                   555555   \s
                  55555555  \s
                  56611665  \s
                  51111115  \s
                   116611   \s
                 5555555588 \s
                 5555555588 \s
                555555588555\s
                555665588555\s
                555668855555\s
                588668855555\s
                 8866555555 \s
                 5566555555 \s
                 5566555555 \s
                 5555  5555 \s
                 5555  5555 \s
                 5555  5555 \s
                 5555  5555 \s
                 5555  5555 \s
                 6666  6666 \s
                """;
    }

    /**
     * Static Subclass for the enemy character.
     */
    public static final class Enemy {
        public static final class Gunner {
            public static final String DOWN_1 = Main.DOWN_1.replace('5', '2');
        }
        /**
         * Static Subclass for the enemy with a mortar.
         */
        public static final class Mortar {
            /**
             * String with the normal frame of the enemy with a mortar.
             */
            public static final String NORMAL = """
                             2222     \s
                           22222222   \s
                           26611662   \s
                           21111112   \s
                            116611    \s
                          2222222222  \s
                         2222222222222\s
                         2222222222222\s
                         2222222222222\s
                         2222222211222\s
                          2222222112  \s
                           222222222  \s
                           222222222  \s
                           222222222  \s
                           222   222  \s
                           222   222  \s
                           222   222  \s
                           222   222  \s
                           666   666  \s
                    """;

            /**
             * String with the loading frame of the enemy with a mortar.
             */
            public static final String LOADING = """
                             2222     \s
                           22222222   \s
                           26611662   \s
                    112    21111112   \s
                    66222   116611    \s
                    6622222222222222  \s
                        22222222222222\s
                          222222222222\s
                          222222222222\s
                          222222211222\s
                           222222112  \s
                           222222222  \s
                           222222222  \s
                           222222222  \s
                           222   222  \s
                           222   222  \s
                           222   222  \s
                           222   222  \s
                           666   666  \s
                    """;
        }
    }
}
