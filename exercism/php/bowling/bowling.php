<?php

class Game {
	private $rolls = array();

	/**
	 * @return int
	 * @throws Exception
	 */
	public function score(): int {
		$frames = $this->calculateFrames();

		if ( $this->isInvalid( $frames ) ) {
			throw new Exception();
		}

		return array_sum( array_slice($frames,0,10) );
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
		if ( $this->areAllPinsHit( $frames ) ) {
			list( $frames, $next ) = $this->calculateStrike( $frames, $i );
			if ( sizeof( $frames ) == 10 ) {
				$go_on = false;
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
	public function calculateStrike( $frames, $i ): array {
		$next = true;
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
		}

		return array( $frames, $next );
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

		if ( $this->areAllPinsHit( $frames ) ) {
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

	/**
	 * @param $frames
	 *
	 * @return bool
	 */
	public function areAllPinsHit( $frames ): bool {
		return $frames[ sizeof( $frames ) - 1 ] == 10;
	}

	/**
	 * @param $frames
	 *
	 * @return bool
	 */
	public function isInvalid( $frames ): bool {
		return sizeof( $this->rolls ) < 12 || sizeof( $frames ) == 11 && $frames[9] < 10;
	}
}