<?php

class BeerSong {
	/**
	 * @return string
	 */
	public function lyrics() {
		return $this->verses( 99, 0 );
	}

	/**
	 * @param $from
	 * @param int $to
	 *
	 * @return string
	 */
	public function verses( $from, $to = - 1 ) {
		$out = $this->verse( $from );

		if ( $to >= 0 ) {
			for ( $i = $from - 1; $i >= $to; $i -- ) {
				$out .= "\n" . $this->verse( $i );
			}
		}

		return $out;
	}

	public function verse( $number ) {
		return $number == 0 ?
			"No more bottles of beer on the wall, no more bottles of beer.\n" .
			"Go to the store and buy some more, 99 bottles of beer on the wall." :
			$this->getBottles( $number ) . " of beer on the wall, " .
			$this->getBottles( $number ) . " of beer.\n" .
			"Take " . $this->one_or_it( $number ) . " down and pass it around, " .
			$this->getBottles( $number - 1 ) . " of beer on the wall.\n";
	}

	/**
	 * @param $number
	 *
	 * @return string
	 */
	public function getBottles( $number ): string {
		return $number == 0 ? "no more bottles" :
			( $number == 1 ? 1 . " bottle" : $number . " bottles" );
	}

	/**
	 * @param $number
	 *
	 * @return string
	 */
	public function one_or_it( $number ): string {
		return $number == 1 ? "it" : "one";
	}
}