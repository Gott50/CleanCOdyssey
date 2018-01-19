<?php

class PhoneNumber {
	private $number;

	/**
	 * PhoneNumber constructor.
	 *
	 * @param $number
	 */
	public function __construct( $number ) {
		if ( ! ( ! preg_match( "/[a-z]/", $number ) ) ) {
			throw new InvalidArgumentException();
		}
		$number = preg_filter( "/\D/", "", $number . " " );
		if ( ! ( strlen( $number ) > 9
		         && ( strlen( $number ) < 11 || ( $number[0] == 1 ) && strlen( $number ) < 12 ) ) ) {
			throw new InvalidArgumentException();
		}
		$this->number = substr( $number, strlen( $number ) - 10, 10 );
	}

	/**
	 * @return int
	 */
	public function number() {
		return $this->number;
	}

	public function prettyPrint() {
		return "(" . $this->areaCode() . ")" . " " . substr( $this->number, 3, 3 )
		       . "-" . substr( $this->number, 6, 4 );
	}

	public function areaCode() {
		return substr( $this->number, 0, 3 );
	}
}