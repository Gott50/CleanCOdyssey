<?php

class Robot {
	public const DIRECTION_NORTH = 0;
	public const DIRECTION_EAST = 1;
	public const DIRECTION_SOUTH = 2;
	public const DIRECTION_WEST = 3;
	public $position;
	public $direction;

	/**
	 * Robot constructor.
	 *
	 * @param $position
	 * @param $direction
	 */
	public function __construct( $position, $direction ) {
		$this->position  = $position;
		$this->direction = $direction;
	}

	/**
	 * @param $string
	 */
	public function instructions( $string ) {
		foreach ( str_split( $string ) as $item ) {
			$this->instruction( $item );
		}
	}

	/**
	 * @param $string
	 */
	private function instruction( $string ) {
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

	/**
	 * @return Robot
	 */
	public function turnLeft():Robot {
		$this->direction += 3;
		$this->direction %= 4;

		return $this;
	}

	/**
	 * @return Robot
	 */
	public function turnRight():Robot {
		$this->direction ++;
		$this->direction %= 4;

		return $this;
	}

	/**
	 * @return Robot
	 */
	public function advance():Robot {
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