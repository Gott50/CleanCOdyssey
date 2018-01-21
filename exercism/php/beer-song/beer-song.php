<?php

class BeerSong {
	/**
	 * @param array ...$numbers
	 *
	 * @return string
	 */
	public function verses( ...$numbers ) {
		$out = $this->verse( $numbers[0] );

		if ( sizeof( $numbers ) > 1 ) {
			for ( $i = $numbers[0] - 1; $i >= $numbers[1]; $i -- ) {
				$out .= "\n" . $this->verse( $i );
			}
		}

		return $out;
	}

	public function verse( $number ) {
		if($number == 0)
			return "No more bottles of beer on the wall, no more bottles of beer.\n" .
			       "Go to the store and buy some more, 99 bottles of beer on the wall.";

		return $this->getBottles( $number ) . " of beer on the wall, " .
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
		if($number == 0)
			return "no more bottles";

		return $number == 1 ? 1 . " bottle" : $number . " bottles";
	}

	/**
	 * @param $number
	 *
	 * @return string
	 */
	public function one_or_it( $number ): string {
		return $number == 1 ? "it" : "one";
	}

	/**
	 * @return string
	 */
	public function lyrics() {
		return $this->verses( 99, 0 );
	}
}