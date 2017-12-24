<?php

class Game {
	private $rolls = array();

	public function score() {
		if ( sizeof( $this->rolls ) <= 2 ) {
			throw new Exception();
		}

		$frames = [];
		$next   = true;
		for ( $i = 0; $i < sizeof( $this->rolls ); $i ++ ) {
			$roll = $this->rolls[ $i ];

			if ( $next ) {
				array_push( $frames, $roll );
				if ( $frames[ sizeof( $frames ) - 1 ] == 10 ) {
					echo $i;

					if ( sizeof( $frames ) <= 10 ) {
						if ( $i + 1 < sizeof( $this->rolls ) ) {
							$frames[ sizeof( $frames ) - 1 ] += $roll = $this->rolls[ $i + 1 ];
						} else {
							throw new Exception();
						}
						if ( $i + 2 < sizeof( $this->rolls ) ) {
							$frames[ sizeof( $frames ) - 1 ] += $roll = $this->rolls[ $i + 2 ];
						}
						$next = ! $next;
					}
				}
			} else {
				if ( $frames[ sizeof( $frames ) - 1 ] < 10
				     && $frames[ sizeof( $frames ) - 1 ] + $roll > 10 ) {
					throw new Exception();
				}
				$frames[ sizeof( $frames ) - 1 ] += $roll;

				if ( $frames[ sizeof( $frames ) - 1 ] == 10 ) {
					if ( $i + 1 < sizeof( $this->rolls ) ) {
						$frames[ sizeof( $frames ) - 1 ] += $roll = $this->rolls[ $i + 1 ];
					}
				}
			}
			$next = ! $next;
		}

		echo var_dump( $frames );
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

	public function roll( $int ) {
		if ( $int < 0 || $int > 10 ) {
			throw new Exception();
		}

		array_push( $this->rolls, $int );
	}
}