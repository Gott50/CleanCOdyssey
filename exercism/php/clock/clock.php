<?php

class Clock {
	private $hours, $minutes;

	/**
	 * Clock constructor.
	 *
	 * @param int $hours
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

	public function sub( $int ) {
		return $this->add( - $int );
	}

	public function add( $minutes ) {
		$total_min = $this->hours * 60 + $this->minutes+$minutes;
		$hours     = $total_min / 60;
		while ($hours < 0)
			$hours += 24;
		$hours     %= 24;
		$minutes   = ( $total_min % 60 + 60 ) % 60;

		return new Clock( $hours, $minutes );
	}

}