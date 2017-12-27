<?php

class Game {
	private $rolls = array();

	/**
	 * @return int
	 * @throws Exception
	 */
	public function score(): int {
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
		$frames          = [];
		$is_in_new_Frame = true;
		$go_on           = true;
		for ( $i = 0; $i < sizeof( $this->rolls ) && $go_on; $i ++ ) {
			if ( $is_in_new_Frame ) {
				list( $frames, $is_in_new_Frame, $go_on ) = $this->newFrame( $frames, $i );
			} else {
				$frames = $this->updateFrame( $frames, $i );
			}
			$is_in_new_Frame = ! $is_in_new_Frame;
		}

		return $frames;
	}

	/**
	 * @param $frames
	 * @param $i
	 *
	 * @return array
	 * @throws Exception
	 */
	public function newFrame( $frames, $i ): array {
		$roll  = $this->rolls[ $i ];
		$go_on = true;
		$next  = true;

		array_push( $frames, $roll );
		if ( $roll == 10 ) {

			if ( sizeof( $frames ) <= 10 ) {
				if ( $i + 1 < sizeof( $this->rolls ) ) {
					$frames[ sizeof( $frames ) - 1 ] += $roll = $this->rolls[ $i + 1 ];
				} else {
					throw new Exception();
				}
				if ( $i + 2 >= sizeof( $this->rolls ) ) {
					throw new Exception();
				}
				$frames[ sizeof( $frames ) - 1 ] += $roll = $this->rolls[ $i + 2 ];
				$next                            = false;

				if ( $this->rolls[ $i + 1 ] != 10
				     && $this->rolls[ $i + 1 ] + $this->rolls[ $i + 2 ] > 10 ) {
					throw new Exception();
				}

				if ( sizeof( $frames ) == 10 ) {
					$go_on = false;
				}
			}
		}

		return array( $frames, $next, $go_on );
	}

	/**
	 * @param $frames
	 * @param $i
	 *
	 * @return array
	 * @throws Exception
	 */
	public function updateFrame( $frames, $i ): array {
		$roll = $this->rolls[ $i ];
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

	/**
	 * @param $int
	 *
	 * @throws Exception
	 */
	public function roll( $int ) {
		if ( $int < 0 || $int > 10 ) {
			throw new Exception();
		}

		array_push( $this->rolls, $int );
	}
}