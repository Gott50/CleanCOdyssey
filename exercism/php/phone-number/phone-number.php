<?php

class PhoneNumber {
	private $number;

	/**
	 * PhoneNumber constructor.
	 *
	 * @param $number
	 */
	public function __construct( $number ) {
		if ( $this->containsLetters( $number ) ) {
			throw new InvalidArgumentException();
		}
		$number = preg_filter( "/\D/", "", $number . " " );
		if ( ! $this->isValidNumber( $number ) ) {
			throw new InvalidArgumentException();
		}
		$this->number = substr( $number, strlen( $number ) - 10, 10 );
	}

	/**
	 * @param $number
	 *
	 * @return bool
	 */
	public function containsLetters( $number ): bool {
		return preg_match( "/[a-z]/", $number );
	}

	/**
	 * @param $number
	 *
	 * @return bool
	 */
	public function isValidNumber( $number ): bool {
		return strlen( $number ) > 9 &&
		       ( strlen( $number ) < 11 || $number[0] == 1 && strlen( $number ) < 12 );
	}

	/**
	 * @return int
	 */
	public function number() {
		return $this->number;
	}

	/**
	 * @return string
	 */
	public function prettyPrint() {
		return "(" . $this->areaCode() . ")" . " " . substr( $this->number, 3, 3 )
		       . "-" . substr( $this->number, 6, 4 );
	}

	/**
	 * @return string
	 */
	public function areaCode() {
		return substr( $this->number, 0, 3 );
	}
}