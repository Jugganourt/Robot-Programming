package rp13.search.problem.words;

public class Jumble {
	
	
	String str[] = new String[100];
	

}


/**


	public enum PuzzleMove {
		UP(-3), DOWN(3), LEFT(-1), RIGHT(1);

		private final int m_move;

		private PuzzleMove(int _move) {
			m_move = _move;
		}

		/**
		 * Cached result of values such that copy isn't done every time.
		 */
		private static final PuzzleMove[] VALUES = values();

		/***
		 * Count of values in list
		 */
		private static final int SIZE = VALUES.length;

		/**
		 * Random number generator
		 */
		private static final Random RANDOM = new Random();

		/**
		 * Returns a move selected at random.
		 * 
		 * @return
		 */
		public static PuzzleMove randomMove() {
			return VALUES[RANDOM.nextInt(SIZE)];
		}

	}

	/**
	 * The pieces in the puzzle, represented as an array.
	 */
	protected final int[] m_board;

	/**
	 * The value that represents the blank.
	 */
	protected final static int BLANK = 0;

	/**
	 * Width of the board
	 */
	protected final static int WIDTH = 3;

	/**
	 * Where the blank is currently located in the array
	 */
	private int m_blankPosition;

	/**
	 * Create an eight puzzle in its default configuration
	 */
	private EightPuzzle() {
		m_board = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, BLANK };
		m_blankPosition = m_board.length - 1;
	}

	/**
	 * Create a new eight puzzle by copying the given puzzle
	 * 
	 * @param _that
	 */
	public EightPuzzle(EightPuzzle _that) {
		m_board = Arrays.copyOf(_that.m_board, _that.m_board.length);
		m_blankPosition = _that.m_blankPosition;
	}

	/**
	 * Slide the blank tile randomly once.
	 */
	public void randomMove() {

		boolean moveMade = false;
		while (!moveMade) {
			PuzzleMove move = PuzzleMove.randomMove();
			moveMade = makeMove(move);
		}
	}

*/