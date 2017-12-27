<?php

class Game {
	private $rolls = array();

	public function score() {
		if ( sizeof( $this->rolls ) < 12 ) {
			throw new Exception();
		}

		$frames = $this->calculateFrames();

		if ( sizeof( $frames ) == 11 && $frames[9] < 10 ) {
			throw new Exception();
		}

		if ( sizeof( $frames ) == 11 ) {
			unset( $frames[10] );
		}

		return array_reduce( $frames, function ( $carry, $item ) {
			return $carry + $item;
		}, 0 );
	}

	/**
	 * @return array
	 * @throws Exception
	 */
	public function calculateFrames(): array {
		$frames = [];
		$next   = true;
		$go_on  = true;
		for ( $i = 0; $i < sizeof( $this->rolls ) && $go_on; $i ++ ) {
			$roll = $this->rolls[ $i ];

			if ( $next ) {
				list( $frames, $roll, $next, $go_on ) = $this->newFrame( $roll, $frames, $i, $next );
			} else {
				$frames = $this->updateFrame( $frames, $roll, $i );
			}
			$next = ! $next;
		}

		return $frames;
	}

	/**
	 * @param $frames
	 * @param $roll
	 * @param $i
	 *
	 * @return array
	 * @throws Exception
	 */
	public function updateFrame( $frames, $roll, $i ): array {
		if ( $frames[ sizeof( $frames ) - 1 ] < 10
		     && $frames[ sizeof( $frames ) - 1 ] + $roll > 10 ) {
			throw new Exception();
		}
		$frames[ sizeof( $frames ) - 1 ] += $roll;

		if ( $frames[ sizeof( $frames ) - 1 ] == 10 ) {
			if ( $i + 1 < sizeof( $this->rolls ) ) {
				$frames[ sizeof( $frames ) - 1 ] += $roll = $this->rolls[ $i + 1 ];
			} else {
				throw new Exception();
			}
		}

		return $frames;
	}

	public function roll( $int ) {
		if ( $int < 0 || $int > 10 ) {
			throw new Exception();
		}

		array_push( $this->rolls, $int );
	}

	/**
	 * @param $roll
	 * @param $frames
	 * @param $i
	 * @param $next
	 *
	 * @return array
	 * @throws Exception
	 */
	public function newFrame( $roll, $frames, $i, $next ): array {
		$go_on = true;

		array_push( $frames, $roll );
		if ( $frames[ sizeof( $frames ) - 1 ] == 10 ) {

			if ( sizeof( $frames ) < 10 ) {
				if ( $i + 1 < sizeof( $this->rolls ) ) {
					$frames[ sizeof( $frames ) - 1 ] += $roll = $this->rolls[ $i + 1 ];
				} else {
					throw new Exception();
				}
				if ( $i + 2 >= sizeof( $this->rolls ) ) {
					throw new Exception();
				}
				$frames[ sizeof( $frames ) - 1 ] += $roll = $this->rolls[ $i + 2 ];
				$next                            = ! $next;

				if ( $this->rolls[ $i + 1 ] != 10
				     && $this->rolls[ $i + 1 ] + $this->rolls[ $i + 2 ] > 10 ) {
					throw new Exception();
				}

			} elseif ( sizeof( $frames ) == 10 ) {
				if ( $i + 1 < sizeof( $this->rolls ) ) {
					$frames[ sizeof( $frames ) - 1 ] += $roll = $this->rolls[ $i + 1 ];
				} else {
					throw new Exception();
				}
				if ( $i + 2 >= sizeof( $this->rolls ) ) {
					throw new Exception();
				}
				$frames[ sizeof( $frames ) - 1 ] += $roll = $this->rolls[ $i + 2 ];
				$next                            = ! $next;

				if ( $this->rolls[ $i + 1 ] != 10
				     && $this->rolls[ $i + 1 ] + $this->rolls[ $i + 2 ] > 10 ) {
					throw new Exception();
				}

				$go_on = false;
			}
		}

		return array( $frames, $roll, $next, $go_on );
	}
}