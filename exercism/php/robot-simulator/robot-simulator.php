<?php

class Robot {
	public const DIRECTION_NORTH = 0;
	public const DIRECTION_EAST = 1;
	public const DIRECTION_SOUTH = 2;
	public const DIRECTION_WEST = 3;
	public $position;
	public $direction;

	public function __construct( $position, $direction ) {
		$this->position  = $position;
		$this->direction = $direction;
	}

	public function instructions( $string ) {
		foreach ( str_split( $string ) as $item ) {
			$this->instruction( $item );
		}
	}

	public function instruction( $string ) {
		switch ( $string ) {
			case "L":
				$this->turnLeft();
				break;
			case "R":
				$this->turnRight();
				break;
			case "A":
				$this->advance();
				break;
			default:
				throw new InvalidArgumentException();
		}
	}

	public function turnLeft() {
		$this->direction += 3;
		$this->direction %= 4;

		return $this;
	}

	public function turnRight() {
		$this->direction ++;
		$this->direction %= 4;

		return $this;
	}

	public function advance() {
		switch ( $this->direction ) {
			case Robot::DIRECTION_NORTH:
				$this->position[1] ++;
				break;
			case Robot::DIRECTION_EAST:
				$this->position[0] ++;
				break;
			case Robot::DIRECTION_SOUTH:
				$this->position[1] --;
				break;
			case Robot::DIRECTION_WEST:
				$this->position[0] --;
				break;
		}

		return $this;
	}
}