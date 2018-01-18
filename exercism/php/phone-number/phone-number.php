<?php

class PhoneNumber {
	private $number;

	/**
	 * PhoneNumber constructor.
	 *
	 * @param $number
	 */
	public function __construct( $number ) {
		if ( ! $this->isValid( $number ) ) {
			throw new InvalidArgumentException();
		}
		$this->number = preg_filter( "/\D/", "", $number );
	}

	/**
	 * @param $number
	 *
	 * @return false|int
	 */
	public function isValid( $number ) {
		return ! preg_match( "/[a-z]/", $number ) && strlen( $number ) > 9;
	}

	/**
	 * @return int
	 */
	public function number() {
		return $this->number;
	}
}