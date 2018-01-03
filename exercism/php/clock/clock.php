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
		$this->hours = $this->reduceHours( $hours + $minutes / 60 );
		$this->minutes = $this->reduceMinutes( $minutes );
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
		return new Clock( $this->reduceHours($hours), $this->reduceMinutes( $total_min ) );
	}

	/**
	 * @param $minutes
	 *
	 * @return int
	 */
	public function reduceMinutes( $minutes ): int {
		return ( $minutes % 60 + 60 ) % 60;
	}

	/**
	 * @param $hours
	 *
	 * @return int
	 */
	public function reduceHours( $hours ): int {
		while ($hours < 0)
			$hours += 24;
		return $hours     % 24;
	}

}