<?php

class SpaceAge {
	private $seconds;

	public function __construct($seconds) {
		$this->seconds = $seconds;
	}

	public function seconds() {
		return $this->seconds;
	}

	public function earth() {
		return $this->seconds() / 31557600;
	}

	public function mercury() {
		return $this->earth() / 0.2408467;
	}

	public function venus() {
		return $this->earth() / 0.61519726;
	}

	public function mars() {
		return $this->earth() / 1.8808158;
	}

	public function jupiter() {
		return $this->earth() / 11.862615;
	}

	public function saturn() {
		return $this->earth() / 29.447498;
	}

	public function uranus() {
		return $this->earth() / 84.016846;
	}

	public function neptune() {
		return $this->earth() / 164.79132;
	}
}