<?php

class BeerSong {
	/**
	 * @param array ...$numbers
	 *
	 * @return string
	 */
	public function verses( ...$numbers ) {
		$verse = function ($n){
			return $this->verse($n);
		};

		return implode("\n",array_map( $verse, $numbers ));
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
}