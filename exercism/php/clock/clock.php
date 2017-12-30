<?php

class Clock {
	private $hours, $minutes;

	/**
	 * Clock constructor.
	 *
	 * @param $hours
	 * @param int $minutes
	 */
	public function __construct( $hours, $minutes = 0 ) {
		$this->hours   = $hours;
		$this->minutes = $minutes;
	}

	/**
	 * @return string
	 */
	public function __toString() {
		return $this->format( $this->hours ) . ":" . $this->format( $this->minutes );
	}

	/**
	 * @param $time
	 *
	 * @return string
	 */
	public function format( $time ): string {
		return $time < 10 ? '0' . $time : $time;
	}

}