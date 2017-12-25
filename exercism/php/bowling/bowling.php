<?php

class Game {
	private $rolls = array();

	public function score() {
		if ( sizeof( $this->rolls ) < 12 ) {
			throw new Exception();
		}

		$frames = [];
		$next   = true;
		for ( $i = 0; $i < sizeof( $this->rolls ); $i ++ ) {
			$roll = $this->rolls[ $i ];

			if ( $next ) {
				array_push( $frames, $roll );
				if ( $frames[ sizeof( $frames ) - 1 ] == 10 ) {

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
						echo sizeof( $frames ) . ": " . $i . PHP_EOL;
						$next = ! $next;

						if ( $this->rolls[ $i + 1 ] != 10
						     && $this->rolls[ $i + 1 ] + $this->rolls[ $i + 2 ] > 10 ) {
							throw new Exception();
						}

						if ( sizeof( $frames ) == 10 ) {
							break;
						}
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
					} else {
						throw new Exception();
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