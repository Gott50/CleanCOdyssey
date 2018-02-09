<?php

class SpaceAge {
	private $seconds;
	private $planets = [
		"mercury" => 0.2408467,
		"venus"   => 0.61519726,
		"mars"    => 1.8808158,
		"jupiter" => 11.862615,
		"saturn"  => 29.447498,
		"uranus"  => 84.016846,
		"neptune" => 164.79132,
	];

	public function __construct( $seconds ) {
		$this->seconds = $seconds;
	}

	public function mercury() {
		return $this->calculateYears( $this->planets["mercury"] );
	}

	/**
	 * @param $quotient
	 *
	 * @return float|int
	 */
	public function calculateYears( $quotient ) {
		return $this->earth() / $quotient;
	}

	public function earth() {
		return $this->seconds() / 31557600;
	}

	public function seconds() {
		return $this->seconds;
	}

	public function venus() {
		return $this->calculateYears( $this->planets["venus"] );
	}

	public function mars() {
		return $this->calculateYears( $this->planets["mars"] );
	}

	public function jupiter() {
		return $this->calculateYears( $this->planets["jupiter"] );
	}

	public function saturn() {
		return $this->calculateYears( $this->planets["saturn"] );
	}

	public function uranus() {
		return $this->calculateYears( $this->planets["uranus"] );
	}

	public function neptune() {
		return $this->calculateYears( $this->planets["neptune"] );
	}
}