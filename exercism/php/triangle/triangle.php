<?php

class Triangle {
	/**
	 * @var array
	 */
	private $lengths;

	public function __construct( ...$lengths ) {
		if ( ! $this->isLegal( $lengths ) ) {
			throw new InvalidArgumentException();
		}

		$this->lengths = $lengths;
	}

	/**
	 * @param $lengths
	 *
	 * @return bool
	 */
	public function isLegal( $lengths ): bool {
		return $this->areLengthsPositive( $lengths );
	}

	/**
	 * @param $lengths
	 *
	 * @return bool
	 */
	public function areLengthsPositive( $lengths ): bool {
		return sizeof( array_filter( $lengths, function ( $e ) {
				return $e > 0;
			} ) ) == 3;
	}

	/**
	 * @return string
	 */
	public function kind() {
		switch ( sizeof( array_unique( $this->lengths ) ) ) {
			case 1:
				return "equilateral";
			case 2:
				return "isosceles";
			case 3:
				return "scalene";
		}
		throw new InvalidArgumentException();
	}
}